class Solution {
	
	public int removeDuplicates(int[] nums) {
	    if (nums.length == 0)
	        return 0; // 异常情况排除
	    int i = 0;
	    for (int j=1; j < nums.length; j++) { // 将j作为循环变量，对数组进行一次遍历
	        if (nums[j] != nums[i]) { // 若后续元素与之前的元素不重复，默认第一次出现为不重复
	            i++;
	            nums[i] = nums[j]; // 相当于存储新数组，变量i记录不重复的元素
	        }
	    }
	    return i+1;
	}
}