package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A custom implementation of a Binary Tree.
 * @param <T> The type of data to store in the tree.
 */
public class MyBinaryTree<T extends Comparable<T>> {
    /**
     * Node used in a Binary tree.
     */
    protected class BinaryTreeNode {
        T data;
        BinaryTreeNode left;
        BinaryTreeNode right;

        public BinaryTreeNode() {
            data = null;
            left = null;
            right = null;
        }

        public BinaryTreeNode(T data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    protected BinaryTreeNode root;

    /**
     * Default constructor used to initialize root to null.
     */
    public MyBinaryTree() {
        root = null;
    }

    /**
     * Constructs a binary tree with the root containing
     * the data passed as an argument.
     * @param data The data to set into the root node.
     */
    public MyBinaryTree(T data) {
        root = new BinaryTreeNode(data);
    }

    /**
     * Copy constructor to construct a binary tree from another binary tree.
     * @param otherTree The binary tree to copy data from.
     */
    public MyBinaryTree(MyBinaryTree<T> otherTree) {
        if (otherTree.root == null)
            root = null;
        else
            root = copy(otherTree.root);
    }

    /**
     * Returns true if the binary tree contains
     * no nodes, false otherwise.
     * @return True if the tree is empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Convenience method to print the contents
     * of the binary tree using inorder traversal.
     */
    public void inorderTraversal() {
        inorder(root);
    }

    /**
     * Convenience method to print the contents
     * of the binary tree using preorder traversal.
     */
    public void preorderTraversal() {
        preorder(root);
    }

    /**
     * Convenience method to print the contents
     * of the binary tree using postorder traversal.
     */
    public void postorderTraversal() {
        postorder(root);
    }

    /**
     * Convenience method to print the contents
     * of the binary tree using level order traversal.
     */
    public void levelOrderTraversal() {
        levelOrder(root);
    }

    /**
     * Determines the height of the binary tree.
     * @return The height of the binary tree.
     */
    public int treeHeight() {
        return height(root);
    }

    /**
     * Determines the number of nodes in the binary tree.
     * @return The number of nodes in the binary tree.
     */
    public int treeNodeCount() {
        return nodeCount(root);
    }

    /**
     * Determines the number of leaf nodes in the binary tree.
     * @return The number of nodes in the binary tree.
     */
    public int treeLeavesCount() {
        return leavesCount(root);
    }

    /**
     * Determines if the data exists in the binary tree.
     * @param data The data to search for.
     * @return True if the data exists, false otherwise.
     */
    public boolean contains(T data) {
        return containsNode(root, data);
    }

    /**
     * Destroys the binary tree.
     */
    public void destroyTree() {
        root = null;
    }

    /**
     * Makes a copy of the binary tree to which otherTree points to.
     * @param otherTree The binary tree to make a copy of.
     */
    public void copyTree(MyBinaryTree<T> otherTree) {
        if (this != otherTree) {
            root = null;

            if (otherTree.root != null) {
                root = copy(otherTree.root);
            }
        }
    }

    /**
     * Makes a copy of the binary tree to which otherTree points to.
     * @param otherTreeRoot The root of the binary tree to make a copy of.
     * @return A copy of the binary tree from the given root node.
     */
    private BinaryTreeNode copy(BinaryTreeNode otherTreeRoot) {
        BinaryTreeNode temp;

        if (otherTreeRoot == null) {
            return null;
        } else {
            temp = new BinaryTreeNode(otherTreeRoot.data);
            temp.left = copy(otherTreeRoot.left);
            temp.right = copy(otherTreeRoot.right);
        }
        return temp;
    }

    /**
     * Performs inorder traversal of the binary tree from the given root.
     * @param root The root of the binary tree to begin traversing from.
     */
    private void inorder(BinaryTreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    /**
     * Performs preorder traversal of the binary tree from the given root.
     * @param root The root of the binary tree to begin traversing from.
     */
    private void preorder(BinaryTreeNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    /**
     * Performs postorder traversal of the binary tree from the given root.
     * @param root The root of the binary tree to begin traversing from.
     */
    private void postorder(BinaryTreeNode root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }

    /**
     * Performs level order traversal of the binary tree from the given root.
     * @param root The root of the binary tree to begin traversing from.
     */
    private void levelOrder(BinaryTreeNode root) {
        Queue<BinaryTreeNode> nodes = new LinkedList<>();

        nodes.add(root);

        while (!nodes.isEmpty()) {
            BinaryTreeNode current = nodes.poll();
            System.out.print(current.data + " ");

            if (current.left != null) {
                nodes.add(current.left);
            }
            if (current.right != null) {
                nodes.add(current.right);
            }
        }
    }

    /**
     * Calculates the height of the binary tree.
     * @param root The root of the binary tree.
     * @return The height of the binary tree.
     */
    private int height(BinaryTreeNode root) {
        if (root == null)
            return 0;
        else
            return 1 + Math.max(height(root.left), height(root.right));
    }

    /**
     * Calculates the number of nodes in the binary tree.
     * @param root The root of the binary tree.
     * @return The number of nodes in the binary tree.
     */
    private int nodeCount(BinaryTreeNode root) {
        if (root == null)
            return 0;
        else
            return 1 + nodeCount(root.left) + nodeCount(root.right);
    }

    /**
     * Calculates the number of leaf nodes in the binary tree.
     * @param root The root of the binary tree.
     * @return The number of leaf nodes in the binary tree.
     */
    private int leavesCount(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return 1;
        } else {
            return leavesCount(root.left) + leavesCount(root.right);
        }
    }

    /**
     * Checks to see if the given data exists in the binary tree.
     * @param root The root of the binary tree.
     * @param data The data to search for.
     * @return True if the data exists in the binary tree, false otherwise.
     */
    private boolean containsNode(BinaryTreeNode root, T data) {
        if (root == null) {
            return false;
        } else {
            if (root.data == data) {
                return true;
            } else {
                return (containsNode(root.left, data) || containsNode(root.right, data));
            }
        }
    }
}
