[TOC]

# Palindrome Number

## 问题描述
给出一个有符号数字，判断是否为回文数字。
### example
Example 1:
Input: 121
Output: true
Example 2:
Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
Example 3:
Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
## 问题分析
数字判断是否回文，最常见的方法就是pop and push操作，判断是否相等，**但是这里需要注意是否有溢出情况！**实际上，我们只需要反转一半的数字便可以判断一个整数是否为回文数字。**关键问题——如何判断反转到一半？** when the original number is **less than** the reversed number, it means we've processed half of the number digits.
```C#
public class Solution {
    public boolean isPalindrome(int num){
   if(num < 0) return  false; 
   int reversed = 0, remainder, original = num;
   while(num != 0) {
        remainder = num % 10; // reversed integer is stored in variable
        reversed = reversed * 10 + remainder; //multiply reversed by 10 then add the remainder so it gets stored at next decimal place.
        num  /= 10;  //the last digit is removed from num after division by 10.
    }
    // palindrome if original and reversed are equal
    return original == reversed;
}
}
```
## SUMMARY

### RelatedKnowledge

