package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable, V>  implements Map61B<K, V>  {
    @Override
    public Iterator<K> iterator() {
        return null;
    }

    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    private Node root;
    private int size;
    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if(key == null) {
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {
        if(node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if(cmp < 0) {
            return get(node.left, key);
        } else if(cmp > 0) {
            return get(node.right, key);
        } else {
            return node.value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if(key == null) {
            throw new IllegalArgumentException();
        }
        root = put(root, key, value);
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    private Node put(Node node, K key, V value) {
        if(node == null) {
            size++;
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);
        if(cmp < 0) {
            node.left = put(node.left, key, value);
        } else if(cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
}