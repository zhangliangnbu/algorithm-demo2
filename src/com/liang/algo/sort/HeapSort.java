package com.liang.algo.sort;

import com.liang.algo.Utils;


/**
 * 算法步骤
 * 创建一个堆 H[0……n-1]；
 *
 * 把堆首（最大值）和堆尾互换；
 *
 * 把堆的尺寸缩小 1，并调用 shift_down(0)，目的是把新的数组顶端数据调整到相应位置；
 *
 * 重复步骤 2，直到堆的尺寸为 1。
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] nums = {9,3,8,4,2,5,1,3,5,6,7,11};
        HeapSort obj = new HeapSort();
        obj.sort(nums);
        Utils.print(nums);
    }

    public void sort(int[] arr) {
        int len = arr.length;
        // 构建最大堆
        buildMaxHeap(arr, len); // n * log(n)
        // 排序 最大索引的无序节点与根节点交换值 n * log(n)
        for (int i = len - 1; i > 0; i --) {
            swap(arr, 0, i);
            len --;
            heapify(arr, 0, len);
        }
    }

    private void buildMaxHeap(int[] arr, int len) {
        // 从倒数第二层节点开始向下处理
        for (int i = len / 2; i >= 0; i --) {
            heapify(arr, i, len);
        }
    }

    private void heapify(int[] arr, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(arr, i, largest);
            // 由于larget(left or right)的值改变了，所以需要向下遍历
            heapify(arr, largest, len);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
