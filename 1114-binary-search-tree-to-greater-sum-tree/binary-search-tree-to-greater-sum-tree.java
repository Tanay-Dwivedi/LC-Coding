class Solution {
    public TreeNode bstToGst(TreeNode root) {
        int[] sum = new int[1];
        solveBst(root, sum);
        return root;
    }
    
    public int solveBst(TreeNode root, int[] sum) {
        if(root == null) return sum[0];
        
        int rS = solveBst(root.right, sum);
        root.val += rS;
        sum[0] = root.val;
        int lS = solveBst(root.left, sum);
        sum[0] = lS;
        
        return sum[0];
    }
}