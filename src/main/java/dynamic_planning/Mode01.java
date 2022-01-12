package dynamic_planning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2022/1/11 11:09
 * @description
 */
public class Mode01 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            int n = Integer.parseInt(str);
            String[] w = bf.readLine().split(" ");
            String[] v = bf.readLine().split(" ");
            int[][] dp = new int[w.length + 1][n + 1];
            for (int i = 1; i <= w.length; i++) {
                for (int j = 1; j <= n; j++) {
                    int wi = Integer.parseInt(w[i - 1]);
                    // dp[i-1][j -wi]代表不加当前物品前的最大价值
                    dp[i][j] = j < wi?dp[i - 1][j]:Math.max(dp[i - 1][j],dp[i-1][j -wi] + wi * Integer.parseInt(v[i -1]));
                }
            }
            System.out.println(dp[w.length][n]);
        }
    }
}
