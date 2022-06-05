package base.tree;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Consumer;

/**
 * @author caotc
 * @date 2022-04-12
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TraversalCirculation2 implements Traversal {

    public static final TraversalCirculation2 INSTANCE = new TraversalCirculation2();

    @Override
    public <T> void pre(Node<T> node, Consumer<Node<T>> visitor) {
        if(node==null){
            return;
        }
        Deque<Node<T>> stack=new LinkedList<>();
        stack.push(node);
        while (!stack.isEmpty()){
            Node<T> n = stack.pop();
            if(n!=null){
                if(n.right!=null){
                    stack.push(n.right);
                }
                if(n.left!=null){
                    stack.push(n.left);
                }
                stack.push(n);
                //标记根节点入栈,弹出为null时下一个即为根节点,需要对根节点进行操作
                stack.push(null);
            }else{
                visitor.accept(stack.pop());
            }
        }
    }

    @Override
    public <T> void in(Node<T> node, Consumer<Node<T>> visitor) {
        if(node==null){
            return;
        }
        Deque<Node<T>> stack=new LinkedList<>();
        stack.push(node);
        while (!stack.isEmpty()){
            Node<T> n = stack.pop();
            if(n!=null){
                if(n.right!=null){
                    stack.push(n.right);
                }
                stack.push(n);
                //标记根节点入栈,弹出为null时下一个即为根节点,需要对根节点进行操作
                stack.push(null);

                if(n.left!=null){
                    stack.push(n.left);
                }

            }else{
                visitor.accept(stack.pop());
            }
        }
    }

    @Override
    public <T> void post(Node<T> node, Consumer<Node<T>> visitor) {
        if(node==null){
            return;
        }
        Deque<Node<T>> stack=new LinkedList<>();
        stack.push(node);
        while (!stack.isEmpty()){
            Node<T> n = stack.pop();
            if(n!=null){
                stack.push(n);
                //标记根节点入栈,弹出为null时下一个即为根节点,需要对根节点进行操作
                stack.push(null);

                if(n.right!=null){
                    stack.push(n.right);
                }
                if(n.left!=null){
                    stack.push(n.left);
                }
            }else{
                visitor.accept(stack.pop());
            }
        }
    }

}
