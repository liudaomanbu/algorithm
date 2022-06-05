package base.graph.sort.topology;

import base.graph.Graph;
import base.graph.Node;

import java.util.List;

/**
 * 给定一个代表编译依赖关系的有向图,输出编译顺序.
 * <p>
 * 不能直接写一个visitor然后使用{@link base.graph.dfs.Dfs}或{@link base.graph.bfs.Bfs}来完成遍历
 * 因为图内有环,所以Dfs和Bfs内部都会标记是否遍历过,来避免重复遍历.
 * 但是拓扑排序中的遍历标准是需要以自己的入度处理有关的.
 * 如果使用Dfs和Bfs来完成遍历,可能会出现Node被遍历到时入度不为0,但后续处理后该Node入度为0,导致遗漏的问题
 *
 * @author caotc
 * @date 2022-05-06
 * @since 1.0.0
 */
public interface TopologySort {
    <T> List<Node<T>> sortedTopology(Graph<T> graph);

    default boolean hasCycle(Graph<?> graph) {
        return sortedTopology(graph).size() != graph.nodes.size();
    }
}
