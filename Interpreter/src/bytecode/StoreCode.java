package bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public class StoreCode extends ByteCode {
    
    private int n;//offset
    private String id;//id
    private int value;//value
    
    public void init(Vector<String> args) {
        n = Integer.parseInt(args.get(0));
        id = args.get(1);
    }
    
    public void execute(VirtualMachine vm) {
        //store the value into variable
        value = vm.runStack.peek();
        vm.runStack.store(n);
        
        //check if the dump is on
        if("ON".equals(vm.dumpFlag)) {
            System.out.println("STORE " + n + " " + id + "    " + id + " = " + value);
        }
    }
}
