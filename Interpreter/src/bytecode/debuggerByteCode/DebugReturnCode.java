package bytecode.debuggerByteCode;

import interpreter.VirtualMachine;
import bytecode.ReturnCode;
import debugger.DebuggerVirtualMachine;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public class DebugReturnCode extends ReturnCode {
    
    public void init(Vector<String> args){
        super.init(args);
    }
    
    public void execute(VirtualMachine vm){ 
        super.execute(vm);
        DebuggerVirtualMachine dvm = (DebuggerVirtualMachine)vm;
        dvm.popFER(retVal);
    }
}
