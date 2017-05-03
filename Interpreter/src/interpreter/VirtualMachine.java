package interpreter;

import bytecode.ByteCode;
import bytecode.DumpCode;
import java.util.Stack;

/**
 *
 * @author zhangzhewei
 */
public class VirtualMachine {
    
    public RunTimeStack runStack;//run time stack
    public int pc;//bytecode index
    public Stack<Integer> returnAddrs;//return address
    public boolean isRunning;//check if the program is running
    public Program program;
    public String dumpFlag = "OFF";//set the dumpFlag to "OFF" by default
    
    public VirtualMachine(Program program) {
        this.program = program;
    }
    
   /**
   * This method is used to execute the program
   */
    public void executeProgram() {
        pc = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack<Integer>();
        isRunning = true;
        
        while(isRunning) {
            ByteCode code = program.getCode(pc);
            code.execute(this);
            if("ON".equals(dumpFlag) && !(code instanceof DumpCode)) {
                runStack.dump();
            }
            pc++;
        }
    }
}
