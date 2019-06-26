package vitaliy94.currencyRest.model;

import lombok.Data;

@Data
public class MonobankCurrency
{
    String currencyCodeA;
    String currencyCodeB;
    String date;
    double rateBuy;
    double rateSell;
    double rateCross;
}
