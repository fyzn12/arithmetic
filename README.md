# char[]转集合

> 利用String的chars()方法,返回的是一个IntStream对象    
> 调用该对象的mapToObj(c -> (char) C)方法
> 再利用Collect(Collectors.toSet())进行转换

```java  
  
		String str = "2 1 2 4".replaceAll(" ","");
		char[] chs = str.toCharArray();
		Arrays.sort(chs);
		Set<Character> charList = new String(chs).chars()
				.mapToObj(c -> (char) c)
				.collect(Collectors.toSet());
		charList.stream().forEach(System.out::println);    
       

        转list  
   
        List<Character> charList = new String(chs).chars()
				.mapToObj(c -> (char) c)
				.collect(Collectors.toList());
  
```

# 动态规划

## 0-1问题解析  代码实现类名 ---Algorithm01

> 问题描述：有一个背包可以装物品的总重量为W，现有N个物品，每个物品重w[i]，价值v[i]，用背包装物品，能装的最大价值是多少？

> 假如有1个背包，背包容量是10，有5个物品，编号为1，2，3，4，5，他们都有各自的重量和价格。要求在不超过背包容量的情况下，使背包装载物品的价值最大。现将问题拆分为五个子问题。

> 构建一个二维表，横坐标表示背包容量，纵左边代表物品的编号  
> 
> {0,2,2,6,5,4};   //物重w,0无意义，只是为方便描述问题而已    
> {0,6,3,5,4,6};   //物价p  
> 
> 思路：当背包容量为0或者是物品序号为0时，最优解肯定是0,因为物重最低都是2，所以当背包容量为1时，最大价值也是0，无论选择那种无论，背包都不能容下

|      物品/背包容量      | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |   
|------------- |---|---|---|---|---|---|---|---|---|---|---|    
| 		0	   | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 
| 		1	   | 0 | 0 |  |  |  |  |  |  |  |  |  |  
| 		2	   | 0 | 0 |  |  |  |  |  |  |  |  |  |    
| 		3	   | 0 | 0 |  |  |  |  |  |  |  |  |  | 
| 		4	   | 0 | 0 |  |  |  |  |  |  |  |  |  |   
| 		5	   | 0 | 0 |  |  |  |  |  |  |  |  |  |     
  
> 当选择物品为1时，无论背包容量为多少，最大价值都是物品1的价值  
>   
  
|      物重/背包容量      | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |   
|------------- |---|---|---|---|---|---|---|---|---|---|---|    
| 		0	   | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| 		1	   | 0 | 0 | 6 | 6 | 6 | 6 | 6 | 6 | 6 | 6 | 6 |  
| 		2	   | 0 | 0 |  |  |  |  |  |  |  |  |  |    
| 		3	   | 0 | 0 |  |  |  |  |  |  |  |  |  |
| 		4	   | 0 | 0 |  |  |  |  |  |  |  |  |  |   
| 		5	   | 0 | 0 |  |  |  |  |  |  |  |  |  |       
    
> 选择物品2时，需要在同一个背包容量下对比选择上一个物品的价值，选择最大值  
> 即假设当前二维表格为dp[][]  
>   
> 选择物品2时 容量为2时最大价值就是 选择当前物品2的价值与选择物品1的价值做对比，因为物品2的重量也是2，与物品1只能二选其一  
> 
> 只有两个物品可选，只考虑背包容量问题 
> 
> 比如 dp[2][7] 取得是dp[1][7] (不包含本物品时的最大值)与包含本物品，但是包含本物品的前提时背包能容纳本物品的重量就是  当前背包的重量 - 本物品重量  
> 分析得到  
> dp[2][7] = max{ dp[1][7] , dp[1][7 - 2] + 3 }  // 7 -2 7:是当前背包的容量  2:当前物品的重量  3:当前物品的价值
>   
> -> dp[2][7] = max{ dp[1][7] , dp[1][5] + 3 }    
> -> dp[2][7] = max{ 6 , 9 }  
> -> dp[2][7] = 9
>

|      物重/背包容量      | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |   
|------------- |---|---|---|---|---|---|---|---|---|---|---|    
| 		0	   | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| 		1	   | 0 | 0 | 6 | 6 | 6 | 6 | 6 | 6 | 6 | 6 | 6 |  
| 		2	   | 0 | 0 | 6 | 6 | 9 | 9 | 9 | 9 | 9 | 9 | 9 |    
| 		3	   | 0 | 0 | 6 | 6 | 9 | 9 | 9 | 9 | 11 | 11 | 14 |
| 		4	   | 0 | 0 | 6 | 6 | 9 | 9 | 9 | 10 | 11 | 13 | 14 |   
| 		5	   | 0 | 0 | 6 | 6 | 9 | 9 | 12 | 12 | 15 | 15 | 15 |         
  
> 通过上面的分析可以得到
> 
> 该题的动态规划状态转移方程为  dp[i][j] = max{ dp[i-1][j] , dp[i-1][j - Wi] + Vi}
>   
> i 表示第i个物品   
> j 表示当前背包的容量  
> Wj 表示当前第i个物品的重量  
> Vi 表示当前第i个物品的物价  

### 0-1变形---购物单   

> 题目背景：https://www.nowcoder.com/practice/f9c6f980eeec43ef85be20755ddbeaf4?tpId=37&&tqId=21239&rp=1&ru=/ta/huawei&qru=/ta/huawei/question-ranking
  
> 王强今天很开心，公司发给N元的年终奖。王强决定把年终奖用于购物，他把想买的物品分为两类：主件与附件，附件是从属于某个主件的，下表就是一些主件与附件的例子：


| 主件 | 附件 |
|----- |--------|
| 电脑 |	打印机，扫描仪 |
| 书柜 |	图书 |
| 书桌 |	台灯，文具 |
| 工作椅 | 无 |  
  

> 如果要买归类为附件的物品，必须先买该附件所属的主件。每个主件可以有 0 个、 1 个或 2 个附件。附件不再有从属于自己的附件。王强想买的东西很多，为了不超出预算，他把每件物品规定了一个重要度，分为 5 等：用整数 1 ~ 5 表示，第 5 等最重要。他还从因特网上查到了每件物品的价格（都是 10 元的整数倍）。他希望在不超过 N 元（可以等于 N 元）的前提下，使每件物品的价格与重要度的乘积的总和最大。
设第 j 件物品的价格为 v[j] ，重要度为 w[j] ，共选中了 k 件物品，编号依次为 j 1 ， j 2 ，……， j k ，则所求的总和为：
v[j 1 ]*w[j 1 ]+v[j 2 ]*w[j 2 ]+ … +v[j k ]*w[j k ] 。（其中 * 为乘号）
请你帮助王强设计一个满足要求的购物单。  
>   
#### 输入描述：

> 输入的第 1 行，为两个正整数，用一个空格隔开：N m
> （其中 N （ <32000 ）表示总钱数， m （ <60 ）为希望购买物品的个数。）
> 从第 2 行到第 m+1 行，第 j 行给出了编号为 j-1 的物品的基本数据，每行有 3 个非负整数 v p q
> （其中 v 表示该物品的价格（ v<10000 ）， p 表示该物品的重要度（ 1 ~ 5 ）， q 表示该物品是主件还是附件。如果 q=0 ，表示该物品为主件，如果 q>0 ，表示该物品为附件， q 是所属主件的编号）

#### 输出描述：

> 输出文件只有一个正整数，为不超过总钱数的物品的价格与重要度乘积的总和的最大值（ <200000 ）。

#### 示例1  
  
        输入：
            1000 5
            800 2 0
            400 5 1
            300 5 1
            400 3 0
            500 2 0
            复制
        输出：
            2200

#### 题目分析：  
  
> 如果题目中没有附件的话就是典型的背包客0-1原型，那么我们在构建状态转移数组的时候先只考虑主件   
> 那么状态转移方程就是：  
> dp[i][j] = max{ dp[i-1][j] , dp[i-1][j - Vi] + Wi*Vi}   
> 
> i : 第i个主件  
> j ：购买金额  
> Vi: 当前主件的价格   
> Wi*Vi: 当前主键的价值  
>   

> 加上附件后：  
> 要考虑这点的只是考虑有附件的主键     
> 考虑每个物品时要考虑每种可能出现的情:  
> 1、主件   --一个主件最多有两个附件，题目给的条件          
> 2、主件+附件1   
> 3、主件+附件2  
> 4、主件+附件1+附件2      
> 
> 综上考虑状态转移方程转为：dp[i][j] = max(物品不放入背包, 主件, 主件+附件1, 主件+附件2, 主件+附件1+附件2)  
  
#### 代码实现  
   
```java  
    
    import java.util.*;

    /**
     * @author zhang.rongjun
     * @version 1.0
     * @date 2021/12/24 12:39
     * @description
     */
    public class Main {
    
    
        /*
        问题描述：有一个背包可以装物品的总重量为W，现有N个物品，每个物品重w[i]，价值v[i]，用背包装物品，能装的最大价值是多少？
         定义状态转移数组dp[i][j]，表示前i个物品，背包重量为j的情况下能装的最大价值。
        例如，dp[3][4]=6，表示用前3个物品装入重量为4的背包所能获得的最大价值为6，此时并不是3个物品全部装入，而是3个物品满足装入背包的条件下的最大价值。
           状态转移方程：
          dp[i][j] = max(dp[i-1][j], dp[i-1][j-w[i]]+v[i])
          dp[i-1][j]表示当前物品不放入背包，dp[i-1][j-w[i]]+v[i]表示当前物品放入背包，即当前第i个物品要么放入背包，要么不放入背包。
        */
    
        public static void main(String[] arg) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            int m = sc.nextInt();
            Map<Integer, Goods> goods = new HashMap<>();
            int ma = 0;
            while (m-- > 0) {
                ma++;
                int v = sc.nextInt();
                int p = sc.nextInt();
                int q = sc.nextInt();
                if (q > 0) {
                    Goods master = goods.get(q);
                    Goods s = new Goods(0, v, p);
                    if (null == master) {
                        List<Goods> substandardGoods = new LinkedList<>();
                        substandardGoods.add(s);
                        master = new Goods(q, 0, 0);
                        master.setSubstandardGoods(substandardGoods);
                        goods.put(q, master);
                    } else {
                        master.getSubstandardGoods().add(s);
                    }
                } else {
                    Goods s = new Goods(ma, v, p);
                    if (null == goods.get(ma)) {
                        goods.put(s.getId(), s);
                    } else {
                        s.setSubstandardGoods(goods.get(ma).getSubstandardGoods());
                        goods.put(s.getId(), s);
                    }
                }
            }
            int size = n / 10 + 1;
            int[][] dp = new int[goods.size() + 1][size];
            for (int i = 0; i < goods.size() + 1; i++) dp[i][0] = 0;
            for (int i = 0; i < size; i++) dp[0][i] = 0;
            int c = 1;
            for (Map.Entry<Integer, Goods> g : goods.entrySet()) {
                Goods value = g.getValue();
                for (int i = 1; i < size; i++) {
                    if ((i * 10) - value.getPrice() < 0) {
                        dp[c][i] = dp[c - 1][i];
                        continue;
                    }
                    if (value.getSubstandardGoods().isEmpty()) {
                        // 先保证当前物品的价格加上  取得最大值前的 物品的价格 < n
                        dp[c][i] = Math.max(dp[c - 1][i], dp[c - 1][(i * 10 - value.getPrice()) / 10] + value.getT());
                    } else {
                        // 找到 主件, 主件+附件1, 主件+附件2, 主件+附件1+附件2中的最大值
                        // 主件
                        int tmpMax = dp[c - 1][(i * 10 - value.getPrice()) / 10] + value.getT();
                        if (value.getSubstandardGoods().size() > 1) {
                            int tmpPrice = 0;
                            int tmpT = 0;
                            for (Goods t : value.getSubstandardGoods()) {
                                if (value.getPrice() + t.getPrice() <= (i * 10)) {
                                    // 主件加附件
                                    int tmp = dp[c - 1][(i * 10 - value.getPrice() - t.getPrice()) / 10] + value.getT() + t.getT();
                                    // 主件加附件取最大值
                                    tmpMax = Math.max(tmpMax, tmp);
                                }
                                tmpPrice += t.getPrice();
                                tmpT += t.getT();
                            }
                            // 主件+附件1+附件2中的最大值
                            if (tmpPrice != 0 && (value.getPrice() + tmpPrice) <= (i * 10)) {
                                int tmp = dp[c - 1][(i * 10 - value.getPrice() - tmpPrice) / 10] + value.getT() + tmpT;
                                tmpMax = Math.max(tmpMax, tmp);
                            }
                        } else if (value.getPrice() + value.getSubstandardGoods().get(0).getPrice() <= (i * 10)) {
                            int tmp = dp[c - 1][(i * 10 - value.getPrice() - value.getSubstandardGoods().get(0).getPrice()) / 10] + value.getT() + value.getSubstandardGoods().get(0).getT();
                            tmpMax = Math.max(tmpMax, tmp);
                        }
                        dp[c][i] = Math.max(dp[c - 1][i], tmpMax);
                    }
                }
                c++;
            }
            System.out.println(dp[goods.size()][size - 1]);
    
        }
    
        static class Goods {
            int id;
            int price;
            int importance;
            List<Goods> substandardGoods = new LinkedList<>();
            int t;
    
            public Goods(int id, int price, int importance) {
                this.id = id;
                this.price = price;
                this.importance = importance;
                this.t = this.price * this.importance;
            }
    
            public void setSubstandardGoods(List<Goods> substandardGoods) {
                this.substandardGoods = substandardGoods;
            }
    
            public int getId() {
                return id;
            }
    
            public int getPrice() {
                return price;
            }
    
            public List<Goods> getSubstandardGoods() {
                return substandardGoods;
            }
    
            public int getT() {
                return t;
            }
    
        }
    
    }

    
```   
  
***   

## 放苹果问题 --代码实现类 AlgorithmPutApple  
   
> 描述   
把m个同样的苹果放在n个同样的盘子里，允许有的盘子空着不放，问共有多少种不同的分法？（用K表示）5，1，1和1，5，1 是同一种分法。
  
> 数据范围：0 <= m <=10 1 <= n <=10  

> 本题含有多组样例输入。    
输入描述：    
输入两个int整数    
输出描述：    
输出结果，int型    
示例1     
输入：     
7 3    
输出：    
8    
> 
> 构建二维表分析  
> 当苹果数为0时，分法就是0种，而盘子最低都有一个盘子
> 当盘子只有一个时，无论苹果有多少个，都只有一种方法去分  
> 当盘子数为0(这里明确说出盘子数不为0，但是为了方便计算，加上盘子数为0的情况)
> 初始化dp数组为如下所示    
  

| 盘子数/苹果数 | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |  
|----- |---|----|---|---|---|---|---|---|     
| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |  
| 1 | 1 | 1 | 1 | 1 | 1 | 1 | 1 | 1 |    
| 2 | 1 |  |  |  |  |  |  |  |     
| 3 | 1 |  |  |  |  |  |  |  |  
  
> 当盘子数为2，苹果数为1时，会发现其实 0 1和1 0只能算一种方法      
>  当盘子数为3 苹果数还是1是也只有一种方法分 

| 盘子数/苹果数 | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |  
|----- |---|----|---|---|---|---|---|---|       
| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |  
| 1 | 1 | 1 | 1 | 1 | 1 | 1 | 1 | 1 |    
| 2 | 1 | 1 |  |  |  |  |  |  |     
| 3 | 1 | 1 |  |  |  |  |  |  |  


> 当盘子数为2，苹果数为2时，有 0 2 和 1 1两种分法      
>  当盘子数为3 苹果数还是1是也只有一种方法分

| 盘子数/苹果数 | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |  
|----- |---|----|---|---|---|---|---|---|      
| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |  
| 1 | 1 | 1 | 1 | 1 | 1 | 1 | 1 | 1 |    
| 2 | 1 | 1 | 2 | 2 | 3 | 3 | 4 | 4 |     
| 3 | 1 | 1 | 2 | 3 | 4 | 5 | 7 | 8 |    
  
> 当苹果数与盘子数相等时，首先会考虑相对

> dp状态方程推导   
> ① 当苹果数小于盘子数时如苹果数为1，盘子数为2对应于dp[2][1] = 1  
> 明确看出：  
> ==> dp[2][1] = dp[2-1][1] + 0   
> ==> dp[3][2] = dp[3-1][2] + 0;  
> ==> 推出当苹果数小于盘子数时，状态方程为 dp[ i -1 ][j] + 0;
>   
> ② 当苹果数大于或者等于盘子数时如 dp[2][2] = 2  
> ==> dp[2][2] = dp[1][1] + dp[1][2] 或者 dp[1][2] + dp[2][0]   
> ==> dp[3][3] = dp[2][3] + dp[3][0]
> ==> dp[i][j] = dp[i-1][j] + dp[i][ j- i ]  
>
> 
>
```java
   public static void main(String[] args) throws Exception {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            String[] arr = str.split(" ");
            // 苹果数
            int n = Integer.parseInt(arr[0]);
            // 盘子数
            int m = Integer.parseInt(arr[1]);
            m = Math.min(n, m);
            int[][] dp = new int[m + 1][n + 1];
            dp[0][0] = 0;
            for (int i = 1; i <= m; i++) {
                dp[i][0] = 1;
                dp[i][1] = 1;
            }
            for (int i = 0; i <= n; i++) {
                dp[0][i] = 0;
                if (i != 0) dp[1][i] = 1;
            }

            for (int i = 2; i <= m; i++) {
                for (int j = 2; j <= n; j++) {
                    dp[i][j] = dp[i - 1][j] + ((j - i) < 0 ? 0 : dp[i][j - i]);
                }
            }
            
            System.out.println(dp[m][n]);
        }

    }
```


# 数组  
   
## 数组划分  
  
#### 题目描述  
  
> 输入int型数组，询问该数组能否分成两组，使得两组中各元素加起来的和相等，并且，所有5的倍数必须在其中一个组中，所有3的倍数在另一个组中（不包括5的倍数），
不是5的倍数也不是3的倍数能放在任意一组，可以将数组分为空数组，能满足以上条件，输出true；不满足时输出false。    
> https://www.nowcoder.com/practice/9af744a3517440508dbeb297020aca86?tpId=37&rp=1&ru=%2Fta%2Fhuawei&qru=%2Fta%2Fhuawei%2Fquestion-ranking&gioEnter=menu
>   
#### 分析  
  
> ==> 原数组arr 需要转化为arr3 和 arr5  
> ==> 假定原数组的和为sum  arr3 和为 sum3 ，arr5 和为 sum5 ，既不是5又不是3的倍数定义为数组arrO    
> ==> 满足条件时  sum3 = sum5    
> ==> sum = sum3 + sum5    
> ==> sum = 2 * sum3 或 sum = 2 * sum5    
> ==> sum3 = sum5 = sum / 2    
> ==> 转化为 int target = sum/2 - sum3 或者 int target = sum/2 - sum5    
> ==> 当 target为0时满足条件   
> ==> target 的值是arrO中某一个或者几个值的和值  
> ==> 因此该问题转化为 给定一个数组和一个目标数，求该数组中满足某一个或者几个值相加和值为目标数的问题   
> ==> 考虑到使用深度优先遍历搜索算法 DFS    
> ==> DFS 递归实现思路    
>   ① 递归终止条件   
>   ② 遍历目标集合或者数组   
>   ③ 判断当前值是否被占用   
>   ④ 没有被占用的情况下，先将该值占用，递归调用自身 就是一次以某一个节点进行查找过程  
>   ⑤ 没有找到符合条件释放上一次节点占用情况
> 
>   
#### 实现  
  
```java   
   public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            int n = Integer.parseInt(str);
            int sum = 0, sum5 = 0;
            List<Integer> other = new LinkedList<>();
            // 标记是否被占用
            Map<Integer, Boolean> check = new HashMap<>();
            String[] arrs = bf.readLine().split(" ");
            int count = 0;
            for (int i = 0; i < n; i++) {
                Integer num = Integer.parseInt(arrs[i]);
                sum += num;
                if (num % 5 == 0 && num != 0) {
                    sum5 += num;
                } else if (num % 3 != 0) {
                    // other 部分数据
                    other.add(num);
                    check.put(count++, false);
                }
            }
            if ((sum & 1) != 0) {
                System.out.println(false);
            } else {
                int target = sum / 2 - sum5;
                if (target == 0) {
                    System.out.println(true);
                } else {
                    System.out.println(dfs(other, check, target, 0));
                }
            }
        }
    }

    public static Boolean dfs(List<Integer> list, Map<Integer, Boolean> check, int target, int index) {
        // 递归终止条件
        if (target == 0 || index == list.size()) {
            return target == 0 ;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!check.get(i)) {
                // 将该数占用
                check.put(i, true);
                if (dfs(list, check, target - list.get(i), index + 1)) {
                    return true;
                }
                // 走完一次流程，发现没有找到满足条件的值 回溯到下一个节点开始下一轮查找，释放本次占用
                check.put(i, false);
            }
        }
        // 回溯的依据
        return false;
    }
     
```

## 迷宫问题

> 定义一个二维数组 N*M ，如 5 × 5 数组下所示：

> int maze[5][5] = {  
> 0, 1, 0, 0, 0,   
0, 1, 1, 1, 0,    
0, 0, 0, 0, 0,   
0, 1, 1, 1, 0,    
0, 0, 0, 1, 0,    
};   
它表示一个迷宫，其中的1表示墙壁，0表示可以走的路，只能横着走或竖着走，不能斜着走，要求编程序找出从左上角到右下角的路线。入口点为[0,0],既第一格是可以走的路。   
>   
#### 输入描述：  
> 输入两个整数，分别表示二维数组的行数，列数。再输入相应的数组，其中的1表示墙壁，0表示可以走的路。数据保证有唯一解,不考虑有多解的情况，即迷宫只有一条通道。

    
#### 分析  
  
> 这就是一个典型的dfs问题(深度优先搜索)   
> 明确问题只能走上下左右，那在dfs的时候肯定得网上下左右试探

```java   
  
  /**
     * <p>
     * 定义一个二维数组 N*M ，如 5 × 5 数组下所示：
     * int maze[5][5] = {
     * 0, 1, 0, 0, 0,
     * 0, 1, 1, 1, 0,
     * 0, 0, 0, 0, 0,
     * 0, 1, 1, 1, 0,
     * 0, 0, 0, 1, 0,
     * };
     * 它表示一个迷宫，其中的1表示墙壁，0表示可以走的路，只能横着走或竖着走，不能斜着走，
     * 要求编程序找出从左上角到右下角的路线。入口点为[0,0],既第一格是可以走的路。
     * 本题含有多组数据。
     * 数据范围：  ， 输入的内容只包含
     * 输入描述：
     * 输入两个整数，分别表示二维数组的行数，列数。再输入相应的数组，其中的1表示墙壁，0表示可以走的路。
     * 数据保证有唯一解,不考虑有多解的情况，即迷宫只有一条通道。
     * </P>
     *
     * @DateTime: 2021/12/29 14:40
     */
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {
            String[] arrs = str.split(" ");
            int row = Integer.parseInt(arrs[0]);
            int co = Integer.parseInt(arrs[1]);
            String[][] target = new String[row][co];
            for (int i = 0; i < row; i++) {
                str = bf.readLine();
                String[] cos = str.split(" ");
                target[i] = cos;
            }
            Map<String, List<Integer>> result = new LinkedHashMap<>();

            dfs(target, 0, 0,result);
            for (Map.Entry<String, List<Integer>> map : result.entrySet()){
                List<Integer> value = map.getValue();
                System.out.println("(" + value.get(0)+","+value.get(1)+")");
            }
        }
    }
    


    // 标识是否走到终点
    static Boolean check = false;
    public static void dfs(String[][] target, int i, int j,Map<String, List<Integer>> result) {
        List<Integer> t = new LinkedList<>();
        t.add(i);
        t.add(j);
        // 记录当前位置
        result.put(i + "-" + j, t);
        // 判断是否走到终点
        if (i == target.length-1 && j == target[0].length-1) {
            check = true;
            return;
        }
        // 将当前值占用
        target[i][j] = "1";

        // 向下走
        if (i + 1 < target.length && target[i + 1][j].equals("0")) {
            dfs(target, i + 1, j,result);
            if (check)return;
        }
        // 向上走是否能走
        if (i - 1 >= 0 && target[i - 1][j].equals("0") ) {
            dfs(target, i - 1, j,result);
            if (check)return;
        }
        // 向左走
        if (j - 1 >= 0 && target[i][j - 1].equals("0")) {
            dfs(target, i, j - 1,result);
            if (check)return;
        }
        // 向右走
        if (j + 1 < target[0].length && target[i][j + 1].equals("0")) {
            dfs(target, i, j + 1,result);
            if (check)return;
        }
        // 释放占用值
        target[i][j] = "0";
        // 去除本次记录的位置
        result.remove(i + "-" + j);
    }  
       
```

