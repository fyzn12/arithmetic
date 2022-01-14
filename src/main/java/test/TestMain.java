package test;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

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

                   String str = "1->2->3";
                   String[] arr = str.split("->");
                   List<Integer> list = Arrays.stream(arr).map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(list);
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




