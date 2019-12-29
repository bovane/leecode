class Solution {
    
	public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10; // pop操作
            x /= 10;
			// 溢出情况讨论，分为正负，这就是需要理解的地方。
			// 第二种情况需要理解。
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop; // push操作
        }
        return rev;
}