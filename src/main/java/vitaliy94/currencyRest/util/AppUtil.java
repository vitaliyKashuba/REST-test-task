package vitaliy94.currencyRest.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.io.Resources;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class AppUtil
{
    public static final String FILENAME = "currencys.json";

    public static void main(String[] args)
    {
        try
        {
            Map isoCurrencys = scrapeISOCurrencys();
            Path file = Paths.get(FILENAME);
            Files.write(file, Arrays.asList(convertToJson(isoCurrencys)), StandardCharsets.UTF_8);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @SneakyThrows   // probably impossible to catch JsonProcessingException while converting to json
    public static String convertToJson(Object o)
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(o);
    }

    /**
     * read text file from resources and return content as string
     */
    public static String readFromResources(String resourceName) throws IOException
    {
        String fileContent;
        fileContent = Resources.toString(Resources.getResource(resourceName), Charset.defaultCharset());
        return fileContent;
    }

    private static Map scrapeISOCurrencys()throws IOException
    {
        final String ISO_CURRENCYS_URL = "https://index.minfin.com.ua/reference/currency/code/";
        final String XPATH_TABLE_SELECTOR = "//*[@id=\"idx-content\"]/table/tbody";

        Map<String, String> currencyISOCodes = new HashMap<>();   // key - numericCode, value - charCode

        try (final WebClient webClient = new WebClient())         // need only to close resources, exceptions not catched
        {
            final HtmlPage page = webClient.getPage(ISO_CURRENCYS_URL);
            HtmlElement n = page.getFirstByXPath(XPATH_TABLE_SELECTOR);
            n.getChildElements().forEach(el -> {
                if(el.getFirstChild().getLocalName().equals("td"))
                {
                    List l = el.getChildNodes().stream().limit(2).collect(Collectors.toList());
                    currencyISOCodes.put(((HtmlElement)l.get(1)).asText(), ((HtmlElement)l.get(0)).asText());
                }
            });
            System.out.println(currencyISOCodes);
        }
        return currencyISOCodes;
    }
}
