package acecontroller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import models.CurrencyRates;
import repository.CurrencyRateRepository;
import repository.OperatingCountryRepository;

@Component
@Slf4j

public class Cron {
	private final Logger log = Logger.getLogger(this.getClass());
	@Autowired
	OperatingCountryRepository operatingCountry;
	@Autowired
	CurrencyRateRepository currencyRateRepository;

	ExecutorService executorService = Executors.newFixedThreadPool(20);
	// CurrencyRateRepository currencyRateRepository;
	// String Key="0fe33a211c9b6650c68a7f57";

	// (cron = sec min hr dd mm week ?)
	 @Scheduled(cron = "1 0 0 * * ?")
	public void fetchCurrencyRates() {
		ArrayList<CurrencyRates> countriesCurrency = new ArrayList<CurrencyRates>();
		ArrayList<String> currencyList = new ArrayList<>();
		currencyList = operatingCountry.findUniqueCountryList();
		String[] result = null;
		for (String currency : currencyList) {

			ArrayList<String> currencies = new ArrayList<>();
			try {
				result = getHTML(currency).split(":\\{")[1].replaceAll("\\}", "").split(",");

				for (String currencyResult : result) {
					for (String cur : currencyList) {
						if (currencyResult.contains(cur)) {
							currencies.add(currencyResult);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("ORDER ERROR --> " + e.toString());
			}
			for(int i=0; i<currencies.size();i++){
			CurrencyRates cr =new CurrencyRates();
				cr.setDate(new Date());
				cr.setFromCurrency(currency);
				cr.setToCurrency(currencies.get(i).split(":")[0].replaceAll("\"", "").trim().toString());
				cr.setRate(Double.parseDouble(currencies.get(i).split(":")[1]));
				countriesCurrency.add(cr);
			}
		}
		currencyRateRepository.save(countriesCurrency);
	}

	/* End Of Morning Cron */


	 //@Scheduled(cron = "*/2 * * * * ?")
	public void sendLunchNotification() throws ClientProtocolException, IOException {
		 
	}

	/* End Of LunchTime Cron */

	/**
	 * Friday Cron
	 */
	
	// @Scheduled(cron = "0 0 7 * * FRI")
	public void sendClientSMS() {
	}

	/* End Of Friday Cron */

	/**
	 * Monday Cron
	 */
	
	// @Scheduled(cron = "0 0 7 * * WED")
	public void sendClientSMS_MON() {
	}

	/* End Of Monday Cron */

	public static String executePost(String country) {
		HttpURLConnection connection = null;
		String targetURL = "";
		try {
			String URIMessage = URLEncoder.encode((country), "UTF-8");
			targetURL = "https://v6.exchangerate-api.com/v6/0fe33a211c9b6650c68a7f57/latest/";
			// Create connection
			URL url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");

			connection.setRequestProperty("Content-Length", Integer.toString(URIMessage.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(URIMessage);
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder(); // or StringBuffer if
															// Java version 5+
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	// public Future<String> asyncGet(String country)
	public String asyncGet(String country)

	{
		String uri;
		String data = "";
		StringBuffer sb = new StringBuffer("");
		HttpURLConnection httpURLConnection = null;
		try {
			String URIMessage = URLEncoder.encode((country), "UTF-8");
			uri = "https://v6.exchangerate-api.com/v6/0fe33a211c9b6650c68a7f57/latest/" + URIMessage.trim();

			httpURLConnection = (HttpURLConnection) new URL(uri).openConnection();
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setRequestProperty("Content-Type", "application/json");
			// httpURLConnection.setRequestProperty("Accept",
			// "application/json");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);

			DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
			wr.flush();
			wr.close();

			InputStream inputStream;
			int status = httpURLConnection.getResponseCode();

			if (status != HttpURLConnection.HTTP_OK) {
				inputStream = httpURLConnection.getErrorStream();
			} else
				inputStream = httpURLConnection.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			while ((data = br.readLine()) != null) {
				sb.append(data);
			}
		} catch (Exception e) {
			// return e.toString();
		} finally {
			String[] res = sb.toString().split(",");
			if (res.length >= 1 && sb.length() > 2) {
				for (String itm : res) {
					String temp = itm.replaceAll("\\[\\[", "");
					temp = temp.replaceAll("\\[", "");
					temp = temp.replaceAll("\"/,\\]", "");
					if (temp.substring(0, 2).contains((","))) {
						temp = temp.substring(1);
					}
					temp = temp.replaceAll("\"", "").trim();
				}
			}
			httpURLConnection.disconnect();
		}
		// return data;

		return data;
	}

	public static String getHTML(String country) throws Exception {
		String urlToRead = "https://v6.exchangerate-api.com/v6/0fe33a211c9b6650c68a7f57/latest/" + country;
		StringBuilder result = new StringBuilder();
		URL url = new URL(urlToRead);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
			for (String line; (line = reader.readLine()) != null;) {
				result.append(line);
			}
		}
		return result.toString();
	}

}
