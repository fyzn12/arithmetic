import java.io.*;
import java.util.*;
/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2022/1/6 22:18
 * @description
 */
public class HuaWei2 {
    public static void main(String[] args) throws Exception {
//        Map<Character, Character> map2 = new HashMap<>();
//        map2.put(')', '(');
//        map2.put(']', '[');
//        map2.put('}', '{');
//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//        String str;
//        while ((str = bf.readLine()) != null) {
//            int max = 0;
//            char[] chars = str.toCharArray();
//            Deque<Character> stack = new LinkedList<>();
//            stack.push(chars[0]);
//            for (int i = 1;i<chars.length;i++){
//                if (stack.contains(map2.get(chars[i]))){
//                    Deque<Character> tmp = new LinkedList<>();
//                    while (!stack.isEmpty()){
//                        char t = stack.poll();
//                        if (map2.get(chars[i]) != t){
//                            tmp.push(t);
//                        }else {
//                            break;
//                        }
//                    }
//                    max = Math.max(max,stack.size()+1);
//                    while (!tmp.isEmpty()){
//                        stack.addAll(tmp);
//                    }
//                }else {
//                    stack.push(chars[i]);
//                }
//            }
//            System.out.println(stack.isEmpty()?max:0);
//        }
//
        System.out.println(isValid("({}[][{()}])")?count:0);

    }

    public static int count = 0;
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
                count = Math.max(stack.size(),count);
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
}
