package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ZhangRongJun
 * @version 1.0
 * @date 2021/12/27 21:11
 * @description:TODO
 */
public class TestMain {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        while ((str = br.readLine()) != null) {
            char[] chs = str.toCharArray();
            int max = 0;
            for (int i = 1; i < chs.length; i++) {
                int maxT = 1;
                int l = i-1 , r = i +1;
                while (i >=0 && r<chs.length){
                    if (chs[l] < chs[i] ){

                    }
                }
            }
        }


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




