package bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public class FalseBranchCode extends BranchCode {
    
    private String label;//label
    private int targetAddress;//target address
 
    public void init(Vector<String> args) {
        label = args.get(0);
        
    }
    
    public void execute(VirtualMachine vm) {
        //if it is false(0), branch to the address
        if(vm.runStack.pop() == 0) {
            vm.pc = targetAddress;
        }
        
        //check if the dump is on
        if("ON".equals(vm.dumpFlag)) {
            System.out.println("FALSEBRANCH " + " " + label);
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
