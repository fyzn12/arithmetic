package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author ZhangRongJun
 * @version 1.0
 * @date 2021/12/27 21:11
 * @description:TODO
 */
public class TestMain {


    /**
     * <p>
     * 输入一个单向链表和一个节点的值，从单向链表中删除等于该值的节点，删除后如果链表中无节点则返回空指针。
     * <p>
     * 链表的值不能重复。
     * <p>
     * 构造过程，例如输入一行数据为:
     * 6 2 1 2 3 2 5 1 4 5 7 2 2
     * 则第一个参数6表示输入总共6个节点，第二个参数2表示头节点值为2，剩下的2个一组表示第2个节点值后面插入第1个节点值，为以下表示:
     * 1 2 表示为
     * 2->1
     * 链表为2->1
     * <p>
     * 3 2表示为
     * 2->3
     * 链表为2->3->1
     * <p>
     * 5 1表示为
     * 1->5
     * 链表为2->3->1->5
     * <p>
     * 4 5表示为
     * 5->4
     * 链表为2->3->1->5->4
     * <p>
     * 7 2表示为
     * 2->7
     * 链表为2->7->3->1->5->4
     * <p>
     * 最后的链表的顺序为 2 7 3 1 5 4
     * <p>
     * 最后一个参数为2，表示要删掉节点为2的值
     * 删除 结点 2
     * <p>
     * 则结果为 7 3 1 5 4
     * </P>
     *
     * @param:
     * @author: zhang.rongjun
     * @DateTime: 2022/1/6 11:00
     */
    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String str;
//        while ((str = br.readLine()) != null) {
            String str = "5 2 3 2 4 3 5 2 1 4 3";
            String[] arr = str.split(" ");
            int n = Integer.parseInt(arr[0]);
            int head = Integer.parseInt(arr[1]);
            Map<Integer, Integer> map = new LinkedHashMap<>();
            map.put(head, null);
            int delete = Integer.parseInt(arr[arr.length - 1]);
            // 6 2 1 2 3 2 5 1 4 5 7 2 2
            for (int i = 2; i < arr.length - 1; i += 2) {
                int v1 = Integer.parseInt(arr[i]);
                int v2 = Integer.parseInt(arr[i + 1]);
                if (map.containsKey(v2)) {
                    if (map.get(v2) != null) {
                        int next = map.get(v2);
                        map.put(v1, next);
                    }
                }
                map.put(v2, v1);
            }

            int key = head;
            StringBuilder builder = new StringBuilder();
            while (!map.isEmpty()) {
                if (delete != key) {
                    builder.append(key).append(" ");
                }
                if (map.containsKey(key)) {
                    int tmp = key;
                    key = map.get(key);
                    map.remove(tmp);
                    // 避免最后一个不被丢弃
                    if (map.isEmpty() && delete != key) {
                        builder.append(key).append(" ");
                    }
                }
            }
            System.out.println(builder.toString().trim());
//        }

//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        String str;
//        while ((str = bf.readLine()) != null) {
//            int j = 0;
//            char[] charArray = str.toCharArray();
//            Deque<String> stack = new LinkedList<>();
//            int max = 0;
//            for (int i = 0;i<str.length();i++){
//                char ch = charArray[i];
//                if (( ch >= '0' && ch <= '9')) {
//                    if ((i +1) != str.length()){
//                        continue;
//                    }
//                    i = i+1;
//                }
//                if (i == j){
//                    j++;
//                    continue;
//                }
//                if ((i - j) > max){
//                    max = i -j;
//                    if (!stack.isEmpty()){
//                        stack.poll();
//                    }
//                    stack.add(str.substring(j,i));
//                }else if ((i - j) == max){
//                    max = i -j;
//                    String poll = stack.poll();
//                    stack.add(poll+str.substring(j,i));
//                }
//                j = i+1;
//            }
//
//            int finalMax = max;
//            stack.forEach(s -> {
//                System.out.println(s +"," + finalMax);
//            });
//
//        }

    }





}




