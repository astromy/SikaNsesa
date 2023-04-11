/*package acecontroller;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

*//**
 * Checks for new Items from the Server Database
 *
 * @author user
 *//*
public class SendSMSJSON {

	ExecutorService executorService;
	
	public SendSMSJSON(){
		executorService = Executors.newCachedThreadPool();
	}
	
	public void syncGet( Phone number of client String clientContact,
			 Greeting Message to client String greetings,
			 Client's Name String clientName,  Main sms body  String message) throws Exception {

		String URIMessage= URLEncoder.encode((greetings+ " "+ clientName+ " "+ message), "UTF-8");
		String uri = "https://sms.arkesel.com/sms/api?action=send-sms&api_key=aXR0ZE9nblRHQWx4UmlHenFnVVU=&to="
				+ clientContact.trim() + "&from=KENTEN&sms=" + URIMessage;

		try {
			URL url = new URL(uri);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setConnectTimeout(3000);
			urlConnection.connect();
			int status = urlConnection.getResponseCode();
			InputStream inputStream;
			if (status != HttpURLConnection.HTTP_OK)
				inputStream = urlConnection.getErrorStream();
			else
				inputStream = urlConnection.getInputStream();

			urlConnection.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Future<String> asyncGet( Phone number of client String clientContact,
			 Greeting Message to client String greetings,
			 Client's Name String clientName,  Main sms body  String message) throws Exception 

	 {
		CompletableFuture<String> completableFuture = new CompletableFuture<String>();
		executorService.execute(() -> {
			String uri;
			try {

				String URIMessage= URLEncoder.encode((greetings+ " "+ clientName+ " "+ message), "UTF-8");
				 uri = "https://sms.arkesel.com/sms/api?action=send-sms&api_key=aXR0ZE9nblRHQWx4UmlHenFnVVU=&to="
						+ clientContact.trim() + "&from=KENTEN&sms=" + URIMessage;


				URL url = new URL(uri);
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setConnectTimeout(3000);
				urlConnection.connect();
				Integer status = urlConnection.getResponseCode();
				InputStream inputStream;
				if (status != HttpURLConnection.HTTP_OK)
					inputStream = urlConnection.getErrorStream();
				else
					inputStream = urlConnection.getInputStream();

				urlConnection.disconnect();
				completableFuture.complete(status.toString());
			} catch (Exception e) {
				completableFuture.completeExceptionally(e);
				e.printStackTrace();
			}
		});

		return completableFuture;
	}

}
*/