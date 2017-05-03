package bytecode.debuggerByteCode;

import interpreter.VirtualMachine;
import bytecode.PopCode;
import debugger.DebuggerVirtualMachine;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public class DebugPopCode extends PopCode {
    
    public void init(Vector<String> args){
        super.init(args);
    }
    
    public void execute(VirtualMachine vm){
        super.execute(vm);
        DebuggerVirtualMachine dvm = (DebuggerVirtualMachine)vm;
        dvm.popRecordVars(n);
    }
}
