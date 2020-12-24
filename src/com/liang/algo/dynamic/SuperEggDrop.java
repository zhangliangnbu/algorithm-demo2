package com.liang.algo.dynamic;

/**
 * 887. 鸡蛋掉落
 * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 *
 * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
 *
 * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 *
 * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 *
 * 你的目标是确切地知道 F 的值是多少。
 *
 * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 *
 *
 *
 * 示例 1：
 *
 * 输入：K = 1, N = 2
 * 输出：2
 * 解释：
 * 鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
 * 如果它没碎，那么我们肯定知道 F = 2 。
 * 因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
 * 示例 2：
 *
 * 输入：K = 2, N = 6
 * 输出：3
 * 示例 3：
 *
 * 输入：K = 3, N = 14
 * 输出：4
 *
 *
 * 提示：
 *
 * 1 <= K <= 100
 * 1 <= N <= 10000
 * 通过次数34,792提交次数120,687
 */
public class SuperEggDrop {

    public static void main(String[] args) {
        SuperEggDrop obj = new SuperEggDrop();
        System.out.println(obj.superEggDrop(1, 2));
        System.out.println(obj.superEggDrop(2, 6));
        System.out.println(obj.superEggDrop(3, 14));
    }

    public int superEggDrop(int K, int N) {
        // 动态规划
        // dps[k][n]表示 k个鸡蛋 n层楼时 最小移动次数
        int[][] dps = new int[K+1][N+1];
        for (int k = 1; k < K + 1; k ++) {
            for (int n = 1; n < N + 1; n ++) {
                dps[k][n] = n;
            }
        }
        // 转移方程：dps[k][n] = max(dps[k-1][i-1] + 1, dps[k][n-i] + 1), i = 2...n-1
        // i表示从首次是从i层楼扔鸡蛋
        for (int k = 2; k < K + 1; k ++) {
            for (int n = 3; n < N + 1; n ++) {

                // 二分查找
                // 函数dps[k-1][x-1]对x单调递增，函数dps[k][n-x]对x单调递减
                int lo = 2, hi = n - 1;
                while (lo + 1 < hi) {
                    int mi = lo + (hi - lo) / 2;
                    if (dps[k-1][mi-1] < dps[k][n-mi]) {
                        lo = mi;
                    } else if (dps[k-1][mi-1] > dps[k][n-mi]){
                        hi = mi;
                    } else {
                        lo = hi = mi;
                    }
                }
                int ml = Math.max(dps[k-1][lo-1] + 1, dps[k][n-lo] + 1);
                int mh = Math.max(dps[k-1][hi-1] + 1, dps[k][n-hi] + 1);
                dps[k][n] = Math.min(Math.min(ml, mh), dps[k][n]);
//                for (int i = 2; i < n; i ++) {
//                    // 第一次从i层楼扔鸡蛋的最坏情况下的移动次数
//                    int mi = Math.max(dps[k-1][i-1] + 1, dps[k][n-i] + 1);
//                    // 取第一次从2..n-1层楼扔鸡蛋情况下，移动次数的最小值
//                    dps[k][n] = Math.min(dps[k][n], mi);
//                }
            }
        }

        return dps[K][N];
    }

}
