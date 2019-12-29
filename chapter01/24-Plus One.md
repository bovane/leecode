[TOC]

# Plus One ⭐

## 问题描述

给出一个非空数组代表一个非负整数，将这个整数+1之后仍然用数组表示。

### example

```
Input: [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Input: [4,3,2,9]
Output: [4,3,3,0]
Explanation: The array represents the integer 4329.
```

## 问题分析

我们可以直接对数组中的数字进行操作，**这里唯一的问题就是处理有进位的情况。**

```java
public int[] plusOne(int[] digits) {
        
    int n = digits.length;
    for(int i=n-1; i>=0; i--) {
        if(digits[i] < 9) {
            digits[i]++;
            return digits;
        }
        
        digits[i] = 0;
    }
    
    int[] newNumber = new int [n+1];
    newNumber[0] = 1;
    
    return newNumber;
}
```

## Summary

这里主要考虑三种情况：没有进位、有一两个进位和全部位置都进位。当没有进位时，直接数字加1然后返回；当有进位是需要将当前位置置为0，再往前一步，直到不产生进位(第一种情况)；每个位置都进位，则需要再循环结束之后，额外申请空间，在数组开头添1。

### related knowledge

- 十进制进位处理（**循环进位应该如何处理？**对于数组自动判断到不进位）