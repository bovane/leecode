class Solution {
	// 给出有n个元素的整形数组，找出所有使得元素和为0的三个元素（a,b,c)
	// 将3 sum 问题转化为 two sum 问题。

	public List<List<Integer>> threeSum(int[] num) {
	    Arrays.sort(num); 
	    List<List<Integer>> result = new LinkedList<>(); // 存储结果三元组
	    int low,high,sum;
	    for(int i=0; i < num.length-2; i++) {
	        if(i==0 || (i>0 && num[i] != num[i-1])) { // 数组中重复的元素只算一次
	            low = i+1;
	            high = num.length-1;
	            sum = 0 - num[i]; // 将三元组的第一个元素作为索引 0 - num[i] 
	            while(low < high) {
	                if(num[low] + num[high] = sum) {
	                    result.add(Arrays.asList(num[i],num[low],num[high])); 
	                    // 将三元组存入二维LinkList
	                    while(low < high && num[low] == num[low+1]) // 跳过低位重复元素
	                        low++;
	                    while(low<high && num[high] == num[high-1]) // 跳过高位重复元素
	                        high--;
	                    low++;high--; // 如果没有排除，则两边指针朝中间移动
	                }
	                else if(num[low] + num[high] < sum) // 如果值小了，则增大低位指针以增大sum
	                    low++;
	                else // 如果值大了，则减小高位指针以减小sum
	                    high--;
	            }
	        }
	    }
	    return result;
	}

	 public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> list = new LinkedList<>();
        Arrays.sort(nums);
        for (int i = 0;i < nums.length-2;i++) {
            if(i > 0 && nums[i] == nums[i-1]) continue;      //corner case {0,0,0,0}
            twoSum(list, nums, i+1,nums.length-1,-nums[i]);
        }
        return list;
    }
    // 将三元组的第一个元素作为索引进行遍历，采用二分搜索
	private void twoSum(List<List<Integer>> list, int[] nums,int low,int high,int target){
	        
	        if(low > high) return;
	        HashSet<Integer> set = new HashSet<>();
	        for (int i = low; i<= high; i++){
	            if(set.contains(target - nums[i])) {
	                list.add(Arrays.asList(-target,nums[i],target-nums[i]));
	                while(i+1 <= high && nums[i] == nums[i+1]) 
	                    i++;    //corner case {0,0,0,0}
	            }
	            else{
	                set.add(nums[i]);
	            }
	        }
	    }
}