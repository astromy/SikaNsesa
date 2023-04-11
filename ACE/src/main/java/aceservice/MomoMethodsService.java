package aceservice;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
@Service
@Transactional
public class MomoMethodsService {
	@Autowired
	HTTPRequest httpRequest;
	
	String OcpApimSubscriptionKey="00fa61ad60304d52aca078c44cd049bd";
	String callBackURL="";
	
public String XRefId() {
	UUID xidentifier =UUID.randomUUID();
	String xidentifierAsString=xidentifier.toString();	
return xidentifierAsString;	
}

public String APIKey(){
	
	return "";
}

public String AccessToken(){
	
	return "";
}

public String CreateAPIUser(){
	String xRefId=XRefId();
	List<NameValuePair> headerList= new ArrayList<NameValuePair>();
	headerList.add(new BasicNameValuePair("X-Reference-Id", xRefId));
	headerList.add(new BasicNameValuePair("Ocp-Apim-Subscription-Key", OcpApimSubscriptionKey));
	String URL="";
	
	List<JSONObject> payloadList = new ArrayList<JSONObject>();
	JSONObject payload = new JSONObject();
	payloadList.add(payload);
	URL="https://sandbox.momodeveloper.mtn.com/v1_0/apiuser";
	payload.put("providerCallbackHost", "https://webhook.site/04455b3b-72d2-4dbd-9b14-c8e681022eb1");
	String response = httpRequest.JsonPost(URL,headerList,payloadList.toString());//post("", xRefId, OcpApimSubscriptionKey);
	return response;
 }

public String GetAPIUser(){
	List<NameValuePair> headerList= new ArrayList<NameValuePair>();
	headerList.add(new BasicNameValuePair("Ocp-Apim-Subscription-Key", OcpApimSubscriptionKey));
	String URL="";
	
	List<JSONObject> payloadList = new ArrayList<JSONObject>();
	JSONObject payload = new JSONObject();
	payloadList.add(payload);
	URL="https://sandbox.momodeveloper.mtn.com/v1_0/apiuser/"+ XRefId();
	payload.put("providerCallbackHost", "https://webhook.site/04455b3b-72d2-4dbd-9b14-c8e681022eb1");
	payload.put("targetEnviroment", "sandbox");
	String response = httpRequest.JsonGet(URL,headerList,payloadList.toString());//post("", xRefId, OcpApimSubscriptionKey);
	return response;
}







}


