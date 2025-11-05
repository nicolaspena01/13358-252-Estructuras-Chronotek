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
            System.out.print("In-Order: ");
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
            System.out.print("Pre-Order: ");
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
            System.out.print("Post-Order: ");
            postorderRec(root);
            System.out.println();
        }

        public TreeNode findValue(TreeNode current, int target) {
            if (current == null)
                return null;

            if (current.value == target)
                return current;

            if (target < current.value)
                return findValue(current.left, target);
            else
                return findValue(current.right, target);
        }

        public TreeNode find(int target) {
            return findValue(root, target);
        }

        public TreeNode deleteNode(TreeNode current, int value) {
            if (current == null) {
                return null;
            }

            if (value < current.value) {
                current.left = deleteNode(current.left, value);
            } else if (value > current.value) {
                current.right = deleteNode(current.right, value);
            } else {
                if (current.left == null && current.right == null) {
                    return null;
                }
                else if (current.left == null) {
                    return current.right;
                } else if (current.right == null) {
                    return current.left;
                }
                else {
                    TreeNode sucesor = findMin(current.right);
                    current.value = sucesor.value;
                    current.right = deleteNode(current.right, sucesor.value);
                }
            }

            return current;
        }

        private TreeNode findMin(TreeNode node) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        public void delete(int value) {
            root = deleteNode(root, value);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        System.out.println("Creando Arbol Binario de Busqueda...");

        int[] values = {8, 3, 10, 1, 6, 14, 4, 7, 13};

        for (int v : values) {
            tree.insert(v);
        }

        System.out.println("\n--- RECORRIDOS ---");
        tree.inorder();   
        tree.preorder();  
        tree.postorder(); 

        System.out.println("\n--- ELIMINACION ---");
        System.out.println("Eliminando nodo 10...");
        tree.delete(10);

        tree.inorder(); 
    }
}
