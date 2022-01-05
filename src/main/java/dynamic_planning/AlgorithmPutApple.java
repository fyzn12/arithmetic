package dynamic_planning;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author ZhangRongJun
 * @version 1.0
 * @date 2021/12/25 22:06
 * @description:TODO
 */
public class AlgorithmPutApple {
    /**
     * <p>
     * <p>
     * 把m个同样的苹果放在n个同样的盘子里，允许有的盘子空着不放，问共有多少种不同的分法？（用K表示）5，1，1和1，5，1 是同一种分法。
     * 数据范围：，。
     * <p>
     * 本题含有多组样例输入。
     * 输入描述：
     * <p>
     * 输入两个int整数
     * 输出描述：
     * <p>
     * 输出结果，int型
     * 示例1
     * 输入：
     * 7 3
     * 输出：
     * 8
     * </p>
     */


//    public static void main(String[] args) throws Exception {
//        List<Map<String, Integer>> list = new LinkedList<>();
//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        String str=" ";
//        while((str = bf.readLine()) != null){
//            if (str.equals(" "))break;
//            // 苹果数
//            String[] s = str.split(" ");
//            int m = Integer.parseInt(s[0]);
//            // 盘子数
//            int n = Integer.parseInt(s[1]);
//            Map<String, Integer> map = new HashMap<>();
//            map.put("m", m);
//            map.put("n", n);
//            list.add(map);
//        }
//        List<Integer> result = new LinkedList<>();
//        for(Map<String,Integer> map: list){
//            int m = map.get("m");
//            int n = map.get("n");
//            if (m == 0 || m == 1) {
//                result.add(m);
//                continue;
//            }
//            if (n == 1) {
//                result.add(n);
//                continue;
//            }
//            int[][] dp = new int[n + 1][m + 1];
//            for (int i = 1; i <= n; i++) {
//                dp[i][0] = 0;
//                dp[i][1] = 1;
//            }
//            for (int i = 2; i <= m; i++) dp[1][i] = 1;
//            for (int i = 2; i <= n; i++) {
//                for (int j = 2; j < m + 1; j++) {
//                    if (j == 2) {
//                        dp[i][j] = 2;
//                        continue;
//                    }
//                    dp[i][j] = dp[i - 1][j] + ((j - i) < 0 ? 0 : dp[i][j - i]);
//                }
//            }
//            result.add(dp[n][m]);
//        }
//        result.forEach(System.out::println);
//    }


    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String str = " ";
//        while((str = br.readLine()) != null){
//            if (str.equals(""))break;
//            String[] s = str.split(" ");
//            int m = Integer.parseInt(s[0]);
//            int n = Integer.parseInt(s[1]);
//            System.out.println(count(m,n));
//        }
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while((str = bf.readLine()) != null){
            String[] arr = str.split(" ");
            // 苹果数
            int n = Integer.parseInt(arr[0]);
            // 盘子数
            int m = Integer.parseInt(arr[1]);
            m = Math.min(n, m);
            int[][] dp = new int[m+1][n+1];
            for (int i = 0;i <= m;i++) {
                dp[i][0] = 0;
                dp[i][1] = 1;
            }
            for (int i = 0;i <= n;i++) {
                dp[0][i] = 0;
                if (i != 0) dp[1][i] = 1;
            }
            for (int i = 2;i<=m;i++){
                for (int j = 2;j<= n;j++){
                    if (j>=i)
                        dp[i][j] = dp[i-1][j] + dp[i][j - i];
                    else dp[i][j] = dp[i-1][j];
                }
            }
            System.out.println(dp[m][n]);
        }

    }
    public static int count(int m,int n){
        if(n == 1 || m == 0)return 1;
        else if(n > m)return count(m,m);
        else return count(m,n - 1) + count(m - n,n);
    }
}
