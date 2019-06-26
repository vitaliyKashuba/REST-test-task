package vitaliy94.currencyRest.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kevinsawicki.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vitaliy94.currencyRest.model.MonobankCurrency;
import vitaliy94.currencyRest.model.MyCurrency;
import vitaliy94.currencyRest.util.AppUtil;
import vitaliy94.currencyRest.util.CurrencyISOConverter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RESTController
{
    final static String MONOBANK_API = "https://api.monobank.ua/bank/currency";

//    @Autowired
//    CurrencyISOConverter converter;

    @RequestMapping("/")
    public String hello()
    {
        return "hello world";
    }

    @RequestMapping("/currency")
    public String getCurrency(@RequestParam(value = "currencyCode", required=false) String currencyCode) throws IOException
    {
        String uahCode = CurrencyISOConverter.getNumericCodeByChar("UAH");

        // TODO validate param

        ObjectMapper objectMapper = new ObjectMapper();
        HttpRequest request = HttpRequest.get(MONOBANK_API);
        if (request.code() != 200)
        {
            // return error here
        }
        List<MonobankCurrency> currencyList = objectMapper.readValue(request.body(), new TypeReference<List<MonobankCurrency>>(){});   // TODO check exception here
//        currencyList.forEach(c -> {
//            System.out.println(c.getCurrencyCodeA() + " " + c.getCurrencyCodeB() + " " + c.getDate() + " " +  c.getRateBuy() + " " + c.getRateSell() + " " + c.getRateCross());
//        });
        if(currencyCode == null)
        {
            // все курсы, для которых
            // https://api.monobank.ua/bank/currency возвращает значения rateBuy и rateSell.

            List l = currencyList.stream()
                    .filter(c -> c.isRateToUah() && c.hasRates())
                    .map(c -> new MyCurrency(CurrencyISOConverter.getCharCodeByNumeric(c.getCurrencyCodeA()), c.getRateBuy(), c.getRateSell(), c.getDate()))
                    .collect(Collectors.toList());

            return AppUtil.convertToJson(l);
        } else
        {
            MyCurrency mc = currencyList.stream()
                    .filter(c -> c.isRateToUah() && c.getCurrencyCodeA().equals(CurrencyISOConverter.getNumericCodeByChar(currencyCode)))
                    .map(c -> new MyCurrency(CurrencyISOConverter.getCharCodeByNumeric(c.getCurrencyCodeA()), c.getRateBuy(), c.getRateSell(), c.getDate()))
                    .findFirst()
                    .get();

            return AppUtil.convertToJson(mc);
        }
    }
}
