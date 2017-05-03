package bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public class GotoCode extends BranchCode {
    
    private String label;//label
    private int targetAddress;//target address
    
    public void init(Vector<String> args) {
         label = args.get(0);
    }
    
    public void execute(VirtualMachine vm) {
        //branch to the address
        vm.pc = targetAddress;
        
        //check if the dump is on
        if("ON".equals(vm.dumpFlag)) {
            System.out.println("GOTO" + " " + label);
        }
    }
    
    public int getTargetAddress(){
        return targetAddress;
    }
    
    public void setTargetAddress(int n){
        targetAddress = n;
    }
    
    public String getLabel(){
        return label;
    }
}
