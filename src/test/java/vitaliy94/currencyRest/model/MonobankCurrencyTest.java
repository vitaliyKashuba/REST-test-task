package vitaliy94.currencyRest.model;

import org.junit.Test;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;

public class MonobankCurrencyTest {

    @Test
    public void hasRates()
    {
        MonobankCurrency m = new MonobankCurrency();

        assertFalse(m.hasRates());
        m.setRateBuy(10);
        assertFalse(m.hasRates());
        m = new MonobankCurrency();
        m.setRateSell(10);
        assertFalse(m.hasRates());
        m.setRateBuy(10);

        assertTrue(m.hasRates());
    }

    @Test
    public void isRateToUah()
    {
        MonobankCurrency m = new MonobankCurrency();
        m.setCurrencyCodeB("978");
        assertFalse(m.isRateToUah());
        m.setCurrencyCodeB("980");
        assertTrue(m.isRateToUah());
    }
}