[TOC]

# Median of Two Sorted Arrays ⭐⭐⭐

## 问题描述
给出两个已排序的数组num1和num2,要求找到这两个数组的中位数，假设两个数组不同时为空。
### emaple
```
nums1 = [1, 3]
nums2 = [2]
The median is 2.0
nums1 = [1, 2]
nums2 = [3, 4]
The median is (2 + 3)/2 = 2.5
```

## 问题分析

所谓中位数，便是最中间的那个数，若数组长度为奇数则为最中间那个书，否则为中间两个元素的一半。

```java
class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
```

## SUMMARY

### Related knowledge

[原题链接](https://leetcode.com/problems/median-of-two-sorted-arrays/)

[解题报告](https://www.jianshu.com/p/da4362380890)