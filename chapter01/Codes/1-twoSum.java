class Solution {

	// 给定一个数组和数，返回数组中两个数之和的下标
	public int[] twoSum(int[] nums, int target) {
		
		for (int i = 0; i < nums.length; i++)
			for (int j = i+1; j < nums.length; j++) {
				if (nums[j] == target - nums[i])
					return new int[] { i,j };
			}
	}

	// use hashmap save (nums[i],i) as (key,value)
	// so we use map.constains(complement) 
	public int[] twoSumHash(int[] nums, int target) {

		Map<Integer, Integer> hMap = new HashMap<>();
		for (int i = 0; i < nums.length; i++)
			hMap.put(nums[i],i);
		for (int j = 0; j < nums.length; j++) {
			int complement = target - nums[j];	// to find sum
			if (hMap.containsKey(complement) && hMap.get(complement) != j)
				return new int[] { j, hMap.get(complement)};
		}
		throw new IllegalArgumentException("No two sum Solution");
	}
}