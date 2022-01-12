package mathematic_model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2022/1/12 14:32
 * @description
 */
public class FootballMatch {

    /**
     *   <p>
     *      有三只球队，每只球队编号分别为球队1，球队2，球队3，这三只球队一共需要进行 n 场比赛。现在已经踢完了k场比赛，每场比赛不能打平，
     *      踢赢一场比赛得一分，输了不得分不减分。已知球队1和球队2的比分相差d1分，球队2和球队3的比分相差d2分，每场比赛可以任意选择两只队伍进行。
     *      求如果打完最后的 (n-k) 场比赛，有没有可能三只球队的分数打平.
     *
     *      输入描述：
     *         第一行包含一个数字 t (1 <= t <= 10)
     *         接下来的t行每行包括四个数字 n, k, d1, d2(1 <= n <= 10^12; 0 <= k <= n, 0 <= d1, d2 <= k)
     *      输出描述：
     *         每行的比分数据，最终三只球队若能够打平，则输出“yes”，否则输出“no”
     *   </P>
     * @param:
     * @author:  zhang.rongjun
     * @DateTime: 2022/1/12 14:32
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while((str = bf.readLine())!=null) {
            int t = Integer.parseInt(str);
            while (t-- > 0) {
                String[] arr = bf.readLine().split(" ");
                long n = Long.parseLong(arr[0]);
                long k = Long.parseLong(arr[1]);
                long d1 = Long.parseLong(arr[2]);
                long d2 = Long.parseLong(arr[3]);
                long dMax = Math.max(d1, d2);
                // 需要同时满足三个条件才有可能追平比赛
                // ① n是3的倍数 ② n 减 k（剩余场数）>= d1 d2的最大值 ③ d1和d2的最大值要小于n的三分之一，因为追平时三个球队的得分就是n的三分之一
                if ((n % 3) == 0 && (n - k) >= dMax && dMax < (n / 3)) {
                    System.out.println("yes");
                    continue;
                }
                System.out.println("no");
            }
        }
    }
}
