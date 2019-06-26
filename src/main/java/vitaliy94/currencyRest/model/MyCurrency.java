package vitaliy94.currencyRest.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * this currency shows rates only to UAH
 */
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

    @JsonGetter("date")
    public String getDateInISO8601Format()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        return dateFormat.format(this.date);
    }
}
