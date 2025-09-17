# LeetCode Blind 75 - Complete Solutions with SteamAPI

## 🎯 Project Overview

This project provides comprehensive solutions to all 75 LeetCode Blind problems, implemented in Java 17 with detailed explanations, diagrams, and complexity analysis. The solutions are organized by category and include multiple approaches for each problem.

## 📁 Project Structure

```
SteamAPI/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── leetcode/
│   │               └── blind75/
│   │                   ├── arrays/          # Array problems (15 problems)
│   │                   ├── strings/         # String problems (8 problems)
│   │                   ├── linkedlist/      # Linked List problems (6 problems)
│   │                   ├── trees/           # Tree problems (15 problems)
│   │                   ├── dp/              # Dynamic Programming (15 problems)
│   │                   ├── graph/           # Graph problems (6 problems)
│   │                   ├── interval/        # Interval problems (3 problems)
│   │                   ├── matrix/          # Matrix problems (2 problems)
│   │                   ├── math/            # Math problems (5 problems)
│   │                   └── LeetCodeBlind75Runner.java
│   └── test/
│       └── java/
│           └── com/
│               └── leetcode/
│                   └── blind75/
│                       └── [test files]
├── docs/
│   ├── ARRAYS_PROBLEMS.md
│   ├── STRINGS_PROBLEMS.md
│   ├── LINKEDLIST_PROBLEMS.md
│   ├── TREES_PROBLEMS.md
│   ├── DP_PROBLEMS.md
│   └── [other category docs]
├── build.gradle
├── README.md
└── PROJECT_OVERVIEW.md
```

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Gradle 8.0 or higher (included via wrapper)

### Installation
```bash
# Clone the repository
git clone <repository-url>
cd SteamAPI

# Build the project
./gradlew build

# Run all solutions
./gradlew run
```

### Running Specific Categories
```bash
# Run array problems
./gradlew runArrays

# Run string problems
./gradlew runStrings

# Run linked list problems
./gradlew runLinkedLists

# Run tree problems
./gradlew runTrees

# Run dynamic programming problems
./gradlew runDP
```

## 📊 Problem Categories

### 1. Arrays (15 problems)
- **Two Sum** - Hash map approach for O(n) time
- **Best Time to Buy and Sell Stock** - Track minimum price
- **Contains Duplicate** - HashSet for O(n) time
- **Product of Array Except Self** - Two passes without division
- **Maximum Subarray** - Kadane's algorithm
- **Maximum Product Subarray** - Track min and max products
- **Find Minimum in Rotated Sorted Array** - Binary search
- **Search in Rotated Sorted Array** - Modified binary search
- **3Sum** - Two pointers with sorting
- **Container With Most Water** - Two pointers from ends
- **Sum of Two Integers** - Bit manipulation
- **Number of 1 Bits** - Bit counting
- **Counting Bits** - Dynamic programming
- **Missing Number** - Mathematical approach
- **Reverse Bits** - Bit manipulation

### 2. Strings (8 problems)
- **Valid Anagram** - Character counting
- **Valid Parentheses** - Stack for matching brackets
- **Valid Palindrome** - Two pointers
- **Longest Substring Without Repeating Characters** - Sliding window
- **Longest Palindromic Substring** - Expand around centers
- **Palindromic Substrings** - Expand around centers
- **Encode and Decode Strings** - Length encoding
- **Group Anagrams** - Sort and group

### 3. Linked Lists (6 problems)
- **Reverse Linked List** - Iterative and recursive
- **Linked List Cycle** - Floyd's cycle detection
- **Merge Two Sorted Lists** - Two pointers
- **Remove Nth Node From End of List** - Two pointers
- **Reorder List** - Find middle, reverse, merge
- **Copy List with Random Pointer** - Hash map approach

### 4. Trees (15 problems)
- **Maximum Depth of Binary Tree** - Recursive DFS
- **Same Tree** - Recursive comparison
- **Invert Binary Tree** - Recursive swapping
- **Binary Tree Maximum Path Sum** - Post-order traversal
- **Binary Tree Level Order Traversal** - BFS with queue
- **Serialize and Deserialize Binary Tree** - Pre-order traversal
- **Subtree of Another Tree** - Recursive comparison
- **Construct Binary Tree from Preorder and Inorder Traversal** - Recursive building
- **Validate Binary Search Tree** - In-order traversal
- **Kth Smallest Element in a BST** - In-order traversal
- **Lowest Common Ancestor of a Binary Search Tree** - BST properties
- **Implement Trie (Prefix Tree)** - Trie data structure
- **Add and Search Word - Data Structure Design** - Trie with wildcard
- **Word Search II** - Trie with DFS
- **Merge k Sorted Lists** - Divide and conquer

### 5. Dynamic Programming (15 problems)
- **Climbing Stairs** - Fibonacci sequence
- **House Robber** - DP with two states
- **House Robber II** - Circular array
- **Decode Ways** - DP with string parsing
- **Unique Paths** - 2D DP
- **Jump Game** - Greedy approach
- **Longest Increasing Subsequence** - DP with binary search
- **Coin Change** - Unbounded knapsack
- **Longest Common Subsequence** - 2D DP
- **Word Break** - DP with string matching
- **Combination Sum IV** - DP with counting
- **House Robber III** - Tree DP
- **Perfect Squares** - DP with square numbers
- **Maximum Product Subarray** - DP with min/max
- **Maximum Sum Subarray** - Kadane's algorithm

## 🎨 Key Patterns and Techniques

### 1. Two Pointers
- **Use when**: Finding pairs, triplets, or palindromes
- **Time Complexity**: O(n)
- **Space Complexity**: O(1)
- **Examples**: Two Sum, 3Sum, Valid Palindrome

### 2. Sliding Window
- **Use when**: Subarray/substring problems with constraints
- **Time Complexity**: O(n)
- **Space Complexity**: O(k) where k is window size
- **Examples**: Longest Substring Without Repeating Characters

### 3. Hash Map/Set
- **Use when**: Lookup operations, counting frequencies
- **Time Complexity**: O(1) average lookup
- **Space Complexity**: O(n)
- **Examples**: Two Sum, Contains Duplicate, Group Anagrams

### 4. Stack
- **Use when**: Matching brackets, nested structures
- **Time Complexity**: O(n)
- **Space Complexity**: O(n)
- **Examples**: Valid Parentheses, Daily Temperatures

### 5. Binary Search
- **Use when**: Sorted arrays, finding specific values
- **Time Complexity**: O(log n)
- **Space Complexity**: O(1)
- **Examples**: Search in Rotated Sorted Array, Find Peak Element

### 6. Dynamic Programming
- **Use when**: Optimization problems, overlapping subproblems
- **Time Complexity**: O(n) to O(n²)
- **Space Complexity**: O(1) to O(n)
- **Examples**: Climbing Stairs, House Robber, Longest Increasing Subsequence

## 📈 Complexity Analysis

### Time Complexity Patterns
- **O(1)**: Constant time operations
- **O(log n)**: Binary search, balanced tree operations
- **O(n)**: Single pass through data
- **O(n log n)**: Sorting, heap operations
- **O(n²)**: Nested loops, some DP problems
- **O(2ⁿ)**: Exponential algorithms (usually avoid)

### Space Complexity Patterns
- **O(1)**: Constant extra space
- **O(log n)**: Recursive call stack
- **O(n)**: Linear extra space
- **O(n²)**: 2D arrays, some DP problems

## 🧪 Testing

### Running Tests
```bash
# Run all tests
./gradlew test

# Run specific test class
./gradlew test --tests "TwoSumTest"

# Run with coverage
./gradlew test jacocoTestReport
```

### Test Structure
Each problem includes:
- Unit tests for all approaches
- Edge case testing
- Performance testing
- Complexity verification

## 📚 Documentation

### Problem Documentation
Each problem includes:
1. **Problem Statement**: Clear description
2. **Approach**: Step-by-step explanation
3. **Algorithm**: Detailed pseudocode
4. **Time Complexity**: O() analysis with explanation
5. **Space Complexity**: O() analysis with explanation
6. **Code Implementation**: Complete Java solution
7. **Test Cases**: Multiple examples
8. **Visual Diagrams**: ASCII art or Mermaid diagrams
9. **Edge Cases**: Important considerations
10. **Follow-up Questions**: Common variations

### Visual Examples
Many problems include visual representations:
- Step-by-step execution traces
- ASCII art diagrams
- Mermaid flowcharts
- Complexity analysis charts

## 🔧 Development

### Adding New Problems
1. Create new class in appropriate category folder
2. Follow naming convention: `ProblemName.java`
3. Include comprehensive documentation
4. Add test cases
5. Update category documentation
6. Update main runner

### Code Style
- Follow Java naming conventions
- Include comprehensive comments
- Use meaningful variable names
- Include complexity analysis
- Add visual examples where helpful

## 🎯 Learning Path

### Beginner (Start Here)
1. Arrays - Two Sum
2. Arrays - Contains Duplicate
3. Strings - Valid Anagram
4. Linked Lists - Reverse Linked List
5. Trees - Maximum Depth of Binary Tree

### Intermediate
1. Arrays - 3Sum
2. Strings - Longest Substring Without Repeating Characters
3. Trees - Validate Binary Search Tree
4. DP - Climbing Stairs
5. Graph - Number of Islands

### Advanced
1. DP - Longest Increasing Subsequence
2. Trees - Serialize and Deserialize Binary Tree
3. Graph - Course Schedule
4. DP - Word Break
5. Trees - Word Search II

## 📞 Support

### Getting Help
- Check the documentation in each problem file
- Review the visual examples and diagrams
- Run the test cases to understand expected behavior
- Refer to the complexity analysis for optimization

### Contributing
1. Fork the repository
2. Create a feature branch
3. Add your solution with proper documentation
4. Include test cases
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- LeetCode for providing the problem set
- The coding interview community for valuable insights
- [YouTube reference](https://www.youtube.com/watch?v=PieZjz2Pyhw&t=4205s) for additional learning resources
- Contributors who helped improve the solutions

## 🚀 Next Steps

1. **Complete the remaining problems** in each category
2. **Practice with variations** of each problem
3. **Implement additional approaches** for each problem
4. **Add more visual examples** and diagrams
5. **Create interactive tutorials** for complex problems
6. **Add performance benchmarks** for different approaches

---

**Happy Coding! 🎉**

*Remember: The key to mastering these problems is understanding the patterns, not memorizing solutions. Focus on the underlying concepts and apply them to new problems.*
