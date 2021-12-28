package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ZhangRongJun
 * @version 1.0
 * @date 2021/12/27 21:11
 * @description:TODO
 */
public class TestMain {


    public static void main(String[] args) throws Exception{

//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        String str = "";
//        while ((str = bf.readLine()) != null){
//            check( str);
//        }
        check("abc");

    }

    public static void check(String str){
        if (str.length() >8){
            System.out.println(str.substring(0,8));
            check(str.substring(8));
        }else {
            String tmp = str;
            for (int i = 0;i<(8-str.length());i++){
                tmp += "0";
            }
            System.out.println(tmp);
        }
    }




}
