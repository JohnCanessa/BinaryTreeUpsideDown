import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 */
public class BinaryTreeUpsideDown {


    /**
     * Given the root of a binary tree, 
     * turn the tree upside down and 
     * return the new root.
     * Recursive call.
     * 
     * 145 / 145 test cases passed.
     * Status: Accepted
     * Runtime: 0 ms
     * Memory Usage: 39.2 MB
     */
    static public TreeNode upsideDownBinaryTree(TreeNode root) {

        // **** base case ****
        if (root == null || root.left == null) return root;

        // **** process left children (recursive call) ****
        TreeNode left = upsideDownBinaryTree(root.left);

        // ???? ????
        System.out.println("upsideDownBinaryTree <<<       root: " + root.toString());
        System.out.println("upsideDownBinaryTree <<<  root.left: " + root.left.toString());
        System.out.println("upsideDownBinaryTree <<< root.right: " + root.right.toString());

        // **** set new root left child ****
        root.left.left  = root.right;

        // **** set new root right child ****
        root.left.right = root;
        
        // **** clear root children (have been repositioned) ****
        root.left   = null;
        root.right  = null;
        
        // **** new root ****
        return left;
    }

    
    /**
     * Test scaffold
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** initialization ****
        Integer[] arr = null;

        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read the input line ****
        String inputLine = br.readLine().trim();

        // **** read and populate array of Integers (if possible) ****
        if (!inputLine.equals(""))
            arr = Arrays.stream(inputLine.split(","))
                                .map(Integer::parseInt)
                                .toArray(Integer[]::new);

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< arr: " + Arrays.toString(arr));

        // **** create and populate the binary tree ****
        BST bt = new BST();
        bt.root = bt.populate(arr);

        // ???? ????
        System.out.println("main <<< bt inOrder: " + bt.inOrder(bt.root));

        // **** call function of interest ****
        TreeNode newRoot = upsideDownBinaryTree(bt.root);

        // **** create an empty upside down binary tree ****
        BST udt = new BST();

        // **** assign the root to the upside down binary tree ****
        udt.root = newRoot;

        // ???? ????
        System.out.println("main <<< udt inOrder: " + udt.inOrder(udt.root));
    }
}