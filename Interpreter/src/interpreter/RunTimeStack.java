package interpreter;

import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;

/**
 *
 * @author zhangzhewei
 */
public class RunTimeStack {
    
    private Stack<Integer> framePointers;//used to record prior frame pointers when calling functions
    private Vector<Integer> runStack;//used to hold the actual data
    
    public RunTimeStack() {
        framePointers = new Stack<Integer>();
        runStack = new Vector<Integer>();
        framePointers.push(0);
    }
    
   /**
   * This method is used to dump the runStack
   */
    public void dump() {
        Iterator iter = framePointers.iterator();
        int nextFrame, currentFrame = (Integer) iter.next();
        //print contents of runtime stack one frame at a time
        for (int i = 0; i < framePointers.size(); i++) {
            if (iter.hasNext()) {
                nextFrame = (Integer) iter.next();
            } else {
                nextFrame = runStack.size();
            }

            System.out.print("[");
            //print contents of current frame
            for (int j = currentFrame; j < nextFrame; j++) {
                System.out.print(runStack.get(j));
                if (j != nextFrame - 1) {
                    System.out.print(",");
                }
            }
            System.out.print("]");
            currentFrame = nextFrame;
        }
        System.out.println();
    }
    
    public int peek() {
        return runStack.lastElement();
    }
    
    public int pop() {
        int temp = runStack.lastElement();
        runStack.remove(runStack.size() - 1);
        return temp;
    }
    
    public int push(int i) {
        runStack.add(i);
        return i;
    }
    
   /**
   * This method is used to start a new frame
   * @param offset the offset from the start of the frame
   */
    public void newFrameAt(int offset) {
        framePointers.push(this.runStack.size() - offset);
    }
    
    /**
   * This method is used to pop the frame
   */
    public void popFrame() {
        int temp = this.peek();
        int temp2 = framePointers.pop();
        for (int i = runStack.size() - 1; i >= temp2; i--) {
            this.pop();
        }
        this.push(temp);
    }
    
   /**
   * This method is used to value into variabels
   * @param offset the offset from the start of the frame
   */
    public int store(int offset) {
        int temp = this.pop();
        runStack.set(framePointers.peek() + offset, temp);
        return temp;
    }
    
    /**
   * This method is used to load value of the variable onto the stack
   * @param offset the offset from the start of the frame
   */
    public int load(int offset) {
        int temp = runStack.get(framePointers.peek() + offset);
        runStack.add(temp);
        return temp;
    }
    
    public Integer push(Integer i) {
        runStack.add(i);
        return i;
    }
    
    public int getOffset() {
        return runStack.size() - framePointers.peek() - 1;
    }
    
    public int getValueAtOffset(int offset) {
        return runStack.elementAt(framePointers.peek() + offset);
    }
    
    public Boolean empty() {
        if (runStack.size() == 0) {
            return true;
        }
        return false;
    }
}

