package vitaliy94.currencyRest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class MonobankCurrency extends BasicCurrency
{
    String currencyCodeA;
    String currencyCodeB;
    double rateCross;

    public boolean hasRates()
    {
        return rateSell > 0 && rateBuy > 0;
    }

    public boolean isRateToUah()
    {
        return currencyCodeB.equals("980");
    }
}
