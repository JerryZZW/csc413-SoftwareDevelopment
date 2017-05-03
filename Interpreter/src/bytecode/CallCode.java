package bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public class CallCode extends BranchCode {
    
    protected String funcName;//function name
    protected int targetAddress;//target address
    protected int value;//value
    
    public void init(Vector<String> args) {
        funcName = args.get(0); 
    }
    
    public void execute(VirtualMachine vm) {
        //push the return address to the stack, go to the function
        vm.returnAddrs.push(vm.pc);
        vm.pc = targetAddress;
        value = vm.runStack.peek();
        
        //check if the dump is on
        if("ON".equals(vm.dumpFlag)) {
            int n = funcName.indexOf("<");
            String temp;
            if(n<0){
                temp = funcName;
            }else{
                temp = funcName.substring(0,n);
            }
            System.out.println("CALL " + funcName + "    " + temp + "(" + value + ")");
        }
    }
    
    public int getTargetAddress(){
        return targetAddress;
    }
    
    public void setTargetAddress(int n){
        targetAddress = n;
    }
    
    public String getLabel(){
        return funcName;
    }
}
