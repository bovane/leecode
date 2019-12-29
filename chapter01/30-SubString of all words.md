[TOC]

# Search in Rotated Sorted Array II ⭐⭐

## 问题描述

给定一个按照升序排列的数组，假设按升序排序的数组**在事先未知的某个枢轴处旋转。** 给定一个值value，要求以O(log n)的时间复杂度查找该值是否存在于数组中，若存在则返回下标，若不存在则返回-1

**这里数组允许重复元素存在。**

### example

```
Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
```

## 问题分析

该问题主要的难点是**升序数组实现通过在某个轴处旋转，**并且需要要求O(log n)的时间复杂度。因此我们很容易想到二分查找，时间复杂度为O(log n),但是该方法需要数组是有序数组，因此==接下来我们需要处理 在某个轴处旋转的问题。==

==由于我们假设数组中没有重复元素==，因此事先经过排序的数组是严格的升序数组，因此如果在某个轴进行旋转，**对于 rotate 过的数组，其最小值一定出现在该 rotation 的唯一拐点。** (因为一个严格升序数组在某个轴旋转之后，==原数组变为两个升序数组==，因此一定会存在**拐点就是最小值**)，除此之外，我们注意到**严格升序的数组一定满足其最左端元素的值小于最右端元素的值**，而 rotate 过的数组一定是==最左端元素的值大于最右端元素的值==。利用这个性质，我们可以构建 binary search：如果一段数组已经是严格升序，那么直接返回其最左端元素即可。**不然我们可以把数组分为两半，**其中一半一定是严格升序的，==而另一半依然是一个 rotated sorted array，==且其中包含原数组的最小值（拐点）。这样我们可以排除掉严格升序的那一半，而在另一半里继续找最小值。

现在由于数组中有重复元素，**原来我们是依靠中间和边缘元素的大小关系**，来判断哪一半是不受rotate影响，仍然有序的。==而现在因为重复的出现，如果我们遇到中间和边缘相等的情况==，我们就丢失了哪边有序的信息，因为哪边都有可能是有序的结果。假设原数组是{1,2,3,3,3,3,3}，那么旋转之后有可能是{3,3,3,3,3,1,2}，或者{3,1,2,3,3,3,3}，**这样的我们判断左边缘和中心的时候都是3，如果我们要寻找1或者2，我们并不知道应该跳向哪一半。**==解决的办法只能是对边缘移动一步，直到边缘和中间不在相等或者相遇，这就导致了会有不能切去一半的可能。==所以最坏情况（比如全部都是一个元素，或者只有一个元素不同于其他元素，而他就在最后一个）就会出现每次移动一步，总共是n步，算法的时间复杂度变成O(n)

![](https://raw.githubusercontent.com/bovane/md_images/master/20190423213616.png)

```c++
class Solution {
public:
  bool search(int A[], int n, int target) {
    int lo = 0, hi = n-1;
    int mid = 0;
    while(lo<hi){
          mid=(lo+hi)/2; // 切一半，寻找mid
          if(A[mid]==target) return true;
          if(A[mid]>A[hi]){
              if(A[mid]>target && A[lo] <= target)
                  hi = mid;
              else 
                  lo = mid + 1;
          }else if(A[mid] < A[hi]){
              if(A[mid]<target && A[hi] >= target)
                  lo = mid + 1;
              else 
                  hi = mid;
          }else{
              hi--;
          }
          
    }
    return A[lo] == target ? true : false;
  }
};
```

```python
class Solution:
    def search(self, nums, target):
        if not nums:
            return False
        low = 0
        high = len(nums) - 1
        while low <= high:
            while low < high and nums[low] == nums[high]:
                # 这样的目的是为了能准确判断mid位置，所以算法的最坏时间复杂度为O(n)
                # 一串重复数字
                low += 1                  
            mid = (low+high)//2
            if target == nums[mid]:
                return True         
            elif nums[mid] >= nums[low]: #高区
                if nums[low] <= target < nums[mid]:  
                    high = mid - 1
                else:
                    low = mid + 1
            elif nums[mid] <= nums[high]:  #低区
                if nums[mid] < target <= nums[high]:
                    low = mid + 1
                else:
                    high = mid - 1
        return False
```

## SUMMARY



### Related Knowledge

- Rotated array
- 二分搜索