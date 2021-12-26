package dynamic_planning;

import java.util.*;

/**
 * @author zhang.rongjun
 * @version 1.0
 * @date 2021/12/24 12:39
 * @description
 */
public class Algorithm01 {


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

        @Override
        public String toString() {
            return "Goods{" +
                    "id=" + id +
                    ", price=" + price +
                    ", importance=" + importance +
                    ", substandardGoods=" + substandardGoods +
                    ", t=" + t +
                    '}';
        }
    }


}

