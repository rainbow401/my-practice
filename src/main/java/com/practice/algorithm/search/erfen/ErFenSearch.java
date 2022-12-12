package com.practice.algorithm.search.erfen;

/**
 * @author yanzhihao
 */
public class ErFenSearch {

    /**
     * 二分查找 [left, right] 左闭右闭
     */
    public int searchV1(Integer[] nums, Integer target) {
        int left = 0, right = nums.length - 1;
        if (target < nums[left] || target > nums[right]) {
            return -1;
        }

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                return nums[mid];
            }
        }

        return -1;
    }

    /**
     * 二分查找 [left, right) 左闭右闭
     */
    public int searchV2(Integer[] nums, Integer target) {
        int left = 0, right = nums.length - 1;
        if (target < nums[0] || target > nums[nums.length - 1]) {
            return -1;
        }

        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (target < nums[mid]) {
                right = mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                return nums[mid];
            }
        }

        return -1;
    }
}
