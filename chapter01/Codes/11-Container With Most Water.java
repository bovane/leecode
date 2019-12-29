public class Solution {

    // 装水多少取决于短的柱子，可以用暴力破解方法将每一种的方案找出来。
    // 采取two pointer方法，two pointer方法是利用矩形高度的增加来弥补宽度减小
    // 当移动时，我们移动更矮的那个柱子
    public int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) // 短的柱子朝长的柱子移动
                l++;
            else
                r--;
        }
        return maxarea;
    }
    public int maxArea(int[] height) {
        int maxarea = 0;
        for (int i = 0; i < height.length; i++)
            for (int j = i + 1; j < height.length; j++) // 从第i+1个柱子到最后一个柱子
                maxarea = Math.max(maxarea, Math.min(height[i], height[j]) * (j - i));
        return maxarea;
    }
}