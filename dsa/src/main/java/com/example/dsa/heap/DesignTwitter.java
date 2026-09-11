package com.example.dsa.heap;

import java.util.*;
import java.util.stream.Collectors;

class UserTweet{
    int count;
    int tweetId;
    public UserTweet(int count,int tweetId){
        this.count = count;
        this.tweetId = tweetId;
    }
}

public class DesignTwitter {

    HashMap<Integer, HashSet<Integer>> fmap;
    HashMap<Integer,List<UserTweet>> usrTweetMap;
    int count;
    public DesignTwitter() {
      fmap  = new HashMap<>();
      usrTweetMap = new HashMap<>();
      count = 0;
    }

    public void postTweet(int userId, int tweetId) {
        // implement
        if(usrTweetMap.containsKey(userId)){
            usrTweetMap.get(userId).add(new UserTweet(++count,tweetId));
        }else{
            usrTweetMap.computeIfAbsent(userId, k -> new ArrayList<>()).add(new UserTweet(++count,tweetId));
        }
    }

    public List<Integer> getNewsFeed(int userId) {
        // implement

        HashSet<Integer> followees = fmap.getOrDefault(userId, new HashSet<>());

        PriorityQueue<UserTweet> pq = new PriorityQueue<>((a,b)->a.count-b.count);

        if(usrTweetMap.containsKey(userId) && usrTweetMap.get(userId).size()>0){
            for(UserTweet userTweet : usrTweetMap.get(userId)) {
                pq.offer(userTweet);
                if(pq.size()>10)
                    pq.poll();
            }
        }
        if(followees.size()==0) {
            return pq.stream().map(userTweet->userTweet.tweetId).toList();
        }

        for(Integer followee : followees){
            for(UserTweet userTweet : usrTweetMap.get(followee)){
                pq.offer(userTweet);
                if(pq.size()>10)
                    pq.poll();
            }
        }
        if(usrTweetMap.get(userId).size()>0){
            for(UserTweet userTweet : usrTweetMap.get(userId)) {
                pq.offer(userTweet);
                if(pq.size()>10)
                    pq.poll();
            }
        }

        return pq.stream().map(userTweet->userTweet.tweetId).toList();
    }

    public void follow(int followerId, int followeeId) {
        // implement

        fmap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);

    }

    public void unfollow(int followerId, int followeeId) {
        if (fmap.containsKey(followerId)) {
            fmap.get(followerId).remove(followeeId);
        }
    }
    public static void main(String[] args) {

        DesignTwitter twitter = new DesignTwitter();

        // Example 1
        twitter.postTweet(1, 5);

        System.out.println("Feed of user 1: "
                + twitter.getNewsFeed(1));

        // Example 2
        twitter.follow(1, 2);

        twitter.postTweet(2, 6);

        System.out.println("Feed of user 1: "
                + twitter.getNewsFeed(1));

        // Example 3
        twitter.unfollow(1, 2);

        System.out.println("Feed of user 1: "
                + twitter.getNewsFeed(1));
    }
}