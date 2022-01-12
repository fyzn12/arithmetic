package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2022/1/12 15:33
 * @description
 */
public class WindowSolve {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while((str = bf.readLine()) != null){
            String[] arrSum = str.split(" ");
            int n = Integer.parseInt(arrSum[0]);
            int m = Integer.parseInt(arrSum[1]);
            str = bf.readLine();
            int l = 0,r=0,an=0,bn=0,max=0;
            while(r < n){
                if(str.charAt(r) == 'a'){
                    an++;
                }else {
                    bn++;
                }
                // 执行替换
                if (an <= m || bn<=m){
                    r++;
                }else{
                    // 当达到替换的值时考虑最长字串
                    max = Math.max(max,r-l);
                    if(str.charAt(l) == 'a'){
                        an--;
                    }else {
                        bn--;
                    }
                    r++;
                    l++;
                }
            }
            // 避免最后一次r++没有计算成功
            max = Math.max(max,r-l);
            System.out.println(max);
        }
    }

    private static int windowSolve(String str, int oper, int len) {
        int res = Integer.MIN_VALUE;
        // 滑动窗口设置两个指针lr
        int left = 0, right = 0;
        // 滑动窗口设置两个指针lr
        int an = 0, bn = 0;
        while (right < len) {
            if (str.charAt(right) == 'a') {
                an++;
            } else {
                bn++;
            }
            // right一直往右走，寻找可行解
            if (an <= oper || bn <= oper) {
                right++;
            } else {  // 从可行解中找最优解：left开始往右走
                // 此时窗口中已经出现了oper个可以改变的字符,更新结果
                res = Math.max(res, right - left);
                // left指针往左走，窗口中退出一个字符相应的计数减1，
                // 而right指针新指的字符计数已经在刚进入while语句时的if判断已加1
                if (str.charAt(left) == 'a') {
                    left++;
                    an--;
                } else {
                    left++;
                    bn--;
                }
                right++;
            }
        }
        res = Math.max(res, right - left);
        return res;
    }

}
