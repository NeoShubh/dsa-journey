package com.example.dsa.graphs;

import java.util.*;

public class CloneGraph {

    // Definition for a Node.
    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    static class Solution {
        HashMap<Integer, Node> map = new HashMap<>();

        public Node cloneGraph(Node node) {
            return clone(node);
        }

        public Node clone(Node node) {
            if (node == null)
                return null;

            if (map.containsKey(node.val))
                return map.get(node.val);

            Node newNode = new Node(node.val, new ArrayList<>());

            // Put it BEFORE cloning neighbors
            map.put(newNode.val, newNode);

            for (Node neighbor : node.neighbors) {
                newNode.neighbors.add(clone(neighbor));
            }

            return newNode;
        }
    }

    public static void main(String[] args) {

        /*
                 1
                / \
               2---4
                \ /
                 3

        Example graph:
        1 -> 2, 4
        2 -> 1, 3, 4
        3 -> 2, 4
        4 -> 1, 2, 3
        */

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node2.neighbors.add(node4);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node2);
        node4.neighbors.add(node3);

        Solution solution = new Solution();

        Node clonedGraph = solution.cloneGraph(node1);

        System.out.println("Original graph:");
        printGraph(node1);

        System.out.println("\nCloned graph:");
        printGraph(clonedGraph);
    }

    static void printGraph(Node node) {

        if (node == null) {
            System.out.println("null");
            return;
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();

        queue.offer(node);
        visited.add(node.val);

        while (!queue.isEmpty()) {

            Node current = queue.poll();

            System.out.print(current.val + " -> ");

            for (Node neighbor : current.neighbors) {
                System.out.print(neighbor.val + " ");

                if (!visited.contains(neighbor.val)) {
                    visited.add(neighbor.val);
                    queue.offer(neighbor);
                }
            }

            System.out.println();
        }
    }
}