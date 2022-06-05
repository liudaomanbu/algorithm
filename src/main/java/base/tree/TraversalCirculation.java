package base.tree;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Stack;
import java.util.function.Consumer;

/**
 * @author caotc
 * @date 2022-04-12
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TraversalCirculation implements Traversal {

    public static final TraversalCirculation INSTANCE = new TraversalCirculation();

    @Override
    public <T> void pre(Node<T> node, Consumer<Node<T>> visitor) {
        Stack<Node<T>> stack=new Stack<>();
        Node<T> cur=node;
        while (!stack.isEmpty() || cur!=null){
            while (cur!=null){
                //从上向左过程中,直接遍历即为该部分先序遍历顺序
                visitor.accept(cur);
                stack.push(cur);
                cur=cur.left;
            }

            //逆序从左向上弹出节点,弹出的节点已经被处理过
            cur=stack.pop();
            //已经被处理过的节点直接转向到右节点,处理右节点左树
            cur=cur.right;
        }
    }

    @Override
    public <T> void in(Node<T> node, Consumer<Node<T>> visitor) {
        Stack<Node<T>> stack=new Stack<>();
        Node<T> cur=node;
        while (!stack.isEmpty() || cur!=null){
            while (cur!=null){
                stack.push(cur);
                cur=cur.left;
            }

            //逆序从左向上弹出节点
            cur=stack.pop();
            //从左向上过程中,直接遍历即为该部分中序遍历顺序
            visitor.accept(cur);
            //节点处理后直接转向到右节点,处理右节点左树
            cur=cur.right;
        }
    }

    @Override
    public <T> void post(Node<T> node, Consumer<Node<T>> visitor) {
        Stack<Node<T>> stack=new Stack<>();
        Node<T> cur=node;
        //处理节点时上次的处理节点
        Node<T> last=null;
        while (!stack.isEmpty() || cur!=null){
            while (cur!=null){
                stack.push(cur);
                cur=cur.left;
            }
            //查看栈顶节点
            cur=stack.peek();

            //如果该节点无右节点或者右节点已经被处理过
            if(cur.right==null || cur.right==last){
                //可以逆序弹出节点并处理
                cur=stack.pop();
                visitor.accept(cur);
                //记录访问节点
                last=cur;
                // 表示不需要转向，继续弹栈
                cur = null;
            }else{
                //有右节点并且右节点尚未被处理过时需要先转向处理右节点
                cur=cur.right;
            }
        }
    }

}
