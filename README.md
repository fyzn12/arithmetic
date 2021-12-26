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

| 盘子数/苹果数 | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |  
|----- |---|----|---|---|---|---|---|---|     
| 1 | 0 | 1 | 1 | 1 | 1 | 1 | 1 | 1 |    
| 2 | 0 |  |  |  |  |  |  |  |     
| 3 | 0 |  |  |  |  |  |  |  |  
  
> 当盘子数为2，苹果数为1时，会发现其实 0 1和1 0只能算一种方法      
>  当盘子数为3 苹果数还是1是也只有一种方法分 

| 盘子数/苹果数 | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |  
|----- |---|----|---|---|---|---|---|---|     
| 1 | 0 | 1 | 1 | 1 | 1 | 1 | 1 | 1 |    
| 2 | 0 | 1 |  |  |  |  |  |  |     
| 3 | 0 | 1 |  |  |  |  |  |  |  


> 当盘子数为2，苹果数为2时，有 0 2 和 1 1两种分法      
>  当盘子数为3 苹果数还是1是也只有一种方法分

| 盘子数/苹果数 | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |  
|----- |---|----|---|---|---|---|---|---|     
| 1 | 0 | 1 | 1 | 1 | 1 | 1 | 1 | 1 |    
| 2 | 0 | 1 | 2 | 2 | 3 | 3 | 4 | 4 |     
| 3 | 0 | 1 | 2 | 2 |  |  |  |  |    
  
> 当苹果数与盘子数相等时，首先会考虑相对
apple1.png

  

