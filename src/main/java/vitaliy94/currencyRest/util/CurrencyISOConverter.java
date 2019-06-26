package vitaliy94.currencyRest.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * class used to convert numeric and char currency codes
 */
@Slf4j
public class CurrencyISOConverter
{
    private static BiMap<String, String> currencyISOCodes; // key - numericCode, value - charCode

    static
    {
        currencyISOCodes = HashBiMap.create();
        try
        {
            String s = AppUtil.readFromResources(AppUtil.FILENAME);
            ObjectMapper mapper = new ObjectMapper();
            currencyISOCodes.putAll(mapper.readValue(s, Map.class));
        } catch (IOException e)
        {
            log.error("probably Jackson error");
            e.printStackTrace();
            // TODO kill process here?
        }
        log.info("ISO currencys: " + currencyISOCodes);
    }

    public static String getCharCodeByNumeric(String numericCode)
    {
        return currencyISOCodes.get(numericCode);
    }

    public static String getNumericCodeByChar(String charCode)
    {
        return currencyISOCodes.inverse().get(charCode);
    }

    public static boolean isValidCharCode(String code)
    {
        return currencyISOCodes.values().contains(code);
    }


}
