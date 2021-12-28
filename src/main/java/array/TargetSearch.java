package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2021/12/28 16:42
 * @description
 */
public class TargetSearch {

    /**
     * <p>
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值
     * 的那 两个 整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum
     * <p>
     * 用这个数以此减去数组每一个数，再去找数组里有没有另一个数
     * nums = [2,7,11,15], target = 9   // [3  3]   0 1  =6
     * </P>
     *
     * @param: args
     * @DateTime: 2021/12/28 16:42
     */
    public static void main(String[] args) {
        int[] t = new int[]{2, 7,3,3, 17, 15};

//        Map<Integer, Boolean> map = new HashMap<>();
//        map.put(0, false);
//        map.put(1, false);
//        map.put(2, false);
//        map.put(3, false);
//        dfs(t, map, 9, 0);
//        for (int i = 0; i < 2; i++) {
//            System.out.println(re[i]);
//        }
        int[] ints = hashMethod(t, 5);

        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }


    }

    static int[] re = new int[2];

    /**
     * <p>
     * 查找  方法1深度优先搜索
     * </P>
     */
    public static Boolean dfs(int[] arr, Map<Integer, Boolean> map, int target, int index) {

        if (target == 0 || index == arr.length) {
            if (target == 0 && 0 == re[1]) {
                re[1] = index;
            }
            return target == 0;
        }
        for (int i = index; i < arr.length; i++) {
//            if (!map.get(i)){
//                map.put(i,true);
            if (dfs(arr, map, target - arr[i], index + 1)) {
                re[0] = i;
                return true;
            }
//                map.put(i,false);
//            }
        }
        return false;
    }


    /**
     * 方法2 利用hash索引
     *
    * @param arr 代求数组
    * @param target 目标数
    * */
    public static int[] hashMethod(int[] arr,int target){
        int[] result = new int[2];
        Map<Integer, Object> tmp = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (tmp.containsKey(num)) {
                Object o = tmp.get(num);
                String str = o + "," + i;
                tmp.put(num, str);
            } else {
                tmp.put(num, i);
            }
        }
        loop:for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            int c = target - num;
            if (tmp.containsKey(c)){
                result[0] = i;
                String str = String.valueOf(tmp.get(c));
                String[] strings = str.split(",");
                for (String s : strings){
                    if (i != Integer.parseInt(s)){
                        result[1] = Integer.parseInt(s);
                        break loop;
                    }
                }
            }
        }
        return result;
    }
}

