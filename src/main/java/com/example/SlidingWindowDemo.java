package com.example;

import java.util.*;

/**
 * Interactive Demonstration of Sliding Window Techniques
 * 
 * This class provides step-by-step visualization of how sliding window
 * algorithms work, making it easier to understand the concepts.
 */
public class SlidingWindowDemo {
    
    /**
     * Demonstrates the longest substring without repeating characters
     * with detailed step-by-step output
     */
    public static void demonstrateLongestSubstringWithoutRepeating(String s) {
        System.out.println("=== Longest Substring Without Repeating Characters ===");
        System.out.println("Input: \"" + s + "\"");
        System.out.println();
        
        if (s == null || s.isEmpty()) {
            System.out.println("Result: 0 (empty string)");
            return;
        }
        
        Set<Character> window = new HashSet<>();
        int left = 0;
        int maxLength = 0;
        String maxSubstring = "";
        
        System.out.println("Step-by-step execution:");
        System.out.println("Format: [left, right] window={characters} maxLength=length substring=\"...\"");
        System.out.println();
        
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            
            System.out.print("Step " + (right + 1) + ": ");
            System.out.print("right=" + right + ", char='" + currentChar + "' ");
            
            // Shrink window from left until no duplicates
            while (window.contains(currentChar)) {
                System.out.print("(duplicate found, removing '" + s.charAt(left) + "' from left) ");
                window.remove(s.charAt(left));
                left++;
            }
            
            window.add(currentChar);
            
            // Update maximum
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                maxSubstring = s.substring(left, right + 1);
            }
            
            // Display current state
            System.out.print("[" + left + ", " + right + "] ");
            System.out.print("window=" + window + " ");
            System.out.print("maxLength=" + maxLength + " ");
            System.out.print("substring=\"" + s.substring(left, right + 1) + "\"");
            
            if (right - left + 1 == maxLength) {
                System.out.print(" ← NEW MAX!");
            }
            
            System.out.println();
        }
        
        System.out.println();
        System.out.println("Final Result:");
        System.out.println("Maximum length: " + maxLength);
        System.out.println("Longest substring: \"" + maxSubstring + "\"");
        System.out.println();
    }
    
    /**
     * Demonstrates the longest substring with at most K distinct characters
     * with detailed step-by-step output
     */
    public static void demonstrateLongestSubstringWithKDistinct(String s, int k) {
        System.out.println("=== Longest Substring with At Most " + k + " Distinct Characters ===");
        System.out.println("Input: \"" + s + "\", k=" + k);
        System.out.println();
        
        if (s == null || s.isEmpty() || k <= 0) {
            System.out.println("Result: 0 (invalid input)");
            return;
        }
        
        Map<Character, Integer> charCount = new HashMap<>();
        int left = 0;
        int maxLength = 0;
        String maxSubstring = "";
        
        System.out.println("Step-by-step execution:");
        System.out.println("Format: [left, right] distinct=count maxLength=length substring=\"...\"");
        System.out.println();
        
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            charCount.put(currentChar, charCount.getOrDefault(currentChar, 0) + 1);
            
            System.out.print("Step " + (right + 1) + ": ");
            System.out.print("right=" + right + ", char='" + currentChar + "' ");
            
            // Shrink window if we have more than k distinct characters
            while (charCount.size() > k) {
                char leftChar = s.charAt(left);
                System.out.print("(too many distinct chars, removing '" + leftChar + "' from left) ");
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                if (charCount.get(leftChar) == 0) {
                    charCount.remove(leftChar);
                }
                left++;
            }
            
            // Update maximum
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                maxSubstring = s.substring(left, right + 1);
            }
            
            // Display current state
            System.out.print("[" + left + ", " + right + "] ");
            System.out.print("distinct=" + charCount.size() + " ");
            System.out.print("maxLength=" + maxLength + " ");
            System.out.print("substring=\"" + s.substring(left, right + 1) + "\"");
            
            if (right - left + 1 == maxLength) {
                System.out.print(" ← NEW MAX!");
            }
            
            System.out.println();
        }
        
        System.out.println();
        System.out.println("Final Result:");
        System.out.println("Maximum length: " + maxLength);
        System.out.println("Longest substring: \"" + maxSubstring + "\"");
        System.out.println();
    }
    
    /**
     * Demonstrates the minimum window substring with detailed output
     */
    public static void demonstrateMinWindow(String s, String t) {
        System.out.println("=== Minimum Window Substring ===");
        System.out.println("Input: s=\"" + s + "\", t=\"" + t + "\"");
        System.out.println();
        
        if (s == null || t == null || s.isEmpty() || t.isEmpty()) {
            System.out.println("Result: \"\" (invalid input)");
            return;
        }
        
        // Count characters in t
        Map<Character, Integer> targetCount = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetCount.put(c, targetCount.getOrDefault(c, 0) + 1);
        }
        
        System.out.println("Target character counts: " + targetCount);
        System.out.println();
        
        int required = targetCount.size();
        int formed = 0;
        int left = 0;
        int minLeft = 0;
        int minLength = Integer.MAX_VALUE;
        
        Map<Character, Integer> windowCount = new HashMap<>();
        
        System.out.println("Step-by-step execution:");
        System.out.println("Format: [left, right] formed/required window=\"...\" minLength=length");
        System.out.println();
        
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            windowCount.put(currentChar, windowCount.getOrDefault(currentChar, 0) + 1);
            
            System.out.print("Step " + (right + 1) + ": ");
            System.out.print("right=" + right + ", char='" + currentChar + "' ");
            
            // Check if current character's count matches target
            if (targetCount.containsKey(currentChar) && 
                windowCount.get(currentChar).intValue() == targetCount.get(currentChar).intValue()) {
                formed++;
                System.out.print("(formed=" + formed + ") ");
            }
            
            // Try to contract window from left
            while (left <= right && formed == required) {
                char leftChar = s.charAt(left);
                
                System.out.print("(contracting from left, removing '" + leftChar + "') ");
                
                // Update minimum window if current is smaller
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minLeft = left;
                    System.out.print("(NEW MIN WINDOW!) ");
                }
                
                // Remove left character from window
                windowCount.put(leftChar, windowCount.get(leftChar) - 1);
                if (targetCount.containsKey(leftChar) && 
                    windowCount.get(leftChar) < targetCount.get(leftChar)) {
                    formed--;
                }
                
                left++;
            }
            
            // Display current state
            System.out.print("[" + left + ", " + right + "] ");
            System.out.print("formed=" + formed + "/" + required + " ");
            System.out.print("window=\"" + s.substring(left, right + 1) + "\" ");
            System.out.print("minLength=" + (minLength == Integer.MAX_VALUE ? "∞" : minLength));
            
            System.out.println();
        }
        
        System.out.println();
        System.out.println("Final Result:");
        if (minLength == Integer.MAX_VALUE) {
            System.out.println("No valid window found");
        } else {
            String result = s.substring(minLeft, minLeft + minLength);
            System.out.println("Minimum window: \"" + result + "\"");
            System.out.println("Length: " + minLength);
        }
        System.out.println();
    }
    
    /**
     * Creates a visual representation of the sliding window
     */
    public static void visualizeWindow(String s, int left, int right, String title) {
        System.out.println(title);
        System.out.println("String: " + s);
        
        // Create visual representation
        StringBuilder visual = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i == left && i == right) {
                visual.append("[").append(s.charAt(i)).append("]");
            } else if (i == left) {
                visual.append("[").append(s.charAt(i));
            } else if (i == right) {
                visual.append(s.charAt(i)).append("]");
            } else if (i > left && i < right) {
                visual.append(s.charAt(i));
            } else {
                visual.append(" ");
            }
        }
        
        System.out.println("Window: " + visual.toString());
        System.out.println("       " + " ".repeat(left) + "^".repeat(right - left + 1));
        System.out.println();
    }
    
    /**
     * Interactive demonstration menu
     */
    public static void runInteractiveDemo() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Interactive Sliding Window Demo ===");
        System.out.println("Choose a demonstration:");
        System.out.println("1. Longest Substring Without Repeating Characters");
        System.out.println("2. Longest Substring with K Distinct Characters");
        System.out.println("3. Minimum Window Substring");
        System.out.println("4. Run all examples");
        System.out.println("5. Exit");
        System.out.print("Enter your choice (1-5): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        switch (choice) {
            case 1:
                System.out.print("Enter a string: ");
                String input1 = scanner.nextLine();
                demonstrateLongestSubstringWithoutRepeating(input1);
                break;
                
            case 2:
                System.out.print("Enter a string: ");
                String input2 = scanner.nextLine();
                System.out.print("Enter k (number of distinct characters): ");
                int k = scanner.nextInt();
                demonstrateLongestSubstringWithKDistinct(input2, k);
                break;
                
            case 3:
                System.out.print("Enter string s: ");
                String s = scanner.nextLine();
                System.out.print("Enter string t (target): ");
                String t = scanner.nextLine();
                demonstrateMinWindow(s, t);
                break;
                
            case 4:
                runAllExamples();
                break;
                
            case 5:
                System.out.println("Goodbye!");
                return;
                
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        
        scanner.close();
    }
    
    /**
     * Runs all example demonstrations
     */
    public static void runAllExamples() {
        System.out.println("=== Running All Examples ===\n");
        
        // Example 1: Longest Substring Without Repeating Characters
        demonstrateLongestSubstringWithoutRepeating("abcabcbb");
        
        // Example 2: Longest Substring with K Distinct Characters
        demonstrateLongestSubstringWithKDistinct("eceba", 2);
        
        // Example 3: Minimum Window Substring
        demonstrateMinWindow("ADOBECODEBANC", "ABC");
        
        // Example 4: Visual representation
        System.out.println("=== Visual Window Representation ===");
        visualizeWindow("hello world", 2, 6, "Window from index 2 to 6");
        visualizeWindow("abcdefgh", 1, 4, "Window from index 1 to 4");
        
        // Example 5: Edge cases
        System.out.println("=== Edge Cases ===");
        demonstrateLongestSubstringWithoutRepeating("");
        demonstrateLongestSubstringWithoutRepeating("a");
        demonstrateLongestSubstringWithKDistinct("aaaaa", 1);
    }
    
    /**
     * Main method to run demonstrations
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("interactive")) {
            runInteractiveDemo();
        } else {
            runAllExamples();
        }
    }
}
