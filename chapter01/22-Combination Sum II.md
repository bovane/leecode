[TOC]

# Combination Sum II

## 问题描述

给定一个数组以及一个target，要求我们找出所有**满足元素和等于target的组合**。==注意数组中的元素只能使用一次，即组合结果中的重复元素个数不能超过数组中重复元素的个数。==

注意数组元素和target都应该为正数

### example

```
Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
```

## 问题分析

该问题还是可用递归和回溯，寻找到一条路径。

```java
public List<List<Integer>> combinationSum2(int[] cand, int target) {
    Arrays.sort(cand); // 对数组事先排序，方便累加判定
    List<List<Integer>> res = new ArrayList<List<Integer>>(); // 最终结果List
    List<Integer> path = new ArrayList<Integer>(); // 当前运算结果path
    dfs_com(cand, 0, target, path, res);
    return res;
}

void dfs_com(int[] cand, int cur, int target, List<Integer> path, List<List<Integer>> res) {
    if (target == 0) { // 递归搜索出口，回溯的起点
        res.add(new ArrayList(path));
        return ;
    }
    if (target < 0) return; // 放弃这条路
    for (int i = cur; i < cand.length; i++){
        if (i > cur && cand[i] == cand[i-1]) continue; // 跳过数组中的重复元素
        path.add(path.size(), cand[i]); // 将当前遍历到的节点加入当前path
        dfs_com(cand, i+1, target - cand[i], path, res);// 移动到后一个(数组中的后一个元素)
        path.remove(path.size()-1); // 删除path中的最后一个元素，尝试下一个可能的组合
    }
}
```

## Summary

时间复杂度为$O(n^{2})$,空间复杂度为O(1)

### Related knowledges

- 微调的深度优先搜索
- 递归和回溯思想