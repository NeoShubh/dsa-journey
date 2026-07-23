package com.example.dsa.binarySearch;

public class SearchIn2DMatrix74 {
    public static void main(String [] args){
//        int [][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int [][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60},{64,65,69,71},{74,77,83,90}};
        int target = 77;

        int left = 0;
        int right = matrix.length-1;

        while(right>=left){
            int mid = left + (right-left)/2;

            if(matrix[mid][0] == target){
                System.out.println("target found");
                return;
            }

            if(matrix[mid][0]<target){
                left = mid+1;
            }
            if(matrix[mid][0]>target)
                right = mid-1;
        }
        System.out.println(left+" "+right);

        int start = 0;
        int end = matrix[0].length-1;

        while(end>=start){
            int mid = start + (end-start)/2;

            if(matrix[right][mid] == target){
                System.out.println("target found");
                return;
            }

            if(matrix[right][mid]<target){
                start = mid+1;
            }
            if(matrix[right][mid]>target)
                end = mid-1;
        }
    }
}
