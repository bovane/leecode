[TOC]

# Merge Two Sorted Lists

## 问题描述

合并两个已排序的链接列表并将其作为新列表返回。新列表应该通过拼接前两个列表的节点来完成。

### example

```
Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
```

## 问题分析

```java
public ListNode mergeTwoLists(ListNode l1, ListNode l2){
		if(l1 == null) return l2;// 递归方法解决
		if(l2 == null) return l1;// 
		if(l1.val < l2.val){
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else{
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
}
```

## SUMMARY

### RelatedKnowledge

