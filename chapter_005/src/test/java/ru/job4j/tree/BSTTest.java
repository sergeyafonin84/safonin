package ru.job4j.tree;

import org.junit.Test;

import java.util.Comparator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BSTTest {

    public static final class User implements Comparable<User> {

        public String name;

        @Override
        public String toString() {
            return "User{" + "name='" + name + '\'' + '}';
        }

        public User(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(User o) {
            return this.name.compareTo((o.name));
        }
    }

    public Comparator<User> getComparator() {
        return new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.name.compareTo(o2.name);
            }
        };
    }

    public void fillTheBST(BST bst) {
        User user10 = new User("user10");
        User user20 = new User("user20");
        User user30 = new User("user30");
        User user40 = new User("user40");
        User user50 = new User("user50");

        User user05 = new User("user05");
        User user15 = new User("user15");
        User user25 = new User("user25");
        User user35 = new User("user35");
        User user45 = new User("user45");

        bst.add(user10);
        bst.add(user20);
        bst.add(user30);
        bst.add(user40);
        bst.add(user50);

        bst.add(user45);
        bst.add(user35);
        bst.add(user25);
        bst.add(user15);
        bst.add(user05);

        bst.add(null);

    }

    @Test
    public void whenAddElementsToBSTTreeWithComparatorThenTheStructureIsAccordingToComparator() {

        Comparator cmp = getComparator();

        BST bst = new BST(cmp);

        fillTheBST(bst);

        boolean result = cmp.compare(bst.getRoot().left.value, new User("user05")) == 0;

        boolean expected = true;

        assertThat(result, is(expected));
    }
}