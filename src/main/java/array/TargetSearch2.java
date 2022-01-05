package array;

import string.LIS;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2022/1/5 12:53
 * @description 在一个升序数组中快速找到第一个大于目标数的问题 时间复杂度lg(N)
 */
public class TargetSearch2 {


    /**
     *   <p>
     *     在一个有序数组中找到第一个大于目标数的值，要求时间复杂度为lg(N) 。
     *     该题型演化成求一个数组中的最大递增子序列问题 见下面方法LIS
     *   </P>
     * @param: args 输入参数
     * @author:  zhang.rongjun
     * @DateTime: 2022/1/5 13:08
     * */
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5};
        int target = 2;
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] > target){
                r = mid;
            }else {
                l = mid +1;
            }
            // 设置终止条件
            if (l == r && arr[mid] > target){
                System.out.println(arr[l]);
                // 必须添加break，不然会一直循环
                break;
            }
        }

        int[] arr2 = new int[]{ 4, 2, 3, 1, 5, 6, 4, 8, 5, 9};
        System.out.println(LIS(arr2));
    }

    /** 最大递增子序列问题 */
    public static int LIS(int[] arr){
        // 声明一个dp数组，该数组中存放递增的元素
        int[] dp = new int[arr.length];
        // 初始化dp[1]
        dp[1] = arr[0];
        // 定义一个最大子序列指针
        int p = 1;
        for (int i = 1;i<arr.length;i++){
            if (arr[i] > dp[p]){
                dp[p+1] = arr[i];
                p++;
            }else {
                // 替换dp数组中第一个大于arr[i]的值
                int l = 0 ,r = p;
                while (l <= r){
                    int mid = l + ((r -l)>>1);
                    if (dp[mid] > arr[i]){
                        r = mid;
                    }else {
                        l = mid +1;
                    }
                    // 终止条件
                    if (l == r && dp[l] > arr[i]){
                        // 替换第一个大于arr[i]的dp值
                        dp[l] = arr[i];
                        break;
                    }
                }
            }
        }
        return p;
    }


    /** 暴力破解法 */
    private static int f(int[] arr) {
        int maxCnt = 0;
        for (int i = 0; i < arr.length; i++) {
            int p = i;
            int cnt = 1;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[p]) {
                    cnt++;
                    p = j;
                }
            }
            maxCnt = Math.max(maxCnt, cnt);
        }
        return maxCnt;

    }





}
