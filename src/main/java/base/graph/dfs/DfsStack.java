package base.graph.dfs;

import base.graph.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * @author caotc
 * @date 2022-05-09
 * @since 1.0.0
 */
public class DfsStack implements Dfs {
    @Override
    public <T> void traversal(Node<T> node, Consumer<Node<T>> visitor) {
        Stack<Node<T>> stack = new Stack<>();
        Set<Node<T>> visited = new HashSet<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node<T> curNode = stack.pop();
            if (!visited.contains(curNode)) {
                visitor.accept(curNode);

                visited.add(curNode);

                //由于需要压栈模拟递归,所以必须逆向压入
                List<Node<T>> nextNodes = new ArrayList<>(curNode.nextNodes());
                for (int i = nextNodes.size() - 1; i >= 0; i--) {
                    Node<T> nextNode = nextNodes.get(i);
                    stack.push(nextNode);
                }
            }
        }
    }

}
