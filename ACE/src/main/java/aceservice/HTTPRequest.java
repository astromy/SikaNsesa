package aceservice;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Component
@Service
@Transactional
public class HTTPRequest {

	public String post(String URL, String XREF, String OCPKEY, String Auth,List<NameValuePair> paramList ) throws ClientProtocolException, IOException{
	HttpClient httpclient = HttpClients.createDefault();
	HttpPost httppost = new HttpPost(URL);
	httppost.addHeader("X-Reference-Id", XREF);
	httppost.addHeader("Ocp-Apim-Subscription-Key", OCPKEY);
	httppost.addHeader("Authorization", Auth);
	httppost.addHeader("", "");

	// Request parameters and other properties.
	List<NameValuePair> params = new ArrayList<NameValuePair>();
	for (NameValuePair p : paramList) {
		params.add(new BasicNameValuePair(p.getName(), p.getValue()));
	}
	httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

	//Execute and get the response.
	HttpResponse response = httpclient.execute(httppost);
	HttpEntity entity = response.getEntity();

	if (entity != null) {
	    try (InputStream instream = entity.getContent()) {
	        // do something useful
	    }
	}
	return"";
	}

	public String post(String URL, String XREF, String OCPKEY ) throws ClientProtocolException, IOException{
	HttpClient httpclient = HttpClients.createDefault();
	HttpPost httppost = new HttpPost(URL);
	httppost.addHeader("X-Reference-Id", XREF);
	httppost.addHeader("Ocp-Apim-Subscription-Key", OCPKEY);
	httppost.addHeader("", "");

	// Request parameters and other properties.
	List<NameValuePair> params = new ArrayList<NameValuePair>(2);
	params.add(new BasicNameValuePair("param-1", "12345"));
	params.add(new BasicNameValuePair("param-2", "Hello!"));
	httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

	//Execute and get the response.
	HttpResponse response = httpclient.execute(httppost);
	HttpEntity entity = response.getEntity();

	if (entity != null) {
	    try (InputStream instream = entity.getContent()) {
	        // do something useful
	    }
	}
	return"";
	}

	public String post(String URL, String XREF ) throws ClientProtocolException, IOException{
	HttpClient httpclient = HttpClients.createDefault();
	HttpPost httppost = new HttpPost(URL);
	httppost.addHeader("X-Reference-Id", XREF);
	httppost.addHeader("", "");

	// Request parameters and other properties.
	List<NameValuePair> params = new ArrayList<NameValuePair>(2);
	params.add(new BasicNameValuePair("param-1", "12345"));
	params.add(new BasicNameValuePair("param-2", "Hello!"));
	httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

	//Execute and get the response.
	HttpResponse response = httpclient.execute(httppost);
	HttpEntity entity = response.getEntity();

	if (entity != null) {
	    try (InputStream instream = entity.getContent()) {
	        // do something useful
	    }
	}
	return"";
	}

	public String post(String URL ) throws ClientProtocolException, IOException{
	HttpClient httpclient = HttpClients.createDefault();
	HttpPost httppost = new HttpPost(URL);
	httppost.addHeader("", "");

	// Request parameters and other properties.
	List<NameValuePair> params = new ArrayList<NameValuePair>(2);
	params.add(new BasicNameValuePair("param-1", "12345"));
	params.add(new BasicNameValuePair("param-2", "Hello!"));
	httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

	//Execute and get the response.
	HttpResponse response = httpclient.execute(httppost);
	HttpEntity entity = response.getEntity();

	if (entity != null) {
	    try (InputStream instream = entity.getContent()) {
	        // do something useful
	    }
	}
	return"";
	}
	
	@SuppressWarnings("unused")
	public String JsonPost(String URL, List<NameValuePair>headers, String payload) {

        String data = "";
        String response="";
        StringBuffer sb = new StringBuffer("");
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(URL).openConnection();
            httpURLConnection.setRequestMethod("POST");
            for (NameValuePair p : headers) {
                httpURLConnection.setRequestProperty("'"+p.getName()+"'", "'"+p.getValue()+"'");
			}
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes(payload);
            wr.flush();
            wr.close();

            InputStream inputStream;

            int status = httpURLConnection.getResponseCode();

            if (status != HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getErrorStream();
            } else
                inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new
                    InputStreamReader(inputStream);

            int inputStreamData = inputStreamReader.read();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            while (inputStreamData != -1) {
                char current = (char) inputStreamData;
                inputStreamData = inputStreamReader.read();
                data += current;
            }
            response=data;

        } catch (Exception e) {
            e.toString();
        } finally {
           // subscribed = Boolean.parseBoolean(data);
        }
        return response;
    }

	
	@SuppressWarnings("unused")
	public String JsonGet(String URL, List<NameValuePair>headers, String payload) {

        String data = "";
        String response="";
        StringBuffer sb = new StringBuffer("");
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(URL).openConnection();
            httpURLConnection.setRequestMethod("GET");
            for (NameValuePair p : headers) {
                httpURLConnection.setRequestProperty("'"+p.getName()+"'", "'"+p.getValue()+"'");
			}
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes(payload);
            wr.flush();
            wr.close();

            InputStream inputStream;

            int status = httpURLConnection.getResponseCode();

            if (status != HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getErrorStream();
            } else
                inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new
                    InputStreamReader(inputStream);

            int inputStreamData = inputStreamReader.read();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            while (inputStreamData != -1) {
                char current = (char) inputStreamData;
                inputStreamData = inputStreamReader.read();
                data += current;
            }
            response=data;

        } catch (Exception e) {
            e.toString();
        } finally {
           // subscribed = Boolean.parseBoolean(data);
        }
        return response;
    }

}
