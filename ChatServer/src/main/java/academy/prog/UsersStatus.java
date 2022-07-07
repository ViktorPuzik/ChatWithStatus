package academy.prog;

import academy.prog.MessageList;

import java.util.*;

public class UsersStatus {
    private static final UsersStatus us = new UsersStatus();
    private static HashMap <String, Long> users = new HashMap<>();
    public static UsersStatus getUS() {
        return us;
    }

    public synchronized void addAction (String user ){
        users.put(user, System.currentTimeMillis());
    }

    public synchronized ArrayList <String> checkActive (long timeOut){
        List<String> lst = new ArrayList<>();
        long currentTime = System.currentTimeMillis();
       for(Map.Entry<String, Long> entry : users.entrySet()){
           if ( currentTime - (entry.getValue()) <= timeOut){
               lst.add(entry.getKey());
           }
        }
        return (ArrayList<String>) lst;
    }
}
