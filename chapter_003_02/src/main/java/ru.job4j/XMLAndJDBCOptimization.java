package ru.job4j;


import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.sql.*;

//WORK ON BUGS разбей на блоки.
//        1. блок чтения
//        2. запись в базу
//        3. xslt.
public class XMLAndJDBCOptimization implements Serializable {

    private String url;

    private String username;
    private String password;
    private int n;

    private Connection conn;
    private long start;

    public XMLAndJDBCOptimization(String url, String username, String password, int n) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.n = n;

        try {
            conn = DriverManager.getConnection(this.getUrl(), this.getUsername(), this.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        XMLAndJDBCOptimization xmlAndJDBCOptimization = new XMLAndJDBCOptimization(
                "jdbc:postgresql://localhost:5432/SQLite", "postgres", "password", 100000);
        xmlAndJDBCOptimization.start = System.currentTimeMillis();
        int n = xmlAndJDBCOptimization.getN(); // 1000000;

        Connection conn = xmlAndJDBCOptimization.getConn();

        xmlAndJDBCOptimization.createAndFillBase(xmlAndJDBCOptimization);
        xmlAndJDBCOptimization.createFirstXML(xmlAndJDBCOptimization);
        xmlAndJDBCOptimization.createSecondXMLFromFirstXMLByXSLConvertation(xmlAndJDBCOptimization);
        xmlAndJDBCOptimization.getArithmeticSummAndDeleteAllFiles(xmlAndJDBCOptimization);

        try {
            xmlAndJDBCOptimization.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long getStart() {
        return start;
    }

    public XMLAndJDBCOptimization() {

    }

    public void setStart(long start) {
        this.start = start;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    private ResultSet readFromBase(String query) {
        Statement st = null;
        try {
            st = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = st.executeQuery("SELECT * FROM TEST");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    private void writeInBase(String query) {
        Statement st = null;
        try {
            st = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            st.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int[] executeBatchInBase() {
        int[] count = null;
        Statement st = null;
        try {
            st = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= n; i++) {
            try {
                st.addBatch("insert into TEST (FIELD) values (" + i + ");");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            count = st.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    private void createAndFillBase(XMLAndJDBCOptimization xmlAndJDBCOptimization) {

        try {
            Statement st = conn.createStatement();
            conn.setAutoCommit(false); //55.55

            ResultSet rs = xmlAndJDBCOptimization.readFromBase("SELECT * FROM TEST");

            if (rs == null) {
                xmlAndJDBCOptimization.writeInBase("CREATE TABLE TEST (FIELD INTEGER )");
                xmlAndJDBCOptimization.writeInBase("insert into TEST (FIELD) values (1)");
                rs = xmlAndJDBCOptimization.readFromBase("SELECT * FROM TEST");
            }

            System.out.println("1 SELECT * FROM TEST time: " + (xmlAndJDBCOptimization.start - System.currentTimeMillis()) / 1000);
            if (rs.next()) {
                xmlAndJDBCOptimization.writeInBase("delete  from TEST");
            }
            System.out.println("2 delete  from TEST time: " + (xmlAndJDBCOptimization.start - System.currentTimeMillis()) / 1000);

            int[] count = xmlAndJDBCOptimization.executeBatchInBase(); //st.executeBatch();

            conn.commit();

            System.out.println("4 execute query  time: " + (xmlAndJDBCOptimization.start - System.currentTimeMillis()) / 1000);
            System.out.println("number of created rows: " + count.length);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void createFolderForTask() {
        File file = new File("task20459");
        if (!file.exists()) {
            file.mkdir();
        }
    }

    private void createSecondXMLFromFirstXMLByXSLConvertation(XMLAndJDBCOptimization xmlAndJDBCOptimization) {

        try {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("task20459\\xslttest.xsl"), "utf-8"))) {
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                        + "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n"
                        + "    <xsl:output method=\"xml\" indent=\"yes\"/>\n"
                        + "\n"
                        + "    <xsl:template match=\"/entries\">\n"
                        + "        <entries>\n"
                        + "            <xsl:apply-templates select=\"entry\"/>\n"
                        + "        </entries>\n"
                        + "    </xsl:template>\n"
                        + "\n"
                        + "    <xsl:template match=\"entry\">\n"
                        + "        <entry field=\"{field}\">\n"
                        + "\n"
                        + "        </entry>\n"
                        + "    </xsl:template>\n"
                        + "\n"
                        + "</xsl:stylesheet>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //-
        //+
        TransformerFactory tff = TransformerFactory.newInstance();
        Transformer tf = null;
        try {
            tf = tff.newTransformer(new StreamSource(new File("task20459\\xslttest.xsl")));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        StreamSource ss = new StreamSource(new File("task20459\\1.xml"));
        StreamResult sr = new StreamResult(new File("task20459\\2.xml"));
        try {
            tf.transform(ss, sr);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        System.out.println("File2 saved!");
        System.out.println("7 time: " + (xmlAndJDBCOptimization.start - System.currentTimeMillis()) / 1000);

    }

    private void getArithmeticSummAndDeleteAllFiles(XMLAndJDBCOptimization xmlAndJDBCOptimization) {

        //++++5. Приложение парсит "2.xml" и выводит арифметическую сумму значений всех атрибутов field в консоль.
        int arithmeticSumm = 0;
        File f = new File("task20459\\2.xml");
        FileReader r = null;
        try {
            r = new FileReader(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(r);
        String line = null;
        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final char dm = (char) 34;
        try {
            while (null != (line = br.readLine())) {
                String[] arraySting = line.split("ield=" + dm);
                if (arraySting.length == 1) {
                    continue;
                }
                String[] arraySting2 = arraySting[1].split(dm + "/>");
                int resultValue = Integer.valueOf(arraySting2[0]);
                arithmeticSumm = arithmeticSumm + resultValue;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            r.close(); // без этого не удавалось удалить папку
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("8 time of parsing: " + (xmlAndJDBCOptimization.start - System.currentTimeMillis()) / 1000);
        System.out.println("arithmeticSumm = [" + arithmeticSumm + "]");
        //----5. Приложение парсит "2.xml" и выводит арифметическую сумму значений всех атрибутов field в консоль.
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - xmlAndJDBCOptimization.start;

        recursiveDelete(new File("task20459"));

        System.out.println("Total time in sec. = " + timeConsumedMillis / 1000);

    }

    private void createFirstXML(XMLAndJDBCOptimization xmlAndJDBCOptimization) {
        ResultSet rs = xmlAndJDBCOptimization.readFromBase("SELECT * FROM TEST");
        System.out.println("5 SELECT * FROM TEST  time: " + (xmlAndJDBCOptimization.start - System.currentTimeMillis()) / 1000);
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("entries");
        doc.appendChild(rootElement);
        try {
            while (rs.next()) {
                Element entry = doc.createElement("entry");
                rootElement.appendChild(entry);
                Element field = doc.createElement("field");
                field.appendChild(doc.createTextNode(String.valueOf(rs.getInt("FIELD"))));
                entry.appendChild(field);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        xmlAndJDBCOptimization.createFolderForTask();

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("task20459\\1.xml"));
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        System.out.println("File saved!");
        System.out.println("6 time: " + (xmlAndJDBCOptimization.start - System.currentTimeMillis()) / 1000);
        //-
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void recursiveDelete(File file) {
        // до конца рекурсивного цикла
        if (!file.exists()) {
            return;
        }


        //если это папка, то идем внутрь этой папки и вызываем рекурсивное удаление всего, что там есть
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                // рекурсивный вызов
                recursiveDelete(f);
            }
        }
        // вызываем метод delete() для удаления файлов и пустых(!) папок
        file.delete();
        System.out.println("Удаленный файл или папка: " + file.getAbsolutePath());
    }

    @Override
    public String toString() {
        return "XMLAndJDBCOptimization{"
                +
                "url='" + url + '\''
                +
                ", username='" + username + '\''
                +
                ", password='" + password + '\''
                +
                ", n=" + n
                +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        XMLAndJDBCOptimization that = (XMLAndJDBCOptimization) o;

        if (n != that.n) {
            return false;
        }
        if (url != null ? !url.equals(that.url) : that.url != null) {
            return false;
        }
        if (username != null ? !username.equals(that.username) : that.username != null) {
            return false;
        }
        return password != null ? password.equals(that.password) : that.password == null;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + n;
        return result;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}