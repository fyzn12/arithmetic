# 二叉树的路径遍历  
  
> 输出二叉树的路径实现注意一下几点  
 
*  遍历路径时是从叶子节点到根节点，即：node.left == null && node.right==null时输出node.val   
*  当node.left != null 时，保存node.left到队列中或者是dfs
*  当node.right != null 时，保存node.right到队列中或者是dfs


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
### 二叉数路径遍历的延申题   

#### 延申1
> 给你二叉树的根节点root 和一个表示目标和的整数targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和targetSum 。如果存在，返回 true ；否则，返回 false 。

```java
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

```
#### 延申2
> 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
  
```java
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
```    
#### 延申3
![avatar](./img/path1.png)
![avatar](./img/path2.png)
![avatar](./img/path3.png)  
  
```java
public int sumNumbers(TreeNode root) {
        if(root == null)return 0;
        Queue<String> path = new LinkedList<>();
        Queue<TreeNode> nodeQue = new LinkedList<>();
        path.add("");
        nodeQue.add(root);
        int sum = 0;
        while(!nodeQue.isEmpty()){
            TreeNode node = nodeQue.poll();
            String curPath = path.poll();
            curPath += node.val;
            if(node.left == null && node.right == null){
               sum += Integer.parseInt(curPath);
            }
            if(node.left != null){
                path.add(curPath);
                nodeQue.add(node.left);
            }
            if(node.right != null){
                path.add(curPath);
                nodeQue.add(node.right);
            }
        }
        return sum;
}
```

#### 路径的另一种题型  
  
![avatar](./img/maxSum1.png)  
![avatar](./img/maxSum2.png)  
  
##### 分析  
> 首先，考虑实现一个简化的函数 maxGain(node)，该函数计算二叉树中的一个节点的最大贡献值，具体而言，就是在以该节点为根节点的子树中寻找以该节点为起点的一条路径，使得该路径上的节点值之和最大。
具体而言，该函数的计算如下。 
> 空节点的最大贡献值等于 00。
> 非空节点的最大贡献值等于节点值与其子节点中的最大贡献值之和（对于叶节点而言，最大贡献值等于节点值）。

> 例如，考虑如下二叉树。


    -10    
    / \   
    9  20    
    /  \     
    15   7     

> 叶节点 99、1515、77 的最大贡献值分别为 99、1515、77。    
> 得到叶节点的最大贡献值之后，再计算非叶节点的最大贡献值。   
> 节点 2020 的最大贡献值等于 20+\max(15,7)=3520+max(15,7)=35，    
> 节点 -10−10 的最大贡献值等于 -10+\max(9,35)=25−10+max(9,35)=25。     
上述计算过程是递归的过程，因此，对根节点调用函数 maxGain，即可得到每个节点的最大贡献值。
> 根据函数 maxGain 得到每个节点的最大贡献值之后，如何得到二叉树的最大路径和？对于二叉树中的一个节点，   
> 该节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值，如果子节点的最大贡献值为正，则计入该节点的最大路径和，否则不计入该节点的最大路径和。维护一个全局变量 maxSum 存储最大路径和，在递归过程中更新 maxSum 的值，最后得到的 maxSum 的值即为二叉树中的最大路径和。

```java
class Solution {
int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    public int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewpath = node.val + leftGain + rightGain;

        // 更新答案
        maxSum = Math.max(maxSum, priceNewpath);

        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }
}
```

> 复杂度分析

> 时间复杂度：O(N)O(N)，其中 NN 是二叉树中的节点个数。对每个节点访问不超过 2 次。

> 空间复杂度：O(N)O(N)，其中 NN 是二叉树中的节点个数。空间复杂度主要取决于递归调用层数，最大层数等于二叉树的高度，最坏情况下，二叉树的高度等于二叉树中的节点个数。

## 二叉树的遍历  
  
### 中序遍历  
  
* 左   
* 根   
* 右   
  
```java
    // List<Integer> r = new LinkedList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> r = new LinkedList<>();
        if(null == root)return r;
        // 方法一 ：递归方式实现
        //  be( root);  
        // 方法 2：采用迭代法 ---难点在于保存左子树，迭代root
        while(root != null || !stack.isEmpty()){
            // 先将左子树保存到栈中
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 获取左子树的值（有可能也是跟节点的值）
            r.add(root.val);
            // 保存右子树
            root = root.right;
        }
        return r;
    }


    // public void be(TreeNode node){
    //     if(node == null){
    //         return;
    //     }
    //     be(node.left);
    //     r.add(node.val);
    //     be(node.right);
    // }
```