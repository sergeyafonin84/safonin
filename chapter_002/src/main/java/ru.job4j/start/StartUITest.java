package ru.job4j.start;

public class StartUITest {

    public static void main(String[] args) {
        Input input = new StubInput(new String[] {"create stub task"}); // ConsoleInput();
        new StartUi(input).init();
    }

}
