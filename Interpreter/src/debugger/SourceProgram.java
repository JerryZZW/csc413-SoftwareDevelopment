package debugger;

/**
 *
 * @author zhangzhewei
 */
public class SourceProgram {
    private String sourceLine;//source program
    private Boolean isBreakptSet = false;//check if the source line is a break point
    private Boolean isValidBrkpt = false;//check if the source line is a valid break point
    
    SourceProgram(String str){
        sourceLine = str;
    }
    
    public String getSourceLine(){
        return sourceLine;
    }
        
    public void setSourceLine(String str){
        sourceLine = str;
    }
    
    public Boolean getIsBreakptSet(){
        return isBreakptSet;
    }
    
    public void setIsBreakptSet(Boolean bool){
        isBreakptSet = bool;
    }

    public boolean getIsValidBrkpt() {
        return isValidBrkpt;
    }

    public void setIsValidBrkpt() {
        isValidBrkpt = true;
    }
}
