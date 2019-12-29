class Solution {
	// 给定☎️拨号键，找出输入数字的字符组合。
    Map<String,String> phone = new HashMap<String,String>() {{
        put("2","abc");
        put("3","efg");
        put("4","ghi");
        put("5","jkl");
        put("6","mno");
        put("7","pqrs");
        put("8","tuv");
        put("9","wxyz");
    }};
    
    List<String> output = new ArrayList<String>();
    public void backTrack(String combination,String next_digit) {
        // 如果这里没有更多的数字
        if(next_digit.length()==0)
            output.add(comination);
        else {
            String digit = next_digit.substring(0,1); // 取一个字母
            String letters = phone.get(digit); // 根据hash映射得到字母串
            for(int i=0; i<letters.length(); i++) {
                String letter = letters.substring(i,i+1); // 取单独一个字母
                backTrack(combination+letter,next_digit.substring(1)); // 处理下一个字母
            }
        }
    }
    public List<String> letterCombinations(String digits) {
        if(digits.length() != 0)
            backTrack("",digits);
        return output;
    }
}