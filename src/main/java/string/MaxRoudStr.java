package string;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2022/1/12 17:15
 * @description
 */
public class MaxRoudStr {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("cdabbacc"));
    }


    /**
     *   <p>
     *       回文准备：
     *          将字符串的空格以#填充
     *          ①：初始话maxRight和center为0
     *          ②：记录maxLen 记录回文过程中的最大回文字串，初始值为1
     *          ③：如何要求输出回文字串需要定义一个start为0
     *        过程分析：
     *          ①：当 i < maxRight时 此时的i必然在center的右侧
     *               需要求出以center 为中心，长度为i-center的左坐标
     *               ==> center - (i - center) = mirror
     *               ==> mirror = 2center - i
     *               此时的p[mirror] 就是之前已经回文过填充的值
     *               此时要考虑p[i]的值就是从 i 到maxRight之间的跳跃数和p[mirror]之间的最小值
     *               ==> p[i] = Math.min(maxRight-i,p[mirror])
     *           ②：定义下一次尝试扩散的起点：
     *               int left = i - (1 + p[i]);
     *               int right = i + (1 + p[i]);
     *           ③：进行回文扩散：
     *               while(left >= 0 && right < 字符串长度 && string.charAt(left) == string.charAt(right)){
     *                   p[i]++;
     *                   left--;
     *                   right++;
     *               }
     *            ④：更新maxRight和center
     *               当 i + p[i] > maxRight 时
     *                  maxRight = i + p[i]
     *                  center = i;
     *             ⑤：判断最长回文字串
     *                当p[i](就是在i节点的时候可以跳跃的步数即回文数) > maxLen时
     *                   maxLen = p[i];
     *                   start = ( i - maxLen) / 2;最大长度的起始坐标
     *
     *   </P>
     * @param:
     * @author:  zhang.rongjun
     * @DateTime: 2022/1/13 9:47
     * */
    public static String longestPalindrome(String s) {
        // 特判
        int len = s.length();
        if (len < 2) {
            return s;
        }
        // 得到预处理字符串
        String str = s.replaceAll("", "#");
        // 新字符串的长度
        int sLen = 2 * len + 1;
        // 数组 p 记录了扫描过的回文子串的信息
        int[] p = new int[sLen];
        // 双指针，它们是一一对应的，须同时更新
        int maxRight = 0;
        int center = 0;
        // 当前遍历的中心最大扩散步数，其值等于原始字符串的最长回文子串的长度
        int maxLen = 1;
        // 原始字符串的最长回文子串的起始位置，与 maxLen 必须同时更新
        int start = 0;
        for (int i = 0; i < sLen; i++) {
            if (i < maxRight) {
                int mirror = 2 * center - i;
                // 这一行代码是 Manacher 算法的关键所在，要结合图形来理解
                p[i] = Math.min(maxRight - i, p[mirror]);
            }
            // 下一次尝试扩散的左右起点，能扩散的步数直接加到 p[i] 中
            int left = i - (1 + p[i]);
            int right = i + (1 + p[i]);
            // left >= 0 && right < sLen 保证不越界
            // str.charAt(left) == str.charAt(right) 表示可以扩散 1 次
            while (left >= 0 && right < sLen && str.charAt(left) == str.charAt(right)) {
                p[i]++;
                left--;
                right++;
            }
            // 根据 maxRight 的定义，它是遍历过的 i 的 i + p[i] 的最大者
            // 如果 maxRight 的值越大，进入上面 i < maxRight 的判断的可能性就越大，这样就可以重复利用之前判断过的回文信息了
            if (i + p[i] > maxRight) {
                // maxRight 和 center 需要同时更新
                maxRight = i + p[i];
                center = i;
            }
            if (p[i] > maxLen) {
                // 记录最长回文子串的长度和相应它在原始字符串中的起点
                maxLen = p[i];
                start = (i - maxLen) / 2;
            }
        }
        System.out.println(maxLen);
        return s.substring(start, start + maxLen);
    }
}


//import java.io.*;
//class Main {
//    public static int way(char[] c, int left, int right) {
//        while (left >= 0 && right < c.length && c[left] == c[right]) {
//            left--;
//            right++;
//        }
//        return right - left - 1;
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String s;
//        while ((s = br.readLine()) != null && s.length() != 0) {
//            int ans = 0;
//            char[] c = s.toCharArray();
//            for (int i = 0; i < c.length; i++) {
//                ans= Math.max(ans, way(c, i, i));
//                if (i > 0) {
//                    ans = Math.max(ans, way(c, i - 1, i));
//                }
//            }
//            System.out.println(ans);
//        }
//    }
//}