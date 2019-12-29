[TOC]

# Longest Substring Without Repeating Characters ⭐⭐

## 问题描述
给出一个字符串，找到没有重复字符的最长子串，返回其长度。
### example
```
Example 1:
Input: "abcabcbb"  
Output: 3   
Explanation: The answer is "abc", with the length of 3. 

Example 2:
Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
```


## 问题分析 
### 方法1 暴力破解
类似这种最长、最短字串，我们都可以用暴力破解的方法进行逐个遍历，找到答案。在这个题中，暴==力破解的方法便是检查 每个没有重复字符的字串==。为了实现暴力破解方法，我们需要做两步，第一步便是找出给定字符串的所有子字符串，第二部便是检查子字符串是否重复，若不重复则可以进行长度更新。代码如下：
```java
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)// 取子串需要O(nxn)
            for (int j = i + 1; j <= n; j++)// 两层循环逐次取字符
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
        return ans;
    }
	// 判断字符串是否有重复字符，利用的hashSet。
	// 该判断方法的原理是：依次取出字符，放入hashSet，
	// 对于新的字符，每次查询一次是否在集合里，若在则重复，返回false
    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);// 取出对应字符
            if (set.contains(ch)) return false;//检查新字符是否存在于集合
            set.add(ch);// 将字符加入hashSet
        }
        return true;
    }
}
```
#### 方法1分析
时间复杂度：O(n^3)
空间复杂度:O(min(n,m)). 
缺点：无法提早结束，导致重复计算很多次。因此方法二采用滑窗法，减少重复计算。

### 方法2 滑窗法
如果一个字符串s_{ij}从i到j-1已经检查到没有重复字符串，那么我们只要检查s[j]是否在s_{ij}中，而不是从头开始逐一重复。这里我们采用HashSet作为滑窗，检查s[j]是否在目前的字符串中，只需要常数时间。A sliding window is an abstract concept commonly used in array/string problems. A window is a range of elements in the array/string which usually defined by the start and end indices, i.e. [i, j)。if we slide [i, j)to the right by 11 element, then it becomes [i+1, j+1)。在我们的问题中，We use HashSet to store the characters in current window [i, j) (j=i initially). Then we slide the index j to the right. If it is not in the HashSet, we slide j further. Doing so until s[j] is already in the HashSet. At this point, we found the maximum size of substrings without duplicate characters start with index i. If we do this for all i, we get our answer.代码如下：
```java
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();// 存储滑窗
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){ //如果滑窗j不存在于现在的字符串中，
                							 // 继续增大j,先将j的后面一位加入Set中
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);// 计算新的长度
            }
            else {
                set.remove(s.charAt(i++));// 换i,i向前移动一步
            }
        }
        return ans;
    }
}
```
#### 方法二分析
方法二非常巧妙，==对于处理最长无重复字符的字串，滑窗法是非常棒的思路，利用HashSet实现滑窗。==以后遇到此类，优先使用滑窗法。
时间复杂度：O(n)一次循环，In the worst case each character will be visited twice by i and j。

### 方法三 滑窗法优化
方法二最坏的情况会有2n步，实际上我们可以不用HashSet而用HashMap,来查询一个字符是否存在。代码如下：
```java
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character 
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}
```
## SUMMARY

利用HastSet实现滑窗法求最长无重复子串是非常巧妙的，并且思路也比暴力破解清晰。

### Related knowledge

- 数据结构：HashMap、HashSet
- 检查字串是否有重复字母（利用HashSet）

[原题链接](https://leetcode.com/problems/longest-substring-without-repeating-characters/)