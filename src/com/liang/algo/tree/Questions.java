package com.liang.algo.tree;

import sun.reflect.generics.tree.Tree;

import java.util.*;

public class Questions {

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        } else if (val < root.val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }

    /**
     * 938. 二叉搜索树的范围和
     * 给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。
     *
     * 二叉搜索树保证具有唯一的值。
     */
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        int left = rangeSumBST(root.left, L, R);
        int right = rangeSumBST(root.right, L, R);
        return (root.val >= L && root.val <= R) ? root.val + left + right : left + right;
    }

    /**
     * 108. 将有序数组转换为二叉搜索树
     * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
     *
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        // 中间值作为根节点，左右同样处理
        if (nums == null || nums.length == 0) {
            return null;
        }
        return sortedArrayToBSTRec(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBSTRec(int[] nums, int start, int end) {
        // 中间值作为根节点，左右同样处理
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(nums[start]);
        }
        int mid = (start + end + 1) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBSTRec(nums, start, mid - 1);
        root.right = sortedArrayToBSTRec(nums, mid + 1, end);
        return root;
    }


    /**
     * 剑指 Offer 54. 二叉搜索树的第k大节点
     * 给定一棵二叉搜索树，请找出其中第k大的节点。
     */
    public int kthLargest(TreeNode root, int k) {
        // 中序遍历，存储到list中，返回list.get(n - k)
        List<Integer> list = new ArrayList<>();
        kthLargestRec(root, list);
        return list.get(list.size() - k);
    }

    private void kthLargestRec(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        kthLargestRec(root.left, list);
        list.add(root.val);
        kthLargestRec(root.right, list);
    }

    /**
     *
     * 897. 递增顺序查找树
     * 给你一个树，请你 按中序遍历 重新排列树，
     * 使树中最左边的结点现在是树的根，并且每个结点没有左子结点，只有一个右子结点。
     *
     */
    public TreeNode increasingBST(TreeNode root) {
        // 中序排序获取list，再组成链表
        List<TreeNode> list = new ArrayList<>();
        increasingBSTRec(root, list);
        for (int i = 0, size = list.size(); i < size; i ++) {
            TreeNode node = list.get(i);
            node.left = null;
            if (i < size - 1) {
                node.right = list.get(i + 1);
            } else {
                node.right = null;
            }
        }
        return list.size() == 0 ? null : list.get(0);
    }

    public void increasingBSTRec(TreeNode root, List<TreeNode> list) {
        // 中序排序获取list，再组成链表
        if (root == null) {
            return;
        }
        increasingBSTRec(root.left, list);
        list.add(root);
        increasingBSTRec(root.right, list);
    }

    /**
     * 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = null, right = null;
        if (p.val < root.val && q.val < root.val) {
           left = lowestCommonAncestor(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            right = lowestCommonAncestor(root.right, p, q);
        } else {
            left = lowestCommonAncestor(root.left, p, q);
            right = lowestCommonAncestor(root.right, p, q);
        }
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    /**
     * 965. 单值二叉树
     * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
     *
     * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
     */
    public boolean isUnivalTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        boolean left = isUnivalTree(root.left);
        boolean right = isUnivalTree(root.right);
        boolean current = (root.left == null || root.val == root.left.val)
                && (root.right == null || root.val == root.right.val);
        return current && left && right;
    }

    /**
     * 669. 修剪二叉搜索树
     * 给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。通过修剪二叉搜索树，
     * 使得所有节点的值在[L, R]中 (R>=L) 。你可能需要改变树的根节点，
     * 所以结果应当返回修剪好的二叉搜索树的新的根节点。
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }

    /**
     * 1022. 从根到叶的二进制数之和
     * 给出一棵二叉树，其上每个结点的值都是 0 或 1 。
     * 每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。
     * 例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。
     *
     * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
     *
     * 以 10^9 + 7 为模，返回这些数字之和。
     */
    private int rootToLeafSum = 0;
    public int sumRootToLeaf(TreeNode root) {
        sumRootToLeafRec(root, 0);
        return rootToLeafSum;
    }

    public void sumRootToLeafRec(TreeNode root, int ps) {
        if (root == null) {
            return;
        }
        int current = ps * 2 + root.val;
        if (root.left == null && root.right == null) {
            rootToLeafSum = (rootToLeafSum + current) % 1000000007;
        } else {
            sumRootToLeafRec(root.left, current);
            sumRootToLeafRec(root.right, current);
        }
    }

    /**
     * 257. 二叉树的所有路径
     * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     */
    private List<String> btpList = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        // up2down
        binaryTreePathsRec(root, "");
        return btpList;
    }

    public void binaryTreePathsRec(TreeNode root, String sb) {
        // up2down
        if (root == null) {
            return;
        }
        sb = sb + root.val;
        if (root.left == null && root.right == null) {
            btpList.add(sb);
        } else {
            sb = sb + "->";
            binaryTreePathsRec(root.left, sb);
            binaryTreePathsRec(root.right, sb);
        }

    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        leafSimilarRec(root1, l1);
        leafSimilarRec(root2, l2);
        int len = l1.size();
        if (len != l2.size()) {
            return false;
        }
        for (int i = 0; i < len; i ++) {
            if (!l1.get(i).equals(l2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public void leafSimilarRec(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            list.add(root.val);
        } else {
            leafSimilarRec(root.left, list);
            leafSimilarRec(root.right, list);
        }
    }


    /**
     * 面试题 17.12. BiNode
     * 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。实现一个方法，把二叉搜索树转换为单向链表，要求依然符合二叉搜索树的性质，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。
     *
     * 返回转换后的单向链表的头节点。
     *
     * 注意：本题相对原题稍作改动
     */
    public TreeNode convertBiNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = convertBiNode(root.left);
        TreeNode right = convertBiNode(root.right);
        if (left == null && right == null) {
            return root;
        }

        if (left == null) {
            root.left = null;
            root.right = right;
            return root;
        }
        // 求left最后一个节点
        TreeNode ll = left;
        while (ll.right != null) {
            ll = ll.right;
        }
        ll.left = null;
        ll.right = root;
        root.left = null;
        root.right = right;
        return left;
    }

    private TreeNode pre, first;
    public TreeNode convertBiNode1(TreeNode root) {
        // 中序遍历 记录前驱
        // 中序遍历的结果就是 链表的顺序，因此记录前驱之后 pre.right = cur即可
        first = new TreeNode(Integer.MIN_VALUE);
        convertBiNode1Rec(root);
        return first.right;
    }

    public void convertBiNode1Rec(TreeNode cur) {
        // 中序遍历 记录前驱
        // 中序遍历的结果就是 链表的顺序，因此记录前驱之后 pre.right = cur即可
        if (cur == null) {
            return;
        }
        convertBiNode1Rec(cur.left);
        if (first.right == null || first.right.val > cur.val) {
            first.right = cur;
        }
        if (pre != null) {
            pre.right = cur;
            cur.left = null;
        }
        pre = cur;
        convertBiNode1Rec(cur.right);
    }

    private int minDelta = Integer.MAX_VALUE, preVal = -1;
    public int getMinimumDifference(TreeNode root) {
        // 中序排序 获得 有序列表 最下差值一定是临近两个节点的差值之一
        // 用两个数字保存最小差值和前驱节点值
        getMinimumDifferenceRec(root);
        return minDelta;
    }

    public void getMinimumDifferenceRec(TreeNode root) {
        if (root == null) {
            return;
        }
        getMinimumDifferenceRec(root.left);
        if (preVal >= 0) {
            minDelta = Math.min(minDelta, Math.abs(root.val - preVal));
        }
        preVal = root.val;
        getMinimumDifferenceRec(root.right);
    }

    /**
     * 剑指 Offer 55 - II. 平衡二叉树
     * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。
     * 如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
     */
    public boolean isBalanced(TreeNode root) {
        return isBalancedRec(root) != -1;
    }

    public int isBalancedRec(TreeNode root) {
        // 每个节点的深度
        if (root == null) {
            return 0;
        }
        int left = isBalancedRec(root.left);
        int right = isBalancedRec(root.right);

        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    /**
     * 653. 两数之和 IV - 输入 BST
     * 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
     */
    public boolean findTarget(TreeNode root, int k) {
        // 中序遍历 存储列表 双指针求和
        if (root == null) {
            return false;
        }
        List<Integer> list = new ArrayList<>();
        findTargetRec(root, list);

        int p = 0, q = list.size() - 1;
        while (p >= 0 && p < q) {
            int sum = list.get(p) + list.get(q);
            if (sum == k) {
                return true;
            } else if (sum > k) {
                q --;
            } else {
                p --;
            }
        }

        return false;
    }

    public void findTargetRec(TreeNode root, List<Integer> list) {
        // 中序遍历
        if (root == null) {
            return;
        }
        findTargetRec(root.left, list);
        list.add(root.val);
        findTargetRec(root.right, list);
    }

    /**
     * 563. 二叉树的坡度
     * 给定一个二叉树，计算整个树的坡度。
     *
     * 一个树的节点的坡度定义即为，该节点左子树的结点之和和右子树结点之和的差的绝对值。空结点的的坡度是0。
     *
     * 整个树的坡度就是其所有节点的坡度之和。
     */
    private int tiltSum = 0;
    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        findTiltRec(root);
        return tiltSum;
    }

    public int findTiltRec(TreeNode root) {
        // 节点和
        if (root == null) {
            return 0;
        }
        int left = findTiltRec(root.left);
        int right = findTiltRec(root.right);
        tiltSum += Math.abs(left - right);
        return left + right + root.val;
    }

    private int leftSum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        sumOfLeftLeavesRec(root);
        return leftSum;
    }

    public void sumOfLeftLeavesRec(TreeNode root) {
        // 每次迭代 计算左叶子和
        if(root == null) {
            return;
        }
        if (root.left != null && root.left.left == null && root.left.right == null) {
            leftSum += root.left.val;
        }
        sumOfLeftLeavesRec(root.left);
        sumOfLeftLeavesRec(root.right);
    }

    /**
     * 543. 二叉树的直径
     * 给定一棵二叉树，你需要计算它的直径长度。
     * 一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
     */
    private int dMax = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        diameterOfBinaryTreeRec(root);
        return dMax - 1;
    }

    public int diameterOfBinaryTreeRec(TreeNode root) {
        // 返回以root为端点的最大直径，计算端点直接和最大直径
        if (root == null) {
            return 0;
        }
        int left = diameterOfBinaryTreeRec(root.left);
        int right = diameterOfBinaryTreeRec(root.right);
        dMax = Math.max(dMax, left + 1 + right);
        return Math.max(left, right) + 1;
    }

    /**
     * 501. 二叉搜索树中的众数
     * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
     *
     * 假定 BST 有如下定义：
     *
     * 结点左子树中所含结点的值小于等于当前结点的值
     * 结点右子树中所含结点的值大于等于当前结点的值
     * 左子树和右子树都是二叉搜索树
     */
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        // 中序遍历 存储hashmap 求最大值 求最大值个数
        Map<Integer, Integer> map = new HashMap<>();
        findModeRec(root, map);
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            max = Math.max(max, entry.getValue());
        }
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (max == entry.getValue()) {
                list.add(entry.getKey());
            }
        }
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }

    public void findModeRec(TreeNode root, Map<Integer, Integer> map) {
        // 中序遍历 存储hashmap 求最大值 求最大值个数
        if (root == null) {
            return;
        }
        int key = root.val;
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + 1);
        } else {
            map.put(key, 1);
        }
        findModeRec(root.left, map);
        findModeRec(root.right, map);
    }


    private int base, count, maxCount;
    public int[] findMode1(TreeNode root) {
        if (root == null) {
            return null;
        }
        Set<Integer> ans = new HashSet<>();
        findMode1Rec(root, ans);
        return ans.stream().mapToInt(Integer::valueOf).toArray();
    }

    public void findMode1Rec(TreeNode root, Set<Integer> ans) {
        if (root == null) {
            return;
        }
        findMode1Rec(root.left, ans);
        if (root.val == base) {
            count ++;
        } else {
            base = root.val;
            count = 1;
        }
        if (maxCount < count) {
            maxCount = count;
            ans.clear();
            ans.add(base);
        } else if (maxCount == count) {
            ans.add(base);
        }


        findMode1Rec(root.right, ans);
    }

    /**
     * 671. 二叉树中第二小的节点
     * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
     *
     * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
     **/
    private int secondMin = -1;
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        findSecondMinimumValueRec(root, root.val);
        return secondMin;
    }

    public void findSecondMinimumValueRec(TreeNode root, int min) {
        if (root == null){
            return;
        }
        if (root.val != min) {
            if (secondMin < 0) {
                secondMin = root.val;
            } else {
                secondMin = Math.min(secondMin, root.val);
            }
        }
        findSecondMinimumValueRec(root.left, min);
        findSecondMinimumValueRec(root.right, min);

    }

    /**
     * 111. 二叉树的最小深度
     * 给定一个二叉树，找出其最小深度。
     *
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     */
    public int minDepth(TreeNode root) {
        // 后序遍历
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return Math.min(left, right) + 1;
    }

    /**
     * 687. 最长同值路径
     * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
     *
     * 注意：两个节点之间的路径长度由它们之间的边数表示。
     */
    private int maxLongest = 0;
    public int longestUnivaluePath(TreeNode root) {
        longestUnivaluePathRec(root);
        return Math.max(maxLongest - 1, 0);
    }

    public int longestUnivaluePathRec(TreeNode root) {
        // 返回以root为端点的最长子树中最长路径
        if (root == null) {
            return 0;
        }
        int left = longestUnivaluePathRec(root.left);
        int right = longestUnivaluePathRec(root.right);
        int ll = 0, lr = 0;
        if (root.left != null && root.left.val == root.val) {
            ll = Math.max(ll, left);
        }
        if (root.right != null && root.right.val == root.val) {
            lr = Math.max(lr, right);
        }
        maxLongest = Math.max(maxLongest, ll + lr + 1);
        return Math.max(ll, lr) + 1;
    }

    /**
     * 1379. 找出克隆二叉树中的相同节点
     * 给你两棵二叉树，原始树 original 和克隆树 cloned，以及一个位于原始树 original 中的目标节点 target。
     *
     * 其中，克隆树 cloned 是原始树 original 的一个 副本 。
     *
     * 请找出在树 cloned 中，与 target 相同 的节点，并返回对该节点的引用（在 C/C++ 等有指针的语言中返回 节点指针，其他语言返回节点本身）。
     *
     *
     *
     * 注意：
     *
     * 你 不能 对两棵二叉树，以及 target 节点进行更改。
     * 只能 返回对克隆树 cloned 中已有的节点的引用。
     * 进阶：如果树中允许出现值相同的节点，你将如何解答？
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null) {
            return null;
        }
        if (original == target) {
            return cloned;
        }
        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        if (left != null) {
            return left;
        }
        return getTargetCopy(original.right, cloned.right, target);
    }










}
