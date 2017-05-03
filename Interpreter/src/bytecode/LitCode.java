package bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public class LitCode extends ByteCode {
    
    protected int n;//value
    protected String id;//id
    
    public void init(Vector<String> args) {
        n = Integer.parseInt(args.get(0));
        if(args.size()>1){
            id = args.get(1);
        }else{
            id = "";
        }
    }
    
    public void execute(VirtualMachine vm) {
        //load the value onto the run time stack
        if("".equals(id)) {
            vm.runStack.push(n);
        } else {
            vm.runStack.push(0);
        }
        
        //check if the dump is on
        if("ON".equals(vm.dumpFlag)) {
            String output = "LIT " + n + " " + id;
            if(!id.equals("")){
                output = output + "    int " + id;
            }
            System.out.println(output);
        }
        
    }
}
