package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2022/1/5 11:13
 * @description 最大递增子序列
 */
public class LIS {
    public static void main(String[] args) throws Exception{
//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str  = "4 2 3 1 5 6";
//        while ((str = bf.readLine())!= null){
            String[] arr = str.split(" ");
            int[] dp = new int[arr.length];
            dp[1] = Integer.parseInt(arr[0]);
            int p = 1;
            // 4 2 3 1 5 6
            for (int i = 1;i<arr.length;i++){
                int tmp = Integer.parseInt(arr[i]);
                if (tmp > dp[p]){
                    dp[p+1] = tmp;
                    p++;
                }else {
                    //替换dp数组中第一个比arr[i]大的数
                    int l = 0, r = p;
                    while (l <= r){
                        int mid = l + ((r-l)>>1);
                        if (dp[mid] > tmp){
                            r = mid;
                        }else {
                            l = mid +1;
                        }
                        if ( l == r && dp[l] >tmp){
                            dp[1] = tmp;
                            break;
                        }
                    }
                }
            }
            for (int i = 1;i<dp.length;i++){
                System.out.println(dp[i]);
            }
//        }
    }
}
