import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2022/1/6 20:33
 * @description
 */
public class HuaWei {
    /**
     *   <p>
     *      为同学找班级
     *      每一个同学都知道自己是否与前面一个同学同班级，找到同学的班级并排序输出
     *      如 1/N 2/Y 3/N 4/Y
     *      输出 1 2
     *          3 4
     *   </P>
     * @param:
     * @author:  zhang.rongjun
     * @DateTime: 2022/1/10 9:26
     * */
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            String[] arr = str.split(" ");
            Map<String, TreeSet<String>> mapT = new TreeMap<>();
            for (int i = 0; i < arr.length; i++) {
                String tmp = arr[i];
                String[] arrT = tmp.split("/");
                int n = Integer.parseInt(arrT[0]);
                if (arrT.length>2 || n > 999 || n <= 0 || !(arrT[1].equals("N") || arrT[1].equals("Y")) ){
                    System.out.println("ERROR");
                    break;
                }
                if (arrT[1].equals("Y") && i != 0) {
                    String pre = arr[i - 1];
                    String[] c = pre.split("/");
                    if (mapT.containsKey(c[0])){
                        TreeSet<String> set = mapT.get(c[0]);
                        set.add(arrT[0]);
                        mapT.remove(c[0]);
                        mapT.put(arrT[0],set);
                    }else {
                        TreeSet<String> set = new TreeSet<>();
                        set.add(arrT[0]);
                        set.add(c[0]);
                        mapT.put(arrT[0],set);
                    }
                }
            }
            for (Map.Entry<String, TreeSet<String>> m : mapT.entrySet()) {
                TreeSet<String> value = m.getValue();
                StringBuilder builder = new StringBuilder();
                for (String c:value){
                    builder.append(c).append(" ");
                }
                System.out.println(builder);
            }
            if(mapT.size()<=1){
                System.out.println();
            }
        }
    }
}
