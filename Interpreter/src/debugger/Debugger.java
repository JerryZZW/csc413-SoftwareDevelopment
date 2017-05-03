package debugger;

import debugger.DebuggerVirtualMachine;
import debugger.ui.DebuggerUI;
import interpreter.CodeTable;
import interpreter.Interpreter;
import interpreter.Program;
import java.io.IOException;

/**
 *
 * @author zhangzhewei
 */
public class Debugger {
    
    private DebuggerVirtualMachine dvm;//debugger vitural machine
    private DebuggerUI ui;//debugger UI
    
    public Debugger(String sourcefile, String codefile) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException{
        Interpreter interpreter = new Interpreter(codefile);        
        CodeTable.debugInit();//initialize debugger code table
        Program program = interpreter.getProgram();
        dvm = new DebuggerVirtualMachine(program, sourcefile);//run debugger vitural machine
        ui = new DebuggerUI(dvm);//run debugger UI
    }
    
    /**
     * This method is used to run debugger UI
     * @throws IOException
     * @throws Exception 
     */
    public void run() throws IOException, Exception{
        ui.runUI();
    } 
    
}
