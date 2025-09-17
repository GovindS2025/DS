package com.leetcode.blind75.arrays;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * LeetCode 1: Two Sum - Functional Programming Approach
 * 
 * This class demonstrates multiple functional programming approaches to solve
 * the Two Sum problem using Java 8+ Stream API and functional interfaces.
 * 
 * Problem: Given an array of integers nums and an integer target, return indices 
 * of the two numbers such that they add up to target.
 * 
 * @author LeetCode Blind 75 Solutions
 * @version 1.0
 */
public class TwoSumStream {
    
    /**
     * Approach 1: Functional with IntStream and Optional
     * 
     * Uses IntStream to generate all possible pairs and find the first match.
     * This approach is more declarative but less efficient for large arrays.
     * 
     * Time Complexity: O(n²) - Still nested iteration
     * Space Complexity: O(1) - No extra space
     * 
     * @param nums input array
     * @param target target sum
     * @return indices of the two numbers that sum to target
     */
    public static int[] twoSumFunctional(int[] nums, int target) {
        return IntStream.range(0, nums.length)
                .boxed()
                .flatMap(i -> IntStream.range(i + 1, nums.length)
                        .boxed()
                        .map(j -> new int[]{i, j}))
                .filter(pair -> nums[pair[0]] + nums[pair[1]] == target)
                .findFirst()
                .orElse(new int[0]);
    }
    
    /**
     * Approach 2: Stream with HashMap (Optimal Functional)
     * 
     * Uses Stream API with a HashMap for O(n) time complexity.
     * This is the most efficient functional approach.
     * 
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(n) - HashMap storage
     * 
     * @param nums input array
     * @param target target sum
     * @return indices of the two numbers that sum to target
     */
    public static int[] twoSumStreamOptimal(int[] nums, int target) {
        Map<Integer, Integer> numToIndex = new HashMap<>();
        
        return IntStream.range(0, nums.length)
                .boxed()
                .filter(i -> {
                    int complement = target - nums[i];
                    if (numToIndex.containsKey(complement)) {
                        return true;
                    }
                    numToIndex.put(nums[i], i);
                    return false;
                })
                .mapToInt(i -> {
                    int complement = target - nums[i];
                    return numToIndex.get(complement);
                })
                .mapToObj(complementIndex -> new int[]{complementIndex, 
                    IntStream.range(0, nums.length)
                            .filter(i -> nums[i] == target - nums[complementIndex] && i != complementIndex)
                            .findFirst()
                            .orElse(-1)})
                .findFirst()
                .orElse(new int[0]);
    }
    
    /**
     * Approach 3: Pure Functional with Collectors
     * 
     * Uses Collectors.groupingBy to group numbers by their values,
     * then finds pairs that sum to target.
     * 
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(n) - HashMap storage
     * 
     * @param nums input array
     * @param target target sum
     * @return indices of the two numbers that sum to target
     */
    public static int[] twoSumWithCollectors(int[] nums, int target) {
        Map<Integer, List<Integer>> valueToIndices = IntStream.range(0, nums.length)
                .boxed()
                .collect(Collectors.groupingBy(i -> nums[i]));
        
        return IntStream.range(0, nums.length)
                .boxed()
                .flatMap(i -> {
                    int complement = target - nums[i];
                    return valueToIndices.getOrDefault(complement, Collections.emptyList())
                            .stream()
                            .filter(j -> !j.equals(i))
                            .map(j -> new int[]{i, j});
                })
                .findFirst()
                .orElse(new int[0]);
    }
    
    /**
     * Approach 4: Functional with AtomicReference (Thread-Safe)
     * 
     * Uses AtomicReference to maintain state in a functional way.
     * This approach is thread-safe and purely functional.
     * 
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(n) - HashMap storage
     * 
     * @param nums input array
     * @param target target sum
     * @return indices of the two numbers that sum to target
     */
    public static int[] twoSumAtomic(int[] nums, int target) {
        AtomicReference<Map<Integer, Integer>> numToIndex = new AtomicReference<>(new HashMap<>());
        
        return IntStream.range(0, nums.length)
                .boxed()
                .map(i -> {
                    int complement = target - nums[i];
                    Map<Integer, Integer> map = numToIndex.get();
                    
                    if (map.containsKey(complement)) {
                        return Optional.of(new int[]{map.get(complement), i});
                    }
                    
                    Map<Integer, Integer> newMap = new HashMap<>(map);
                    newMap.put(nums[i], i);
                    numToIndex.set(newMap);
                    return Optional.<int[]>empty();
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElse(new int[0]);
    }
    
    /**
     * Approach 5: Recursive Functional (Divide and Conquer)
     * 
     * Uses recursion with functional programming principles.
     * This approach demonstrates functional recursion patterns.
     * 
     * Time Complexity: O(n²) - Recursive approach
     * Space Complexity: O(n) - Recursion stack
     * 
     * @param nums input array
     * @param target target sum
     * @return indices of the two numbers that sum to target
     */
    public static int[] twoSumRecursive(int[] nums, int target) {
        return twoSumHelper(nums, target, 0, 1);
    }
    
    private static int[] twoSumHelper(int[] nums, int target, int i, int j) {
        if (i >= nums.length - 1) {
            return new int[0];
        }
        if (j >= nums.length) {
            return twoSumHelper(nums, target, i + 1, i + 2);
        }
        if (nums[i] + nums[j] == target) {
            return new int[]{i, j};
        }
        return twoSumHelper(nums, target, i, j + 1);
    }
    
    /**
     * Approach 6: Parallel Stream (Multi-threaded)
     * 
     * Uses parallel streams for potentially faster execution on large arrays.
     * Note: This approach may not be faster due to overhead, but demonstrates
     * parallel processing concepts.
     * 
     * Time Complexity: O(n²) - Parallel nested iteration
     * Space Complexity: O(1) - No extra space
     * 
     * @param nums input array
     * @param target target sum
     * @return indices of the two numbers that sum to target
     */
    public static int[] twoSumParallel(int[] nums, int target) {
        return IntStream.range(0, nums.length)
                .parallel()
                .boxed()
                .flatMap(i -> IntStream.range(i + 1, nums.length)
                        .parallel()
                        .boxed()
                        .map(j -> new int[]{i, j}))
                .filter(pair -> nums[pair[0]] + nums[pair[1]] == target)
                .findFirst()
                .orElse(new int[0]);
    }
    
    /**
     * Approach 7: Functional with Custom Collector
     * 
     * Creates a custom collector that finds the first pair that sums to target.
     * This demonstrates advanced functional programming techniques.
     * 
     * Time Complexity: O(n) - Single pass
     * Space Complexity: O(n) - HashMap storage
     * 
     * @param nums input array
     * @param target target sum
     * @return indices of the two numbers that sum to target
     */
    public static int[] twoSumCustomCollector(int[] nums, int target) {
        return IntStream.range(0, nums.length)
                .boxed()
                .collect(new TwoSumCollector(nums, target));
    }
    
    /**
     * Custom Collector for Two Sum Problem
     * 
     * This collector maintains a HashMap and returns the first valid pair found.
     */
    private static class TwoSumCollector implements Collector<Integer, Map<Integer, Integer>, int[]> {
        private final int[] nums;
        private final int target;
        private int[] result = new int[0];
        private boolean found = false;
        
        public TwoSumCollector(int[] nums, int target) {
            this.nums = nums;
            this.target = target;
        }
        
        @Override
        public Supplier<Map<Integer, Integer>> supplier() {
            return HashMap::new;
        }
        
        @Override
        public BiConsumer<Map<Integer, Integer>, Integer> accumulator() {
            return (map, i) -> {
                if (!found) {
                    int complement = target - nums[i];
                    if (map.containsKey(complement)) {
                        result = new int[]{map.get(complement), i};
                        found = true;
                    } else {
                        map.put(nums[i], i);
                    }
                }
            };
        }
        
        @Override
        public BinaryOperator<Map<Integer, Integer>> combiner() {
            return (map1, map2) -> {
                map1.putAll(map2);
                return map1;
            };
        }
        
        @Override
        public Function<Map<Integer, Integer>, int[]> finisher() {
            return map -> result;
        }
        
        @Override
        public Set<Characteristics> characteristics() {
            return Set.of(Characteristics.UNORDERED);
        }
    }
    
    /**
     * Performance comparison method
     * 
     * Compares the performance of different functional approaches.
     * 
     * @param nums input array
     * @param target target sum
     */
    public static void comparePerformance(int[] nums, int target) {
        System.out.println("=== Performance Comparison ===");
        System.out.println("Array size: " + nums.length);
        System.out.println("Target: " + target);
        System.out.println();
        
        // Test each approach
        String[] approaches = {
            "Functional (IntStream)",
            "Stream Optimal",
            "With Collectors",
            "Atomic Reference",
            "Recursive",
            "Parallel Stream",
            "Custom Collector"
        };
        
        Supplier<int[]>[] functions = new Supplier[] {
            () -> twoSumFunctional(nums, target),
            () -> twoSumStreamOptimal(nums, target),
            () -> twoSumWithCollectors(nums, target),
            () -> twoSumAtomic(nums, target),
            () -> twoSumRecursive(nums, target),
            () -> twoSumParallel(nums, target),
            () -> twoSumCustomCollector(nums, target)
        };
        
        for (int i = 0; i < approaches.length; i++) {
            long startTime = System.nanoTime();
            int[] result = functions[i].get();
            long endTime = System.nanoTime();
            
            System.out.printf("%-20s: %8d ns, Result: %s%n", 
                approaches[i], 
                endTime - startTime,
                Arrays.toString(result));
        }
    }
    
    /**
     * Main method to demonstrate all approaches
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("=== Two Sum - Functional Programming Approaches ===\n");
        
        // Test cases
        int[][] testCases = {
            {2, 7, 11, 15},
            {3, 2, 4},
            {3, 3},
            {-1, -2, -3, -4, -5}
        };
        
        int[] targets = {9, 6, 6, -8};
        
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.toString(testCases[i]) + ", Target: " + targets[i]);
            System.out.println();
            
            // Test all approaches
            System.out.println("1. Functional (IntStream): " + 
                Arrays.toString(twoSumFunctional(testCases[i], targets[i])));
            System.out.println("2. Stream Optimal: " + 
                Arrays.toString(twoSumStreamOptimal(testCases[i], targets[i])));
            System.out.println("3. With Collectors: " + 
                Arrays.toString(twoSumWithCollectors(testCases[i], targets[i])));
            System.out.println("4. Atomic Reference: " + 
                Arrays.toString(twoSumAtomic(testCases[i], targets[i])));
            System.out.println("5. Recursive: " + 
                Arrays.toString(twoSumRecursive(testCases[i], targets[i])));
            System.out.println("6. Parallel Stream: " + 
                Arrays.toString(twoSumParallel(testCases[i], targets[i])));
            System.out.println("7. Custom Collector: " + 
                Arrays.toString(twoSumCustomCollector(testCases[i], targets[i])));
            System.out.println();
        }
        
        // Performance comparison
        int[] largeArray = IntStream.range(0, 1000).toArray();
        int largeTarget = 1998; // 999 + 999
        comparePerformance(largeArray, largeTarget);
        
        System.out.println("\n=== Complexity Analysis ===");
        System.out.println("Approach 1 (Functional): O(n²) time, O(1) space");
        System.out.println("Approach 2 (Stream Optimal): O(n) time, O(n) space");
        System.out.println("Approach 3 (With Collectors): O(n) time, O(n) space");
        System.out.println("Approach 4 (Atomic Reference): O(n) time, O(n) space");
        System.out.println("Approach 5 (Recursive): O(n²) time, O(n) space");
        System.out.println("Approach 6 (Parallel): O(n²) time, O(1) space");
        System.out.println("Approach 7 (Custom Collector): O(n) time, O(n) space");
    }
}
