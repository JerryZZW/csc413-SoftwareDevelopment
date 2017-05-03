package bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public class LoadCode extends ByteCode {
    
    private int n;//value
    private String id;//id
    
    public void init(Vector<String> args) {
        n = Integer.parseInt(args.get(0));
        id = args.get(1);
    }
    
    public void execute(VirtualMachine vm) {
        //load the value onto the run time stack
        vm.runStack.load(n);
        
        //check if the dump is on
        if("ON".equals(vm.dumpFlag)) {
            System.out.println("LOAD " + n + " " + id + "    <load " + id + ">");
        }
    }
}
