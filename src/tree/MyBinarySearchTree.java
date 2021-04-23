package tree;

/**
 * A custom implementation of a Binary Search Tree (BST).
 * @param <T> The type of data to store in the tree.
 */
public class MyBinarySearchTree<T extends Comparable<T>> extends MyBinaryTree<T> {
    /**
     * Default constructor used to initialize the root node to null.
     */
    public MyBinarySearchTree() {
        this.root = null;
    }

    /**
     * Constructs a BST with the root node containing the specified data.
     * @param data The data to set into the root.
     */
    public MyBinarySearchTree(T data) {
        this.root = new BinaryTreeNode(data);
    }

    /**
     * Copy constructor to initialize a BST from another binary tree.
     * @param otherTree The binary search tree to copy data from.
     */
    public MyBinarySearchTree(MyBinarySearchTree<T> otherTree) {
        copyTree(otherTree);
    }

    /**
     * Searches for an item in the BST using iteration.
     * @param searchItem The item to search for.
     * @return True if the item exists in the list, false otherwise.
     */
    public boolean searchIteratively(T searchItem) {
        BinaryTreeNode current = root;
        boolean found = false;

        while (current != null && !found) {
            if (current.data.equals(searchItem)) {
                found = true;
            } else if (searchItem.compareTo(current.data) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return found;
    }

    /**
     * Searches for an item in the BST using recursion.
     * @param searchItem The item to search for.
     * @return True if the item exists in the list, false otherwise.
     */
    private boolean searchRecursively(T searchItem) {
        return searchRecursively(root, searchItem);
    }

    /**
     * Inserts an item into the BST using iteration.
     * @param insertItem The item to insert.
     */
    public void insertIteratively(T insertItem) {
        BinaryTreeNode current;
        BinaryTreeNode trailCurrent = null;
        BinaryTreeNode newNode = new BinaryTreeNode(insertItem);

        if (root == null) {
            root = newNode;
        } else {
            current = root;

            while (current != null) {
                trailCurrent = current;

                if (current.data.equals(insertItem)) {
                    return;
                } else if (insertItem.compareTo(current.data) < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            if (insertItem.compareTo(trailCurrent.data) < 0) {
                trailCurrent.left = newNode;
            }
            else {
                trailCurrent.right = newNode;
            }
        }
    }

    /**
     * Inserts an item into tht BST using recursion.
     * @param insertItem The item to insert.
     */
    public void insertRecursively(T insertItem) {
        root = insertRecursively(root, insertItem);
    }

    /**
     * Removes an item from the BST using iteration.
     * @param deleteItem The item to delete.
     */
    public void deleteNodeIteratively(T deleteItem) {
        BinaryTreeNode current;
        BinaryTreeNode trailCurrent;

        if (root == null) {
            throw new NullPointerException("Cannot delete from an empty tree");
        } else{
            current = root;
            trailCurrent = root;

            while (current != null) {
                if (current.data.equals(deleteItem)) {
                    break;
                } else {
                    trailCurrent = current;

                    if (deleteItem.compareTo(current.data) < 0) {
                        current = current.left;
                    } else {
                        current = current.right;
                    }
                }
            }

            if (current == null) {
                System.out.println("Item to delete not found!");
            } else {
                if (current == root) {
                    root = deleteFromTree(root);
                } else if (deleteItem.compareTo(trailCurrent.data) < 0) {
                    trailCurrent.left = deleteFromTree(trailCurrent.left);
                } else {
                    trailCurrent.right = deleteFromTree(trailCurrent.right);
                }
            }
        }
    }

    /**
     * Deletes an item from the BST using recursion.
     * @param deleteItem The item to delete.
     */
    public void deleteNodeRecursively(T deleteItem) {
        root = deleteRecursively(root, deleteItem);
    }

    /**
     * Searches for an item in the binary tree using recursion.
     * @param  root The root node to start searching from.
     * @param searchItem The item to search for.
     * @return True if the item exists in the list, false otherwise.
     */
    private boolean searchRecursively(BinaryTreeNode root, T searchItem) {
        if (root == null) {
            return false;
        } else {
            if (root.data.equals(searchItem)) {
                return true;
            } else if (searchItem.compareTo(root.data) < 0) {
                return searchRecursively(root.left, searchItem);
            } else {
                return searchRecursively(root.right, searchItem);
            }
        }
    }

    /**
     * Inserts an item into the bst using recursion.
     * @param root The root node to of the bst.
     * @param insertItem The item to insert.
     * @return The BinaryTreeNode with the new item inserted.
     */
    private BinaryTreeNode insertRecursively(BinaryTreeNode root, T insertItem) {
        BinaryTreeNode temp = root;

        if (temp == null) {
            temp = new BinaryTreeNode(insertItem);
        } else {
            if (insertItem.compareTo(root.data) < 0) {
                root.left = insertRecursively(root.left, insertItem);
            } else if (insertItem.compareTo(root.data) > 0){
                root.right = insertRecursively(root.right, insertItem);
            }
        }

        return temp;
    }

    /**
     * Removes the node from the BST using iteration.
     * @param node The node to delete.
     * @return The BST with the node deleted.
     */
    private BinaryTreeNode deleteFromTree(BinaryTreeNode node) {
        BinaryTreeNode current;
        BinaryTreeNode trailCurrent;

        if (node == null) {
            throw new NullPointerException("Node to be deleted is null");
        } else if (node.left == null && node.right == null) {
            node = null;
        } else if (node.left == null) {
            node = node.right;
        } else if (node.right == null) {
            node = node.left;
        } else {
            current = node.left;
            trailCurrent = null;

            while (current.right != null) {
                trailCurrent = current;
                current = current.right;
            }

            node.data = current.data;

            if (trailCurrent == null) {
                node.left = current.left;
            } else {
                trailCurrent.right = current.left;
            }
        }

        return node;
    }

    /**
     * Deletes a node from the BST if it exists using recursion.
     * @param root The root node of the current BST.
     * @param deleteItem The item to delete.
     * @return The BST with the deleteItem possibly removed.
     */
    private BinaryTreeNode deleteRecursively(BinaryTreeNode root, T deleteItem) {
        if (root == null) {
            return null;
        } else if (deleteItem.compareTo(root.data) < 0) {
            root.left = deleteRecursively(root.left, deleteItem);
        } else if (deleteItem.compareTo(root.data) > 0) {
            root.right = deleteRecursively(root.right, deleteItem);
        } else {
            // Node has one or zero children
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children, find inorder successor
            // (smallest value in right subtree)
            root.data = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRecursively(root.right, root.data);
        }

        return root;
    }

    /**
     * Finds the inorder successor for the given BST.
     * @param root The root of the BST.
     * @return The smallest value in the right subtree.
     */
    private T minValue(BinaryTreeNode root) {
        T minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }
}
