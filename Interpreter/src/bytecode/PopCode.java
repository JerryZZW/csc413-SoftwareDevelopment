package bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public class PopCode extends ByteCode {
    
    protected int n;//top n level

    public void init(Vector<String> args) {
        n = Integer.parseInt(args.get(0));
    }
    
    public void execute(VirtualMachine vm) {
        //pop top n level of the run time stack
        for(int i = 0; i < n; i++) {
            vm.runStack.pop();
        }
        
        //check if the dump is on
        if("ON".equals(vm.dumpFlag)) {
            System.out.println("POP" + " " + n);
        }
    }
}
