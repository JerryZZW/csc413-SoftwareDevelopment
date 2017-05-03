package interpreter;

import bytecode.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public class ByteCodeLoader {
    
    private BufferedReader source;//read the file
    
    public ByteCodeLoader(String codeFile) throws IOException {
       source = new BufferedReader(new FileReader(codeFile));
    }
    
   /**
   * This method is used to load the bytecodes to the program class.
   * And then address will be resolved by program.
   * @return the program class
   */
    public Program loadCodes() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException{ 
       Program program = new Program();       
       //The Vector args holds the arguments associated with each bytecode (i.e. LIT 0, 0 is args[0])
       Vector<String> args = new Vector<String>();
       //read first line of the program
       String code = source.readLine();
        
       while (code != null) {
            StringTokenizer tok = new StringTokenizer(code);
            args.clear(); //clear argument list on each iteration
            
            String codeClass = CodeTable.get(tok.nextToken());
            while(tok.hasMoreTokens()) {
                args.add(tok.nextToken());
            }
            
            ByteCode byteCode = (ByteCode)(Class.forName("bytecode."+codeClass).newInstance());
            //intitialize the bytecode instance with the arguments in args
            byteCode.init(args);            
            //add the bytecode to the ArrayList in Program.java that keeps track of each bytecode
            program.add(byteCode);
            //get the next line in the program
            code = source.readLine();
        }
       //resolve the target addresses for branching bytecodes
       program.resolve();  
       return program;
    }     
 
}
