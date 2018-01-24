package ru.job4j;


import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.sql.*;

public class XMLAndJDBCOptimization implements Serializable {

    private String url;

    private String username;
    private String password;
    private int n;

    public XMLAndJDBCOptimization() {

    }

    public XMLAndJDBCOptimization(String url, String username, String password, int n) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.n = n;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        XMLAndJDBCOptimization xmlAndJDBCOptimization = new XMLAndJDBCOptimization(
                "jdbc:postgresql://localhost:5432/SQLite", "postgres", "password", 1000000);
        int n = xmlAndJDBCOptimization.getN(); // 1000000;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(xmlAndJDBCOptimization.getUrl(), xmlAndJDBCOptimization.getUsername(), xmlAndJDBCOptimization.getPassword());
            Statement st = conn.createStatement();
            conn.setAutoCommit(false); //55.55
            ResultSet rs;
            try {
                rs = st.executeQuery("SELECT * FROM TEST");
            } catch (SQLException e) {
                st.execute("CREATE TABLE TEST (FIELD INTEGER )");
                st.execute("insert into TEST (FIELD) values (1)");
                rs = st.executeQuery("SELECT * FROM TEST");
            }
            System.out.println("1 SELECT * FROM TEST time: " + (start - System.currentTimeMillis()) / 1000);
            if (rs.next()) {
                try {
                    st.execute("delete  from TEST");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("2 delete  from TEST time: " + (start - System.currentTimeMillis()) / 1000);
            for (int i = 1; i <= n; i++) {
                st.addBatch("insert into TEST (FIELD) values (" + i + ");");
            }
            int[] count = st.executeBatch();
            conn.commit();
            System.out.println("4 execute query  time: " + (start - System.currentTimeMillis()) / 1000);
            System.out.println("number of created rows: " + count.length);
            //+
            rs = st.executeQuery("SELECT * FROM TEST");
            System.out.println("5 SELECT * FROM TEST  time: " + (start - System.currentTimeMillis()) / 1000);
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("entries");
            doc.appendChild(rootElement);
            while (rs.next()) {
                Element entry = doc.createElement("entry");
                rootElement.appendChild(entry);
                Element field = doc.createElement("field");
                field.appendChild(doc.createTextNode(String.valueOf(rs.getInt("FIELD"))));
                entry.appendChild(field);
            }
            File file = new File("task20459");
            if (!file.exists()) {
                file.mkdir();
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("task20459\\1.xml"));
            transformer.transform(source, result);
            System.out.println("File saved!");
            System.out.println("6 time: " + (start - System.currentTimeMillis()) / 1000);
            //-
            rs.close();
            st.close();
            //+
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
            //-
            //+
            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer tf = tff.newTransformer(new StreamSource(new File("task20459\\xslttest.xsl")));
            StreamSource ss = new StreamSource(new File("task20459\\1.xml"));
            StreamResult sr = new StreamResult(new File("task20459\\2.xml"));
            tf.transform(ss, sr);
            System.out.println("File2 saved!");
            System.out.println("7 time: " + (start - System.currentTimeMillis()) / 1000);
            //-
            //++++5. Приложение парсит "2.xml" и выводит арифметическую сумму значений всех атрибутов field в консоль.
            int arithmeticSumm = 0;
            File f = new File("task20459\\2.xml");
            FileReader r = new FileReader(f);
            BufferedReader br = new BufferedReader(r);
            String line = br.readLine();
            final char dm = (char) 34;
            while (null != (line = br.readLine())) {
                String[] arraySting = line.split("ield=" + dm);
                if (arraySting.length == 1) {
                    continue;
                }
                String[] arraySting2 = arraySting[1].split(dm + "/>");
                int resultValue = Integer.valueOf(arraySting2[0]);
                arithmeticSumm = arithmeticSumm + resultValue;
            }
            r.close(); // без этого не удавалось удалить папку
            System.out.println("8 time of parsing: " + (start - System.currentTimeMillis()) / 1000);
            System.out.println("arithmeticSumm = [" + arithmeticSumm + "]");
            //----5. Приложение парсит "2.xml" и выводит арифметическую сумму значений всех атрибутов field в консоль.
            long finish = System.currentTimeMillis();
            long timeConsumedMillis = finish - start;

            recursiveDelete(new File("task20459"));

            System.out.println("Total time in sec. = " + timeConsumedMillis / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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