package vitaliy94.currencyRest.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kevinsawicki.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vitaliy94.currencyRest.model.MonobankCurrency;

import java.io.IOException;
import java.util.List;

@RestController
public class RESTController
{
    final static String MONOBANK_API = "https://api.monobank.ua/bank/currency";

    @RequestMapping("/")
    public String hello()
    {
        return "hello world";
    }

    @RequestMapping("/currency")
    public String getCurrency(@RequestParam(value = "currencyCode", required=false) String currencyCode) throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpRequest request = HttpRequest.get(MONOBANK_API);
        if (request.code() != 200)
        {
            // return error here
        }
        List<MonobankCurrency> l = objectMapper.readValue(request.body(), new TypeReference<List<MonobankCurrency>>(){});   // TODO check exception here
        l.forEach(c -> {
            System.out.println(c.getCurrencyCodeA() + " " + c.getCurrencyCodeB() + " " + c.getDate() + " " +  c.getRateBuy() + " " + c.getRateSell() + " " + c.getRateCross());
        });
        if(currencyCode == null)
        {
            // данные по курсу валюты.
        } else
        {
            // все курсы, для которых
            // https://api.monobank.ua/bank/currency возвращает значения rateBuy и rateSell.
        }
        return "hello world";
    }
}
