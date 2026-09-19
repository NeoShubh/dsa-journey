package com.example.dsa.binarySearch;

public class SearchinRotatedSortedArray33 {

    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int ans = 0;
        while (l <= r) {
            int mid = l + ((r - l) / 2);

            if (nums[mid] == target)
               ans = mid;



            if (nums[l] <= nums[mid]) {
                if (target>nums[mid] || nums[l] > target) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else {
                if (target<nums[mid] || nums[r] < target) {
                    r = mid - 1;
                } else {
                     l = mid + 1;
                }
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5, 6, 0, 1};
        SearchinRotatedSortedArray33 obj = new SearchinRotatedSortedArray33();
        System.out.println(obj.search(arr, 0));
    }
}
