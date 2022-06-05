package leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/stack-of-plates-lcci/
 * 堆盘子。设想有一堆盘子，堆太高可能会倒下来。因此，在现实生活中，盘子堆到一定高度时，我们就会另外堆一堆盘子。请实现数据结构SetOfStacks，模拟这种行为。SetOfStacks应该由多个栈组成，并且在前一个栈填满时新建一个栈。此外，SetOfStacks.push()和SetOfStacks.pop()应该与普通栈的操作方法相同（也就是说，pop()返回的值，应该跟只有一个栈时的情况一样）。 进阶：实现一个popAt(int index)方法，根据指定的子栈，执行pop操作。
 * <p>
 * 当某个栈为空时，应当删除该栈。当栈中没有元素或不存在该栈时，pop，popAt 应返回 -1.
 * <p>
 *
 * @author caotc
 * @date 2022-05-24
 * @since 1.0.0
 */
public class StackOfPlates {
    List<Stack<Integer>> stacks = new LinkedList<>();
    int cap;

    public StackOfPlates(int cap) {
        this.cap = cap;
    }

    public void push(int val) {
        if (cap > 0) {
            if (stacks.isEmpty() || stacks.get(stacks.size() - 1).size() == cap) {
                stacks.add(new Stack<>());
            }
            Stack<Integer> stack = stacks.get(stacks.size() - 1);
            stack.push(val);
        }
    }

    public int pop() {
        return popAt(stacks.size() - 1);
    }

    public int popAt(int index) {
        if (index < 0 || index > (stacks.size() - 1)) {
            return -1;
        }
        Stack<Integer> stack = stacks.get(index);
        int value = stack.pop();
        if (stack.isEmpty()) {
            stacks.remove(index);
        }
        return value;
    }
}

