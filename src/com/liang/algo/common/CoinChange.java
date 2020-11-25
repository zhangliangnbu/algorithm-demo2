package com.liang.algo.common;

import sun.applet.Main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 322. 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 *
 *
 * 示例 1：
 *
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 *
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：coins = [1], amount = 0
 * 输出：0
 * 示例 4：
 *
 * 输入：coins = [1], amount = 1
 * 输出：1
 * 示例 5：
 *
 * 输入：coins = [1], amount = 2
 * 输出：2
 *
 *
 * 提示：
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {186,419,83,408};
        int amount = 6249;
        CoinChange c = new CoinChange();
        System.out.println(c.coinChange(coins, amount));
    }

    private int comb = -1, count = 0;
    public int coinChange(int[] coins, int amount) {
        if (coins == null) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        // 回溯
        // 选择列表 coins, 从大到小选
        Arrays.sort(coins);
        // 选择路径
        Map<Integer, Integer> track = new HashMap<>();
        // 递归
        backtrack(coins, amount, coins.length - 1);
        return comb;
    }

    // 找到
    private void backtrack(int[] coins, int amount, int start) {
        if (comb != -1 && comb <= count) {
            return;
        }
        if (amount == 0) {
            comb = count;
            return;
        }
        for (int i = start; i >= 0; i --) {
            if (amount < coins[i]) {
                continue;
            }

            int multi = amount / coins[i];
            count += multi;
            for (int j = multi; j > 0; j --) {
                backtrack(coins, amount - coins[i] * j, i - 1);
                count --;
            }
        }
    }

    public int coinChange2(int[] coins, int amount) {
        // 动态规划
        // dp[s]表示面额为s时的最小硬币数
        // dp[s] = min(dp[s-c(i)], i=0~n-1) + 1;
        if (amount == 0) {
            return 0;
        }
        if (coins == null || coins.length == 0) {
            return -1;
        }

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i ++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
