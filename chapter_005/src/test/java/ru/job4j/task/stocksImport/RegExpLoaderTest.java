package ru.job4j.task.stocksImport;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO: comment
 * @author parsentev
 * @since 24.10.2015
 */
public class RegExpLoaderTest {

    @Ignore
    @org.junit.Test
    public void testParse() throws Exception {
        RegExpLoader loader = new RegExpLoader();
        final Order order = loader.parse("<AddOrder book=\"book-1\" operation=\"SELL\" price=\"100.50\" volume=\"81\" orderId=\"1\" />", true);
        assertThat(order.id, is(1));
    }
}
