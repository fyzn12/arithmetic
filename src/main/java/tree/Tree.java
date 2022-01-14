package tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2022/1/6 17:35
 * @description
 */
public class Tree {
    static class TreeNode{
        int val;
        TreeNode right;
        TreeNode left;
    }

    private static List<String> binTreePath1(TreeNode root) {
        LinkedList<String> l = new LinkedList<String>();
        //如果为空，返回空
        if(root==null)return l;
        getPath(root,l,root.val+"");
        return l;
    }


    /** 二叉树的路径遍历 */
    private static void getPath(TreeNode root, List<String> re, String s) {
        //此时为叶节点，把s添加到结果列表中，返回
        if(root.left==null && root.right==null) {
            re.add(s+"");
            return ;
        }
        //左子树不为空，递归
        if(root.left!=null)
            getPath(root.left,re,s+"->"+root.left.val);
        //右子树不为空，递归
        if(root.right!=null)
            getPath(root.right,re,s+"->"+root.right.val);
    }


    /** 二叉树的路径遍历：队列BFS算法应用 */
    private static List<String> binTreePath2(TreeNode root) {
        //结果列表
        List<String> l=new ArrayList<String>();
        //存储节点队列
        Queue<TreeNode> queueNode = new LinkedList<TreeNode>();
        //存储路径队列
        Queue<String> queueStrPath = new LinkedList<String>();
        if(root==null)return l;
        queueNode.add(root);
        queueStrPath.add("");
        while(!queueNode.isEmpty()) {
            TreeNode curNode = queueNode.poll();
            String curPath = queueStrPath.poll();
            if(curNode.left==null && curNode.right==null) {
                l.add(curPath+curNode.val);
            }
            if(curNode.left!=null) {
                queueNode.add(curNode.left);
                queueStrPath.add(curPath+curNode.val+"->");
            }
            if(curNode.right!=null) {
                queueNode.add(curNode.right);
                queueStrPath.add(curPath+curNode.val+"->");
            }
        }

        return l;
    }


    public boolean hasPathSum(TreeNode root, int targetSum) {
        Queue<Integer> pathSum = new LinkedList<>();
        Queue<TreeNode> nodeQue = new LinkedList<>();
        nodeQue.add(root);
        pathSum.add(0);
        while(!nodeQue.isEmpty()){
            TreeNode node = nodeQue.poll();
            Integer curPathSum = pathSum.poll();
            curPathSum += node.val;
            if(node.left == null && node.right == null){
                if(curPathSum == targetSum)return true;
            }
            if(node.left != null){
                nodeQue.add(node.left);
                pathSum.add(curPathSum);
            }
            if(node.right != null) {
                nodeQue.add(node.right);
                pathSum.add(curPathSum);
            }
        }
        return false;
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new LinkedList<>();
        if (null == root)return result;
        Queue<Integer> pathSum = new LinkedList<Integer>();
        Queue<String> path = new LinkedList<>();
        Queue<TreeNode> nodeQue = new LinkedList<>();
        nodeQue.add(root);
        pathSum.add(0);
        while(!nodeQue.isEmpty()){
            TreeNode node = nodeQue.poll();
            Integer curPathSum = pathSum.poll();
            String curPath = path.poll();
            curPathSum += node.val;
            curPath += node.val;
            if(node.left == null && node.right == null){
                if(curPathSum == targetSum){
                    String[] arr = curPath.split("->");
                    List<Integer> list = Arrays.stream(arr).map(Integer::parseInt).collect(Collectors.toList());
                    result.add(list);
                }
            }
            if(node.left != null){
                nodeQue.add(node.left);
                pathSum.add(curPathSum);
                path.add(curPath +"->");
            }
            if(node.right != null) {
                nodeQue.add(node.right);
                pathSum.add(curPathSum);
                path.add(curPath +"->");
            }

        }
        return result;
    }


    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        return dfs(root);
    }

    private int dfs(TreeNode root){
        if(root == null)return 0;
        int maxLeft = Math.max(dfs(root.left),0);
        int maxRight = Math.max(dfs(root.right),0);
        int curMax = Math.max(maxLeft + root.val,maxRight+root.val);
        max = Math.max(max,curMax) + root.val;
        return max;
    }


}
