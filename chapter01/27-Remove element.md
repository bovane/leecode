[TOC]

# Remove element

## 问题描述

给定一个数组，给定一个值value，编写一个算法删除数组中的指定值value，返回数组长度。

### example

```java
Given nums = [3,2,2,3], val = 3
output nums[2,2],num.length = 2
```

## 问题分析

### My soluation

用两个指针$i,j$，用指针$j$向后逐次遍历，如果遇到数组中的值等于指定值，则跳过，否则将值存入i中。

```java
public int removeElement(int[] nums, int val) {
    int i = 0;
    for (int j = 0; j < nums.length; j++) { // 用j作为循环变量
        if (nums[j] != val) { // 不等于指定值时，跳过
            nums[i] = nums[j];
            i++;
        }
    }
    return i;
}
```

### approach 2:Two Pointers - when elements to remove are rare

```java
public int removeElement(int[] nums, int val) {
    int i = 0;
    int n = nums.length;
    while (i < n) {
        if (nums[i] == val) {
            nums[i] = nums[n - 1];
            // reduce array size by one
            n--;
        } else {
            i++;
        }
    }
    return n;
}
```

## Summary

### 涉及知识点

- 数据结构：Array
- Two pointer 遍历