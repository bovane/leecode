[TOC]

# Remove duplicates from sorted array

## 问题描述

给定一个排序后的数组，编写算法删除数组中的重复元素并返回新数组的长度

### example

```
Given nums = [1,1,2]
output nums = [1,2] num.length = 2
```

## 问题分析

### My soluation

因为数组已排序好的数组，因此如果有重复元素那么重复元素应该连在一起。因此我们将第一个出现的元素认定为独特元素，我们可以使用两个指针$i,j$来解决这个问题，其中$i$是遍历地更慢地指针，$j$遍历地更快。

```java

```

### Codes

```java
public int removeDuplicates(int[] nums) {
    if (nums.length == 0)
        return 0; // 异常情况排除
    int i = 0;
    for (int j=1; j < nums.length; j++) { // 将j作为循环变量，对数组进行一次遍历
        if (nums[j] != nums[i]) { // 若后续元素与之前的元素不重复，默认第一次出现为不重复
            i++;
            nums[i] = nums[j]; // 相当于存储新数组，变量i记录不重复的元素
        }
    }
    return i+1;
}
```

## Summary

### Relatedknowledge



