package assn06;

public class Main {
    public static void main(String[] args) {

        // Create a new empty tree.
        SelfBalancingBST<Integer> avl_bst = new AVLTree<Integer>();

        // Insert 50 random integers.
        // Note how we need to capture the result of insert back into
        // the variable avl_bst because the post-insertion root that is
        // returned may be different from the original root because of the insertion.
        // result should be about 6.

        for (int i = 0; i < 50; i++) {
            avl_bst = avl_bst.insert((int) (Math.random() * 100));
        }
        System.out.println(avl_bst.height());

        // Now insert 50 integers in increasing order which would
        // cause a simple BST to become very tall but for our
        // self-balancing tree won't be too bad (should be 7)

        for (int i = 0; i < 50; i++) {
            avl_bst = avl_bst.insert(i);
        }
        System.out.println(avl_bst.height());
        // System.out.println(avl_bst.toString());

        System.out.println("test cases:");

        SelfBalancingBST<Integer> avl = new AVLTree<Integer>();

        int[] numbers = { 20, 11, 50, 4, 6, 15, 3, 16, 17 };

        for (int i = 0; i < numbers.length; i++) {
            avl = avl.insert(numbers[i]);
        }

        System.out.println(avl.height());
        System.out.println(avl.toString());
        System.out.println(avl.isBalanced());
    }
}
