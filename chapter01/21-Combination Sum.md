[TOC]

# Combination Sum

## 问题描述

给定一个没有重复元素的数组以及一个target，要求我们找出所有**满足元素和等于target的组合**。注意，数组中的元素可以重复使用。

注意数组元素和target都应该为正数

### example

```
Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
```

## 问题分析

解决一个问题时我们最容易想到的说暴力破解，but由于这个的复杂性，我们无法通过遍历所有可能结果，因为有太多不确定性并且时间复杂度随着数组长度和数组元素值都有关，很混乱。因此我们需要考虑根据题目特点想出何深的解决方案。**这里的特点就是逐一元素找下去，采用回溯的思想。** 

- 先对数组进行排序

- 然后从小到大累加

- 等于或超过target时回溯，得出结果

![](https://raw.githubusercontent.com/bovane/md_images/master/20190413130819.png)

```java
public class Solution {
    List<List<Integer>> ans = new ArrayList<List<Integer>>();// List[List[]]作为结果保存
    int[] cans = {}; // 类成员变量
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.cans = candidates;
        Arrays.sort(cans); // 预先对数组排序
        backTracking(new ArrayList(), 0, target);// 开始回溯
        return ans;
    }
    
    public void backTracking(List<Integer> cur, int from, int target) {
        // @param 当前List，回溯起点，target
        if (target == 0) { // 递归出口，若结果符合要求，回溯保存
            List<Integer> list = new ArrayList<Integer>(cur);
            ans.add(list);
        } else { // 继续向下搜索，有一点深度优先搜索的味道
            for (int i = from; i < cans.length && cans[i] <= target; i++) {
                cur.add(cans[i]);
                backTracking(cur, i, target - cans[i]);
                cur.remove(new Integer(cans[i]));// 移除最后一个使能从上一层开始搜索
            }
        }
    }
}
```

```java
public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
    	Arrays.sort(candidates); // 对数组进行排序
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        getResult(result, new ArrayList<Integer>(), candidates, target, 0);
        
        return result;
    }
    
    private void getResult(List<List<Integer>> result, List<Integer> cur, int candidates[], int target, int start){
    	if(target > 0){ // 注意这里的循环条件，target >= candidates[i]
    		for(int i = start; i < candidates.length && target >= candidates[i]; i++){
    			cur.add(candidates[i]); // 暂存此次搜索结果
    			getResult(result, cur, candidates, target - candidates[i], i);
    			cur.remove(cur.size() - 1); //remove last element of the list 
                // We remove the digit so that we try next possible combination.
    		}//for
    	}//if
    	else if(target == 0 ){ // 此次搜索结果符合要求
    		result.add(new ArrayList<Integer>(cur));
    	}//else if
    }
}
```

## Summary

解决元素组合之和等于某一target的问题，我们不能通过for循环暴力列举所有可能情况，因为这样思路非常混乱以及太多的不确定性。==相反，我们采用递归和回溯的思想解决此类问题==，**递归的出口便是当遇到符合要求的搜索结果。**通过递归和回溯，我们能够找到所有的可能结果(有一点深度优先搜索的味道)，其实这样的问题可以归结为在$n * n$的矩阵中找到一条路径，其中$n$为数组长度。如图

![](https://raw.githubusercontent.com/bovane/md_images/master/20190413130819.png)

### 相关知识

- 数据结构List的操作
- **递归和回溯过程(通过递归搜索所有可能结果，通过回溯保存结果)** ==解决这一类的题的思想，元素组合的和等于目标值==