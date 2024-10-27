
import java.util.*;
class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public boolean hasPathSum(TreeNode root, int targetSum) {
        
        DFSVisit(root,targetSum);
        return awn;
    }
    HashSet<TreeNode> visited = new HashSet<>();
     boolean awn = false; 
    public  void DFSVisit(TreeNode root , int targetSum){
        if( root != null){
            visited.add(root);
           
            targetSum-=root.val;
            
            if( root.left == null && root.right == null){
                if(targetSum == 0){
                    
                    awn = true; 
                }
            }
            ArrayList<TreeNode> children = new ArrayList<>(); 
            if(root.left!= null){
                children.add(root.left);
            }
            if(root.right != null){
                children.add(root.right);
            }
            for(TreeNode child: children){
                if(!visited.contains(child)){
                    DFSVisit(child,targetSum);
                }   
            }   
        }
        
    }

}