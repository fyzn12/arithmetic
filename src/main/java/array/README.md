# 数组相关算法问题详解

## 1.原始题型(1) ---给定一个递增数组和一个目标数，求出该数组中第一个大于目标数的值或者下标，要求时间复杂度o(lg(N))

### 分析

> 该题中如果没有要求时间复杂度，可以通过遍历数组，找到答案，时间复杂度o(N)
> 考虑到时间复杂度o(lg(N))，可以使用二分法
>

```java  
  
   public int lis(int[] arr,int target){
      int l = 0, r = arr.length;
      while (l <= r){
         // 采用二分法
         int mid = l + ((r -l)>>1);
         if(arr[mid] > target){
            r = mid;
         }else{
            l = mid +1;
         }
         // 设置终止条件
         if(l == r && arr[l] > target){
             // 如果是求第一个数直接输出arr[l]，如果是求下标直接输出 l
             System.out.println(arr[l]);
             // 必须终止while
             break;
         }
      }
   }
    
```  

### 由题型的衍生出的题型

#### 1.给定一个数组(无序),求最大递增子序列 class-TargetSearch2

> 输出最长递增子序列的长度，如输入 4 2 3 1 5 6，输出 4 （因为 2 3 5 6组成了最长递增子序
>

##### 分析

> 方法一，可以采用暴力法
>

```java

 // 暴力破解法
     private static int f(int[]arr){
        int maxCnt=0;
        for(int i=0;i<arr.length;i++){
            int p=i;
            int cnt=1;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]>arr[p]){
                cnt++;
                p=j;
            }
            maxCnt=Math.max(maxCnt,cnt);
        }
        return maxCnt;
     }

```  
  
> 方法二：采用动态规划，构建一个dp数组，将满足递增的值存入到dp数组中，这样dp数组就是一个递增的数组  
> 当目标数组arr中的元素不满足递增时，要考虑替换dp数组中第一个大于此元素的值   
  
> 该问题就会转化成给定一个递增数组和一个目标数，求第一个大于目标数的问题  
  
  
```java
 
public int LIS(int[] arr){
    // 构建一个dp数组
    int[] dp = new int[arr.length];
    // 初始化dp第一个数  
    dp[1] = arr[0];
    // 以一个指针p标识该数组arr中的最大递增子序列的长度  
    int p = 1;
    for (int i = 1;i<arr.length;i++){
        if(arr[i] > dp[p]){
            // 满足递增条件
            dp[p+1] = arr[i];
            p++;
        }else{
            // 替换dp数组中第一个大于arr[i]的值，也就是转化为上面的题型1问题
            // 之所以right是p，是因为当前dp数组中只初始化了1-p的位置
            int l = 0 ,r = p;
            while(l <= r){
                int mid = l + ((r -l)>>1);
                if(dp[mid]>arr[i]){
                    r = mid;
                }else{
                    l = mid + 1;
                }   
                // 设置while的终止条件
                if( l == r && dp[l] > arr[i]){
                    dp[l] = arr[i];
                    break;
                }       
            }    
        }
    }
    return p;

}

```  

#### 2.合唱队 class-HeChangDui

> 描述
计算最少出列多少位同学，使得剩下的同学排成合唱队形

> 说明：

> N 位同学站成一排，音乐老师要请其中的 (N - K) 位同学出列，使得剩下的 K 位同学排成合唱队形。
合唱队形是指这样的一种队形：设K位同学从左到右依次编号为 1，2…，K ，他们的身高分别为 T1，T2，…，TK ，   则他们的身高满足存在 i （1<=i<=K） 使得 T1<T2<......<Ti-1<Ti>Ti+1>......>TK 。

> 你的任务是，已知所有N位同学的身高，计算最少需要几位同学出列，可以使得剩下的同学排成合唱队形。

> 注意：不允许改变队列元素的先后顺序 且 不要求最高同学左右人数必须相等
请注意处理多组输入输出！


> 输入描述：
有多组用例，每组都包含两行数据，第一行是同学的总数 N ，第二行是 N 位同学的身高，以空格隔开

> 输出描述：
最少需要几位同学出列  
>
> 示例1     
输入：
8
186 186 150 200 160 130 197 200    
> 输出：    
4    
> 说明：     
由于不允许改变队列元素的先后顺序，所以最终剩下的队列应该为186 200 160 130或150 200 160 130    
>   
  
##### 分析  
  
> 该问题又是可以传化成求最大递增子序列问题  
  
```java  
  public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        while ((str = br.readLine()) != null) {
            int n = Integer.parseInt(str);
            String[] arr = br.readLine().split(" ");
            if (n <= 1) {
                System.out.println(0);
                continue;
            }
            int[] dpL = new int[n];
            dpL[0] = Integer.parseInt(arr[0]);
            int p = 1;
            int[] dpR = new int[n];
            dpR[0] = Integer.parseInt(arr[n-1]);
            int[] dp = new int[n];
            for(int i = 1;i<n;i++){
                int tmp = Integer.parseInt(arr[i]);
                if(tmp > dpL[p-1]){
                    dp[i] = p;
                    dpL[p++] = tmp;
                }else{
                    // 不满足递增时，找到该值在递增中的位置，也就是说找到dpL中第一个大于tmp的位置
                    int l = 0,r = p-1;
                    while( l < r){
                        int mid = l + ((r - l)>>1);
                        if(dpL[mid] < tmp)l = mid + 1;
                        else r = mid;
                    }
                    // 替换dpL中的值，保证dpL递增
                    dpL[l] = tmp;
                    // i位置时左边由l递增子序列
                    dp[i] = l;
                }
            }
            // 重新初始化p
            p = 1;
            for(int i = n -2;i>=0;i--){
                int tmp = Integer.parseInt(arr[i]);
                if(tmp > dpR[p-1]){
                    dp[i] += p;
                    dpR[p++] = tmp;
                }else{
                    int l = 0,r = p-1;
                    while(l < r){
                        int mid = l + ((r -l)>>1);
                        if(dpR[mid] < tmp)l = mid +1;
                        else r = mid;
                    }
                    dpR[l] = tmp;
                    dp[i] += l;
                }
            }
            int max = 1;
            for( int t : dp)max = Math.max(max,t);
            System.out.println(n - max -1);
        }
    }
```  
  
