[TOC]

# Construct Binary Tree from Preorder and Inorder Traversal ⭐

## 问题描述

给出二叉树的先序和中序遍历结果，要求编写算法构造出二叉树。

### example

```
preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]

result：
    3
   / \
  9  20
    /  \
   15   7
```

## 问题分析

已知二叉树的先序和中序遍历结果要求还原二叉树，根据先序遍历和中序遍历的特点知：

- 先序遍历第一个结点为根节点
- 确定根节点后，中序遍历中，根结点前后将序列分为两部分，分别为左子树和右子树
- 中序遍历，先输出左孩子，树根结点在中间输出

**先序遍历：**根节点永远在数组第一个（无论是整个二叉树的先序遍历还是二叉树中一个子树的先序遍历），在根节点之后，数组中的元素可以分为左右子树两组（并且元素位置不会交叉）。

**中序遍历：**如果在数组中能确定代表根节点的元素，那么该元素左边的部分为根节点的左子树，右边为右子树。

最开始由先序得到一个根节点（此时是一棵树树），然后通过在中序上找到该节点可以知道这个根节点的左右子树总共有多少节点，再从先序中划分出左右子树，得到两个根节点（得到左右子树两棵树）。**使用分治的思想可以完成。**

```java
class Solution {
    int pStart = 0; // 全局变量，用于控制根结点，构建二叉树
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null ||inorder == null || preorder.length ==0 || inorder.length==0)	// 异常情况处理
            return null;
        Map<Integer, Integer> indexes = new HashMap();
        for(int i = 0; i<inorder.length; i++) {	//建立以中序的HashMap，key=元素值，value=i
            indexes.put(inorder[i],i);     
        }
        return helper(preorder,inorder,0, preorder.length-1, indexes);
        
    }
    private TreeNode helper(int[] preorder, int[] inorder, int pstart,int pend,  Map<Integer, Integer> indexes ){
        if(pstart>pend || pStart>= preorder.length)
            return null;	// 递归出口
        int idx = indexes.get(preorder[pStart]); // 得到当前根节点元素在中序的下标
        TreeNode root = new TreeNode(preorder[pStart]);	// new一个根节点
        pStart++; // 全局变量
        root.left =  helper(preorder, inorder, pstart, idx-1, indexes);//中序下标分为两半
        root.right = helper(preorder, inorder,  idx+1, pend, indexes);  
        return root;
    }    
}

```

```java
public TreeNode buildTree(int[] preorder, int[] inorder) {
    return helper(0, 0, inorder.length - 1, preorder, inorder);
}

public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
    if (preStart > preorder.length - 1 || inStart > inEnd) { // 异常情况处理
        return null;
    }
    TreeNode root = new TreeNode(preorder[preStart]);
    int inIndex = 0; // Index of current root in inorder
    for (int i = inStart; i <= inEnd; i++) {
        if (inorder[i] == root.val) {	// 找到当前root结点在中序遍历中的下标
            							// 用于将inorder分为两部分，便于递归
            inIndex = i;
        }
    }
    root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
    root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
    return root;
}
```



## SUMMARY

关键思路为前序的根节点将中序遍历分为两部分，然后递归的构建二叉树。因此整个解决方案的核心代码为找到根节点在中序遍历中的对应索引(HashMap OR for循环)

### Related knowledge

- 二叉树前序遍历、二叉树中序遍历
- HashMap

[原题链接](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)