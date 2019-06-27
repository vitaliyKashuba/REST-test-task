package vitaliy94.currencyRest.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class MyCurrencyTest {

    @Test
    public void getDateInISO8601Format()
    {
        Date d = new Date(1);
        MyCurrency c = new MyCurrency("UAH", 1, 1, d);
        assertEquals("1970-01-01", c.getDateInISO8601Format());
    }
}