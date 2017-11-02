package ru.job4j.lite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.String.*;

public class ListExamle {

//    static class Node {
//        Node next;
//    }

    public static void main(String[] args) {

//        Node first = new Node();
//        Node second = new Node();
//        Node third = new Node();
//
//        first.next = second;
//        second.next = third;

        List<Integer> list = new ArrayList<Integer>();
        list.add(425);

//        list.add(425);
//
        for (Integer value : list) {
            System.out.println(value);
        }

        Integer value = list.get(0);
        System.out.println(value);

        list.add(1, 32);

        System.out.println(list.get(1));

        List<Integer> flats = new LinkedList<>(); //ArrayList<>();////
        flats.add(4); // index = 2
        flats.add(3); // index = 3

        list.addAll(flats);

        System.out.println(format("find 4 index: %s", list.indexOf(4)));

        System.out.println(format("find 4 index: %s", list.lastIndexOf(4)));

        list.remove(2);

        System.out.println(format("find 4 index: %s", list.lastIndexOf(4)));

        System.out.println(format("find 3 index: %s", list.lastIndexOf(3)));
    }
}
