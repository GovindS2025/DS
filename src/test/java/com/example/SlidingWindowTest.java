package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

/**
 * Comprehensive test suite for sliding window string algorithms
 */
public class SlidingWindowTest {
    
    // Note: Static methods are used directly, so instance variables are not needed
    
    @Test
    void testLongestSubstringWithoutRepeating() {
        // Test case 1: Normal case
        assertEquals(3, SlidingWindowStrings.longestSubstringWithoutRepeating("abcabcbb"));
        
        // Test case 2: Single character
        assertEquals(1, SlidingWindowStrings.longestSubstringWithoutRepeating("bbbbb"));
        
        // Test case 3: All different characters
        assertEquals(3, SlidingWindowStrings.longestSubstringWithoutRepeating("pwwkew"));
        
        // Test case 4: Empty string
        assertEquals(0, SlidingWindowStrings.longestSubstringWithoutRepeating(""));
        
        // Test case 5: Single character
        assertEquals(1, SlidingWindowStrings.longestSubstringWithoutRepeating("a"));
        
        // Test case 6: Two characters
        assertEquals(2, SlidingWindowStrings.longestSubstringWithoutRepeating("au"));
    }
    
    @Test
    void testLongestSubstringWithKDistinct() {
        // Test case 1: Normal case
        assertEquals(3, SlidingWindowStrings.longestSubstringWithKDistinct("eceba", 2));
        
        // Test case 2: All same characters
        assertEquals(5, SlidingWindowStrings.longestSubstringWithKDistinct("aaaaa", 1));
        
        // Test case 3: K greater than unique characters
        assertEquals(4, SlidingWindowStrings.longestSubstringWithKDistinct("abcd", 5));
        
        // Test case 4: K is 0
        assertEquals(0, SlidingWindowStrings.longestSubstringWithKDistinct("abc", 0));
        
        // Test case 5: Empty string
        assertEquals(0, SlidingWindowStrings.longestSubstringWithKDistinct("", 2));
    }
    
    @Test
    void testMinWindow() {
        // Test case 1: Normal case
        assertEquals("BANC", SlidingWindowStrings.minWindow("ADOBECODEBANC", "ABC"));
        
        // Test case 2: No valid window
        assertEquals("", SlidingWindowStrings.minWindow("a", "aa"));
        
        // Test case 3: Single character
        assertEquals("a", SlidingWindowStrings.minWindow("a", "a"));
        
        // Test case 4: Empty strings
        assertEquals("", SlidingWindowStrings.minWindow("", "a"));
        assertEquals("", SlidingWindowStrings.minWindow("a", ""));
        
        // Test case 5: Target longer than source
        assertEquals("", SlidingWindowStrings.minWindow("a", "ab"));
    }
    
    @Test
    void testLongestSubstringWithKRepeating() {
        // Test case 1: Normal case
        assertEquals(3, SlidingWindowStrings.longestSubstringWithKRepeating("aaabb", 3));
        
        // Test case 2: All characters meet requirement
        assertEquals(5, SlidingWindowStrings.longestSubstringWithKRepeating("ababbc", 2));
        
        // Test case 3: No valid substring
        assertEquals(0, SlidingWindowStrings.longestSubstringWithKRepeating("ab", 3));
        
        // Test case 4: Single character
        assertEquals(1, SlidingWindowStrings.longestSubstringWithKRepeating("a", 1));
        
        // Test case 5: Empty string
        assertEquals(0, SlidingWindowStrings.longestSubstringWithKRepeating("", 2));
    }
    
    @Test
    void testFindAnagrams() {
        // Test case 1: Normal case
        List<Integer> result1 = SlidingWindowStrings.findAnagrams("cbaebabacd", "abc");
        assertEquals(Arrays.asList(0, 6), result1);
        
        // Test case 2: No anagrams
        List<Integer> result2 = SlidingWindowStrings.findAnagrams("abcd", "ef");
        assertTrue(result2.isEmpty());
        
        // Test case 3: Single character
        List<Integer> result3 = SlidingWindowStrings.findAnagrams("abab", "ab");
        assertEquals(Arrays.asList(0, 1, 2), result3);
        
        // Test case 4: Empty pattern
        List<Integer> result4 = SlidingWindowStrings.findAnagrams("abc", "");
        assertTrue(result4.isEmpty());
        
        // Test case 5: Pattern longer than string
        List<Integer> result5 = SlidingWindowStrings.findAnagrams("ab", "abc");
        assertTrue(result5.isEmpty());
    }
    
    @Test
    void testCheckInclusion() {
        // Test case 1: Normal case - true
        assertTrue(AdvancedSlidingWindow.checkInclusion("ab", "eidbaooo"));
        
        // Test case 2: Normal case - false
        assertFalse(AdvancedSlidingWindow.checkInclusion("ab", "eidboaoo"));
        
        // Test case 3: Single character
        assertTrue(AdvancedSlidingWindow.checkInclusion("a", "ab"));
        
        // Test case 4: Empty strings
        assertFalse(AdvancedSlidingWindow.checkInclusion("", "a"));
        assertFalse(AdvancedSlidingWindow.checkInclusion("a", ""));
        
        // Test case 5: s1 longer than s2
        assertFalse(AdvancedSlidingWindow.checkInclusion("ab", "a"));
    }
    
    @Test
    void testFindSubstring() {
        // Test case 1: Normal case
        String[] words1 = {"foo", "bar"};
        List<Integer> result1 = AdvancedSlidingWindow.findSubstring("barfoothefoobarman", words1);
        assertEquals(Arrays.asList(0, 9), result1);
        
        // Test case 2: No matches
        String[] words2 = {"word", "good"};
        List<Integer> result2 = AdvancedSlidingWindow.findSubstring("wordgoodgoodgoodbestword", words2);
        assertTrue(result2.isEmpty());
        
        // Test case 3: Single word
        String[] words3 = {"a"};
        List<Integer> result3 = AdvancedSlidingWindow.findSubstring("a", words3);
        assertEquals(Arrays.asList(0), result3);
        
        // Test case 4: Empty words array
        String[] words4 = {};
        List<Integer> result4 = AdvancedSlidingWindow.findSubstring("abc", words4);
        assertTrue(result4.isEmpty());
    }
    
    @Test
    void testCharacterReplacement() {
        // Test case 1: Normal case
        assertEquals(4, AdvancedSlidingWindow.characterReplacement("AABABBA", 1));
        
        // Test case 2: No replacements needed
        assertEquals(4, AdvancedSlidingWindow.characterReplacement("AAAA", 0));
        
        // Test case 3: All replacements allowed
        assertEquals(7, AdvancedSlidingWindow.characterReplacement("AABABBA", 7));
        
        // Test case 4: Single character
        assertEquals(1, AdvancedSlidingWindow.characterReplacement("A", 0));
        
        // Test case 5: Empty string
        assertEquals(0, AdvancedSlidingWindow.characterReplacement("", 1));
    }
    
    @Test
    void testMaxSlidingWindow() {
        // Test case 1: Normal case
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] expected1 = {3, 3, 5, 5, 6, 7};
        assertArrayEquals(expected1, AdvancedSlidingWindow.maxSlidingWindow(nums1, 3));
        
        // Test case 2: Single element
        int[] nums2 = {1};
        int[] expected2 = {1};
        assertArrayEquals(expected2, AdvancedSlidingWindow.maxSlidingWindow(nums2, 1));
        
        // Test case 3: Descending array
        int[] nums3 = {7, 2, 4};
        int[] expected3 = {7, 4};
        assertArrayEquals(expected3, AdvancedSlidingWindow.maxSlidingWindow(nums3, 2));
        
        // Test case 4: Empty array
        int[] nums4 = {};
        int[] expected4 = {};
        assertArrayEquals(expected4, AdvancedSlidingWindow.maxSlidingWindow(nums4, 1));
    }
    
    @Test
    void testLongestSubarrayWithSumLessThanOrEqual() {
        // Test case 1: Normal case
        int[] nums1 = {1, 2, 3, 4, 5};
        assertEquals(3, AdvancedSlidingWindow.longestSubarrayWithSumLessThanOrEqual(nums1, 10));
        
        // Test case 2: All elements exceed target
        int[] nums2 = {10, 20, 30};
        assertEquals(0, AdvancedSlidingWindow.longestSubarrayWithSumLessThanOrEqual(nums2, 5));
        
        // Test case 3: Single element
        int[] nums3 = {5};
        assertEquals(1, AdvancedSlidingWindow.longestSubarrayWithSumLessThanOrEqual(nums3, 5));
        
        // Test case 4: Empty array
        int[] nums4 = {};
        assertEquals(0, AdvancedSlidingWindow.longestSubarrayWithSumLessThanOrEqual(nums4, 10));
        
        // Test case 5: Target is 0
        int[] nums5 = {1, 2, 3};
        assertEquals(0, AdvancedSlidingWindow.longestSubarrayWithSumLessThanOrEqual(nums5, 0));
    }
    
    @Test
    void testEdgeCases() {
        // Test null inputs
        assertThrows(NullPointerException.class, () -> {
            SlidingWindowStrings.longestSubstringWithoutRepeating(null);
        });
        
        // Test negative values
        assertEquals(0, SlidingWindowStrings.longestSubstringWithKDistinct("abc", -1));
        assertEquals(0, AdvancedSlidingWindow.longestSubarrayWithSumLessThanOrEqual(new int[]{1, 2, 3}, -1));
        
        // Test zero values
        assertEquals(0, SlidingWindowStrings.longestSubstringWithKDistinct("abc", 0));
        assertEquals(0, AdvancedSlidingWindow.maxSlidingWindow(new int[]{1, 2, 3}, 0));
    }
    
    @Test
    void testPerformance() {
        // Test with larger inputs to ensure O(n) complexity
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append((char) ('a' + (i % 26)));
        }
        String largeString = sb.toString();
        
        long startTime = System.currentTimeMillis();
        int result = SlidingWindowStrings.longestSubstringWithoutRepeating(largeString);
        long endTime = System.currentTimeMillis();
        
        // Should complete in reasonable time (less than 100ms for 1000 characters)
        assertTrue(endTime - startTime < 100);
        assertEquals(26, result); // All 26 characters are unique in the pattern
    }
}
