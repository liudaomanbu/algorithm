package base.cache;


import java.util.HashMap;
import java.util.Map;

/**
 * @author caotc
 * @date 2022-05-05
 * @since 1.0.0
 */

public class Lru<K, V> implements Cache<K, V> {
    int capacity;
    Map<K, Node<K, V>> map = new HashMap<>(capacity);
    LinkedList<K, V> linkedList = new LinkedList<>();

    public Lru(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = map.get(key);
        if (node != null) {
            linkedList.moveToHead(node);
            return node.value;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        V preValue = null;
        Node<K, V> node = map.get(key);
        if (node == null) {
            if (map.size() == capacity) {
                Node<K, V> tail = linkedList.removeTail();
                map.remove(tail.key);
            }

            node = new Node<>(key, value);
            map.put(key, node);
            linkedList.add(node);
        } else {
            preValue = node.value;
            node.value = value;
            linkedList.moveToHead(node);
        }

        return preValue;
    }

    @Override
    public int maxSize() {
        return capacity;
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
                node.pre=null;
                node.next=head;
                head.pre=node;
                head=node;
            }
        }

        public void moveToHead(Node<K, V> node) {
            if(head==node){
                return;
            }

            if(tail==node){
                tail.pre.next=null;
                tail=tail.pre;
            }else{
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }

            node.pre = null;
            node.next = head;
            head.pre=node;
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
                    result.pre=null;
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
