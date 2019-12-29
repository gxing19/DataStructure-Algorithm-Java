package com.datastructure.algorithm.binaryheap;

import java.util.Arrays;

/**
 * 二叉堆创建与操作
 */
public class BinaryHeap {

    /**
     * " 上浮调整 "
     *
     * @param array 待调整的堆
     */
    public static void upAdjust(int[] array) {
        int childIndex = array.length - 1;
        int parentIndex = (childIndex - 1) / 2;
        // temp 保存插入的叶子节点的值，用于最后赋值
        int temp = array[childIndex];
        while (childIndex > 0 && temp < array[parentIndex]) {
            //无须真正交换, 单向赋值即可
            array[childIndex] = array[parentIndex];

            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;

        }
        array[childIndex] = temp;

    }

    /**
     * 下沉 调整
     *
     * @param array       待调整的堆
     * @param parentIndex 父节点索引
     * @param length      堆大小
     */
    public static void downAdjust(int[] array, int parentIndex, int length) {
        //保存父节点值，用于最后赋值
        int temp = array[parentIndex];
        int childIndex = 2 * parentIndex + 1;
        while (childIndex < length) {
            //如果有右子节点，且右子节点小于左子节点的值，则定位到右子节点
            if (childIndex + 1 < length && array[childIndex + 1] < array[childIndex]) {
                childIndex++;
            }

            if (temp <= array[childIndex]) {
                break;
            }

            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        array[parentIndex] = temp;
    }

    /**
     * 构建堆
     *
     * @param array
     */
    public static void buildHeap(int[] array) {

        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            //数组取半求父节点的索引 -1, 给最后的右页子节点留位置 -1, 实际求的是堆的深度(层级数), 非叶子节点依次下沉
            downAdjust(array, i, array.length);
            System.out.println("遍历：" + Arrays.toString(array));
    }
    }


    public static void main(String[] args) {
        int[] array = new int[]{4, 2, 11, 10, 8, 3, 9, 1, 5, 6, 7};
        System.out.println("原始：" + Arrays.toString(array));
        buildHeap(array);//1,2,3,4,6,5,9,7,10,8,11
        System.out.println("最终：" + Arrays.toString(array));
    }

}
