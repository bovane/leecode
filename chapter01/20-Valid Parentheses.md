[TOC]

# Valid Parentheses

## 问题描述

   给出一个字符串，其中包括各种括号，判断括号是否合理。合理的标准为：必须使用相同类型的括号关闭左括号；必须以正确的顺序关闭左括号。

### example

```
Input: "()"
Output: true
Input: "()[]{}"
Output: true
Input: "(]"
Output: false
Input: "([)]"
Output: false
Input: "{[]}"
Output: true
```

## 问题分析

括弧匹配问题，我们先从最简单的情况考虑，假设当只有一种括弧时，我们可以简单地通过计数来判断括号是否匹配。**思路如下：** 我们从左边开始依次遍历字符串，处理括号，初始时左括号计数$left=0$,在遍历字符串时，遇到左括号时$left=left+1$,遇到右括号时$left=left-1$。当完全遍历字符串后，我们检查$left$的值，便可以得知括号是否匹配。**那么如何修改才能适合deal多种括号的情况呢？这里我们使用堆栈(stack)**。

关于有效括号表达式的有趣属性是有效表达式的子表达式也应该是有效表达式。【但是不是所有的子表达式】

![example](https://raw.githubusercontent.com/bovane/md_images/master/20190105204815.png)

如果每当我们在表达式中遇到一对匹配的括号时，我们只是从表达式中删除它？在表示问题的递归结构时，堆栈数据结构可以派上用场。我们无法从内到外真正地处理这个问题，因为我们对整体结构一无所知。**但是，堆栈可以帮助我们递归地处理，即从外部到内部。**

```java
class Solution {
  // Hash table that takes care of the mappings.
  private HashMap<Character, Character> mappings;

  // Initialize hash map with mappings. This simply makes the code easier to read.
  public Solution() {
    this.mappings = new HashMap<Character, Character>();
    this.mappings.put(')', '('); // 为什么是这样的键值对？调换一下行不行？不行
    this.mappings.put('}', '{'); // 因为我们是从左到右扫描，遇到右括号，向前查找一下是否有左括号
    this.mappings.put(']', '[');
  }

  public boolean isValid(String s) {

    //初始化堆栈
    Stack<Character> stack = new Stack<Character>();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      // 如果当前的字符为右括号.
      if (this.mappings.containsKey(c)) {

        // 获取当前堆栈顶部元素，如果当前堆栈为空, set a dummy value of '#'
        char topElement = stack.empty() ? '#' : stack.pop();

        // If the mapping for this bracket doesn't match the stack's top element, 、return false.为什么是找最顶部的元素？因为右括号和最近的一个左括号组成一对儿！
        if (topElement != this.mappings.get(c)) {
          return false;
        }
      } 
        else {
        // 如果当前字符为左括号, push to the stack.
        stack.push(c);
      }
    }

    // 如果堆栈最后不为空, then it is an invalid expression.
    return stack.isEmpty();
  }
}
```

### 复杂度分析

Time complexity : O(n)

Space complexity : O(n)

核心思路：遍历字符串，遇到左括号推进堆栈，遇到右括号弹出堆栈顶部字符比较。利用hashmap保存括号之间的对应关系。

## SUMMARY

### RelatedKnowledge

