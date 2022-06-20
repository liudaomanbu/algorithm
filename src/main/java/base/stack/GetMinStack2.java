package base.stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * 使用2个栈完成,一个栈做正常功能,另一个栈存最小数.
 * push时只有newValue<getMIn()才push到最小数栈,pop时只有pop的值=最小数栈的getMIn,才pop最小数栈
 *
 * @author caotc
 * @date 2022-06-06
 * @since 1.0.0
 */
public class GetMinStack2<E extends Comparable<E>> extends GetMinStack<E> {
    Stack<E> delegate = new Stack<>();
    Stack<E> minStack = new Stack<>();

    @Override
    public E getMin() {
        return minStack.peek();
    }

    @Override
    public E push(E item) {
        E result = delegate.push(item);
        if (isEmpty() || item.compareTo(getMin()) < 0) {
            minStack.push(item);
        }
        return result;
    }

    @Override
    public synchronized E pop() {
        E result = delegate.pop();
        if (result.compareTo(getMin()) == 0) {
            minStack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println(scanner.nextLine());
    }
}
