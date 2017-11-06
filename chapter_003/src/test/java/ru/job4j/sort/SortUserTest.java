package ru.job4j.sort;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

//пока не исправил компаратор, то тест был красным
//@Override
//public String toString() {
//        return "User{" + "name='" + name + '\'' + ", age=" + age + '}';
//        }
//
//@Override
//public int compareTo(User o) {
//        // return this.age > o.age ? 1 : -1;
//
//        if (this.age > o.age) {
//        return 1;
//        } else if (this.age < o.age) {
//        return -1;
//        } else {
//        return 0;
//        }
//        }
public class SortUserTest {

    @Test
    public void whenSortUserInListAndConvertToTreeSetThenGetTreeSetWithSortedUsers() {

        List<User> userList = new ArrayList<User>();
        userList.addAll(Arrays.asList(
                new User("serg", 33),
                new User("zuma", 11),
                new User("ivan", 22)

                )
        );

        TreeSet<User> resultUserTreeSet = new SortUser().sort(userList);

        TreeSet<User> expectedtuserTreeSet = new TreeSet<>();

        expectedtuserTreeSet.add(new User("zuma", 11));
        expectedtuserTreeSet.add(new User("ivan", 22));
        expectedtuserTreeSet.add(new User("serg", 33));

        assertThat(resultUserTreeSet, is(expectedtuserTreeSet));
    }

//    @Test
//    public void whenSortUserInListAndConvertToTreeSetThenGetTreeSetWithSortedUsers() {
//
//        List<User> userList = new ArrayList<User>();
//        userList.addAll(Arrays.asList(
//                new  User("serg", 33),
//                new User("zuma", 11),
//                new User("ivan", 22)
//
//                )
//        );
//
//        SortUser sortUser = new SortUser();
//
//        TreeSet<User> resultUserTreeSet = sortUser.sort(userList);
//
//        TreeSet<User> expectedtuserTreeSet = new TreeSet<User>();
//
//        expectedtuserTreeSet.add(new User("zuma", 11));
//        expectedtuserTreeSet.add(new User("ivan", 22));
//        expectedtuserTreeSet.add(new  User("serg", 33));
//
//        String resultString = "";
//        String expectedString = "";
//
//        for (User resultUser : resultUserTreeSet) {
//            resultString = addStringToString(resultString, resultUser.toString());
//        }
//
//        for (User expectedUser : expectedtuserTreeSet) {
//            expectedString = addStringToString(expectedString, expectedUser.toString());
//        }
//
//        assertThat(resultString, is(expectedString));
//    }
//
//    String addStringToString(String s1, String s2) {
//        return s1 + s2;
//    }
}
