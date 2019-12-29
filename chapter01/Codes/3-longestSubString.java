class Solution {

	public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)// 取子串需要O(nxn)
            for (int j = i + 1; j <= n; j++)// 两层循环逐次取字符
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
        return ans;
    }
	// 判断字符串是否有重复字符，利用的hashSet。
	// 该判断方法的原理是：依次取出字符，放入hashSet，
	// 对于新的字符，每次查询一次是否在集合里，若在则重复，返回false
    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);// 取出对应字符
            if (set.contains(ch)) return false;//检查新字符是否存在于集合
            set.add(ch);// 将字符加入hashSet
        }
        return true;
    }

     public int lengthOfLongestSubstring(String s) {

        int n = s.length();
        Set<Character> set = new HashSet<>();// 存储滑窗[i,j]
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){ //如果滑窗j不存在于现在的字符串中，
                							 // 继续增大j,先将j的后面一位加入Set中
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);// 计算新的长度
            }
            else { // 以当前字母i开始的最大长度已经找到并存储到 ans中
                set.remove(s.charAt(i++));// 换i,i向前移动一步
            }
        }
        return ans;
    }

    public int lengthOfLongestSubstring(String s) {

        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character 
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) { // j 持续前移，直到出现重复字符
                i = Math.max(map.get(s.charAt(j)), i); // 计算i的位置
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1); // use（value，j+1） as （key，value）
        }
        return ans;
    }
	
}