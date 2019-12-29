class Solution {

	// 从字符串数组找到公共最长前缀，首先可以考虑横向扫描方式
	public String longestCommonPrefix(String[] strs) {

	    if (strs.length == 0) return "";
	    String prefix = strs[0];// 以第一个字符串设为初始值
	    for (int i = 1; i < strs.length; i++)
	        // indexOf 方法返回一个整数值，指出 String 对象内子字符串的开始位置。
	        // 如果没有找到子字符串，则返回-1。
	        while (strs[i].indexOf(prefix) != 0) {// 这里是关键需要返回0,确保prefix是前缀
	            prefix = prefix.substring(0, prefix.length() - 1);// 缩短子串，substring取不到最后一个
	            if (prefix.isEmpty()) return "";
	        }        
	    return prefix;
	}

	public String longestCommonPrefix(String[] strs) {
		// 当一个非常短的字符串位于数组的末尾，横向扫描则不理想
		// 纵向扫描意味着在移动到下一列（下个索引）之前，我们纵向比较所有字符串
	    if (strs == null || strs.length == 0) return "";
	    for (int i = 0; i < strs[0].length() ; i++){
	        char c = strs[0].charAt(i);// 取出首个字符串的第i个字符
	        for (int j = 1; j < strs.length; j ++) {
	            // 这里判断是关键，判断长度，判断字符是否相等。
	            if (i == strs[j].length() || strs[j].charAt(i) != c)
	                return strs[0].substring(0, i);             
	        }
	    }
	    return strs[0];
	}
	
}