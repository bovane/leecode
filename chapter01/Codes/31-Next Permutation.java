class Solution {
	public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) { // 从右到左找到第一个破坏升序的数字i
            i--;
        }
        if (i >= 0) { // 如果i有效，则在找过的升序数字里找到最近大于数字i的数字j
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j); // 交换两个数字
        }
        reverse(nums, i + 1); // 将原来升序序列反转，变为降序
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}