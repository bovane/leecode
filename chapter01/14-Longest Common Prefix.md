[TOC]

# Longest Common Prefix 	⭐⭐

## 问题描述

编写一个函数来查找**字符串数组中最长的公共前缀字符串**。 如果没有公共前缀字符串，则返回空字符串“”。

### example

```
Input: ["flower","flow","flight"]
Output: "fl"
Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
```

## 问题分析

这个题目要求我们从一个字符串数组里面找到，各个字符串的公共前缀。首先我们考虑横向扫描的方式，如图所示

![](https://raw.githubusercontent.com/bovane/md_images/master/20190102232854.png)

```java
 public String longestCommonPrefix(String[] strs) {
    if (strs.length == 0) return "";
    String prefix = strs[0];// 以第一个字符串设为初始值
    for (int i = 1; i < strs.length; i++)
        // indexOf 方法返回一个整数值，指出 String 对象内子字符串的开始位置。
        // 如果没有找到子字符串，则返回-1。
        while (strs[i].indexOf(prefix) != 0) {// 这里是关键,确保prefix是前缀
            prefix = prefix.substring(0, prefix.length() - 1);// 缩短子串
            if (prefix.isEmpty()) return "";
        }        
    return prefix;
}
```

### 复杂度分析：

- Time complexity : $O(S^{2})$, where S is the sum of all characters in all strings.
- Space complexity : O(1)

### 方法二：纵向搜索

想象一下，一个非常短的字符串位于数组的末尾。上述方法仍将进行S次比较。优化此案例的一种方法是进行垂直扫描。所谓纵向扫描就是，在移动到下一列(下一个索引)之前，我们纵向比较所有的字符(具有相同索引)

```java
public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) return "";
    for (int i = 0; i < strs[0].length() ; i++){
        char c = strs[0].charAt(i);// 取出首个字符串的第i个字符
        for (int j = 1; j < strs.length; j ++) {
            // 这里判断是关键，判断长度，判断字符是否相等。
            if (i == strs[j].length() || strs[j].charAt(i) != c)
                return strs[0].substring(0, i);             
        }
    }
    return strs[0];
}
```

### 复杂度分析

- Time complexity : O(S)
- Space complexity : O(1)

## SUMMARY

给定一个字符串数组，要求我们找到最长公共**前缀**字符子串。所谓前缀，便是从第一个字母开始。我们可以采取两种扫描方式，一种是横向扫描，一种为纵向扫描。

### Related knowledge

- 数据结构：字符串数组
- 截取子串如何操作？$str.subString(0,i)$

[原题链接](https://leetcode.com/problems/longest-common-prefix/)