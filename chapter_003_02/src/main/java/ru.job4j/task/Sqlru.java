package ru.job4j.task;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

//https://jsoup.org/cookbook/extracting-data/attributes-text-html
public class Sqlru implements Job {

    private static final Logger LOGGG = LoggerFactory.getLogger(Sqlru.class);
    Connection connection;

    public static void main(String[] args) {
        /**
         * if you want jast start the program without scheduler
         *  Sqlru sqlru = new Sqlru();
         *  sqlru.executeProgram();
         */
        schedulerEveryDayInAppropriateTimaRunTaskSqlru("21", "31");
//        Sqlru sqlru = new Sqlru();
//        sqlru.executeProgram();
    }

    public static void schedulerEveryDayInAppropriateTimaRunTaskSqlru(String hour, String minute) {

        JobDetail job = JobBuilder
                .newJob(Sqlru.class)
                .withIdentity("Sqlru", "group1")
                .build();

//   формат дат     http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/crontrigger.html
//   tutorial     http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/tutorial-lesson-06.html
//    пример    https://ru.stackoverflow.com/questions/585061/%D0%97%D0%B0%D0%BF%D1%83%D1%81%D0%BA-%D0%BF%D0%BE-%D0%B2%D1%80%D0%B5%D0%BC%D0%B5%D0%BD%D0%B8
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("SqlruTrigger", "group1")
                .withSchedule(CronScheduleBuilder
//                        .cronSchedule("12 45 9-18/1 ? * MON-FRI"))
//                        .cronSchedule("0 11 20 ? * *"))
                        .cronSchedule("0 " + minute + " " + hour + " ? * *"))
                .build();

        SchedulerFactory schedFact = new StdSchedulerFactory();
        Scheduler sched = null;
        try {
            sched = schedFact.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
            LOGGG.error(e.getMessage(), e);
        }
        try {
            sched.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
            LOGGG.error(e.getMessage(), e);
        }
        try {
            sched.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
            LOGGG.error(e.getMessage(), e);
        }
    }

    void executeProgram() {

        System.out.println("executeProgram() ");
        LOGGG.info("executeProgram() ");

        MyConnection myConnection = new MyConnection();
        HashSet<String> pagesUrls = new HashSet<String>();
        HashSet vacancies = new LinkedHashSet<Vacancy>();

        myConnection.initConnection();
        connection = myConnection.conn;
        WorkWithDataBase workWithDataBase = new WorkWithDataBase(connection);

        GetVacFromPages getVacFromPages = new GetVacFromPages();
        pagesUrls = getVacFromPages.getPagesUrls(workWithDataBase);
        workWithDataBase.addCurrDateToTable();
        getVacFromPages.getVacFromPages(pagesUrls, workWithDataBase);

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGG.error(e.getMessage(), e);
        }
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Sqlru sqlru = new Sqlru();
        sqlru.executeProgram();
    }
}

class GetVacFromPages {

    private static final Logger LOGGG = LoggerFactory.getLogger(GetVacFromPages.class);

    public HashSet<String> getPagesUrls(WorkWithDataBase workWithDataBase) {

        HashSet<String> pagesUrls = new LinkedHashSet<>();
        String initialPartOfUrlString = "http://www.sql.ru/forum/job-offers/";
        //новые вакансии
        for (int i = 1; i < 100; i++) { //44.44
            String currUrl = initialPartOfUrlString + String.valueOf(i);
            pagesUrls.add(currUrl);
        }
        //старые вакансии
        if (workWithDataBase.itIsFirsLoad()) {

            for (int i = 101; i < 645; i++) { //44.44

                String currUrl = initialPartOfUrlString + String.valueOf(i);

                if (!workWithDataBase.thisUrlExist(currUrl, "new")) {
                    pagesUrls.add(currUrl);
                }
            }
        }
        return pagesUrls;
    }

    public HashSet<String> getVacFromPages(HashSet pagesUrl, WorkWithDataBase workWithDataBase) {

        HashSet vacancies = new LinkedHashSet();
        Iterator iterator = pagesUrl.iterator();

        while (iterator.hasNext()) {
            String tableOfVacUrl = (String) iterator.next();
            HashSet currVacancies = new HashSet<Vacancy>();
            currVacancies = getVacFromPage(tableOfVacUrl, workWithDataBase);
            vacancies.addAll(currVacancies);
        }
        return vacancies;
    }

    public HashSet<Vacancy> getVacFromPage(String pageUrl, WorkWithDataBase workWithDataBase) {

        HashSet<String> hashSet = new LinkedHashSet<>();
        HashSet<String> hashSetUrlVac = new LinkedHashSet<>();

        HashSet<Vacancy> vacanciesFromPage = new LinkedHashSet<>();

        Document docTab = null;
        try {
            docTab = Jsoup.connect(pageUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGG.error(e.getMessage(), e);
        }
        Elements elelementsTab = docTab.getAllElements();

        for (Element elTab : elelementsTab) {
            if (elTab.absUrl("href").split("http://www.sql.ru/forum/").length > 1) {
                hashSet.add(elTab.absUrl("href"));
            }
        }

        for (String elHashSet : hashSet) {
            try {
                if (
                        elHashSet.split("/").length > 4
                                && elHashSet.split("/")[4].split("=").length > 0
                                &&
                                Integer.parseInt(elHashSet.split("/")[4].split("=")[0]) > 100000
                        ) {
                    hashSetUrlVac.add(elHashSet);
                }
            } catch (Exception e) {
            }
        }
        for (String urlVac : hashSetUrlVac) {
            Document docVacancy = null;
            try {
                docVacancy = Jsoup.connect(urlVac).get();
            } catch (IOException e) {
                e.printStackTrace();
                LOGGG.error(e.getMessage(), e);
            }
            Elements elementsVacancy = docVacancy.getAllElements();
            for (Element elVacancy : elementsVacancy) {
                Node namesddfdf;
                if (!(
                        elVacancy.childNodes().isEmpty()
                                || (elVacancy.childNodes().size() <= 2)
                                || elVacancy.childNodes().get(2).attributes().size() == 1
                                || (elVacancy.childNodes().get(2).childNodes().size() < 1)
                                || elVacancy.childNodes().get(2).childNodes().isEmpty()
                                || elVacancy.childNodes().get(2).childNodes().get(0).attributes().size() == 1
                                || elVacancy.childNodes().get(2).childNodes().get(0).childNodes().isEmpty()
                                || (elVacancy.childNodes().get(2).childNodes().get(0).childNodes().size() < 2)
                                || elVacancy.childNodes().get(2).childNodes().get(0).childNodes().get(1).attributes().size() == 1
                                || (elVacancy.childNodes().get(2).childNodes().get(0).childNodes().get(1).childNodes().size() < 1)
                )) {
                    namesddfdf = elVacancy.childNodes().get(2).childNodes().get(0).childNodes().get(1).childNodes().get(0);
                    String nameOfVac = namesddfdf.toString();
                    if ((nameOfVac.toLowerCase().split("java").length > 1)
                            && !((nameOfVac.toLowerCase().split("javascript").length > 1))
                            && !((nameOfVac.toLowerCase().split("java script").length > 1))) {

                        Vacancy vacancy = new Vacancy(nameOfVac, urlVac, pageUrl);
                        vacanciesFromPage.add(vacancy);
                        workWithDataBase.addVacancyToDataBase(vacancy);
                    }
                }
            }
        }
        return vacanciesFromPage;
    }
}

class MyConnection {

    private static final Logger LOGGG = LoggerFactory.getLogger(MyConnection.class);
    Connection conn;
    private String url;
    private String username;
    private String password;

    public MyConnection(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public MyConnection() {
    }

    public void initConnection() {
        this.url = "jdbc:postgresql://localhost:5432/task1731";
        this.username = "postgres";
        this.password = "password";
        this.conn = this.getConnection();
    }

    public Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
                LOGGG.error(e.getMessage(), e);
            }
        }
        return conn;
    }
}

class WorkWithDataBase {

    private static final Logger LOGGG = LoggerFactory.getLogger(Sqlru.class);
    Connection conn;

    public WorkWithDataBase(Connection conn) {
        this.conn = conn;
    }

    public void addVacancyToDataBase(Vacancy vacancy) {

        if (!this.vacancyExist(vacancy)) {
            String query = "insert into vacancies (name, vacancyurl, pageurl) values ('" + vacancy.name + "', \n"
                    + "'" + vacancy.vacancyurl + "', '" + vacancy.pageurl + "')";

            String massage = "";

            try (Statement st = conn.createStatement()) {

                st.execute(query);

                massage = "добавляем = " + vacancy.name + " url: " + vacancy.vacancyurl + " page url: " + vacancy.pageurl;

                System.out.println(massage);
                LOGGG.info("executeProgram()");

            } catch (Exception e) {
                e.printStackTrace();
                LOGGG.error(e.getMessage(), e);
            }
        }
    }

    public void addCurrDateToTable() {

        String query = "insert into lastload (loaddate) values ('" + LocalDateTime.now() + "')"; //('2018-01-26T11:38:09.685')";

        try (Statement st = conn.createStatement()) {
            st.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGG.error(e.getMessage(), e);
        }
    }

    public boolean vacancyExist(Vacancy vacancy) {

        boolean exist = false;
        String query;
        ResultSet rs = null;

        query = "select * from vacancies as v\n"
                + "where v.vacancyurl = '" + vacancy.vacancyurl + "';";

        try (Statement st = conn.createStatement();) {
            rs = st.executeQuery(query);
            if (rs.next()) {
                exist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGG.error(e.getMessage(), e);
        }
        return exist;
    }

    public boolean itIsFirsLoad() {

        this.createTablelastloadIfNotExist();

        boolean itIsFirsLoad = true;
        String query;
        ResultSet rs = null;

        query = "select * from lastload";

        try (Statement st = conn.createStatement();) {
            rs = st.executeQuery(query);
            if (rs.next()) {
                itIsFirsLoad = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGG.error(e.getMessage(), e);
        }
        return itIsFirsLoad;
    }


    public void createTableIfNotExist() {

        ResultSet rs = readFromBase("select * from vacancies");

        if (rs == null) {
            try (Statement st = this.conn.createStatement()) {
                st.execute("create table vacancies (\n"
                        + "\n"
                        + "name varchar,\n"
                        + "\n"
                        + "vacancyurl varchar,\n"
                        + "\n"
                        + "pageurl varchar\n"
                        + ")");
            } catch (SQLException e1) {
                e1.printStackTrace();
                LOGGG.error(e1.getMessage(), e1);
            }
        }
    }

    public void createTablelastloadIfNotExist() {

        ResultSet rs = readFromBase("select * from lastload");

        if (rs == null) {

            try (Statement st = this.conn.createStatement()) {
                st.execute("create table lastload(\n"
                        + "loaddate timestamp\n"
                        + ")");
            } catch (SQLException e1) {
                e1.printStackTrace();
                LOGGG.error(e1.getMessage(), e1);
            }
        }
    }

    public boolean thisUrlExist(String url, String newVacOrOld) {

        this.createTablelastloadIfNotExist();
        this.createTableIfNotExist();

        boolean thisUrlExist = true;

        String query; //"select * from usedpagesurls as u where u.pageurl = '" + url + "'";

        if (newVacOrOld == "old") {
            query = "select * from usedpagesurls as u where u.pageurl = '" + url + "'";
        } else {
            query = "select * from vacancies as v where v.pageurl = '" + url + "';";
        }


        ResultSet rs = null;
        try (Statement st = this.conn.createStatement()) {
            rs = st.executeQuery(query);
            if (!rs.next()) {
                thisUrlExist = false;
            } else if (rs.getString("pageurl") == null) {
                thisUrlExist = false;
            } else {
                thisUrlExist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGG.error(e.getMessage(), e);
        }
        return thisUrlExist;
    }


    ResultSet readFromBase(String query) {
        ResultSet rs = null;
        try (Statement st = this.conn.createStatement()) {
            rs = st.executeQuery(query);
        } catch (SQLException e) {
//          situation first run, then there is no table  e.printStackTrace();
        }
        return rs;
    }
}

class Vacancy {

    String name;
    String vacancyurl;
    String pageurl;

    public Vacancy(String name, String vacancyurl, String pageurl) {
        this.name = name;
        this.vacancyurl = vacancyurl;
        this.pageurl = pageurl;
    }

    public Vacancy() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vacancy vacancy = (Vacancy) o;

        if (name != null ? !name.equals(vacancy.name) : vacancy.name != null) {
            return false;
        }
        if (vacancyurl != null ? !vacancyurl.equals(vacancy.vacancyurl) : vacancy.vacancyurl != null) {
            return false;
        }
        return pageurl != null ? pageurl.equals(vacancy.pageurl) : vacancy.pageurl == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (vacancyurl != null ? vacancyurl.hashCode() : 0);
        result = 31 * result + (pageurl != null ? pageurl.hashCode() : 0);
        return result;
    }
}