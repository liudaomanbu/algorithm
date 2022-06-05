package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/lru-cache/
 *
 * @author caotc
 * @date 2022-05-05
 * @since 1.0.0
 */
public class LRUCache {
    int capacity;
    Map<Integer, Node<Integer, Integer>> map = new HashMap<>(capacity);
    LinkedList<Integer, Integer> linkedList = new LinkedList<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node<Integer, Integer> node = map.get(key);
        if (node != null) {
            linkedList.moveToHead(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node<Integer, Integer> node = map.get(key);
        if (node == null) {
            if (map.size() == capacity) {
                Node<Integer, Integer> tail = linkedList.removeTail();
                map.remove(tail.key);
            }

            node = new Node<>(key, value);
            map.put(key, node);
            linkedList.add(node);
        } else {
            node.value = value;
            linkedList.moveToHead(node);
        }
    }

    static class LinkedList<K, V> {
        Node<K, V> head = null;
        Node<K, V> tail = null;

        public void add(Node<K, V> node) {
            if (node == null) {
                return;
            }
            if (head == null) {
                head = node;
                tail = node;
            } else {
                node.pre = null;
                node.next = head;
                head.pre = node;
                head = node;
            }
        }

        public void moveToHead(Node<K, V> node) {
            if (head == node) {
                return;
            }

            if (tail == node) {
                tail.pre.next = null;
                tail = tail.pre;
            } else {
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }

            node.pre = null;
            node.next = head;
            head.pre = node;
            head = node;
        }

        public Node<K, V> removeTail() {
            if (tail != null) {
                Node<K, V> result = tail;
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    tail.pre.next = null;
                    tail = tail.pre;
                    result.pre = null;
                }
                return result;
            }
            return null;
        }
    }

    static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> pre;
        public Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
