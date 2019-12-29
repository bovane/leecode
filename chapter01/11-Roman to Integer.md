[TOC]

# Roman to Integer

## 问题描述

给出罗马数字，转化成对应的阿拉伯数字。

```java
Symbol       Value				Sepcial cases
I             1		I can be placed before V (5) and X (10) to make 4 and 9. 
					X can be placed before L (50) and C (100) to make 40 and 90. 
					C can be placed before D (500) and M (1000) to make 400 and 900.
V             5
X             10
L             50
C             100
D             500
M             1000
```



### example

```
Input: "III"
Output: 3
```

**Example 2:**

```
Input: "IV"
Output: 4
```

**Example 3:**

```
Input: "IX"
Output: 9
```

**Example 4:**

```
Input: "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.
```

**Example 5:**

```
Input: "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
```

## 问题分析

```java
public int romanToInt(String s) {
     int sum=0; // 提前将特殊case，减去
    if(s.indexOf("IV")!=-1){sum-=2;}// IV=4
    if(s.indexOf("IX")!=-1){sum-=2;}// IX=9
    if(s.indexOf("XL")!=-1){sum-=20;}// XL=40
    if(s.indexOf("XC")!=-1){sum-=20;}// XC=90
    if(s.indexOf("CD")!=-1){sum-=200;}// CD=400
    if(s.indexOf("CM")!=-1){sum-=200;}// CM=900
    
    char c[]=s.toCharArray(); // 转为字符数组
    int count=0;
    
   for(;count<=s.length()-1;count++){
       if(c[count]=='M') sum+=1000;
       if(c[count]=='D') sum+=500;
       if(c[count]=='C') sum+=100;
       if(c[count]=='L') sum+=50;
       if(c[count]=='X') sum+=10;
       if(c[count]=='V') sum+=5;
       if(c[count]=='I') sum+=1;    
   }
   return sum;
    
} 
// 这是整数转化为罗马数字
public static String intToRoman(int num) {
    String M[] = {"", "M", "MM", "MMM"};
    String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
}
```

## SUMMARY

### RelatedKnowledge

