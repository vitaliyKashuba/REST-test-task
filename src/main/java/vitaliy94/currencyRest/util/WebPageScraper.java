//package vitaliy94.currencyRest.util;
//
//import com.gargoylesoftware.htmlunit.WebClient;
//import com.gargoylesoftware.htmlunit.html.HtmlElement;
//import com.gargoylesoftware.htmlunit.html.HtmlPage;
//import com.google.common.collect.BiMap;
//import com.google.common.collect.HashBiMap;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Component
//public class WebPageScraper
//{
//    public Map getCurrencysISOCodes()
//    {
//        Map<String, String> currencyISOCodes = new HashMap<>();   // key - numericCode, value - charCode
//
//        try (final WebClient webClient = new WebClient())
//        {
//            final HtmlPage page = webClient.getPage("https://index.minfin.com.ua/reference/currency/code/");
//            HtmlElement n = page.getFirstByXPath("//*[@id=\"idx-content\"]/table/tbody");
//            n.getChildElements().forEach(el -> {
//                if(el.getFirstChild().getLocalName().equals("td"))
//                {
//                    List l = el.getChildNodes().stream().limit(2).collect(Collectors.toList());
//                    currencyISOCodes.put(((HtmlElement)l.get(1)).asText(), ((HtmlElement)l.get(0)).asText());
//                }
//            });
//            System.out.println(currencyISOCodes);
//        } catch (MalformedURLException e)
//        {
//            e.printStackTrace();
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//
//        return currencyISOCodes;
//    }
//}
