[TOC]

# Reverse Integer

## 问题描述
给出一个32bit的有符号数字，反转该数的数字部分，并输出。
### example
Example 1:
Input: 123
Output: 321
Example 2:--------------------------
Input: -123
Output: -321
Example 3: -----------------------------
Input: 120
Output: 21
## 问题分析
反转数字，这是一道非常常规的题目，在这个题目中需要注意的是：<font color=red>符号的处理</font>以及原数字最后一位为0，两种情况的处理。**其实这里采用的是 pop and push操作**![](https://i.imgur.com/YVeF8Pp.png)这里讲的在做反转操作时，可能存在溢出情况(32 bit的整数范围为-2147483648~2147483647)
```java
class Solution {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10; // pop操作
            x /= 10;
			// 溢出情况讨论，分为正负，这就是需要理解的地方。
			// 第二种情况需要理解。
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop; // push操作
        }
        return rev;
    }
}
```
## SUMMARY

### RelatedKnowledge