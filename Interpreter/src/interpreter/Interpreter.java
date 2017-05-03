package interpreter;

import java.io.*;
import debugger.Debugger;

/**
 * <pre>
 * 
 *  
 *   
 *     Interpreter class runs the interpreter:
 *     1. Perform all initializations
 *     2. Load the bytecodes from file
 *     3. Run the virtual machine
 *     
 *   
 *  
 * </pre>
 */
public class Interpreter {

    protected ByteCodeLoader bcl;

    public Interpreter(String codeFile) {
        try {
            CodeTable.init();
            bcl = new ByteCodeLoader(codeFile);
        } catch (IOException e) {
            System.out.println("**** " + e);
        }
    }

    public void run() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
        Program program = bcl.loadCodes();
        VirtualMachine vm = new VirtualMachine(program);
        vm.executeProgram();
    }
    
    public Program getProgram() throws InstantiationException, IllegalAccessException, ClassNotFoundException, ClassNotFoundException, IOException {
        Program program = bcl.loadCodes();
        return program;
    }

    public static void main(String args[]) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, Exception {
        
        //check the running mode
        switch (args.length) {
            case 1://run the interpreter mode
                (new Interpreter(args[0])).run();
                break;
            case 2://run the debugger mode
                if (args[0].equals("-d")) {
                    (new Debugger(args[1]+".x", args[1]+".x.cod")).run();
                }
                break;
            default://exit program if file is not found
                System.out.println("***Incorrect usage, try: " + "java interpreter.Interpreter <file>");
                System.exit(1);
                break;
        }
       
    }
    
}