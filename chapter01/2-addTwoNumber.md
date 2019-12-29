[TOC]

# Add Two Number     ⭐

## 问题描述
给你两个非空邻接表分别代表两个**非负**的数字，**该数字以相反的顺序存储在邻接表中**，每个节点代表单个数字。现要求加上这两个数字 并 以邻接表的方式返回结果。 **难点：** 进位的处理 和 数字逆序存储。

### Example
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)  两个邻接链表  
Output: 7 -> 0 -> 8   一个邻接链表  
Explanation: 342 + 465 = 807.  

## 问题分析

该问题属于邻接表操作问题，这里需要注意的地方有指针的移动以及进位的检测，还有边界情况的考虑。
![](https://raw.githubusercontent.com/bovane/md_images/master/20190420184121.png)

两两数字相加，分为两种情况，第一种情况有进位，eg:5+7=12,carry=1,此时保存2在当前位置，**carry进入下一次迭代，并且carry的值只能取0、1**,接下来，让我们考虑集中特殊的情况。

![](https://raw.githubusercontent.com/bovane/md_images/master/20190420201503.png)

### 解决方案
第一种方案为常规方案，代码如下
```java
/** 数据结构
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
    }
}
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0); // 假头节点
    ListNode p = l1, q = l2, curr = dummyHead;
    int carry = 0; // 初始carry=0
    while (p != null || q != null) {
        int x = (p != null) ? p.val : 0; // 判断并取值
        int y = (q != null) ? q.val : 0;
        int sum = carry + x + y; 
        carry = sum / 10;
        curr.next = new ListNode(sum % 10); // 生成下一个节点
        curr = curr.next;
        if (p != null) p = p.next;
        if (q != null) q = q.next;
    }
    if (carry > 0) { // 最后检测 carry，满足上表第3种情况。
        curr.next = new ListNode(carry);
    }
    return dummyHead.next;
}
```
第二种方案为递归方案，不建议使用(但是更容易理解)
```java
public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        int value = l1.val + l2.val;
        ListNode result = new ListNode(value % 10);
        result.next = addTwoNumbers2(l1.next, l2.next);
        if (value >= 10) {
            result.next = addTwoNumbers2(new ListNode(value / 10), result.next);
        }
        return result;
    }
```
## SUMMARY
用链表反向存储一个整数，并对链表进行操作。这里**需要处理的进位和借位问题**。eg:5+7=12,carry=1,此时保存2在当前位置，**carry进入下一次迭代，并且carry的值只能取0、1**。减法借位也是同样道理，eg:5-7 = 8,carry=-1，8保存在当前位置，carry进入下一次迭代，并且carry的值只能取0,-1。

### Related knowledge

- 数据结构：链表
- 链表的遍历，指针的申请个数
- 加减法如何表示数字进位、借位

[原题链接](https://leetcode.com/articles/add-two-numbers/)



