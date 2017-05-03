package bytecode.debuggerByteCode;

import interpreter.VirtualMachine;
import bytecode.ByteCode;
import debugger.DebuggerVirtualMachine;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public class FunctionCode extends ByteCode {
    
    private String name;
    private int start;
    private int end;
    
    public void init(Vector<String> args){
        name = args.get(0);
        start = Integer.parseInt(args.get(1)); 
        end = Integer.parseInt(args.get(2));
    }
    
    public void execute(VirtualMachine vm){
        DebuggerVirtualMachine dvm = (DebuggerVirtualMachine)vm;
        dvm.function(name, start, end);
    }
    
    public String getName(){
        return name;
    }
    
}
