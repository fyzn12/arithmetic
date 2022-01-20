package tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2022/1/18 17:35
 * @description
 */
public class Tree2 {
    static class TreeNode{
        int val;
        TreeNode right;
        TreeNode left;

        public TreeNode(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", right=" + right +
                    ", left=" + left +
                    '}';
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        left.setLeft(null);
        left.setRight(null);

        TreeNode right = new TreeNode(3);
        TreeNode right2 = new TreeNode(4);
        TreeNode right3 = new TreeNode(5);

        right.setLeft(right2);
        right.setRight(right3);

        root.setLeft(left);
        root.setRight(right);

        System.out.println(rserialize(root, ""));
        System.out.println(serialize(root));
        System.out.println(deserialize("[1,2]"));






    }

    public static String rserialize(TreeNode root, String str) {
        if (root == null) {
            str += "None,";
        } else {
            str += str.valueOf(root.val) + ",";
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        }
        return str;
    }

    public static TreeNode deserialize(String data) {
        TreeNode root = null;
        if (null == data)return root;
        List<String> list = Arrays.asList(data.substring(1,data.length()-1).split(","));
        Queue<TreeNode> queue = new LinkedList<>();
        root = new TreeNode(Integer.parseInt(list.get(0).trim()));
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty() && i<list.size()){
            TreeNode node = queue.poll();
            if (!"null".equals(list.get(i).trim())){
                node.left = new TreeNode(Integer.parseInt(list.get(i).trim()));
                queue.offer(node.left);
            }
            i++;
            if (i < list.size() && !"null".equals(list.get(i).trim())){
                node.right = new TreeNode(Integer.parseInt(list.get(i).trim()));
                queue.offer(node.right);
            }
            i++;
        }
        return root;
    }




    public static String serialize(TreeNode root) {
        List<String> list = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(null == node){
                list.add("null");
                continue;
            }
            list.add(String.valueOf(node.val));
            queue.add(node.left);
            queue.add(node.right);
        }
        if(list.isEmpty())return list.toString();
        // 由Arrays.asList(split);转化的list，对其进行增删时会报 java.lang.UnsupportedOperationException，解决方法就是基于该转化的list，重新实例化一个list
        while (true){
            int size = list.size();
            if ("null".equals(list.get(size -1))){
                list.remove(size-1);
                continue;
            }
            break;
        }
        return list.toString();
    }


    int count = 0;
    public int pathSum(TreeNode root, int targetSum) {
        dfs(root,targetSum);
        return count;

    }

    int dfs(TreeNode root, int targetSum){
        if(root == null)return 0;
        if(root.val == targetSum){
            count++;
            dfs( root.left, targetSum);
            dfs( root.right, targetSum);
            return 0;
        }
        int left = dfs(root.left,  targetSum);
        int right = dfs(root.right,targetSum);


        if((root.val + left) == targetSum || (root.val + right) == targetSum || (root.val + left +right) == targetSum){
            count++;
            return root.val;
        }else if((root.val + left) > targetSum && (root.val + right) > targetSum && (root.val + left +right) > targetSum){
            return root.val > targetSum?0:root.val;
        }else{
            return root.val + left < targetSum?root.val + left:root.val + right<targetSum?root.val + right:root.val + left +right < targetSum?root.val + left +right:0;
        }

    }



}
