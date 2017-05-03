package interpreter;

/**
 *
 * @author zhangzhewei
 */
public class CodeTable {
    
    public static java.util.HashMap<String, String> codeTable = new java.util.HashMap<String,String>();
    
    //init the code table for the interpreter
    public static void init() {
        codeTable.put("HALT", "HaltCode");
        codeTable.put("POP", "PopCode");
        codeTable.put("FALSEBRANCH", "FalseBranchCode");
        codeTable.put("GOTO", "GotoCode");
        codeTable.put("STORE", "StoreCode");
        codeTable.put("LOAD", "LoadCode");
        codeTable.put("LIT", "LitCode");
        codeTable.put("ARGS", "ArgsCode");
        codeTable.put("CALL", "CallCode");
        codeTable.put("RETURN", "ReturnCode");
        codeTable.put("BOP", "BopCode");
        codeTable.put("READ", "ReadCode");
        codeTable.put("WRITE", "WriteCode");
        codeTable.put("LABEL", "LabelCode");
        codeTable.put("DUMP", "DumpCode");
    }
    
    //init the code table for the debugger
    public static void debugInit(){
        codeTable.put("POP", "debuggerByteCode.DebugPopCode");
        codeTable.put("LIT", "debuggerByteCode.DebugLitCode");
        codeTable.put("CALL", "debuggerByteCode.DebugCallCode");
        codeTable.put("RETURN", "debuggerByteCode.DebugReturnCode");
        codeTable.put("LINE", "debuggerByteCode.LineCode");
        codeTable.put("FUNCTION", "debuggerByteCode.FunctionCode");
        codeTable.put("FORMAL", "debuggerByteCode.FormalCode");
    }
    
    public static String get(String code) {
        return codeTable.get(code);
    }
}
