package vitaliy94.currencyRest.model;

import lombok.Data;

@Data
public class MonobankCurrency extends BasicCurrency
{
    String currencyCodeA;
    String currencyCodeB;
    double rateCross;
}
