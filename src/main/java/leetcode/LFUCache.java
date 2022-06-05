package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lfu-cache/
 *
 * @author caotc
 * @date 2022-05-05
 * @since 1.0.0
 */
public class LFUCache {
    int capacity;
    Map<Integer, Node<Integer, Integer>> map = new HashMap<>();
    Map<Integer, LinkedList<Integer, Integer>> frequencyToLinkedList = new HashMap<>();
    Map<Node<Integer, Integer>, LinkedList<Integer, Integer>> nodeToLinkedList = new HashMap<>();
    LinkedList<Integer, Integer> headLinkedList = null;

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node<Integer, Integer> node = map.get(key);
        if (node != null) {
            LinkedList<Integer, Integer> linkedList = nodeToLinkedList.get(node);
            LinkedList<Integer, Integer> nextLinkedList = frequencyToLinkedList.get(linkedList.frequency + 1);
            if (nextLinkedList == null) {
                nextLinkedList = new LinkedList<>(linkedList.frequency + 1);
                frequencyToLinkedList.put(nextLinkedList.frequency, nextLinkedList);
            }
            linkedList.remove(node);
            if(linkedList.head==null && headLinkedList==linkedList){
                headLinkedList=nextLinkedList;
            }
            nextLinkedList.addToTail(node);
            nodeToLinkedList.put(node,nextLinkedList);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(capacity==0){
            return;
        }

        Node<Integer, Integer> node = map.get(key);
        if (node == null) {
            if (map.size() == capacity && headLinkedList != null) {
                Node<Integer, Integer> head = headLinkedList.removeHead();
                map.remove(head.key);
            }

            LinkedList<Integer, Integer> linkedList = frequencyToLinkedList.get(1);
            if (linkedList == null) {
                linkedList = new LinkedList<>(1);
                frequencyToLinkedList.put(linkedList.frequency, linkedList);
            }

            node = new Node<>(key, value);
            map.put(key, node);
            linkedList.addToTail(node);
            headLinkedList=linkedList;
            nodeToLinkedList.put(node,linkedList);
        } else {
            LinkedList<Integer, Integer> linkedList = nodeToLinkedList.get(node);
            LinkedList<Integer, Integer> nextLinkedList = frequencyToLinkedList.get(linkedList.frequency + 1);
            if (nextLinkedList == null) {
                nextLinkedList = new LinkedList<>(linkedList.frequency + 1);
                frequencyToLinkedList.put(nextLinkedList.frequency, nextLinkedList);
            }
            linkedList.remove(node);
            if(linkedList.head==null && headLinkedList==linkedList){
                headLinkedList=nextLinkedList;
            }
            nextLinkedList.addToTail(node);
            nodeToLinkedList.put(node,nextLinkedList);

            node.value = value;
        }
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

        public void addToHead(Node<K, V> node) {
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

        public void addToTail(Node<K, V> node) {
            if (node == null) {
                return;
            }
            if (head == null) {
                head = node;
                tail = node;
            } else {
                node.next = null;
                node.pre = tail;
                tail.next = node;
                tail = node;
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

        public void remove(Node<K, V> node) {
            if (node.pre != null) {
                node.pre.next = node.next;
            }
            if (node.next != null) {
                node.next.pre = node.pre;
            }
            if (head == node) {
                head = node.next;
            }
            if (tail == node) {
                tail = node.pre;
            }
            node.pre = null;
            node.next = null;
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

        public Node<K, V> removeHead() {
            if (head != null) {
                Node<K, V> result = head;
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    head.next.pre = null;
                    head = head.next;
                    result.next = null;
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
