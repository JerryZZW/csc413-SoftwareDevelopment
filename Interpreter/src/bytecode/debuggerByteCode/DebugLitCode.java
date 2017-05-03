package bytecode.debuggerByteCode;

import interpreter.VirtualMachine;
import bytecode.LitCode;
import debugger.DebuggerVirtualMachine;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public class DebugLitCode extends LitCode {
    
    public void init(Vector<String> args){
        super.init(args);
    }
    
    public void execute(VirtualMachine vm) {
        super.execute(vm);
        DebuggerVirtualMachine dvm = (DebuggerVirtualMachine)vm;
        if (!id.equals("")) {
            int offset = dvm.getRunStack().getOffset();
            dvm.enter(id, offset);
        }
    }
}
