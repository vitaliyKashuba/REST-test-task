package vitaliy94.currencyRest.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.util.Date;

@Data
abstract class BasicCurrency
{
    double rateBuy;
    double rateSell;
    Date date;

    @JsonSetter("date")
    public void setDateFromUnixTime(int unixTime) {
        this.date = new java.util.Date((long)unixTime*1000);    // because java use milliseconds
    }
}


