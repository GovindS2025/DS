package com.example.SteamAPI;
import java.util.*;
public class TwoSum {

    public static int[] main(String[] args) {
        int arr[] = {2,7,11,15};
        int target = 9;

//        int arr1[] = {9,55,23,2,1,10,5,6,7,8};
        //sorted array using stream

        int sorted[] =Arrays.stream(arr).sorted()//sorting the accending order
                .toArray();//converting back to array

int result = Arrays.binarySearch(sorted,target);
        System.out.println("Element found at index: "+result);


        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int diff = target - arr[i];
            if (map.containsKey(diff)) {

                return new int[]{map.get(diff), i};
            }
        }

        return arr;
    }
}
