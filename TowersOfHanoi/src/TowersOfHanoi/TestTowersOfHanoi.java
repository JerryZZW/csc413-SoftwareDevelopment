/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TowersOfHanoi;

import java.util.*;
/**
 *
 * @author zhangzhewei
 */

class Disk {
    private String name;
    private int size;
    
    //default constructor
    public Disk() {
        name = "1";
        size = 1;
    }
    
    //parameterized constructor
    public Disk(String newName, int newSize) {
        name = newName;
        size = newSize;
    }
    
    //get name of a disk
    public String getName() {
        return name;
    }
    
    //get size of a disk
    public int getSize() {
        return size;
    }
    
}

class Peg {
    private String name;
    private Stack<Disk> pegStack;
    
    //default constructor
    public Peg() {
        name = "A";
        pegStack = new Stack<Disk>();
    }
    
    //parameterized constructor
    public Peg(String newName) {
        name = newName;
        pegStack = new Stack<Disk>();
    }
    
    //get name of a peg
    public String getName() {
        return name;
    }
    
    //get size of a peg stack
    public Stack<Disk> getPegStack() {
        return pegStack;
    }
    
    //generate disks in a peg stack
    public void generateDisksInPeg(int numOfDisk) {
        
        for(int i = numOfDisk; i > 0; i--) {
            Disk newDisk = new Disk(String.valueOf(i),i);
            pegStack.push(newDisk);
        }
    }
    
}

public class TestTowersOfHanoi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Check user input first
        if(!checkInput(args)) {
            System.exit(1);
        }
        
        //get user input and creat pegs
        int input = Integer.parseInt(args[0]);
        Peg p1 = new Peg("A");
        Peg p2 = new Peg("B");
        Peg p3 = new Peg("C");
        
        //generate disks in source peg according to user input
        p1.generateDisksInPeg(input);
        
        System.out.println("If " + input + " is input then the output should be:");
        System.out.println("Move"+ "\t"+"\t"+"\t" +"Peg Configuration");
        System.out.println("\t"+"\t"+"\t" + p1.getName() + "\t"+"\t" + p2.getName() + "\t"+"\t" + p3.getName());
        System.out.print("init" + "\t"+"\t"+"\t");
        
        //move disks from source to dest peg
        printPegs(p1, p2, p3);
        moveDisks(input, p1, p2, p3, p1, p2, p3);
        
        System.exit(0);
    }
    //end TestTowersOfHanoi
    
    //Check user input from the commend line arguement
    public static boolean checkInput(String[] Args) {
        int firstArg;
        
        if(Args.length > 0) {//If there is no commend line args, exit the program.
            try {
                firstArg = Integer.parseInt(Args[0]);
                if(firstArg >= 1 && firstArg <= 9) {//if commend line args >= 1 and <=9, return true, else exit the program.
                    return true;
                }
                else {
                    System.out.println("The argument must be greater than 1 and less 10.");
                    System.out.println("The program exits.");
                    return false;
                }
            }
            catch (NumberFormatException e) {//if commend line args is not an integer, exit the program.
                System.out.println("Argument " + Args[0] + " must be an integer.");
                System.out.println("Program exits.");
                return false;
            }
        }
        else {
            System.out.println("Please enter an commend line argument.");
            System.out.println("Program exits.");
            return false;
        }
    }
    //end checkInput
    
    //print the disks in the pegs
    public static void printPegs(Peg A, Peg B, Peg C) {
        Stack<Disk> tempStack = new Stack<Disk>();
        
        //print peg A
        while(!A.getPegStack().empty()) {
            tempStack.push(A.getPegStack().pop());
        }
        while(!tempStack.empty()) {
            System.out.print(tempStack.peek().getName());
            A.getPegStack().push(tempStack.pop());
        }
        System.out.print("\t"+"\t");
        
        //print peg B
        while(!B.getPegStack().empty()) {
            tempStack.push(B.getPegStack().pop());
        }
        while(!tempStack.empty()) {
            System.out.print(tempStack.peek().getName());
            B.getPegStack().push(tempStack.pop());
        }
        System.out.print("\t"+"\t");
        
        //print peg C
        while(!C.getPegStack().empty()) {
            tempStack.push(C.getPegStack().pop());
        }
        while(!tempStack.empty()) {
            System.out.print(tempStack.peek().getName());
            C.getPegStack().push(tempStack.pop());
        }
        System.out.println();
    }
    //end printSource
    
    //move disks between 3 pegs using recursive function
    public static void moveDisks(int topN, Peg from, Peg utility, Peg to, Peg A, Peg B, Peg C) {
        
        //base case of the recursive function
        if(topN == 1) {
            to.getPegStack().push(from.getPegStack().pop());
            System.out.print("1 from " + from.getName() + " to " + to.getName() + "\t"+"\t");
            printPegs(A, B, C);
        }
        else {//general case of the recursive function
            moveDisks(topN-1, from, to, utility, A, B, C);
            to.getPegStack().push(from.getPegStack().pop());
            System.out.print(topN + " from " + from.getName() + " to " + to.getName() + "\t"+"\t");
            printPegs(A, B, C);
            moveDisks(topN-1, utility, from, to, A, B, C);
        }
    }
    //end generateTowers
}
