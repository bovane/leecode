[TOC]

## Remove Nth Node From End of List

## 问题描述

给定邻接链表，从列表末尾删除第n个节点并返回其头部。

### example

```
Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
```

## 问题分析

该题目是操作邻接表的问题，无论是从头开始移除，还是从结尾开始移除。这里的关键点都是如何操作指针，首先我们需要遍历找到需要删除的节点，然后改变指针。从列表尾开始移除第$n$个节点，一般而言反向移除，不容易理解。我们可以换一下思维，反向移除第$n$个，实际上就是正向移除$L-n+1$个。可以用特值法来找移除第几个的规律。

![](https://raw.githubusercontent.com/bovane/md_images/master/20190104201243.png)

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);// 伪头节点
    dummy.next = head;
    int length  = 0;
    ListNode first = head;
    while (first != null) { // 这个while循环是计算链表的长度，因为不像数组可以直接获得长度
        length++;
        first = first.next;
    }
    length -= n;
    first = dummy;// 为什么要这样？因为链表遍历一次之后指针会指到最后，不会回复，因此需要重新指定
    while (length > 0) { //遍历，找到需要删除的元素
        length--;
        first = first.next;
    }
    first.next = first.next.next;// 改变指针指向
    return dummy.next;
}
```

### 复杂度分析

Time complexity : O(L)

Space complexity : O(1)

### 方法二 One-pass

上面的方法是使用一个指针，两次循环遍历，实际上我们可以使用两个指针、一次遍历解决问题。

![](https://raw.githubusercontent.com/bovane/md_images/master/20190104204148.png)

第一个指针从开头开始按n + 1步向前移动列表，其中n为用户输入，而第二个指针从列表的开头开始。现在，两个指针完全被n个节点分开。我们通过将两个指针一起推进，**直到第一个指针到达最后一个节点来维持这个恒定的间隙**。第二个指针将指向从最后一个开始计数的第n个节点。**我们重新链接第二个指针引用的节点的下一个指针，指向节点的下一个下一个节点。**

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode first = dummy;
    ListNode second = dummy;
    // Advances first pointer so that the gap between first and second is n nodes apart
    for (int i = 1; i <= n + 1; i++) {
        first = first.next;
    }
    // Move first to the end, maintaining the gap
    while (first != null) {
        first = first.next;
        second = second.next;
    }
    second.next = second.next.next;
    return dummy.next;
}
```

## Summary

### RelatedKnowledge



