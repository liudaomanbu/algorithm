package base.stack;

import java.util.Stack;

/**
 * 实现有getMin功能的栈
 * @author caotc
 * @date 2022-06-06
 * @since 1.0.0
 */
public abstract class GetMinStack<E extends Comparable<E>> extends Stack<E> {
    public abstract E getMin();
}
