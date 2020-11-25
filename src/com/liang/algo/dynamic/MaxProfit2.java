package com.liang.algo.dynamic;

/**
 * 309. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 *
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * 通过次数58,455提交次数102,255
 */
public class MaxProfit2 {

    public static void main(String[] args) {
//        int[] prices = {1,2,3,6,7,3,22,5,0,2};
//        System.out.println(my1(prices));

//        int[] p1 = {7,1,5,3,6,4};
//        System.out.println(my2(p1));

//        int[] p3 = {7,1,5,3,6,4};
//        System.out.println(my3(p3));

//        int[] p4 = {3,3,5,0,0,3,1,4};
//        System.out.println(my4(p4));

//        int[] p5 = {3,2,6,5,0,3};
//        System.out.println(my5o(2, p5));

        int[] p7 = {1, 3, 2, 8, 4, 9};
        System.out.println(my7(p7, 2));
    }

    // 714. 买卖股票的最佳时机含手续费
    // 不限次数
    public static int my7(int[] prices, int fee) {
        // 状态: dps[i][k][j], 第i天,剩余最多交易k次,操作后持有股票j==1或不持有股票j==0,利润
        // dps[i][k][0] = max(dps[i-1][k][0], dps[i-1][k][1] + prices[i])
        // dps[i][k][1] = max(dps[i-1][k][1], dps[i-1][k+1][0] - prices[i])
        // dps[n-1][k][0], k >= 0

        // 本题，交易次数不限，变形为：去掉k
        // 不持有：dps[i][0] = max(dps[i-1][0], dps[i-1][1] + prices[i])
        // 持有：dps[i][1] = max(dps[i-1][1], dps[i-1][0] - prices[i] - fee)
        // 时空复杂度分别为：O(n)/O(n)

        if (prices == null || prices.length < 2) {
            return 0;
        }
        int n = prices.length;
        int[][] dps = new int[n][2];
        dps[0][0] = 0;
        dps[0][1] = -prices[0] - fee;
        for (int i = 1; i < n; i ++) {
            dps[i][0] = Math.max(dps[i-1][0], dps[i-1][1] + prices[i]);
            dps[i][1] = Math.max(dps[i-1][1], dps[i-1][0] - prices[i] - fee);
        }
        return dps[n-1][0];
    }

    public static int maxProfit(int[] prices) {
        // 状态转移：0~i天内完成交易的最大利润 = max(0~i-1天内最大利润，第i天卖出的最大利润)
        // 方程：dps[i] = max(dps[i-1], max(dps[j-2] + prices[i] - prices[j], j = 0..i-1))
        // 时间复杂度O(n^2), 空间复杂度O(n)

        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int n = prices.length;
        int[] dps = new int[n];
        for (int i = 1; i < n; i ++ ) {
            // 第i天卖出的最大利润
            int imp = 0;
            for (int j = 0; j < i; j ++) {
                imp = Math.max(imp, (j-2 >= 1 ? dps[j-2] : 0) + prices[i] - prices[j]);
            }
            // 转移
            dps[i] = Math.max(dps[i-1], imp);
        }

        return dps[n-1];
    }

    // 309. 最佳买卖股票时机含冷冻期
    public static int my1(int[] prices) {
        // 状态: dps[i][k][j], 第i天,剩余最多交易k次,操作后持有股票j==1或不持有股票j==0,利润
        // dps[i][k][0] = max(dps[i-1][k][0], dps[i-1][k][1] + prices[i])
        // dps[i][k][1] = max(dps[i-1][k][1], dps[i-1][k+1][0] - prices[i])
        // max(dps[n-1][k][0], k >= 0)

        // 本题，交易次数不限，且有冷冻期1天，变形为：去掉k，增加一个状态不持有且处于冷冻期
        // 不持有非冷冻：dps[i][0] = max(dps[i-1][0], dps[i-1][1])
        // 不持有且冷冻：dps[i][1] = dps[i-1][2] + prices[i],  表示第i+1天不能买入股票
        // 持有：dps[i][2] = max(dps[i-1][2], dps[i-1][0] - prices[i])
        // 时空复杂度分别为：O(n)/O(n)

        if (prices == null || prices.length < 2) {
            return 0;
        }
        int n = prices.length;
        int[][] dps = new int[n][3];
        dps[0][0] = 0;
        dps[0][1] = 0;
        dps[0][2] = - prices[0];
        for (int i = 1; i < n; i ++) {
            dps[i][0] = Math.max(dps[i-1][0], dps[i-1][1]);
            dps[i][1] = dps[i-1][2] + prices[i];
            dps[i][2] = Math.max(dps[i-1][2], dps[i-1][0] - prices[i]);
        }
        return Math.max(dps[n-1][0], dps[n-1][1]);
    }

    // 121. 买卖股票的最佳时机
    // 只交易一次
    public static int my2(int[] prices) {
        // 状态dps[i][j][k], i天, 最多允许的最大交易数j, 持有或不持有k, 值表示最大利润
        // j = 1, k = 0或1, 0不持有 1持有
        // 转移方程：
        // dps[i][1][0] = max(dps[i-1][1][0], dps[i-1][1][1] + prices[i])
        // dps[i][1][1] = max(dps[i-1][1][1], dps[i-1][0][0] - prices[i])
        //              = max(dps[i-1][1][1], 0 - prices[i])
        // 变形为：
        // dps[i][0] = max(dps[i-1][0], dps[i-1][1] + prices[i])
        // dps[i][1] = max(dps[i-1][1], - prices[i])

        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int n = prices.length;
        int[][] dps = new int[n][2];
        dps[0][0] = 0;
        dps[0][1] = -prices[0];
        for (int i = 1; i < n; i ++) {
            dps[i][0] = Math.max(dps[i-1][0], dps[i-1][1] + prices[i]);
            dps[i][1] = Math.max(dps[i-1][1], - prices[i]);
        }
        return dps[n-1][0];
    }

    // 122. 买卖股票的最佳时机 II
    // 可以多次交易
    public static int my3(int[] prices) {
        // 状态dps[i][j][k], i天, 最多允许的最大交易数j, 持有或不持有k, 值表示最大利润
        // j不限制, 可删除
        // k = 0或1, 0不持有 1持有
        // 转移方程：
        // dps[i][0] = max(dps[i-1][0], dps[i-1][1] + prices[i])
        // dps[i][1] = max(dps[i-1][1], dps[i-1][0] - prices[i])

        if(prices == null || prices.length <= 1) {
            return 0;
        }
        int n = prices.length;
        int[][] dps = new int[n][2];
        dps[0][0] = 0;
        dps[0][1] = -prices[0];
        for (int i = 1; i < n; i ++) {
            dps[i][0] = Math.max(dps[i-1][0], dps[i-1][1] + prices[i]);
            dps[i][1] = Math.max(dps[i-1][1], dps[i-1][0] - prices[i]);
        }

        return dps[n-1][0];
    }

    // 123. 买卖股票的最佳时机 III
    // 最多可进行2次交易
    public static int my4(int[] prices) {
        // 状态dps[i][j][k], i天, 最多允许的交易数j, 持有或不持有k, 值表示最大利润
        // j=1..2
        // k=0..1, 0不持有 1持有
        // 转移方程：
        // dps[i][2][0] = max(dps[i-1][2][0], dps[i-1][2][1] + prices[i])
        // dps[i][2][1] = max(dps[i-1][2][1], dps[i-1][1][0] - prices[i])
        // dps[i][1][0] = max(dps[i-1][1][0], dps[i-1][1][1] + prices[i])
        // dps[i][1][1] = max(dps[i-1][1][1], - prices[i])

        if(prices == null || prices.length <= 1) {
            return 0;
        }
        int n = prices.length;
        int[][][] dps = new int[n][3][2];
        dps[0][2][0] = 0;
        dps[0][2][1] = -prices[0];
        dps[0][1][0] = 0;
        dps[0][1][1] = -prices[0];
        for (int i = 1; i < n; i ++) {
             dps[i][2][0] = Math.max(dps[i-1][2][0], dps[i-1][2][1] + prices[i]);
             dps[i][2][1] = Math.max(dps[i-1][2][1], dps[i-1][1][0] - prices[i]);
             dps[i][1][0] = Math.max(dps[i-1][1][0], dps[i-1][1][1] + prices[i]);
             dps[i][1][1] = Math.max(dps[i-1][1][1], - prices[i]);
        }

        return dps[n-1][2][0];
    }

    // 123. 买卖股票的最佳时机 III
    // 最多可进行2次交易 空间复杂度优化
    public static int my4o(int[] prices) {
        // 状态dps[i][j][k], i天, 最多允许的交易数j, 持有或不持有k, 值表示最大利润
        // j=1..2
        // k=0..1, 0不持有 1持有
        // 转移方程：
        // dps[i][2][0] = max(dps[i-1][2][0], dps[i-1][2][1] + prices[i])
        // dps[i][2][1] = max(dps[i-1][2][1], dps[i-1][1][0] - prices[i])
        // dps[i][1][0] = max(dps[i-1][1][0], dps[i-1][1][1] + prices[i])
        // dps[i][1][1] = max(dps[i-1][1][1], - prices[i])

        // 空间优化O(1)

        if(prices == null || prices.length <= 1) {
            return 0;
        }
        int n = prices.length;
        int d20 = 0;
        int d21 = -prices[0];
        int d10 = 0;
        int d11 = -prices[0];
        for (int i = 1; i < n; i ++) {
             d20 = Math.max(d20, d21 + prices[i]);
             d21 = Math.max(d21, d10 - prices[i]);
             d10 = Math.max(d10, d11 + prices[i]);
             d11 = Math.max(d11, -prices[i]);
        }

        return d20;
    }

    // 188. 买卖股票的最佳时机 IV
    // 最多可进行K次交易
    public static int my5(int k, int[] prices) {
        // 状态dps[i][j][k], i天, 最多允许的交易数j, 持有或不持有k, 值表示最大利润
        // j=1..k
        // k=0..1, 0不持有 1持有
        // 转移方程：
        // dps[i][j][0] = max(dps[i-1][j][0], dps[i-1][j][1] + prices[i])
        // dps[i][j][1] = max(dps[i-1][j][1], dps[i-1][j-1][0] - prices[i])

        if(prices == null || prices.length <= 1 || k <= 0) {
            return 0;
        }
        int n = prices.length;
        int[][][] dps = new int[n][k+1][2];

        // 第0天
        for (int j = 1; j <= k; j ++) {
            dps[0][j][0] = 0;
            dps[0][j][1] = -prices[0];
        }
        for (int i = 0; i < n; i ++) {
            dps[i][0][0] = 0;
            dps[i][0][1] = 0;
        }

        for (int i = 1; i < n; i ++) {
            for (int j = 1; j <= k; j ++) {
                 dps[i][j][0] = Math.max(dps[i-1][j][0], dps[i-1][j][1] + prices[i]);
                 dps[i][j][1] = Math.max(dps[i-1][j][1], dps[i-1][j-1][0] - prices[i]);
            }
        }

        return dps[n-1][k][0];
    }

    // 188. 买卖股票的最佳时机 IV
    // 最多可进行K次交易 空间优化
    public static int my5o(int k, int[] prices) {
        // 状态dps[i][j][k], i天, 最多允许的交易数j, 持有或不持有k, 值表示最大利润
        // j=1..k
        // k=0..1, 0不持有 1持有
        // 转移方程：
        // dps[i][j][0] = max(dps[i-1][j][0], dps[i-1][j][1] + prices[i])
        // dps[i][j][1] = max(dps[i-1][j][1], dps[i-1][j-1][0] - prices[i])
        // 优化：k>n/2时变为不限次数交易问题

        if(prices == null || prices.length <= 1 || k <= 0) {
            return 0;
        }
        int n = prices.length;
        if (k > n/2) {
            // 可以当成不限次数交易
            int[][] dps = new int[n][2];
            dps[0][0] = 0;
            dps[0][1] = -prices[0];
            for (int i = 1; i < n; i ++) {
                dps[i][0] = Math.max(dps[i-1][0], dps[i-1][1] + prices[i]);
                dps[i][1] = Math.max(dps[i-1][1], dps[i-1][0] - prices[i]);
            }

            return dps[n-1][0];
        }

        int[][] dps0 = new int[2][k+1];
        int[][] dps1 = new int[2][k+1];

        // 第0天
        for (int j = 1; j <= k; j ++) {
            dps0[0][j] = 0;
            dps1[0][j] = -prices[0];
        }
        for (int i = 0; i < 2; i ++) {
            dps0[i][0] = 0;
            dps1[i][0] = 0;
        }

        int[] temp0, temp1;
        for (int i = 1; i < n; i ++) {
            for (int j = 1; j <= k; j ++) {
                 dps0[1][j] = Math.max(dps0[0][j], dps1[0][j] + prices[i]);
                 dps1[1][j] = Math.max(dps1[0][j], dps0[0][j-1] - prices[i]);
            }
            // swap
            temp0 = dps0[0];
            dps0[0] = dps0[1];
            dps0[1] = temp0;
            temp1 = dps1[0];
            dps1[0] = dps1[1];
            dps1[1] = temp1;
        }

        return dps0[0][k];
    }

}
