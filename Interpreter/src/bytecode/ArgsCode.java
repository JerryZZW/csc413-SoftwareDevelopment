package bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public class ArgsCode extends ByteCode {
    
    private int n;//num of arguments
        
    public void init(Vector<String> args) {
        n = Integer.parseInt(args.get(0));
    }
    
    public void execute(VirtualMachine vm) {
        vm.runStack.newFrameAt(n);
        
        //check if the dump is on
        if("ON".equals(vm.dumpFlag)) {
            System.out.println("ARGS" + " " + n);
        }
    }
}
