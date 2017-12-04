package ru.job4j.threads;

public class Count {

    public static void main(String[] args) throws InterruptedException {

        String text = Count.getText();

        Thread thread1 = new Thread(new ShowStart());
        Thread thread2 = new Thread(new CountTheNumberOfWords(text));
        Thread thread3 = new Thread(new CountTheNumberOfSpaces(text));
        Thread thread4 = new Thread(new ShowFinish());

        thread1.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();

        thread3.start();

        try {
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread4.start();
    }

    public static final class ShowStart implements Runnable {

        @Override
        public void run() {
            System.out.println("start");
        }
    }

    public static final class ShowFinish implements Runnable {

        @Override
        public void run() {
            System.out.println("finish");
        }
    }

    public static final class CountTheNumberOfWords implements Runnable {
        private final String text;

        public CountTheNumberOfWords(String text) {
            this.text = text;
        }

        @Override
        public void run() {
            char[] chars = text.toCharArray();

            for (int ind = 0; ind < chars.length; ind++) {
                System.out.println("Thread 1 CountTheNumberOfWords = " + ind);
            }
        }
    }

    public static final class CountTheNumberOfSpaces implements Runnable {
        private final String text;

        public CountTheNumberOfSpaces(String text) {
            this.text = text;
        }

        @Override
        public void run() {
            char[] chars = text.toCharArray();

            for (int ind = 0; ind < chars.length; ind++) {
                System.out.println("Thread 2 CountTheNumberOfSpaces = " + ind);
            }
        }
    }

    public static String getText() {
        return "Описание[править | править вики-текст]\n"
                +
                "Сутью многопоточности является квазимногозадачность на уровне одного исполняемого процесса, то есть все потоки выполняются в адресном пространстве процесса. Кроме этого, все потоки процесса имеют не только общее адресное пространство, но и общие дескрипторы файлов. Выполняющийся процесс имеет как минимум один (главный) поток.\n"
                +
                "Многопоточность (как доктрину программирования) не следует путать ни с многозадачностью, ни с многопроцессорностью, несмотря на то, что операционные системы, реализующие многозадачность, как правило, реализуют и многопоточность.\n"
                +
                "К достоинствам многопоточности в программировании можно отнести следующее:\n"
                +
                "Упрощение программы в некоторых случаях за счёт использования общего адресного пространства.\n"
                +
                "Меньшие относительно процесса временны́е затраты на создание потока.\n"
                +
                "Повышение производительности процесса за счёт распараллеливания процессорных вычислений и операций ввода-вывода.\n"
                +
                "Аппаратная реализация[править | править вики-текст]\n"
                +
                "На обычном процессоре управление потоками осуществляется операционной системой. Поток исполняется до тех пор, пока не произойдёт аппаратное прерывание, системный вызов или пока не истечёт отведённое для него операционной системой время. После этого процессор переключается на код операционной системы, который сохраняет состояние потока (его контекст) или переключается на состояние другого потока, которому тоже выделяется время на исполнение. При такой многопоточности достаточно большое количество тактов процессора тратится на код операционной системы, переключающий контексты. Если поддержку потоков реализовать аппаратно, то процессор сам сможет переключаться между потоками, а в идеальном случае - выполнять несколько потоков одновременно за каждый такт. Для операционной системы и пользователя один такой физический процессор будет виден как несколько логических процессоров.\n"
                +
                "Различают две формы многопоточности, которые могут быть реализованы в процессорах аппаратно:\n"
                +
                "Временная многопоточность (англ. Temporal multithreading)\n"
                +
                "Одновременная многопоточность (англ. Simultaneous multithreading)";
    }
}