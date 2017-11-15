package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import java.util.ArrayList;



public class UserStoreTest {


    @Test
    public void whenAddUserToUserStoreThenOneCanFindUserInTheUserStore() {

        UserStore userStore = new UserStore(4);

        User user1 = new User("1234");
        User user2 = new User("2222");


        userStore.add(user1);
        userStore.add(user2);

        User result = (User) userStore.findByBaseObjectsInternalId("2222");

        User expected = user2;

        assertThat(result, is(expected));

    }

    @Test
    public void whenUpdateThenGetUpdatedValue() {

        UserStore userStore = new UserStore(4);

        User user1 = new User("1234");
        User user2 = new User("2222");
        User user3 = new User("2222");

        userStore.add(user1);
        userStore.add(user2);


        userStore.update(user3);

        User result = (User) userStore.findByBaseObjectsInternalId("2222");

        User expected = user3;

        assertThat(result, is(expected));

    }

    @Test
    public void whenDeleteThenTheElementDeleted() {

        UserStore userStore = new UserStore(4);

        User user1 = new User("1234");
        User user2 = new User("2222");


        userStore.add(user1);
        userStore.add(user2);

        String user2Id = user2.getId();

        userStore.delete(user2Id);

        User result = (User) userStore.findByBaseObjectsInternalId(user2Id);

        User expected = null;

        assertThat(result, is(expected));

    }

}