package com.liang.algo.common;

public class CanThreePartsEqualSum {

    public static void main(String[] args) {
        CanThreePartsEqualSum ct = new CanThreePartsEqualSum();
        int[] A = {1,-1,1,-1};
        System.out.println(ct.canThreePartsEqualSum(A));
    }

    public boolean canThreePartsEqualSum(int[] A) {
        int s1 = A[0], s2 = 0, sum = 0, part;
        int n = A.length;
        for (int value : A) {
            sum += value;
        }
        if (sum % 3 != 0) {
            return false;
        }
        part = sum / 3;

        for (int i = 1; i < n - 1; i ++) {
            if (s1 != part) {
                s1 += A[i];
            } else {
                s2 += A[i];
                if (s2 == part) {
                    return true;
                }
            }
        }
        return false;
    }

}
