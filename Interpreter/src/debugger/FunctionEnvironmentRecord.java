package debugger;

import java.util.Set;
import java.util.Iterator;

/**
 *
 * @author Zhewei Zhang
 */
public class FunctionEnvironmentRecord {
    
    private Table symbolTable;//the table used to store symbol/value pairs
    private String funcName;//the name of the function
    private int startLine;//the starting line number
    private int endLine;//the ending line number
    private int currentLine;//the current line number
    
    FunctionEnvironmentRecord() {
        symbolTable = new Table();
        funcName = "-";
        startLine = 0;
        endLine = 0;
        currentLine = 0;
    }
    
    /**
     * This method is used to set the begin scope for the symbol table
     */
    public void beginScope() {
        symbolTable.beginScope();
    }
    
    /**
     * This method is used to set the name,starting/ending line number of the function
     * @param name name of the function
     * @param start the starting line number
     * @param end the ending line number
     */
    public void setFunctionInfo(String name, int start, int end) {
        funcName = name;
        startLine = start;
        endLine = end;
    }
    
    /**
     * This method is used to enter the var/value pair in the symbol table
     * @param var name of the variable
     * @param value value of the variable
     */
    public void setVarVal(String var, Object value) {
        symbolTable.put(var, value);
    }
    
    /**
     * This method is used to pop the last n var/value pairs from the symbol table
     * @param n the last n var/value pairs that needs to be poped
     */
    public void doPop(int n) {
        for(int i = 0; i < n; i++) {
            symbolTable.endScope();
        }
    }
    
    /**
     * This method is used to dump the function environment record
     */
    public void dumpFER() {
        Set keys =  symbolTable.keys();//get all the keys from the symbol table
        Iterator iter = keys.iterator();//use the keys as a iterator
        
        //print the var/value paris in the symbol table 
        System.out.print("(<");
        if (iter.hasNext()) {
            while (iter.hasNext()) {
                String s = (String) iter.next();
                System.out.print(s + "/" + symbolTable.get(s));
                if (iter.hasNext()) {
                    System.out.print(",");
                }
            }
        }
        //print the fucntion information including name, starting/ending/current line number
        System.out.print(">," + getFuncName() + ",");
        if (getStartLine() == 0) {
            System.out.print("-,");
        } else {
            System.out.print(getStartLine() + ",");
        }
        if (getEndLine() == 0) {
            System.out.print("-,");
        } else {
            System.out.print(getEndLine() + ",");
        }
        if (getCurrentLine() == 0) {
            System.out.println("-)");
        } else {
            System.out.println(getCurrentLine() + ")");
        }
    }
    
    public Table getTable() {
        return symbolTable;
    }

    public String getFuncName() {
        return funcName;
    }

    public int getStartLine() {
        return startLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public int getCurrentLine() {
        return currentLine;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }

    public void setCurrentLine(int currentLine) {
        this.currentLine = currentLine;
    }
    
    /*
    public static void main(String[] args) {
        FunctionEnvironmentRecord fctEnvRecord = new FunctionEnvironmentRecord();
        
        //Test case starts here
        System.out.print("BS\t\t");
        //BS
        fctEnvRecord.symbolTable.beginScope();
        fctEnvRecord.dumpFER();
        //Function g 1 20
        System.out.print("Function g 1 20\t");
        fctEnvRecord.setFunctionInfo("g", 1, 20);
        fctEnvRecord.dumpFER();
        //Line 5
        System.out.print("Line 5\t\t");
        fctEnvRecord.setCurrentLine(5);
        fctEnvRecord.dumpFER();
        //Enter a 4
        System.out.print("Enter a 4\t");
        fctEnvRecord.setVarVal("a", 4);
        fctEnvRecord.dumpFER();
        //Enter b 2
        System.out.print("Enter b 2\t");
        fctEnvRecord.setVarVal("b", 2);
        fctEnvRecord.dumpFER();
        //Enter c 7
        System.out.print("Enter c 7\t");
        fctEnvRecord.setVarVal("c", 7);
        fctEnvRecord.dumpFER();
        //Enter a 1
        System.out.print("Enter a 1\t");
        fctEnvRecord.setVarVal("a", 1);
        fctEnvRecord.dumpFER();
        //Pop 2
        System.out.print("Pop 2\t\t");
        fctEnvRecord.doPop(2);
        fctEnvRecord.dumpFER();
        //Pop 1
        System.out.print("Pop 1\t\t");
        fctEnvRecord.doPop(1);
        fctEnvRecord.dumpFER();
        //end test case
    }
    */
    
}

/** <pre>
 *  Binder objects group 3 fields
 *  1. a value
 *  2. the next link in the chain of symbols in the current scope
 *  3. the next link of a previous Binder for the same identifier
 *     in a previous scope
 *  </pre>
*/
class Binder {
  private Object value;
  private String prevtop;   // prior symbol in same scope
  private Binder tail;      // prior binder for same symbol
                            // restore this when closing scope
  Binder(Object v, String p, Binder t) {
	value=v; prevtop=p; tail=t;
  }

  Object getValue() { return value; }
  String getPrevtop() { return prevtop; }
  Binder getTail() { return tail; }
}


/** <pre>
 * The Table class is similar to java.util.Dictionary, except that
 * each key must be a Symbol and there is a scope mechanism.
 *
 * Consider the following sequence of events for table t:
 * t.put(Symbol("a"),5)
 * t.beginScope()
 * t.put(Symbol("b"),7)
 * t.put(Symbol("a"),9)
 * 
 * symbols will have the key/value pairs for Symbols "a" and "b" as:
 * 
 * Symbol("a") ->
 *     Binder(9, Symbol("b") , Binder(5, null, null) )
 * (the second field has a reference to the prior Symbol added in this
 * scope; the third field refers to the Binder for the Symbol("a")
 * included in the prior scope)
 * Binder has 2 linked lists - the second field contains list of symbols
 * added to the current scope; the third field contains the list of
 * Binders for the Symbols with the same string id - in this case, "a"
 * 
 * Symbol("b") ->
 *     Binder(7, null, null)
 * (the second field is null since there are no other symbols to link
 * in this scope; the third field is null since there is no Symbol("b")
 * in prior scopes)
 * 
 * top has a reference to Symbol("a") which was the last symbol added
 * to current scope
 * 
 * Note: What happens if a symbol is defined twice in the same scope??
 * </pre>
*/
class Table {

  private java.util.HashMap<String,Binder> symbols = new java.util.HashMap<String,Binder>();
  private String top;    // reference to last symbol added to
                         // current scope; this essentially is the
                         // start of a linked list of symbols in scope
  private Binder marks;  // scope mark; essentially we have a stack of
                         // marks - push for new scope; pop when closing
                         // scope

  public Table(){}

 /**
  * Gets the object associated with the specified symbol in the Table.
  */
  public Object get(String key) {
	Binder e = symbols.get(key);
	return e.getValue();
  }

 /**
  * Puts the specified value into the Table, bound to the specified Symbol.<br>
  * Maintain the list of symbols in the current scope (top);<br>
  * Add to list of symbols in prior scope with the same string identifier
  */
  public void put(String key, Object value) {
	symbols.put(key, new Binder(value, top, symbols.get(key)));
	top = key;
  }

 /**
  * Remembers the current state of the Table; push new mark on mark stack
  */
  public void beginScope() {
    marks = new Binder(null,top,marks);
    top=null;
  }

 /**
  * Restores the table to what it was at the most recent beginScope
  *	that has not already been ended.
  */
  public void endScope() {
	if (top!=null) {
	   Binder e = symbols.get(top);
	   if (e.getTail()!=null) symbols.put(top,e.getTail());
	   else symbols.remove(top);
	   top = e.getPrevtop();
	}
  }

  /**
   * @return a set of the Table's symbols.
   */
  public java.util.Set<String> keys() {return symbols.keySet();}
}
