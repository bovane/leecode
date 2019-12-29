[TOC]

# Search in Rotated Sorted Array

## 问题描述

给定一个按照升序排列的数组，假设按升序排序的数组**在事先未知的某个枢轴处旋转。** 给定一个值value，要求以O(log n)的时间复杂度查找该值是否存在于数组中，若存在则返回下标，若不存在则返回-1

### example

```
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
```

## 问题分析

该问题主要的难点是**升序数组实现通过在某个轴处旋转，**并且需要要求O(log n)的时间复杂度。因此我们很容易想到二分查找，时间复杂度为O(log n),但是该方法需要数组是有序数组，因此==接下来我们需要处理 在某个轴处旋转的问题。==

由于我们假设数组中没有重复元素，因此事先经过排序的数组是严格的升序数组，因此如果在某个轴进行旋转，**对于 rotate 过的数组，其最小值一定出现在该 rotation 的唯一拐点。** (因为一个严格升序数组在某个轴旋转之后，==原数组变为两个升序数组==，因此一定会存在**拐点就是最小值**)，除此之外，我们注意到**严格升序的数组一定满足其最左端元素的值小于最右端元素的值**，而 rotate 过的数组一定是==最左端元素的值大于最右端元素的值==。利用这个性质，我们可以构建 binary search：如果一段数组已经是严格升序，那么直接返回其最左端元素即可。**不然我们可以把数组分为两半，**其中一半一定是严格升序的，==而另一半依然是一个 rotated sorted array，==且其中包含原数组的最小值（拐点）。这样我们可以排除掉严格升序的那一半，而在另一半里继续找最小值。**注意理解这里的思路：我们自定义了一个二分搜索。**

```java
public int search(int[] nums, int target) {
     if (nums == null || nums.length == 0) {
         return -1;
    }
     int left = 0;
     int right = nums.length - 1;
     while (left <= right) {
         int mid = left + (right - left) / 2;
         if (nums[mid] == target) {
             return mid;
         } else if (nums[left] <= nums[mid]) {
             if (nums[left] <= target && target  < nums[mid]) {
                 right = mid - 1;
             } else {
                 left = mid + 1;
             }
         } else {
             if (nums[mid] < target && target  <= nums[right]) {
                 left = mid + 1;
             } else {
                 right = mid - 1;
             }
         }
     }
     return -1;
 }
```



代码不容易读 —— 主要因为if else 分支比较深入。而且这里需要理解如何自定义二分搜索的。

## Summary

### 相关知识

- 二分搜索
- 严格数组按某个轴旋转后的特点？**[最小值在唯一拐点处]**
- 如何自定义二分搜索，==主要是思维问题，==能将原问题分为更小的两个子问题。

[参考文献](https://zhuanlan.zhihu.com/p/34974336)