# Strings Problems - LeetCode Blind 75

## 1. Valid Anagram

### Problem Statement
Check if two strings are anagrams (same characters, different order).

### Approach 1: Sorting
```java
public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) return false;
    
    char[] sChars = s.toCharArray();
    char[] tChars = t.toCharArray();
    Arrays.sort(sChars);
    Arrays.sort(tChars);
    
    return Arrays.equals(sChars, tChars);
}
```

**Time Complexity:** O(n log n) - Sorting
**Space Complexity:** O(1) - In-place sorting

### Approach 2: Character Counting (Optimal)
```java
public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) return false;
    
    int[] count = new int[26];
    
    for (char c : s.toCharArray()) {
        count[c - 'a']++;
    }
    
    for (char c : t.toCharArray()) {
        count[c - 'a']--;
        if (count[c - 'a'] < 0) {
            return false;
        }
    }
    
    return true;
}
```

**Time Complexity:** O(n) - Single pass each string
**Space Complexity:** O(1) - 26 characters max

### Visual Example
```
s = "anagram", t = "nagaram"

Character counting:
Initial count: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]

After counting s:
count: [3,0,0,0,1,0,1,0,0,0,0,0,1,1,0,0,0,1,0,0,0,0,0,0,0,0]
        a b c d e f g h i j k l m n o p q r s t u v w x y z

After subtracting t:
count: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]

All counts are 0, so they are anagrams.
```

## 2. Valid Parentheses

### Problem Statement
Check if string has valid parentheses (matching brackets).

### Approach: Stack
```java
public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    
    for (char c : s.toCharArray()) {
        if (c == '(' || c == '[' || c == '{') {
            stack.push(c);
        } else if (c == ')' || c == ']' || c == '}') {
            if (stack.isEmpty()) return false;
            
            char top = stack.pop();
            if (!isMatching(top, c)) {
                return false;
            }
        }
    }
    
    return stack.isEmpty();
}

private boolean isMatching(char open, char close) {
    return (open == '(' && close == ')') ||
           (open == '[' && close == ']') ||
           (open == '{' && close == '}');
}
```

**Time Complexity:** O(n) - Single pass
**Space Complexity:** O(n) - Stack storage

### Visual Example
```
s = "()[]{}"

Step 1: '(' -> push to stack: ['(']
Step 2: ')' -> pop '(', matches -> stack: []
Step 3: '[' -> push to stack: ['[']
Step 4: ']' -> pop '[', matches -> stack: []
Step 5: '{' -> push to stack: ['{']
Step 6: '}' -> pop '{', matches -> stack: []

Stack is empty, so valid.
```

## 3. Longest Substring Without Repeating Characters

### Problem Statement
Find length of longest substring without repeating characters.

### Approach: Sliding Window
```java
public int lengthOfLongestSubstring(String s) {
    Set<Character> window = new HashSet<>();
    int left = 0;
    int maxLength = 0;
    
    for (int right = 0; right < s.length(); right++) {
        char currentChar = s.charAt(right);
        
        // Shrink window from left until no duplicates
        while (window.contains(currentChar)) {
            window.remove(s.charAt(left));
            left++;
        }
        
        window.add(currentChar);
        maxLength = Math.max(maxLength, right - left + 1);
    }
    
    return maxLength;
}
```

**Time Complexity:** O(n) - Each character visited at most twice
**Space Complexity:** O(min(m,n)) - m is charset size, n is string length

### Visual Example
```
s = "abcabcbb"

Step 1: [a]bcabcbb, window={a}, maxLength=1
Step 2: [ab]cabcbb, window={a,b}, maxLength=2
Step 3: [abc]abcbb, window={a,b,c}, maxLength=3
Step 4: a[bc]abcbb, window={b,c}, maxLength=3 (removed 'a')
Step 5: a[bca]bcbb, window={b,c,a}, maxLength=3
Step 6: ab[ca]bcbb, window={c,a}, maxLength=3 (removed 'b')
Step 7: abc[ab]cbb, window={a,b}, maxLength=3 (removed 'c')
Step 8: abca[b]cbb, window={b}, maxLength=3 (removed 'a')

Result: 3
```

## 4. Longest Palindromic Substring

### Problem Statement
Find the longest palindromic substring.

### Approach: Expand Around Centers
```java
public String longestPalindrome(String s) {
    if (s == null || s.length() < 1) return "";
    
    int start = 0, end = 0;
    
    for (int i = 0; i < s.length(); i++) {
        int len1 = expandAroundCenter(s, i, i);     // Odd length
        int len2 = expandAroundCenter(s, i, i + 1); // Even length
        int len = Math.max(len1, len2);
        
        if (len > end - start) {
            start = i - (len - 1) / 2;
            end = i + len / 2;
        }
    }
    
    return s.substring(start, end + 1);
}

private int expandAroundCenter(String s, int left, int right) {
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
        left--;
        right++;
    }
    return right - left - 1;
}
```

**Time Complexity:** O(n²) - For each center, expand outward
**Space Complexity:** O(1) - Only using variables

### Visual Example
```
s = "babad"

Center at i=0: expand "b" -> len=1
Center at i=1: expand "a" -> len=1
Center at i=2: expand "b" -> len=3 (bab)
Center at i=3: expand "a" -> len=1
Center at i=4: expand "d" -> len=1

Result: "bab"
```

## 5. Group Anagrams

### Problem Statement
Group strings that are anagrams of each other.

### Approach: Sort and Group
```java
public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    
    for (String str : strs) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        String key = String.valueOf(chars);
        
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
    }
    
    return new ArrayList<>(map.values());
}
```

**Time Complexity:** O(n * m log m) - n strings, m average length
**Space Complexity:** O(n * m) - Storage for all strings

### Visual Example
```
strs = ["eat","tea","tan","ate","nat","bat"]

After sorting each string:
"eat" -> "aet"
"tea" -> "aet"
"tan" -> "ant"
"ate" -> "aet"
"nat" -> "ant"
"bat" -> "abt"

Grouping by sorted key:
"aet" -> ["eat","tea","ate"]
"ant" -> ["tan","nat"]
"abt" -> ["bat"]

Result: [["eat","tea","ate"],["tan","nat"],["bat"]]
```

## Key Patterns for String Problems

### 1. Two Pointers
- Use for palindromes, anagrams
- One pointer at start, one at end
- Move based on condition

### 2. Sliding Window
- Use for substring problems
- Expand/contract window
- Track window state

### 3. Hash Map/Set
- Use for character counting
- Group similar strings
- Fast lookup operations

### 4. Stack
- Use for matching brackets
- Nested structures
- LIFO operations

### 5. String Manipulation
- Character arrays
- StringBuilder for efficiency
- Regular expressions

## Common Edge Cases

1. **Empty string**: Check length before processing
2. **Single character**: Handle separately
3. **All same characters**: Consider duplicates
4. **Case sensitivity**: Convert to lowercase
5. **Special characters**: Handle non-alphabetic
6. **Unicode characters**: Use proper encoding

## Time Complexity Cheat Sheet

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Character Counting | O(n) | O(1) |
| Sorting | O(n log n) | O(1) |
| Sliding Window | O(n) | O(k) |
| Two Pointers | O(n) | O(1) |
| Stack Operations | O(n) | O(n) |

## Space Complexity Cheat Sheet

| Data Structure | Space Complexity | Notes |
|----------------|------------------|-------|
| Character Array | O(n) | String length |
| Hash Map | O(n) | Character counts |
| Stack | O(n) | Worst case all brackets |
| StringBuilder | O(n) | Result string |
| Variables | O(1) | Constant space |
