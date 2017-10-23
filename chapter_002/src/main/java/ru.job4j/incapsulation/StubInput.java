package ru.job4j.incapsulation;

public class StubInput implements Input {
    private String[] answers;
    private int position = 0;

    public StubInput(String[] answers) {
        this.answers = answers;
    }


    @Override
    public String ask(String question) {
        return answers[position++];
    }
}
