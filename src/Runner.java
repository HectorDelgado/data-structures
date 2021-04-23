import tree.MyBinarySearchTree;
import tree.MyBinaryTree;

import java.util.LinkedList;
import java.util.Stack;

public class Runner {
    public static void main(String[] args) {
        Integer[] arr = {7, 10, 4, 50, 51, 2, 11};
        MyBinarySearchTree<Integer> binarySearchTree = new MyBinarySearchTree<>();
        binarySearchTree.insertIteratively(12);
        binarySearchTree.insertIteratively(7);
        binarySearchTree.insertIteratively(19);
        binarySearchTree.insertIteratively(3);
        binarySearchTree.insertIteratively(8);
        binarySearchTree.insertIteratively(15);
        binarySearchTree.insertIteratively(20);
        binarySearchTree.levelOrderTraversal();
    }


}
