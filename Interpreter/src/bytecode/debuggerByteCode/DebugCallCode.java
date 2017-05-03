package bytecode.debuggerByteCode;

import interpreter.VirtualMachine;
import bytecode.CallCode;
import debugger.DebuggerVirtualMachine;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public class DebugCallCode extends CallCode {
    
    public void init(Vector<String> args){
        super.init(args);        
    }
    
    public void execute(VirtualMachine vm){
        super.execute(vm);
        DebuggerVirtualMachine dvm = (DebuggerVirtualMachine)vm;
        dvm.pushRecord();
    }
    
    public int getTargetAddress(){
        return super.targetAddress;
    }
    
    public void setTargetAddress(int n){
        super.targetAddress = n;
    }
    
    public String getLabel(){
        return super.funcName;
    }
}
