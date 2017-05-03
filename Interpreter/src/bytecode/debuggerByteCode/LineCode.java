package bytecode.debuggerByteCode;

import interpreter.VirtualMachine;
import bytecode.ByteCode;
import debugger.DebuggerVirtualMachine;
import java.util.Vector;


/**
 *
 * @author zhangzhewei
 */
public class LineCode extends ByteCode {
    
    private int lineNum;
    
    public void init(Vector<String> args){
        lineNum = Integer.parseInt(args.get(0));       
    }
    
    public void execute(VirtualMachine vm) {
        DebuggerVirtualMachine dvm = (DebuggerVirtualMachine)vm;
        dvm.line(lineNum);   
    }
    
    public int getLineNum() {
        return lineNum;
    }
    
}
