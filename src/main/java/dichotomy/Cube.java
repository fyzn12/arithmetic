package dichotomy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2021/12/27 15:20
 * @description 求一个数的立方根
 */
public class Cube {
    /**
     * 计算一个浮点数的立方根，不使用库函数。
     * 保留一位小数。
     *
     * 数据范围：
     *
     * 输入描述：
     * 待求解参数，为double类型（一个实数）
     *
     * 输出描述：
     * 输入参数的立方根。保留一位小数。
     *
     * 示例1
     * 输入：
     * 216
     * 输出：
     * 6.0
     * 示例2
     * 输入：
     * 2.7
     * 输出：
     * 1.4
     */
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str = bf.readLine();
        Double value = Double.parseDouble(str.trim());
        if (value == 0.0 || value == 1D || value == -1D) {
            System.out.println(value);
            return;
        }

        // 使用BigDecimal实现四舍五入(支持float和double类型)
        boolean flag = false;
        if (value < 0) {
            flag = true;
            value = -value;
        }

        // 因为负数也有立方根
        double left = value < 1 ?value:0;
        double right = value < 1? 1: value;
        while (left < right) {
            double m = ((left + right) / 2.0);
            double tmp = m * m * m;
            if (tmp > value) {
                right = m;
            } else if (tmp < value) {
                 left = m;
            }
            if (tmp == value || Math.abs(tmp - value) <= 0.000001) {
                double r = new BigDecimal(m).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                System.out.println(flag?-r:r);
                return;
            }
        }

    }



//        public static void main(String[] args) throws IOException {
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//            String str = "";
//            while((str = br.readLine())!=null){
//                double num = Double.valueOf(str);
//                double start = 0;
//                double end = num;
////          排序
//                if(end<start){
//                    double tmp = end;
//                    end = start;
//                    start = tmp;
//                }
////          处理0~1之间 越乘越小的问题
//                if(num<0 && num>-1)start=-1;
//                if(num>0 && num<1)end=1;
//
//                double middle = (start + end) / 2;
//                double thred = middle * middle * middle;
//                double diff = Math.abs(thred - num);
//
//                while(diff>0.000001){
//                    if(thred > num) end = middle;
//                    else start = middle;
//
//                    middle = (start + end)/2;
//                    thred = middle * middle * middle;
//                    diff = Math.abs(thred - num);
//                }
////          数学运算比内置转换快
//                System.out.println((double)Math.round(middle*10)/10);
//            }
//        }

}
