package bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public abstract class ByteCode {
    
   /**
   * This method is used to initialize bytecode
   * @param args arguments
   */
    public abstract void init(Vector<String> args);
    
   /**
   * This method is used to execute bytecode
   * @param vm virtual machine
   */
    public abstract void execute(VirtualMachine vm);
}
