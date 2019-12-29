[TOC]

# Unique Paths II

## 问题描述

给定一个$m \times n$的矩阵，一个机器人位于矩阵的左上角，在每个时刻机器人只能旋转向下或者向右移动一步，请问要使机器人达到右下角出口位置，一共有多少条不同路径？**如果在矩阵中添加障碍物，此时一共有多少条不同路径？** 其中矩阵中1代表障碍物，0代表无障碍物

### example

```
Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2
Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
```

## 问题分析

该问题主要是**确定障碍物对整个棋盘的影响。**假设整个棋盘只有一行，那么在第i个位置上设置一个障碍物后，说明位置i到最后一个格子这些路都没法走了。如果整个棋盘只有一列，那么第i位置上的障碍物，也会影响从第i位置往后的路。**所以在初始条件时，如果一旦遇到障碍物，障碍物后面所有格子的走法都是0。**相当于路断了，该路的dp值设置为0

再看求解过程，当然按照上一题的分析$dp[i][j] = dp[i-1][j] + dp[i][j-1]$ 的递推式依然成立（机器人只能向下或者向右走嘛）。**但是，一旦碰到了障碍物，那么这时的到这里的走法应该设为0**(也就是dp[i]的值变为0)，因为机器人只能向下走或者向右走，==所以到这个点就无法通过。==

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int R = obstacleGrid.length;
        int C = obstacleGrid[0].length;

        // If the starting cell has an obstacle, then simply return as there would be
        // no paths to the destination.
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        // Number of ways of reaching the starting cell = 1.
        obstacleGrid[0][0] = 1;

        // Filling the values for the first column
        for (int i = 1; i < R; i++) {
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
        }

        // Filling the values for the first row
        for (int i = 1; i < C; i++) {
            obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1 : 0;
        }

        // Starting from cell(1,1) fill up the values
        // No. of ways of reaching cell[i][j] = cell[i - 1][j] + cell[i][j - 1]
        // i.e. From above and left.
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (obstacleGrid[i][j] == 0) {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }

        // Return value stored in rightmost bottommost cell. That is the destination.
        return obstacleGrid[R - 1][C - 1];
    }
}
```



```java
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int width = obstacleGrid[0].length;
    int[] dp = new int[width];
    dp[0] = 1;
    for (int[] row : obstacleGrid) {
        for (int j = 0; j < width; j++) {
            if (row[j] == 1)
                dp[j] = 0;
            else if (j > 0)
                dp[j] += dp[j - 1];
        }
    }
    return dp[width - 1];
}
```



## Summary

当机器人遇到障碍物时，则到达该点的路径总数设置为0。

### related knowledge

- DP