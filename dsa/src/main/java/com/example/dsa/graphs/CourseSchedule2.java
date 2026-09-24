package com.example.dsa.graphs;

import java.util.*;

public class CourseSchedule2 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        HashMap<Integer, List<Integer>> mp = new HashMap<>();

        for (int i = 0; i < numCourses; i++) {
            mp.put(i, new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            mp.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        HashSet<Integer> visits = new HashSet<>();
        HashSet<Integer> processed = new HashSet<>();

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {

            if (!dfs(i, mp, visits, processed, ans)) {
                return new int[]{};
            }
        }

        return ans.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    boolean dfs(int curr,
                HashMap<Integer, List<Integer>> mp,
                HashSet<Integer> visits,
                HashSet<Integer> processed,
                List<Integer> ans) {

        // Cycle detected
        if (visits.contains(curr)) {
            return false;
        }

        // Already completely processed
        if (processed.contains(curr)) {
            return true;
        }

        visits.add(curr);

        for (int pre : mp.get(curr)) {

            if (!dfs(pre, mp, visits, processed, ans)) {
                return false;
            }
        }

        visits.remove(curr);

        processed.add(curr);

        // Add after all prerequisites
        ans.add(curr);

        return true;
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

        CourseSchedule2 solution = new CourseSchedule2();

        int[] result = solution.findOrder(numCourses, prerequisites);

        System.out.println("Course order: " + Arrays.toString(result));
    }
}