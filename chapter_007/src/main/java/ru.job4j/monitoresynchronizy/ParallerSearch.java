package ru.job4j.monitoresynchronizy;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//public class ParallerSearch(String root, String text, List<String> exts) {
//        }
//
//        ,где
//        root - путь до папки откуда надо осуществлять поиск.
//        text - заданных текст.
//        exts - расширения файлов в которых нужно делать поиск.
//
//        Приложения должно искать текст в файлах (в том числе и в подкаталогах) и сохранять адрес файла.
//
//        List<String> result(); - возвращает список всех файлов.
//
//        Логика приложения.
//
//        1. Запустить код.
//        2. Внутри запустить несколько потоков. Объяснить для чего нужно делать потоки.
//        3. Дождаться завершения поиска.результат.
//        4. Вывести на консоль результат.
public class ParallerSearch {

    File root;
    String text;
    ArrayList<String> exts;

    private volatile List<String> result = new ArrayList<>();

    public ParallerSearch(File root, String text, ArrayList<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;

    }

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        File[] disks = File.listRoots();

        int numberOfDisks = disks.length;

        if (numberOfDisks > 0) {

            ArrayList<String> exts = new ArrayList<>();

            exts.add("txt");

            ParallerSearch parallerSearch = new ParallerSearch(disks[0], "asdfasdfasdf", exts);

            List<String> result = parallerSearch.result(100);

            for (String path : result) {
                System.out.println("Found path: " + path);
            }

            long endTime = System.currentTimeMillis();

            long timeConsumedMillis = endTime - startTime;

            System.out.println("all time: " + timeConsumedMillis + "ms");
        }
    }


    public List<String> result(int numberOfThreads) {

        ExecutorService pool = Executors.newFixedThreadPool(numberOfThreads);

        list(root, pool, result, exts);

        return result;
    }

    void list(File szDir, ExecutorService pool, List<String> result, ArrayList<String> exts) {

        File f = szDir; //new File(szDir);
        File[] sDirList = f.listFiles(new Filter(exts)); //f.list();

        // Если папка пуста то не заходим
        if (sDirList != null) {
            for (int i = 0; i < sDirList.length; i++) {
                File currFileOrDir = sDirList[i];
                if (currFileOrDir.isFile()) {
                    pool.submit(new TaskFind(text, currFileOrDir.getPath()));
//                    new TaskFindNotThread(text, currFileOrDir.getPath()).run();
                } else {
                    list(currFileOrDir, pool, result, exts);
                }
            }
        }
    }

    class Filter implements FileFilter {
        ArrayList<String> ext;

        Filter(ArrayList<String> ext) {
            this.ext = ext;
        }

        private String getExtension(File pathname) {
            String filename = pathname.getPath();
            int i = filename.lastIndexOf('.');
            if ((i > 0) && (i < filename.length() - 1)) {
                return filename.substring(i + 1).toLowerCase();
            }
            return "";
        }

        public boolean accept(File pathname) {

            if (!pathname.isFile()) {
                return true;
            }

            String extension = getExtension(pathname);

            for (String e : ext) {
                if (e.equalsIgnoreCase(extension)) {
                    return true;
                }
            }
            return false;
        }
    }

    public class TaskFind implements Runnable {

        String szName, findTxt;

        public TaskFind(String findTxt, String szName) {
            this.szName = szName;
            this.findTxt = findTxt;
        }

        public void run() {

            System.out.println("name: " + Thread.currentThread().getName() + " file: " + szName);

            String s;

            try {

                LineNumberReader lnr = new LineNumberReader(new BufferedReader(new FileReader(szName)));

                while (true) {

                    s = lnr.readLine();

                    if (s == null) {
                        break;
                    }

                    if (s.indexOf(findTxt) != -1) {

                        System.out.println("FIND TEXT!!!!!: " + szName);

                        result.add(szName);

                        break;

                    }
                }

                lnr.close();

            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    public class TaskFindNotThread {

        String szName, findTxt;

        public TaskFindNotThread(String findTxt, String szName) {
            this.szName = szName;
            this.findTxt = findTxt;
        }

        public void run() {

            System.out.println("name: " + Thread.currentThread().getName() + " file: " + szName);

            String s;

            try {
                LineNumberReader lnr = new LineNumberReader(new BufferedReader(new FileReader(szName)));
                while (true) {

                    s = lnr.readLine();

                    if (s == null) {
                        break;
                    }

                    if (s.indexOf(findTxt) != -1) {

                        System.out.println("FIND TEXT!!!!!: " + szName);

                        result.add(szName);

                        break;
                    }
                }

                lnr.close();

            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}