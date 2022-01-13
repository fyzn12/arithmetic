# 二叉树的路径遍历  
  
> 输出二叉树的路径  
  
```java
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
```  


> 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
