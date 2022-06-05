package base.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * @author caotc
 * @date 2022-05-04
 * @since 1.0.0
 */
public class Bfs1 implements Bfs{
    @Override
    public <T> void bfs(Node<T> node, Consumer<Node<T>> visitor) {
        Queue<Node<T>> queue=new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            Node<T> cur = queue.poll();
            visitor.accept(cur);

            if(cur.left!=null){
                queue.add(cur.left);
            }
            if(cur.right!=null){
                queue.add(cur.right);
            }
        }
    }
}
