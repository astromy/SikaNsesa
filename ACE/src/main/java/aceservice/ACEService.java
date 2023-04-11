package aceservice;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.TransferOrderDTO;
import models.Bank;
import models.BankTransaction;
import models.CommissionRates;
import models.OperatingCountries;
import models.RecipeintTransaction;
import models.Transaction;
import repository.BankRepository;
import repository.BankTransactionRepository;
import repository.CommisionRateRepository;
import repository.CurrencyRateRepository;
import repository.OperatingCountryRepository;
import repository.RecipientTransactionRepository;
import repository.SenderTransactionRepository;
import repository.StatusRepository;
import repository.TransactionCommisionRepository;
import repository.TransactionRepository;

@Service
@Transactional
public class ACEService {
	private final Logger log = Logger.getLogger(this.getClass());

	@Autowired
	RecipientTransactionRepository recipientTransactionRepository;
	@Autowired
	TransactionCommisionRepository transactionCommisionRepository;
	@Autowired
	StatusRepository statusRepository;
	@Autowired
	CurrencyRateRepository currencyRateRepository;
	@Autowired
	CommisionRateRepository commissionRateRepository;
	@Autowired
	SenderTransactionRepository senderTransactionRepository;
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	BankTransactionRepository bankTransactionRepository;
	@Autowired
	OperatingCountryRepository operatingCountryRepository;
	@Autowired
	BankRepository bankRepository;
	@Autowired
	MomoMethodsService momoService;

	OperatingCountries peratingCountries = null;

	Double monthlySubscription = 25.0;
	Double quaterySubscription = 65.0;
	Double biannualSubscription = 100.0;
	Double annualSubscription = 190.0;

	// SendSMSJSON sendSMS = new SendSMSJSON();

	// ************************* REST SECTION *********************

	public ResponseEntity<String> RTO(@RequestBody TransferOrderDTO jsonString) {

		try {
			List<CommissionRates> cr = commissionRateRepository.findCurrentRate();
			Double CommissionRate = (cr.get(cr.size() - 1).getRates() / 100);
			Double sendCommissionRate = (cr.get(cr.size() - 1).getSendingBank() / 100);
			Double recieveCommissionRate = (cr.get(cr.size() - 1).getRecievingBank() / 100);
			Double tAmount = jsonString.getSenderAmount() * (1 - CommissionRate);

			Double AceSendingBankCommission = jsonString.getSenderAmount()
					- (jsonString.getSenderAmount() * (1 - sendCommissionRate));
			Double AceRecievingCommission = jsonString.getSenderAmount()
					- (jsonString.getSenderAmount() * (1 - recieveCommissionRate));
			Double AceCommission = (jsonString.getSenderAmount() - tAmount) - AceSendingBankCommission
					- AceRecievingCommission;

			Double ExchangeRate = currencyRateRepository
					.findConversionRate(jsonString.getSenderCurrency(), jsonString.getRecipientCurrency()).getRate();
			int ind = String.valueOf(ExchangeRate * tAmount).indexOf(".");
			Double transAmount = Double
					.parseDouble((String.valueOf(ExchangeRate * tAmount) + "0.000000001").substring(0, ind + 3));

			log.info("Amount Sent =" + jsonString.getSenderAmount() + "\nAce Commission =" + AceCommission
					+ "\nSending Bank Commission =" + AceSendingBankCommission + "\nRecieveing Bank Commission ="
					+ AceRecievingCommission + "\nTotal Commission ="
					+ (AceCommission + AceSendingBankCommission + AceRecievingCommission));

			Transaction transaction = transactionRepository.findTransactionByReciept(jsonString.getSender(),
					jsonString.getSenderCurrency(), jsonString.getSenderAmount());
			if (transaction != null) {
				RecipeintTransaction rt = new RecipeintTransaction();

				transaction.setRecipient(jsonString.getRecipient());
				transaction.setRecipientBank(jsonString.getBank());
				transaction.setRecipientAmount(transAmount);
				transaction.setDestinationCountry(
						operatingCountryRepository.findByName(jsonString.getDestinationCountry()));
				transaction.setRecipientCurrency(jsonString.getRecipientCurrency());
				transaction.setStatus(statusRepository.findStatusByName(""));

				rt.setAmount(transAmount);
				rt.setRecipientCountry(operatingCountryRepository.findByName(jsonString.getDestinationCountry()));
				rt.setTransaction(transaction);
				;
				rt.setSender(jsonString.getSender());
				rt.setStatus(statusRepository.findStatusByName(""));
				rt.setDateTime(LocalDateTime.now());
				List<Bank> b = bankRepository.findBankByBalanceAndCountry(transAmount,
						operatingCountryRepository.findByName(jsonString.getDestinationCountry()));
				if (b.size() > 0) {
					rt.setBank(b.get(0));
				} else {
					rt.setBank(
							bankRepository
									.findBankByCountry(
											operatingCountryRepository.findByName(jsonString.getDestinationCountry()))
									.get(0));
				}

				Bank bnk = (bankRepository.findOne(rt.getBank().getIdbanks()));
				bnk.setBankBalance(bnk.getBankBalance() + AceRecievingCommission);

				BankTransaction bt = new BankTransaction();
				bt.setBank(rt.getBank());
				bt.setBanktransactionAmount(AceRecievingCommission);
				bt.setBanktransactionCommentary("Reciepient Commission for Transfer");
				bt.setBanktransactionCountry(rt.getRecipientCountry());
				bt.setBanktransactionCurrency(jsonString.getRecipientCurrency());
				bt.setBanktransactionDate(LocalDateTime.now());
				bt.setBanktransactionReciept(jsonString.getSender());
				bt.setBanktransactionType("Recieving");
				bt.setStatus(statusRepository.findStatusByName("Successful"));
				bt.setBanktransactionBalance(bnk.getBankBalance());

				transactionRepository.save(transaction);
				recipientTransactionRepository.save(rt);
				bankTransactionRepository.save(bt);
				bankRepository.save(bnk);
			}

		} catch (Exception e) {
			log.info("Transaction ERROR --> " + e.toString());
		}
		return new ResponseEntity<String>("NOT SENT, Error In Transaction", HttpStatus.OK);
	}

	public Double getCommissionRate() {
		List<CommissionRates> cr = commissionRateRepository.findCurrentRate();
		return cr.get(cr.size() - 1).getRates();

	}

	public List<Object> getAllCountries() {
		// momoService.CreateAPIUser();
		List<Object> operatingCountryList = new ArrayList<Object>();
		try {
			operatingCountryList = operatingCountryRepository.findCountryList();
		} catch (Exception e) {
		}
		return operatingCountryList;

	}

	public ArrayList<byte[]> getConversionRate(@RequestBody String currencies) {
		ArrayList<byte[]> results = new ArrayList<>();
		Double conversionRate = 0.00;
		String fromCurrency = currencies.split(",")[0].split(":")[1].replaceAll("\\{", "").replaceAll("\\}", "")
				.replaceAll("\\n", "").replaceAll("\"", "").trim();
		;
		String toCurrency = currencies.split(",")[1].split(":")[1].replaceAll("\\{", "").replaceAll("\\}", "")
				.replaceAll("\\n", "").replaceAll("\"", "").trim();
		;
		// currencies=currencies.replaceAll("\\{", "").replaceAll("\\}",
		// "").replaceAll("\\n", "").replaceAll("\"", "").trim();
		// try {
		conversionRate = currencyRateRepository.findConversionRate(fromCurrency, toCurrency).getRate();
		int x = conversionRate.toString().indexOf(".");
		conversionRate = Double.parseDouble(conversionRate.toString() + "00001");
		conversionRate = (Double.parseDouble(conversionRate.toString().substring(0, x + 3)));
		/*
		 * } catch (Exception e) { return null; }
		 */
		String m = "1";
		int t = conversionRate.toString().split("\\.")[1].length();
		for (int c = 0; c < t; c++) {
			m += "0";
		}
		int valToMul = Integer.parseInt(m);
		Integer valToConv = ((int) (conversionRate * valToMul));
		byte[] bytearray = valToConv.toString().getBytes();
		Integer val = Integer.parseInt(convertNumberToNewBase(bytearray, 10, 3));
		results.add(val.toString().getBytes());
		results.add(m.getBytes());
		log.info(results);
		return results;

	}

	public Boolean checkAmnt(String shopCords) {
		Boolean response = false;
		new ArrayList<Integer>();
		try {

		} catch (Exception e) {
			log.error("Item Price error-->" + e.toString());
		}
		response = true;
		return response;

	}

	public String checkContact(@RequestBody String deviceId) {
		try {

		} catch (Exception e) {

		}
		return null;
	}

	public Boolean UpdateToken(@RequestBody String deviceToken) {
		Boolean subscribed = false;
		log.info("NE USERS Device Token-->" + deviceToken);
		try {
		} catch (Exception e) {
			log.error("Update Token error-->" + e.toString());
		}
		return subscribed;
	}

	public String createClient(@RequestBody String deviceId) {
		try {
		} catch (Exception e) {
			log.error("Create Client Error-->" + e.toString());
		}
		return null;
	}

	public Object getKentenUserLocation(@RequestBody String phone) {

		return null;
	}

	@ResponseBody
	@RequestMapping(value = { "/servicePost/makePayment" }, method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Object makePayment(@RequestBody String phone) {

		return null;
	}

	public Object makeUSSDPayment(@RequestBody String orderNumber) {

		return "Kenten USSD payment error";
	}

	public Object creditUSSDPayment(@RequestBody String orderNumber) {
		orderNumber = orderNumber.split(":")[1].replace("}", "").replace("{", "").replaceAll("\"", "")
				.replaceAll("\n", "").trim();

		return "Kenten Credit USSD payment error";
	}

	public Object servicePromo() {

		return null;
	}

	public Object updateContact(@RequestBody String phone) {

		return "Failed";
	}

	public Object updateName(@RequestBody String userName) {

		return "Failed";
	}

	public Object updateEmail(@RequestBody String userEmail) {

		return "Failed";
	}

	public String changePhone(@RequestBody String phone) {

		return "Failed";
	}

	public Double checkWallet(@RequestBody String phone) {

		return 0.0;
	}

	public Boolean checkSubscription(@RequestBody String phone) {

		return false;
	}

	public List<Object> checkCreditStatus(@RequestBody String deviceid) {

		return null;
	}

	public List<Object> getDisptachRevenue(Authentication auth, @RequestBody String jsonString) {

		return null;
	}

	public String updateDispatchStatus(Authentication auth, @RequestBody String jsonString) {

		return "fail";
	}

	public String updateDispatchLocation(Authentication auth, @RequestBody String jsonString,
			@RequestBody String jsonString2) {

		return "fail";
	}

	public String paymentStatusUpdate(@RequestBody String phone) {

		return "failed";
	}

	@SuppressWarnings("unused")
	public static Long dateDiff(String d) {

		Long timestamp = Long.parseLong(d);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestamp);
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
		String date = formatter.format(calendar.getTime());

		new Date().toString();
		Long diffVal = null;

		new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

		try {
		} catch (Exception e) {
			e.printStackTrace();
		}

		return diffVal;

	}

	/**
	 * Captures email details of clients who wish to subscribe to the kenten
	 * news
	 * 
	 * @param auth
	 * @param email
	 * @return
	 */

	public String updateNewsLetter(Authentication auth, @RequestParam("email") String email) {

		return "Success";
	}

	public void errorLogger(Authentication auth, @RequestBody String error) {
		log.error("Error Message from app = " + error);
	}

	/**
	 * Fetches the item images from the database for the client app
	 * 
	 * @param response
	 * @param itemId
	 * @throws IOException
	 */
	/*
	 * @RequestMapping(value = "/servicePost/imageDisplay/{id}", method =
	 * RequestMethod.GET) public void getDocumentFileContent(final
	 * HttpServletResponse response, @PathVariable("id") final Integer itemId)
	 * throws IOException { log.info("FETCHING ITEM IMAGES"); Item item =
	 * itemRepository.findOne(itemId); try {
	 * 
	 * writeFileContentToHttpResponse(item.getItemImage(), response); } catch
	 * (SQLException e) { e.printStackTrace(); } }
	 */

	public static void writeFileContentToHttpResponse(final Blob image, final HttpServletResponse response)
			throws IOException, SQLException {

		ServletOutputStream out = response.getOutputStream();
		InputStream in = image.getBinaryStream();
		int lenght = (int) image.length();
		int bufferSize = 1024;
		byte[] buffer = new byte[bufferSize];

		response.setContentType("image/jpeg");
		while ((lenght = in.read(buffer)) != -1) {
			out.write(buffer, 0, lenght);
		}
		in.close();
		out.flush();
	}

	/**
	 * Gets the list of Items from the database to populate the client app at
	 * runtime.
	 * 
	 * @author Peter Jude Ackon
	 * @param auth
	 * @param jsonString
	 * @return
	 */

	public JSONArray getItemsFromServer(Authentication auth, @RequestBody String jsonString) {
		new ArrayList<Integer>();
		new JSONArray();
		try {
		} catch (Exception e) {
			e.toString();
		}
		return null;
	}

	/**
	 * A lottory api for rewarding customers at random
	 * 
	 * @author Peter Jude Ackon
	 * @param auth
	 * @param draw
	 * @return
	 */

	public String winners(Authentication auth, @RequestParam("draw") String draw) {
		String winners = "";
		ArrayList<String> winners1 = new ArrayList<>();
		Random randomList = new Random();

		int list;
		for (int i = 0; i <= 2; i++) {
			if (winners1.size() > 0) {
				list = randomList.nextInt(winners1.size());
				Collections.shuffle(winners1);
				String x = ((winners1.get(list).replace("}", "").replace("\n", "").replaceAll("\\\\", "").replace("\"",
						"")));
				winners = winners + x;
				winners = winners + ", ";
				winners1.remove(list);
			}
		}
		return winners;
	}

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
	 * @SuppressWarnings("unchecked")
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/servicePost/mmsubscription", method =
	 * RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	 * public String subscriptionPayment(Authentication auth, @RequestBody
	 * String momodetails) { momodetails = momodetails.replace("{",
	 * "").replace("}", "").replace("\"", "");
	 * 
	 * String details[] = momodetails.split(","); String subscriptionType =
	 * details[2].split(":")[1]; Boolean notsubscribed = false;
	 * 
	 * OperatingCountries c =
	 * clientRepository.findClientByCode(details[0].split(":")[1]);
	 * ClientSubscription cs1 =
	 * clientSubscriptionRepository.findClientSubscriptionByClientAndDate(c); if
	 * (cs1 != null) { String clientSubscriptionType =
	 * cs1.getSubscriptionType(); LocalDate subscibDate =
	 * cs1.getSubscriptionDate(); LocalDate currentdate = LocalDate.now(); if
	 * (clientSubscriptionType.contains("monthly")) { if
	 * (subscibDate.getMonthValue() != currentdate.getMonthValue()) {
	 * notsubscribed = true; } } else if
	 * (clientSubscriptionType.contains("quaterly")) { if
	 * (subscibDate.get(IsoFields.QUARTER_OF_YEAR) !=
	 * currentdate.get(IsoFields.QUARTER_OF_YEAR)) { notsubscribed = true; } }
	 * else if (clientSubscriptionType.contains("biannual")) { if
	 * ((subscibDate.getMonthValue() <= 6 && currentdate.getMonthValue() > 6) ||
	 * (subscibDate.getMonthValue() > 6 && currentdate.getMonthValue() <= 6)) {
	 * notsubscribed = true; } } else if
	 * (clientSubscriptionType.contains("annual")) { if (subscibDate.getYear()
	 * != currentdate.getYear()) { notsubscribed = true; } } }
	 * 
	 * if (cs1 == null || notsubscribed == true) {} else { return ""; } return
	 * reference; }
	 */
	/**
	 * Generates and sends a Paystack OPT message to the phone of the client.
	 * 
	 * @author Ackon Peter Jude
	 * @param req
	 * @return
	 */

	public String subscriptionMobileMoneyOPTSubmit(@RequestBody String req) {
		String res = req.split("opt")[1].replace(":", "").replace("}", "").replace("\"", "");
		req.split("opt")[0].split("ref")[1].replace(":", "").replace("}", "").replace("\"", "").replace(",", "");
		req.split("phone")[1].split("ref")[0].replace(":", "").replace("}", "").replace("\"", "").replace(",", "");
		req.split("type")[1].replace(":", "").replace("}", "").replace("\"", "");

		try {
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.error(res);
		return "";
	}

	/**
	 * 
	 * @author Peter Ackon Jude
	 * @param req
	 * @return
	 */

	public String subscriptionPaymentStatus(@RequestBody String req) {
		String res = req.split("status")[1].split(",")[0];

		/*
		 * if (res.toUpperCase().contains("SUCCESS")) { ClientSubscription cs1 =
		 * clientSubscriptionRepository.findClientSubscriptionByClientAndDate(c)
		 * ; if (cs1 == null) { } }
		 */
		log.error(res);
		return "";
	}

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

	/*
	 * @RequestMapping("/servicePost/sendFirebaseMessage")
	 * 
	 * @ResponseBody public String sendNotification(@RequestBody Note note)
	 * throws FirebaseMessagingException { return
	 * firebaseService.sendNoteNotification(note); // return
	 * firebaseService.sendNotification(note.getSubject(), // note.getContent(),
	 * note.getToken()); }
	 */

	private static String convertNumberToNewBase(byte[] number, int base, int newBase) {
		String ConvertedVal = "";

		String toConv = new String(number, StandardCharsets.UTF_8);
		Integer x = (Integer.parseInt(toConv, base));
		Integer.toString(x, newBase);
		ConvertedVal = (Integer.toString(Integer.parseInt(toConv, base), newBase));

		return ConvertedVal;
	}

	@SuppressWarnings("unused")
	private static String convertNumberToNewBase(String number, int base, int newBase) {
		String ConvertedVal = "";
		ConvertedVal = (Integer.toString(Integer.parseInt(number, base), newBase));
		return ConvertedVal;
	}

	@SuppressWarnings("unused")
	private static String convertNumberBack(String number, int base, int newBase) {

		String ConvertedBase = Integer.toString(Integer.parseInt(number, base), newBase);

		byte[] ConvertedVal = ConvertedBase.getBytes();

		String ReturnVal = new String(ConvertedVal, StandardCharsets.UTF_8);
		return ReturnVal;
	}

	@SuppressWarnings("unused")
	private short toNumber(String val) {
		ByteBuffer wrapped = ByteBuffer.wrap(val.getBytes()); // big-endian by
																// default
		short num = wrapped.getShort();
		return num;
	}

}
