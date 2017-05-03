package bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public class WriteCode extends ByteCode {

    public void init(Vector<String> args) {
        
    }
    
     public void execute(VirtualMachine vm) {
         System.out.println(vm.runStack.peek());
         
         //check if the dump is on
         if("ON".equals(vm.dumpFlag)) {
             System.out.println("WRITE");
         }
     }
}
