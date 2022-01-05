package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2022/1/5 16:57
 * @description
 */
public class Train {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            String[] arr = bf.readLine().split(" ");
            Deque<String> stack = new LinkedList<>();
            Deque<String> stack2 = new LinkedList<>();
            List<String> list = new LinkedList<>();
            for(int i=arr.length-1;i>=0;i--) stack.push(arr[i]);
            dfs("",stack,stack2,list);
            Collections.sort(list);
            for(String s:list)
                System.out.println(s);
        }
    }

    public static void dfs(String str,Deque<String> stack,Deque<String> stack2,List<String> list){
        if (stack.isEmpty() && stack2.isEmpty()){
            list.add(str.trim());
            return;
        }
        if (!stack2.isEmpty()){
            String s = stack2.poll();
            dfs(str+" " + s,stack,stack2,list);
            stack2.push(s);
        }
        if (!stack.isEmpty()){
            String s = stack.poll();
            stack2.push(s);
            dfs(str,stack,stack2,list);
            stack2.poll();
            stack.push(s);
        }
    }
}
