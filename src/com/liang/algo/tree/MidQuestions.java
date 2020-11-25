package com.liang.algo.tree;

import java.util.*;


public class MidQuestions {

    /**
     * 1305. 两棵二叉搜索树中的所有元素
     * 给你 root1 和 root2 这两棵二叉搜索树。
     *
     * 请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        List<Integer> list = new ArrayList<>();
        pushLeft(root1, stack1);
        pushLeft(root2, stack2);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (stack1.isEmpty()) {
                addHelper(stack2, list);
            } else if (stack2.isEmpty()) {
                addHelper(stack1, list);
            } else {
                TreeNode r1 = stack1.peek();
                TreeNode r2 = stack2.peek();
                if (r1.val <= r2.val) {
                    addHelper(stack1, list);
                } else {
                    addHelper(stack2, list);
                }
            }
        }
        return list;
    }

    public void addHelper(Stack<TreeNode> stack, List<Integer> list) {
        TreeNode root = stack.pop();
        list.add(root.val);
        if (root.right != null) {
            pushLeft(root.right, stack);
        }
    }

    public void pushLeft(TreeNode root, Stack<TreeNode> stack) {
        if (root != null) {
            stack.push(root);
            pushLeft(root.left, stack);
        }
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countNodes(root.left);
        int right = countNodes(root.right);
        return left + right + 1;
    }

    /**
     * 1325. 删除给定值的叶子节点
     * 给你一棵以 root 为根的二叉树和一个整数 target ，请你删除所有值为 target 的 叶子节点 。
     *
     * 注意，一旦删除值为 target 的叶子节点，它的父节点就可能变成叶子节点；如果新叶子节点的值恰好也是 target ，那么这个节点也应该被删除。
     *
     * 也就是说，你需要重复此过程直到不能继续删除。
     */
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        TreeNode left = removeLeafNodes(root.left, target);
        TreeNode right = removeLeafNodes(root.right, target);
        root.left = left;
        root.right = right;
        if (root.val == target && root.left == null && root.right == null) {
            return null;
        }
        return root;
    }

    public void removeLeafNodesRec(TreeNode root, TreeNode parent, int target) {
        if (root == null) {
            return;
        }
        removeLeafNodesRec(root.left, root, target);
        removeLeafNodesRec(root.right, root, target);
        if (root.val == target && root.left == null && root.right == null) {
            if (root == parent.left) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
    }


    /**
     * 1008. 前序遍历构造二叉搜索树
     * 返回与给定前序遍历 preorder 相匹配的二叉搜索树（binary search tree）的根结点。
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return bstFromPreorderRec(preorder, 0, preorder.length - 1);
    }

    public TreeNode bstFromPreorderRec(int[] preorder, int start, int end) {
        if (start > end) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[start]);
        if (start == end) {
            return root;
        }

        int rightStart = findRightStart(preorder, start, end);
        if (rightStart < 0) {
            root.left = bstFromPreorderRec(preorder, start + 1, end);
            root.right = null;
        } else {
            root.left = bstFromPreorderRec(preorder, start + 1, rightStart - 1);
            root.right = bstFromPreorderRec(preorder, rightStart, end);
        }
        return root;
    }

    public int findRightStart(int[] preorder, int start, int end) {
        for (int i = start; i <= end; i ++) {
            if (preorder[i] > preorder[start]) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 814. 二叉树剪枝
     * 给定二叉树根结点 root ，此外树的每个结点的值要么是 0，要么是 1。
     *
     * 返回移除了所有不包含 1 的子树的原二叉树。
     *
     * ( 节点 X 的子树为 X 本身，以及所有 X 的后代。)
     */
    public TreeNode pruneTree(TreeNode root) {
        // 移除纯零叶子结点
        if (root == null) {
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }
        return root;
    }


    /**
     * 230. 二叉搜索树中第K小的元素
     * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
     *
     * 说明：
     * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
     */
    private int kth = -1, kk = 0;
    public int kthSmallest(TreeNode root, int k) {
        kthSmallestRec(root, k);
        return kth;
    }

    public void kthSmallestRec(TreeNode root, int k) {
        if (root == null || k == kk) {
            return;
        }
        kthSmallestRec(root.left, k);
        kk ++;
        if (kk == k) {
            kth = root.val;
        }
        kthSmallestRec(root.right, k);
    }

    /**
     * 513. 找树左下角的值
     * 给定一个二叉树，在树的最后一行找到最左边的值。
     */
    private int maxDpt = 0, leftVal = 0;
    public int findBottomLeftValue(TreeNode root) {
        findBottomLeftValueRec(root, 0);
        return leftVal;
    }
    public void findBottomLeftValueRec(TreeNode root, int parentDpt) {
        if (root == null) {
            return;
        }
        int currentDpt = parentDpt + 1;
        if (maxDpt < currentDpt) {
            maxDpt = currentDpt;
            leftVal = root.val;
        }
        findBottomLeftValueRec(root.left, currentDpt);
        findBottomLeftValueRec(root.right, currentDpt);
    }

    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        return checkTreeRec(t1, t2) || checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
    }

    public boolean checkTreeRec(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        return (t1.val == t2.val) && checkTreeRec(t1.left, t2.left) && checkTreeRec(t1.right, t2.right);
    }

    /**
     * 114. 二叉树展开为链表
     * 给定一个二叉树，原地将它展开为一个单链表。
     */
    public void flatten(TreeNode root) {
        flattenRec(root);
    }

    // 返回最深最右边的结点
    public TreeNode flattenRec(TreeNode root) {
        if(root == null) {
            return null;
        }
        TreeNode left = flattenRec(root.left);
        TreeNode right = flattenRec(root.right);
        if (left != null) {
            TreeNode lastLeft = findLastNode(left);
            lastLeft.right = right;
            root.right = left;
            root.left = null;
        }

        return root;
    }

    public TreeNode findLastNode(TreeNode root) {
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

    /**
     * 面试题 04.08. 首个共同祖先
     * 设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。不得将其他的节点存储在另外的数据结构中。注意：这不一定是二叉搜索树。
     *
     * 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4]
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (root.val == p.val || root.val == q.val) {
            return root;
        }
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        }
        return right;
    }

    /**
     * 129. 求根到叶子节点数字之和
     * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
     *
     * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
     *
     * 计算从根到叶子节点生成的所有数字之和。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     */
    public int sumNumbers(TreeNode root) {
        return sumNumbersRec(root, 0);
    }

    public int sumNumbersRec(TreeNode root, int parentSum) {
        if (root == null) {
            return 0;
        }
        int cur = parentSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return cur;
        } else {
            return sumNumbersRec(root.left, cur) + sumNumbersRec(root.right, cur);
        }
    }

    /**
     * 437. 路径总和 III
     * 给定一个二叉树，它的每个结点都存放着一个整数值。
     *
     * 找出路径和等于给定数值的路径总数。
     *
     * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     *
     * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
     */
    private int ptSum = 0;
    // 利用hashmap存储父节点
    private Map<TreeNode, TreeNode> ptMap = new HashMap<>();
    public int pathSum(TreeNode root, int sum) {
        pathSumRec(root, null, sum);
        return ptSum;
    }
    public void pathSumRec(TreeNode root, TreeNode parent, int sum) {
        if (root == null) {
            return;
        }
        ptMap.put(root, parent);
        int s = 0;
        TreeNode p = root;
        while (p != null) {
            s += p.val;
            if (s == sum) {
                ptSum ++;
            }
            p = ptMap.get(p);
        }
        pathSumRec(root.left, root, sum);
        pathSumRec(root.right, root, sum);
    }

    /**
     * 863. 二叉树中所有距离为 K 的结点
     * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
     *
     * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        // 保存父联系
        // 遍历target子树、target父和父的另一颗子树
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        createParentRelation(root, null, parentMap);
        // target子树
        disK(target, 0, K, list);
        // 父和兄弟
        disKParent(target, K, parentMap, list);
        return list;
    }

    // 保存root的父节点以及兄弟结点
    public void disKParent(TreeNode root, int K, Map<TreeNode, TreeNode> parentMap, List<Integer> list) {
        TreeNode parent = parentMap.get(root);
        if (parent == null) {
            return;
        }
        if (K == 1) {
            list.add(parent.val);
        } else {
            disK(root == parent.left ? parent.right : parent.left, 0, K - 2, list);
            disKParent(parent, K - 1, parentMap, list);
        }
    }

    // 保存父联系
    private void createParentRelation(TreeNode root, TreeNode parent, Map<TreeNode, TreeNode> map) {
        if (root == null) {
            return;
        }
        map.put(root, parent);
        createParentRelation(root.left, root, map);
        createParentRelation(root.right, root, map);
    }

    // 保存以root为根节点 距离为k的节点
    private void disK(TreeNode root, int currentK, int K, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (currentK == K) {
            list.add(root.val);
        } else {
            disK(root.left, currentK + 1, K, list);
            disK(root.right, currentK + 1, K, list);
        }
    }


    /**
     * 剑指 Offer 26. 树的子结构
     * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
     *
     * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null) {
            return false;
        }
        return isSubStructureRec(A, B);
    }

    public boolean isSubStructureRec(TreeNode A, TreeNode B) {
        if (A == null) {
            return isSimilar(A, B);
        } else {
            return isSimilar(A, B)
                    || isSubStructureRec(A.left, B)
                    || isSubStructureRec(A.right, B);
        }
    }

    public boolean isSimilar(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }
        return A.val == B.val && isSimilar(A.left, B.left) && isSimilar(A.right, B.right);
    }












}
