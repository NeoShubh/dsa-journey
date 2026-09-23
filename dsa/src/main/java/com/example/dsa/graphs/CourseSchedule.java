package com.example.dsa.graphs;

import java.util.*;
public class CourseSchedule {

    static class Solution {

        public boolean canFinish(int numCourses, int[][] prerequisites) {

            HashMap<Integer, List<Integer>> mp = new HashMap<>();

            // Create an empty list for every course
            for (int i = 0; i < numCourses; i++) {
                mp.put(i, new ArrayList<>());
            }

            // Add prerequisites
            for (int i = 0; i < prerequisites.length; i++) {
                mp.get(prerequisites[i][0]).add(prerequisites[i][1]);
            }

            HashSet<Integer> visits = new HashSet<>();

            for (int i = 0; i < numCourses; i++) {
                if (!dfs(i, mp, visits)) {
                    return false;
                }
            }

            return true;
        }

        boolean dfs(int curr, HashMap<Integer, List<Integer>> mp,
                    HashSet<Integer> visits) {

            if (visits.contains(curr))
                return false;

            if (mp.get(curr).isEmpty())
                return true;

            visits.add(curr);

            for (int i : mp.get(curr)) {
                if (!dfs(i, mp, visits))
                    return false;
            }

            visits.remove(curr);

            // Already completely checked
            mp.put(curr, new ArrayList<>());

            return true;
        }
    }

    public static void main(String[] args) {

        int numCourses = 5;

        int[][] prerequisites = {
                {0, 1},
                {0, 2},
                {1, 3},
                {1, 4},
                {3, 4}
        };

        Solution solution = new Solution();

        boolean result = solution.canFinish(numCourses, prerequisites);

        System.out.println("Can finish all courses: " + result);
    }
}