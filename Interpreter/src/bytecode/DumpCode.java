package bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public class DumpCode extends ByteCode {
    
    private String dumpState;//dumping state
    
    public void init(Vector<String> args) {
        dumpState = args.get(0);
    }
    
     public void execute(VirtualMachine vm) {
         //set the dump flag
         vm.dumpFlag = dumpState;
         
     }
}
