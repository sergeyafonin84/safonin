package ru.job4j.studymokito;

import java.io.IOException;

// https://en.wikipedia.org/wiki/Mockito
public class HelloApplication {

    public static void main(String... args) throws IOException {
        new HelloAction(new HelloGreeter("hello", ": "), System.out).sayHello("application", "world");
    }

    public static interface Greeter {
        String getGreeting(String subject);
        String getIntroduction(String actor);
    }

    public static interface HelloActable {
        void sayHello(String actor, String subject) throws IOException;
    }

    public static class HelloGreeter implements Greeter {
        private String hello;
        private String segmenter;

        public HelloGreeter(String hello, String segmenter) {
            this.hello = hello;
            this.segmenter = segmenter;
        }
        public String getGreeting(String subject) {
            return hello + " " + subject;
        }
        public String getIntroduction(String actor) {
            return actor + segmenter;
        }
    }

    public static class HelloAction implements HelloActable {
        private Greeter helloGreeter;
        private Appendable helloWriter;

        public HelloAction(Greeter helloGreeter, Appendable helloWriter) {
            super();
            this.helloGreeter = helloGreeter;
            this.helloWriter = helloWriter;
        }
        public void sayHello(String actor, String subject) throws IOException {
            helloWriter.append(helloGreeter.getIntroduction(actor)).append(helloGreeter.getGreeting(subject));
        }
    }

}
