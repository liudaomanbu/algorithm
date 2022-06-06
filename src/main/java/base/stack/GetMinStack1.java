package base.stack;

import java.util.Stack;

/**
 * 使用2个栈完成,一个栈做正常功能,另一个栈存最小数.
 * 保持两个栈大小相等,每次push时,最小数栈压入min(getMin(),newValue),pop时两个栈一起pop
 *
 * @author caotc
 * @date 2022-06-06
 * @since 1.0.0
 */
public class GetMinStack1<E extends Comparable<E>> extends GetMinStack<E> {
    Stack<E> delegate = new Stack<>();
    Stack<E> minStack = new Stack<>();

    @Override
    public E getMin() {
        return minStack.peek();
    }

    @Override
    public E push(E item) {
        E result = delegate.push(item);
        if(isEmpty()){
            minStack.push(item);
        }else{
            minStack.push(item.compareTo(getMin()) < 0 ? item : getMin());
        }
        return result;
    }

    @Override
    public synchronized E pop() {
        E result = delegate.pop();
        minStack.pop();
        return result;
    }
}
