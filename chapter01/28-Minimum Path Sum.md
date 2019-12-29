[TOC]

# Minimum Path Sum

## 问题描述

给定一个$m \times n$的矩阵，编写一个算法使得从**左上角到右下角的路径上数字之和**最小。每一次移动只能向下或者向右。

### example

```
Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
```

## 问题分析

和单纯的从左上角走到右下角的路径条数不同，这里我们需要计算每走一步的代价。总体的思路，我们寻找出所有的走法并计算每一条路径的代价。注意代价和寻找路径是同步的，走一步计算一步。**我们可以用动态规划思想寻找所有路径。**我们维护一个二维的dp数组，其中$dp[i][j]$表示当前位置的最小路径和，递推式也容易写出来 $dp[i][j] = grid[i][j] + min(dp[i - 1][j],dp[i][j-1])$

```c++
class Solution {
public:
    int minPathSum(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size(); 
        vector<vector<int> > sum(m, vector<int>(n, grid[0][0])); // 申请一个与grid相同维度									//的向量数组，用于保存走到i,j位置的最小代价
        for (int i = 1; i < m; i++)	// 处理第一列的数据同步，因为这是固定的
            sum[i][0] = sum[i - 1][0] + grid[i][0];
        for (int j = 1; j < n; j++)	// 处理第一行的数据同步，因为这也是固定的
            sum[0][j] = sum[0][j - 1] + grid[0][j];
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++) //每个位置i,j都有两种方式到达
                sum[i][j]  = min(sum[i - 1][j], sum[i][j - 1]) + grid[i][j];
        return sum[m - 1][n - 1];
    }
};
```

正如我们所见，每次当我们更新$sum[i][j]$时，我们只需要$sum[i-1][j] or sum[i][j-1]$，因此我们不需要维护一个$m \times n$的矩阵，只需要维护两列足矣。代码如下：

```c++

```

## Summary

动态规划问题，第一个方法是最基本，后面两个方法针对在空间上进行优化。

### RelatedKnowledge

