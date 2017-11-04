package ru.job4j.sort;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SortUserTest {

    @Test
    public void whenSortUserInListAndConvertToTreeSetThenGetTreeSetWithSortedUsers() {

        List<User> userList = new ArrayList<User>();
        userList.addAll(Arrays.asList(
                new  User("serg", 33),
                new User("zuma", 11),
                new User("ivan", 22)

                )
        );

        SortUser sortUser = new SortUser();

        TreeSet<User> resultUserTreeSet = sortUser.sort(userList);

        TreeSet<User> expectedtuserTreeSet = new TreeSet<User>();

        expectedtuserTreeSet.add(new User("zuma", 11));
        expectedtuserTreeSet.add(new User("ivan", 22));
        expectedtuserTreeSet.add(new  User("serg", 33));

        String resultString = "";
        String expectedString = "";

        for (User resultUser : resultUserTreeSet) {
            resultString = addStringToString(resultString, resultUser.toString());
        }

        for (User expectedUser : expectedtuserTreeSet) {
            expectedString = addStringToString(expectedString, expectedUser.toString());
        }

        assertThat(resultString, is(expectedString));
    }

    String addStringToString(String s1, String s2) {
        return s1 + s2;
    }
}
