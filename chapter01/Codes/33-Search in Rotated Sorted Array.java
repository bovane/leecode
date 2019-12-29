class Solution {
    // 给定一个升序数组，按照在未知位置的轴旋转，给定value，要求在O（logn）查找
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
         return -1;
    }
     int left = 0;
     int right = nums.length - 1;
     while (left <= right) {
         int mid = left + (right - left) / 2;
         if (nums[mid] == target) {
             return mid;
         } else if (nums[left] <= nums[mid]) {
             if (nums[left] <= target && target  < nums[mid]) {
                 right = mid - 1;
             } else {
                 left = mid + 1;
             }
         } else {
             if (nums[mid] < target && target  <= nums[right]) {
                 left = mid + 1;
             } else {
                 right = mid - 1;
             }
         }
     }
     return -1;
    }
}