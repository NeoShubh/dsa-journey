package com.example.dsa.binarySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Pair4 {
    private String value;
    private int timestamp;

    public Pair4(String value, int timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Pair4{" +
                "value='" + value + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}

class TimeMap {
    HashMap<String, List<Pair4>> hm;

    public TimeMap() {
        hm = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        Pair4 p = new Pair4(value, timestamp);
        if (hm.containsKey(key)) {
            hm.get(key).add(p);
        } else {
            List<Pair4> list = new ArrayList<>();
            list.add(p);
            hm.put(key, list);
        }
    }

    public String get(String key, int timestamp) {
        if(!hm.containsKey(key) || hm.get(key).size() == 0)
            return "";
        else {
            int left = 0;
            int right = hm.get(key).size() - 1;

            while (right >= left) {
                int mid = left + (right - left) / 2;

                if (hm.get(key).get(mid).getTimestamp() == timestamp) {
                    return hm.get(key).get(mid).getValue();
                }
                if (hm.get(key).get(mid).getTimestamp() < timestamp)
                    left = mid + 1;
                else
                    right = mid - 1;
            }
            if(right < 0) return "";
            return hm.get(key).get(right).getValue();
        }

    }
}


public class TimeBasedKeyValueStore981 {
    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
        timeMap.get("foo", 1);         // return "bar"
        timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
        timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
        timeMap.get("foo", 4);         // return "bar2"
        timeMap.get("foo", 5);

    }
}
