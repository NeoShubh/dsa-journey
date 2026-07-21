package com.example.dsa.stack;

import java.util.Stack;
class Pair3{
    int height;
    int index;

    public Pair3(int height, int index) {
        this.height = height;
        this.index = index;
    }

    public int getHeight() {
        return height;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "Pair3{" +
                "height=" + height +
                ", index=" + index +
                '}';
    }
}
public class LargestRectangleInHistogram {
    public static void main(String [] args){
        int [] heights = {2,1,5,6,2,3};
        int maxArea = 0;
        Stack<Pair3> stk = new Stack<>();

        for(int i=0;i<heights.length;i++){
          int  start = i;
            while(!stk.isEmpty() && stk.peek().height>heights[i]){
                maxArea = Math.max(maxArea,stk.peek().height*(i-stk.peek().index));
                start = stk.peek().index;
                stk.pop();
            }
            stk.push(new Pair3(heights[i],start));
        }

        for(Pair3 entry: stk){
            maxArea = Math.max(maxArea, entry.height*(heights.length - entry.index));
        }
        System.out.println(maxArea);
    }
}
