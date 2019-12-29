[TOC]

# Maximum Subarray  ⭐

## 问题描述

给定一个整数数组，找到其连续最大和的子串，并返回其和。

### example

```
Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
```

## 问题分析

该问题属于一维动态规划问题，我们可以通过O(n)的时间复杂度找到连续最大和子序列

```java
public int maxSubArray(int[] nums) {
    int maxEnding = nums[0]; // 最终结果
    int currMax = nums[0]; // 当前最大
    for(int i = 1; i < nums.length; i++) {
        if(currMax < 0) currMax = 0;
        currMax += nums[i]; // currMax就是不断选择起点累加
        maxEnding = Math.max(maxEnding, currMax); // maxEnding不断比较，保存较大的那个
    }
    return maxEnding;
}
```

### Kadane算法

adane算法的简单想法就是**寻找所有连续的正的子数组**（max_ending_here就是用来干这事的），同时，记录所有这些连续的正的子数组中的和最大的连续数组。**每一次我们得到一个正数，就将它与max_so_far比较**，如果它的值比max_so_far大，则更新max_so_far的值。

```java
public static int maxSubArray(int[] A) {
    int maxSoFar=A[0]; // 记录当前最大连续子数组的值
    int maxEndingHere=A[0]; // 寻找记录所有正的连续子数组
    for (int i=1;i<A.length;++i){
    	maxEndingHere= Math.max(maxEndingHere+A[i],A[i]); // 连续和不断和当前元素比较
    	maxSoFar=Math.max(maxSoFar, maxEndingHere);	// 上一次当前最大和这一次的当前最大比较
    }
    return maxSoFar;
}
```

## Summary

此题只需要返回最大子数组和，并未要求同时返回最大子数组，倘若要求返回最大子数组，则需要额外维护数组用来保存当前的最大子数组。

### related knowledge

- 动态规划思想？DP：**将一个问题拆成几个子问题，分别求解这些子问题，即可推断出大问题的解**。[什么是动态规划](https://www.zhihu.com/question/23995189)