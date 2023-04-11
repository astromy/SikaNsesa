/*package acecontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import acemodels.OrderItem;
import acerepository.ClientRepository;
import acerepository.DispatchBikeRepository;
import acerepository.DispatchRepository;
import acerepository.GenderRepository;
import acerepository.ItemPriceRepository;
import acerepository.ItemRepository;
import acerepository.KentenPromoRepository;
import acerepository.NewsLetterRepository;
import acerepository.OrderDispatchRepository;
import acerepository.OrderItemRepository;
import acerepository.OrderRepository;
import acerepository.SalesRepository;
import acerepository.ShopRepository;
import acerepository.ShopUserRepository;
import acerepository.StatusRepository;


@RestController
@Controller
public class UserAccountController {
	private final Logger log = Logger.getLogger(this.getClass());

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	DispatchRepository dispatchRepository;
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	ShopUserRepository shopUserRepository;
	@Autowired
	ShopRepository shopRepository;
	@Autowired
	SalesRepository salesRepository;
	@Autowired
	StatusRepository statusRepository;
	@Autowired
	OrderItemRepository orderItemRepository;
	@Autowired
	OrderDispatchRepository orderDispatchRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	GenderRepository genderRepository;
	@Autowired
	ItemPriceRepository itemPriceRepository;
	@Autowired
	DispatchBikeRepository bikeRepository;
	@Autowired
	NewsLetterRepository newsLetterRepository;
	@Autowired
	KentenPromoRepository kentenPromoRepository;
	

	@ResponseBody
	@RequestMapping(value={"/servicePost/USSDTransaction"}, method = RequestMethod.POST)
	public String USSDTransactions(@RequestParam("userResponse") String userResponse, HttpServletRequest req,
			HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String stage= "";
		stage= (String) session.getAttribute("currentStage");
		if (stage==null){stage="";}
		String useroption="";
		useroption= (String) session.getAttribute("userOption");
		if (useroption==null){useroption="";}
		
		switch (stage) {
		case "1":
			switch (useroption) {
			case "2":
				String bal="0.00";
					session.invalidate();
					Double amountDue = orderItemRepository.findTotalOrderAmount(Long.parseLong(userResponse));
					if(amountDue >0){
				bal=amountDue.toString();
				}
				 return "Balance is GHS "+ bal;
			case "1":
				String bal2="0.00";
				session.invalidate();
			 Double amountDue1B = orderItemRepository.findTotalOrderAmount(Long.parseLong(userResponse));
			
			bal2=amountDue1B.toString();
			
			if (bal2=="0.00"){
				return "Amount Due for Order :" + userResponse + " is GHS" + bal2 + "\nProceed to make payment";
			}else{
				return "Amount Due for Order :" + userResponse + " is GHS" + bal2 + "\nProceed to make payment. Enter Amount to pay";
			}
			default:
				break;
			}

		default:
			switch (userResponse) {
			case "1":
				session.setAttribute("currentStage", "1");
				session.setAttribute("userOption", "1");
				return "1. Make Payment For Order \nPlease Enter Order Code";
			case "2":
				session.setAttribute("currentStage", "1");
				session.setAttribute("userOption", "2");
				return "2. Check Loan Status \nPlease Enter Order Code";

			default:
				return "1. Make Payment For Order \n2. Check Loan Status ";
			}
		}

	}
	
	public OrderItem fetchOrderItem(String orderId){
		OrderItem oi=(OrderItem) orderItemRepository.findOrderItemByOrder(orderRepository.findOne(Long.parseLong(orderId)));
		return oi;
	}
	
	public String initiatePropertyPayment(String propertyID){
		
		
		return null;
	}

}
*/