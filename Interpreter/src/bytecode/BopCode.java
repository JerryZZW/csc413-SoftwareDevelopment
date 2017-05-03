package bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public class BopCode extends ByteCode {
    
    private String op;//operator
    
    public void init(Vector<String> args) {
        op = args.get(0);
    }
    
    public void execute(VirtualMachine vm) {
        //pop the two operands
        int op2 = vm.runStack.pop();
        int op1 = vm.runStack.pop();
        
        //execute the operation according to the operator
        switch(op) {
            case "+":
                vm.runStack.push(op1+op2);
                break;
            case "-":
                vm.runStack.push(op1-op2);
                break;
            case "/":
                vm.runStack.push(op1/op2);
                break;
            case "*":
                vm.runStack.push(op1*op2);
                break;
            case "==":
                if(op1 == op2) {
                    vm.runStack.push(1);
                } else {
                    vm.runStack.push(0);
                }
                break;
            case "!=":
                if(op1 != op2) {
                    vm.runStack.push(1);
                } else {
                    vm.runStack.push(0);
                }
                break;
            case "<=":
                if(op1 <= op2) {
                    vm.runStack.push(1);
                } else {
                    vm.runStack.push(0);
                }
                break;
            case ">":
                if(op1 > op2) {
                    vm.runStack.push(1);
                } else {
                    vm.runStack.push(0);
                }
                break;
            case ">=":
                if(op1 >= op2) {
                    vm.runStack.push(1);
                } else {
                    vm.runStack.push(0);
                }
                break;
            case "<":
                if(op1 < op2) {
                    vm.runStack.push(1);
                } else {
                    vm.runStack.push(0);
                }
                break;
        }
        
        //check if the dump is on
        if("ON".equals(vm.dumpFlag)) {
            System.out.println("BOP" + " " + op);
        }
    }
}
