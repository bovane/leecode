[TOC]

# Longest Palindromic Substring ⭐⭐⭐

## 问题描述
给出一个字符串s,找到它的最长回文子串，假设字符串最大长度不超过1000。
### example
```
Example 1:
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Example 2:
Input: "cbbd"
Output: "bb"
```

## 问题分析
首先我们需要回文的概念，所谓回文串是指无论正反都是一样的，也就是说是对称字串。人们很容易想到一个**错误的解决办法**：对给定的s进行反转成s'，然后再找到两个串的最长公共字串，把他当作答案。这样的思路是错误的，**因为有的例子找到的最长公共字串不是回文字串。**S = "abacdfgdcaba", S'= "abacdgfdcaba".The longest common substring between S and S' is "abacd". **Clearly, this is not a valid palindrome.**

![](https://raw.githubusercontent.com/bovane/md_images/master/20190420205510.png)

### 暴力破解方法
暴力破解方法需要找到所有的子串，然后再验证每个字串是否为回文子串。暴力破解需要解决两个问题，一是找到所有子串(两个for循环)，二是验证一个字符串是否为回文字符串**(如何验证？)**

### 动态规划
动态规划的观点便是在暴力破解的基础上减少一些不必要的计算。我们观察到一个回文字符满足这样的情况，Consider the case "ababa". If we already knew that "bab" is a palindrome, it is obvious that "ababa" must be a palindrome **since the two left and right end letters are the same.**

这是典型的动态规划问题，我们首先初始化一个和两个字母的回文，然后找到所有三个字母的回文，等等..

```java
public String longestPalindrome(String s) {
    if (s == null || s.length() < 1) return "";
    int start = 0, end = 0;
    for (int i = 0; i < s.length(); i++) {
		// 回文有两种形式的中心，第一种为单个字符，第二个为双字符。
        int len1 = expandAroundCenter(s, i, i); // 回文以单字符为中心
        int len2 = expandAroundCenter(s, i, i + 1); // 回文以双字符为中心
        int len = Math.max(len1, len2);
        if (len > end - start) { // 这里更新是关键
            start = i - (len - 1) / 2; // 为什么更新起点需要-1?
            end = i + len / 2;
        }
    }
    return s.substring(start, end + 1);// subString取不到最后一个元素
}

private int expandAroundCenter(String s, int left, int right) {
    int L = left, R = right;
    while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
        // 如果符合条件，就继续向两边扩张
		L--;
        R++;
    }
    return R - L - 1; // 最小返回的为0
}
```
## SUMMARY

要求一个字符串中的最长回文字符，我们通过动态规划来解决，如果我们以及知道一个字符子串是回文，那么左右分别扩张一位，如果左右扩张的元素相同，那么它也一定为回文。回文有两种形式的中心，第一种为单个字符，第二个为双字符。

### Related knowledge

- 求一个回文字符串 DP法
- 动态规划
- subString()

[原题链接](https://leetcode.com/problems/longest-palindromic-substring/)