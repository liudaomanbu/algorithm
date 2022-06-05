package base.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caotc
 * @date 2022-05-05
 * @since 1.0.0
 */
public class Lfu<K, V> implements Cache<K, V> {
    int capacity;
    Map<K,Node<K,V>> map=new HashMap<>();
    Map<Integer,LinkedList<K,V>> frequencyToLinkedList=new HashMap<>();
    Map<Node<K,V>,LinkedList<K,V>> nodeToLinkedList=new HashMap<>();
    LinkedList<K,V> headLinkedList=null;

    public Lfu(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = map.get(key);
        if (node != null) {
            LinkedList<K, V> linkedList = nodeToLinkedList.get(node);
            LinkedList<K, V> nextLinkedList=frequencyToLinkedList.get(linkedList.frequency+1);
            if(nextLinkedList==null){
                nextLinkedList=new LinkedList<>(linkedList.frequency+1);
                frequencyToLinkedList.put(nextLinkedList.frequency,nextLinkedList);
            }
            linkedList.remove(node);
            nextLinkedList.add(node);
            return node.value;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        V preValue = null;
        Node<K, V> node = map.get(key);
        if (node == null) {
            if (map.size() == capacity && headLinkedList!=null) {
                Node<K, V> tail = headLinkedList.removeTail();//todo
                map.remove(tail.key);
            }

            LinkedList<K, V> linkedList = frequencyToLinkedList.get(1);
            if(linkedList==null){
                linkedList=new LinkedList<>(1);
                frequencyToLinkedList.put(linkedList.frequency,linkedList);
            }

            node = new Node<>(key, value);
            map.put(key, node);
            linkedList.add(node);
        } else {
            preValue = node.value;
            LinkedList<K, V> linkedList = nodeToLinkedList.get(node);
            node.value = value;
            linkedList.moveToHead(node);
        }

        return preValue;
    }

    @Override
    public int maxSize() {
        return 0;
    }

    static class LinkedList<K, V> {
        Node<K, V> head = null;
        Node<K, V> tail = null;
        int frequency;

        public LinkedList(int frequency) {
            this.frequency = frequency;
        }

        public int getFrequency() {
            return frequency;
        }

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

        public void remove(Node<K, V> node){
            if(node.pre!=null){
                node.pre.next=node.next;
            }
            if(node.next!=null){
                node.next.pre=node.pre;
            }
            if(head==node){
                head=node.next;
            }
            if(tail==node){
                tail=node.pre;
            }
            node.pre=null;
            node.next=null;
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
