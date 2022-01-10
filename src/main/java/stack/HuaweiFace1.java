package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2022/1/10 11:23
 * @description
 */
public class HuaweiFace1 {
    /**
     *   <p>
     *     输入的字符只包括 ( ) [ ] { }这几种
     *     字符[]代表能封闭  字符{(})不能封闭
     *     若字符能封闭，返回最大的且套层数  如([]{{()}}) 返回4
     *   </P>
     * @param:
     * @author:  zhang.rongjun
     * @DateTime: 2022/1/10 11:23
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            if ((str.length() & 1) == 1) {
                System.out.println(0);
                continue;
            }
            Map<Character,Character> map = new HashMap<Character,Character>(){{
                put(')','(');
                put(']','[');
                put('}','{');
            }};
            int count = 0;
            Deque<Character> stack = new LinkedList<>();
            for (int i = 0;i<str.length();i++){
                char ch = str.charAt(i);
                if (map.containsKey(ch)){
                    // 返回栈顶的值。
                    if (stack.isEmpty() || !stack.peek().equals(map.get(ch))){
                        break;
                    }
                    count = Math.max(stack.size(),count);
                    // pop：相当于get的操作，就是只是查看。
                    // poll：相当于先get然后再remove掉，就是查看的同时，也将这个元素从容器中删除掉。
                    stack.pop();
                }else {
                    stack.push(ch);
                }
            }
            System.out.println(count);
        }

    }


    /**
     *   <p>
     *      原题型
     *      输入的字符只包括 ( ) [ ] { }这几种
     *      字符[]代表能封闭  字符{(})不能封闭
     *      若能封闭则返回true
     *   </P>
     * @param:
     * @author:  zhang.rongjun
     * @DateTime: 2022/1/10 12:42
     * */
    public static boolean isValid(String s) {
        int n = s.length();
        if ((n & 1) == 1) {
            return false;
        }
        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || !stack.peek().equals(pairs.get(ch))) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

}
