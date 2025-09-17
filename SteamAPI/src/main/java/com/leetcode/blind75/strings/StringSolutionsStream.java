package com.leetcode.blind75.strings;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.regex.Pattern;

/**
 * LeetCode Blind 75 - String Problems using Stream API and Functional Programming
 * 
 * This class contains functional programming solutions for all string problems
 * in the LeetCode Blind 75 list using Java 8+ Stream API.
 * 
 * @author LeetCode Blind 75 Solutions
 * @version 1.0
 */
public class StringSolutionsStream {
    
    /**
     * 1. Valid Anagram - Functional Approach
     * 
     * Problem: Check if two strings are anagrams
     * Approach: Stream with character counting
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        
        return s.chars()
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .equals(t.chars()
                        .boxed()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
    }
    
    /**
     * 2. Valid Parentheses - Functional Approach
     * 
     * Problem: Check if string has valid parentheses
     * Approach: Stream with stack simulation
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static boolean isValid(String s) {
        Map<Character, Character> brackets = Map.of(')', '(', ']', '[', '}', '{');
        
        return s.chars()
                .boxed()
                .map(i -> (char) i.intValue())
                .reduce(new Stack<Character>(),
                    (stack, c) -> {
                        if (brackets.containsKey(c)) {
                            if (stack.isEmpty() || !stack.pop().equals(brackets.get(c))) {
                                stack.push('X'); // Mark as invalid
                            }
                        } else {
                            stack.push(c);
                        }
                        return stack;
                    },
                    (s1, s2) -> s1)
                .isEmpty();
    }
    
    /**
     * 3. Valid Palindrome - Functional Approach
     * 
     * Problem: Check if string is palindrome after removing non-alphanumeric characters
     * Approach: Stream with filtering and comparison
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static boolean isPalindrome(String s) {
        String cleaned = s.chars()
                .filter(Character::isLetterOrDigit)
                .map(Character::toLowerCase)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        
        return IntStream.range(0, cleaned.length() / 2)
                .allMatch(i -> cleaned.charAt(i) == cleaned.charAt(cleaned.length() - 1 - i));
    }
    
    /**
     * 4. Longest Substring Without Repeating Characters - Functional Approach
     * 
     * Problem: Find length of longest substring without repeating characters
     * Approach: Stream with sliding window
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(min(m, n))
     */
    public static int lengthOfLongestSubstring(String s) {
        return s.chars()
                .boxed()
                .reduce(new int[]{0, 0, 0}, // [left, right, maxLength]
                    (acc, c) -> {
                        int left = acc[0];
                        int right = acc[1];
                        int maxLength = acc[2];
                        
                        // Find the last occurrence of current character
                        int lastIndex = s.lastIndexOf(c, right);
                        if (lastIndex >= left) {
                            left = lastIndex + 1;
                        }
                        
                        right++;
                        maxLength = Math.max(maxLength, right - left);
                        
                        return new int[]{left, right, maxLength};
                    },
                    (acc1, acc2) -> new int[]{
                        Math.max(acc1[0], acc2[0]),
                        Math.max(acc1[1], acc2[1]),
                        Math.max(acc1[2], acc2[2])
                    })[2];
    }
    
    /**
     * 5. Longest Palindromic Substring - Functional Approach
     * 
     * Problem: Find the longest palindromic substring
     * Approach: Stream with expand around centers
     * 
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        
        return IntStream.range(0, s.length())
                .boxed()
                .flatMap(i -> Stream.of(
                    expandAroundCenter(s, i, i),     // Odd length
                    expandAroundCenter(s, i, i + 1)  // Even length
                ))
                .max(Comparator.comparing(String::length))
                .orElse("");
    }
    
    private static String expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }
    
    /**
     * 6. Palindromic Substrings - Functional Approach
     * 
     * Problem: Count number of palindromic substrings
     * Approach: Stream with expand around centers
     * 
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     */
    public static int countSubstrings(String s) {
        return IntStream.range(0, s.length())
                .boxed()
                .flatMap(i -> Stream.of(
                    countPalindromes(s, i, i),     // Odd length
                    countPalindromes(s, i, i + 1)  // Even length
                ))
                .mapToInt(Integer::intValue)
                .sum();
    }
    
    private static int countPalindromes(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }
    
    /**
     * 7. Encode and Decode Strings - Functional Approach
     * 
     * Problem: Encode and decode list of strings
     * Approach: Stream with length encoding
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static String encode(List<String> strs) {
        return strs.stream()
                .map(s -> s.length() + "#" + s)
                .collect(Collectors.joining());
    }
    
    public static List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        int i = 0;
        
        while (i < s.length()) {
            int j = i;
            while (s.charAt(j) != '#') j++;
            int length = Integer.parseInt(s.substring(i, j));
            result.add(s.substring(j + 1, j + 1 + length));
            i = j + 1 + length;
        }
        
        return result;
    }
    
    /**
     * 8. Group Anagrams - Functional Approach
     * 
     * Problem: Group strings that are anagrams of each other
     * Approach: Stream with grouping by sorted characters
     * 
     * Time Complexity: O(n * m log m)
     * Space Complexity: O(n * m)
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        return Arrays.stream(strs)
                .collect(Collectors.groupingBy(
                    s -> s.chars()
                            .sorted()
                            .collect(StringBuilder::new, 
                                StringBuilder::appendCodePoint, 
                                StringBuilder::append)
                            .toString()
                ))
                .values()
                .stream()
                .collect(Collectors.toList());
    }
    
    /**
     * 9. Longest Common Prefix - Functional Approach
     * 
     * Problem: Find longest common prefix among strings
     * Approach: Stream with character comparison
     * 
     * Time Complexity: O(n * m)
     * Space Complexity: O(1)
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            final int index = i;
            if (Arrays.stream(strs).allMatch(s -> index < s.length() && s.charAt(index) == strs[0].charAt(index))) {
                result.append(strs[0].charAt(i));
            } else {
                break;
            }
        }
        return result.toString();
    }
    
    /**
     * 10. Valid Parentheses with Multiple Types - Functional Approach
     * 
     * Problem: Check if string has valid parentheses with multiple bracket types
     * Approach: Stream with stack simulation
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static boolean isValidMultiple(String s) {
        Map<Character, Character> brackets = Map.of(
            ')', '(', ']', '[', '}', '{', '>', '<'
        );
        
        return s.chars()
                .boxed()
                .map(i -> (char) i.intValue())
                .reduce(new Stack<Character>(),
                    (stack, c) -> {
                        if (brackets.containsKey(c)) {
                            if (stack.isEmpty() || !stack.pop().equals(brackets.get(c))) {
                                stack.push('X'); // Mark as invalid
                            }
                        } else if (brackets.containsValue(c)) {
                            stack.push(c);
                        }
                        return stack;
                    },
                    (s1, s2) -> s1)
                .isEmpty();
    }
    
    /**
     * 11. String Compression - Functional Approach
     * 
     * Problem: Compress string by replacing repeated characters with count
     * Approach: Stream with grouping and counting
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static String compress(String s) {
        if (s.length() <= 1) return s;
        
        StringBuilder result = new StringBuilder();
        char current = s.charAt(0);
        int count = 1;
        
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == current) {
                count++;
            } else {
                result.append(current);
                if (count > 1) result.append(count);
                current = s.charAt(i);
                count = 1;
            }
        }
        
        result.append(current);
        if (count > 1) result.append(count);
        
        return result.toString();
    }
    
    /**
     * 12. Reverse Words in String - Functional Approach
     * 
     * Problem: Reverse the order of words in a string
     * Approach: Stream with splitting and reversing
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static String reverseWords(String s) {
        return Arrays.stream(s.trim().split("\\s+"))
                .collect(Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> {
                        Collections.reverse(list);
                        return list;
                    }
                ))
                .stream()
                .collect(Collectors.joining(" "));
    }
    
    /**
     * 13. Valid Number - Functional Approach
     * 
     * Problem: Check if string is a valid number
     * Approach: Stream with regex validation
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static boolean isNumber(String s) {
        Pattern pattern = Pattern.compile("^[+-]?(\\d+\\.?\\d*|\\.\\d+)([eE][+-]?\\d+)?$");
        return pattern.matcher(s.trim()).matches();
    }
    
    /**
     * 14. Minimum Window Substring - Functional Approach
     * 
     * Problem: Find minimum window in s that contains all characters in t
     * Approach: Stream with sliding window
     * 
     * Time Complexity: O(|s| + |t|)
     * Space Complexity: O(|s| + |t|)
     */
    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";
        
        Map<Character, Long> targetCount = t.chars()
                .boxed()
                .collect(Collectors.groupingBy(
                    c -> (char) c.intValue(),
                    Collectors.counting()
                ));
        
        int required = targetCount.size();
        int formed = 0;
        int left = 0;
        int minLeft = 0;
        int minLength = Integer.MAX_VALUE;
        
        Map<Character, Integer> windowCount = new HashMap<>();
        
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            windowCount.put(currentChar, windowCount.getOrDefault(currentChar, 0) + 1);
            
            if (targetCount.containsKey(currentChar) && 
                windowCount.get(currentChar).intValue() == targetCount.get(currentChar).intValue()) {
                formed++;
            }
            
            while (left <= right && formed == required) {
                char leftChar = s.charAt(left);
                
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minLeft = left;
                }
                
                windowCount.put(leftChar, windowCount.get(leftChar) - 1);
                if (targetCount.containsKey(leftChar) && 
                    windowCount.get(leftChar) < targetCount.get(leftChar)) {
                    formed--;
                }
                
                left++;
            }
        }
        
        return minLength == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLength);
    }
    
    /**
     * 15. Find All Anagrams in a String - Functional Approach
     * 
     * Problem: Find all start indices of p's anagrams in s
     * Approach: Stream with sliding window
     * 
     * Time Complexity: O(|s| + |p|)
     * Space Complexity: O(1)
     */
    public static List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) return Collections.emptyList();
        
        int[] pCount = new int[26];
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }
        
        int[] windowCount = new int[26];
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < s.length(); i++) {
            windowCount[s.charAt(i) - 'a']++;
            
            if (i >= p.length()) {
                windowCount[s.charAt(i - p.length()) - 'a']--;
            }
            
            if (i >= p.length() - 1 && Arrays.equals(pCount, windowCount)) {
                result.add(i - p.length() + 1);
            }
        }
        
        return result;
    }
    
    /**
     * Utility method to demonstrate string problems
     */
    public static void demonstrateStringProblems() {
        System.out.println("=== String Problems - Functional Programming Solutions ===\n");
        
        // Test cases
        String s1 = "anagram", t1 = "nagaram";
        String s2 = "()[]{}";
        String s3 = "A man, a plan, a canal: Panama";
        String s4 = "abcabcbb";
        String s5 = "babad";
        String s6 = "aaa";
        String[] strs1 = {"eat","tea","tan","ate","nat","bat"};
        String[] strs2 = {"flower","flow","flight"};
        
        // 1. Valid Anagram
        System.out.println("1. Valid Anagram:");
        System.out.println("Input: s=\"" + s1 + "\", t=\"" + t1 + "\"");
        System.out.println("Output: " + isAnagram(s1, t1));
        System.out.println();
        
        // 2. Valid Parentheses
        System.out.println("2. Valid Parentheses:");
        System.out.println("Input: \"" + s2 + "\"");
        System.out.println("Output: " + isValid(s2));
        System.out.println();
        
        // 3. Valid Palindrome
        System.out.println("3. Valid Palindrome:");
        System.out.println("Input: \"" + s3 + "\"");
        System.out.println("Output: " + isPalindrome(s3));
        System.out.println();
        
        // 4. Longest Substring Without Repeating Characters
        System.out.println("4. Longest Substring Without Repeating Characters:");
        System.out.println("Input: \"" + s4 + "\"");
        System.out.println("Output: " + lengthOfLongestSubstring(s4));
        System.out.println();
        
        // 5. Longest Palindromic Substring
        System.out.println("5. Longest Palindromic Substring:");
        System.out.println("Input: \"" + s5 + "\"");
        System.out.println("Output: \"" + longestPalindrome(s5) + "\"");
        System.out.println();
        
        // 6. Palindromic Substrings
        System.out.println("6. Palindromic Substrings:");
        System.out.println("Input: \"" + s6 + "\"");
        System.out.println("Output: " + countSubstrings(s6));
        System.out.println();
        
        // 7. Group Anagrams
        System.out.println("7. Group Anagrams:");
        System.out.println("Input: " + Arrays.toString(strs1));
        System.out.println("Output: " + groupAnagrams(strs1));
        System.out.println();
        
        // 8. Longest Common Prefix
        System.out.println("8. Longest Common Prefix:");
        System.out.println("Input: " + Arrays.toString(strs2));
        System.out.println("Output: \"" + longestCommonPrefix(strs2) + "\"");
        System.out.println();
        
        // 9. String Compression
        System.out.println("9. String Compression:");
        System.out.println("Input: \"aabcccccaaa\"");
        System.out.println("Output: \"" + compress("aabcccccaaa") + "\"");
        System.out.println();
        
        // 10. Reverse Words
        System.out.println("10. Reverse Words:");
        System.out.println("Input: \"the sky is blue\"");
        System.out.println("Output: \"" + reverseWords("the sky is blue") + "\"");
        System.out.println();
        
        // 11. Valid Number
        System.out.println("11. Valid Number:");
        System.out.println("Input: \"2e10\"");
        System.out.println("Output: " + isNumber("2e10"));
        System.out.println();
        
        // 12. Minimum Window Substring
        System.out.println("12. Minimum Window Substring:");
        System.out.println("Input: s=\"ADOBECODEBANC\", t=\"ABC\"");
        System.out.println("Output: \"" + minWindow("ADOBECODEBANC", "ABC") + "\"");
        System.out.println();
        
        // 13. Find All Anagrams
        System.out.println("13. Find All Anagrams:");
        System.out.println("Input: s=\"cbaebabacd\", p=\"abc\"");
        System.out.println("Output: " + findAnagrams("cbaebabacd", "abc"));
        System.out.println();
    }
    
    /**
     * Main method
     */
    public static void main(String[] args) {
        demonstrateStringProblems();
    }
}
