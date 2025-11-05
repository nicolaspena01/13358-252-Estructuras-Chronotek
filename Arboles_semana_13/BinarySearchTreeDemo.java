package Arboles_semana_13;

public class BinarySearchTreeDemo {

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        TreeNode(int value) {
            this.value = value;
        }
    }

    static class BinarySearchTree {

        TreeNode root;

        public TreeNode findValue(TreeNode current, int target) {
            if (current == null) {
                return null;
            }

            if (current.value == target) {
                return current;
            }

            if (target < current.value) {
                return findValue(current.left, target);
            } else {
                return findValue(current.right, target);
            }
        }

        public TreeNode find(int target) {
            return findValue(root, target);
        }

        public void insertNode(TreeNode current, int newValue) {
            if (newValue == current.value) {
                System.out.println("Valor duplicado: " + newValue);
                return;
            }

            if (newValue < current.value) {
                if (current.left != null) {
                    insertNode(current.left, newValue);
                } else {
                    current.left = new TreeNode(newValue);
                    current.left.parent = current;
                }
            } else {
                if (current.right != null) {
                    insertNode(current.right, newValue);
                } else {
                    current.right = new TreeNode(newValue);
                    current.right.parent = current;
                }
            }
        }

        public void insert(int newValue) {
            if (root == null) {
                root = new TreeNode(newValue);
            } else {
                insertNode(root, newValue);
            }
        }

        private void inorderRec(TreeNode node) {
            if (node != null) {
                inorderRec(node.left);
                System.out.print(node.value + " ");
                inorderRec(node.right);
            }
        }

        public void inorder() {
            System.out.print("Recorrido In-Order: ");
            inorderRec(root);
            System.out.println();
        }

        private void preorderRec(TreeNode node) {
            if (node != null) {
                System.out.print(node.value + " ");
                preorderRec(node.left);
                preorderRec(node.right);
            }
        }

        public void preorder() {
            System.out.print("Recorrido Pre-Order: ");
            preorderRec(root);
            System.out.println();
        }

        private void postorderRec(TreeNode node) {
            if (node != null) {
                postorderRec(node.left);
                postorderRec(node.right);
                System.out.print(node.value + " ");
            }
        }

        public void postorder() {
            System.out.print("Recorrido Post-Order: ");
            postorderRec(root);
            System.out.println();
        }

        private TreeNode treeMinimum(TreeNode node) {
            while (node != null && node.left != null) {
                node = node.left;
            }
            return node;
        }

        private void transplant(TreeNode u, TreeNode v) {
            if (u.parent == null) {
            
                root = v;
            } else if (u == u.parent.left) {
                u.parent.left = v;
            } else {
                u.parent.right = v;
            }

            if (v != null) {
                v.parent = u.parent;
            }
        }
    
        private void deleteNode(TreeNode z) {
            if (z.left == null) {
            
                transplant(z, z.right);
            } else if (z.right == null) {
            
                transplant(z, z.left);
            } else {
            
            
                TreeNode y = treeMinimum(z.right);

                if (y.parent != z) {
                
                    transplant(y, y.right);
                
                    y.right = z.right;
                    if (y.right != null) {
                        y.right.parent = y;
                    }
                }

                transplant(z, y);
            
                y.left = z.left;
                if (y.left != null) {
                    y.left.parent = y;
                }
            }
        }
    
        public void delete(int value) {
            TreeNode z = find(value);

            if (z == null) {
                System.out.println("Valor no encontrado: " + value);
                return;
            }

            deleteNode(z);
            System.out.println("Nodo eliminado: " + value);
        }

    }

    public static void main(String[] args) {

        BinarySearchTree tree = new BinarySearchTree();

        System.out.println("Creando árbol binario de búsqueda...");

        int[] values = { 8, 3, 10, 1, 6, 14, 4, 7, 13 };

        for (int v : values) {
            tree.insert(v);
        }

        tree.inorder();
        tree.preorder();
        tree.postorder();

    
        System.out.println("\nEliminando nodo 10 (tiene dos hijos)...");
        tree.delete(10);
        tree.inorder();

        System.out.println("\nEliminando nodo 1 (hoja)...");
        tree.delete(1);
        tree.inorder();

        System.out.println("\nEliminando nodo 3 (un hijo)...");
        tree.delete(3);
        tree.inorder();
    }

}
