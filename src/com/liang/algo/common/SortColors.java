package com.liang.algo.common;

public class SortColors {

    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        // count
        int redEnd = -1, whiteEnd = -1, blueEnd = -1;
        for (int val : nums) {
            if (val == 0) {
                redEnd ++;
                nums[redEnd] = 0;

                whiteEnd ++;
                if (redEnd + 1 <= whiteEnd) {
                    nums[whiteEnd] = 1;
                }

                blueEnd ++;
                if (whiteEnd + 1 <= blueEnd) {
                    nums[blueEnd] = 2;
                }
            } else if (val == 1) {
                whiteEnd ++;
                nums[whiteEnd] = 1;
                blueEnd ++;
                if (whiteEnd + 1 <= blueEnd) {
                    nums[blueEnd] = 2;
                }
            } else {
                blueEnd ++;
            }
        }
    }
}
