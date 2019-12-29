[TOC]

#  ZigZag Conversion

## 问题描述
给出一个字符串和一个数字，需要将这个字符串变换为ZigZag的展示方式。
### example

![](https://i.imgur.com/QHWPPM3.png)
## 问题分析
类似这样的展示题的关键都为找字符串位置的规律，在本题中我们通过迭代的从左到右扫描字符串，我们能够很容易地确定每个字符在ZigZag模式的位置。
```java
class Solution {
    public String convert(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());
// 这里使用min(numRows,s.length)的最小值来代表ZigZag中的非空行

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) { // 字符串转为字符数组
            rows.get(curRow).append(c);
		// 检查当前行是否为第一行或者最后一行，改变循环状态
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }
}
```
复杂度分析

Time Complexity: O(n), where n==len(s)
Space Complexity: O(n)
### 找规律

![找规律](https://i.imgur.com/4KnjVmx.png)
```C++
class Solution {
public:
    string convert(string s, int nRows) {
        if(nRows == 1)return s;
        int len = s.size(), k = 0, interval = (nRows<<1)-2;
        string res(len, ' ');
        for(int j = 0; j < len ; j += interval)//处理第一行
            res[k++] = s[j];
        for(int i = 1; i < nRows-1; i++)//处理中间行
        {
            int inter = (i<<1);
            for(int j = i; j < len; j += inter)
            {
                res[k++] = s[j];
                inter = interval - inter;
            }
        }
        for(int j = nRows-1; j < len ; j += interval)//处理最后一行
            res[k++] = s[j];
        return res;
    }
};
```
## 总结分析

### Relatedknowledge

[原题出自Leetcode](https://leetcode.com/problems/zigzag-conversion/)。

