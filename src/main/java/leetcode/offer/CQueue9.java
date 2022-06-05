package leetcode.offer;

import java.util.Stack;

/**
 * @author caotc
 * @date 2021-04-22
 * @since 1.0.0
 */
public class CQueue9 {
    Stack<Integer> pushStack = new Stack<>();
    Stack<Integer> popStack = new Stack<>();

    public CQueue9() {

    }

    public void appendTail(int value) {
        pushStack.push(value);
    }

    public int deleteHead() {
        if (!popStack.empty()) {
            return popStack.pop();
        }
        if(pushStack.empty()){
            return -1;
        }
        while (!pushStack.empty()){
            Integer value = pushStack.pop();
            popStack.push(value);
        }
        return popStack.pop();
    }
}
