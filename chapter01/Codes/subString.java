class Solution {

	// give a string and you need find the longest substring without duplication
	// the first approach is find all subtring then use uniqe function
	// then choose currently the longest substring 
	// time complexity:O(n^3)
	public int lengthOfLongestSubstring(String s) {

		int n = s.length();
		int ans = 0;

		for (int i = 0; i < n; i++)
			for (int j = i+1; j <= n;j++)
				if(allUnique(s,i,j))
					ans = Math.max(ans, j - i)
		return ans;

	}

	public boolean allUnique(String s, int start, int end) {

		set<Character> set = new HashSet<>(); 
		for (int i = start; i < end; i++) {
			Character ch = s.charAt(i);
			if(set.contains(ch)) // use hashSet to determine dulication
				return false;
			set.add(ch);
		}

	}

	//approach 2 :silding window
	// the key to this problem is how to get string and check unique
	public int lengthOfLongestSubstring(String s) {
		
		int n = s.length;
		Set<Character> set = new HashSet<>();
		int ans = 0,i = 0,j = 0;
		while (i < n && j < n) { // get the substring length
			// try to extend the range [i,j]
			if(!set.contains(s.charAt(j))) {
				set.add(s.charAt(j++));
				ans = Math.max(ans, j-i);
			}
			else
				set.remove(s.charAt(i++)) // control the substring start
		}
		return ans;
	}
}