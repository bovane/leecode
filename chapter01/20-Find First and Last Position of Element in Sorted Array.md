[TOC]

#  Find First and Last Position of Element in Sorted Array

## 问题描述

给定一个排序好的的升序数组以及一个目标值value，需要找到该值在数组中的起始位置(数组中允许重复数字)

### example

```
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
```

## 问题分析

该问题属于一个数组操作的简单问题，首先我们可以尝试暴力破解方法，通过从头到尾一次遍历找出下标

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1}; // 初始化索引值

        // 先找到最左端的下标值(起始值)
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) { // 找到第一个之后就跳出循环
                targetRange[0] = i;
                break;
            }
        }

        // 如果循环结束之后，仍然未找到起始值，则该数组中不存在target，直接返回
        if (targetRange[0] == -1) {
            return targetRange;
        }

        // 反向遍历，找到最右端的下标值(终点值)
        for (int j = nums.length-1; j >= 0; j--) {
            if (nums[j] == target) {
                targetRange[1] = j;
                break;
            }
        }

        return targetRange;
    }
}
```

通过一次遍历可知，该方法的时间复杂度为O(n)，但是题目要求我们在O(log n)的时间复杂度解决问题，因此我们可以想到二分搜索(**因为这个问题的本质就是搜索问题**)

```java
class Solution {
    // 返回target在数组中的最左端索引(最右端索引)
    // 略微修改的二分搜索
    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        // left为一个bool变量，用于指示当num[mid]==target时的行为
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2; 
            if (nums[mid] > target || (left && target == nums[mid])) { // 向左查找的条件
                hi = mid;
            }
            else { // 向右查找
                lo = mid+1;
            }
        }

        return lo;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIdx = extremeInsertionIndex(nums, target, true); // 先向左查找，找左索引

      	// 确保leftIdx在数组范围内，并且确保target在数组中
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false)-1;//向右查找,找右索引

        return targetRange;
    }
}
```

## Summary

该问题的关键是要求O(log n)的时间复杂度，因此我们需要用修改之后的二分查找。和普通的二分算法不同，该算法并不是找到target就终止，**而是我们持续搜索直到 lo == hi**，同时我们**还添加一个 Indicator来标志旋转向左和向右搜索**，我们的二分算法是朝左还是朝右搜索【因为我们这里是需要找到两个索引——起点和终点，因此当mid的值恰好落在中间时，我们应该朝那边搜索呢？】。

第一遍用来找左边界， ==若mid 大于 或者 等于 target时左边界都会在前半部分。,此时需要hi=mid==。**若是取mid小于target, 左边界会在后半部分.此时需要lo=mid+1**

第二遍用来找右边界，**如果mid大于target，则右边界会在前半部分，此时需要hi=mid**，==若mid小于或者等于target则右边界在后半部分，此时需要lo=mid+1==

**这里最巧妙的是indicator的设置。**

### 相关知识

- 二分搜索
- 微调二分搜索(自定义停止条件，增加参数)