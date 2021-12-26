package recursion;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author ZhangRongJun
 * @version 1.0
 * @date 2021/12/26 18:53
 * @description:最小公倍数
 */
public class LowestCommonMultiple {

    /**
     * 两个数最小公倍数 = 两个数相乘 除以 两个数的最大公因数
     * 最大公因数求法
     * 辗转相除法：辗转相除法是求两个自然数的最大公约数的一种方法，也叫欧几里德算法。
     * 例如，求（319，377）：
     * ∵ 319÷377=0（余319）
     * ∴（319，377）=（377，319）；
     * ∵ 377÷319=1（余58）
     * ∴（377，319）=（319，58）；
     * ∵ 319÷58=5（余29）
     * ∴ （319，58）=（58，29）；
     * ∵ 58÷29=2（余0）
     * ∴ （58，29）= 29；
     * ∴ （319，377）=29。
     */
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        while ((str = bf.readLine()) != null) {
            if (null == str || str.trim().equals("")) break;
            String[] arr = str.split(" ");
            int a = Integer.parseInt(arr[0]);
            int b = Integer.parseInt(arr[1]);
            // 求最大公因数
            int max = Math.max(a, b);
            int min = Math.min(a, b);
            int remainder = max % min;
            if (remainder != 0) {
                while (true) {
                    max = min;
                    min = remainder;
                    remainder = max % remainder;
                    if (remainder == 0) {
                        remainder = min;
                        break;
                    }
                }
            } else {
                remainder = min;
            }
            System.out.println((a * b) / remainder);
        }
    }
}
