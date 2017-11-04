package ru.job4j.sort;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortUser {

    public TreeSet<User> sort(List<User> userList) {

//        userList.sort(new Comparator<User>() {
//            @Override
//            public int compare(User o1, User o2) {
//                return User;
//            }
//        });

        TreeSet<User> userTreeSet = new TreeSet<User>();

//

//        userTreeSet.addAll(userList);

        for (User user : userList) {
            userTreeSet.add(user);
        }

        return userTreeSet;
//        }
    }
}
