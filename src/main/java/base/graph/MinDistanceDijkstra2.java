package base.graph;

import lombok.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caotc
 * @date 2022-06-08
 * @since 1.0.0
 */
public class MinDistanceDijkstra2 implements MinDistance {
    @Override
    public <E> Map<Node<E>, Integer> targetNodeToMinDistance(Node<E> head, int size) {
        NodeHeap<E> nodeHeap = new NodeHeap<>(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        HashMap<Node<E>, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            NodeRecord<E> record = nodeHeap.pop();
            Node<E> cur = record.node;
            int distance = record.distance;
            for (Edge<E> edge : cur.nextEdges()) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            result.put(cur, distance);
        }
        return result;
    }

    @Value
    static class NodeRecord<E> {
        Node<E> node;
        int distance;
    }

    public static class NodeHeap<E> {
        private Node<E>[] nodes; // 实际的堆结构
        // key 某一个node， value 上面堆中的位置
        private HashMap<Node<E>, Integer> heapIndexMap;
        // key 某一个节点， value 从源节点出发到该节点的目前最小距离
        private HashMap<Node<E>, Integer> distanceMap;
        private int size; // 堆上有多少个点

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 有一个点叫node，现在发现了一个从源节点出发到达node的距离为distance
        // 判断要不要更新，如果需要的话，就更新
        public void addOrUpdateOrIgnore(Node<E> node, int distance) {
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                insertHeapify(node, heapIndexMap.get(node));
            }
            if (!isEntered(node)) {
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(node, size++);
            }
        }

        public NodeRecord<E> pop() {
            NodeRecord<E> nodeRecord = new NodeRecord<>(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            heapIndexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            // free C++同学还要把原本堆顶节点析构，对java同学不必
            nodes[size - 1] = null;
            heapify(0, --size);
            return nodeRecord;
        }

        private void insertHeapify(Node<E> node, int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
                        ? left + 1
                        : left;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
                if (smallest == index) {
                    break;
                }
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        private boolean isEntered(Node<E> node) {
            return heapIndexMap.containsKey(node);
        }

        private boolean inHeap(Node<E> node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        private void swap(int index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node<E> tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }
    }
}
