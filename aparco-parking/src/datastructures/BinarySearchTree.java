package datastructures;

import java.util.function.BiConsumer;

public class BinarySearchTree<K extends Comparable<K>, V> {

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left, right;

        Node(K k, V v) {
            this.key = k;
            this.value = v;
        }
    }

    private Node<K, V> root;

    // Insertar o reemplazar
    public void put(K key, V value) {
        root = insert(root, key, value);
    }

    private Node<K, V> insert(Node<K, V> node, K key, V value) {
        if (node == null)
            return new Node<>(key, value);
        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = insert(node.left, key, value);
        else if (cmp > 0)
            node.right = insert(node.right, key, value);
        else
            node.value = value; // reemplazar
        return node;
    }

    // Buscar
    public V get(K key) {
        Node<K, V> n = root;
        while (n != null) {
            int cmp = key.compareTo(n.key);
            if (cmp < 0)
                n = n.left;
            else if (cmp > 0)
                n = n.right;
            else
                return n.value;
        }
        return null;
    }

    // Eliminar
    public void remove(K key) {
        root = delete(root, key);
    }

    private Node<K, V> delete(Node<K, V> node, K key) {
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = delete(node.left, key);
        else if (cmp > 0)
            node.right = delete(node.right, key);
        else {
            // caso 1: sin hijos
            if (node.left == null && node.right == null)
                return null;
            // caso 2: un hijo
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;
            // caso 3: dos hijos -> tomar mínimo de la derecha
            Node<K, V> min = findMin(node.right);
            node.key = min.key;
            node.value = min.value;
            node.right = delete(node.right, min.key);
        }
        return node;
    }

    private Node<K, V> findMin(Node<K, V> node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    // Recorrido inorder (recursivo) para listar ordenado
    public void inorder(BiConsumer<K, V> visitor) {
        inorder(root, visitor);
    }

    private void inorder(Node<K, V> node, BiConsumer<K, V> visitor) {
        if (node == null)
            return;
        inorder(node.left, visitor);
        visitor.accept(node.key, node.value);
        inorder(node.right, visitor);
    }

    public boolean isEmpty() {
        return root == null;
    }
}
