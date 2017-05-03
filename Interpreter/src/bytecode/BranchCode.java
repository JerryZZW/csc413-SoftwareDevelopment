package bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public abstract class BranchCode extends ByteCode {
    public abstract void init(Vector<String> args);
    public abstract void execute(VirtualMachine vm);
    public abstract int getTargetAddress();
    public abstract void setTargetAddress(int n);
    public abstract String getLabel();
}
