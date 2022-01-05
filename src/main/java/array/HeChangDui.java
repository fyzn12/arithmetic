package array;
import java.io.*;
/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2022/1/5 16:43
 * @description
 */
public class HeChangDui {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        while ((str = br.readLine()) != null) {
            int n = Integer.parseInt(str);
            String[] arr = br.readLine().split(" ");
            if (n <= 1) {
                System.out.println(0);
                continue;
            }
            int[] dpL = new int[n];
            dpL[0] = Integer.parseInt(arr[0]);
            int p = 1;
            int[] dpR = new int[n];
            dpR[0] = Integer.parseInt(arr[n-1]);
            int[] dp = new int[n];
            for(int i = 1;i<n;i++){
                int tmp = Integer.parseInt(arr[i]);
                if(tmp > dpL[p-1]){
                    dp[i] = p;
                    dpL[p++] = tmp;
                }else{
                    // 不满足递增时，找到该值在递增中的位置，也就是说找到dpL中第一个大于tmp的位置
                    int l = 0,r = p-1;
                    while( l < r){
                        int mid = l + ((r - l)>>1);
                        if(dpL[mid] < tmp)l = mid + 1;
                        else r = mid;
                    }
                    // 替换dpL中的值，保证dpL递增
                    dpL[l] = tmp;
                    // i位置时左边由l递增子序列
                    dp[i] = l;
                }
            }
            // 重新初始化p
            p = 1;
            for(int i = n -2;i>=0;i--){
                int tmp = Integer.parseInt(arr[i]);
                if(tmp > dpR[p-1]){
                    dp[i] += p;
                    dpR[p++] = tmp;
                }else{
                    int l = 0,r = p-1;
                    while(l < r){
                        int mid = l + ((r -l)>>1);
                        if(dpR[mid] < tmp)l = mid +1;
                        else r = mid;
                    }
                    dpR[l] = tmp;
                    dp[i] += l;
                }
            }
            int max = 1;
            for( int t : dp)max = Math.max(max,t);
            System.out.println(n - max -1);
        }
    }
}
