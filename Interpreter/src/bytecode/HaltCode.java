package bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public class HaltCode extends ByteCode {
    
    public void init(Vector<String> args) {
        
    }
    
    public void execute(VirtualMachine vm) {
        //end the program
        vm.isRunning = false;
        
        //check if the dump is on
        if("ON".equals(vm.dumpFlag)) {
            System.out.println("HALT");
        }
    }
}
