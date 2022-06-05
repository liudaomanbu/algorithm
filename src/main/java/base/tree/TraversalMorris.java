package base.tree;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

/**
 * @author caotc
 * @date 2022-04-12
 * @since 1.0.0
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TraversalMorris implements Traversal {

    public static final TraversalMorris INSTANCE = new TraversalMorris();

    @Override
    public <T> void pre(Node<T> node, Consumer<Node<T>> visitor) {
        Node<T> cur=node;
        Node<T> leftTreeMostRight=null;
        while (cur!=null){
            if(cur.left!=null){
                leftTreeMostRight=cur.left;
                while (leftTreeMostRight.right!=null && leftTreeMostRight.right != cur){
                    leftTreeMostRight=leftTreeMostRight.right;
                }

                //第二次访问
                if(leftTreeMostRight.right==cur){
                    leftTreeMostRight.right=null;
                    cur=cur.right;
                }else{ //第一次访问
                    leftTreeMostRight.right=cur;

                    visitor.accept(cur);

                    cur=cur.left;
                }
            }else{//左子树为空唯一一次访问
                visitor.accept(cur);
                cur=cur.right;
            }
        }
    }

    @Override
    public <T> void in(Node<T> node, Consumer<Node<T>> visitor) {
        Node<T> cur=node;
        while (cur!=null){
            if(cur.left!=null){
                Node<T> leftTreeMostRight=cur.left;
                while (leftTreeMostRight.right!=null && leftTreeMostRight.right != cur){
                    leftTreeMostRight=leftTreeMostRight.right;
                }

                //第二次访问
                if(leftTreeMostRight.right==cur){
                    leftTreeMostRight.right=null;

                    visitor.accept(cur);

                    cur=cur.right;
                }else{ //第一次访问
                    leftTreeMostRight.right=cur;
                    cur=cur.left;
                }
            }else{//左子树为空唯一一次访问
                visitor.accept(cur);
                cur=cur.right;
            }
        }
    }

    @Override
    public <T> void post(Node<T> node, Consumer<Node<T>> visitor) {
        Node<T> cur=node;
        while (cur!=null){
            if(cur.left!=null){
                Node<T> leftTreeMostRight=cur.left;
                while (leftTreeMostRight.right!=null && leftTreeMostRight.right != cur){
                    leftTreeMostRight=leftTreeMostRight.right;
                }

                //第二次访问
                if(leftTreeMostRight.right==cur){
                    leftTreeMostRight.right=null;

                    //逆序处理左子树右节点链表
                    visitorRightNodes(cur.left,visitor);

                    cur=cur.right;
                }else{ //第一次访问
                    leftTreeMostRight.right=cur;
                    cur=cur.left;
                }
            }else{//左子树为空唯一一次访问
                cur=cur.right;
            }
        }
        visitorRightNodes(node,visitor);
    }

    /**
     * 逆序处理右节点组成的单链表
     * @param node 需要逆序处理右节点的根节点
     * @param visitor 处理函数
     */
    private <T> void visitorRightNodes(Node<T> node, Consumer<Node<T>> visitor){
        Node<T> tail=reverse(node);
        Node<T> cur=tail;
        while (cur!=null){
            visitor.accept(cur);
            cur=cur.right;
        }
        reverse(tail);
    }

    private <T> Node<T> reverse(Node<T> node){
        Node<T> pre=null;
        Node<T> cur=node;
        Node<T> next;
        while (cur!=null){
            //记录原下一个节点
            next=cur.right;
            //反转
            cur.right=pre;

            //移到下一个节点
            pre=cur;
            cur=next;
        }
        //cur=null,pre为最后一个节点即反转后的头结点
        return pre;
    }
}
