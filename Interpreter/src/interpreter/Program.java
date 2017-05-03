package interpreter;

import bytecode.*;
import java.util.ArrayList;

/**
 *
 * @author zhangzhewei
 */
public class Program {
    
    //used to hold the bytecode instance
    private ArrayList<ByteCode> byteCodeList = new ArrayList<ByteCode>();
    //labelTable is the hashmap used to resolve target addresses of branching bytecodes key = label, value = target address 
    private static java.util.HashMap<String, Integer> labelTable = new java.util.HashMap<String, Integer>();
    
   /**
   * This method is used to add the bytecode instance to the program class
   */
    public void add(ByteCode byteCode) {
        if (byteCode instanceof LabelCode){
            LabelCode label = (LabelCode)byteCode;
            labelTable.put(label.getLabel(), byteCodeList.size());
        }
        byteCodeList.add(byteCode);
    }
    
   /**
   * This method is used to load the bytecodes to the program class.
   * And then address will be resolved by program.
   * @param pc bytecode index
   * @return the bytecode accroding to bytecode index
   */
    public ByteCode getCode(int pc) {
        return byteCodeList.get(pc);
    }
    
    /**
   * This method is used to resolve address for each bytecode instance in the program class
   */
    public void resolve() {
        Integer jumpAddress;
        for (int i=0; i < byteCodeList.size(); i++) {            
            if (byteCodeList.get(i) instanceof BranchCode){
                BranchCode temp = (BranchCode)byteCodeList.get(i);
                jumpAddress = new Integer(labelTable.get(temp.getLabel()));
                temp.setTargetAddress(jumpAddress.intValue());
            }          
        }
    }
    
    public ArrayList<ByteCode> getByteCodeList() {
        return byteCodeList;
    }
}
