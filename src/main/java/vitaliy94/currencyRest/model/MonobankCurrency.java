package vitaliy94.currencyRest.model;

import lombok.Data;
import vitaliy94.currencyRest.util.CurrencyISOConverter;

/**
 * data class for monobank currencys
 */
@Data
public class MonobankCurrency extends BasicCurrency
{
    String uahCode = CurrencyISOConverter.getNumericCodeByChar("UAH");

    String currencyCodeA;
    String currencyCodeB;
    double rateCross;

    /**
     * @return true if currency has sell and buy rates
     */
    public boolean hasRates()
    {
        return rateSell > 0 && rateBuy > 0;
    }

    /**
     * @return true if this is currency between UAH and smth else
     */
    public boolean isRateToUah()
    {
        return currencyCodeB.equals(uahCode);
    }
}
