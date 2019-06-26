package vitaliy94.currencyRest.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kevinsawicki.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vitaliy94.currencyRest.model.ErrorMesage;
import vitaliy94.currencyRest.model.MonobankCurrency;
import vitaliy94.currencyRest.model.MyCurrency;
import vitaliy94.currencyRest.util.AppUtil;
import vitaliy94.currencyRest.util.CurrencyISOConverter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class RESTController
{
    private final static String MONOBANK_API = "https://api.monobank.ua/bank/currency";

    @RequestMapping(value = "/currency", produces = "application/json")
    public ResponseEntity getCurrency(@RequestParam(value = "currencyCode", required=false) String currencyCode)
    {
        if (currencyCode != null && !CurrencyISOConverter.isValidCharCode(currencyCode))                                // input validation
        {
            return new ResponseEntity<>(new ErrorMesage("Invalid currency code \'" + currencyCode + "\'") , HttpStatus.BAD_REQUEST);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        HttpRequest request = HttpRequest.get(MONOBANK_API);
        List<MonobankCurrency> currencyList;

        if (request.code() != 200)
        {
            log.error(request.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);                                              // failed to request monobank api
        }

        try {
            currencyList = objectMapper.readValue(request.body(), new TypeReference<List<MonobankCurrency>>() {});
        } catch (IOException e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(currencyCode == null)
        {
            List l = currencyList.stream()
                    .filter(c -> c.isRateToUah() && c.hasRates())
                    .map(c -> new MyCurrency(CurrencyISOConverter.getCharCodeByNumeric(c.getCurrencyCodeA()), c.getRateBuy(), c.getRateSell(), c.getDate()))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(l, HttpStatus.OK);
        } else
        {
            String curCodeNumeric = CurrencyISOConverter.getNumericCodeByChar(currencyCode);

            MyCurrency mc = currencyList.stream()
                    .filter(c -> c.isRateToUah() && c.getCurrencyCodeA().equals(curCodeNumeric))
                    .map(c -> new MyCurrency(currencyCode, c.getRateBuy(), c.getRateSell(), c.getDate()))
                    .findFirst()
                    .get();

            return new ResponseEntity<>(mc, HttpStatus.OK);
        }
    }
}
