class Solution {

    public String convert(String s, int numRows) {

        if (numRows == 1) return s;
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());
        // 这里使用min(numRows,s.length)的最小值来代表ZigZag中的非空行
        // 每一行为一个字符数组

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) { // 字符串转为字符数组
            rows.get(curRow).append(c);
		// 检查当前行是否为第一行或者最后一行，改变循环状态
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }
}