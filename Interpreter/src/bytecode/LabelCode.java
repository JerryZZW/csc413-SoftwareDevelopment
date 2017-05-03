package bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public class LabelCode extends ByteCode {
    
    private String label;//label
    
    
    public void init(Vector<String> args) {
        label = args.get(0);
    }
    
     public void execute(VirtualMachine vm) {
         
         //check if the dump is on
         if("ON".equals(vm.dumpFlag)) {
             System.out.println("LABEL" + " " + label);
         }
     }
     
     public String getLabel() {
        return label;
    }
}
