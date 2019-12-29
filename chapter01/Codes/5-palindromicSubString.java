class Solution {
    
    public String longestPalindrome(String s) {

        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 回文有两种形式的中心，第一种为单个字符，第二个为双字符。
            int len1 = expandAroundCenter(s, i, i); // 回文以单字符为中心
            int len2 = expandAroundCenter(s, i, i + 1); // 回文以双字符为中心
            int len = Math.max(len1, len2);
            if (len > end - start) { // 这里更新是关键
                start = i - (len - 1) / 2; // 序号比下标多1
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);// subString取不到最后一个元素
    }

    private int expandAroundCenter(String s, int left, int right) {

        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            // 如果符合条件，就继续向两边扩张
            L--;
            R++;
        }
        return R - L - 1; // 最小返回的为0
    }
}