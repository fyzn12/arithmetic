package array;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2021/12/29 14:39
 * @description
 */
public class Labyrinth {
    /**
     * <p>
     * 定义一个二维数组 N*M ，如 5 × 5 数组下所示：
     * int maze[5][5] = {
     * 0, 1, 0, 0, 0,
     * 0, 1, 1, 1, 0,
     * 0, 0, 0, 0, 0,
     * 0, 1, 1, 1, 0,
     * 0, 0, 0, 1, 0,
     * };
     * 它表示一个迷宫，其中的1表示墙壁，0表示可以走的路，只能横着走或竖着走，不能斜着走，
     * 要求编程序找出从左上角到右下角的路线。入口点为[0,0],既第一格是可以走的路。
     * 本题含有多组数据。
     * 数据范围：  ， 输入的内容只包含
     * 输入描述：
     * 输入两个整数，分别表示二维数组的行数，列数。再输入相应的数组，其中的1表示墙壁，0表示可以走的路。
     * 数据保证有唯一解,不考虑有多解的情况，即迷宫只有一条通道。
     * </P>
     *
     * @DateTime: 2021/12/29 14:40
     */
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            String[] arrs = str.split(" ");
            int row = Integer.parseInt(arrs[0]);
            int co = Integer.parseInt(arrs[1]);
            String[][] target = new String[row][co];
            for (int i = 0; i < row; i++) {
                str = bf.readLine();
                String[] cos = str.split(" ");
                target[i] = cos;
            }
            Map<String, List<Integer>> result = new LinkedHashMap<>();

            dfs(target, 0, 0,result);
            for (Map.Entry<String, List<Integer>> map : result.entrySet()){
                List<Integer> value = map.getValue();
                System.out.println("(" + value.get(0)+","+value.get(1)+")");
            }
        }
    }



    static Boolean check = false;
    public static void dfs(String[][] target, int i, int j,Map<String, List<Integer>> result) {
        List<Integer> t = new LinkedList<>();
        t.add(i);
        t.add(j);
        // 记录当前位置
        result.put(i + "-" + j, t);
        // 判断是否走到终点
        if (i == target.length-1 && j == target[0].length-1) {
            check = true;
            return;
        }
        // 将当前值占用
        target[i][j] = "1";

        // 向下走
        if (i + 1 < target.length && target[i + 1][j].equals("0")) {
            dfs(target, i + 1, j,result);
            if (check)return;
        }
        // 向上走是否能走
        if (i - 1 >= 0 && target[i - 1][j].equals("0") ) {
            dfs(target, i - 1, j,result);
            if (check)return;
        }
        // 向左走
        if (j - 1 >= 0 && target[i][j - 1].equals("0")) {
            dfs(target, i, j - 1,result);
            if (check)return;
        }
        // 向右走
        if (j + 1 < target[0].length && target[i][j + 1].equals("0")) {
            dfs(target, i, j + 1,result);
            if (check)return;
        }
        // 释放占用值
        target[i][j] = "0";
        // 去除本次记录的位置
        result.remove(i + "-" + j);
    }

//    public static void initResult(int i ,int j,){
//        List<Integer> t = new LinkedList<>();
//        t.add(i);
//        t.add(j);
//        // 记录当前位置
//        result.put(i + "-" + j, t);
//    }

}
