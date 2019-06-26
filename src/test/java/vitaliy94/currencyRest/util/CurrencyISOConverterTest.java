package vitaliy94.currencyRest.util;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class CurrencyISOConverterTest {

    CurrencyISOConverter converter = new CurrencyISOConverter();

    @Test
    public void getCharCodeByNumeric()
    {
        assertEquals("UAH", converter.getCharCodeByNumeric("980"));
    }

    @Test
    public void getNumericCodeByChar()
    {
        assertEquals("980", converter.getNumericCodeByChar("UAH"));
    }
}