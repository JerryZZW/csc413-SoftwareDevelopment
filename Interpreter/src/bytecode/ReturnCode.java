package bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public class ReturnCode extends ByteCode {
    
    protected String funcName;//function name
    protected int retVal;
    
    public void init(Vector<String> args) {
        if(args.size()>0){
            funcName = args.get(0);
        }else{
            funcName = "";
        }
    }
    
    public void execute(VirtualMachine vm) {
        //return the return address
        vm.pc = vm.returnAddrs.pop();
        vm.runStack.popFrame();
        retVal = vm.runStack.peek();
        
        ////check if the dump is on
        if("ON".equals(vm.dumpFlag)) {
            int n = funcName.indexOf("<");
            String temp;
            if(n<0){
                temp = funcName;
            }else{
                temp = funcName.substring(0,n);
            }
            System.out.println("RETURN " + funcName + "    exit " + temp + ": " + retVal);
        }
        
    }
}
