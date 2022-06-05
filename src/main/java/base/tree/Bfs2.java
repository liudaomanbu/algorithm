package base.tree;

import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author caotc
 * @date 2022-05-04
 * @since 1.0.0
 */
public class Bfs2 implements Bfs{
    @Override
    public <T> void bfs(Node<T> node, Consumer<Node<T>> visitor) {
        bfs(Lists.newArrayList(node),visitor);
    }

    private <T> void bfs(List<Node<T>> nodes, Consumer<Node<T>> visitor){
        if(!nodes.isEmpty()){
            List<Node<T>> nextNodes=new LinkedList<>();
            for (Node<T> cur:nodes){
                visitor.accept(cur);
                if(cur.left!=null){
                    nextNodes.add(cur.left);
                }
                if(cur.right!=null){
                    nextNodes.add(cur.right);
                }
            }
            bfs(nextNodes,visitor);
        }
    }
}
