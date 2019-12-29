[TOC]

# Next Permutation

## 问题描述

实现下一个排列，此排列时按照**字典的顺序**来重新编排数字==得到一下一个大的数字排列。==如果不能重新排成下一个大的数字排列，则恢复层最小的排列(例如：升序排列)

### 字典顺序

什么是字典顺序，**基本规则就是字母表从小到大**。 如果一些单词是$abcd$组成，字母表顺序是$abcd->abdc->acbd->acdb->adbc->adcb$

如果是数字123，那么字典顺序为：

1 2 3
1 3 2
2 1 3
2 3 1
3 1 2
3 2 1

我们可以发现，**所谓字典顺序其实就是排列组合**（一个全排列组成一个字典）。

### example

`1,2,3` → `1,3,2`
`3,2,1` → `1,2,3`
`1,1,5` → `1,5,1`

## 问题分析

### 题意解析

给定一个数组排列，为了找到下一个比当前给定要大的排列，**我们需要清楚下一个排列是如何产生的**，这里有一个例子。

比如给定的1, 2, 3, 这三个数组任意交换位置，总共有6种可能，我将所有可能列在下面，并按从小到大的顺序排序。所以，如果初始给的是1, 2, 3, 那么它的**Next** **Permutation**就是1,3,2； 如果初始给的是2,3,1，那个它的**Next Permutation**就是3,1,2。

![](https://raw.githubusercontent.com/bovane/md_images/master/20190410171504.png)

### 代码思路

这个问题跟数字的进制有点类似，比如我们从0数到9，**这个时候遇到进制的最大值，我们需要进一位，后面的位变为最小**，于是变为10；又比如继续数到99，这个你同样需要进一位，后面的位变为最小，于是变成100。==不过这里不是像「数数」那样逐一递增，而是通过变换数字的位置。==下面通过图例解析一下：

![](https://raw.githubusercontent.com/bovane/md_images/master/20190410172023.png)

因此为了产生一个比当前排列刚刚大一点的排列，我们可以通过以下三步得到：

- 从右到左依次扫描，找到第一个打破升序顺序的数字(从右到左方向形成的升序)        $\leftarrow$
- 将第一个打破升序顺序的数字$\alpha$与**已扫描升序数字中刚刚大于**数字$\alpha$的数字$\beta$ 交换    $swap(\alpha,\beta)$
- 将交换后的升序序列变换为降序排列(从右到左方向形成降序)         $\leftarrow$

![](https://raw.githubusercontent.com/bovane/md_images/master/20190410173607.png)

### 代码实现

```java
public class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) { // 从右到左找到第一个破坏升序的数字i
            i--;
        }
        if (i >= 0) { // 如果i有效，则在找过的升序数字里找到最近大于数字i的数字j
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j); // 交换两个数字
        }
        reverse(nums, i + 1); // 将原来升序序列反转，变为降序
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```



## Summary

该题的关键之处有两点，**第一点为准确理解题意**，该题描述了一个怎样的场景，不光要知晓其形式化的定义，并且还能否用例子描述出来？第二点是为了达到题目要求，我们应该怎样设计算法？为什么要这么设计算法？

### 涉及知识点

- 数据结构：Array

- 排列组合规则(全排列)
- 逆序和交换数字

## 参考文献

[Next Permutation详解](https://zhuanlan.zhihu.com/p/53318285)