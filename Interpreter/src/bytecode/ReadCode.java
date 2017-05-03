package bytecode;

import interpreter.VirtualMachine;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public class ReadCode extends ByteCode {
    
    public void init(Vector<String> args) {
        
    }
    
     public void execute(VirtualMachine vm) {
         //read integer from user
         System.out.print("Please enter an integer: ");
         
         try {
             BufferedReader in = new BufferedReader( new InputStreamReader(System.in ) );
             String line = in.readLine();
             vm.runStack.push(Integer.parseInt(line));
         } catch( java.io.IOException ex ) {   
         }
         
         //check if the dump is on
         if("ON".equals(vm.dumpFlag)) {
             System.out.println("READ");
         }
     }
}
