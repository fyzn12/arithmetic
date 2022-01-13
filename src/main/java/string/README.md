# 字符串相关算法题解  

## 算法一：滑动窗口  
 
### 最大字串  

#### 最长回文字串
> 一般的思路就是中心扩散  
> 1、将字符串的间隙用#替换，避免奇偶回文的情况  
> 2、遍历字符串，采用中心回文记录跳跃数到p[i]中，数组p中的最大值就是字符串的最大回文字串的长度   
> 3、时间复杂度o(n^2) 空间复杂度o(n)
> 
> 
> 对上述时间复杂度优化，达到o(n)  

> 具体做法是：在遍历的过程中，除了循环变量 i 以外，我们还需要记录两个变量，它们是 maxRight 和 center ，它们分别的含义如下：

1. maxRight：记录当前向右扩展的最远边界，即从开始到现在使用“中心扩散法”能得到的回文子串，它能延伸到的最右端的位置 。对于 maxRight 我们说明 3 点：
2. “向右最远”是在计算辅助数组 p 的过程中，向右边扩散能走的索引最大的位置，注意：得到一个 maxRight 所对应的回文子串，并不一定是当前得到的“最长回文子串”，很可能的一种情况是，某个回文子串可能比较短，但是它正好在整个字符串比较靠后的位置；
3. maxRight 的下一个位置可能是被程序看到的，停止的原因有 2 点：（1）左边界不能扩散，导致右边界受限制也不能扩散，maxRight 的下一个位置看不到；（2）正是因为看到了 maxRight 的下一个位置，导致 maxRight 不能继续扩散。
4. 为什么 maxRight 很重要？因为扫描是从左向右进行的， maxRight 能够提供的信息最多，它是一个重要的分类讨论的标准，因此我们需要一个变量记录它。
5. center：center 是与 maxRight 相关的一个变量，它是上述 maxRight 的回文中心的索引值。对于 center 的说明如下：
  
> center和maxRight 的形式化定义：  center = i ; maxRight = i + p[i]
     

##### 实现  

> 回文准备：
*          将字符串的空格以#填充
*          ①：初始话maxRight和center为0
*          ②：记录maxLen 记录回文过程中的最大回文字串，初始值为1
*          ③：如何要求输出回文字串需要定义一个start为0
> 过程分析：
*          ①：当 i < maxRight时 此时的i必然在center的右侧
               需要求出以center 为中心，长度为i-center的左坐标
               ==> center - (i - center) = mirror
               ==> mirror = 2center - i
               此时的p[mirror] 就是之前已经回文过填充的值
               此时要考虑p[i]的值就是从 i 到maxRight之间的跳跃数和p[mirror]之间的最小值
               ==> p[i] = Math.min(maxRight-i,p[mirror])
*           ②：定义下一次尝试扩散的起点：
               int left = i - (1 + p[i]);
               int right = i + (1 + p[i]);
*           ③：进行回文扩散：
               while(left >= 0 && right < 字符串长度 && string.charAt(left) == string.charAt(right)){
                   p[i]++;    
                   left--;     
                   right++;        
                }
*            ④：更新maxRight和center
               当 i + p[i] > maxRight 时
                  maxRight = i + p[i]
                  center = i;
*             ⑤：判断最长回文字串
                当p[i](就是在i节点的时候可以跳跃的步数即回文数) > maxLen时
                   maxLen = p[i];
                   start = ( i - maxLen) / 2;最大长度的起始坐标  
  
```java
public static String longestPalindrome(String s) {
        // 特判
        int len = s.length();
        if (len < 2) {
            return s;
        }
        // 得到预处理字符串
        String str = s.replaceAll("", "#");
        // 新字符串的长度
        int sLen = 2 * len + 1;
        // 数组 p 记录了扫描过的回文子串的信息
        int[] p = new int[sLen];
        // 双指针，它们是一一对应的，须同时更新
        int maxRight = 0;
        int center = 0;
        // 当前遍历的中心最大扩散步数，其值等于原始字符串的最长回文子串的长度
        int maxLen = 1;
        // 原始字符串的最长回文子串的起始位置，与 maxLen 必须同时更新
        int start = 0;
        for (int i = 0; i < sLen; i++) {
            if (i < maxRight) {
                int mirror = 2 * center - i;
                // 这一行代码是 Manacher 算法的关键所在，要结合图形来理解
                p[i] = Math.min(maxRight - i, p[mirror]);
            }
            // 下一次尝试扩散的左右起点，能扩散的步数直接加到 p[i] 中
            int left = i - (1 + p[i]);
            int right = i + (1 + p[i]);
            // left >= 0 && right < sLen 保证不越界
            // str.charAt(left) == str.charAt(right) 表示可以扩散 1 次
            while (left >= 0 && right < sLen && str.charAt(left) == str.charAt(right)) {
                p[i]++;
                left--;
                right++;
            }
            // 根据 maxRight 的定义，它是遍历过的 i 的 i + p[i] 的最大者
            // 如果 maxRight 的值越大，进入上面 i < maxRight 的判断的可能性就越大，这样就可以重复利用之前判断过的回文信息了
            if (i + p[i] > maxRight) {
                // maxRight 和 center 需要同时更新
                maxRight = i + p[i];
                center = i;
            }
            if (p[i] > maxLen) {
                // 记录最长回文子串的长度和相应它在原始字符串中的起点
                maxLen = p[i];
                start = (i - maxLen) / 2;
            }
        }
        System.out.println(maxLen);
        return s.substring(start, start + maxLen);
}
```  
  

#### 最大字串变形1 