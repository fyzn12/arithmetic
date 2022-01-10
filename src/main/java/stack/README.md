# 栈的基本了解  
## 栈的定义及不推荐使用Stack创建栈的原因解析   

```java  
// jdk1.8以后推荐使用Deque定义栈
Deque<Character> stack = new LinkedList<Character>();   
// 不推荐使用下面的方式定义栈  
Stack stack = new Stack();  
// Stack继承Vector    
public class Stack<E> extends Vector<E> {}   
// 而Vector是线程安全的，但是其实现方式采用的是Synchronized作为对象锁，源码如下  

   /**
     * Returns the element at the specified position in this Vector.
     *
     * @param index index of the element to return
     * @return object at the specified index
     * @throws ArrayIndexOutOfBoundsException if the index is out of range
     *            ({@code index < 0 || index >= size()})
     * @since 1.2
     */
    public synchronized E get(int index) {
        if (index >= elementCount)
            throw new ArrayIndexOutOfBoundsException(index);

        return elementData(index);
    }

    /**
     * Replaces the element at the specified position in this Vector with the
     * specified element.
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws ArrayIndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index >= size()})
     * @since 1.2
     */
    public synchronized E set(int index, E element) {
        if (index >= elementCount)
            throw new ArrayIndexOutOfBoundsException(index);

        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }   
// 因此在使用栈的时候不推荐直接使用Stack创建栈
```  
## 栈的常见方法解析
> poll：相当于先get然后再remove掉，就是查看的同时，也将这个元素从容器中删除掉。       
> peek()： 返回栈顶的值。    
> pop()： 返回栈顶的值，并栈顶的值删除。   
> push(): 入栈  
>   
## 题型1 判断字符串是否封闭   
  
> 原始题型描述：  
> 输入的字符只包括 ( ) [ ] { }这几种，字符[]代表能封闭  字符{(})不能封闭，若能封闭则返回true   
  
> 题目解答  
>   
```java
 public Boolean isVaild(String str){
    // 如果给定的字符串的长度是奇数，那么肯定是不能封闭的直接返回false
    if((str.length() & 1) == 1)return false;
    // 将输入的字符定制成一个hash表
    Map<Character,Character> map = new HashMap<>();
    map.put(')','(');
    map.put(']','[');
    map.put('}','{');
    // 定义一个栈用于存放和判断封闭情况
    Deque<Character> stack = new LinkedList<>();
    for(int i = 0 ;i< str.length();i++){
        char ch = str.charAt(i);
        if(map.containsKey(ch)){
            if(stack.isEmpty() || !stack.peek().equals(map.get(ch))){
                return false;
            }
            stack.pop();
        }else{
            stack.push(ch);
        }
    }
    return stack.isEmpty();

}
```  
  
### 原始题型1变形--面试题  
#### 题目描述
> 输入的字符只包括 ( ) [ ] { }这几种    
> 字符[]代表能封闭  字符{(})不能封闭     
> 若字符能封闭，返回最大的且套层数  如([]{{()}}) 返回4    
  
#### 题目解析  
> 该题型的思路和解题方式和原始题型一致，都需要一个hash表作为参考，栈最为是否封闭的判断  
  
#### 代码实现  
  
```java
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

```  
