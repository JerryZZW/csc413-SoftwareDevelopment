package debugger.ui;

import debugger.DebuggerVirtualMachine;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author zhangzhewei
 */
public class DebuggerUI {
    
    private DebuggerVirtualMachine dvm;//debugger virtual machine
    private boolean isDebugRunning;//check if the debugger is running

    public DebuggerUI(DebuggerVirtualMachine dvm) {
        this.dvm = dvm;
    }
    
    /**
     * This method is used to run the debugger UI
     */
    public void runUI() {
        isDebugRunning = true;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        printSource();
        while (isDebugRunning) {
            //check if HaltCode reached
            if (dvm.getDone()) {
                isDebugRunning = false;
                System.out.println("Execution complete\n");
            } else { //print prompt, take in command and execute
                System.out.print(">> ");
                try {
                    line = in.readLine();
                    executeCommand(line);
                } catch (Exception e) {
                    System.out.println("***ERROR: " + e + "\n");
                }
            }
        }
    }
    
    /**
     * This method is used to execute the commands from the user
     */
    public void executeCommand(String line) {
        StringTokenizer tok = new StringTokenizer(line);
        try {
            while (tok.hasMoreTokens()) {
                String str = tok.nextToken();
                if (str.equals("?")) {
                    helpMenu();
                } else if (str.equals("sb")) {
                    int i;
                    while (tok.hasMoreTokens()) {
                        i = (int) Integer.parseInt(tok.nextToken());
                        setBreaks(i);
                    }
                    System.out.println();
                } else if (str.equals("cb")) {
                    if (!tok.hasMoreTokens()) {
                        clearBreaks();
                    }
                    int i;
                    while (tok.hasMoreTokens()) {
                        i = (int) Integer.parseInt(tok.nextToken());
                        clearBreaks(i);
                    }
                    System.out.println();
                } else if (str.equals("df")) {
                    displayFunc();
                } else if (str.equals("dv")) {
                    displayVars();
                } else if (str.equals("c")) {
                    continueExec();
                } else if (str.equals("q")) {
                    quit();
                } else if (str.equals("so")) {
                    stepOut();
                } else if (str.equals("li")) {
                    listBreaks();
                } else if (str.equals("ds")) {
                    displayStack();
                } else if (str.equals("sr")) {
                    stepOver();
                } else if (str.equals("si")) {
                    stepIn();
                } else if (str.equals("t")) {
                    if (tok.hasMoreTokens()) {
                        trace(tok.nextToken());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("***ERROR: " + e + "\n");
        }
    }
    
    /**
     * This method is used to print out the help menu
     */
    public void helpMenu() {
        System.out.println("Help Menu:");
        System.out.printf("%-25s %s \n", "sb <line number(s)>", ": Set breakpoints at specified lines (e.g: sb 3, sb 3 6)");
        System.out.printf("%-25s %s \n", "cb <line number(s)>", ": Clear breakpoints at specified lines (e.g: cb 3, cb 3 6, cb )");
        System.out.printf("%-25s %s \n", "li", ": List current breakpoints");
        System.out.printf("%-25s %s \n", "df", ": Display current function");
        System.out.printf("%-25s %s \n", "dv", ": Display local variables in current function");
        System.out.printf("%-25s %s \n", "ds", ": Display call stack");
        System.out.printf("%-25s %s \n", "sr", ": Step over current line");
        System.out.printf("%-25s %s \n", "si", ": Step into function on current line");
        System.out.printf("%-25s %s \n", "so", ": Step out of current function");
        System.out.printf("%-25s %s \n", "t <on/off>", ": Turn tracing ON/OFF (e.g: t on, t off)");
        System.out.printf("%-25s %s \n", "c", ": continue execution");
        System.out.printf("%-25s %s \n\n", "q", ": quit execution");
    }

    public void setBreaks(int line) {
        System.out.println(dvm.setBreakpt(line));
    }

    public void clearBreaks() {
        System.out.println(dvm.clearBreakpt());
    }

    public void clearBreaks(int line) {
        System.out.println(dvm.clearBreakpt(line));
    }
    
    public void displayFunc() {
        System.out.println("Current Function:\n\n" + dvm.displayFunc());
    }

    public void continueExec() {
        System.out.println(dvm.continueExec() + "\n");
        dvm.executeProgram();
        //The trace is displayed whenever control is passed back to the user 
        //and trace is ON
        if (dvm.getTrace()) {
            printTrace();
        }
        System.out.println(dvm.displayFunc());
    }

    public void quit() {
        System.out.println("Exit Debugger\n");
        isDebugRunning = false;
    }

    public void displayVars() {
        System.out.println(dvm.displayVars() + "\n");
    }
    
    public void stepOut() {
        System.out.println(dvm.stepOut());
        if (dvm.getTrace()) {
            printTrace();
        }
        System.out.println(dvm.displayFunc());
    }
    
    public void stepOver() {
        System.out.println(dvm.stepOver() + "\n");
        if (dvm.getTrace()) {
            printTrace();
        }
        System.out.println(dvm.displayFunc());
    }
    
    public void stepIn() {
        System.out.println(dvm.stepIn() + "\n");
        if (dvm.getTrace()) {
            printTrace();
        }
        System.out.println(dvm.displayFunc());
    }

    public void printSource() {
        System.out.println(dvm.printSource());
    }
    
    public void listBreaks() {
        System.out.println(dvm.listBreaks() + "\n");
    }
    
    public void displayStack() {
        System.out.println(dvm.displayStack());
    }
    
    public void trace(String str) {
        System.out.println(dvm.trace(str) + "\n");
    }
    
    public void printTrace() {
        System.out.println(dvm.printTrace());
    }
}
