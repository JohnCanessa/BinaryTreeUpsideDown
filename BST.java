import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * The idea of the algorithm of a rotation is simple. 
 * First, take the middle subtree out, 
 * do the rotation and reattach the middle subtree. 
 */
public class BST {


    // **** binary tree root ****
    TreeNode root;


    /**
     * Constructor.
     */
    public BST() {
        this.root = null;
    }


    /**
     * Constructor.
     */
    public BST(TreeNode node) {
        this.root = node;
    }


    /**
     * Utility function to get the height of a node in an AVL BST.
     */
    private int heightAVL(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return node.height;
        }
    }


    /**
     * Utility function that returns the max of two integers.
     */
    private int max(int a, int b) {
        return (a > b) ? a : b;
    }


    /**
     * Insert array values into specified BST.
     * 
     * Entry point for recursive call.
     */
    public TreeNode insertBST(int[] arr) {

        // **** loop through the array ****
        for (int i = 0; i < arr.length; i++) {
            insertBST(this.root, arr[i]);
        }

        // **** return the root of the BST ****
        return this.root;
    }


    /**
     * Insert node into a BST.
     * Recursive call.
     */
    public void insertBST(TreeNode node, int val) {

        // **** BST is empty ****
        if (this.root == null) {
            this.root = new TreeNode(val);
            return;
        }

        // **** recurse left ****
        if (val < node.val) {
            if (node.left == null) {
                node.left = new TreeNode(val);
                return;
            }
            insertBST(node.left, val);
        }

        // **** recurse right ****
        else if (val > node.val) {
            if (node.right == null) {
                node.right = new TreeNode(val);
                return;
            }
            insertBST(node.right, val);
        }
    }


    /**
     * Populate the binary tree per level.
     */
    public TreeNode populate(Integer[] arr) {

        // **** sanity checks ****
        if (arr == null || arr.length == 0) {
            return null;
        }

        if (arr[0] == null)
            return null;

        // **** initialization ****
        Queue<TreeNode> treeNodeQ   = new LinkedList<>();
        Queue<Integer> integerQ     = new LinkedList<>();

        // **** populate integer queue ****
        for (int i = 1; i < arr.length; i++) {
            integerQ.offer(arr[i]);
        }

        // **** prime the tree node queue ****
        TreeNode node = new TreeNode(arr[0]);
        treeNodeQ.offer(node);

        // **** ****
        while (!integerQ.isEmpty()) {

            // **** ****
            Integer leftVal     = integerQ.isEmpty() ? null : integerQ.poll();
            Integer rightVal    = integerQ.isEmpty() ? null : integerQ.poll();
            TreeNode current    = treeNodeQ.poll();

            // **** ****
            if (leftVal != null) {
                TreeNode left = new TreeNode(leftVal);
                current.left = left;
                treeNodeQ.offer(left);
            }

            // **** ****
            if (rightVal != null) {
                TreeNode right = new TreeNode(rightVal);
                current.right = right;
                treeNodeQ.offer(right);
            }
        }

        // **** return root of binary tree ****
        return node;
    }


    // **** list of level node values ****
    private List<List<Integer>> levelOrderList = null;


    /**
     * Utility function that generates a string with information 
     * from a breadth first traversal (BFT).
     */
    public String levelOrder() {

        // **** initialization ****
        StringBuilder sb = new StringBuilder("\n");

        // **** ****
        List<List<Integer>> lst = levelOrderTraversal(this.root);

        // **** traverse lst building the string ****
        for (int i = 0; i < lst.size(); i++) {

            // **** ****
            List<Integer> l = lst.get(i);

            // **** ****
            for (int j = 0; j < l.size(); j++) {
                if (j == l.size() - 1)
                    sb.append(l.get(j));
                else
                    sb.append(l.get(j) + ",");
            }

            // **** ****
            if (i < lst.size() - 1)
                sb.append("\n");
        }

        // **** return string ****
        return sb.toString();
    }


    /**
     * Traverse binary tree in level order.
     * Recursive call entry point.
     */
    public List<List<Integer>> levelOrderTraversal(TreeNode node) {

        // **** sanity checks ****
        if (node == null)
            return new ArrayList<List<Integer>>();

        // **** initialization ****
        levelOrderList = new ArrayList<>();

        // **** recursion entry point ****
        levelOrderTraversal(node, 0);

        // **** return list of lists ****
        return levelOrderList;
    }


    /**
     * Traverse binary tree in level order.
     * Recursive call.
     */
    private void levelOrderTraversal(TreeNode node, int level) {
        if (node != null) {

            // **** initialize new array list ****
            if (level + 1 > levelOrderList.size())
                levelOrderList.add(new ArrayList<Integer>());

            // **** add node value to the list ****
            levelOrderList.get(level).add(node.val);

            // **** recurse left ****
            levelOrderTraversal(node.left, level + 1);

            // **** recurse right ****
            levelOrderTraversal(node.right, level + 1);
        }
    }


    /**
     * Utility function that returns the balance factor 
     * of the specified node in an AVL BST.
     */
    public int getBalanceAVL(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return heightAVL(node.left) - heightAVL(node.right);
        }
    }


    /**
     * Rotate right in BST.
     * A right rotation decreases the depth of the left subtree by 1, 
     * and increases that of the right subtree by 1.
     */
    public TreeNode rightRotate(TreeNode y) {

        // ???? ????
        System.out.println("rightRotate <<< y: " + y.toString());

        // **** initialization ****
        TreeNode x  = y.left;
        TreeNode t2 = x.right;

        // **** perform rotation ****
        x.right = y;
        y.left  = t2;

        // **** update heights ****
        y.height = max(heightAVL(y.left), heightAVL(y.right)) + 1; 
        x.height = max(heightAVL(x.left), heightAVL(x.right)) + 1; 

        // **** return new root ****
        return x;
    }


    /**
     * Rotate left in BST.
     * A left rotation decreases the depth of the right subtree by 1, 
     * and increases that of the left subtree by 1. 
     */
    public TreeNode leftRotate(TreeNode x) {

        // **** initialization ****
        TreeNode y  = x.right;
        TreeNode t2 = y.left;

        // **** perform rotation ****
        y.left  = x;
        x.right = t2;

        // **** update heights ****
        x.height = max(heightAVL(x.left), heightAVL(x.right)) + 1; 
        y.height = max(heightAVL(y.left), heightAVL(y.right)) + 1; 
  
        // **** return new root ****
        return y;
    }


    /**
     * Insert node into AVL BST.
     * Recursive call.
     */
    public TreeNode insertAVL(TreeNode node, int val) {

        // **** 1) normal BST value insertion ****
        if (node == null)
            return (new TreeNode(val));

        // **** look where to insert the new node (recursion) ****
        if (val < node.val)
            node.left = insertAVL(node.left, val);
        else if (val > node.val)
            node.right = insertAVL(node.right, val);
        else 
            return node;
        
        // **** 2) update the height of the root node (use instead private local method) ****
        node.height = 1 + max(heightAVL(node.left), heightAVL(node.right));

        // **** 3) get the balance factor ****
        int balance = getBalanceAVL(node);

        // **** 4a) Left Left case ****
        if (balance > 1 && val < node.left.val)
            return rightRotate(node);

        // **** 4b) Right Right case ****
        if (balance < -1 && val > node.right.val)
            return leftRotate(node);

        // **** 4c) Left Right case ****
        if (balance > 1 && val > node.left.val) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // **** 4d) Right Left case ****
        if (balance < -1 && val < node.right.val) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // **** return node pointer ****
        return node;
    }


    /**
     * Pre order traversal.
     * 
     * Recursive call.
     */
    public void preOrder(TreeNode node) {

        // **** base condition ****
        if (node == null)
            return;

        // **** display the node value ****
        System.out.print(node.toString() + " ");

        // **** left child sub tree ****
        preOrder(node.left);

        // **** right child sub tree ****
        preOrder(node.right);
    }


    /**
     * 
     */
    public String inOrder(TreeNode node) {

        // **** ****
        StringBuilder sb = new StringBuilder();

        // **** ****
        inOrder(node, sb);

        // **** ****
        return sb.toString();
    }

    
    /**
     * Inorder DFS traversal.
     * Populates a string builder.
     * Recursive call.
     */
    private void inOrder(TreeNode node, StringBuilder sb) {
        
        // **** base case ****
        if (node == null) return;

        // **** recurse left ****
        inOrder(node.left, sb);

        // **** append node representation to string ****
        sb.append(node.val + " ");

        // **** recurse right ****
        inOrder(node.right, sb);
    }


    /**
     * Clone a binary tree.
     * Entry point call.
     */
    public TreeNode cloneTree(TreeNode original) {
        TreeNode n = new TreeNode();
        n.val = original.val;
        cloneTree(original, n);
        return n;
    }


    /**
     * Clone a binary tree.
     * Recursive call.
     */
    private void cloneTree(TreeNode root, TreeNode newNode) {

        // **** end node ****
        if (root == null) {
            return;
        }

        // **** traverse left sub tree ****
        if (root.left != null) {
            newNode.left = new TreeNode();
            newNode.left.val = root.left.val;
            cloneTree(root.left, newNode.left);
        }

        // **** traverse right sub tree ****
        if (root.right != null) {
            newNode.right = new TreeNode();
            newNode.right.val = root.right.val;
            cloneTree(root.right, newNode.right);
        }
    }


    /**
     * Find node with specified value.
     * Recursion entry point.
     */
    public TreeNode findBT(TreeNode root, int val) {
        TreeNode[] found = new TreeNode[1];
        findBT(root, val, found);
        return found[0];
    }


    /**
     * Find node in binary tree with specified value.
     * Recursive call;
     */
    private void findBT(TreeNode root, int val, TreeNode[] found) {
        if (root != null) {

            // **** traverse left sub tree ****
            findBT(root.left, val, found);

            // **** check if this is the node we are looking for ****
            if (root.val == val) {
                found[0] = root;
            }

            // *** traverse right sub tree ****
            findBT(root.right, val, found);
        }
    }
}