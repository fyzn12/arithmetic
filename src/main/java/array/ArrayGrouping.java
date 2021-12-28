package array;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2021/12/28 9:20
 * @description
 */
public class ArrayGrouping {
    /**
     * <p>
     * 输入int型数组，询问该数组能否分成两组，使得两组中各元素加起来的和相等，并且，所有5的倍数必须在其中一个组中，所有3的倍数在另一个组中（不包括5的倍数），
     * 不是5的倍数也不是3的倍数能放在任意一组，可以将数组分为空数组，能满足以上条件，输出true；不满足时输出false。
     * ==> arr5 + arr3 + arrO = sum
     * ==> 要 将原arr 划分为满足条件的两个 arr 必须满足  sum/2 = sum3 = sum5
     * ==> 即 sum/2 -sum3 = 0  或者  sum/2 - sum5 = 0
     * ==> int target = sum/2 -sum3   表示 arr3要满足条件 还相差target  只需要在 aarO数组中找到 相加值为target 即可
     * ==> 因此将该问题转化为  给定一个数组和一个目标数，在数组中找出相加和为目标数的问题
     * 采用深度优先搜索进行查询
     * </P>
     *
     * @param: args
     * @DateTime: 2021/12/28 9:20
     */

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            int n = Integer.parseInt(str);
            int sum = 0, sum5 = 0;
            List<Integer> other = new LinkedList<>();
            // 标记是否被占用
            Map<Integer, Boolean> check = new HashMap<>();
            String[] arrs = bf.readLine().split(" ");
            int count = 0;
            for (int i = 0; i < n; i++) {
                Integer num = Integer.parseInt(arrs[i]);
                sum += num;
                if (num % 5 == 0 && num != 0) {
                    sum5 += num;
                } else if (num % 3 != 0) {
                    // other 部分数据
                    other.add(num);
                    check.put(count++, false);
                }
            }
            if ((sum & 1) != 0) {
                System.out.println(false);
            } else {
                int target = sum / 2 - sum5;
                if (target == 0) {
                    System.out.println(true);
                } else {
                    System.out.println(dfs(other, check, target, 0));
                }
            }
        }
    }

    public static Boolean dfs(List<Integer> list, Map<Integer, Boolean> check, int target, int index) {
        // 递归终止条件
        if (target == 0 || index == list.size()) {
            return target == 0 ;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!check.get(i)) {
                // 将该数占用
                check.put(i, true);
                if (dfs(list, check, target - list.get(i), index + 1)) {
                    return true;
                }
                // 走完一次流程，发现没有找到满足条件的值 回溯到下一个节点开始下一轮查找，释放本次占用
                check.put(i, false);
            }
        }
        // 回溯的依据
        return false;
    }


    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值
     * 的那 两个 整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 用这个数以此减去数组每一个数，再去找数组里有没有另一个数
     * nums = [2,7,11,15], target = 9   // [3  3]   0 1  =6
     */
    public static int[] twoSum(int[] nums, int target) {
        //新建一个存储找到索引的数组，第一个元素致为-1 ,以免为0判断失误
        int[] arr = new int[2];
        arr[0] = -1;
        for (int i = 0; i < nums.length; i++) {
            //如果能找到数字A，就证明相加等于target
            int a = target - nums[i];
            //需要查找的数组，不能为数字a ，需要找数字b
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == a && j != i) {
                    //如果找到就放到数组里面
                    arr[0] = j;
                    break;
                }
            }
            if (arr[0] != -1) {
                arr[1] = i;
                break;
            }
        }

        return arr;
    }


}
