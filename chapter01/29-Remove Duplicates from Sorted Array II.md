[TOC]

# Remove Duplicates from Sorted Array II ⭐

## 问题描述

给定一个有序数组然后去除数组中元素重复超过两次的元素。也就是说最后的结果数组包含的元素重复不超过两次。

### example

```
Given nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.

Given nums = [0,0,1,1,1,1,2,3,3],

Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.
```

## 问题分析

这里允许最多重复的次数是两次**，那么我们就需要用一个变量count来记录还允许有几次重复**，count初始化为1，如果出现过一次重复，则count递减1，那么下次再出现重复，快指针直接前进一步，如果这时候不是重复的，则count恢复1，由于整个数组是有序的，所以一旦出现不重复的数，则一定比这个数大，此数之后不会再有重复项。

```c++
class Solution {
public:
    int removeDuplicates(int A[], int n) {
        if (n <= 2) return n; // 边界情况检查，数组元素少于2个，直接返回
        int pre = 0, cur = 1, count = 1; //双指针法
        while (cur < n) {
            if (A[pre] == A[cur] && count == 0) ++cur; // 元素重复次数用完，跳过
            else {
                if (A[pre] == A[cur]) --count; // 如果出现重复，置count=0
                else count = 1;
                A[++pre] = A[cur++]; // 赋值元素，注意++pre，以及cur++的区别
            }
        }
        return pre + 1;
    }
};
```

```java
public int removeDuplicates(int[] nums) { // 去除重复元素
    int i = 0;
    for(int n : nums) // 依次取出数组中的value
        if(i < 1 || n > nums[i - 1]) // 这里的下标问题，根据 || 运算的特性，前半部分成立，则截断
            nums[i++] = n;
    return i;
}
public int removeDuplicates(int[] nums) { // 允许元素重复一次
   int i = 0;
   for (int n : nums)
      if (i < 2 || n > nums[i - 2])// 这里的下标问题，根据 || 运算的特性，前半部分成立，则截断
         nums[i++] = n;
   return i;
}
```

## SUMMARY

### Related knowledge

- sorted 数组
- 去除重复元素

[原题链接](https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/)