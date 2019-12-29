class Solution {

	class ListNode { // define the LinkList node
		int value;
		ListNode next;

		ListNode(int x) {
			value = x;
		}
	}
    // use two linklist to save two nonnegetive number (adverse order)
    // eg:[7][2][1] represent 127
    // now we need add two number and return the sum as adverve order

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

		ListNode dummyHead = new ListNode(0); // the result linkList
		ListNode p = l1,q = l2;
		ListNode curr = dummyHead;
		int carry = 0 ; // whether carry to next iteration or not 

		while (p != null || q != null) {
			int x = (p != null) ? p.value : 0;
			int y = (q != null) ? q.value : 0;

			int sum = carry + x + y;
			carry = sum / 10;
			curr.next = new ListNode(sum % 10); // save the result 
			curr = curr.next;	               // and move the pointer

			if (p != null)  // deal with when l1 length isn't equal l2 length
				p = p.next;
			if (q != null)
				q = q.next;
		}
		if (carry > 0). // when the last iteration still need carry 
			curr.next = new ListNode(carry);
		return dummyHead;
	}
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

		if (l1 == null || l2 == null) 	// recursive exportß
			return l1 == null ? l2 : l1;
		int value = l1.value + l2.value;
		ListNode result = new ListNode(value % 10);
		result.next = addTwoNumbers(l1.next, l2.next);
		if (value >= 10)
			result.next = addTwoNumbers(new ListNode(value/10),result.next);
		return result;
	}
}