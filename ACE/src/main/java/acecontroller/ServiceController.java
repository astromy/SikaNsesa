package acecontroller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import aceservice.ACEService;
import dto.BankTransactionDTO;
import dto.TransferOrderDTO;
import models.OperatingCountries;

@RestController
@Controller
public class ServiceController {
	private final Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	ACEService aceService;
	OperatingCountries peratingCountries = null;

	
	// ************************* REST SECTION *********************

	@RequestMapping(value = { "/rto" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> RTO(Authentication auth, @RequestBody TransferOrderDTO jsonString) {
		
		return aceService.RTO(jsonString);
	}

	@ResponseBody
	@RequestMapping(value = { "/servicePost/gcmr" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Double getCommissionRate() {
	return	aceService.getCommissionRate();

	}

	@ResponseBody
	@RequestMapping(value = { "/servicePost/allCountries" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Object> getAllCountries() {
		return aceService.getAllCountries();
	}

	@ResponseBody
	@RequestMapping(value = { "/servicePost/gcvr" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ArrayList<byte[]> getConversionRate(@RequestBody String currencies, HttpServletRequest request) {
		log.info(request.getRemoteAddr());;
		return aceService.getConversionRate(currencies);
	}

	@ResponseBody
	@RequestMapping(value = { "/servicePost/ca" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Boolean checkAmnt(@RequestBody String shopCords) {
		
		return aceService.checkAmnt(shopCords);

	}

	@ResponseBody
	@RequestMapping(value = { "/bapi/cdt" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> PBT(@RequestBody  BankTransactionDTO jsonString) {
		
		return aceService.BankTransaction(jsonString);
	}

	/*@ResponseBody
	@RequestMapping(value = { "/servicePost/deviceToken" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Boolean UpdateToken(@RequestBody String deviceToken) {
		Boolean subscribed = false;
		log.info("NE USERS Device Token-->" + deviceToken);
		try {} catch (Exception e) {
			log.error("Update Token error-->" + e.toString());
		}
		return subscribed;
	}*/

	/*@ResponseBody
	@RequestMapping(value = { "/servicePost/createClient" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String createClient(@RequestBody String deviceId) {
		try {} catch (Exception e) {
			log.error("Create Client Error-->" + e.toString());
		}
		return null;
	}*/

	/*@ResponseBody
	@RequestMapping(value = { "/servicePost/getKentenUserLocation" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Object getKentenUserLocation(@RequestBody String phone) {
		
		return null;
	}*/

	/*@ResponseBody
	@RequestMapping(value = { "/servicePost/makePayment" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Object makePayment(@RequestBody String phone) {
		
		return null;
	}*/

	/*@ResponseBody
	@RequestMapping(value = { "/servicePost/makeUSSDPayment" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Object makeUSSDPayment(@RequestBody String orderNumber) {
		
		return "Kenten USSD payment error";
	}*/

	/*@ResponseBody
	@RequestMapping(value = { "/servicePost/creditUSSDPayment" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Object creditUSSDPayment(@RequestBody String orderNumber) {
		orderNumber = orderNumber.split(":")[1].replace("}", "").replace("{", "").replaceAll("\"", "")
				.replaceAll("\n", "").trim();

		return "Kenten Credit USSD payment error";
	}*/

	/*@ResponseBody
	@RequestMapping(value = { "/servicePost/servicePromo" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Object servicePromo() {
		
			return null;
	}*/

	/*@ResponseBody
	@RequestMapping(value = { "/servicePost/updateContact" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Object updateContact(@RequestBody String phone) {

		return "Failed";
	}*/

	/*@ResponseBody
	@RequestMapping(value = { "/servicePost/updateName" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Object updateName(@RequestBody String userName) {

		return "Failed";
	}*/

	/*@ResponseBody
	@RequestMapping(value = { "/servicePost/updateEmail" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Object updateEmail(@RequestBody String userEmail) {

		return "Failed";
	}*/

	/*@ResponseBody
	@RequestMapping(value = { "/servicePost/changePhone" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String changePhone(@RequestBody String phone) {

		return "Failed";
	}*/

	/*@ResponseBody
	@RequestMapping(value = { "/servicePost/checkWallet" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Double checkWallet(@RequestBody String phone) {

		return 0.0;
	}*/

	/*@ResponseBody
	@RequestMapping(value = { "/servicePost/checkSubscription" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Boolean checkSubscription(@RequestBody String phone) {

		return false;
	}*/

	/*@ResponseBody
	@RequestMapping(value = { "/servicePost/checkCreditStatus" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Object> checkCreditStatus(@RequestBody String deviceid) {

		return null;
	}*/

	/*@ResponseBody
	@RequestMapping(value = "/servicePost/dispachRevenue", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Object> getDisptachRevenue(Authentication auth, @RequestBody String jsonString) {

		return null;
	}*/

	/*@ResponseBody
	@RequestMapping(value = "/servicePost/dispachOrder", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Order> getClientLocation(Authentication auth, @RequestBody UserPass jsonString) {
		Transaction d;
		Status s;
		try {} catch (Exception e) {
			log.info("Orders error = " + e.toString());
		}
		return null;
	}*/

	/*@ResponseBody
	@RequestMapping(value = "/servicePost/dispatchStatusUpdate", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public String updateDispatchStatus(Authentication auth, @RequestBody String jsonString) {

		return "fail";
	}*/

	/*@ResponseBody
	@RequestMapping(value = "/servicePost/updateDispatchLocation", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public String updateDispatchLocation(Authentication auth, @RequestBody String jsonString,
			@RequestBody String jsonString2) {

		return "fail";
	}*/

	/*@ResponseBody
	@RequestMapping(value = "/servicePost/makePaymet", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public String paymentStatusUpdate(@RequestBody String phone) {

		return "failed";
	}*/

	/*@ResponseBody
	@RequestMapping(value = "/servicePost/login", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String Login(Authentication auth, @RequestBody UserPass jsonString) {
		log.info(jsonString.getUsername() + " Dated = " + new Date());
		try {} catch (Exception e) {
			log.info("Login Error --->" + e.toString() + " Dated = " + new Date());
		}
		return "false";
	}*/

	/*public static Long dateDiff(String d) {

		Long timestamp = Long.parseLong(d);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestamp);
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
		String date = formatter.format(calendar.getTime());

		String dateStart = date;
		String dateStop = new Date().toString();
		Long diffVal = null;

		// HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		SimpleDateFormat sdf3 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

		Date d1 = null;
		Date d2 = null;

		try {} catch (Exception e) {
			e.printStackTrace();
		}

		return diffVal;

	}*/

	/**
	 * Captures email details of clients who wish to subscribe to the kenten
	 * news
	 * 
	 * @param auth
	 * @param email
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping(value = "/servicePost/subscribenews", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public String updateNewsLetter(Authentication auth, @RequestParam("email") String email) {

		return "Success";
	}*/

	/*@ResponseBody
	@RequestMapping(value = "/servicePost/errorfromapp", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public void errorLogger(Authentication auth, @RequestBody String error) {
		log.error("Error Message from app = " + error);
	}*/

	/**
	 * Fetches the item images from the database for the client app
	 * 
	 * @param response
	 * @param itemId
	 * @throws IOException
	 */
	/*@RequestMapping(value = "/servicePost/imageDisplay/{id}", method = RequestMethod.GET)
	public void getDocumentFileContent(final HttpServletResponse response, @PathVariable("id") final Integer itemId)
			throws IOException {
		log.info("FETCHING ITEM IMAGES");
		Item item = itemRepository.findOne(itemId);
		try {

			writeFileContentToHttpResponse(item.getItemImage(), response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/

	
	/**
	 * Gets the list of Items from the database to populate the client app at
	 * runtime.
	 * 
	 * @author Peter Jude Ackon
	 * @param auth
	 * @param jsonString
	 * @return
	 */
	/*@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = { "/servicePost/getItemsFromServer" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public JSONArray getItemsFromServer(Authentication auth, @RequestBody String jsonString) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		JSONArray returnVal = new JSONArray();
		List<Item> res = null;
		try {} catch (Exception e) {
			e.toString();
		}
		return null;
	}*/

	String reference = "";

	/**
	 * Called when user initiates subscription process. Gets the corresponding
	 * subscription type and sets the amount. Then calls the paystack api for
	 * further processing
	 * 
	 * @author Peter Jude Ackon
	 * @param auth
	 * @param momodetails
	 * @return
	 */
/*
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/servicePost/mmsubscription", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public String subscriptionPayment(Authentication auth, @RequestBody String momodetails) {
		momodetails = momodetails.replace("{", "").replace("}", "").replace("\"", "");

		String details[] = momodetails.split(",");
		String subscriptionType = details[2].split(":")[1];
		Boolean notsubscribed = false;

		OperatingCountries c = clientRepository.findClientByCode(details[0].split(":")[1]);
		ClientSubscription cs1 = clientSubscriptionRepository.findClientSubscriptionByClientAndDate(c);
		if (cs1 != null) {
			String clientSubscriptionType = cs1.getSubscriptionType();
			LocalDate subscibDate = cs1.getSubscriptionDate();
			LocalDate currentdate = LocalDate.now();
			if (clientSubscriptionType.contains("monthly")) {
				if (subscibDate.getMonthValue() != currentdate.getMonthValue()) {
					notsubscribed = true;
				}
			} else if (clientSubscriptionType.contains("quaterly")) {
				if (subscibDate.get(IsoFields.QUARTER_OF_YEAR) != currentdate.get(IsoFields.QUARTER_OF_YEAR)) {
					notsubscribed = true;
				}
			} else if (clientSubscriptionType.contains("biannual")) {
				if ((subscibDate.getMonthValue() <= 6 && currentdate.getMonthValue() > 6)
						|| (subscibDate.getMonthValue() > 6 && currentdate.getMonthValue() <= 6)) {
					notsubscribed = true;
				}
			} else if (clientSubscriptionType.contains("annual")) {
				if (subscibDate.getYear() != currentdate.getYear()) {
					notsubscribed = true;
				}
			}
		}

		if (cs1 == null || notsubscribed == true) {} else {
			return "";
		}
		return reference;
	}
*/
	/**
	 * Generates and sends a Paystack OPT message to the phone of the client.
	 * 
	 * @author Ackon Peter Jude
	 * @param req
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping(value = {
			"/servicePost/subscriptionMobileMoneyOPTSubmit" }, method = RequestMethod.POST, consumes = {
					MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String subscriptionMobileMoneyOPTSubmit(@RequestBody String req) {
		String res = req.split("opt")[1].replace(":", "").replace("}", "").replace("\"", "");
		String ref = req.split("opt")[0].split("ref")[1].replace(":", "").replace("}", "").replace("\"", "")
				.replace(",", "");
		String device = req.split("phone")[1].split("ref")[0].replace(":", "").replace("}", "").replace("\"", "")
				.replace(",", "");
		String subscription = req.split("type")[1].replace(":", "").replace("}", "").replace("\"", "");

		try {} catch (Exception e) {
			e.printStackTrace();
		}

		log.error(res);
		return "";
	}*/

	/**
	 * 
	 * @author Peter Ackon Jude
	 * @param req
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping(value = { "/servicePost/subscriptionpaymentStatus" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String subscriptionPaymentStatus(@RequestBody String req) {
		String res = req.split("status")[1].split(",")[0];

		
		 * if (res.toUpperCase().contains("SUCCESS")) { ClientSubscription cs1 =
		 * clientSubscriptionRepository.findClientSubscriptionByClientAndDate(c)
		 * ; if (cs1 == null) { } }
		 
		log.error(res);
		return "";
	}*/

	/**
	 * This is to check if the client is a first time user or not
	 * 
	 * @author Peter Jude Ackon
	 * @param device
	 * @return
	 */
	public boolean checkClientSubscription(OperatingCountries client) {

		return false;
	}

/*	@RequestMapping("/servicePost/sendFirebaseMessage")
	@ResponseBody
	public String sendNotification(@RequestBody Note note) throws FirebaseMessagingException {
		return firebaseService.sendNoteNotification(note);
		// return firebaseService.sendNotification(note.getSubject(),
		// note.getContent(), note.getToken());
	}*/

}
