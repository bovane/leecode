public class Solution {
  
  // 数字判断回文最简单的方法便是进行push、pop操作，最后两个值相等。
  public boolean isPalindrome(int num){

   if(num < 0) return  false; // 有符号数字则负数不为回文。
   int reversed = 0, remainder, original = num;
   while(num != 0) {
        remainder = num % 10; // reversed integer is stored in variable
        reversed = reversed * 10 + remainder; //multiply reversed by 10 then add the remainder so it gets stored at next decimal place.
        num  /= 10;  //the last digit is removed from num after division by 10.
    }
    // palindrome if original and reversed are equal
    return original == reversed;
  }
}