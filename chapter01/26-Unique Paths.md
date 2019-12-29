[TOC]

# Unique Paths

## 问题描述

给定一个$m \times n$的矩阵，一个机器人位于矩阵的左上角，在每个时刻机器人只能旋转向下或者向右移动一步，请问要使机器人达到右下角出口位置，一共有多少条不同路径？

![](https://raw.githubusercontent.com/bovane/md_images/master/20190417131049.png)

### example

```
Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right

Input: m = 7, n = 3
Output: 28
```

## 问题分析

此题与迷宫问题类似，但却没有迷宫问题复杂。由于问题的性质，我们不能简单用循环列举所有的唯一路径，我们考虑使用动态规划的思想。[所谓动态规划就是](https://www.zhihu.com/question/23995189)**：将一个问题拆成几个子问题，分别求解这些子问题，即可推断出大问题的解**。 

- 典型的动态规划题目
- 定义m*n的矩阵，$arr[i][j]$**表示到达第i行第j列的位置时有多少种不同的路径**
- 因为只能向下或者向右移动，所以==很明显有状态方程==$arr[i][j]=arr[i-1][j] + arr[i][j-1]$ 
- 构建二维数组即可解决，但是空间复杂度为O(m*n)；

- 简化重复子问题，我们每次只用到**上一步的前一个结果**和==上一步的当前结果==，所以用一个数组表示即可。$arr[i] = arr[i] + arr[i-1]$

```c++
class Solution {
public:
    int uniquePaths(int m, int n) {
        vector<vector<int>> dp(m, vector<int>(n, 1)); // 初始化一个矩阵
        for (int i = 1; i < m; i++) { // 左上角元素为[0][0]
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]; // 机器人达到i,j的位置
                // 只取决于[i-1][j]向下走一步和[i][j-1]向右走一步
            }
        }
        return dp[m - 1][n - 1];
    }
};
```

上面这一种解法的时间复杂度为$O(m \times n)$,空间复杂度也为$O(m \times n)$ 。但是，实际上我们可以观察到每次我们跟新$dp[i][j]$的状态时，我们只用到了前一行$dp[i-1][j]$和当前行$dp[i][j-1]$，因此我们减少内存的使用$O(n)$

![](https://raw.githubusercontent.com/bovane/md_images/master/20190417154706.png)

```c++
class Solution {
public:
    // 简化重复子问题，我们每次只用到上一步的前一个结果和上一步的当前结果，所以用一个向量表示即可。
    // cur[j] = pre[j]+cur[j-1]
    int uniquePaths(int m, int n) {
        vector<int> pre(n, 1), cur(n, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cur[j] = pre[j] + cur[j - 1]; // pre[j]就是更新前的cur[j]
            }
            swap(pre, cur);
        }
        return pre[n - 1];
    }
     int uniquePaths2(int m, int n) {
        vector<int> cur(n, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cur[j] += cur[j - 1];
            }
        }
        return cur[n - 1];
    }
};
```

## Summary

寻找矩阵中==到达第i行第j列的位置时有多少种不同的路径问题==是一个典型的动态规划问题。根据规定的走法不同，$dp[i][j]$有不同的算法。

### Related knowledge

- [DP分析](https://leetcode.windliang.cc/leetCode-62-Unique-Paths.html)
- vector

