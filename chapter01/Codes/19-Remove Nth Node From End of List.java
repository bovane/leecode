class Solution {
    // 从邻接表尾移除第n个节点，则意为正向移除 l-n+1 个节点

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

    // 单链表删除节点，则需要找到前一个节点，采取two pointer方法，一次遍历
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
        while (first != null) { // 这里second就代表要删除节点的前一个节点
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
}