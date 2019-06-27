package vitaliy94.currencyRest.util;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class CurrencyISOConverterTest {

    @Test
    public void getCharCodeByNumeric()
    {
        assertEquals("UAH", CurrencyISOConverter.getCharCodeByNumeric("980"));
    }

    @Test
    public void getNumericCodeByChar()
    {
        assertEquals("980", CurrencyISOConverter.getNumericCodeByChar("UAH"));
    }

    @Test
    public void isValidCharCode()
    {
        assertTrue(CurrencyISOConverter.isValidCharCode("UAH"));
        assertFalse(CurrencyISOConverter.isValidCharCode("UA"));
    }
}