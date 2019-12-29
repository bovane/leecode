[TOC]

# Container With Most Water ⭐

## 问题描述

给出n个非负整数$a_{1},a_{2},...a_{n}$,每一个在坐标轴上代表一个点$(i,a_{i})$ ,这些点$(i,a_{i})$ 和$(i,0)$构成n条竖直线，我们任取其中两条竖线连同x轴，构成一个装水的矩形，现在要求找到能装最多的矩形，并求出能装多少水。

![](https://raw.githubusercontent.com/bovane/md_images/master/question.jpg)

### example

```java
Input: [1,8,6,2,5,4,8,3,7]
Output: 49
```

## 问题分析

装水的面积受到两个柱子的最短高度以及柱子间的距离约束，我们可以通过暴力破解，一一枚举每一种情况进行解题。

### 方法一：Brute Force

```java
public class Solution {
    public int maxArea(int[] height) {
        int maxarea = 0;
        for (int i = 0; i < height.length; i++)
            for (int j = i + 1; j < height.length; j++) // 从第i+1个柱子到最后一个柱子
                maxarea = Math.max(maxarea, Math.min(height[i], height[j]) * (j - i));
        return maxarea;
    }
}
```

### 复杂度分析

- Time complexity : $O(n^2)$
- Space complexity : $O(1)$ 

### 方法二：Two Pointer Approach

两点方法是通过初始化选择第一个点和最后一个点作为柱子。At every step, we find out the area formed between them, update max area and **move the pointer pointing to the shorter line towards the other end by one step**.(短的柱子朝长的柱子移动，为什么这样做有道理？) 通过高度的增加来抵消宽度的减少**因为宽度是以1为单位减少的，而高度则是以1的倍数增加的。**现在，为了最大化面积，我们需要考虑较大长度的线之间的区域。 如果我们试图将指针向内移动更长的线，我们将不会获得任何面积增加，因为它受到较短线的限制。 但是，尽管宽度减小了，但根据相同的论点，移动较短线的指针可能会变得有利。 这样做是因为通过移动较短线的指针获得的相对较长的线可以克服由宽度减小引起的面积减小。

```java
public class Solution {
    public int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) // 短的柱子朝长的柱子移动
                l++;
            else
                r--;
        }
        return maxarea;
    }
}
```

### 复杂度分析

- Time complexity : $O(n)$. Single pass.
- Space complexity : $O(1)$. 

## SUMMARY

本题算一个简单的优化问题，我们很容易用一个两重循环算出所有的面积，最后选择一个最大的面积。但是我们能用更简单的方法找到最大面积。通过高度的增加来抵消宽度的减少**因为宽度是以1为单位减少的，而高度则是以1的倍数增加的。**现在，为了最大化面积，我们需要考虑较大长度的线之间的区域。 我们移动两个边界线更短的一个而不是长的那个（==因为向内移动长的那个线期望表现不好==）移动较短线的指针可能会变得有利。 这样做是因为通过移==动较短线的指针获得的相对较长的线可以克服由宽度减小引起的面积减小。==

### Related knowledge

- 数据结构：Arrays
- 优化问题 —— 最大值问题

[原题链接](https://leetcode.com/articles/container-with-most-water/) 