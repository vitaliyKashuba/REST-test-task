package vitaliy94.currencyRest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyRestApplicationTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void getCurrencys() throws Exception {
		this.mockMvc.perform(get("/currency")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));
	}

	@Test
	public void getCurrency() throws Exception {
		this.mockMvc.perform(get("/currency?currencyCode=USD")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.currencyCode").value("USD"));
	}

	@Test
	public void getInvalidCurrency() throws Exception {
		this.mockMvc.perform(get("/currency?currencyCode=qwerty")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().is4xxClientError())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.error").value("Invalid currency code \'qwerty\'"));
	}

	@Test
	public void getUAHCurrency() throws Exception {
		this.mockMvc.perform(get("/currency?currencyCode=UAH")
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.currencyCode").value("UAH"))
				.andExpect(jsonPath("$.rateBuy").value("1.0"))
				.andExpect(jsonPath("$.rateSell").value("1.0"));
	}
}
