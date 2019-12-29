[TOC]

# Letter Combinations of a Phone Number

## 问题描述

给定范围在$[2,9]$的两个数字，返回数字包含的所有字母组合，字母组合的长度为2。

### Example

![](https://raw.githubusercontent.com/bovane/md_images/master/20190408120007.png)

```
Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
```

## 问题分析

### My solution

数字和字母之间的对应关系使用HashMap存储，key -> 数字，字母为字符串，然后逐层遍历。形成如图的树形结构。

![](https://raw.githubusercontent.com/bovane/md_images/master/20190408120459.png)

### Codes

```java
class Solution {
    Map<String,String> phone = new HashMap<String,String>() {{
        put("2","abc");
        put("3","efg");
        put("4","ghi");
        put("5","jkl");
        put("6","mno");
        put("7","pqrs");
        put("8","tuv");
        put("9","wxyz");
    }};
    List<String> output = new ArrayList<String>();
    public void backTrack(String combination,String next_digit) {
        // 如果这里没有更多的数字
        if(next_digit.length()==0)
            output.add(comination);
        else {
            String digit = next_digit.substring(0,1); // 取一个字母
            String letters = phone.get(digit); // 根据hash映射得到字母串
            for(int i=0; i<letters.length(); i++) {
                String letter = letters.substring(i,i+1); // 取单独一个字母
                backTrack(combination+letter,next_digit.substring(1)); // 处理下一个字母
            }
        }
    }
    public List<String> letterCombinations(String digits) {
        if(digits.length() != 0)
            backTrack("",digits);
        return output;
    }
}
```

### Code Review

该问题的关键便是如何表示 **层次关系**。在这里**我们巧妙的设计一个递归**来表示不同的层次关系。

## SUMMARY

### RelatedKnowledge



