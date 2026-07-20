package com.example.dsa.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Pair1 {
    int Positions;
    int Speed;

    @Override
    public String toString() {
        return "Pair1{" +
                "Positions=" + Positions +
                ", Speed=" + Speed +
                '}';
    }

    public int getPositions() {
        return Positions;
    }


    public int getSpeed() {
        return Speed;
    }


    Pair1(int x, int y) {
        this.Positions = x;
        this.Speed = y;
    }

}

public class CarFleet853 {
    public static void main(String[] args) {

        int target = 12;
        int[] positions = {10, 8, 0, 5, 3};
        int[] speed = {2, 4, 1, 1, 3};
        List<Pair1> list = new ArrayList<>();
        for (int i = 0; i < positions.length; i++) {
            Pair1 obj = new Pair1(positions[i], speed[i]);
            list.add(obj);
        }
        System.out.println(list);

        list.sort((o1, o2) -> {
            if ((o2.getPositions() - o1.getPositions()) < 0) return -1;
            else if ((o2.getPositions() - o1.getPositions()) > 0) return 1;
            return 0;
        });
        System.out.println(list);
        Stack<Float> stk = new Stack<>();
        for (Pair1 pair : list) {
            if(stk.isEmpty()){
                stk.push((float) (target - pair.getPositions()) / pair.getSpeed());

            }
            if(!stk.isEmpty() && (float) (target - pair.getPositions()) / pair.getSpeed()> stk.peek()) {
                stk.push((float) (target - pair.getPositions()) / pair.getSpeed());
            }
        }
        System.out.println(stk);
    }
}
