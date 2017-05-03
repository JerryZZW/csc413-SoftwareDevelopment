package bytecode.debuggerByteCode;

import interpreter.VirtualMachine;
import bytecode.ByteCode;
import debugger.DebuggerVirtualMachine;
import java.util.Vector;


/**
 *
 * @author zhangzhewei
 */
public class FormalCode extends ByteCode {
    
    private String variable;
    private int offset;
    
    public void init(Vector<String> args){
        variable = args.get(0);
        offset = Integer.parseInt(args.get(1));
    }
    
    public void execute(VirtualMachine vm){
        DebuggerVirtualMachine dvm = (DebuggerVirtualMachine)vm;
        dvm.enter(variable, offset);
    }
    
    public String getVariable(){
        return variable;
    }
    
    public int getOffset(){
        return offset;
    }
}
