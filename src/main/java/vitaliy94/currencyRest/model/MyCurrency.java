package vitaliy94.currencyRest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
public class MyCurrency extends BasicCurrency
{
    String currencyCode;

    public MyCurrency(String currencyCode, double rateBuy, double rateSell, Date date) // lombok can't generate constructor with fields of super class init
    {
        this.currencyCode = currencyCode;
        this.rateBuy = rateBuy;
        this.rateSell = rateSell;
        this.date = date;
    }
}
