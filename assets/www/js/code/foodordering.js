var masterData=$.parseXML(localStorage.getItem("xml"));

var wsUrl='';
var soapRequest='';
var html='';
var cartItemsArray=new Array();
var appid='';
var first=1;
var accountDispl='block';
var loginDispl='block';
//*************var for dynamic**************

var home_food = "Home";
var my_shop_food = "My Shop Food";
var cart_food="Cart";
var my_account_food="My Account";
var My_Orders_food = "My Orders";
var pickup_food = "PickUp Address";
var categories_food = "Categories Food";
var submit_food = "Submit";
var forgot_password_food = "Forgot Password";
var email_id_food = "Email Id";
var password_food = "Password";
var do_not_have_an_account_food = "Do not have an account?";
var search_food = "Search";
var sign_up_now_food = "Sign Up Now";
var name_food = "Name";
var phone_food = "Phone";
var confirm_password_food = "Confirm Password";
var sign_up_food = "Sign Up";
var already_have_an_account_food = "Already have an account?";
var sign_in_food = "Sign In";
var contact_information_food = "Contact Information";
var my_address_food = "My Address";
var default_billing_address_food = "Default Billing Address";
var default_shipping_address_food = "Default Shipping Address";
var order_history_food = "Order History";
var order_id_food = "Order Id";
var billing_address_food = "Billing Address";
var shipping_address_food = "Shipping Address";
var order_detail_food = "Order Detail";
var product_name_food = "Product Name";
var price_food = "Price";
var qty_food = "Qty";
var order_date_food = "Order Date";
var subtotal_food = "Subtotal";
var tax_food = "Tax";
var coupon_food = "Coupon";
var discount_food = "Discount";
var shipping_food = "Shipping";
var grand_total_food= "Grand Total";
var payment_method_food = "Payment Method";
var order_status_food = "Order Status";
var personal_information_food = "Personal Information";
var change_password_food = "Change Password";
var current_password_food = "Current Password";
var confirm_new_password_food = "Confirm New Password";
var no_product_exists_food = "No Product Exists";
var add_to_cart_food = "Add To Cart";
var cart_list_food = "Cart List";
var edit_food = "Edit";
var done_food = "Done";
var payment_details_food = "Payment Details";
var apply_coupon_code_food = "Apply Coupon Code";
var enter_your_coupon_code_if_you_have_one_food = "Enter coupon code";
var apply_food = "Apply";
var continue_shopping_food = "Continue Shopping";
var checkout_food = "Checkout";
var first_name_food = "First Name";
var last_name_food = "Last Name";
var address_food = "Address";
var zip_postal_code_food = "Zip/Postal Code";
var city_food = "City";
var country_food = "Country";
var state_province_food = "State/Province";
var telephone_food = "Telephone";
var fax_food = "Fax";
var same_as_billing_address_food = "Same as Billing Address";
var pay_now_food = "Pay Now";
var cash_on_delivery_food = "Cash On Delivery";
var please_click_on_place_order_button_to_place_the_order_food = "Please Click On Place Order Button To Place The Order";
var credit_card_food = "Credit Card";
var paypal_food = "Paypal";
var you_will_be_redirected_to_paypal_site_food = "You will be redirected to paypal site";
var order_by_phone_food = "Order by Phone";
var you_can_order_by_calling_food = "You can order by calling";
var place_order_food = "Place Order";
var instock_food= "Instock";
var out_of_stock_food = "Out Of Stock";
var test_food = "Test food";

var required_fields_food="Required Fields";
var no_items_in_cart_food="You have no items in your shopping cart.";
var add_products_in_cart_food="Please add products in your shopping cart.";
var	login_food="Login";
var new_password_food="New Password";
var logout_food="Logout";
var no_products_exists_food="No Products Exists";
var already_have_an_account_food="Already have an account?";
var instructions_foodorder="Instructions";


var privacypolicy_food = "Privacy Policy";
var homee_food = "Home";
var loginsignup_food = "Login/Sign Up";
var cartt_food = "Cart"
var myorder_food = "My Order";
var termscconditions_food = "Terms & Conditions";
var lllogout_food = "Logout";
var searchproducts_food = "Search Products";
var featureddproducts_food = "Featured Products";
var searchproductin_food = "Search Productin";
var ssearch_food = "Search";
var aalert_food = 'Alert!';
var pleaseenterkeywordsearch_food = 'Please enter a keyword to search';
var adddd_food = "add";
var noproductfoundd_food = "No Product Found";
var searchresultt_food = "Search Result for";
var thereproductadded_food = "There is no product added.";
var productsuccessfullyaddedcart_food = "Product successfully Added into your Cart.";
var productquantityzero_food = "Product Quantity should be greater than Zero.";
var sizeformeal_food = "Size for meal";
var youhaveaddedmaximumquantity_food = "You have added maximum quantity.";
var select_food = "Select";
var  subbb_food = '- ';
var addzero_food = '+ 0';
var addddd_food = '+ ';
var pleaseenterquantityessthann_food = "Please enter quantity less than ";
var pleaseselectt_food = 'Please select ';
var errorrr_food = "Error";
var okkk_food = "OK";
var miordervalueis_food = 'Min. order value is ';
var ssorry_food = "Sorry";
var productsuccessfullydadednourart_food = "Product successfully Added In Your Cart.";
var quantityshouldgreaterzero_food = "Quantity should be greater than zero";
var deliverycharge_food = "Delivery Charge "; 
var discountcodes_food = "Discount Codes";
var shoppingcart_food = "Shopping cart";
var clearcart_food = "Clear Cart";
var tip_food = "Tip";
var continueoordering_food ="Continue Ordering"
var qquantity_food = "Quantity";
var pprice_food = "Price";
var ttotal_food = "Total";
var ttax_food = "Tax";
var ddiscount_food = "Discount";
var ddeliverycharge_food = "Delivery Charge";
var ggrandtotal_food = "Grand Total";
var therenoitemcart_food = "There is no Item in the cart";
var doneee_food = "Done";
var couponpppage_food = "Coupon Page";
var notavalidcccoupon_food = "Not a valid coupon "; 
var eeenteryourcouponcode_food= "Enter your Coupon Code";
var ppaymentddetails_food = "Payment Details";
var ssubtotal_food= "Subtotal ";
var ccoupon_food= "Coupon ";
var llloginregistration_food = "Login / Registration";
var mmyaccount_food = "My Account";
var mysshop_food= "My Shop";
var invalidloginidorpassword_food= "Invalid login id or password.";
var fforgotyourpassword_food= "Forgot your password?";
var orr_food = "OR";
var ccconfirmpassword_food = "Confirm  Password";
var wehavesentemailemailid_food = "We have sent an email to your email id";
var rrresetpassword_food = "Reset Password";
var aaaalreadyhaveanaccount_food= "Already have an account? ";
var ppleaseenterfirstnname_food ='Please Enter First Name.';
var ppleaseeentervvalidemailiid_food = 'Please Enter Valid Email Id.';
var ppleaseenterpphonennumber_food = 'Please Enter Phone Number.';
var pppleaseetervlidphonenumber_food = 'Please Enter Valid Phone Number.';
var ppleaseenterpassword_food = 'Please Enter Password';
var cconfirmpassworddonotmatch_food = 'Confirm Password do not match';
var sssignuuccessful_food ='Signup Successful';
var eeemaillreadyegistered_food= 'Email Id Already Registered';
var uuhoh_food = 'Uh-oh, inValid email ID.';
var ppasswordesewasuccessfullleasecheckemailfornewpassword_food = 'Password Reset was successfull,Please check your email for new password.';
var yyouhavenotbeenregisteredwithusyet_food = 'You have not been registered with us yet.';
var iiinvalidemailaddress_food = 'Invalid Email Address...';
var dddashboard_food = "Dashboard";
var mmydashboard_food = "My Dashboard";
var helloharmenrdrupta_food = "Hello, Dharmenrdra Gupta";
var ffromyoucounashboardbilityviewsnapshottion_food = "From your My Account Dashboard you have the ability to view a snapshot of your recent account activity and update your account information. Select a link below to view or edit information.";
var aaaaddressook_food = "Address Book ";
var iiinformationpdateuccessfully_food  = "Information Updated Successfully.";
var ssssave_food = "Save";
var nnnnname_food = "Name";
var eeemaiddress_food = "Email Address";
var enterourmailid_food = "Enter your Email ID";
var ttelephonenno_food = "Telephone No";
var billingformationpdatedccessfully_food = "Billing Information Updated Successfully.";
var nnname_food ="Name"
var sstreetssddress_food= "Street Address";
var enteourity_food = "Enter Your City";
var enterstatepprovince_food ="Enter State/Province";
var eenteipostalode_food ="Enter Zip/Postal Code";
var sshippinddresiffereillinddress_food =" Shipping Address Different From Billing Address";
var sshippingformationdateduccessfully_food= "Shipping Information Updated Successfully.";
var ffirstnme_food = "First Name";
var zzipostalode_food= "Zip/Postal Code";
var oooder_food = " Order ID";
var deliverycccharge_food ="delivery Charge";
var ccouponddiscount_food = "Coupon Discount";
var ttotalamount_food ="Total Amount";
var cccccomment_food = "Comment";
var iiitemisnotavailable_food = "Item is not available";
var dddelivery_food = "Delivery";
var dddeliveryromocation_food = "Delivery From Location";
var aaaaaaaaaddress_food = "Address";
var pppickupime_food = "Pickup Time";
var cooonfirm_food = "Confirm";
var pppaymentorderfor_food ="Payment of Product order for ";
var alerttText='Please select pickup time 45 minutes later from current time';
var ppleaseaterpentime_food = 'Please select pickup time 45 minutes later from open time ';
var pppickupime_food = 'Pick up Time';
var ppppleaseenterapreferredpickuptime_food ='Please enter a preferred pick up time';
var ppleaseenterareferredweenentime_food ='Please enter a preferred pick up time between store open time ';
var andstoreclosettime_food = ' and store close time ';
var ppleaseselectdeliverytimetime_food = 'We need at least 45 minutes to deliver.';
var ppppleasecheckdelivimlease_food='Please check delivery time. Please note delivery cannot be scheduled before 45 minutes from placing the order';
var emmmail_food = "Email";
var ssstateprovince_food = "State/Province";
var ccccountry_food = "Country";
var ssshhhippingddress_food = "Shipping Address";
var preferred_deliverytime_Text="Preferred Delivery Time:-";
var pppppayow_food = "Pay Now";
var ddddeliveryime_food = 'Delivery Time';
var pppleaseentepreferreddelivery_food='Please enter a preferred delivery';
var pppleasestoreopentimee_food = 'Please enter a preferred delivery time between ';
var dddddeliveryiotavailableinyourlocation_food = 'Delivery is not available in your location';
var ppppppleasenteountry_food = 'Please Enter Country';
var bblankfield_food = 'Blank Field';
var pppllleasenterterovince_food ='Please Enter State/Province';
var pplleasenterourity_food ='Please Enter Your City';
var pppleasntetreetress_food = 'Please Enter Street Address';
var ppppleasnteridhonember_food = 'Please Enter Valid Phone number';
var iiiiinvalihoneumber_food = 'Invalid Phone Number';
var ppleaseenterfirsname_food ='Please Enter First Name';
var ppleasnterountry_food = 'Please Enter Country';
var ppleasenterlidailddress_food ='Please Enter Valid Email Address';
var ppleasnterrstame_food = 'Please Enter First Name';
var pppaycashatdoor_food = "Pay cash at the door";
var ppaypalexpress_food = "Paypal Express";
var ppawithareditcard_food = "Pay with a credit card";
var oorderbyhone_food = "Order by Phone";
var ccallnow_food = "Call Now";
var pppleaseclickontheconfirmtopaythroughcreditcard_food ="Please Click on the confirm to pay through credit card";
var ppleaseclickonconfirmbuttonlaceherder_food = "Please Click On confirm Button To Place The Order";
var cccardype_food = "Card Type";
var viisa_food ="visa";
var mmastercard_food = "mastercard";
var amexxx_food = "amex";
var dddiscover_food = "discover";
var caaardnumber_food = "Card Number";
var exxxpirymonth_food = "Expiry Month";
var eeexpiryear_food = "Expiry Year";
var cccccardolder_food = "Card Holder";
var ccardssecuritode_food = "Card Security Code";
var caaaard_food = "Card";
var paaaypal_food = "Paypal";
var cood_food = "COD";
var checkthebackoyourcreditcardforcvv_ffod = "check the back of your credit card for cvv";
var cvvv_food = "CVV";
var cardholdernaaame_food = "Card Holder Name";
var yyoucanorderycalling_food = "You can order by calling ";
var ppleaseclickontheconfirmaypal_food = "Please click on the confirm to pay through Paypal";
var paaayusingashonelivery_food = "Pay using Cash-on-Delivery";
var pllaaceorder_food = "PLACE ORDER";
var ppleaseentervalidumber_food = "Please enter valid Number";
var ppleaseentervalidxpairyonth_food = "Please enter valid Expairy Month";
var ppleaseentervalidxpairyear_food ="Please enter valid Expairy year";
var ppleaseentervalidardolderame_food="Please enter valid Card Holder Name ";
var pppleaseentervalidode_food = "Please enter valid cvv Code ";
var iiinvalidcardetails_food = 'Invalid card details';
var innnvalidcard_food = "Invalid Card";
var ppppleaseenterthevalidaddrespickup_food = 'Please enter the valid address for pickup';
var ppleasentethtimeforpickup_food = 'Please enter the time for pickup';
var cccancel_food = "Cancel";
var rrretry_food = "Retry";
var cccontinue_food = "Continue";
var wwvereceivedourorder_food = "We\'ve received your order";
var yourorderwuccessfu_food = 'Your Order was successful!';
var wwearesorry_food = 'We are sorry!';
var toText_food = ' to ';



//var instructionsText=instructions_mcom;

//**********************************
sessionStorage.setItem("first","yes");



//
function getFoodorderingData(index)
{
    if(localStorage.getItem("appLanguageCheck") == "sa")
   {
      arabic();
   }
	
	$menuXmlData = $(masterData).find( ""+"foodordering"+"[indexval="+PageIndex[index]+"]" );
 
	 if($menuXmlData.find("LanguageSettings").find("home_food").text())
      {
      console.log('open page vij --->'+PageIndex[index]);
      home_food=$menuXmlData.find("LanguageSettings").find("home_food").text();
	   console.log('home_food --->'+home_food);
	  }
	  if($menuXmlData.find("LanguageSettings").find("my_shop_food").text())
      {
		my_shop_food=$menuXmlData.find("LanguageSettings").find("my_shop_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("login_signup_food").text())
      {
		login_signup_food=$menuXmlData.find("LanguageSettings").find("login_signup_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("cart_food").text())
      {
		cart_food=$menuXmlData.find("LanguageSettings").find("cart_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("my_account_food").text())
      {
		my_account_food=$menuXmlData.find("LanguageSettings").find("my_account_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("My_Orders_food").text())
      {
		My_Orders_food=$menuXmlData.find("LanguageSettings").find("My_Orders_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("featured_product_food").text())
      {
		featured_product_food=$menuXmlData.find("LanguageSettings").find("featured_product_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("categories_food").text())
      {
		categories_food=$menuXmlData.find("LanguageSettings").find("categories_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("submit_food").text())
      {
		submit_food=$menuXmlData.find("LanguageSettings").find("submit_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("forgot_password_food").text())
      {
		forgot_password_food=$menuXmlData.find("LanguageSettings").find("forgot_password_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("email_id_food").text())
      {
		email_id_food=$menuXmlData.find("LanguageSettings").find("email_id_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("password_food").text())
      {
		password_food=$menuXmlData.find("LanguageSettings").find("password_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("do_not_have_an_account_food").text())
      {
		do_not_have_an_account_food=$menuXmlData.find("LanguageSettings").find("do_not_have_an_account_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("search_food").text())
      {
		search_food=$menuXmlData.find("LanguageSettings").find("search_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("sign_up_now_food").text())
      {
		sign_up_now_food=$menuXmlData.find("LanguageSettings").find("sign_up_now_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("name_food").text())
      {
		name_food=$menuXmlData.find("LanguageSettings").find("name_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("phone_food").text())
      {
		phone_food=$menuXmlData.find("LanguageSettings").find("phone_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("confirm_password_food").text())
      {
		confirm_password_food=$menuXmlData.find("LanguageSettings").find("confirm_password_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("sign_up_food").text())
      {
		sign_up_food=$menuXmlData.find("LanguageSettings").find("sign_up_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("already_have_an_account_food").text())
      {
		already_have_an_account_food=$menuXmlData.find("LanguageSettings").find("already_have_an_account_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("sign_in_food").text())
      {
		sign_in_food=$menuXmlData.find("LanguageSettings").find("sign_in_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("contact_information_food").text())
      {
		contact_information_food=$menuXmlData.find("LanguageSettings").find("contact_information_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("my_address_food").text())
      {
		my_address_food=$menuXmlData.find("LanguageSettings").find("my_address_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("default_billing_address_food").text())
      {
		default_billing_address_food=$menuXmlData.find("LanguageSettings").find("default_billing_address_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("default_shipping_address_food").text())
      {
		default_shipping_address_food=$menuXmlData.find("LanguageSettings").find("default_shipping_address_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("order_history_food").text())
      {
		order_history_food=$menuXmlData.find("LanguageSettings").find("order_history_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("order_id_food").text())
      {
		order_id_food=$menuXmlData.find("LanguageSettings").find("order_id_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("billing_address_food").text())
      {
		billing_address_food=$menuXmlData.find("LanguageSettings").find("billing_address_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("shipping_address_food").text())
      {
		shipping_address_food=$menuXmlData.find("LanguageSettings").find("shipping_address_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("order_detail_food").text())
      {
		order_detail_food=$menuXmlData.find("LanguageSettings").find("order_detail_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("product_name_food").text())
      {
		product_name_food=$menuXmlData.find("LanguageSettings").find("product_name_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("price_food").text())
      {
		price_food=$menuXmlData.find("LanguageSettings").find("price_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("qty_food").text())
      {
		qty_food=$menuXmlData.find("LanguageSettings").find("qty_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("order_date_food").text())
      {
		order_date_food=$menuXmlData.find("LanguageSettings").find("order_date_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("subtotal_food").text())
      {
		subtotal_food=$menuXmlData.find("LanguageSettings").find("subtotal_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("tax_food").text())
      {
		tax_food=$menuXmlData.find("LanguageSettings").find("tax_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("coupon_food").text())
      {
		coupon_food=$menuXmlData.find("LanguageSettings").find("coupon_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("discount_food").text())
      {
		discount_food=$menuXmlData.find("LanguageSettings").find("discount_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("shipping_food").text())
      {
		shipping_food=$menuXmlData.find("LanguageSettings").find("shipping_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("grand_total_food").text())
      {
		grand_total_food=$menuXmlData.find("LanguageSettings").find("grand_total_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("payment_method_food").text())
      {
		payment_method_food=$menuXmlData.find("LanguageSettings").find("payment_method_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("order_status_food").text())
      {
		order_status_food=$menuXmlData.find("LanguageSettings").find("order_status_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("personal_information_food").text())
      {
		personal_information_food=$menuXmlData.find("LanguageSettings").find("personal_information_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("change_password_food").text())
      {
		change_password_food=$menuXmlData.find("LanguageSettings").find("change_password_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("current_password_food").text())
      {
		current_password_food=$menuXmlData.find("LanguageSettings").find("current_password_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("confirm_new_password_food").text())
      {
		confirm_new_password_food=$menuXmlData.find("LanguageSettings").find("confirm_new_password_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("no_product_exists_food").text())
      {
		no_product_exists_food=$menuXmlData.find("LanguageSettings").find("no_product_exists_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("add_to_cart_food").text())
      {
		add_to_cart_food=$menuXmlData.find("LanguageSettings").find("add_to_cart_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("cart_list_food").text())
      {
		cart_list_food=$menuXmlData.find("LanguageSettings").find("cart_list_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("edit_food").text())
      {
		edit_food=$menuXmlData.find("LanguageSettings").find("edit_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("done_food").text())
      {
		done_food=$menuXmlData.find("LanguageSettings").find("done_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("payment_details_food").text())
      {
		payment_details_food=$menuXmlData.find("LanguageSettings").find("payment_details_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("apply_coupon_code_food").text())
      {
		apply_coupon_code_food=$menuXmlData.find("LanguageSettings").find("apply_coupon_code_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("enter_your_coupon_code_if_you_have_one_food").text())
      {
		enter_your_coupon_code_if_you_have_one_food=$menuXmlData.find("LanguageSettings").find("enter_your_coupon_code_if_you_have_one_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("apply_food").text())
      {
		apply_food=$menuXmlData.find("LanguageSettings").find("apply_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("continue_shopping_food").text())
      {
		continue_shopping_food=$menuXmlData.find("LanguageSettings").find("continue_shopping_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("checkout_food").text())
      {
		checkout_food=$menuXmlData.find("LanguageSettings").find("checkout_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("first_name_food").text())
      {
		first_name_food=$menuXmlData.find("LanguageSettings").find("first_name_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("last_name_food").text())
      {
		last_name_food=$menuXmlData.find("LanguageSettings").find("last_name_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("address_food").text())
      {
		address_food=$menuXmlData.find("LanguageSettings").find("address_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("zip_postal_code_food").text())
      {
		zip_postal_code_food=$menuXmlData.find("LanguageSettings").find("zip_postal_code_food").text();
	  } 
	  if($menuXmlData.find("LanguageSettings").find("city_food").text())
      {
		city_food=$menuXmlData.find("LanguageSettings").find("city_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("country_food").text())
      {
		country_food=$menuXmlData.find("LanguageSettings").find("country_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("state_province_food").text())
      {
		state_province_food=$menuXmlData.find("LanguageSettings").find("state_province_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("telephone_food").text())
      {
		telephone_food=$menuXmlData.find("LanguageSettings").find("telephone_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("fax_food").text())
      {
		fax_food=$menuXmlData.find("LanguageSettings").find("fax_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("same_as_billing_address_food").text())
      {
		same_as_billing_address_food=$menuXmlData.find("LanguageSettings").find("same_as_billing_address_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("pay_now_food").text())
      {
		pay_now_food=$menuXmlData.find("LanguageSettings").find("pay_now_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("cash_on_delivery_food").text())
      {
		cash_on_delivery_food=$menuXmlData.find("LanguageSettings").find("cash_on_delivery_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("please_click_on_place_order_button_to_place_the_order_food").text())
      {
		please_click_on_place_order_button_to_place_the_order_food=$menuXmlData.find("LanguageSettings").find("please_click_on_place_order_button_to_place_the_order_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("credit_card_food").text())
      {
		credit_card_food=$menuXmlData.find("LanguageSettings").find("credit_card_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("paypal_food").text())
      {
		paypal_food=$menuXmlData.find("LanguageSettings").find("paypal_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("you_will_be_redirected_to_paypal_site_food").text())
      {
		you_will_be_redirected_to_paypal_site_food=$menuXmlData.find("LanguageSettings").find("you_will_be_redirected_to_paypal_site_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("order_by_phone_food").text())
      {
		order_by_phone_food=$menuXmlData.find("LanguageSettings").find("order_by_phone_food").text();
	  }
	  
	  
	   if($menuXmlData.find("LanguageSettings").find("you_can_order_by_calling_food").text())
      {
		you_can_order_by_calling_food=$menuXmlData.find("LanguageSettings").find("you_can_order_by_calling_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("place_order_food").text())
      {
		place_order_food=$menuXmlData.find("LanguageSettings").find("place_order_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("instock_food").text())
      {
		instock_food=$menuXmlData.find("LanguageSettings").find("instock_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("out_of_stock_food").text())
      {
		out_of_stock_food=$menuXmlData.find("LanguageSettings").find("out_of_stock_food").text();
	  }
	   if($menuXmlData.find("LanguageSettings").find("test_food").text())
      {
		test_food=$menuXmlData.find("LanguageSettings").find("test_food").text();
	  }
 if($menuXmlData.find("LanguageSettings").find("instructions_foodorder").text())
      {
		instructions_foodorder=$menuXmlData.find("LanguageSettings").find("instructions_foodorder").text();
	  }
	  if($menuXmlData.find("LanguageSettings").find("my_shop_food").text())
      {
		my_shop_food=$menuXmlData.find("LanguageSettings").find("my_shop_food").text();
	  }
	  if($menuXmlData.find("LanguageSettings").find("pickup_food").text())
      {
		pickup_food=$menuXmlData.find("LanguageSettings").find("pickup_food").text();
	  }

	
	 
	  
	
    $('.appypie-loader').show();
    if(localStorage.getItem("layout")=="slidemenu"){
        snapper.close();
    }
    localStorage.setItem("payTypeFoodOrEcom",'food');
    
    setTimeout(function(){
               $("#mainmenu").empty();
               if(!localStorage.getItem("fooduserid"))
              // $("#mainmenu").append('<a onclick="goHome()" class="menu-nav slideMainMenuBack">'+returnIconImageHtml('icon-left-1')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">Home</span></a><a onclick="getFoodorderingData()" class="home-nav">'+returnIconImageHtml('iconz-home')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">My Shop</span></a><a onclick="bindCart(\'mainpagefood\')" class="cart-nav">'+returnIconImageHtml('icon-cart')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">Cart</span></a><a onclick="myaccount()" class="account-nav">'+returnIconImageHtml('iconz-user')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">'+my_account_food+'</span></a><a onclick="termsEcom()" class="terms-nav">'+returnIconImageHtml('icon-info-circled')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">Terms & Conditions</span></a><a onclick="PolicyEcom()" class="policy-nav">'+returnIconImageHtml('iconz-briefcase')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">Privacy Policy</span></a>');
              // },1000);
              {
               $("#mainmenu").append('<a onclick="goHome()" class="menu-nav slideMainMenuBack">'+returnIconImageHtml('icon-left-1')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">'+homee_food+'</span></a><a onclick="getFoodorderingData()" class="home-nav">'+returnIconImageHtml('iconz-home')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">'+my_shop_food+'</span></a><a onclick="loginEcom()" class="login-nav">'+returnIconImageHtml('iconz-log-in')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">'+loginsignup_food+'</span></a><a onclick="bindCart(\'mainpagefood\')" class="cart-nav">'+returnIconImageHtml('icon-cart')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">'+cartt_food+'</span></a><a onclick="myaccount()" class="account-nav">'+returnIconImageHtml('iconz-user')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">'+my_account_food+'</span></a><a onclick="myaccount()" class="account-nav">'+returnIconImageHtml('icon-list-2')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">'+myorder_food+'</span></a><a onclick="termsEcom()" class="terms-nav">'+returnIconImageHtml('icon-info-circled')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">'+termscconditions_food+'</span></a><a onclick="PolicyEcom()" class="policy-nav">'+returnIconImageHtml('iconz-briefcase')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">'+privacypolicy_food+'</span></a>');
               
               }
               else
               {
               $("#mainmenu").append('<a onclick="goHome()" class="menu-nav slideMainMenuBack">'+returnIconImageHtml('icon-left-1')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">'+homee_food+'</span></a><a onclick="getFoodorderingData()" class="home-nav">'+returnIconImageHtml('iconz-home')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">'+my_shop_food+'</span></a><a onclick="logout()" class="login-nav">'+returnIconImageHtml('iconz-log-in')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">'+lllogout_food+'</span></a><a onclick="bindCart(\'mainpagefood\')" class="cart-nav">'+returnIconImageHtml('icon-cart')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">'+cartt_food+'</span></a><a onclick="myaccount()" class="account-nav">'+returnIconImageHtml('iconz-user')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">'+my_account_food+'</span></a><a onclick="myaccount()" class="account-nav">'+returnIconImageHtml('icon-list-2')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">'+myorder_food+'</span></a><a onclick="termsEcom()" class="terms-nav">'+returnIconImageHtml('icon-info-circled')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">'+termscconditions_food+'</span></a><a onclick="PolicyEcom()" class="policy-nav">'+returnIconImageHtml('iconz-briefcase')+'<span style="color:'+sessionStorage.getItem('navTextColor')+' !important">'+privacypolicy_food+'</span></a>');
               }
 
              },1000);
    $('.appypie-loader').show();
    window.location = "iad:"+"hide";
    
    sessionStorage.setItem("AppPageName","foodordering");
//    $("#logo").hide();
//    $("#mainback").hide();
//    $("#shopmenu").show();
//    $("#mainbackfoodecom").hide();
//    $("#reload").hide();
    
    appid=localStorage.getItem("applicationID");
    sessionStorage.setItem("foodorderingIndex",index);
//    $("#logo").hide();
//    $("#mainback").hide();
//    $("#shopmenu").show();
    categoriesListView();
//    if(checkNetworkConnection())
//    {
//        wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#generateFoodCategoryXml";
//        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><generateFoodCategoryXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#generateFoodCategoryXml\"><appId>'+appid+'</appId></generateFoodCategoryXml></soap:Body></soap:Envelope>';
//        
//        console.log(soapRequest);
//        jQuery.support.cors = true;
//        $.ajax({
//               type: "POST",
//               url: wsUrl,
//               contentType: "text/xml",
//               cache: false,
//               dataType: "text",
//               data: soapRequest,
//               success: function(data, status, req)
//               {
//               var strJSON = $(req.responseText).find("return").text();
//               console.log("food category xml--->"+strJSON);
//               foodCategoryView(strJSON,index);
//               },
//               error: function(response, textStatus, errorThrown)
//               {
//               console.log(response);
//               setTimeout(function(){$('.appypie-loader').hide();},1000);
//               }
//               });
//    }
    
}
function categoriesListView(strJSON,index) {
    
	
	
    if(localStorage.getItem("fooduserid"))
    {
        
        accountDispl='block';
        loginDispl='none';
    }
    else
    {
        accountDispl='none';
        loginDispl='block';
    }
    
    foodHTML_home = '<div id="appypie-search-section">\
    <input data-role="none" type="text" id="txtSearch" placeholder="'+searchproducts_food+'"/><input data-role="none" type="button" value='+search_food+' '+primaryColor+' onclick="openSearchProducts()" /></div>\
    <div class="cart-MSG" id="cart-MSG1" style="display:none"><span class="er_close">x</span>\
    <div class="cart_success"></div></div><div id="categories-listing"><h3 class="catName">'+categories_food+'<h3><span id="show"><a>+</a></span><span id="hide"><a>-</a>\
    </span><div id="categoryList"></div></div><div class="categories-listing" id = "featuredProducts"><h3 class="catName">'+featureddproducts_food+'</h3><ul class="food_pro_list_new">\
    </ul></div><ul class="sub-menu" style="display:none;" >\
    <li><a onclick="home()" class="home-nav">'+home_food+'</a></li>\
    <li style="display:'+loginDispl+';"><a onclick="loginEcom()" class="login-nav">'+login_food+'</a></li>\
    <li style="display:'+accountDispl+';"><a onclick="myaccount()" class="account-nav">'+my_account_food+'</a></li>\
    <li><a onclick="goHome()" class="menu-nav">'+my_shop_food+'</a></li>\
	<li><a onclick="bindCart(\'mainpagefood\')" class="cart-nav">'+cartt_food+'</a></li>\
    <li><a onclick="termsEcom()" class="terms-nav">'+termscconditions_food+'</a></li>\
    <li><a onclick="PolicyEcom()" class="policy-nav">'+privacypolicy_food+'</a></li>\
    </ul>';
    
    
    //appendHtml(foodHTML_home,'',1);
    appendHtml(foodHTML_home,'',1,'food');
    
    $("#featuredProducts").hide();
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#generateFoodCategoryXml";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><generateFoodCategoryXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#generateFoodCategoryXml\"><appId>'+localStorage.getItem("applicationID")+'</appId></generateFoodCategoryXml></soap:Body></soap:Envelope>';
    
    jQuery.support.cors = true;
    
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           console.log("strJSON---"+strJSON);
           
           categorylistOption(strJSON);
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    
    
    
    $("#show").click(function(){
                     $("#categoryList").slideToggle();
                     $("#hide").css("display", "block");
                     });
    
    $("#hide").click(function(){
                     $("#categoryList").slideToggle();
                     $("#hide").css("display", "none");
                     });
    
}

function categorylistOption(strJSON){
    $.ajax({
           type: "GET",
           url: strJSON,
           dataType: "xml",
           success: xmlParser
           });
}
function xmlParser(xml) {
    
    html="<ul>";
    $(xml).find("category").each(function () {
                                 
                                 var categoryId= $(this).find("categoryId").text();
                                 var categoryName= $(this).find("categoryName").text();
                                 html+='<li><a onclick="openFoodListPage(\''+categoryId+'\',\''+escape(categoryName)+'\')">'+categoryName+'</a></li>'
                                 
                                 
                                 });
    html+="</ul>"
    $("#categoryList").html(html);
}
function openSearchProductsnew()
{
    var txtSearch=document.getElementById("txtSearch2").value;
    sessionStorage.setItem("foodsearchText",txtSearch);
    
    if(sessionStorage.getItem("foodsearchText"))
    {
        
        searchProductsfood();
    }
    else
    {
        openEcomListPage(sessionStorage.getItem("ecomcategoryid"),sessionStorage.getItem("ecomcategoryName"));
    }
    
}

function openSearchProducts()
{
	
    if(checkNetworkConnection())
    {
        $('.appypie-loader').show();
        var txtSearch=document.getElementById("txtSearch").value;
        
        if(txtSearch.length > 0)
        {
            sessionStorage.setItem("foodsearchText",txtSearch);
            console.log("txtSearch --->"+txtSearch);
            
            searchPage_HTML='<div id="appypie-search-section">\
            <input data-role="none" type="text" id="txtSearch2" placeholder="'+searchproductin_food+'"/><input data-role="none" type="button" value="'+ssearch_food+'" '+primaryColor+' onclick="openSearchProductsnew()" />\
            </div><div class="cart-MSG" id="cart-MSG1" style="display:none"><span class="er_close">x</span>\
            <div class="cart_success"></div></div><div class="categories-listing"><h3 class="catName" id="ecomSrchTitle">'+txtSearch+'</h3><ul class="food_pro_list">\
            </ul></div><ul class="sub-menu" style="display:none;" >\
            <li id="homeIcon" class="homeIconClass"><a onclick="home()" class="home-nav">'+home_food+'</a></li>\
            <li style="display:'+loginDispl+';"><a onclick="loginEcom()" class="login-nav">'+login_food+'</a></li>\
            <li style="display:'+accountDispl+';"><a onclick="myaccount()" class="account-nav">'+my_account_food+'</a></li>\
            <li><a onclick="goHome()" class="menu-nav">'+my_shop_food+'</a></li>\
			<li><a onclick="bindCart(\'mainpagefood\')" class="cart-nav">'+cartt_food+'</a></li>\
            <li><a onclick="termsEcom()" class="terms-nav">'+termscconditions_food+'</a></li>\
            <li><a onclick="PolicyEcom()" class="policy-nav">'+privacypolicy_food+'</a></li>\
            </ul>';
            
            appendHtml(searchPage_HTML,2,2,'food');
            var txtSearch=document.getElementById("txtSearch").value;
            sessionStorage.setItem("foodsearchText",txtSearch);
            
            if(sessionStorage.getItem("foodsearchText"))
            {
                searchProductsfood();
            }
            else
            {
                openEcomListPage(sessionStorage.getItem("foodcategoryid"),sessionStorage.getItem("foodcategoryName"));
            }
        }
        else
        {
        	
            alertPopUp(aalert_food,pleaseenterkeywordsearch_food);
            return false;
        }
        
    }
}

function searchProductsfood()
{
    var txtSearch=sessionStorage.getItem("foodsearchText");
    
    document.getElementById("ecomSrchTitle").innerHTML=txtSearch;
    var id=localStorage.getItem("applicationID")
    var catid ="catid"
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodSearchXml";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodSearchXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodSearchXml\"><appId>'+localStorage.getItem("applicationID")+'</appId><search>'+txtSearch+'</search></foodSearchXml></soap:Body></soap:Envelope>';
    
    console.log('searchProducts---------->>>>'+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           cache: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           
           console.log('searchProducts--->strJSON---------->>>>'+strJSON);
           listFoodCategories(strJSON);
           },
           error: function(response, textStatus, errorThrown)
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
}

//function foodCategoryView(strJSON,index) {
//    
//    
//    if(parseInt(window.sessionStorage.getItem("checkoutFlag"))>0)
//    {
//        var displayValue="block";
//    }
//    else
//    {
//        var displayValue="none";
//    }
//    
//    if(localStorage.getItem("fooduserid"))
//    {
//        
//        accountDispl='block';
//        loginDispl='none';
//    }
//    else
//    {
//        accountDispl='none';
//        loginDispl='block';
//    }
//    var counter=0;
//    foodlist_HTML='<div class="product-top"><h3 id="product_pageHeader" >Menu Categories</h3>\
//    <div class="checkOut-Btn" onclick="bindCart(\'mainpagefood\')" id="checkoutbtn" style="display:'+displayValue+'">Checkout</div></div>\
//    <div id="foodordering-listing">\
//    <div class="cart-MSG" id="cart-MSG3" style="display:none">\
//    <div class="cart_success">You have logged out successfully.</div>\
//    </div>\
//    </div><ul id="sub-menu" class="sub-menu" style="display:none;" >\
//    <li id="homeIcon" class="homeIconClass"><a onclick="home()" class="home-nav">'+home_food+'</a></li>\
//    <li style="display:'+loginDispl+';"><a onclick="loginEcom()" class="login-nav">Login / Registration</a></li>\
//    <li style="display:'+accountDispl+';"><a onclick="myaccount()" class="account-nav">'+my_account_food+'</a></li>\
//    <li><a onclick="goHome()" class="menu-nav">Main Menu</a></li>\
//    <li><a onclick="termsEcom()" class="terms-nav">Terms & Conditions</a></li>\
//    <li><a onclick="PolicyEcom()" class="policy-nav">Privacy Policy</a></li>\
//    </ul>';
//    
//    
//    appendHtml(foodlist_HTML,'',1,'food');
//    listFoodCategories(strJSON);
//    
//    
//}
function listFoodCategories(strJSON)
{
    $.ajax({
           url: strJSON,
           dataType: "text",
           crossDomain: true,
           cache: false,
           success: function(newxml) {
           html='';
           var counter=0;
           
           var xml=jQuery.parseXML(newxml);
           console.log("xml======"+newxml);
           $(xml).find("productList").each(function () {
                                           $(xml).find("product").each(function () {
                                                                       counter=counter+1;
                                                                       var productId= $(this).find("productId").text();
                                                                       var productName= $(this).find("productName").text();
                                                                       var price= $(this).find("price").text();
                                                                       var productImage= $(this).find("productImage").text();
                                                                       var productId= $(this).find("productId").text();
                                                                       var currency= $(this).find("currency").text();
                                                                       var description=$(this).find("description").text()
                                                                       var foodType= $(this).find("foodType").text();
                                                                       if(productName.length>30) {
                                                                       productName=productName.substring(0,30);
                                                                       }
                                                                       if(description.length>30) {
                                                                       description=description.substring(0,30);
                                                                       }
                                                                       if(foodType=='Non-Veg') {
                                                                       foodType='nonVeg';
                                                                       }
                                                                       else if(foodType=='Veg'){
                                                                       foodType='veg';
                                                                       }
                                                                       else
                                                                       {
                                                                       foodType='';
                                                                       }
                                                                       
                                                                       html+='<li  onclick="openDetailPage(\''+productId+'\')"><span class="food-proImg"><img class="productImageClass" src="'+productImage+'"  border="0" /><span class="'+foodType+'"></span></span><h4>'+productName+'</h4><h5>'+description+'</h5><div class="food-mobileProPrice">'+currency+price+'<span class="food-addtocart">'+adddd_food+'</span></div></li>';
                                                                       });
                                           });
           
           
           if(counter>0)
           {
           $(".food_pro_list").empty();
           $(".food_pro_list").append(html);
           console.log("dharmendra--- >" +html );
           }
           else
           {
        	   
        	 
           $(".food_pro_list").empty();
           $(".food_pro_list").append('<center><b><h2>'+noproductfoundd_food+'</h2></b><br/></center>');
           }
           if(sessionStorage.getItem("foodsearchText"))
           {
        	   
        	  
           $("#product_pageHeader").empty();
           $("#product_pageHeader").append(searchresultt_food + "("+sessionStorage.getItem("foodsearchText")+")");
           sessionStorage.removeItem("foodsearchText")
           }
           else
           {
           $("#product_pageHeader").empty();
           $("#product_pageHeader").append(sessionStorage.getItem("foodcategoryName"));
           }
           newxml={};
           },
           error: function(XMLHttpRequest, textStatus, errorThrown) {
           console.log('fail');
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
           }
           });
    setTimeout(function(){$('.appypie-loader').hide();},1000);
}
//function listFoodCategories(strJSON)
//{
//    $.ajax({
//           url: strJSON,
//           dataType: "text",
//           crossDomain: true,
//           cache: false,
//           success: function(newxml) {
//           html='<ul>';
//           var counter=0;
//           var xml=jQuery.parseXML(newxml);
//           $(xml).find("category").each(function () {
//                                        counter=counter+1;
//                                        var categoryId= $(this).find("categoryId").text();
//                                        var categoryName= $(this).find("categoryName").text();
//                                        
//                                        html+='<li><a onclick="openFoodListPage(\''+categoryId+'\',\''+escape(categoryName)+'\')">'+categoryName+'</a></li>';
//                                        });
//           html+='</ul>';
//           console.log("html category--->"+html);
//           if(counter>0)
//           {
//           $("#foodordering-listing").empty();
//           $("#foodordering-listing").append(html);
//           }
//           else
//           {
//           $("#foodordering-listing").empty();
//           $("#foodordering-listing").append('<center><b><h2>There is no food category added</h2></b><br/></center>');
//           }
//           setTimeout(function(){$('.appypie-loader').hide();},1000);
//           },
//           error: function(XMLHttpRequest, textStatus, errorThrown) {
//           console.log('fail');
//           console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
//           setTimeout(function(){$('.appypie-loader').hide();},1000);
//           }
//           
//           });
//    setTimeout(function(){$('.appypie-loader').hide();},1000);
//}

function home()
{
    $.mobile.changePage("#page1", {transition: 'slidefade',reverse: true });
    if(localStorage.getItem("layout")=="bottom") {
        $(".app_navigation_bottom").show();
        localStorage.setItem("bottomHide","false");
    }
    $("#shopmenu").hide();
    $("#logo").show();
    $("#mainback").hide();
    $("#mainbackfoodecom").hide();
    Showhideupdatebutton();
    $("#bookmark").hide();
    sessionStorage.setItem("socialpageback","false");
    sessionStorage.setItem("socialpageback_comment","false");
	//dynamicCSS('');
	
    if(localStorage.getItem("layout")=="slidemenu"){
      openPage(0);
    }
	
}
function openFoodListPage(id,categoryName)
{
    categoryName=unescape(categoryName);
    if(checkNetworkConnection())
    {
        $('.appypie-loader').show();
        $("#mainbackfoodecom").show();
        $("#reload").hide();
        var counter=0;
        var ClassProduvtName;
        window.sessionStorage.setItem("foodcategoryid",id);
        window.sessionStorage.setItem("foodcategoryName",categoryName);
        if(parseInt(window.sessionStorage.getItem("checkoutFlag"))>0)
        {
            var displayValue="block";
            ClassProduvtName='checkOut-BtnName';
        }
        else
        {
            var displayValue="none";
            ClassProduvtName='';
        }
        
        var html_products='<div class="product-top"><h3 id="product_pageHeader" class='+ClassProduvtName+'>'+categoryName.substring(0,15)+'</h3>\
        <div class="checkOut-Btn" '+primaryColor+' onclick="bindCart(\'mainpagefood\')" id="checkoutbtn" style="display:'+displayValue+'">'+checkout_food+'</div></div><div class="food-ordering-product"><div id="cat-option" class="cat-option"></div><ul class="food_pro_list">\
        </ul></div><ul class="sub-menu" style="display:none;" >\
        <li id="homeIcon" class="homeIconClass"><a onclick="home()" class="home-nav">'+home_food+'</a></li>\
        <li style="display:'+loginDispl+';"><a onclick="loginEcom()" class="login-nav">'+login_food+'</a></li>\
        <li style="display:'+accountDispl+';"><a onclick="myaccount()" class="account-nav">'+my_account_food+'</a></li>\
        <li><a onclick="goHome()" class="menu-nav">'+my_shop_food+'</a></li>\
		<li><a onclick="bindCart(\'mainpagefood\')" class="cart-nav">'+cartt_food+'</a></li>\
        <li><a onclick="termsEcom()" class="terms-nav">'+termscconditions_food+'</a></li>\
        <li><a onclick="PolicyEcom()" class="policy-nav">'+privacypolicy_food+'</a></li>\
        </ul>';
        
        appendHtml(html_products,2,2,'food');
        
        if(parseInt(window.sessionStorage.getItem("checkoutFlag"))>0)
        {
            document.getElementById("checkoutbtn").style.display="block";
        }
        
        showFoodProducts(id,categoryName);
        
        
        
        
        
    }
    
    
}
function showFoodProducts(catid,categoryName)
{
    var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodSubCategoryProductXml";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodSubCategoryProductXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodSubCategoryProductXml\"><appId>'+appid+'</appId><parentId>'+catid+'</parentId></foodSubCategoryProductXml></soap:Body></soap:Envelope>';
cache: false,
    console.log(soapRequest);
    jQuery.support.cors = true;
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(data).find("return").text();
           console.log('showFoodProducts----->'+strJSON);
           ShowFoodProducts(strJSON);
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
}
function showcat(catname)
{
    $(".food_pro_list").empty();
    $(".food_pro_list").append(window.sessionStorage.getItem(document.getElementById("cat_list").value));
}
function ShowFoodProducts(xml)
{
    $.ajax({
           url: xml,
           dataType: "text",
           cache: false,
           crossDomain: true,
           success: function(newxml) {
           var xmll =jQuery.parseXML(newxml);
           var tcount=0;
           var ccount=0;
           var catcount=0;
           var maincatname='';
           var newcatcount=0;
           var categorieslist='<select id="cat_list" onchange="showcat()">';
           
           var productlist='';
           $(xmll).find("subcategory").each(function () {
                                            var subcatName= $(this).find("subcatName").text();
                                            if(catcount==0)
                                            {
                                            
                                            if(subcatName!=window.sessionStorage.getItem("foodcategoryName"))
                                            {
                                            categorieslist+='<option>'+sessionStorage.getItem("foodcategoryName")+'</option>';
                                            newcatcount=1;
                                            }
                                            maincatname=window.sessionStorage.getItem("foodcategoryName");
                                            }
                                            else
                                            {
                                            productlist+='<div class="categoriesNameList">'+subcatName+'</div>';
                                            }
                                            categorieslist+='<option>'+subcatName+'</option>';
                                            catcount=catcount+1;
                                            var $list = $(this);
                                            var sub_cat='';
                                            $list.find("product").each(function () {
                                                                       var id= $(this).find("id").text();
                                                                       var ptitle= $(this).find("ptitle").text();
                                                                       if(ptitle.length>30) {
                                                                       ptitle=ptitle.substring(0,30);
                                                                       }
                                                                       var productImage= $(this).find("productImage").text();
                                                                       var description= $(this).find("description").text();
																	     var quantity= $(this).find("quantity").text();

                                                                       if(description.length>30) {
                                                                       description=description.substring(0,30);
                                                                       }
                                                                       var currency= $(this).find("currency").text();
                                                                       var price= $(this).find("price").text();
                                                                       var status= $(this).find("status").text();
                                                                       var foodType= $(this).find("foodType").text();
                                                                       ccount=0;
                                                                       if(status=="1")
                                                                       {
                                                                       if(foodType=='Non-Veg')
                                                                       foodType='nonVeg';
                                                                       else if(foodType=='Veg')
                                                                       foodType='veg';
                                                                       else
                                                                       foodType='';
                                                                       
                                                                       ccount=ccount+1;
                                                                       tcount=tcount+1;
                                                                       sub_cat+='<li onclick="openDetailPage(\''+id+'\')"><span class="food-proImg"><img  src="'+productImage+'"  border="0" /><span class="'+foodType+'"></span></span><h4>'+ptitle+'</h4><h5>'+description+'</h5><div class="food-mobileProPrice">'+currency+' '+parseFloat(price).toFixed(2)+'<span class="food-addtocart">'+adddd_food+'</span></div></li>';
                                                                       productlist+='<li onclick="openDetailPage(\''+id+'\')"><span class="food-proImg"><img  src="'+productImage+'"  border="0" /><span class="'+foodType+'"></span></span><h4>'+ptitle+'</h4><h5>'+description+'</h5><div class="food-mobileProPrice">'+currency+' '+parseFloat(price).toFixed(2)+'<span class="food-addtocart">'+adddd_food+'</span></div></li>';
                                                                       }
                                                                       if(ccount>0 && (catcount>1 || newcatcount==1))
                                                                       {
                                                                       window.sessionStorage.setItem(subcatName,sub_cat);
                                                                       }
                                                                       else
                                                                       {
                                                                    	   
                                                                    	   
                                                                       var msg='<div style="text-align:center; color:#000"> <b>'+thereproductadded_food+'</b></div>';
                                                                       console.log(subcatName);
                                                                       console.log(msg);
                                                                       window.sessionStorage.setItem(subcatName,msg);
                                                                       }
                                                                       });
                                            newcatcount=0;
                                            });
           if(catcount>1)
           {
           document.getElementById('cat-option').style.display = "block";
           }
           categorieslist+='</select>';
           product_pageHeader
           
           $(".cat-option").empty();
           $(".cat-option").append(categorieslist);
           
           $(".food_pro_list").empty();
           
           if(tcount>0)
           $(".food_pro_list").append(productlist);
           else
           $(".food_pro_list").append('<center><b><h2>'+thereproductadded_food+'</h2></b><br/></center>');
           
           if(tcount>0)
           window.sessionStorage.setItem(maincatname,productlist);
           else
           window.sessionStorage.setItem(maincatname,'<center><b><h2>'+thereproductadded_food+'</h2></b><br/></center>');
           
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           },
           error: function(XMLHttpRequest, textStatus, errorThrown) {
           console.log('fail');
           console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    //setTimeout(function(){$('.appypie-loader').hide();},1000);
}
function openDetailPage(id)
{
	
	
    if(checkNetworkConnection())
    {
        $('.appypie-loader').show();
        var counter=0;
        var ClassProduvtName;
        localStorage.setItem("detailPageId",id);
        if(parseInt(window.sessionStorage.getItem("checkoutFlag"))>0)
        {
            var displayValue="block";
            ClassProduvtName='checkOut-BtnName';
        }
        else
        {
            var displayValue="none";
            ClassProduvtName='';
        }
        
        html_productDetail='<div class="product-top"><h3 id="productName" class='+ClassProduvtName+'></h3>\
        <div class="checkOut-Btn" '+secondaryColor+' onclick="bindCart(\'mainpagefood\')" id="checkoutbtn" style="display:'+displayValue+'">'+checkout_food+'</div></div><div class="food-ordering-product"><div class="cart-MSG" style="display:none"><span class="er_close">x</span><div class="cart_success">'+productsuccessfullyaddedcart_food+'</div></div>\
        <div class="cart-MSG" style="display:none"><span class="er_close">x</span><div class="cart_error">'+productquantityzero_food+'</div></div>\
        <div class="food-ordering-product"></div><div class="food-pro_details" id="pro_details"></div><div class="food-pro_details" style="display:none"><h4>\
        <span>'+sizeformeal_food+'</span></h4></div><div class="food-pro_details" id="configurableProduct"></div><div class="food-pro_details"><div class="food-quantity">'+qty_food+':<input data-role="none" id="quantity" type="tel" style="width:70px;" /></div><br/>\
            <span><img width="35px" onclick="minusButton()" src="images/e-com/sliderminus.png"/></span><div id="slider"></div><span><img width="35px" onclick="plusButtonFood()" src="images/e-com/sliderplus.png"/></span>\
            </div></div><a onclick="cart_add()"  '+primaryColor+' class="addtocart-btn">'+add_to_cart_food+'</a>\
            </div></div><ul class="sub-menu" style="display:none;" >\
            <li id="homeIcon" class="homeIconClass"><a onclick="home(\''+localStorage.getItem("pageIndex")+'\')" class="home-nav">'+home_food+'</a></li>\
            <li style="display:'+loginDispl+';"><a onclick="loginEcom()" class="login-nav">'+login_food+'</a></li>\
            <li style="display:'+accountDispl+';"><a onclick="myaccount()" class="account-nav">'+my_account_food+'</a></li>\
            <li><a onclick="goHome()" class="menu-nav">'+my_shop_food+'</a></li>\
			<li><a onclick="bindCart(\'mainpagefood\')" class="cart-nav">'+cartt_food+'</a></li>\
            <li><a onclick="termsEcom()" class="terms-nav">'+termscconditions_food+'</a></li>\
            <li><a onclick="PolicyEcom()" class="policy-nav">'+privacypolicy_food+'</a></li>\
            </ul><div></div>';
             appendHtml(html_productDetail,3,3,'food');
        document.getElementById("quantity").value= 1;
        window.sessionStorage.setItem("productid",id);
        
        
        
        showProductdetails(id);
        var Quantity = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48','49','50','51','52','53','54','55','56','57','58','59','60','61','62','63','64','65','66','67','68','69','70','71','72','73','74','75','76','77','78','79','80','81','82','83','84','85','86','87','88','89','90','91','92','93','94','95','96','97','98','99','100'];
        
        
        $('#slider').slider({
                            min: 1,
                            max: 100,
                            step: 1,
                            create: function (event, ui) {
                            
                            document.getElementById("quantity").value= Quantity[0];
                            //alert("create");
                            },
                            slide: function (event, ui) {
                            //   alert(ui.value);
							//modified by mohsin
							var slider_qty=Quantity[ui.value];
							var session_qty=window.sessionStorage.getItem("quantity");
							var actual_slider_value=parseInt((session_qty*slider_qty)/100);
							
                            document.getElementById("quantity").value= actual_slider_value;
                            //console.log("quantity change"+Quantity[ui.value]);
							if(actual_slider_value==0){
								document.getElementById("quantity").value=1;					
							}
							//--
                            
                            }
                            
                            });
        $("#quantity").change(function() {
                              if(parseInt(document.getElementById("quantity").value)>0){
                              var inputValue= document.getElementById("quantity").value;
							  //modified by mohsin
							  var session_qty=window.sessionStorage.getItem("quantity");
							  var actual_slider_value=parseInt((100*parseInt(inputValue))/session_qty);
                              $("#slider").slider("value",actual_slider_value);
							  //--
                              }
                              else {
                              $("#cart-MSG").slideToggle();
                              setTimeout( function(){
                                         $("#cart-MSG").slideToggle();
                                         },2500);
                              document.getElementById("quantity").value=1;
							  $("#slider").slider("value",1);
                              }
                              });
        
    }
    
    
    
    //alert("complete");
    //alert($("#contentHolder3").html());
}

function minusButton(){
    var quantity=document.getElementById("quantity").value;
    //alert(quantity)
    if(parseInt(quantity)>1) {
        var parsedQuantity= parseInt(quantity)-1;
        document.getElementById("quantity").value= parsedQuantity;
		
		//modified by mohsin
		var session_qty=window.sessionStorage.getItem("quantity");
		var actual_slider_value=parseInt((100*parsedQuantity)/session_qty);
        $("#slider").slider("value",actual_slider_value);
		//-

    }else{
		document.getElementById("quantity").value=1;
		$("#slider").slider("value",0);
	}
}

function plusButtonFood(){
	
    var quantity=document.getElementById("quantity").value;
	var parsedQuantity= parseInt(quantity)+1;
	
	//modified by mohsin
	var actual_qty=window.sessionStorage.getItem("quantity");
	
	if(parseInt(parsedQuantity)>parseInt(actual_qty)){
		alert(youhaveaddedmaximumquantity_food);
		return;
	}

	var actual_slider_value=parseInt((100*parsedQuantity)/actual_qty);
    document.getElementById("quantity").value= parsedQuantity;
    $("#slider").slider("value",actual_slider_value);
	//--
	
    //$(".ui-slider-handle").css('left',parsedQuantity+'%');
}

function plusButton(id){
    var quantity=document.getElementById("quantity"+id).value;
    var parsedQuantity= parseInt(quantity)+1;
    //alert("parsed"+parsedQuantity);
    document.getElementById("quantity"+id).value= parsedQuantity;
    $("#slider"+id).slider("value",parsedQuantity);
    if(sessionStorage.getItem("ecomquantity"))
    {
        if( parseInt(parsedQuantity) > parseInt(sessionStorage.getItem("ecomquantity")) )
        {
            document.getElementById("quantity"+id).value=(parseInt(sessionStorage.getItem("ecomquantity")));
            $("#slider"+id).slider("value",(parseInt(sessionStorage.getItem("ecomquantity"))));
        }
    }
}
function showProductdetails(productid)
{
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodproductDetailXml";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodproductDetailXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodproductDetailXml\"><appId>'+appid+'</appId><id>'+productid+'</id></foodproductDetailXml></soap:Body></soap:Envelope>';
    
    console.log(soapRequest);
    jQuery.support.cors = true;
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           showDetail(strJSON);
           console.log('product detail return--->'+strJSON);
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    
}
var option_id=new Array();
var option_title=new Array();
var option_req=new Array();
var option_sort=new Array();
function showDetail(strJSON)
{
	
	
	 
    $.ajax({
           url: strJSON,
           dataType: "text",
           crossDomain: true,
           cache: false,
           success: function(newxml) {
           var xml =jQuery.parseXML(newxml);
           
           html='<ul>';
           option_id.length=0;
           option_title.length=0;
           option_req.length=0;
           //var option_req=new Array();
           // var option_sort=new Array();
           var option_row=new Array();
           var dropdownHtml='';
           
           $(xml).find("product").each(function () {
                                       var productId= $(this).find("productId").text();
                                       var productName= $(this).find("productName").text();
                                       var catName= $(this).find("catName").text();
                                       var quantity= $(this).find("quantity").text();
                                       var price= $(this).find("price").text();
                                       var currency= $(this).find("currency").text();
									    var backorder= $(this).find("backorder").text();
                                       localStorage.setItem("backorder",backorder);
                                       var discount= $(this).find("discount").text();
                                       var description= $(this).find("description").text();
                                       var productImage= $(this).find("productImage").text();
                                       var foodType=$(this).find("foodType").text();
                                       if(foodType=='Non-Veg') {
                                       foodType='nonVeg';
                                       }
                                       else if(foodType=='Veg'){
                                       foodType='veg';
                                       }
                                       else
                                       {
                                       foodType='';
                                       }
                                       
                                       if($(this).find("customoptions").find("option").text())
                                       {
                                       
                                       $(this).find("customoptions").find("option").each(function () {
                                                                                         console.log("in showDetail() -->1");
                                                                                         option_id.push($(this).find("option_id").text());
                                                                                         console.log("in showDetail() -->2");
                                                                                         option_title.push($(this).find("option_title").text());
                                                                                         console.log("in showDetail() -->3");
                                                                                         option_req.push($(this).find("option_req").text());
                                                                                         console.log("in showDetail() -->4");
                                                                                         option_sort.push($(this).find("option_sort").text());
                                                                                         console.log("in showDetail() -->5");
                                                                                         option_row.push($(this).find("option_row").text());
                                                                                         console.log("in showDetail() -->6");
                                                                                         dropdownHtml+='<label>'+$(this).find("option_title").text()+':-</label><select id="'+$(this).find("option_id").text()+'" onchange="checkConfigProduct();"><option value="select">'+select_food+'</option>';
                                                                                         console.log('option_id--->'+option_id[0]+'--option_title--->'+option_title[0]+'----option_req--->'+option_req[0]+'--option_sort--->'+option_sort[0]+'----option_row--->'+option_row[0]);
                                                                                         var jsonObj=jQuery.parseJSON($(this).find("option_row").text());
                                                                                         console.log('jsonObj 1');
                                                                                         var showPrice='';
                                                                                         for(var i=0;i<jsonObj.length;i++)
                                                                                         {
                                                                                         
                                                                                         var row_title=jsonObj[i].row_title;
                                                                                         console.log('jsonObj 2');
                                                                                         var row_price=jsonObj[i].row_price;
                                                                                         console.log('jsonObj 3');
                                                                                         var row_pricetype=jsonObj[i].row_pricetype;
                                                                                         console.log('jsonObj 4');
                                                                                         if(row_pricetype=='m')
                                                                                         {
                                                                                        	
                                                                                        	 showPrice=subbb_food+row_price.trim();
                                                                                         row_price='-'+row_price.trim();
                                                                                         }
                                                                                         else if(row_pricetype == '')      
                                                                                         {
                                                                                        	
                                                                                        	 showPrice=addzero_food;
                                                                                         row_price='+0';
                                                                                         }
                                                                                         else
                                                                                         {
                                                                                        	   showPrice=addddd_food+row_price.trim();
                                                                                         row_price='+'+row_price.trim();
                                                                                         }
                                                                                         console.log('jsonObj 5');
                                                                                         var showFinalPrice='';
																						 //alert("gou :" + jsonObj[i].row_price.trim());
                                                                                         //if(row_price.length==1)
																						 if(parseInt(jsonObj[i].row_price.trim())<1 || isNaN(parseInt(jsonObj[i].row_price.trim())))
                                                                                         {
                                                                                         
                                                                                         var newPrice=row_title;
                                                                                         showFinalPrice=row_title;
																						 //alert(newPrice+ " ---" + showFinalPrice );
                                                                                         }
                                                                                         else
                                                                                         {
                                                                                         var newPrice=row_title+','+row_price.trim();
                                                                                         showFinalPrice=row_title+','+showPrice.trim();
                                                                                         }
                                                                                         
                                                                                         
                                                                                         console.log('jsonObj 6');
                                                                                         dropdownHtml+='<option value="'+escape(newPrice)+'">'+showFinalPrice+'</option>';
                                                                                         }
                                                                                         dropdownHtml+='</select><br/>';
                                                                                         });
                                       $("#configurableProduct").empty();
                                       $("#configurableProduct").append(dropdownHtml);
                                       }
                                       else
                                       {
                                       sessionStorage.setItem("ecomconfigProduct",'false');
                                       }
                                       
                                       var stock='';
                                    
                                        if(parseInt(quantity)>0 || backorder>0) {
                                       
                                       stock=instock_food;
                                       $("#food-quantity").show();
                                       $(".addtocart-btn").show();
                                       }
                                       else {
                                       stock=out_of_stock_food;
                                       $("#food-quantity").hide();
                                       $(".addtocart-btn").hide();
                                       }
                                       
                                       window.sessionStorage.setItem("productName",productName);
                                       window.sessionStorage.setItem("productImage",productImage);
                                       sessionStorage.removeItem('ecomprice');
                                       window.sessionStorage.setItem("price",price);
                                       window.sessionStorage.setItem("currency",currency);
                                       window.sessionStorage.setItem("productdescription",description);
                                       window.sessionStorage.setItem("foodType",foodType);
                                       window.sessionStorage.setItem("quantity",quantity);
                                       
                                       console.log('=====>'+productName);
                                       $("#productName").append(productName);
                                       $("#pro_details").append('<span class="foodDetailproImg"><span class="'+foodType+'"></span></span><img src="'+productImage+'" class="food-pro_details-img" /><p>'+description+'</p><h3>'+currency+' <span id="productPrice">'+parseFloat(price).toFixed(2)+'</span></h3><h3 id="stockid" class="stock">'+stock+'</h3><div style="clear:both; height:3px;">');
                                       
                                       
                                       
                                       });
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           },
           error: function(XMLHttpRequest, textStatus, errorThrown) {
           console.log('fail');
           console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           
           });
}



function cart_add()
{
	
	//modified by mohsin
	var actual_qty=window.sessionStorage.getItem("quantity");
	var quantity= document.getElementById("quantity").value;
	
	if(parseInt(quantity)>parseInt(actual_qty)){
		alert(pleaseenterquantityessthann_food+parseInt(actual_qty));
		return;
	}
	//--
	
    $('.appypie-loader').show();
    if(checkNetworkConnection())
    {
        $('.appypie-loader').show();
        var productPrice=parseFloat(document.getElementById('productPrice').innerHTML);
        console.log('cart_add price--->'+productPrice);
        if(parseFloat(productPrice) >= 0)
        {
            
            var configTitle='';
            var sendData='';
            if(option_id.length>0)
            {
                console.log('option_id.length---->'+option_id.length);
                for(var x=0;x<option_id.length;x++)
                {
                    var selectedIndexnew=unescape(document.getElementById(''+option_id[x]).value);
                    var valueOfselectedIndex=selectedIndexnew.split(',');
                    console.log("selectedIndexnew--->"+selectedIndexnew+"<---option_req[x]--->"+option_req[x]);
                    
                    if(selectedIndexnew == 'select' && parseFloat(option_req[x]) == 1)
                    {
                        setTimeout(function(){ $('.appypie-loader').hide();},1000);
                        navigator.notification.alert(pleaseselectt_food+option_title[x], setTimeout, errorrr_food, okkk_food);
                        return false;
                    }
                    else
                    {
                        if(x!=0 && configTitle.length >2)
                        {
                            if(selectedIndexnew != 'select')
                            {
                                configTitle=configTitle+','+option_title[x];
                                sendData=sendData + ',' + valueOfselectedIndex[0];
                            }
                        }
                        else
                        {
                            if(selectedIndexnew == 'select' && parseFloat(option_req[x]) == 0)
                            {
                                configTitle='';
                                sendData='';
                            }
                            else
                            {
                                configTitle=option_title[x];
                                sendData=valueOfselectedIndex[0];
                            }
                            
                        }
                    }
                }
            }
            else
            {
                configTitle='';
                sendData='';
            }
            console.log("configTitle ---->"+configTitle+"<----sendData--->"+sendData);
            
            var quantity= document.getElementById("quantity").value;
            var productId=sessionStorage.getItem("productid");
            var productName=sessionStorage.getItem("productName");
            // var productPrice=parseFloat(document.getElementById('productPrice').innerHTML);
            var foodcurrency=sessionStorage.getItem("currency");
            var productImage=sessionStorage.getItem("productImage");
            var productdescription=sessionStorage.getItem("productdescription");
            localStorage.setItem("fromDetailPage","true");
            var foodType=sessionStorage.getItem("foodType");
            console.log('1');
            
            var cart;
            var flag=0;
            console.log('cart value ---->'+sessionStorage.getItem("cart"));
            
            if(sessionStorage.getItem("cart"))
            {
                cart=window.sessionStorage.getItem("cart");
                cart=parseInt(cart)+1;
                console.log('2 if');
                for (var x=1; x<cart; x++)
                {
                    console.log("product id 1-->"+productId+'<--2nd-->'+sessionStorage.getItem("productId"+x));
                    console.log('status of product---->'+sessionStorage.getItem("status"+x));
                    console.log('value of drop down---->'+sessionStorage.getItem("foodconfigValueProduct"+x)+'<---2nd---->'+sendData);
                    
                    if(window.sessionStorage.getItem("productId"+x)==productId && sessionStorage.getItem("status"+x)=="1" && sessionStorage.getItem("foodconfigValueProduct"+x)==sendData)
                    {
                     
                        if((parseInt(quantity)+parseInt(sessionStorage.getItem("quantity"+x,qty))) > sessionStorage.getItem("quantity"))
                        {
                            var qty=sessionStorage.getItem("quantity");
                            console.log("Cart Quantity" + qty);
                            qty=parseInt(qty);
                         
                            sessionStorage.setItem("quantity"+x,qty);
                          
                        }
                        else
                        {
                            var qty=sessionStorage.getItem("quantity"+x);
                         
                            qty=parseInt(qty)+parseInt(quantity);
                       
                            sessionStorage.setItem("quantity"+x,qty);
                       
                        }

                        flag=1;
                        
                    }
                    else
                    {
                        flag=0;
                    }
                }
                sessionStorage.setItem("cart",cart);
                
            }
            else
            {
                window.sessionStorage.setItem("cart",1);
                cart=1;
                console.log('2 else');
            }
             if(parseInt(localStorage.getItem("backorder"))>0)
            {if(flag==1)
            {
                
                    if(cart>=2)
                    {
                        cart=1;
                    }
              
                window.sessionStorage.setItem("productId"+cart,productId);
                console.log('3');
                //alert("cart,quantity"+cart,quantity);
                
                window.sessionStorage.setItem("quantity"+cart,quantity);
                window.sessionStorage.setItem("productName"+cart,productName);
                window.sessionStorage.setItem("productdescription"+cart,productdescription);
                sessionStorage.setItem("price"+cart,productPrice.toFixed(2));
                sessionStorage.setItem("currency"+cart,foodcurrency);
                window.sessionStorage.setItem("productImage"+cart,productImage);
                console.log('4');
                window.sessionStorage.setItem("status"+cart,"1");
                sessionStorage.setItem("foodconfigTitleProduct"+cart,configTitle);
                sessionStorage.setItem("foodconfigValueProduct"+cart,sendData);
                
                window.sessionStorage.setItem("foodType"+cart,foodType);
                window.sessionStorage.setItem("foodflag","1");
                console.log('5');
                sessionStorage.setItem("foodAvailquantity"+cart,sessionStorage.getItem("quantity"));
            }
        }
            if(flag==0)
            {
				if(parseInt(localStorage.getItem("backorder"))>0)
                {
                    if(cart>=2)
                    {
                        cart=1;
                    }
                }
                window.sessionStorage.setItem("productId"+cart,productId);
                console.log('3');
                window.sessionStorage.setItem("quantity"+cart,quantity);
                window.sessionStorage.setItem("productName"+cart,productName);
                window.sessionStorage.setItem("productdescription"+cart,productdescription);
                sessionStorage.setItem("price"+cart,productPrice.toFixed(2));
                sessionStorage.setItem("currency"+cart,foodcurrency);
                window.sessionStorage.setItem("productImage"+cart,productImage);
                console.log('4');
                window.sessionStorage.setItem("status"+cart,"1");
                sessionStorage.setItem("foodconfigTitleProduct"+cart,configTitle);
                sessionStorage.setItem("foodconfigValueProduct"+cart,sendData);
                
                window.sessionStorage.setItem("foodType"+cart,foodType);
                window.sessionStorage.setItem("foodflag","1");
                console.log('5');
				sessionStorage.setItem("foodAvailquantity"+cart,sessionStorage.getItem("quantity"));
            }
			
            else
            {
                cart=cart-1;
                window.sessionStorage.setItem("cart",cart);
                console.log('6');
            }
            if(checkNetworkConnection())
            {
                window.sessionStorage.setItem("DeleteCalled","false");
                // setTimeout(function(){$('.appypie-loader').hide();},1000);
                setTimeout(function(){
                           bindCart();
                           },100);
                
            }
            
            //  addToCart(productid,quantity,productName,productPrice,productImage,productdescription,foodType,foodcurrency);
        }
        else
        {
            setTimeout(function(){$('.appypie-loader').hide();},1000);
        }
    }
    
}
var grandTotal=0;
function tipChange(value) {
    console.log("value in tipchange function --->>"+value);
    sessionStorage.setItem("tipAmount",value);
    document.getElementById("tipAmount").value=value+'%';
    var percentAmount= parseFloat(grandTotal)*(value/100);
    window.sessionStorage.setItem("tipCharges",percentAmount);
    var gtotral=parseFloat(grandTotal)-parseFloat(sessionStorage.getItem("fooddiscount"))+parseFloat(sessionStorage.getItem("foodtax"))+parseFloat(sessionStorage.getItem("fooddelivery"))-parseFloat(sessionStorage.getItem("couponAmount"))+parseFloat(percentAmount.toFixed(2));
    var newTaxTotalAmount=calculateMiscTax(grandTotal);
    gtotral=parseFloat(gtotral)+parseFloat(newTaxTotalAmount);
    
    
    console.log("calculation of gross total--->>>newsubtotal--->"+grandTotal+" fooddiscount--->"+sessionStorage.getItem("fooddiscount")+"foodtax--->>>"+sessionStorage.getItem("foodtax")+"fooddelivery--->"+sessionStorage.getItem("fooddelivery")+"couponDiscount--->>"+parseFloat(sessionStorage.getItem("couponAmount"))+"gtotral--->"+grandTotal+"total amount--->>>"+gtotral);
    
    
    
    sessionStorage.setItem("gtotal",parseFloat(gtotral).toFixed(2));
    $("#gtotal").empty();
    $("#gtotal").append(sessionStorage.getItem("currency")+parseFloat(gtotral).toFixed(2));
    sessionStorage.setItem("gtotal1",parseFloat(gtotral).toFixed(2));
    document.getElementById("tipslider").value= value;
}
function tipSliderMinus(){
    var quantity=parseInt(document.getElementById("tipAmount").value);
    if(quantity>0 && quantity<=20) {
        var parsedQuantity= parseInt(quantity)-1;
        document.getElementById("tipAmount").value= parsedQuantity+'%';
        document.getElementById("tipslider").value= parsedQuantity;
        tipChange(parsedQuantity);
    }
}
function tipSliderPlus(){
    var quantity=parseInt(document.getElementById("tipAmount").value);
    if(quantity>=0 && quantity<20) {
        var parsedQuantity= parseInt(quantity)+1;
        document.getElementById("tipAmount").value= parsedQuantity+'%';
        document.getElementById("tipslider").value= parsedQuantity;
        tipChange(parsedQuantity);
    }
}
function continueCheckout(){
    console.log('checkout flag---->'+sessionStorage.getItem("checkoutFlag"));
	
	 if(localStorage.getItem("layout")=="bottom") {
            $(".app_navigation_bottom").show();
   $(".app_navigation_bottom_carousel").show();
            localStorage.setItem("bottomHide","false");
        }
	
    if(parseInt(sessionStorage.getItem("checkoutFlag")) > 0){
        if(document.getElementById("editoncart").style.display=="none"){
            
        }
        else {
            if(document.getElementById("editoncart").innerHTML==doneee_food){
                
            }
            else {
                
                //new code
                //                    if(sessionStorage.getItem('storeOpenTimmings'))
                //                    {
                //                        var d = new Date();
                //                        var n = d.getHours();
                //                        var x= d.getMinutes();
                //                        if(parseFloat(x) < 10)
                //                        {
                //                            x='0'+x;
                //                        }
                //                        var timmings=n+''+x;
                //                        if(parseFloat(timmings) > parseFloat(sessionStorage.getItem('storeOpenTimmings')) && parseFloat(timmings) < parseFloat(sessionStorage.getItem('storeClosingTimmings')))
                //                        {
                //                navigator.notification.alert('Sorry we are closed now,\n Please try again later', setTimeout, "Error", "OK");
                //                            return false;
                //                        }
                //                    }
                
                if(sessionStorage.getItem("min_order_apply")=='no')
                {
                    checkoutView('1');
                }
                else
                {
                    if(parseFloat(sessionStorage.getItem("minOrderValue")) <= parseFloat(sessionStorage.getItem("gtotal1")))
                    {
                        
                        checkoutView('1');
                    }
                    else
                    {
                        if(!sessionStorage.getItem("minOrderValue"))
                        {
                            checkoutView('1');
                        }
                        else
                        {
                        	
                            navigator.notification.alert(miordervalueis_food+sessionStorage.getItem("minOrderValue"), setTimeout, ssorry_food,okkk_food);
                        }
                    }
                }

            }
        }
    }
    else
    {
        
    }
}
function bindCart(x){
	
	
	//alertPopUp('Alert','There is no item in your cart.');
    $('.appypie-loader').show();
    if(x == 'mainpagefood')
    {
        sessionStorage.setItem('backCheck','mainpagefood');
        $("#mainbackfoodecom").show();
    }
    
    else{
        sessionStorage.setItem('backCheck','false');
        $("#mainbackfoodecom").show();
    }
        if(localStorage.getItem("layout")=="slidemenu")
        {
           snapper.close();
        }
    
  
    
    var counter=0;
    var cart_HTML = '<div class="product-top">\
    <h3>'+shoppingcart_food+'</h3>\
    <div class="checkOut-Btn" id="editoncart" '+secondaryColor+' onclick="showeditOnCart()">'+edit_food +'</div>\
    </div>\
    <div class="food-ordering-product">\
    <div class="cart-MSG" id="cart-MSG1" style="display:none">\
    <span class="er_close">x</span>\
    <div class="cart_success">'+productsuccessfullydadednourart_food+'</div>\
    </div>\
    <div class="cart-MSG" id="cart-MSG2" style="display:none">\
    <span class="er_close">x</span>\
    <div class="cart_error">'+quantityshouldgreaterzero_food+'</div>\
    </div>\
    <div  id="user_tab1">\
    \
    \
    </div>\
    \
    \
    <div class="user_tab" style="display:none">\
    <h4>'+discountcodes_food+'</h4>\
    '+enter_your_coupon_code_if_you_have_one_food+'\
        \
        <div class="form-page">\
        <input data-role="none" type="text" />\
        </div>\
        <div class="cart-update-btn">\
        <a class="food-mobile-btn f-right">'+clearcart_food+'</a>\
        </div>\
        \
        </div>\
        \
        \
        <div class="user_tab" id="user_tab3" style="display:none">\
        <h4>'+payment_details_food+'</h4>\
        <ul class="pay-mobile-cart">\
        <li >\
        '+subtotal_food+'<span id="subtotal"></span>\
        </li>\
        <li id="DeliveryDiv">\
        '+deliverycharge_food+'<span id="delivery"></span>\
        </li>\
        \
        <li id="taxDiv">\
        '+tax_food +'<span  id="tax"></span>\
        </li>\
        \
        <li class="tipClass"><span class="tiptext">'+tip_food+'</span><span><img width="20px" class="sliderminus1" onclick="tipSliderMinus()" src="images/e-com/sliderminus.png"/></span><input data-role="none" type=range min=0 max=20 value=10 class="tipslider" id="tipslider" step=1 onchange="tipChange(value)"/><span><img width="20px" class="sliderplus1" onclick="tipSliderPlus()" src="images/e-com/sliderplus.png"/></span><span><input data-role="none" type="text" style="border:none;width:50px;" value="0" id="tipAmount" class="qty" readonly/></span></li>\
        <li id="discountDiv">\
        '+discount_food+'<span id="discount"></span>\
        </li>\
        <li id="couponDID">'+coupon_food+'<span id="coupon"></span></li>\
        <li>\
        '+grand_total_food+'<span class="gtotal" id="gtotal"></span>\
        </li>\
        </ul>\
        <a class="couponsubmit_btn" id="coupons" onclick="showCouponPage();" >'+apply_coupon_code_food+'</a>\
        <div class="food-cart-update-btn">\
        </div>\
        \
        </div></div><ul class="sub-menu" style="display:none;" >\
        <li id="homeIcon" class="homeIconClass"><a onclick="home()" class="home-nav">'+home_food+'</a></li>\
        <li style="display:'+loginDispl+';"><a onclick="loginEcom()" class="login-nav">'+login_food+'</a></li>\
        <li style="display:'+accountDispl+';"><a onclick="myaccount()" class="account-nav">'+my_account_food+'</a></li>\
        <li><a onclick="goHome()" class="menu-nav">'+my_shop_food+'</a></li>\
		<li><a onclick="bindCart(\'mainpagefood\')" class="cart-nav">'+cartt_food+'</a></li>\
        <li><a onclick="termsEcom()" class="terms-nav">'+termscconditions_food+'</a></li>\
        <li><a onclick="PolicyEcom()" class="policy-nav">'+privacypolicy_food+'</a></li>\
        </ul>';
        appendHtml(cart_HTML,4,4,'food');
    
    if(localStorage.getItem("layout")=="bottom") {
        $(".app_navigation_bottom").hide();
        $(".app_navigation_bottom_carousel").hide();
        localStorage.setItem("bottomHide","ture");
        sessionStorage.setItem("mcomCheckOut","true");
    }
    else
    {
    $("#contentHolder4 .food-ordering-product").css('padding-bottom','61px');  }
    $("#contentHolder4").append("<div class='cart-footer ui-footer ui-bar-a ui-footer-fixed slideup'><span class='addmoreCart' "+secondaryColor+" onclick='goToHomePageFood();'>"+continueoordering_food+"</span> <span class='checkoutCart' "+primaryColor+" onclick='continueCheckout();'>"+checkout_food+"</span></div>");
    
    if(window.sessionStorage.getItem("clearCart")=='true') {
        window.sessionStorage.setItem("clearCart","false");
    }
    else {
        if(window.sessionStorage.getItem("flag")=="1")
        {
            setTimeout( function(){
                       $("#cart-MSG1").slideToggle();
                       }, 3000 );
            window.sessionStorage.setItem("flag","0");
        }
    }
    bindCartView();
    
}


function bindCartView()
{
	
	
    console.log("bindCartView() start");
    $('.appypie-loader').show();
    var cart;
    html='';
    cartItemsArray.length = 0;
    var subtotal=0;
    var discount=0;
    var foodcurrency='';
    var counter=0;
	 var qty=0;
    if(window.sessionStorage.getItem("cart"))
    {
        cart=sessionStorage.getItem("cart");
        html+='<div class="user_tab" >';
        
        var newsubtotal=0;
        var couponAmount=0;
		 if(parseInt(localStorage.getItem("backorder"))>0)
        {
            if(cart>=0)
            {
                cart=1;
            }
         }
        
        for (var x=1; x<=parseInt(cart); x++)
        {
            console.log("bindCartView() cart--->"+cart);
            qty=window.sessionStorage.getItem("quantity"+x);
			 if(parseInt(localStorage.getItem("backorder"))>0)
            {
                 qty=window.sessionStorage.getItem("quantity"+x);
               
            }

            else
            {
            
            if(parseInt(qty) > parseInt(sessionStorage.getItem("foodAvailquantity"+x))) {
              var qty=parseInt(sessionStorage.getItem("foodAvailquantity"+x));
                sessionStorage.setItem("quantity"+x,qty);
            }
			}
            var productId=window.sessionStorage.getItem("productId"+x);
            console.log("bindCartView() productId--->"+productId);
            var productName=window.sessionStorage.getItem("productName"+x);
            console.log("bindCartView() productName--->"+productName);
            if(productName.length>30){
                productName=productName.substring(0,30);
            }
            var	price=window.sessionStorage.getItem("price"+x);
            var productImage=window.sessionStorage.getItem("productImage"+x);
            var status=window.sessionStorage.getItem("status"+x);
            var productDesc = window.sessionStorage.getItem("productdescription"+x);
            var foodType=window.sessionStorage.getItem("foodType"+x);
            foodcurrency=window.sessionStorage.getItem("currency"+x);
            console.log('status===>'+status);
            if(status=="1")
            {
                cartItemsArray.push(x);
                if(productName.length>30){
                    productDesc=productDesc.substring(0,30);
                }
                console.log("status if condition--->subtotal--->"+subtotal);
                subtotal=parseFloat(subtotal)+(parseFloat(price)*parseInt(qty));
                console.log("status if condition--->newsubtotal--->"+newsubtotal);
                newsubtotal=parseFloat(newsubtotal)+(parseFloat(price)*parseInt(qty));
                
                html+='<ul class="food_pro_list">';
                html+='<li><img style="display:none;" class="deleteButton" id="deleteButton'+x+'" width="25px" onclick="deleteFromCart(\''+x+'\')" src="images/e-com/sliderminus.png"/><img class="productImageClass" src="'+productImage+'"  border="0" /><h2 class="proname">'+productName+'</h2><ol class="prodit"><li><span class="porqu">'+qquantity_food+'</span><span class="porqu">'+pprice_food+'</span><span class="tot">'+ttotal_food+'</span></li><li><span class="qut"><input data-role="none" type="number" style="border:none;"  onchange="onQuantityChangeFood(\''+x+'\')" value="'+parseInt(qty)+'" id="qty'+x+'" class="qty" readonly/></span><span class="qut">'+foodcurrency+' '+parseFloat(price).toFixed(2)+'</span><span class="tot teObxrt">'+foodcurrency+' '+(parseFloat(price)*parseInt(qty)).toFixed(2)+'</span></li></ol><div id="sliderdiv'+x+'" class="sliderDiv" style="display:none;"><span><img width="30px" class="sliderminus" onclick="sliderMinusButton(\''+x+'\')" src="images/e-com/sliderminus.png"/></span><input data-role="none" class="rangeSlider" type=range min=0 max=100 value="'+parseInt(qty)+'" id="slider'+productId+'" step=1 onchange="outputUpdateFood(value,\''+x+'\')"/><span><img width="30px" class="sliderplus" onclick="sliderPlusButtonFood(\''+x+'\')" src="images/e-com/sliderplus.png"/></span></div></li>';
                html+='</ul>';
                counter++;
                
                
                console.log("html of bindCartView()---->"+html);
                console.log("end of status if condition");
            }
        }
        console.log("newsubtotal--->"+grandTotal);
        console.log("newsubtotal--->"+newsubtotal);
        console.log("counter-->"+counter);
        grandTotal=newsubtotal;
        var couponNewHTML='';
        if(sessionStorage.getItem("discountType") && parseFloat(counter)>0)
        {
            if(window.sessionStorage.getItem("discountType")=='percentage')
            {
                couponAmount=(subtotal*parseFloat(window.sessionStorage.getItem("couponDiscount")/100));
            }
            else
            {
                couponAmount=parseFloat(window.sessionStorage.getItem("couponDiscount"));
            }
            sessionStorage.setItem("couponAmount",couponAmount.toFixed(2));
            sessionStorage.setItem("couponAmountFood",couponAmount.toFixed(2));
            subtotal=newsubtotal-couponAmount;
            
            //            $("#couponDID").show();
            //            $("#coupon").empty();
            couponNewHTML='<li id="couponDID">'+coupon_food+'<span id="ecomcoupon">'+sessionStorage.getItem("currency")+couponAmount.toFixed(2)+'</span></li>';
            
            //$("#coupon").append('-'+foodcurrency+couponAmount);
        }
        else if(parseFloat(counter)>0)
        {
            couponNewHTML='';
            sessionStorage.setItem("discountType",0);
            sessionStorage.setItem("couponDiscount",0);
            sessionStorage.setItem("couponAmount",0);
        }
        
        if(parseFloat(counter)>0)
        {
            document.getElementById("user_tab3").style.display="block";
            $("#user_tab1").empty();
            $("#user_tab1").append(html);
            
            getAllData(newsubtotal);
            
            sessionStorage.setItem("subtotal",parseFloat(newsubtotal).toFixed(2));
            setTimeout(function(){
                       
                       var gtotral=parseFloat(newsubtotal)-parseFloat(sessionStorage.getItem("fooddiscount"))+parseFloat(sessionStorage.getItem("foodtax"));
                       getDeliveryCharges(gtotral);
                       var newTaxTotalAmount=calculateMiscTax(subtotal);
                       gtotral=parseFloat(gtotral)+parseFloat(sessionStorage.getItem("fooddelivery"))+parseFloat(newTaxTotalAmount);
                       
                       var newSubHtml='<li >Subtotal<span id="ecomsubtotal">'+sessionStorage.getItem("currency")+(parseFloat(newsubtotal)).toFixed(2)+'</span></li><li id="taxDiv">'+ttax_food+'<span id="ecomtax">'+sessionStorage.getItem("currency")+sessionStorage.getItem("foodtax")+'</span></li>'+calculateMiscTax(newsubtotal,'food')+'<li id="discountDiv">'+ddiscount_food+'<span id="ecomdiscount">'+sessionStorage.getItem("currency")+sessionStorage.getItem("fooddiscount")+'</span></li><li id="DeliveryDiv">'+ddeliverycharge_food+'<span id="ecomdelivery">'+sessionStorage.getItem("currency")+sessionStorage.getItem("fooddelivery")+'</span></li>'+couponNewHTML+'<li class="tipClass"><span class="tiptext">'+tip_food+'</span><span><img width="20px" class="sliderminus1" onclick="tipSliderMinus()" src="images/e-com/sliderminus.png"/></span><input data-role="none" type=range min=0 max=20 value=10 class="tipslider" id="tipslider" step=1 onchange="tipChange(value)"/><span><img width="20px" class="sliderplus1" onclick="tipSliderPlus()" src="images/e-com/sliderplus.png"/></span><span><input data-role="none" type="text" style="border:none;width:50px;" value="0" id="tipAmount" class="qty" readonly/></span></li><li>'+ggrandtotal_food+'<span id="gtotal">'+sessionStorage.getItem("currency")+gtotral.toFixed(2)+'</span></li>';
                       $(".pay-mobile-cart").empty();
                       $(".pay-mobile-cart").append(newSubHtml);
                       
                       
                       sessionStorage.setItem("gtotalAmount",foodcurrency+' '+parseFloat(gtotral).toFixed(2));
                       
                       sessionStorage.setItem("deliveryAmount",foodcurrency+' '+sessionStorage.getItem("fooddelivery"));
                       window.sessionStorage.setItem("discountAmount",foodcurrency+' '+sessionStorage.getItem("fooddiscount"));
                       window.sessionStorage.setItem("subtotalAmount",foodcurrency+' '+(subtotal+couponAmount));
                       console.log("food tax bind cart--->"+sessionStorage.getItem("foodtax"));
                       sessionStorage.setItem("TaxAmount",foodcurrency+' '+sessionStorage.getItem("foodtax"));
                       sessionStorage.setItem("gtotal",parseFloat(gtotral).toFixed(2));
                       sessionStorage.setItem("checkoutFlag",1);
                       if(sessionStorage.getItem("tipAmount"))
                       {
                       var valueTip=parseFloat(sessionStorage.getItem("tipAmount"));
                       tipChange(parseFloat(valueTip).toFixed(2));
                       }
                       else
                       {
                       var valueTip=parseFloat(document.getElementById("tipAmount").value);
                       tipChange(parseFloat(valueTip).toFixed(2));
                       }
                       },300);
        }
        else
        {
            html='<center><b>'+therenoitemcart_food+'</b><br/></center>';
            html+='<div class="cart-update-btn" id="cart-update-btn2" style="text-align:center"> <a class="food-mobile-btn" onclick=" goToHomePageFood();">'+continueoordering_food+'</a> </div></div>';
            $("#user_tab1").empty();
            $("#user_tab3").hide();
            $("#user_tab1").append(html);
            document.getElementById("editoncart").style.display="none";
            sessionStorage.setItem("checkoutFlag",0);
            window.sessionStorage.getItem("discountType",'');
            window.sessionStorage.getItem("couponDiscount",'');
            setTimeout(function(){$('.appypie-loader').hide();},1000);
        }
        
        
        
    }
    else
    {
        html='<center><b>'+therenoitemcart_food+'</b><br/></center>';
        html+='<div class="cart-update-btn" id="cart-update-btn2" style="text-align:center"> <a class="e-mobile-btn" onclick="goToHomePageFood();">'+continueoordering_food+'</a> </div></div>';
        $("#user_tab1").empty();
        $("#user_tab").append(html);
        document.getElementById("editoncart").style.display="none";
        sessionStorage.setItem("checkoutFlag",0);
    }
    if(window.sessionStorage.getItem("DeleteCalled")=="true") {
    	
        document.getElementById("editoncart").innerHTML=doneee_food;
        showeditOnCart();
    }
    showPaymentDetailsFood();
    setTimeout(function(){$('.appypie-loader').hide();},1000);
}

function getAllData(grossTotal)
{
    sessionStorage.setItem("fooddiscount",0);
    sessionStorage.setItem("foodtax",0);
    var wsUrl1 = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodCombinedJson";
    var soapRequest1 ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodCombinedJson xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodCombinedJson\"><appId>'+appid+'</appId><gTotal>'+grossTotal+'</gTotal></foodCombinedJson></soap:Body></soap:Envelope>';
    console.log("soapRequest1--->"+soapRequest1);
    $.ajax({
           type: "POST",
           url: wsUrl1,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           async: false,
           data: soapRequest1,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           console.log("getAllData() strJSON--->"+strJSON);
           var obj=jQuery.parseJSON(strJSON);
           var amt=0;
           if(obj.discount != null && obj.discount != "none")
           {
           console.log("discountPrice--->"+obj.discount.discountPrice);
           console.log("discountRate--->"+obj.discount.discountRate);
           var discountRate=obj.discount.discountRate;
           var discountPrice=obj.discount.discountPrice;
           if(discountRate=='Percentage')
           {
           amt=(grossTotal*discountPrice)/100;
           }
           else
           {
           amt=discountPrice;
           }
           sessionStorage.setItem("fooddiscount",parseFloat(amt).toFixed(2));
           }
           if(obj.tax != null && obj.tax != "none")
           {
           console.log("getAllData() tax--->"+obj.tax);
           amt=0;
           var taxPrice= obj.tax.taxPrice;
           var taxRate= obj.tax.taxRate;
           if(taxRate=='Percentage')
           {
           amt=(grossTotal*taxPrice)/100;
           }
           else
           {
           amt=taxPrice;
           }
           
           sessionStorage.setItem("foodtax",parseFloat(amt).toFixed(2));
           }
           if(obj.misctax != null && obj.misctax != "none")
           {
           console.log("getAllData() misctax--->"+obj.misctax);
           
           var miscLen=obj.misctax
           for(i=0;i<miscLen.length;i++)
           {
           if(parseFloat(miscLen[i].status) > 0)
           {
           sessionStorage.setItem("taxType"+i,miscLen[i].taxType);
           sessionStorage.setItem("taxAmount"+i,miscLen[i].taxAmount);
           sessionStorage.setItem("taxRate"+i,miscLen[i].taxRate);
           console.log("taxType--->"+miscLen[i].taxType);
           }
           
           }
           sessionStorage.setItem("noOfTax",parseFloat(miscLen.length));
           
           }
           
           if(obj.minorder != null && obj.minorder != "none")
           {
           console.log("getAllData() minorder amount--->"+obj.minorder.amount);
           if(parseFloat(obj.minorder.amount) > 0)
           {
           sessionStorage.setItem("minOrderValue",obj.minorder.amount);
           }
           }
           
           var openTime=0000;
		  	   if(obj.store_open_time)		
           {
           if(obj.store_open_time != null && obj.store_open_time != "none")
           {
		    openTime = convert_to_24h(obj.store_open_time);
           //openTime=obj.store_open_time.replace(':','');
           }
           
           var closeTime=2400;
           if(obj.store_close_time != null && obj.store_close_time != "none")
           {
           closeTime=obj.store_close_time.replace(':','');
           
           }
           if(openTime.trim()=="")
           {
           openTime=0000;
           }
           if(closeTime.trim()=="")
           {
           closeTime=2400;
           }
           var min_order_apply=obj.min_order_apply;
           sessionStorage.setItem("min_order_apply",min_order_apply);
           var preferred_deliverytime_required=obj.preferred_deliverytime_required;
            sessionStorage.setItem("preferred_deliverytime_required",preferred_deliverytime_required);
           
           sessionStorage.setItem("workingHour",closeTime);
           closeTime=parseFloat(openTime)+parseFloat(closeTime);
           sessionStorage.setItem("storeClosingTimmings",closeTime);
           sessionStorage.setItem("storeOpenTimmings",openTime);
           }
		   },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
}

function convert_to_24h(time_str) {
    // Convert a string like 10:05:23 PM to 24h format, returns like [22,5,23]
    var time = time_str.match(/(\d+):(\d+) (\w)/);
    
    //alert(time);
    var hours = Number(time[1]);
    var minutes = Number(time[2]);
   // var seconds = Number(time[3]);
   // var meridian = time[4].toLowerCase();
    
   // alert(hours);
   // alert(time[1]);
   // alert("meridian: " + time[3]);
   // alert(minutes.length +"  " +  minutes);
    var meridian = time[3];

    if (meridian == 'P' && hours < 12) {
      hours = hours + 12;
    }
    else if (meridian == 'A' && hours == 12) {
      hours = hours - 12;
    }
    if(minutes == '0')
    	{
    	  minutes = "00";
    	}
    //alert(hours + minutes);
    return hours + minutes;
  };
function getMinOrderValue(value)
{
    
    var wsUrl1 = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodShipmentMinOrder";
    var soapRequest1 ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodShipmentMinOrder xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodShipmentMinOrder\"><appId>'+appid+'</appId></foodShipmentMinOrder></soap:Body></soap:Envelope>';
    console.log('request=>'+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl1,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           async: false,
           data: soapRequest1,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           
           if(parseFloat(strJSON) > 0)
           {
           sessionStorage.setItem("minOrderValue",strJSON);
           }
           console.log('minOrderValue=>'+strJSON);
           
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    
}
function showPaymentDetailsFood()
{
    
    console.log("couponAmountFood---->showPaymentDetailsFood---->"+sessionStorage.getItem("couponAmountFood"));
    if(sessionStorage.getItem("couponAmountFood") == 0) {
        $("#couponDID").hide();
    }
    else
    {
        $("#couponDID").show();
    }
    if(window.sessionStorage.getItem("fooddelivery") == 0)
    {
        $("#DeliveryDiv").hide();
    }
    else
    {
        $("#DeliveryDiv").show();
    }
    if(window.sessionStorage.getItem("fooddiscount") == 0)
    {
        $("#discountDiv").hide();
    }
    else
    {
        $("#discountDiv").show();
    }
    if(sessionStorage.getItem("foodtax") == 0)
    {
        $("#taxDiv").hide();
    }
    else
    {
        $("#taxDiv").show();
    }
    console.log("tipAmount--->>>showPaymentDetailsFood()"+sessionStorage.getItem("tipAmount"));
    console.log("showPaymentDetailsFood---->couponDiscount---->"+sessionStorage.getItem("couponDiscount"));
    if(sessionStorage.getItem("couponDiscount") == 0)
    {
        $("#couponDID").hide();
    }
    else
    {
        $("#couponDID").show();
    }
}
function getfoodDiscount(TotalAmount)
{
    window.sessionStorage.setItem("fooddiscount",0);
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodDiscount";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodDiscount xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodDiscount\"><appId>'+appid+'</appId><gTotal>'+TotalAmount+'</gTotal></foodDiscount></soap:Body></soap:Envelope>';
    console.log('request=>'+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           async: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           console.log('getfoodDiscount==>'+strJSON);
           if(strJSON!='No Discount')
           {
           
           console.log('=x=x=x=x='+strJSON);
           $.ajax({
                  url: strJSON,
                  dataType: "text",
                  crossDomain: true,
                  async: false,
                  cache: false,
                  success: function(discountXmlData) {
                  var grossTotal= $(discountXmlData).find("grossTotal").text();
                  var discountPrice= $(discountXmlData).find("discountPrice").text();
                  var discountRate= $(discountXmlData).find("discountRate").text();
                  
                  if(discountRate=='Percentage')
                  {
                  amt=(grossTotal*discountPrice)/100;
                  }
                  else
                  {
                  amt=discountPrice;
                  }
                  sessionStorage.setItem("fooddiscount",parseFloat(amt).toFixed(2));
                  },
                  error: function(XMLHttpRequest, textStatus, errorThrown) {
                  console.log('fail');
                  console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
                  setTimeout(function(){$('.appypie-loader').hide();},1000);
                  }
                  });
           }
           console.log('fooddiscount=>'+window.sessionStorage.getItem("fooddiscount"));
           
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    
}

function getfoodtax(TotalAmount)
{
    window.sessionStorage.setItem("foodtax",0);
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodTax";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodTax xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodTax\"><appId>'+appid+'</appId><gTotal>'+TotalAmount+'</gTotal></foodTax></soap:Body></soap:Envelope>';
    console.log('request=>'+soapRequest);
    jQuery.support.cors = true;
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           async: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           console.log('getfoodTax==>'+strJSON);
           if(strJSON!='Tax Free')
           {
           $.ajax({
                  url: strJSON,
                  dataType: "text",
                  crossDomain: true,
                  async: false,
                  cache: false,
                  success: function(xmldata) {
                  var grossTotal= $(xmldata).find("grossTotal").text();
                  var taxPrice= $(xmldata).find("taxPrice").text();
                  var taxRate= $(xmldata).find("taxRate").text();
                  if(taxRate=='Percentage')
                  {
                  amt=(grossTotal*taxPrice)/100;
                  }
                  else
                  {
                  amt=taxPrice;
                  }
                  
                  window.sessionStorage.setItem("foodtax",parseFloat(amt).toFixed(2));
                  },
                  error: function(XMLHttpRequest, textStatus, errorThrown) {
                  console.log('fail');
                  console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
                  setTimeout(function(){$('.appypie-loader').hide();},1000);
                  }
                  });
           }
           console.log('fooddiscount=>'+window.sessionStorage.getItem("foodtax"));
           
           },
           error: function(response, textStatus, errorThrown)
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           console.log(response);
           }
           });
    
}
function getDeliveryCharges(TotalAmount)
{
    window.sessionStorage.setItem("fooddelivery",0);
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodDeliveryCharge";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodDeliveryCharge xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodDeliveryCharge\"><appId>'+appid+'</appId><gTotal>'+TotalAmount+'</gTotal></foodDeliveryCharge></soap:Body></soap:Envelope>';
    console.log('request=>'+soapRequest);
    jQuery.support.cors = true;
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           async: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           
           var strJSON = $(req.responseText).find("return").text();
           console.log('foodDeliveryCharge==>'+strJSON);
           if(strJSON!='Free Shipping')
           {
           $.ajax({
                  url: strJSON,
                  dataType: "text",
                  crossDomain: true,
                  async: false,
                  cache: false,
                  success: function(xmldata) {
                  var grossTotal= $(xmldata).find("grossTotal").text();
                  var deliveryPrice= $(xmldata).find("deliveryPrice").text();
                  var deliveryRate= $(xmldata).find("deliveryRate").text();
                  if(deliveryRate=='Percentage')
                  {
                  amt=(grossTotal*deliveryPrice)/100;
                  }
                  else
                  {
                  amt=deliveryPrice;
                  }
                  
                  sessionStorage.setItem("fooddelivery",parseFloat(amt).toFixed(2));
                  },
                  error: function(XMLHttpRequest, textStatus, errorThrown) {
                  console.log('fail');
                  console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
                  setTimeout(function(){$('.appypie-loader').hide();},1000);
                  }
                  });
           }
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           
           }
           });
    
}

function showCouponPage(){
	
	
    
    $('.appypie-loader').show();
    var foodcurrency=window.sessionStorage.getItem("currency");
    var subtotal= window.sessionStorage.getItem("subtotal");
    var couponDisp='none';
    var deliveryDisp='block';
    var discountDisp='block';
    var taxDisp='block';
    var tipDisp='block';
    var deliveryDisp='block';
    //  var subtotal=sessionStorage.getItem("ecomsubtotalAmount");
    var extraTaxHtml=calculateMiscTax(subtotal,'checkoutpage');
    if(window.sessionStorage.getItem("couponAmountFood")) {
        var couponDisp='block';
    }
    
    
    if(window.sessionStorage.getItem("fooddelivery") == 0)
    {
        deliveryDisp="none";
    }
    if(window.sessionStorage.getItem("fooddiscount") == 0)
    {
        discountDisp="none";
    }
    if(sessionStorage.getItem("foodtax") == 0)
    {
        taxDisp="none";
    }
    console.log("tipAmount--->>>"+sessionStorage.getItem("tipAmount"));
    if(sessionStorage.getItem("tipAmount") == 0)
    {
        tipDisp="none";
    }
    if(sessionStorage.getItem("couponDiscount") == 0)
    {
        var couponDisp='none';
    }
    var counter=0;
    
    if(localStorage.getItem("layout")=="bottom") {
        $(".app_navigation_bottom").show();
        localStorage.setItem("bottomHide","false");
    }
    
    var coupon_HTML='<div class="product-top"><h3>'+couponpppage_food+'</h3><div class="checkOut-Btn" onclick="bindCart()" id="checkoutbtn" style="display:none">'+checkout_food+'</div></div><div class="cart-MSG" id="coupon-MSG" style="display:none"><div class="cart_success">'+notavalidcccoupon_food+'<div class="er_close"  onclick="closeCoupon()">X</div></div></div><section id="container" ><div class="food-ordering-product"><div class="food-pro_details"><h4>'+eeenteryourcouponcode_food+'</h4><input data-role="none" type="text" id="couponCode" class="couponBox" /><a class="submit_btn couponButton" '+primaryColor+' onclick="applyCoupon();" >'+apply_food+'</a></div><div class="user_tab"><h4>'+ppaymentddetails_food+'</h4><ul class="pay-mobile-cart"><li >'+ssubtotal_food+'<span style="color:#000" id="subtotal">'+foodcurrency+' '+
    subtotal+'</span></li><li style="display:'+deliveryDisp+'">'+ddeliverycharge_food+' <span style="color:#000" id="delivery">'+foodcurrency+' '+window.sessionStorage.getItem("fooddelivery")+'</span></li><li style="display:'+taxDisp+'">'+ttax_food+' <span style="color:#000;" id="tax">'+foodcurrency+' '+window.sessionStorage.getItem("foodtax")+'</span></li>'+extraTaxHtml+'<li class="tipClass" ;display:'+tipDisp+' !important"><span class="tiptext">'+tip_food+'</span><input data-role="none" type="text" style="border:none;width:50px;" style="color:#000" id="tipAmount" class="qty" value="'+sessionStorage.getItem("tipAmount")+'%" readonly/></span></li><li style="display:'+discountDisp+'">'+ddiscount_food+' <span style="color:#000;" id="discount">'+foodcurrency+' '+window.sessionStorage.getItem("fooddiscount")+'</span></li><li id="couponDID" style="color:#000;display:'+couponDisp+'">'+ccoupon_food+'<span id="coupon" tyle="color:#000">'+foodcurrency+' '+window.sessionStorage.getItem("couponAmountFood")+'</span></li><li>'+ggrandtotal_food+'<span style="color:#000" class="gtotal" id="gtotal">'+foodcurrency+' '+window.sessionStorage.getItem("gtotal1")+'</span></li></ul></div></div></section><ul class="sub-menu" style="display:none;" ><li id="homeIcon" class="homeIconClass"><a onclick="home()" class="home-nav">'+home_food+'</a></li><li style="display:'+loginDispl+';"><a onclick="loginEcom()" class="login-nav">'+llloginregistration_food+'</a></li><li style="display:'+accountDispl+';"><a onclick="myaccount()" class="account-nav">'+mmyaccount_food+'</a></li><li><a onclick="goHome()" class="menu-nav">'+mysshop_food+'</a></li><li><a onclick="bindCart(\'mainpagefood\')" class="cart-nav">'+cartt_food+'</a></li><li><a onclick="termsEcom()" class="terms-nav">'+termscconditions_food+'</a></li><li><a onclick="PolicyEcom()" class="policy-nav">'+privacypolicy_food+'</a></li></ul>';
    
     appendHtml(coupon_HTML,5,5,'food');
    if(parseInt(window.sessionStorage.getItem("checkoutFlag"))>0)
    {
        document.getElementById("checkoutbtn").style.display="block";
    }
    
    
    
    
    
}
function applyCoupon()
{
    $('.appypie-loader').show();
    var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodCoupon";
    var couponCode=document.getElementById("couponCode").value;
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodCoupon xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodCoupon\"><appId>'+appid+'</appId><couponCode>'+couponCode+'</couponCode></foodCoupon></soap:Body></soap:Envelope>';
    
    console.log("Soap"+ soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           //  alert(data);
           var strJSON = $(data).find("return").text();
           
           if(strJSON == "No Discount" || strJSON == "Coupon Expire" || strJSON == "Not a valid coupon")
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           $("#coupon-MSG").css('display','block');
           setTimeout( function(){
                      $("#coupon-MSG").hide();
                      //$("#coupon-MSG").css('display','none');
                      },2500 );
           
           }
           else
           {
           $('.appypie-loader').show();
           ShowFoodCoupon(strJSON);
           }
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
}

function closeCoupon()
{$("#coupon-MSG").hide();}

function ShowFoodCoupon(xml)
{
    console.log(xml);
    $.ajax({
           url: xml,
           dataType: "text",
           cache: false,
           crossDomain: true,
           success: function(xmll) {
           
           var couponDiscount =$(xmll).find("couponDiscount").text();
           
           var discountType=$(xmll).find("discountType").text();
           console.log("discountType-->"+discountType+"couponDiscount ---->>>"+couponDiscount);
           sessionStorage.setItem("discountType",discountType);
           sessionStorage.setItem("couponDiscount",couponDiscount);
           getecomDeliveryCharges(window.sessionStorage.getItem("subtotal"));
           bindCart();
           },
           error: function(XMLHttpRequest, textStatus, errorThrown) {
           console.log('fail');
           console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
}
function outputUpdateFood(value,id) {
    document.getElementById("qty"+id).value= value;
}

function onQuantityChangeFood(productId){
    if(parseInt(document.getElementById("qty"+productId).value)>0){
        var inputValue= document.getElementById("qty"+productId).value;
        document.getElementById("slider"+productId).value= inputValue;
    }
    else {
        $("#cart-MSG2").slideToggle();
        setTimeout( function(){
                   $("#cart-MSG2").slideToggle();
                   },2500);
        document.getElementById("qty"+productId).value=1;
    }
}


function sliderMinusButton(id){
    
    var quantity=document.getElementById("qty"+id).value;
    if(parseInt(quantity)>1) {
        var parsedQuantity= parseInt(quantity)-1;
        document.getElementById("qty"+id).value= parsedQuantity;
        document.getElementById("slider"+id).value= parsedQuantity;
    }
}
function sliderPlusButtonFood(id){
    var quantity=document.getElementById("qty"+id).value;
    var parsedQuantity= parseInt(quantity)+1;
    document.getElementById("qty"+id).value= parsedQuantity;
    document.getElementById("slider"+id).value= parsedQuantity;
}
function sliderPlusButton(id)
{
    console.log("sliderPlusButton id--->>>"+id);
    
    var parsedQuantity= parseInt(document.getElementById("qty"+id).value)+1;
    console.log("slider plus cart --->"+sessionStorage.getItem("ecomAvailquantity"+id));
    if(sessionStorage.getItem("ecomAvailquantity"+id))
    {
        if( parseInt(parsedQuantity) > parseInt(sessionStorage.getItem("ecomAvailquantity"+id)) )
        {
            parsedQuantity=(parseInt(sessionStorage.getItem("ecomAvailquantity"+id)));
        }
    }
    document.getElementById("qty"+id).value= parsedQuantity;
    document.getElementById("slider"+id).value= parsedQuantity;
}
function showeditOnCart()
{
    
    for(var i=0;i <cartItemsArray.length;i++){
        $("#deleteButton"+cartItemsArray[i]).slideToggle();
        $("#sliderdiv"+cartItemsArray[i]).slideToggle();
        $("#qty"+cartItemsArray[i]).removeAttr("readonly");
        $("#qty"+cartItemsArray[i]).addClass("bordered");
    }
    if(document.getElementById("editoncart").innerHTML==doneee_food) {
        hideeditOnCart();
    }
    else {
        document.getElementById("editoncart").innerHTML=doneee_food;
    }
    
}
function hideeditOnCart()
{
    document.getElementById("editoncart").innerHTML=edit_food;
    var ulHtml=document.getElementsByClassName('food_pro_list')[0].innerHTML;
    var inputs = document.getElementsByTagName("input");
    for (var i = 0, len = inputs.length; i < len; i++) {
        if (inputs[i].id.indexOf("qty") == 0) {
            for(var p=1; p <= parseInt(window.sessionStorage.getItem("cart")); p++)
            {
                var idp=inputs[i].id.replace("qty","");
                if(idp==p)
                {
                    window.sessionStorage.setItem("quantity"+p,inputs[i].value)
                }
            }
        }
    }
    if(window.sessionStorage.getItem("DeleteCalled")=="true") {
        window.sessionStorage.setItem("DeleteCalled","false");
        
        document.getElementById("editoncart").innerHTML=doneee_food;
    }
    else{
        bindCartView();
    }
}

function deleteFromCart(id)
{
    $('.appypie-loader').show();
    if(window.sessionStorage.getItem("cart") )
    {
        cart=window.sessionStorage.getItem("cart");
        for (var x=1; x<=parseInt(cart); x++)
        {
            var productId=window.sessionStorage.getItem("productId"+x);
            if(x==id)
            {
                sessionStorage.setItem("status"+x,"0");
            }
        }
    }
    window.sessionStorage.setItem("DeleteCalled","true");
    bindCartView();
}
function getecomDeliveryCharges(TotalAmount)
{
    window.sessionStorage.setItem("ecomdelivery",0);
    wsUrl = "http://"+localStorage.getItem("reseller")+"/services/ecomm-soap#ecommShippingCharge";
    soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommShippingCharge xmlns=\"http://'+localStorage.getItem("reseller")+'/services/ecomm-soap#ecommShippingCharge\"><appId>'+appid+'</appId><gTotal>'+TotalAmount+'</gTotal></ecommShippingCharge></soap:Body></soap:Envelope>';
    console.log('request=>'+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           async: false,
           data: soapRequest,
           success: function(data, status, req)
           {
           console.log('response=>'+req.responseText);
           var strJSON = $(req.responseText).find("return").text();
           console.log('foodDeliveryCharge==>'+strJSON);
           if(strJSON!='Free Shipping')
           {
           console.log('=x=x=x=x='+strJSON);
           $.ajax({
                  url: strJSON,
                  dataType: "text",
                  crossDomain: true,
                  async: false,
                  cache: false,
                  success: function(xmldata) {
                  console.log('yesssssss');
                  var grossTotal= $(xmldata).find("grossTotal").text();
                  var shippingPrice= $(xmldata).find("shippingPrice").text();
                  var shippingRate= $(xmldata).find("shippingRate").text();
                  if(shippingRate=='Percentage')
                  {
                  amt=(grossTotal*shippingPrice)/100;
                  }
                  else
                  {
                  amt=shippingPrice;
                  }
                  window.sessionStorage.setItem("ecomdelivery",amt);
                  
                  },
                  error: function(XMLHttpRequest, textStatus, errorThrown) {
                  console.log('fail');
                  console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
                  setTimeout(function(){$('.appypie-loader').hide();},1000);
                  }
                  });
           }
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    
}
function loginEcom()
{
	
	
	
	
    if(localStorage.getItem("layout")=="slidemenu"){
        snapper.close();
    }
    if(appid.length < 2 && reseller.length < 2)
    {
        appid=localStorage.getItem("applicationID");
        reseller=localStorage.getItem("reseller");
    }
    if(!localStorage.getItem("fooduserid"))
    {
        var counter=0;
        $('.appypie-loader').show();
        var loginHtml='<section class="home_screen"><div class="page-text"><div id="scroll_contener" class="scroll_contener"><div class="appypie-login">\
        <h2>'+sign_in_food+'</h2><div class="cart-MSG" id="cart-MSG" style="display:none"><div class="cart_error2">'+invalidloginidorpassword_food+'</div>\
        </div><div class="login-feald"><label>'+email_id_food+'</label><input data-role="none" type="text" id="loginid"/></div><div class="login-feald"><label>'+password_food+'</label><input data-role="none" type="password" id="loginpass" /></div>\
        <div class="login-feald"><a class="login_btn" onclick="checkUserLogin()">'+login_food+'</a> <a onclick="foodForgotPassView()">'+fforgotyourpassword_food+'</a></div>\
        <div class="or-feald"><span>'+orr_food+'</span></div><div class="fb-div"><br clear="all" />'+do_not_have_an_account_food+'<a onclick="foodSignUpView()">'+sign_up_now_food+'</a></div>\
        </div></div></div></section><ul class="sub-menu" style="display:none;" >\
        <li id="homeIcon" class="homeIconClass"><a onclick="home()" class="home-nav">'+home_food+'</a></li>\
        <li style="display:'+loginDispl+';"><a onclick="loginEcom()" class="login-nav">'+llloginregistration_food+'</a></li>\
        <li style="display:'+accountDispl+';"><a onclick="myaccount()" class="account-nav">'+my_account_food+'</a></li>\
        <li><a onclick="goHome()" class="menu-nav">'+my_shop_food+'</a></li>\
		<li><a onclick="bindCart(\'mainpagefood\')" class="cart-nav">'+cartt_food+'</a></li>\
        <li><a onclick="termsEcom()" class="terms-nav">'+termscconditions_food+'</a></li>\
        <li><a onclick="PolicyEcom()" class="policy-nav">'+privacypolicy_food+'</a></li>\
        </ul>';
        appendHtml(loginHtml,2,2,'food');
    }
    else
    {
        myaccount();
    }
    
}
function foodSignUpView() {
	
    var counter=0;
    $('.appypie-loader').show();
    foodsignup_HTML='<section class="home_screen"><div class="page-text"><div id="scroll_contener" class="scroll_contener">\
    <div class="appypie-login"><h2>'+sign_up_now_food+'</h2><div class="cart-MSG" id="cart-MSG" style="display:none">\
    <div class="cart_error"></div></div><div class="cart-MSG" id="cart-MSG2" style="display:none">\
    <div class="cart_success2"></div></div><div class="login-feald"><label>'+name_food+'<sup class="red-astrick">*</sup></label>\
    <input data-role="none" type="text" id="fname" /></div><div class="login-feald"><label>'+email_id_food+'<sup class="red-astrick">*</sup></label>\
    <input data-role="none" type="text" id="emailId" /></div><div class="login-feald"><label>'+phone_food+'<sup class="red-astrick">*</sup></label>\
    <input data-role="none" type="text" id="pNo" /></div><div class="login-feald"><label>'+password_food+'<sup class="red-astrick">*</sup></label>\
    <input data-role="none" type="password" id="pass"/></div><div class="login-feald"><label>'+ccconfirmpassword_food+'<sup class="red-astrick">*</sup></label>\
    <input data-role="none" type="password" id="cpass"/></div><div class="login-feald"><a class="submit_btn" onclick="addUser()">'+sign_up_now_food +'</a>\
    </div><br /><div class="fb-div">'+already_have_an_account_food+'<a onclick="loginEcom()">'+sign_in_food+'</a></div></div></div>\
    </div></section><ul class="sub-menu" style="display:none;" >\
    <li id="homeIcon" class="homeIconClass"><a onclick="home()" class="home-nav">'+home_food+'</a></li>\
    <li style="display:'+loginDispl+';"><a onclick="loginEcom()" class="login-nav">'+login_food+'</a></li>\
    <li style="display:'+accountDispl+';"><a onclick="myaccount()" class="account-nav">'+my_account_food+'</a></li>\
    <li><a onclick="goHome()" class="menu-nav">'+my_shop_food+'</a></li>\
	<li><a onclick="bindCart(\'mainpagefood\')" class="cart-nav">'+cartt_food+'</a></li>\
    <li><a onclick="termsEcom()" class="terms-nav">'+termscconditions_food+'</a></li>\
    <li><a onclick="PolicyEcom()" class="policy-nav">'+privacypolicy_food+'</a></li>\
    </ul>';
    appendHtml(foodsignup_HTML,3,3,'food');
}
function foodForgotPassView() {
	
	
    var counter=0;
    $('.appypie-loader').show();
    foodforgotpass_HTML='<section class="home_screen"><div class="page-text"><div id="scroll_contener" class="scroll_contener"><div class="appypie-login forgot-pss"><h2>'+forgot_password_food+'</h2><div class="cart-MSG" id="passwordError" style="display:none"><div class="cart_error"></div></div><div class="cart-MSG" id="cart-MSG1" style="display:none"><div class="cart_success">'+wehavesentemailemailid_food+'</div></div><div class="login-feald"><label>'+email_id_food+'</label><input data-role="none" type="text" id="forpassid" /></div><br /><div class="login-feald"><a class="submit_btn" onclick="sendPassword()">'+rrresetpassword_food+'</a></div><br /><div class="fb-div">'+do_not_have_an_account_food+'<a onclick="foodSignUpView()">'+sign_up_now_food+'</a><br /><br />'+aaaalreadyhaveanaccount_food+'<a onclick="loginEcom()">'+sign_in_food+'</a></div></div></div></div></section><ul class="sub-menu" style="display:none;" ><li id="homeIcon" class="homeIconClass"><a onclick="home()" class="home-nav">'+home_food+'</a></li><li style="display:'+loginDispl+';"><a onclick="loginEcom()" class="login-nav">'+llloginregistration_food+'</a></li><li style="display:'+accountDispl+';"><a onclick="myaccount()" class="account-nav">'+mmyaccount_food+'</a></li><li><a onclick="goHome()" class="menu-nav">'+mysshop_food+'</a></li><li><a onclick="bindCart(\'mainpagefood\')" class="cart-nav">'+cartt_food+'</a></li><li><a onclick="termsEcom()" class="terms-nav">'+termscconditions_food+'</a></li><li><a onclick="PolicyEcom()" class="policy-nav">'+privacypolicy_food+'</a></li></ul>';
    appendHtml(foodforgotpass_HTML,3,3,'food');

    if(localStorage.getItem("fooduserid"))
    {
        $('#loginFoodli').hide();
    }
    else
    {
        $('#myaccountFoodli').hide();
    }
    
}
/************Signup*************/
function addUser()
{
	
	
    if(checkNetworkConnection())
    {
        $('.appypie-loader').show();
        var fname=document.getElementById("fname").value;
        var pNo=document.getElementById("pNo").value;
        var emailId=document.getElementById("emailId").value;
        var pass=document.getElementById("pass").value;
        var cpass=document.getElementById("cpass").value;
        
        
        
        if(fname=='' || fname=='First Name')
        {
            
            $(".cart_error").empty();
            $(".cart_error").append(ppleaseenterfirstnname_food);
            
            $("#cart-MSG").slideToggle();
            console.log($("body").html());
            setTimeout( function(){
                       $("#fname").focus();
                       $("#cart-MSG").slideToggle();
                       }
                       , 3000 );
            return;
        }
        else if(!checkEmail(emailId) || emailId=='')
        {
            $(".cart_error").empty();
            $(".cart_error").append(ppleaseeentervvalidemailiid_food);
            
            $("#cart-MSG").slideToggle();
            setTimeout( function(){
                       $("#emailId").focus();
                       $("#cart-MSG").slideToggle();
                       }
                       , 3000 );
            return;
        }
       /* else if(pNo=='' || pNo=='Phone Number' )
        {
            $(".cart_error").empty();
            $(".cart_error").append(ppleaseenterpphonennumber_food);
            
            $("#cart-MSG").slideToggle();
            setTimeout( function(){
                       $("#pNo").focus();
                       $("#cart-MSG").slideToggle();
                       }
                       , 3000 );
            return;
        }*/
        else if(isNaN(pNo))
        {
            $(".cart_error").empty();
            $(".cart_error").append(pppleaseetervlidphonenumber_food);
            
            $("#cart-MSG").slideToggle();
            setTimeout( function(){
                       $("#pNo").focus();
                       $("#cart-MSG").slideToggle();
                       }
                       , 3000 );
            return;
        }
        else if(pass==''  || pass=='Password')
        {
            $(".cart_error").empty();
            $(".cart_error").append(ppleaseenterpassword_food);
            
            $("#cart-MSG").slideToggle();
            setTimeout( function(){
                       $("#pass").focus();
                       $("#cart-MSG").slideToggle();
                       }
                       , 3000 );
            return;
        }
        else if(cpass!=pass)
        {
            $(".cart_error").empty();
            $(".cart_error").append(cconfirmpassworddonotmatch_food);
            
            $("#cart-MSG").slideToggle();
            setTimeout( function(){
                       $("#cpass").focus();
                       $("#cart-MSG").slideToggle();
                       }
                       , 3000 );
            return;
        }
        else
        {
            userAdd(fname,pNo,emailId,pass);
            
        }
    }
}

function checkEmail(email) {
    
    
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    
    if (!filter.test(email)) {
        return false;
    }
    return true;
}
function userAdd(fname,pNo,emailId,pass)
{
	
    if(checkNetworkConnection())
    {
        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/app-user-soap#customerRegistration";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><customerRegistration xmlns=\"http://'+localStorage.getItem("reseller")+'/services/app-user-soap#customerRegistration\"><appId>'+appid+'</appId><email>'+emailId+'</email><password>'+pass+'</password><fname>'+fname+'</fname><lname>foodorder</lname><phone>'+pNo+'</phone></customerRegistration></soap:Body></soap:Envelope>';
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               dataType: "text",
               data: soapRequest,
               success: function(data, status, req)
               {
               var strJSON = $(req.responseText).find("return").text();
               console.log('strJSON==>'+strJSON);
               var result=strJSON.split("_");
               if(parseInt(result.length)==2)
               {
              
               $("#cart-MSG2").slideToggle();
               $(".cart_success2").append(sssignuuccessful_food);
               setTimeout( function(){
                          $("#cart-MSG2").slideToggle();
                          
                          }
                          , 3000 );
               setTimeout( function(){
                          loginEcom();
                          
                          }
                          , 5000 );
              
              
               }
               else
               {
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               navigator.notification.alert(eeemaillreadyegistered_food, setTimeout, errorrr_food, okkk_food);
               $("#emailId").focus();
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               return;
               }
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               },
               error: function(response, textStatus, errorThrown)
               {
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               console.log(response);
               }
               });
    }
}
/**********Send Password*********/
function sendPassword()
{
	
	
	
    if(checkNetworkConnection())
    {
        $('.appypie-loader').show();
        var emailid=document.getElementById("forpassid").value;
        
        
        
        if(!checkEmail(emailid) || emailid=='')
        {
            $(".cart_error").empty();
            $(".cart_error").append(uuhoh_food);
            
            $("#passwordError").slideToggle();
            //console.log($("body").html());
            setTimeout( function(){
                       $("#forpassid").focus();
                       $("#passwordError").slideToggle();
                       }
                       , 3000 );
            $('.appypie-loader').hide();
            return;
            
        }
        else
        {
            var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/app-user-soap#forgetPassword";
            var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><forgetPassword xmlns=\"http://'+localStorage.getItem("reseller")+'/services/app-user-soap#forgetPassword\"><appId>'+appid+'</appId><email>'+emailid+'</email></forgetPassword></soap:Body></soap:Envelope>';
            console.log("SOAP "+ soapRequest);
            jQuery.support.cors = true;
            $.ajax({
                   type: "POST",
                   url: wsUrl,
                   contentType: "text/xml",
                   dataType: "text",
                   data: soapRequest,
                   success: function(data, status, req)
                   {
                   var strJSON = $(req.responseText).find("return").text();
                   console.log(strJSON);
                   if(strJSON!='NotExist')
                   {
                   $(".cart_error").empty();
                   $(".cart_error").append(ppasswordesewasuccessfullleasecheckemailfornewpassword_food);
                   $("#cart-MSG1").slideToggle();
                   setTimeout( function(){
                              $("#cart-MSG1").slideToggle();
                              }
                              , 3000 );
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                   return;
                   }
                   else
                   {
                   $(".cart_error").empty();
                   $(".cart_error").append(yyouhavenotbeenregisteredwithusyet_food);
                   
                   $("#passwordError").slideToggle();
                   setTimeout( function(){
                              $("#forpassid").focus();
                              $("#passwordError").slideToggle();
                              }
                              , 3000 );
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                   return;
                   }
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                   },
                   error: function(response, textStatus, errorThrown)
                   {
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                   console.log("error--->"+response.responseText);
                   }
                   });
        }
    }
}
/********Login Page*************/
function checkUserLogin()
{
	
	
    if(checkNetworkConnection())
    {
        $('.appypie-loader').show();
        var emailid=document.getElementById("loginid").value;
        var pass=document.getElementById("loginpass").value;
        
        if(!checkEmail(emailid) || emailid=='')
        {
            setTimeout(function(){$('.appypie-loader').hide();},1000);
            navigator.notification.alert(iiinvalidemailaddress_food, setTimeout, errorrr_food,okkk_food);
            $("#emailid").focus();
            
            return;
        }
        
        else if(pass==''  || pass=='Password')
        {
            setTimeout(function(){$('.appypie-loader').hide();},1000);
            navigator.notification.alert(ppleaseenterpassword_food, setTimeout, errorrr_food, okkk_food);
            $("#pass").focus();
            return;
        }
        else
        {
            checklogin(emailid,pass);
        }
    }
}
function checklogin(emailid,pass)
{
    if(checkNetworkConnection())
    {
        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/app-user-soap#customerLogin";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><customerLogin xmlns=\"http://'+localStorage.getItem("reseller")+'/services/app-user-soap#customerLogin\"><email>'+emailid+'</email><password>'+pass+'</password><appid>'+appid+'</appid></customerLogin></soap:Body></soap:Envelope>';
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               dataType: "text",
               data: soapRequest,
               success: function(data, status, req)
               {
			   console.log("krishna"+req.responseText);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               var strJSON = $(req.responseText).find("return").text();
               console.log(strJSON);
               if(strJSON!='NotExist')
               {
               localStorage.setItem("fooduserid",emailid);
               accountDispl='block';
               loginDispl='none';
               window.sessionStorage.setItem("foodlogincheck","1");
               localStorage.setItem("ecomuserid",emailid);
               window.sessionStorage.setItem("ecomlogincheck","1");
               localStorage.setItem("foodEcomUserId",strJSON);
               if(sessionStorage.getItem('pickUpSignup') == 'true')
               {
               getUserDetail();
               
               }
               else
               {
                getFoodorderingData(sessionStorage.getItem("foodorderingIndex"));
               }
              
               
               }
               else
               {
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               $("#cart-MSG").slideToggle();
               
               setTimeout( function(){
                          $("#cart-MSG").slideToggle();
                          }
                          , 3000 );
               return;
               }
               },
               error: function(response, textStatus, errorThrown)
               {
               console.log(response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
    }
}
function goHome()
{
    if(localStorage.getItem("layout")=="bottom")
    {
        $(".app_navigation_bottom").show();
        localStorage.setItem("bottomHide","false");
    }
    
    sessionStorage.setItem("EcomSubMenu","true");
    
    
    if(localStorage.getItem("layout")=="slidemenu")
    {
        $("#mainmenu").empty();
        getIndexData();
    }
    else
    {
        getFoodorderingData(sessionStorage.getItem("foodorderingIndex"));
    }
    
}
function goToHomePageFood()
{
    if(localStorage.getItem("layout")=="bottom") {
        $(".app_navigation_bottom").show();
        localStorage.setItem("bottomHide","false");
    }
    
    window.sessionStorage.setItem("EcomSubMenu","true");
    
    $("#mainmenu").empty();
    
    getFoodorderingData(sessionStorage.getItem("ecommerceIndex"));
}
function termsEcom()
{
    if(localStorage.getItem("layout")=="slidemenu"){
        snapper.close();
    }
    var counter=0;
    $('.appypie-loader').show();
    var TermsHtml='\
    <h3 id="pageHead"></h3>\
    <div class="food-ordering-product">\
    </div>\
    <ul class="sub-menu" style="display:none;" >\
    <li id="homeIcon" class="homeIconClass"><a onclick="home()" class="home-nav">'+home_food+'</a></li>\
    <li style="display:'+loginDispl+';"><a onclick="loginEcom()" class="login-nav">'+login_food+'</a></li>\
    <li><a onclick="myaccount()" class="account-nav">'+my_account_food+'</a></li>\
    <li><a onclick="goHome()" class="menu-nav">'+my_shop_food+'</a></li>\
	<li><a onclick="bindCart(\'mainpagefood\')" class="cart-nav">'+cartt_food+'</a></li>\
    <li><a onclick="termsEcom()" class="terms-nav">'+termscconditions_food+'</a></li>\
    <li><a onclick="PolicyEcom()" class="policy-nav">'+privacypolicy_food+'</a></li>\
    </ul>';
    appendHtml(TermsHtml,2,2,'food');
    getCMS('terms_and_conditions',"food");
    
}
function getCMS(pagename,pagetype)
{
    console.log("pagename--->>>"+pagename);
    if(pagetype=="food") {
        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodCmsXml";
        console.log("wsUrl---->"+wsUrl);
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodCmsXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodCmsXml\"><appId>'+appid+'</appId><identifire>'+pagename+'</identifire></foodCmsXml></soap:Body></soap:Envelope>';
    }
    else {
        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/ecomm-soap#ecommCmsXml";
        console.log("wsUrl---->"+wsUrl);
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommCmsXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/ecomm-soap#ecommCmsXml\"><appId>'+appid+'</appId><identifire>'+pagename+'</identifire></ecommCmsXml></soap:Body></soap:Envelope>';
    }
    console.log("getCMS---->>"+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           
           
           var strJSON = $(req.responseText).find("return").text();
           
           console.log("getCMS---->>strJSON--->"+strJSON);
           showCMSData(strJSON);
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
}

function showCMSData(strJSON)
{
    $.ajax({
           url: strJSON,
           dataType: "text",
           crossDomain: true,
           cache: false,
           success: function(xml) {
           xml=$.parseXML(xml);
           html='';
           var counter=0;
           $(xml).find("product").each(function () {
                                       counter=counter+1;
                                       var name= $(this).find("name").text();
                                       var content= $(this).find("content").text();
                                       $(".food-ordering-product").append(content);
                                       $("#pageHead").append(name);
                                       console.log("name--->>"+name+"content--->>"+content);
                                       });
           
           
           console.log("counter--->>>"+counter);
           },
           error: function(XMLHttpRequest, textStatus, errorThrown) {
           console.log('fail');
           console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    
}

function PolicyEcom()
{
    if(localStorage.getItem("layout")=="slidemenu"){
        snapper.close();
    }
    var counter=0;
    $('.appypie-loader').show();
    var PolicyHtml='<section id="container">\
    <h3 style="color:#fff;" id="pageHead"></h3>\
    <div class="food-ordering-product" style="color:#fff; padding:10px">\
    \
    \
    </div>\
    </section><ul class="sub-menu" style="display:none;" >\
    <li id="homeIcon" class="homeIconClass"><a onclick="home()" class="home-nav">'+home_food+'</a></li>\
    <li style="display:'+loginDispl+';"><a onclick="loginEcom()" class="login-nav">'+llloginregistration_food+'</a></li>\
    <li style="display:'+accountDispl+';"><a onclick="myaccount()" class="account-nav">'+my_account_food+'</a></li>\
    <li><a onclick="goHome()" class="menu-nav">'+my_shop_food+'</a></li>\
	<li><a onclick="bindCart(\'mainpagefood\')" class="cart-nav">'+cartt_food+'</a></li>\
    <li><a onclick="termsEcom()" class="terms-nav">'+termscconditions_food+'</a></li>\
    <li><a onclick="PolicyEcom()" class="policy-nav">'+privacypolicy_food+'</a></li>\
    </ul>';
    appendHtml(PolicyHtml,2,2,'food');
    getCMS('privacy_policy',"food");
    
    if(localStorage.getItem("fooduserid"))
    {
        $('#loginFoodli').hide();
    }
    else
    {
        $('#myaccountFoodli').hide();
    }
}


function myaccount()
{
	
	
	
    if(localStorage.getItem("layout")=="slidemenu")
    {
        snapper.close();
    }
    if(checkNetworkConnection())
    {
        if(appid.length < 2)
        {
            appid=localStorage.getItem("applicationID");
        }
        if(!localStorage.getItem("fooduserid"))
        {
            loginEcom();
        }
        else
        {
            $('.appypie-loader').show();
            var counter=0;
            var MyaccountHtml='';
            MyaccountHtml='<section id="container"><nav id="user_nav"><a onclick="myaccount();" class="active">'+dddashboard_food+'</a><a onclick="myOrders();">'+My_Orders_food +'</a><a onclick="logout()">'+logout_food+'</a></nav><div class="userPage-text"><h2>'+mmydashboard_food+'</h2><div class="user_tab" style="display:none">\
            <h3>'+helloharmenrdrupta_food+'</h3>\
            '+ffromyoucounashboardbilityviewsnapshottion_food+'</div>\
            <div class="user_tab" id="user_tab1">\
            <h4>'+contact_information_food+'<a class="e-user_edit" onclick="conatct_info(\'food\');">'+edit_food+'</a></h4>\
            </div>\
            <div class="user_tab" id="user_tab2">\
            <h4>'+aaaaddressook_food+'<a class="e-user_edit" onclick="address_book(\'food\');">'+edit_food+'</a></h4>\
            </div>\
            </section><ul class="sub-menu" style="display:none;" >\
            <li id="homeIcon" class="homeIconClass"><a onclick="home()" class="home-nav">'+home_food+'</a></li>\
            <li style="display:'+loginDispl+';"><a onclick="loginEcom()" class="login-nav">'+llloginregistration_food+'</a></li>\
            <li style="display:'+accountDispl+';"><a onclick="myaccount()" class="account-nav">'+my_account_food+'</a></li>\
            <li><a onclick="goHome()" class="menu-nav">'+my_shop_food+'</a></li>\
			<li><a onclick="bindCart(\'mainpagefood\')" class="cart-nav">'+cartt_food+'</a></li>\
            <li><a onclick="termsEcom()" class="terms-nav">'+termscconditions_food+'</a></li>\
            <li><a onclick="PolicyEcom()" class="policy-nav">'+privacypolicy_food+'</a></li>\
            </ul>';
            appendHtml(MyaccountHtml,2,2,'food');
            showContactInfo('food');
        }
    }
}



function conatct_info(pageName)
{
	
    if(checkNetworkConnection())
    {
        $('.appypie-loader').show();
        localStorage.setItem("payTypeFoodOrEcom",pageName);
        var counter=0;
        var conatctinfoHtml='';
        conatctinfoHtml='<section id="container">\
        <div class="userPage-text">\
        <h2>'+contact_information_food+'</h2><div class="cart-MSG" id="cart-MSG1" style="display:none">\
        <div class="cart_success">'+iiinformationpdateuccessfully_food+'</div>\
        </div>\
        <div class="cart-MSG" id="cart-MSG2" style="display:none">\
        <div class="cart_error"></div>\
        </div>\
        \
        <div class="user_tab">\
        <h4>'+personal_information_food+'<a onclick="updatePesonalInformation();" class="e-user_save">'+ssssave_food+'</a></h4>\
        <div class="form-page">\
        \
        <strong>'+name_food+'<span class="required">*</span></strong><input data-role="none" id="fname" type="text" placeholder="'+nnnnname_food+'">\
        <strong>'+eeemaiddress_food+'<span class="required">*</span></strong><input data-role="none" id="emailid" type="text" placeholder="'+enterourmailid_food+'" readonly="readonly">\
        <strong>'+telephone_food+'<span class="required">*</span></strong><input data-role="none" id="telno" type="text" placeholder="'+ttelephonenno_food+'">\
        </div></div></div></section>';
        if(pageName == 'food')
        {
            var tab_html='<ul class="sub-menu" style="display:none;" >\
            <li id="homeIcon" class="homeIconClass"><a onclick="home()" class="home-nav">'+home_food+'</a></li>\
            <li><a onclick="myaccount()" class="account-nav">'+my_account_food+'</a></li>\
            <li><a onclick="goHome()" class="menu-nav">'+my_shop_food+'</a></li>\
			<li><a onclick="bindCart(\'mainpagefood\')" class="cart-nav">'+cartt_food+'</a></li>\
            <li><a onclick="termsEcom()" class="terms-nav">'+termscconditions_food+'</a></li>\
            <li><a onclick="PolicyEcom()" class="policy-nav">'+privacypolicy_food+'</a></li></ul>';
        }
        else
        {
            var tab_html='<ul class="sub-menu" style="display:none;" >\
            <li id="homeIcon" class="homeIconClass"><a onclick="home()" class="home-nav">'+home_food+'</a></li>\
            <li><a onclick="myaccountEcommerce()" class="account-nav">'+mmyaccount_food+'</a></li>\
            <li><a onclick="goHomeEcom()" class="menu-nav">'+mysshop_food+'</a></li>\
			<li><a onclick="ecomCart_View()" class="cart-nav">'+cartt_food+'</a></li>\
            <li><a onclick="termsEcommerce()" class="terms-nav">'+termscconditions_food+'</a></li>\
            <li><a onclick="PolicyEcommerce()" class="policy-nav">'+privacypolicy_food+'</a></li>\
            </ul>';
        }
        
        appendHtml(conatctinfoHtml+tab_html,3,3,'food');
        showContactInfoedit();
    }
    
    if(localStorage.getItem("fooduserid"))
    {
        $('#loginFoodli').hide();
    }
    else
    {
        $('#myaccountFoodli').hide();
    }
}

function address_book(pageName)
{
	
	
	
    if(checkNetworkConnection())
    {
        $('.appypie-loader').show();
        localStorage.setItem("payTypeFoodOrEcom",pageName);
        var counter=0;
        var addressbookHtml='';
        addressbookHtml='<section id="container"><div class="userPage-text"><h2>'+aaaaddressook_food+'</h2><div class="user_tab"><h4>'+billing_address_food+'<a onclick="updateDefaultBilling();" class="e-user_save">'+ssssave_food+'</a>\
        </h4>\
        <div class="cart-MSG" id="cart-MSG1" style="display:none">\
        <div class="cart_success">'+billingformationpdatedccessfully_food+'</div>\
        </div>\
        <div class="form-page">\
        <strong>\
        '+nnname_food+'<span class="required">*</span>\
        </strong>\
        <input data-role="none" id="bfname" type="text" placeholder="'+nnname_food+'">\
        <strong>\
        '+ttelephonenno_food+'<span class="required">*</span>\
        </strong>\
        <input data-role="none" id="bpNo"  type="text" placeholder="'+ttelephonenno_food+'">\
        <strong>\
        '+address_food+'<span class="required">*</span>\
        </strong>\
        <input data-role="none" id="bsAddress"  type="text" placeholder="'+sstreetssddress_food+'">\
        <strong>\
        '+city_food+'<span class="required">*</span>\
        </strong>\
        <input data-role="none" id="bCity"  type="text" placeholder="'+enteourity_food+'">\
        <strong>\
        '+state_province_food+'<span class="required">*</span>\
        </strong>\
        <input data-role="none" id="bState" type="text" placeholder="'+enterstatepprovince_food+'">\
        <strong>\
        '+zip_postal_code_food+'<span class="required">*</span>\
        </strong>\
        <input data-role="none" id="bZip"  type="text" placeholder="'+eenteipostalode_food+'">\
        <strong>\
        '+country_food+'<span class="required">*</span>\
        </strong>\
        <div id="bcountryTry"></div>\
        <strong>\
        <input data-role="none" type="checkbox" id="show_billing_address"/>'+sshippinddresiffereillinddress_food+'\
        </strong>\
        </div>\
        </div>\
        <div class="user_tab" id="billing_address">\
        <h4>\
        '+shipping_address_food+'<a onclick="updateDefaultShipping()" class="e-user_save">'+ssssave_food+'</a>\
        </h4>\
        <div class="cart-MSG" id="cart-MSG2" style="display:none">\
        \
        <div class="cart_success">'+sshippingformationdateduccessfully_food+'</div>\
        </div>\
        <div class="form-page">\
        <strong>\
        '+name_food+'<span class="required">*</span>\
        </strong>\
        <input data-role="none" type="text"  id="sfname" placeholder="'+ffirstnme_food+'">\
        <strong>\
        '+telephone_food+'<span class="required">*</span>\
        </strong>\
        <input data-role="none" type="text"  id="spNo" placeholder="'+ttelephonenno_food+'">\
        <strong>\
        '+address_food+'<span class="required">*</span>\
        </strong>\
        <input data-role="none" type="text"  id="ssAddress" placeholder="'+sstreetssddress_food+'">\
        <strong>\
        '+city_food+'<span class="required">*</span>\
        </strong>\
        <input data-role="none" type="text"  id="sCity" placeholder="'+enteourity_food+'">\
        <strong>\
        '+state_province_food+'<span class="required">*</span>\
        </strong>\
        <input data-role="none" type="text"  id="sState" placeholder="'+enterstatepprovince_food+'">\
        <strong>\
        '+zzipostalode_food+'<span class="required">*</span>\
        </strong>\
        <input data-role="none" type="text"  id="sZip" placeholder="'+eenteipostalode_food+'">\
        <strong>\
        '+country_food+'<span class="required">*</span>\
        </strong>\
        <div id="scountryTry"></div>\
        </div></div></div></section>';
        if(pageName == 'food')
        {
            var tab_html='<ul class="sub-menu" style="display:none;" >\
            <li id="homeIcon" class="homeIconClass"><a onclick="home()" class="home-nav">'+home_food+'</a></li>\
            <li><a onclick="myaccount()" class="account-nav">'+mmyaccount_food+'</a></li>\
            <li><a onclick="goHome()" class="menu-nav">'+my_shop_food+'</a></li>\
			<li><a onclick="bindCart(\'mainpagefood\')" class="cart-nav">'+cartt_food+'</a></li>\
            <li><a onclick="termsEcom()" class="terms-nav">'+termscconditions_food+'</a></li>\
            <li><a onclick="PolicyEcom()" class="policy-nav">'+privacypolicy_food+'</a></li></ul>';
        }
        else
        {
            var tab_html='<ul class="sub-menu" style="display:none;" >\
            <li id="homeIcon" class="homeIconClass"><a onclick="home()" class="home-nav">'+home_food+'</a></li>\
            <li><a onclick="myaccountEcommerce()" class="account-nav">'+mmyaccount_food+'</a></li>\
            <li><a onclick="goHomeEcom()" class="menu-nav">'+my_shop_food+'</a></li>\
			<li><a onclick="ecomCart_View()" class="cart-nav">'+cartt_food+'</a></li>\
            <li><a onclick="termsEcommerce()" class="terms-nav">'+termscconditions_food+'</a></li>\
            <li><a onclick="PolicyEcommerce()" class="policy-nav">'+privacypolicy_food+'</a></li>\
            </ul>';
        }
        
        appendHtml(addressbookHtml+tab_html,3,3,'food');
        getCountry("addressbook");
    }
    
    
}
/************Billing Shipping Edit Page*****************/


function showContactInfoedit()
{
    if(checkNetworkConnection())
    {
        wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodContactInfoXml";
        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodContactInfoXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodContactInfoXml\"><appId>'+appid+'</appId><email>'+localStorage.getItem("fooduserid")+'</email></foodContactInfoXml></soap:Body></soap:Envelope>';
        
        console.log(soapRequest);
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               cache: false,
               dataType: "text",
               data: soapRequest,
               success: function(data, status, req)
               {
               var strJSON = $(req.responseText).find("return").text();
               console.log(strJSON);
               showContactDetailsedit(strJSON);
               },
               error: function(response, textStatus, errorThrown)
               {
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               console.log(response);
               }
               });
    }
}

function showContactDetailsedit(strJSON)
{
    if(checkNetworkConnection())
    {
        $.ajax({
               url: strJSON,
               dataType: "text",
               crossDomain: true,
               cache: false,
               success: function(xml) {
               var xml =jQuery.parseXML(xml);
               var Name=$(xml).find("name").text();
               var email=$(xml).find("email").text();
               var phone=$(xml).find("phone").text();
               
               var fname=document.getElementById('fname');
               fname.value=Name;
               var emailid=document.getElementById('emailid');
               emailid.value=email;
               
               var telno=document.getElementById('telno');
               telno.value=phone;
               },
               error: function(XMLHttpRequest, textStatus, errorThrown) {
               console.log('fail');
               console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
    }
}

function getCountry(value)
{
    $.ajax({
           type: 'GET',
           url: "countrylist.xml",
           processData: true,
           data: {},
           dataType: "text",
           success: function(txtxml) {
           
           var xml=jQuery.parseXML(txtxml);
           console.log("getCountry(value)--->");
           var html1='<select id="sCountry"><option>Select</option>';
           var html2='<select id="bCountry"><option>Select</option>';
           var html='';
           console.log("html--->>"+html1);
           $(xml).find("country").each(function () {
                                       
                                       
                                       html+='<option value="'+$(this).find("shortname").text()+'">'+ $(this).find("longname").text()+'</option>';
                                       
                                       
                                       });
           $("#scountryTry").empty();
           $("#scountryTry").append(html1+html+'</select>');
           
           console.log(html1);
           $("#bcountryTry").empty();
           $("#bcountryTry").append(html2+html+'</select>');
           console.log('get country '+localStorage.getItem("fooduserid"));
           if(localStorage.getItem("fooduserid"))
           {
           showBillingShippingedit(value);
           }
           else
           {
           var bCountry=document.getElementById("bCountry");
           bCountry.value='US';
           var sCountry=document.getElementById("sCountry");
           sCountry.value='US';
		   
		   var appId=localStorage.getItem("applicationID");
           if(appId == 'ed33b19831c5' || appId == 'e0c2d735f46a' || appId=='cc3052bb8850' || appId=='ade727c1484c' || appId=='12667f32a13c' || appId=='6fce880ac2c6' || appId=='dba50f8ea3be' || appId=='a803984e3524')
           {
           bCountry.value='AU';
           sCountry.value='AU';
           }
		   }
           
           },
           error: function(XMLHttpRequest, textStatus, errorThrown) {
           console.log('fail');
           console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
}

function myOrders()
{
    if(checkNetworkConnection())
    {
        sessionStorage.setItem("AppPageName","Myorder");
        var counter=0;
        $('.appypie-loader').show();
        var MyOrderHtml='<section id="container"><nav id="user_nav"><a onclick="myaccount();">'+dddashboard_food+'</a><a onclick="myOrders();" class="active">'+My_Orders_food +'</a><a onclick="logout()">'+logout_food+'</a></nav><div class="userPage-text"><h2>'+order_history_food+'</h2></div></section><ul class="sub-menu" style="display:none;" >\
        <li id="homeIcon" class="homeIconClass"><a onclick="home(\''+localStorage.getItem("pageIndex")+'\')" class="home-nav">'+home_food+'</a></li>\
        <li style="display:'+loginDispl+';"><a onclick="loginEcom()" class="login-nav">'+login_food+'</a></li>\
        <li style="display:'+accountDispl+';"><a onclick="myaccount()" class="account-nav">'+my_account_food+'</a></li>\
        <li><a onclick="goHome()" class="menu-nav">'+my_shop_food+'</a></li>\
		<li><a onclick="bindCart(\'mainpagefood\')" class="cart-nav">'+cartt_food+'</a></li>\
        <li><a onclick="termsEcom()" class="terms-nav">'+termscconditions_food+'</a></li>\
        <li><a onclick="PolicyEcom()" class="policy-nav">'+privacypolicy_food+'</a></li>\
        </ul>';
        appendHtml(MyOrderHtml,2,2,'food');
        listOrders();
        
    }
}
function listOrders()
{
    if(checkNetworkConnection())
    {
        wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodOrderListXml";
        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><foodOrderListXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodOrderListXml\"><appId>'+appid+'</appId><emailId>'+localStorage.getItem("fooduserid")+'</emailId></foodOrderListXml></soap:Body></soap:Envelope>';
        console.log("PPPPPPPPPPPPPPPPPPP"+soapRequest);
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               cache: false,
               dataType: "text",
               data: soapRequest,
               success: function(data, status, req)
               {
               console.log("success"+req.responseText);
               
               var strJSON = $(req.responseText).find("return").text();
               console.log(strJSON);
               listofOrders(strJSON);
               },
               error: function(response, textStatus, errorThrown)
               {
               console.log(response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
    }
}

function listofOrders(strJSON)
{
	
	
    console.log("in list of orders"+strJSON);
    $.ajax({
           url: strJSON,
           dataType: "text",
           crossDomain: true,
           cache: false,
           success: function(xml) {
           html='';
           console.log("??????????????????????????????????????"+xml);
           $(xml).find("orderData").each(function () {
                                         var orderId= $(this).find("orderId").text();
                                         var orderDate= $(this).find("orderDate").text();
                                         //html+='<li><a onclick="openListPage(\''+categoryId+'\',\''+categoryName+'\')">'+categoryName+'</a></li>';
                                         var isFromPickUp=localStorage.getItem('isFromPickUp');
                                         
                                         html+='<div class="e-mobileOH_tab open_tab">';
                                         html+='<h3>'+oooder_food+' #'+orderId+'</h3>';
                                         
                                         if(isFromPickUp=='1'||isFromPickUp==1)
                                         {
                                         html+='<div class="e-mobileOH_subtab" onclick="getbilling(\''+orderId+'\',1)">';
                                         html+='<h5 class="open_tab">'+pickup_food+'</h5>';
                                         var dividb="Billing"+orderId;
                                         html+='<span class="e-mobileOD" id="'+dividb+'">';
                                         html+='</span>';
                                         html+='</div>';
                                         }
                                         else
                                         {
                                         html+='<div class="e-mobileOH_subtab" onclick="getbilling(\''+orderId+'\',1)">';
                                         html+='<h5 class="open_tab">'+billing_address_food+'</h5>';
                                         var dividb="Billing"+orderId;
                                         html+='<span class="e-mobileOD" id="'+dividb+'">';
                                         html+='</span>';
                                         html+='</div>';
                                         }
                                         
                                         
                                         if(isFromPickUp=='0'||isFromPickUp==0)
                                         {
                                         
                                         html+='<div class="e-mobileOH_subtab" onclick="getbilling(\''+orderId+'\',2)">';
                                         html+='<h5 class="open_tab">'+shipping_address_food+'</h5>';
                                         var divids="Shipping"+orderId;
                                         html+='<span class="e-mobileOD" id="'+divids+'">';
                                         
                                         html+='</span>';
                                         html+='</div>';
                                         }
                                         else
                                         {
                                         
                                         }
                                         html+='<div class="e-mobileOH_subtab">';
                                         html+='<h5 class="open_tab" onclick="getOrder(\''+orderId+'\')"> Order Details</h5>';
                                         var divid0="Order"+orderId;
                                         html+='<span class="e-mobileOD" id="'+divid0+'">';
                                         
                                         html+='</span>';
                                         html+='</div>';
                                         html+='</div>';
                                         
                                         });
           $(".userPage-text").empty();
           $(".userPage-text").append(html);
           $(".sub-menu,.e-mobileOH_subtab, .e-mobileOD,#billing_address").hide();
           
           
           $(".e-menu").click(function(){
                              $(".sub-menu").slideToggle();
                              });
           
           $(".e-mobileOH_tab h3").click(function(){
                                         $(this).parents(".e-mobileOH_tab").toggleClass("open_tab");
                                         $(this).parents(".e-mobileOH_tab").toggleClass("close_tab");
                                         $(this).parents(".e-mobileOH_tab").children("div").slideToggle();
                                         });
           
           $(".e-mobileOH_subtab h5").click(function(){
                                            $(this).toggleClass("open_tab");
                                            $(this).toggleClass("close_tab");
                                            $(this).parents(".e-mobileOH_subtab").children("span").slideToggle();
                                            });
           
           $("#show_billing_address").click(function(){
                                            $("#billing_address").slideToggle();
                                            });
           },
           error: function(XMLHttpRequest, textStatus, errorThrown) {
           console.log('fail');
           console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    
}
function logoutEcom()
{
    sessionStorage.setItem("logincheck","2");
    window.sessionStorage.setItem("ecomlogincheck","2");
    localStorage.removeItem("fooduserid");
    accountDispl='none';
    loginDispl='block';
    
    getEcommerceData(sessionStorage.getItem("ecommerceIndex"));
    setTimeout(function(){
               $("#cart-MSG3").slideToggle();
               setTimeout(function(){
                          $("#cart-MSG3").hide();
                          },2000);
               },200);
    localStorage.removeItem("ecomuserid");
    localStorage.removeItem("foodEcomUserId")
}


function logout()
{
    sessionStorage.setItem("logincheck","2");
    window.sessionStorage.setItem("ecomlogincheck","2");
    localStorage.removeItem("fooduserid");
    accountDispl='none';
    loginDispl='block';
    localStorage.removeItem("ecomuserid");
    localStorage.removeItem("foodEcomUserId")
    getFoodorderingData(sessionStorage.getItem("foodorderingIndex"));
    setTimeout(function(){
               $("#cart-MSG3").slideToggle();
               setTimeout(function(){
                          $("#cart-MSG3").hide();
                          },2000);
               },200);
			   
    toaster.setUserStatus("");
    toaster.setLoginStatus("");
    window.location="removeWebSite:";
    localStorage.setItem("userLoginStatus","false");
	
	
}

function getbilling(orderid,billship)
{
    if(checkNetworkConnection())
    {
        $('.appypie-loader').show();
        if(billship==1) {
            billship='Billing';
        }
        else {
            billship='Shipping';
        }
        wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#billingShippingXml";
        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><billingShippingXml xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#billingShippingXml\"><appId>'+appid+'</appId><orderId>'+orderid+'</orderId><userId>'+localStorage.getItem("foodEcomUserId")+'</userId><billShip>'+billship+'</billShip></billingShippingXml></soap:Body></soap:Envelope>';
        
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               cache: false,
               dataType: "text",
               data: soapRequest,
               success: function(data, status, req)
               {
               var strJSON = $(req.responseText).find("return").text();
               console.log('billship===>'+strJSON);
               billingDetails(strJSON,billship,orderid);
               },
               error: function(response, textStatus, errorThrown)
               {
               console.log(response);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
    }
}
function billingDetails(strJSON,billship,orderid)
{
    if(checkNetworkConnection())
    {
    	var isFromPickUp=localStorage.getItem('isFromPickUp');
        $.ajax({
               url: strJSON,
               dataType: "text",
               crossDomain: true,
               cache: false,
               success: function(xml) {
               $(xml).find("billingShippingList").each(function () {
                                                       if(billship=="Billing") {
                                                       console.log('result====>'+billship);
                                                       var name= $(this).find("name").text();
                                                       var address= $(this).find("address").text();
                                                       var city= $(this).find("city").text();
                                                       var state= $(this).find("state").text();
                                                       var country= $(this).find("country").text();
                                                       var zip= $(this).find("zip").text();
                                                      if(isFromPickUp=='1'||isFromPickUp==1)
                                                       {
                                                       var phone= "";
                                                       }
                                                       else
                                                       {
                                                       var phone= $(this).find("phone").text();
                                                       }
                                                       $("#"+billship+orderid).empty();
                                                       $("#"+billship+orderid).append(name+'<br/>'+address+'<br/>'+city+'<br/>'+state+'<br/>'+country+'<br/>'+zip+'<br/>'+phone);
                                                       }
                                                       else {
                                                       console.log('result====>'+billship);
                                                       var name= $(this).find("name").text();
                                                       var address= $(this).find("address").text();
                                                       var city= $(this).find("city").text();
                                                       var state= $(this).find("state").text();
                                                       var country= $(this).find("country").text();
                                                       var zip= $(this).find("zip").text();
                                                       var phone= $(this).find("phone").text();
                                                       $("#"+billship+orderid).empty();
                                                       $("#"+billship+orderid).append(name+'<br/>'+address+'<br/>'+city+'<br/>'+state+'<br/>'+country+'<br/>'+zip+'<br/>'+phone);
                                                       }
                                                       
                                                       });
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               },
               error: function(XMLHttpRequest, textStatus, errorThrown) {
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               console.log('fail');
               console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
               }
               });
    }
}
function getOrder(orderid)
{
    
    if(checkNetworkConnection())
    {
        $('.appypie-loader').show();
        wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodOrderDetail";
        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodOrderDetail xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodOrderDetail\"><appId>'+appid+'</appId><orderId>'+orderid+'</orderId></foodOrderDetail></soap:Body></soap:Envelope>';
        console.log("IIIIIIIIIIIIIIIIIIIIIII"+soapRequest);
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               cache: false,
               dataType: "text",
               data: soapRequest,
               success: function(data, status, req)
               {
               var xmlret=req.responseText;
               var strJSON = $(xmlret).find("return").text();
               console.log(strJSON);
               getOrderDetailsXML(strJSON,orderid);
               
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               },
               error: function(response, textStatus, errorThrown)
               {
               console.log('error message--->'+response.responseText);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               }
               });
    }
}
function getOrderDetailsXML(strJSON,orderid)
{
	
	
	
    if(checkNetworkConnection())
    {
        $.ajax({
               url: strJSON,
               dataType: "text",
               crossDomain: true,
               cache: false,
               success: function(xml) {
               var xml =jQuery.parseXML(xml);
               console.log("CCCCCCCCCCCCCCCCCCCCCCCC"+xml);
               html='';
               var totalAmount=0;
               var currency='';
               $(xml).find("product").each(function () {
                                           console.log("1--->");
                                           currency='';
                                           currency=$(this).find("currency").text();
                                           
                                           sessionStorage.setItem('currency',currency);
                                           var customOption=$(this).find("custom_option").text();
                                           console.log("1--->"+customOption);
                                           if(customOption.length>1)
                                           {
                                           html+='<span class="e-mobileOD-list"><label>'+ product_name_food+':</label>'+$(this).find("productName").text()+'('+customOption+')</span>';
                                           }
                                           else
                                           {
                                           html+='<span class="e-mobileOD-list"><label>'+ product_name_food+':</label>'+$(this).find("productName").text()+'</span>';
                                           }
                                           html+='<span class="e-mobileOD-list"><label>'+qty_food+':</label>'+$(this).find("quantity").text()+'</span>';
                                           html+='<span class="e-mobileOD-list"><label>'+pprice_food+':</label>'+$(this).find("price").text()+'</span>';
                                           totalAmount +=parseFloat($(this).find("quantity").text())*parseFloat($(this).find("price").text());
                                           });
               totalAmount=parseFloat(totalAmount)+(parseFloat($(xml).find("delivery").text())+parseFloat($(xml).find("tax").text())+parseFloat($(xml).find("tip").text()))-(parseFloat($(xml).find("discount").text())+parseFloat($(xml).find("coupon").text()));
               console.log("totalAmount--->"+totalAmount);
               
               var delivery=$(xml).find("delivery").text();
               var tax=$(xml).find("tax").text();
               var tip=$(xml).find("tip").text();
               var discount=$(xml).find("discount").text();
               var coupon=$(xml).find("coupon").text();
               if(parseFloat(delivery) > 0)
               {
               console.log('1');
               html+='<span class="e-mobileOD-list"><label>'+deliverycccharge_food+':</label>'+delivery+'</span>';
               }
               if(parseFloat(tax) > 0)
               {
               console.log('2');
               html+='<span class="e-mobileOD-list"><label>'+tax_food+':</label>'+tax+'</span>';
               }
               if(parseFloat(tip) > 0)
               {
               console.log('3');
               html+='<span class="e-mobileOD-list"><label>'+tip_food+':</label>'+tip+'</span>';
               }
               if(parseFloat(discount) > 0)
               {
               console.log('4');
               html+='<span class="e-mobileOD-list"><label>'+discount_food+':</label>'+discount+'</span>';
               }
               if(parseFloat(coupon) > 0)
               {
               console.log('5');
               html+='<span class="e-mobileOD-list"><label>'+ccouponddiscount_food+':</label>'+coupon+'</span>';
               }
               console.log("misctax---->start");
               var jsonObjTax=$(xml).find("misctax").text();
               console.log("misctax---->start-->"+jsonObjTax.length);
               if(jsonObjTax.length > 4) 
               {
               var misctax=jQuery.parseJSON(jsonObjTax);
               
               console.log("misctax.length---->"+misctax.length);
               console.log("the value of totalAmount is-->"+totalAmount);
               var newTotalAmt=0.00;
               if(misctax.length > 0)
               {
               for(c=0;c<misctax.length;c++)
               {
               console.log("the value of c");
               var taxMiscAmount=misctax[c].tax;
               html+='<span class="e-mobileOD-list"><label>'+misctax[c].taxType+':</label>'+formatCurrency(taxMiscAmount,1)+'</span>';
               console.log("the value of c-->"+c);
               newTotalAmt=parseFloat(newTotalAmt) + parseFloat(misctax[c].tax);
               
               }
               newTotalAmt=newTotalAmt.toFixed(2);
               }
               
               console.log("the value of newTotalAmt-->"+newTotalAmt);
               totalAmount=parseFloat(newTotalAmt)+parseFloat(totalAmount);
               }
               console.log('6');
               totalAmount=formatCurrency(totalAmount,1);
               console.log('6');
               html+='<span class="e-mobileOD-list"><label>'+ttotalamount_food+':</label>'+currency+totalAmount+'</span>';
               html+='<span class="e-user_edit" '+primaryColor+' onclick="wantToReorder(\'food\',\''+orderid+'\')">Reorder</span>';
               
               console.log('7');
               if($(xml).find("pickupComment").text())
               {
               var comment=$(xml).find("pickupComment").text();
               console.log('comment length greater then 0===================>'+comment);
               if(comment.length>2)
               html+='<span class="e-mobileOD-list" style="white-space:pre-wrap !important;"><label>'+cccccomment_food+':</label>'+comment+'</span>';
               }
               console.log(html);
               $("#Order"+orderid).empty();
               $("#Order"+orderid).append(html);
               //Ext.Viewport.setMasked(false);
               },
               error: function(XMLHttpRequest, textStatus, errorThrown) {
               console.log('fail');
               
               console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
               }
               });
    }
}

function wantToReorder(pageName,orderid)
{
	
	
	
    $('.appypie-loader').show();
    
    if(pageName == 'food')
    {
        wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodReorderProductJson";
        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodReorderProductJson xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodReorderProductJson\"><appId>'+appid+'</appId><orderId>'+orderid+'</orderId></foodReorderProductJson></soap:Body></soap:Envelope>';
    }
    else
    {
        wsUrl = "http://"+localStorage.getItem("reseller")+"/services/ecomm-soap#ecommReorderProductJson";
        soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommReorderProductJson xmlns=\"http://'+localStorage.getItem("reseller")+'/services/ecomm-soap#ecommReorderProductJson\"><appId>'+appid+'</appId><orderId>'+orderid+'</orderId></ecommReorderProductJson></soap:Body></soap:Envelope>';
    }
    
    console.log("wantToReorder---->"+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           
           var strJSON = $(req.responseText).find("return").text();
           var obj=jQuery.parseJSON(strJSON);
           console.log("wantToReorder---->Data--->"+obj.length);
           if(obj.length == 0)
           {
           alertPopUp(ssorry_food+"!",iiitemisnotavailable_food)
           }
           else
           {
           var cart=0;
           console.log("wantToReorder---->Data--->1"+req.responseText);
           if(!sessionStorage.getItem("cart"))
           {
           sessionStorage.setItem("cart",1);
           
           }
           cart=sessionStorage.getItem("cart");
           console.log("wantToReorder---->Data--->"+obj.length);
           for(var x=0;x<obj.length;x++)
           {
           console.log("wantToReorder---->cart--->"+cart);
           
           var productId=obj[x].productId;
           console.log("0-->productId--->"+productId);
           var qty=obj[x].qty;
           console.log('qty--->'+qty);
           var productName=obj[x].productName;
           console.log("1-->"+productName);
           var price=obj[x].price;
           
           console.log("price-->"+price);
           var productImage=obj[x].productImage;
           console.log("2-->"+productImage);
           var productdescription=obj[x].productdescription;
           var custom_option=obj[x].custom_option;
           console.log("3-->"+custom_option);
           var options=custom_option.split('|');
           console.log("4-->"+options);
           var configTitle='';
           var configValue='';
           var foodcurrency=window.sessionStorage.getItem("currency");
           console.log("4-->foodcurrency--->"+foodcurrency);
           for(var i=0;i<options.length;i++)
           {
           console.log("value of i"+i);
           var titleValue=options[i].split(':');
           
           if(i!=0)
           {
           configTitle=configTitle+','+titleValue[0];
           configValue=configValue + ',' + titleValue[1];
           }
           else
           {
           configTitle=titleValue[0];
           configValue=titleValue[1];
           }
           console.log("configTitle-->"+configTitle+"<--configValue--->"+configValue);
           }
           console.log("pageName--->"+pageName);
           if(pageName == 'food')
           {
           var foodType=obj[x].food_type;
           console.log("foodType--->"+foodType);
           sessionStorage.setItem("foodType"+cart,foodType);
           sessionStorage.setItem("productId"+cart,productId);
           console.log('3');
           sessionStorage.setItem("quantity"+cart,qty);
           sessionStorage.setItem("productName"+cart,productName);
           sessionStorage.setItem("productdescription"+cart,productdescription);
           sessionStorage.setItem("price"+cart,price);
           sessionStorage.setItem("currency"+cart,foodcurrency);
           sessionStorage.setItem("productImage"+cart,productImage);
           console.log('4');
           sessionStorage.setItem("status"+cart,"1");
           sessionStorage.setItem("foodconfigTitleProduct"+cart,configTitle);
           sessionStorage.setItem("foodconfigValueProduct"+cart,configValue);
           sessionStorage.setItem("foodflag","1");
           sessionStorage.setItem("cart",cart);
           }
           else
           {
           sessionStorage.setItem("ecomproductId"+cart,productId);
           sessionStorage.setItem("ecomquantity"+cart,qty);
           sessionStorage.setItem("ecomproductName"+cart,productName);
           sessionStorage.setItem("ecomprice"+cart,price);
           sessionStorage.setItem("ecomproductImage"+cart,productImage);
           sessionStorage.setItem("ecomproductdescription"+cart,productdescription);
           sessionStorage.setItem("ecomstatus"+cart,"1");
           sessionStorage.setItem("ecomconfigTitleProduct"+cart,configTitle);
           sessionStorage.setItem("ecomconfigValueProduct"+cart,configValue);
           sessionStorage.setItem("ecomflag","1");
           console.log("available quantity--->"+obj[x].stock);
           sessionStorage.setItem("ecomAvailquantity"+cart,obj[x].stock);
           sessionStorage.setItem("ecomcart",cart);
           }
           console.log('5');
           
           cart=parseFloat(cart)+1;
           }
           
           if(pageName == 'food')
           {
           setTimeout(function(){
                      
                      bindCart();
                      },100);
           }
           else
           {
           setTimeout(function(){
                      ecomCart_View();
                      },100);
           }
           
           }
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log('error message--->'+response.responseText);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
}

function deliveryType(value)
{
	 
	 
	 var paymetType_HTML='';
	if(value == "0")
		{
		
		   paymetType_HTML+='<div class="page-text">\
			    <div class="newsTand-MobileTab">\
			    <div class="tabViewall" onclick="checkoutView(\'1\');"><a href="#" >'+dddelivery_food+'</a></div>\
			    <div class="tabmyCollection" onclick="checkoutView();"><a href="#"  class="active">'+pickup_food+'</a></div></div><div class="user_tab" ><div class="form-page"><strong>'+dddeliveryromocation_food+'<span class="required">*</span></strong><select data-role="none" id="pickUpAddress"></select> <strong style="text-align:center !important;display:none;"  id="pickUpAddText">'+aaaaaaaaaddress_food+'<span class="required">*</span></strong><textarea data-role="none" style="display:none;" id="pickUpText" class="comments"></textarea>  <strong>'+pppickupime_food+'<span class="required">*</span></strong><input data-role="none" type="time" name="currenttime" id="pickUpTimeFood"/> <strong>'+instructions_foodorder+'</strong><textarea data-role="none"  id="instructionsText" class="comments"></textarea> <div class="cart-update-btn"><a class="e-mobile-btn payment-mobile-btn" onclick="checkoutView(\'2\');">'+cooonfirm_food+'</a></div></div></div></div>';
			    
			 
		}
	else{
		
		  paymetType_HTML+='<div class="page-text">\
			   <div class="user_tab" ><div class="form-page"><strong>'+dddeliveryromocation_food+'<span class="required">*</span></strong><select data-role="none" id="pickUpAddress"></select> <strong style="text-align:center !important;display:none;"  id="pickUpAddText">'+aaaaaaaaaddress_food+'<span class="required">*</span></strong><textarea data-role="none" style="display:none;" id="pickUpText" class="comments"></textarea>  <strong>'+pppickupime_food+'<span class="required">*</span></strong><input data-role="none" type="time" name="currenttime" id="pickUpTimeFood"/> <strong>'+instructions_foodorder+'</strong><textarea data-role="none"  id="instructionsText" class="comments"></textarea> <div class="cart-update-btn"><a class="e-mobile-btn payment-mobile-btn" onclick="checkoutView(\'2\');">'+cooonfirm_food+'</a></div></div></div></div>';
			    
		
		
	}
	
	
 appendHtml(paymetType_HTML,5,5,'food');
    getStoreAdd();
}
function getStoreAdd()
{
    
    
    var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodStoreAddress";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodStoreAddress xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodStoreAddress\"><appId>'+localStorage.getItem("applicationID")+'</appId></foodStoreAddress></soap:Body></soap:Envelope>';
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           console.log('get Delivery Data===>'+strJSON);
           var obj=jQuery.parseJSON(strJSON);
           var storeAdd=obj.store_address;
           var storeHtml='';
           for(var x=0;x<storeAdd.length;x++)
           {
           storeHtml+='<option value="'+storeAdd[x].address+'">'+storeAdd[x].address+'</option>';
           
           }
          
           $('#pickUpAddress').append(storeHtml);
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    
}

function getUserDetail()
{
    var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/app-user-soap#getUserProfileDetailJson";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getUserProfileDetailJson xmlns=\"http://'+localStorage.getItem("reseller")+'/services/app-user-soap#getUserProfileDetailJson\"><appId>'+localStorage.getItem('applicationID')+'</appId><email>'+localStorage.getItem('fooduserid')+'</email></getUserProfileDetailJson></soap:Body></soap:Envelope>';
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           
           console.log("data --->"+req.responseText);
           var dataObj=$(req.responseText).find("return").text();
           var obj=jQuery.parseJSON(dataObj);
           var name=obj[0].name;
           var email=obj[1].email;
           var phnNo=obj[2].phone;
           sessionStorage.setItem('userName',name);
           sessionStorage.setItem('userEmail',email);
           sessionStorage.setItem('userPhnNo',phnNo);
           addDataForPickUp();
           },
           error: function(response, textStatus, errorThrown)
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
}


function addDataForPickUp()
{
	
	
	
    
    var bfname=sessionStorage.getItem('userName');
    var bemail=sessionStorage.getItem('userEmail');
    var bpNo=sessionStorage.getItem('userPhnNo');
    window.sessionStorage.setItem("OrderEmailId",bemail);
    var bsAddress='';
    var bCity='';
    var bState='';
    var bZip='';
    var bCountry='';
    var html='';
     var order='';
    if(window.sessionStorage.getItem("cart") )
    {
        cart=sessionStorage.getItem("cart");
        console.log('Cart====>'+cart);
       
        html='';
        html+='[';
        order+='[';
        var counter=0;
        for (var x=1; x<=parseInt(cart); x++)
        {
            console.log("1 -->"+x);
            var qty=window.sessionStorage.getItem("quantity"+x);
            console.log("2 -->"+x);
            var productId=window.sessionStorage.getItem("productId"+x);
            console.log("3-->"+x);
            var productName=window.sessionStorage.getItem("productName"+x);
            console.log("4-->"+productName);
            var price=sessionStorage.getItem("price"+x);
            console.log("5-->"+x);
            var status=sessionStorage.getItem("status"+x);
            console.log("6-->"+x);
            if(status=="1")
            {
                if(counter>0)
                {
                    order+=',';
                    html+=',';
                }
                var configTitle=sessionStorage.getItem("foodconfigTitleProduct"+x);
                var configTitle1=configTitle.split(',');
                var sendData= sessionStorage.getItem("foodconfigValueProduct"+x);
                var sendData1=sendData.split(',');
                var coustomOption='';
                for(var i=0;i<sendData1.length;i++)
                {
                    coustomOption+=configTitle1[i]+':'+sendData1[i];
                    if((i+1) != sendData1.length)
                    {
                        coustomOption+='|';
                    }
                }
                
                if(coustomOption.length > 2)
                {
                    html+='{"productId":"'+productId+'","qty":"'+qty+'","custom_option":"'+coustomOption+'","price":"'+price+'"}';
                }
                else
                {
                    html+='{"productId":"'+productId+'","qty":"'+qty+'","custom_option":""}';
                }
                order+='{"name":"'+productName+'","qty":"'+qty+'","price":"'+price+'","sku":"'+productId+'"}'
                counter=counter+1;
            }
        }
        console.log("1");
        order+=']'
        html+=']';
    }
    console.log("addDataForPickUp() ---->billingAddress---->starts");
    var billingAddress = '{"name":"'+bfname+'","email":"'+bemail+'","phone":"'+bpNo+'","line1":"'+bsAddress+'","city":"'+bCity+'","countryCode":"'+bCountry+'","postalCode":"'+bZip+'","state":"'+bState+'"}';
    var billing='{"billShip":"Billing","name":"'+bfname+'","address":"'+bsAddress+'","city":"'+bCity+'","state":"'+bState+'","country":"'+bCountry+'","zip":"'+bZip+'","phone":"'+bpNo+'"}';
    console.log("addDataForPickUp() ---->billingAddress---->"+billingAddress);
    var shipping='{"billShip":"Shipping","name":"'+bfname  +'","address":"'+bsAddress+'","city":"'+bCity+'","state":"'+bState+'","country":"'+bCountry+'","zip":"'+bZip+'","phone":"'+bpNo+'"}';
    console.log("addDataForPickUp() ---->shipping---->"+shipping);
     var blname='';
    if(bfname.indexOf(' '))
    {
        var blfname=bfname.split(' ');
        if(blfname[1] != null)
        {
            blname=blfname[1];
        }
    }
    else
    {
       blname='';
    }
    console.log("addDataForPickUp() ---->blname---->"+blname);
    sessionStorage.setItem("LastNamePayment",bfname);
    sessionStorage.setItem("billingAddressPayment",billingAddress);
    sessionStorage.setItem("OrderPayment",order);
    var paypalAddFormHtml='<!-- Specify a Buy Now button. -->\
    <input type="hidden" name="cmd" value="_xclick">\
    <input type="hidden" name="first_name" value="'+bfname+'" >\
    <input type="hidden" name="last_name" value="'+blname+'" >\
    <input type="hidden" name="address1" value="'+bsAddress+'" >\
    <input type="hidden" name="city" value="'+bCity+'">\
    <input type="hidden" name="state" value="'+bState+'">\
    <input type="hidden" name="zip" value="'+bZip+'">\
    <input type="hidden" name="country" value="'+bCountry+'">\
    <input type="hidden" name="night_phone_a" value="'+bpNo+'">\
    <input type="hidden" name="night_phone_b" value="{MOB2}"><input type="hidden" name="night_phone_c" value="{MOB3}">\
    <input type="hidden" name="email" value="'+bemail+'">\
    <!-- Specify details about the item that buyers will purchase. -->\
    <input type="hidden" name="item_name" value="'+pppaymentorderfor_food+' '+localStorage.getItem("AppName")+'('+localStorage.getItem("applicationID")+')">\
    <input type="hidden" name="amount" value="'+parseFloat(window.sessionStorage.getItem("gtotal1")).toFixed(2)+'">\
    <input type="hidden" name="quantity" value="1">\
    <input type="hidden" name="currency_code" value="'+sessionStorage.getItem("currency")+'">\
    <input name=Õ¢nÔ value=Õ�ppyPie_SPÔ type=Ô hiddenÓ¾\
    <input type="hidden" name="custom" value="1">';
    console.log("addDataForPickUp() ---->paypalAddFormHtml---->"+paypalAddFormHtml);
    sessionStorage.setItem('paypalFormHtml',paypalAddFormHtml);
    
    console.log("addDataForPickUp() ---->html---->"+html);
    sessionStorage.setItem("udetails",html);
    sessionStorage.setItem("ubilling",billing);
    sessionStorage.setItem("ushipping",shipping);
    console.log("addDataForPickUp() ---->done till here---->");
    
    
	submitOrderPickUp('1');
    /*
    if(sessionStorage.getItem("onlyPickUp"))
    		{
    
    	
    		}
    else{
    	
    	 paymentTypeView("food");
    	
    }*/
   
}

function checkoutView(value)
{
    
    if(parseInt(value) == 2)
    {
        if(document.getElementById("instructionsText"))
        {
            console.log("PayNow1() 2.1 ");
            var instructionsText=document.getElementById("instructionsText").value;
            sessionStorage.setItem('instructionsText',instructionsText);
        }
        
        var deliveryTime='';
        if(document.getElementById("pickUpTimeFood"))
        {
            deliveryTime=document.getElementById("pickUpTimeFood").value;
        }
        
        var storeClosingTimmings=parseFloat(sessionStorage.getItem("storeClosingTimmings"));
        var storeOpenTimmings= parseFloat(sessionStorage.getItem("storeOpenTimmings"));
        
        
        if(deliveryTime!= null && deliveryTime != "none" && deliveryTime!="")
        {
            deliveryTime=deliveryTime.replace(':','');
            
        }
        var openedView=sessionStorage.getItem("CheckOutViewOpen");
        //deepak
        if(openedView == 'food')
        {
            var preferredDeliveryTime=parseFloat(deliveryTime);
            if(preferredDeliveryTime<60)
            {
                
                preferredDeliveryTime=2400+preferredDeliveryTime;
            }
            
            if((preferredDeliveryTime<=storeClosingTimmings)&&(preferredDeliveryTime>=storeOpenTimmings))
            {
                var str = "";
                var currentDate = new Date();
                var currentTime = new Date(currentDate.getTime() + (45*60*1000))
                var hours = currentTime.getHours();
                var minutes = currentTime.getMinutes();
                var seconds = currentTime.getSeconds();
                
                if (minutes < 10) {
                    minutes = "0" + minutes
                }
                if (seconds < 10) {
                    seconds = "0" + seconds
                }
                str += hours.toString() + minutes.toString() ;
                if(!(preferredDeliveryTime>=(parseInt(str))))
                {
                    
                    if(window.localStorage.getItem("applicationID") != "ef301b95420d")
                	{
                    
                    
                    
                    alertPopUp(pppickupime_food,alerttText);
                    return;
                	}
                    
                }
                else if(preferredDeliveryTime>=(storeOpenTimmings+45))
                {
                }
                else if((storeClosingTimmings>=parseInt(str))&&(parseInt(str)<=(storeOpenTimmings+45)))
                {
                    
                    
                    if(storeOpenTimmings>2400)
                    {
                        storeOpenTimmings=storeOpenTimmings-2400;
                    }
                    
                    var openAmPm;
                    
                    if(storeOpenTimmings>=1200)
                    {
                        openAmPm="PM";
                    }
                    else
                    {
                        openAmPm="AM";
                    }
                    
                    if(storeOpenTimmings<1000)
                    {
                        if(storeOpenTimmings<100)
                        {
                            storeOpenTimmings="00"+storeOpenTimmings.toString();
                        }
                        else
                        {
                            storeOpenTimmings="0"+storeOpenTimmings.toString();
                        }
                    }
                    
                    storeOpenTimmings= storeOpenTimmings.toString().substr(0, 2) + ":" + storeOpenTimmings.toString().substr(2);
                    
                   
                    
                    var alertText=ppleaseaterpentime_food+storeOpenTimmings+' '+openAmPm+'';
                    
                    
                    alertPopUp(pppickupime_food,alertText);
                    return;
                }
                
                
            }
            
            else
            {
                if(preferredDeliveryTime.toString()=="NaN"||preferredDeliveryTime.toString()=='NaN')
                {
                    
                    alertPopUp(pppickupime_food,ppppleaseenterapreferredpickuptime_food);
                    return;
                }
                var workingHour=parseInt(sessionStorage.getItem("workingHour"));
                if(workingHour<1000)
                {
                    
                    if(workingHour<10)
                    {
                        workingHour="000"+workingHour.toString();
                    }
                    else if(workingHour<100)
                    {
                        workingHour="00"+workingHour.toString();
                    }
                    else
                    {
                        workingHour="0"+workingHour.toString();
                    }
                }
                if(parseInt(workingHour)>=2400)
                {
                    
                }
                else
                {
                    if(storeClosingTimmings>2400)
                    {
                        storeClosingTimmings=storeClosingTimmings-2400;
                    }
                    if(storeOpenTimmings>2400)
                    {
                        storeOpenTimmings=storeOpenTimmings-2400;
                    }
                    
                    var openAmPm;
                    var closeAmPm;
                    
                    
                    
                    if(storeClosingTimmings>=1200)
                    {
                        closeAmPm="PM";
                    }
                    else
                    {
                        closeAmPm="AM";
                    }
                    if(storeOpenTimmings>=1200)
                    {
                        openAmPm="PM";
                    }
                    else
                    {
                        openAmPm="AM";
                    }
                    
                    if(storeClosingTimmings<1000)
                    {
                        if(storeClosingTimmings<100)
                        {
                            storeClosingTimmings="00"+storeClosingTimmings.toString();
                        }
                        else
                        {
                            storeClosingTimmings="0"+storeClosingTimmings.toString();
                        }
                    }
                    if(storeOpenTimmings<1000)
                    {
                        if(storeOpenTimmings<100)
                        {
                            storeOpenTimmings="00"+storeOpenTimmings.toString();
                        }
                        else
                        {
                            storeOpenTimmings="0"+storeOpenTimmings.toString();
                        }
                    }
                    
                    storeClosingTimmings=storeClosingTimmings.toString().substr(0, 2) + ":" + storeClosingTimmings.toString().substr(2);
                    
                    
                    storeOpenTimmings= storeOpenTimmings.toString().substr(0, 2) + ":" + storeOpenTimmings.toString().substr(2);
                   
                    
                    var alertText=ppleaseenterareferredweenentime_food+storeOpenTimmings+' '+openAmPm+andstoreclosettime_food+storeClosingTimmings+' '+closeAmPm+'';
                    
                    
                    alertPopUp(pppickupime_food,alertText);
                    return;
                }
            }
        }
        //deepak
        //shivshankar
            if(localStorage.getItem('fooduserid'))
            {
               
                getUserDetail();
                
            }
            else
            {
                sessionStorage.setItem('pickUpSignup','true');
                loginEcom();
            }
        return false;
    }
    
  /*  if(sessionStorage.getItem("first") && value)
    {
    	document.getElementById("foodDelHide").style.display = "block";
    	document.getElementById("user_tab2").style.display = "block";
    	document.getElementById("instructionsText").style.display = "block";
    	sessionStorage.removeItem("first");
        return false;
    }*/
    
    if(!value)
    {
    	
    	
    	
    	
    	
        deliveryType("0");
        return false;
    }
    
    sessionStorage.setItem("CheckOutViewOpen","food");
    if(checkNetworkConnection())
    {
        if(localStorage.getItem("layout")=="bottom") {
            $(".app_navigation_bottom").show();
            localStorage.setItem("bottomHide","false");
        }
        var couponDisp='none';
        var deliveryDisp='block';
        var discountDisp='block';
        var taxDisp='block';
        var tipDisp='block';
        var deliveryDisp='block';
        if(window.sessionStorage.getItem("couponAmountFood")) {
            var couponDisp='block';
        }
        
        
        if(window.sessionStorage.getItem("fooddelivery") == 0)
        {
            deliveryDisp="none";
        }
        if(window.sessionStorage.getItem("fooddiscount") == 0)
        {
            discountDisp="none";
        }
        if(sessionStorage.getItem("foodtax") == 0)
        {
            taxDisp="none";
        }
        console.log("tipAmount--->>>"+sessionStorage.getItem("tipAmount"));
        if(sessionStorage.getItem("tipAmount") == 0)
        {
            tipDisp="none";
        }
        if(sessionStorage.getItem("couponDiscount") == 0)
        {
            var couponDisp='none';
        }
        
        
        var foodcurrency=window.sessionStorage.getItem("currency");
        var subtotal= window.sessionStorage.getItem("subtotal");
        
        var extraTaxHtml=calculateMiscTax(subtotal,'checkoutpage');
        var counter=0;
        //$('.appypie-loader').show();
        var strJSON123="";
        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodPaymentMethodLabel";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodPaymentMethodLabel xmlns=\"'+localStorage.getItem("reseller")+'services/food-soap#foodPaymentMethodLabel\"><appId>'+appid+'</appId></foodPaymentMethodLabel></soap:Body></soap:Envelope>';
    	console.log("soapRequestsoapRequestsoapRequestsoapRequest>>>>>>"+soapRequest);
        
        $.ajax({
            type: "POST",
            url: wsUrl,
            contentType: "text/xml",
            dataType: "text",
            data: soapRequest,
            success: function(data, status, req)
            {
            	strJSON123=$(req.responseText).find("return").text();
           
            	  var ckechout_HTML='';
                  
                  
                  //alert(strJSON123);
                  
                  var checkCondition=strJSON123.indexOf("cod|") > -1 || strJSON123.indexOf("##paypal|") > -1 || strJSON123.indexOf("##cc|") > -1 || strJSON123.indexOf("##cc_phone|") > -1;
                  
                  //alert(checkCondition);
                  
                  
                  if(strJSON123.indexOf("##pu|") > -1  && checkCondition)
                 {
                   ckechout_HTML+='<div class="page-text">\
               <div class="newsTand-MobileTab">\
               <div class="tabViewall" onclick="checkoutView(\'1\');"><a href="#" class="active" >'+dddelivery_food+'</a></div>\
               <div class="tabmyCollection" onclick="checkoutView();"><a href="#">'+pickup_food+'</a></div></div>';             
			   }
                  
                  else if(!checkCondition && strJSON123.indexOf("pu|") > -1)
                	  {
                	  
                	  sessionStorage.setItem("onlyPickUp",true);
                	  
                	  deliveryType("1");
                      return false;

                	  
                	  }
                  
                  
                  
                 ckechout_HTML+='<!--div class="e-mobile-product"--><div class="cart-MSG" id="cart-MSG1" style="display:none"><span class="er_close">x</span><div class="cart_success"></div></div><div class="cart-MSG" id="cart-MSG2" style="display:none"><span class="er_close">x</span><div class="cart_error2"></div></div><div class="user_tab" id="user_tab2"><h4>'+shipping_address_food+'</h4><div class="form-page"><strong>'+first_name_food+'<span class="required">*</span></strong><input data-role="none" id="bfname" type="text" placeholder="'+first_name_food+'"><strong>'+telephone_food+'<span class="required">*</span></strong><input data-role="none" id="bpNo"  type="text" placeholder="'+telephone_food+'" ><strong>'+email_id_food+'<span class="required">*</span></strong><input data-role="none" id="bemail"  type="text" placeholder="'+email_id_food+'" ><strong>'+address_food+'<span class="required">*</span></strong><input data-role="none" id="bsAddress"  type="text" placeholder="'+address_food+'" ><strong>'+city_food+'<span class="required">*</span></strong><input data-role="none" id="bCity"  type="text" placeholder="'+city_food+'" ><strong>'+state_province_food+'<span class="required">*</span></strong><input data-role="none" id="bState" type="text" placeholder="'+state_province_food+'" ><strong>'+zip_postal_code_food+'<span class="required">*</span></strong><input data-role="none" id="bZip"  type="text" placeholder="'+zip_postal_code_food+'" ><strong>'+ccccountry_food+'<span class="required">*</span></strong><div id="bcountryTry"></div><input style="display:none;" data-role="none" type="checkbox" id="show_billing_address"/></div></div><div style="display:none;" class="user_tab" id="billing_address"><h4>'+ssshhhippingddress_food+'</h4><div class="form-page"><strong>'+first_name_food+'<span class="required">*</span></strong><input data-role="none" type="text"  id="sfname" placeholder="'+first_name_food+'" ><strong>'+telephone_food+'<span class="required">*</span></strong><input data-role="none" type="text"  id="spNo" placeholder="'+telephone_food+'" ><strong>'+address_food+'<span class="required">*</span></strong><input data-role="none" type="text"  id="ssAddress" placeholder="'+address_food+'"><strong>City<span class="required">*</span></strong><input data-role="none" type="text"  id="sCity" placeholder="'+city_food+'" ><strong>'+state_province_food+'<span class="required">*</span></strong><input data-role="none" type="text"  id="sState" placeholder="'+state_province_food+'" ><strong>'+zip_postal_code_food+'<span class="required">*</span></strong><input data-role="none" type="text"  id="sZip" placeholder="'+zip_postal_code_food+'" ><strong>'+ccccountry_food+'<span class="required">*</span></strong><div id="scountryTry"></div></div></div>';
                
				
               var preferred_deliverytime_Time="block";
               if(sessionStorage.getItem("preferred_deliverytime_required")!="yes")
               {
               preferred_deliverytime_Text="";
               preferred_deliverytime_Time="none";
               }
				
				ckechout_HTML+='<div class="e-mobileOH_tab open_tab" id="InstructionsTab"><h4>'+instructions_foodorder+'</h4><textarea data-role="none" style="width:100%;height:100px" id="instructionsText"></textarea><br><div><div style="float:left;width:45%">'+preferred_deliverytime_Text+'</div><div style="float:left; width:45%"><input data-role="none" type="time" name="currenttime" id="pickUpTime" style="display:'+preferred_deliverytime_Time+';"/></div></div><br><br><div class="user_tab" ><div class="form-page"><p style="float:left;"><strong>'+dddeliveryromocation_food+'<span class="required">*</span></strong><select data-role="none" id="pickUpAddress"></select> <strong style="text-align:center !important;display:none;"  id="pickUpAddText">'+aaaaaaaaaddress_food+'<span class="required">*</span></strong><textarea data-role="none" style="display:none;" id="pickUpText" class="comments"></textarea></div></div></p><br clear="both"></div>';
				//ckechout_HTML+='<div class="e-mobileOH_tab open_tab" id="InstructionsTab"><h4>'+instructions_food+'</h4><textarea data-role="none" style="width:100%;height:100px" id="instructionsText"></textarea><br><p style="float:left;">Preferred Delivery Time:-<input data-role="none" type="time" name="currenttime" id="pickUpTime" style=""/></p><br><br><div class="user_tab" ><div class="form-page"><p style="float:left;"><strong>Delivery From Location<span class="required">*</span></strong><select data-role="none" id="pickUpAddress"></select> <strong style="text-align:center !important;display:none;"  id="pickUpAddText">Address<span class="required">*</span></strong><textarea data-role="none" style="display:none;" id="pickUpText" class="comments"></textarea></div></div></p><br clear="both"></div>';
               //ckechout_HTML+='<div class="e-mobileOH_tab open_tab" id="InstructionsTab"><h4>'+instructions_food+'</h4><textarea data-role="none" style="width:100%;height:100px" id="instructionsText"></textarea><br><p style="float:left;">Preferred Delivery/Pickup Time:-<input data-role="none" type="time" name="currenttime" id="pickUpTime" style=""/></p><br clear="both"></div>';
               
               ckechout_HTML+=' <div class="user_tab"><h4>'+payment_details_food+'</h4><ul class="pay-mobile-cart"><li >'+subtotal_food+'<span style="color:#000" id="subtotal">'+foodcurrency+' '+
               subtotal+'</span></li><li style="display:'+deliveryDisp+'">'+ddeliverycharge_food+' <span style="color:#000" id="delivery">'+foodcurrency+' '+window.sessionStorage.getItem("fooddelivery")+'</span></li><li style="display:'+taxDisp+'">'+ttax_food+' <span style="color:#000;" id="tax">'+foodcurrency+' '+window.sessionStorage.getItem("foodtax")+'</span></li>'+extraTaxHtml+'<li class="tipClass"><span class="tiptext">'+tip_food+'</span><input data-role="none" type="text" style="border:none;width:50px;" style="color:#000" id="tipAmount" class="qty" value="'+sessionStorage.getItem("tipAmount")+'%" readonly/></span></li><li style="display:'+discountDisp+'">'+ddiscount_food+' <span style="color:#000;" id="discount">'+foodcurrency+' '+window.sessionStorage.getItem("fooddiscount")+'</span></li><li id="couponDID" style="color:#000;display:'+couponDisp+'">'+ccoupon_food+'<span id="coupon" tyle="color:#000">'+foodcurrency+' '+sessionStorage.getItem("couponAmountFood")+'</span></li><li>'+ggrandtotal_food+'<span style="color:#000" class="gtotal" id="gtotal">'+foodcurrency+' '+window.sessionStorage.getItem("gtotal1")+'</span></li></ul><div class="cart-update-btn"><a class="e-mobile-btn payment-mobile-btn" '+primaryColor+' onclick="PayNow();">'+pppppayow_food+'</a></div></div><!--/div--><ul class="sub-menu" style="display:none;" ><li id="homeIcon" class="homeIconClass"><a onclick="home()" class="home-nav">'+home_food+'</a></li><li style="display:'+loginDispl+';"><a onclick="loginEcom()" class="login-nav">'+llloginregistration_food+'</a></li><li style="display:'+accountDispl+';"><a onclick="myaccount()" class="account-nav">'+mmyaccount_food+'</a></li><li><a onclick="goHome()" class="menu-nav">'+mysshop_food+'</a></li><li><a onclick="bindCart(\'mainpagefood\')" class="cart-nav">'+cartt_food+'</a></li><li><a onclick="termsEcom()" class="terms-nav">'+termscconditions_food+'</a></li><li><a onclick="PolicyEcom()" class="policy-nav">'+privacypolicy_food+'</a></li></ul>';                  
               getStoreAdd(); 
				 appendHtml(ckechout_HTML,5,5,'food');
                  //$("#contentHolder3").empty();
                  getCountry("checkout");
                  getDeliveryDistance();
                  $("#show_billing_address").click(function(){
                                                   $("#billing_address").slideToggle();
                                                   });
                  $("#instructionsText").hide();
                  $("#InstructionsTab h4").click(function(){
                                                 $(this).parents("#InstructionsTabb").toggleClass("open_tab");
                                                 
                                                 $(this).parents("#InstructionsTab").children("textarea").slideToggle();
                                                 });
                  
                 
                  if(document.getElementById("foodDelHide") && document.getElementById("user_tab2") && document.getElementById("instructionsText") && first!=1)
              	{
                	 var xx= document.getElementById("DeliveryTab");	  
                	 xx.setAttribute("class", "active");
              	document.getElementById("foodDelHide").style.display = "block";
              	document.getElementById("user_tab2").style.display = "block";
              	document.getElementById("instructionsText").style.display = "block";
              	
              	
              	}
                  
                 
                  else if( checkCondition)
                      
                  {
                	 if(strJSON123.indexOf("##pu|") > -1 )
                		 {
                		 
                		 
                		 
                		 }
                	 else
                		 {
                		 
                		 document.getElementById("foodDelHide").style.display = "block";
                       	document.getElementById("user_tab2").style.display = "block";
                       	document.getElementById("instructionsText").style.display = "block";
                		 
                		 }
                		 
                		
                 
                   
                  }
                  
                  
               
                  first++;
                 
            
            
            },
            error: function(response, textStatus, errorThrown) {
               
		  		   //alert(response);
            	 setTimeout(function(){$('.appypie-loader').hide();},1000);
			
          console.log("Error : " + JSON.stringify(response));
          console.log("Error : " + textStatus);
         console.log("Error : " + errorThrown.responseText);
          }
            });
        
        
      
    	
        
      
    }
    
}




function getDeliveryDistance()
{
    var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodConfiguration";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodConfiguration xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodConfiguration\"><appId>'+appid+'</appId></foodConfiguration></soap:Body></soap:Envelope>';
    
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           cache: false,
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON = $(req.responseText).find("return").text();
           console.log('getDeliveryDistance===>'+strJSON);
           var obj=jQuery.parseJSON(strJSON);
           
           var storeAdd=obj.store_address;
           var storeLat=obj.store_latitude;
           var storeLong=obj.store_longitude;
           var storeDist=obj.free_delivery_distance;
           var storeDistUnits=obj.free_delivery_unit;
           console.log("storeDist--->"+storeLat+"<--storeLong--->"+storeLong);
           if(storeAdd.length > 1 && storeLat.length > 1 && storeLong.length > 1 && parseFloat(storeDist) > 0)
           {
           sessionStorage.setItem("localLatitude",storeLat);
           sessionStorage.setItem("localLongitude",storeLong);
           sessionStorage.setItem("foodDeliveryDist",storeDist);
           sessionStorage.setItem("storeAdd",storeAdd);
           if(storeDistUnits == 'km')
           {
           localStorage.setItem('calculateNearByInUnits','kms');
           }
           else
           {
           localStorage.setItem('calculateNearByInUnits','mi');
           }
           }
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
}
function PayNow()
{
	localStorage.setItem('isFromPickUp',0);
    $('.appypie-loader').show();
    console.log("PayNow() 1 ");
    var openedView=sessionStorage.getItem("CheckOutViewOpen");
    console.log("PayNow() 1.1 ");
    var bfname=document.getElementById("bfname").value;
    console.log("PayNow() 1.2 ");
    var bpNo=document.getElementById("bpNo").value;
    var bemail=document.getElementById("bemail").value;
    var bsAddress=document.getElementById("bsAddress").value;
    console.log("PayNow() 1.3 ");
    var bCity=document.getElementById("bCity").value;
    var bState=document.getElementById("bState").value;
    var bZip=document.getElementById("bZip").value;
    console.log("PayNow() 1.4 ");
    var bCountry=document.getElementById("bCountry").value;
    
    console.log("PayNow() 1.5 ");
    var sfname=document.getElementById("sfname").value;
    var spNo=document.getElementById("spNo").value;
    var ssAddress=document.getElementById("ssAddress").value;
    var sCity=document.getElementById("sCity").value;
    var sState=document.getElementById("sState").value;
    var sZip=document.getElementById("sZip").value;
    var sCountry=document.getElementById("sCountry").value;
     console.log("PayNow() 2 ");
    if(document.getElementById("instructionsText"))
    {
        console.log("PayNow() 2.1 ");
        var instructionsText=document.getElementById("instructionsText").value;
        var deliveryTime='';
        if(document.getElementById("pickUpTime"))
        {
            deliveryTime=document.getElementById("pickUpTime").value;
        }
        var storeClosingTimmings=parseFloat(sessionStorage.getItem("storeClosingTimmings"));
        var storeOpenTimmings= parseFloat(sessionStorage.getItem("storeOpenTimmings"));
        
        if(deliveryTime!= null && deliveryTime != "none" && deliveryTime!="")
        {
            deliveryTime=deliveryTime.replace(':','');
            
        }
		//deepak
		
        if(openedView == 'food')
        {
            
            if(parseFloat(sessionStorage.getItem("minOrderValue")) <= parseFloat(sessionStorage.getItem("gtotal1")))
            {
                
                
            }
            else
            {
                if(!sessionStorage.getItem("minOrderValue"))
                {
                    
                    
                }
                else
                {
                    navigator.notification.alert(miordervalueis_food+sessionStorage.getItem("minOrderValue"), setTimeout, ssorry_food, okkk_food);
                    $('.appypie-loader').hide();
                    return;
                }
            }
	      if(sessionStorage.getItem("preferred_deliverytime_required")=="yes")
            {
            var preferredDeliveryTime=parseFloat(deliveryTime);
            if(preferredDeliveryTime<60)
            {
                
                preferredDeliveryTime=2400+preferredDeliveryTime;
            }
            
      
            if((preferredDeliveryTime<=storeClosingTimmings)&&(preferredDeliveryTime>=storeOpenTimmings))
            {
			var str="";
                var currentDate = new Date();
                var currentTime = new Date(currentDate.getTime() + (45*60*1000))
                var hours = currentTime.getHours();
                var minutes = currentTime.getMinutes();
                var seconds = currentTime.getSeconds();
                
                if (minutes < 10) {
                    minutes = "0" + minutes
                }
                if (seconds < 10) {
                    seconds = "0" + seconds
                }
                
                str += hours.toString() + minutes.toString() ;
                
                if(!(preferredDeliveryTime>=(parseInt(str))))
                {
                	
                    
                  
                    
                    
                    alertPopUp(ddddeliveryime_food,ppleaseselectdeliverytimetime_food);
                    return;
                    
                }
                else if(preferredDeliveryTime>=(storeOpenTimmings+45))
                {
                }
                else if((storeClosingTimmings>=parseInt(str))&&(parseInt(str)<=(storeOpenTimmings+45)))
                {
                    
                    
                    if(storeOpenTimmings>2400)
                    {
                        storeOpenTimmings=storeOpenTimmings-2400;
                    }
                    
                    var openAmPm;
                    
                    if(storeOpenTimmings>=1200)
                    {
                        openAmPm="PM";
                    }
                    else
                    {
                        openAmPm="AM";
                    }
                    
                    if(storeOpenTimmings<1000)
                    {
                        if(storeOpenTimmings<100)
                        {
                            storeOpenTimmings="00"+storeOpenTimmings.toString();
                        }
                        else
                        {
                            storeOpenTimmings="0"+storeOpenTimmings.toString();
                        }
                    }
                    
                    storeOpenTimmings= storeOpenTimmings.toString().substr(0, 2) + ":" + storeOpenTimmings.toString().substr(2);
                    
                    //var alertText='Please select delivery time 45 minutes later from open time '+storeOpenTimmings+' '+openAmPm+'';
                    
                    
                    alertPopUp(ddddeliveryime_food,ppppleasecheckdelivimlease_food);
                    
                    //alertPopUp('Delivery Time',alertText);
                    return;
                }
                
                
            }
            
            else
            {
                if(preferredDeliveryTime.toString()=="NaN"||preferredDeliveryTime.toString()=='NaN')
                {
                   
                    alertPopUp(ddddeliveryime_food,pppleaseentepreferreddelivery_food);
                    return;
                }
                var workingHour=parseInt(sessionStorage.getItem("workingHour"));
                if(workingHour<1000)
                {
                    
                    if(workingHour<10)
                    {
                        workingHour="000"+workingHour.toString();
                    }
                    else if(workingHour<100)
                    {
                        workingHour="00"+workingHour.toString();
                    }
                    else
                    {
                        workingHour="0"+workingHour.toString();
                    }
                }
                if(parseInt(workingHour)>=2400)
                {
                    
                }
                else
                {
                    if(storeClosingTimmings>2400)
                    {
                        storeClosingTimmings=storeClosingTimmings-2400;
                    }
                    if(storeOpenTimmings>2400)
                    {
                        storeOpenTimmings=storeOpenTimmings-2400;
                    }
                    
                    var openAmPm;
                    var closeAmPm;
                    
                    
                    
                    if(storeClosingTimmings>=1200)
                    {
                        closeAmPm="PM";
                    }
                    else
                    {
                        closeAmPm="AM";
                    }
                    if(storeOpenTimmings>=1200)
                    {
                        openAmPm="PM";
                    }
                    else
                    {
                        openAmPm="AM";
                    }
                    
                    if(storeClosingTimmings<1000)
                    {
                        if(storeClosingTimmings<100)
                        {
                            storeClosingTimmings="00"+storeClosingTimmings.toString();
                        }
                        else
                        {
                            storeClosingTimmings="0"+storeClosingTimmings.toString();
                        }
                    }
                    if(storeOpenTimmings<1000)
                    {
                        if(storeOpenTimmings<100)
                        {
                            storeOpenTimmings="00"+storeOpenTimmings.toString();
                        }
                        else
                        {
                            storeOpenTimmings="0"+storeOpenTimmings.toString();
                        }
                    }
                    
					
					 storeClosingTimmings=storeClosingTimmings.toString().substr(0, 2) + ":" + storeClosingTimmings.toString().substr(2);
                    var H = +storeClosingTimmings.substr(0, 2);
                	var h = H % 12 || 12;
                	var ampm = H < 12 ? "AM" : "PM";
                	storeClosingTimmings = h + storeClosingTimmings.substr(2, 3);
                    
                    storeOpenTimmings= storeOpenTimmings.toString().substr(0, 2) + ":" + storeOpenTimmings.toString().substr(2);
                    
                    var HH = +storeOpenTimmings.substr(0, 2);
                	var hh = HH % 12 || 12;
                	var ampmm = HH < 12 ? "AM" : "PM";
                	storeOpenTimmings = hh+ storeOpenTimmings.substr(2, 3);
                    //storeClosingTimmings=storeClosingTimmings.toString().substr(0, 2) + ":" + storeClosingTimmings.toString().substr(2);
                    
                    
                    //storeOpenTimmings= storeOpenTimmings.toString().substr(0, 2) + ":" + storeOpenTimmings.toString().substr(2);
                    
                    
                     
                    
                    var alertText=pppleasestoreopentimee_food+storeOpenTimmings+' '+openAmPm+toText_food+storeClosingTimmings+' '+closeAmPm+'';
                    
                    
                    alertPopUp(ddddeliveryime_food,alertText);
                    return;
                }
            }
        }
        
		
        if(instructionsText.length < 1)
        {
            instructionsText='';
        }
        if(openedView == 'food')
        {
            sessionStorage.setItem('instructionsText',instructionsText+'\n Delivery\Pickup Time:-'+deliveryTime);
        }
        else
        {
            sessionStorage.setItem('instructionsText',instructionsText);
        }
         console.log("PayNow() 2.2 ");
    }
}
    else
    {
        if(openedView == 'food')
        {
            sessionStorage.setItem('instructionsText','Delivery\Pickup Time:-'+deliveryTime);
        }
        else
        {
            sessionStorage.setItem('instructionsText','');
        }
        console.log("PayNow() 2.3 ");
    }
    console.log("PayNow() 3 ");
    if(bfname !='' && bfname != null) {
        if(bpNo !='' && bpNo != null) {
            if(bemail !='' && bemail != null) {
                if(bsAddress !='' && bsAddress != null) {
                    if(bCity !='' && bCity != null) {
                        if(bState !='' && bState != null) {
                            if(bZip !='' && bZip != null) {
                                if(bCountry !='' && bCountry != null && bCountry != 'Select') {
                                    if(document.getElementById("show_billing_address").checked)
                                    {
                                        
                                        if(sfname !='' && sfname != null) {
                                            if(spNo !='' && spNo != null) {
                                                if(ssAddress !='' && ssAddress != null) {
                                                    if(sCity !='' && sCity != null) {
                                                        if(sState !='' && sState != null) {
                                                            if(sZip !='' && sZip != null) {
                                                                if(sCountry !='' && sCountry != null && sCountry != 'Select') {
                                                                    if(openedView == 'food')
                                                                    {
                                                                        
                                                                        if(sessionStorage.getItem("foodDeliveryDist"))
                                                                        {
                                                                            var addForLatLong=ssAddress+'+'+sCity+'+'+sState+'+'+sCountry;
                                                                            
                                                                            if(localStorage.getItem('calculateNearByInUnits') == 'kms')
                                                                            {
                                                                                var units='metric';
                                                                            }
                                                                            else
                                                                            {
                                                                                var units='imperial';
                                                                            }
                                                                            
                                                                            
                                                                            var destinations=sessionStorage.getItem("storeAdd");
																			destinations = encodeURI(destinations);
                                                                            addForLatLong = encodeURI(addForLatLong);
																			
                                                                            $.ajax({
                                                                                   url: 'https://maps.googleapis.com/maps/api/distancematrix/json?origins='+addForLatLong.replace(' ','+')+'&destinations='+destinations.replace(' ','+')+'&units='+units+'&mode=DRIVING&key=AIzaSyAUlshWWtBduQdUrTSA9VMThhWfGk3Hm9A',
                                                                                   dataType: "text",
                                                                                   crossDomain: true,
                                                                                   cache: false,
                                                                                   success: function(xml) {
                                                                                   var response=jQuery.parseJSON(xml);
                                                                                   var results = response.rows[0].elements;
                                                                                   var element = results[0];
                                                                                   if(element.status=="OK")
                                                                                   {
                                                                                   var distance = element.distance.text;
                                                                                   if(parseFloat(distance) > parseFloat(sessionStorage.getItem("foodDeliveryDist")) && parseFloat(distance))
                                                                                   {
                                                                                	  
                                                                                   navigator.notification.alert(
                                                                                		   dddddeliveryiotavailableinyourlocation_food,
                                                                                                                alertDismissed,
                                                                                                                ssorry_food,
                                                                                                                okkk_food
                                                                                                                );
                                                                                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                                                                                   return false;
                                                                                   }
                                                                                   else
                                                                                   {
                                                                                   payAfterValidate(openedView);
                                                                                   }
                                                                                   }
                                                                                   else
                                                                                   {
                                                                                   
                                                                                   navigator.notification.alert(
                                                                                		   dddddeliveryiotavailableinyourlocation_food,
                                                                                                                alertDismissed,
                                                                                                                ssorry_food,
                                                                                                                okkk_food
                                                                                                                );
                                                                                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                                                                                   return false;
                                                                                   
                                                                                   }
                                                                                   
                                                                                   },
                                                                                   error: function(XMLHttpRequest, textStatus, errorThrown) {
                                                                                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                                                                                   console.log('fail');
                                                                                   console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
                                                                                   if(sessionStorage.getItem("foodDeliveryDist"))
                                                                                   {
                                                                                   navigator.notification.alert(
                                                                                		   dddddeliveryiotavailableinyourlocation_food,
                                                                                                                alertDismissed,
                                                                                                                ssorry_food,
                                                                                                                okkk_food
                                                                                                                );
                                                                                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                                                                                   return false;
                                                                                   }
                                                                                   else
                                                                                   {
                                                                                   payAfterValidate(openedView);
                                                                                   }
                                                                                   }
                                                                                   });
                                                                            
                                                                        }
                                                                        else
                                                                        {
                                                                            payAfterValidate(openedView);
                                                                        }
                                                                    }
                                                                    else
                                                                    {
                                                                        payAfterValidate(openedView);
                                                                    }
                                                                    
                                                                }
                                                                else {
                                                                	
                                                                    navigator.notification.alert(
                                                                    		ppppppleasenteountry_food,
                                                                                                 alertDismissed,
                                                                                                 bblankfield_food,
                                                                                                 okkk_food
                                                                                                 );
                                                                    setTimeout(function(){$('.appypie-loader').hide();},1000);
                                                                    return false;
                                                                }
                                                            }
                                                            else {
                                                                navigator.notification.alert(
                                                                		eenteipostalode_food,
                                                                                             alertDismissed,
                                                                                             bblankfield_food,
                                                                                             okkk_food
                                                                                             );
                                                                setTimeout(function(){$('.appypie-loader').hide();},1000);
                                                                return false;
                                                            }
                                                        }
                                                        else {
                                                            navigator.notification.alert(
                                                            		pppllleasenterterovince_food,
                                                                                         alertDismissed,
                                                                                         bblankfield_food,
                                                                                         okkk_food
                                                                                         );
                                                            setTimeout(function(){$('.appypie-loader').hide();},1000);
                                                            return false;
                                                        }
                                                    }
                                                    else {
                                                        navigator.notification.alert(
                                                        		pplleasenterourity_food,
                                                                                     alertDismissed,
                                                                                     bblankfield_food,
                                                                                     okkk_food
                                                                                     );
                                                        setTimeout(function(){$('.appypie-loader').hide();},1000);
                                                        return false;
                                                    }
                                                }
                                                else {
                                                    navigator.notification.alert(
                                                    		pppleasntetreetress_food,
                                                                                 alertDismissed,
                                                                                 bblankfield_food,
                                                                                 okkk_food
                                                                                 );
                                                    setTimeout(function(){$('.appypie-loader').hide();},1000);
                                                    return false;
                                                }
                                            }
                                            else {
                                                navigator.notification.alert(
                                                		ppppleasnteridhonember_food,
                                                                             alertDismissed,
                                                                             iiiiinvalihoneumber_food,
                                                                             okkk_food
                                                                             );
                                                setTimeout(function(){$('.appypie-loader').hide();},1000);
                                                return false;
                                            }
                                        }
                                        else {
                                            navigator.notification.alert(
                                            		ppleaseenterfirsname_food,
                                                                         alertDismissed,
                                                                         bblankfield_food,
                                                                         okkk_food
                                                                         );
                                            setTimeout(function(){$('.appypie-loader').hide();},1000);
                                            return false;
                                        }
                                    }
                                    else {
                                        if(openedView == 'food')
                                        {
                                            
                                            if(sessionStorage.getItem("foodDeliveryDist"))
                                            {
                                                var addForLatLong=bsAddress+'+'+bCity+'+'+bState+'+'+bCountry;
                                                
                                                if(localStorage.getItem('calculateNearByInUnits') == 'kms')
                                                {
                                                    var units='metric';
                                                }
                                                else
                                                {
                                                    var units='imperial';
                                                }
                                                
                                                
                                                var destinations=sessionStorage.getItem("storeAdd");
												destinations = encodeURI(destinations);
                                                addForLatLong = encodeURI(addForLatLong);
                                                $.ajax({
                                                       url: 'https://maps.googleapis.com/maps/api/distancematrix/json?origins='+addForLatLong.replace(' ','+')+'&destinations='+destinations.replace(' ','+')+'&units='+units+'&mode=DRIVING&key=AIzaSyAUlshWWtBduQdUrTSA9VMThhWfGk3Hm9A',
                                                       dataType: "text",
                                                       crossDomain: true,
                                                       cache: false,
                                                       success: function(xml) {
                                                       var response=jQuery.parseJSON(xml);
                                                       var results = response.rows[0].elements;
                                                       var element = results[0];
                                                       if(element.status=="OK")
                                                       {
                                                       var distance = element.distance.text;
                                                       if(parseFloat(distance) > parseFloat(sessionStorage.getItem("foodDeliveryDist")) && parseFloat(distance))
                                                       {
                                                       navigator.notification.alert(
                                                    		   dddddeliveryiotavailableinyourlocation_food,
                                                                                    alertDismissed,
                                                                                    ssorry_food,
                                                                                    okkk_food
                                                                                    );
                                                       setTimeout(function(){$('.appypie-loader').hide();},1000);
                                                       return false;
                                                       }
                                                       else
                                                       {
                                                       payAfterValidate(openedView);
                                                       }
                                                       }
                                                       else
                                                       {
                                                       navigator.notification.alert(
                                                    		   dddddeliveryiotavailableinyourlocation_food,
                                                                                    alertDismissed,
                                                                                    ssorry_food,
                                                                                    okkk_food
                                                                                    );
                                                       setTimeout(function(){$('.appypie-loader').hide();},1000);
                                                       return false;
                                                       
                                                       }
                                                       },
                                                       error: function(XMLHttpRequest, textStatus, errorThrown) {
                                                       setTimeout(function(){$('.appypie-loader').hide();},1000);
                                                       console.log('fail');
                                                       console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
                                                       navigator.notification.alert(
                                                    		   dddddeliveryiotavailableinyourlocation_food,
                                                                                    alertDismissed,
                                                                                    ssorry_food,
                                                                                    okkk_food
                                                                                    );
                                                       setTimeout(function(){$('.appypie-loader').hide();},1000);
                                                       return false;
                                                       }
                                                       });
                                                
                                            }
                                            else
                                            {
                                                payAfterValidate(openedView);
                                            }
                                        }
                                        else
                                        {
                                            payAfterValidate(openedView);
                                        }
                                    }
                                }
                                else {
                                	
                                    navigator.notification.alert(
                                    		ppleasnterountry_food,
                                                                 alertDismissed,
                                                                 bblankfield_food,
                                                                 okkk_food
                                                                 );
                                    setTimeout(function(){$('.appypie-loader').hide();},1000);
                                    return false;
                                }
                            }
                            else {
                                navigator.notification.alert(
                                		eenteipostalode_food,
                                                             alertDismissed,
                                                             bblankfield_food,
                                                             okkk_food
                                                             );
                                setTimeout(function(){$('.appypie-loader').hide();},1000);
                                return false;
                            }
                        }
                        else {
                            navigator.notification.alert(
                            		pppllleasenterterovince_food,
                                                         alertDismissed,
                                                         bblankfield_food,
                                                         okkk_food
                                                         );
                            setTimeout(function(){$('.appypie-loader').hide();},1000);
                            return false;
                        }
                    }
                    else {
                        navigator.notification.alert(
                        		pplleasenterourity_food,
                                                     alertDismissed,
                                                     bblankfield_food,
                                                     okkk_food
                                                     );
                        setTimeout(function(){$('.appypie-loader').hide();},1000);
                        return false;
                    }
                }
                else {
                    navigator.notification.alert(
                    		pppleasntetreetress_food,
                                                 alertDismissed,
                                                 bblankfield_food,
                                                 okkk_food
                                                 );
                    setTimeout(function(){$('.appypie-loader').hide();},1000);
                    return false;
                }
            }
            else {
            	
                navigator.notification.alert(
                		ppleasenterlidailddress_food,
                                             alertDismissed,
                                             bblankfield_food,
                                             okkk_food
                                             );
                setTimeout(function(){$('.appypie-loader').hide();},1000);
                return false;
            }
        }
        else {
            navigator.notification.alert(
            		ppppleasnteridhonember_food,
                                         alertDismissed,
                                         iiiiinvalihoneumber_food,
                                         okkk_food
                                         );
            setTimeout(function(){$('.appypie-loader').hide();},1000);
            return false;
        }
    }
    else {
    	
    	
        navigator.notification.alert(
        		ppleasnterrstame_food,
                                     alertDismissed,
                                     bblankfield_food,
                                     okkk_food
                                     );
        setTimeout(function(){$('.appypie-loader').hide();},1000);
        return false;
    }
}
function payAfterValidate(value){
    if(checkNetworkConnection())
    {
        if(value=="food")
        {
            addOrder();
        }
        else
        {
            addecomOrder();
        }
    }
}
function addOrder()
{
    
    var bfname=document.getElementById("bfname").value;
    var bpNo=document.getElementById("bpNo").value;
    var bemail=document.getElementById("bemail").value;
    window.sessionStorage.setItem("OrderEmailId",bemail);
    var bsAddress=document.getElementById("bsAddress").value;
    var bCity=document.getElementById("bCity").value;
    var bState=document.getElementById("bState").value;
    var bZip=document.getElementById("bZip").value;
    var bCountry=document.getElementById("bCountry").value;
    
    
    var sfname=document.getElementById("sfname").value;
    var spNo=document.getElementById("spNo").value;
    var ssAddress=document.getElementById("ssAddress").value;
    var sCity=document.getElementById("sCity").value;
    var sState=document.getElementById("sState").value;
    var sZip=document.getElementById("sZip").value;
    var sCountry=document.getElementById("sCountry").value;
    
    var dileveryoption=document.getElementById("pickUpAddress").value
    dileveryoption = dileveryoption.replace(/(\r\n|\n|\r)/gm,"");
    
    if(window.sessionStorage.getItem("cart") )
    {
        cart=window.sessionStorage.getItem("cart");
        console.log('Cart====>'+cart);
        var order='';
        html='';
        html+='[';
        order+='[';
        var counter=0;
        for (var x=1; x<=parseInt(cart); x++)
        {
            console.log("1 -->"+x);
            var qty=window.sessionStorage.getItem("quantity"+x);
            console.log("2 -->"+x);
            var productId=window.sessionStorage.getItem("productId"+x);
            console.log("3-->"+x);
            var productName=window.sessionStorage.getItem("productName"+x);
            console.log("4-->"+productName);
            var price=sessionStorage.getItem("price"+x);
            console.log("5-->"+x);
            var status=sessionStorage.getItem("status"+x);
            console.log("6-->"+x);
            if(status=="1")
            {
                if(counter>0)
                {
                    order+=',';
                    html+=',';
                }
                var configTitle=sessionStorage.getItem("foodconfigTitleProduct"+x);
                var configTitle1=configTitle.split(',');
                var sendData= sessionStorage.getItem("foodconfigValueProduct"+x);
                var sendData1=sendData.split(',');
                var coustomOption='';
                for(var i=0;i<sendData1.length;i++)
                {
                    coustomOption+=configTitle1[i]+':'+sendData1[i];
                    if((i+1) != sendData1.length)
                    {
                        coustomOption+='|';
                    }
                }
                
                if(coustomOption.length > 2)
                {
                    html+='{"productId":"'+productId+'","qty":"'+qty+'","custom_option":"'+coustomOption+'","price":"'+price+'"}';
                }
                else
                {
                    html+='{"productId":"'+productId+'","qty":"'+qty+'","custom_option":""}';
                }
                order+='{"name":"'+productName+'","qty":"'+qty+'","price":"'+price+'","sku":"'+productId+'"}'
                counter=counter+1;
            }
        }
        console.log("1");
        order+=']'
        html+=']';
    }
    
    var billingAddress = '{"name":"'+bfname+'","email":"'+bemail+'","phone":"'+bpNo+'","line1":"'+bsAddress+'","city":"'+bCity+'","countryCode":"'+bCountry+'","postalCode":"'+bZip+'","state":"'+bState+'"}';
    sessionStorage.setItem("LastNamePayment",bfname);
    sessionStorage.setItem("billingAddressPayment",billingAddress);
    sessionStorage.setItem("OrderPayment",order);
    
    var billing='{"billShip":"Billing","name":"'+bfname+'","address":"'+bsAddress+'","city":"'+bCity+'","state":"'+bState+'","country":"'+bCountry+'","zip":"'+bZip+'","phone":"'+bpNo+'","deliveryFrom":"'+dileveryoption+'"}';
    
    var shipping='';
    if(document.getElementById("show_billing_address").checked)
    {
        shipping='{"billShip":"Shipping","name":"'+sfname  +'","address":"'+ssAddress+'","city":"'+sCity+'","state":"'+sState+'","country":"'+sCountry+'","zip":"'+sZip+'","phone":"'+spNo+'"}';
    }
    else
    {
        shipping='{"billShip":"Shipping","name":"'+bfname  +'","address":"'+bsAddress+'","city":"'+bCity+'","state":"'+bState+'","country":"'+bCountry+'","zip":"'+bZip+'","phone":"'+bpNo+'"}';
    }
    
    console.log("bfname--->"+bfname);
    if(bfname.indexOf(' '))
    {
        var blfname=bfname.split(' ');
        console.log("bfname--->1"+bfname);
        var blname='';
        console.log("bfname--->2"+bfname);
        if(blfname[1] != null)
        {
            blname=blfname[1];
        }
    }
    else
    {
        var blname='';
    }
    console.log("blname--->"+blname);
    var paypalAddFormHtml='<!-- Specify a Buy Now button. -->\
    <input type="hidden" name="cmd" value="_xclick">\
    <input type="hidden" name="first_name" value="'+bfname+'" >\
    <input type="hidden" name="last_name" value="'+blname+'" >\
    <input type="hidden" name="address1" value="'+bsAddress+'" >\
    <input type="hidden" name="city" value="'+bCity+'">\
    <input type="hidden" name="state" value="'+bState+'">\
    <input type="hidden" name="zip" value="'+bZip+'">\
    <input type="hidden" name="country" value="'+bCountry+'">\
    <input type="hidden" name="night_phone_a" value="'+bpNo+'">\
    <input type="hidden" name="night_phone_b" value="{MOB2}"><input type="hidden" name="night_phone_c" value="{MOB3}">\
    <input type="hidden" name="email" value="'+bemail+'">\
    <!-- Specify details about the item that buyers will purchase. -->\
    <input type="hidden" name="item_name" value="'+pppaymentorderfor_food+' '+localStorage.getItem("AppName")+'('+localStorage.getItem("applicationID")+')">\
    <input type="hidden" name="amount" value="'+parseFloat(window.sessionStorage.getItem("gtotal1")).toFixed(2)+'">\
    <input type="hidden" name="quantity" value="1">\
    <input type="hidden" name="currency_code" value="'+sessionStorage.getItem("currency")+'">\
    <input name=Õ¢nÔ value=Õ�ppyPie_SPÔ type=Ô hiddenÓ¾\
    <input type="hidden" name="custom" value="1">';
    
    //alert(paypalAddFormHtml);
    console.log('paypalAddFormHtml');
    sessionStorage.setItem('paypalFormHtml',paypalAddFormHtml);
    
    console.log("4");
    console.log(html);
    console.log(billing);
    console.log(shipping);
    
    sessionStorage.setItem("udetails",html);
    sessionStorage.setItem("ubilling",billing);
    sessionStorage.setItem("ushipping",shipping);
    paymentTypeView("food");
}

function OrderNow()
{
    $("#Paycashatthedoor").attr('checked', true);
    $("#paypal").hide();
    $("#cash_delivery").show();
    $("#pickUp").hide();
    $("#paypal_phone").hide();
    $("#paypal_express").hide();
    $("#payu_express").hide();
}

function paymentnow()
{
    $("#Paywithacreditcard").attr('checked',true);
    $("#cash_delivery").hide();
    $("#paypal").show();
    $("#pickUp").hide();
    $("#paypal_phone").hide();
    $("#paypal_express").hide();
    $("#payu_express").hide();
}

function pickupNow()
{
    $("#payment_Type_pickUp").attr('checked', true);
    $("#cash_delivery").hide();
    $("#paypal").hide();
    $("#pickUp").show();
    $("#paypal_phone").hide();
    $("#paypal_express").hide();
     $("#payu_express").hide()}

function OrderNowPayu()
{
    $("#paypal_express").hide();
    $("#payu_express").show();
    $("#paypal").hide();
    $("#cash_delivery").hide();
    $("#pickUp").hide();
    $("#paypal_phone").hide();
    $('#payuExpress').attr('checked', true);
}
function OrderNowPaypal()
{
    $("#paypal_express").show();
    $("#paypal").hide();
    $("#cash_delivery").hide();
    $("#pickUp").hide();
    $("#paypal_phone").hide();
     $("#payu_express").hide();
    $('#paypalExpress').attr('checked', true);
}
function OrderNowPhone()
{
    $('#paypalExpress').attr('checked', true);
    $("#paypal_express").hide();
    $("#paypal").hide();
    $("#cash_delivery").hide();
    $("#pickUp").hide();
    $("#paypal_phone").show();
     $("#payu_express").hide();
}
function checkPaymentType(labelText)
{
			$("#eOrderCard").hide();
			$("#eOrderCod").hide();
			$("#eOrderPaypal").hide();
			$("#eOrderPhone").hide();
			$("#eOrderPayu").hide();
			
			$("#payCard").hide();
			$("#payCashOnD").hide();
			$("#payPaypal").hide();
			$("#payPayu").hide();
			$("#payPhoneCall").hide();
			$("#payBtn").show();
			
    console.log("labelText: "+ labelText);
	var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/ecomm-soap#ecomPaymentMethod";
    
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecomPaymentMethod xmlns=\"http://'+localStorage.getItem("reseller")+'/services/ecomm-soap#ecomPaymentMethod\"><appId>'+appid+'</appId></ecomPaymentMethod></soap:Body></soap:Envelope>';
    
    console.log("PayButtonEcom -->>soapRequest=--->>>"+soapRequest);
    
    
    var paymentLebelType=new Array();
    var paymentLebelIndex=new Array();
    var paymentLebelValue=new Array();
    
    paymentLebelType=labelText.split("##");
    
    for(var t=0;t<paymentLebelType.length ;t++)
    	{
    	
    	paymentLebelIndex=paymentLebelType[t].split("|");
		console.log("paymentLebelIndex: "+ paymentLebelIndex);
    	if(paymentLebelIndex[0]=="cod")
    		{
    		
    			if(paymentLebelIndex[1])
    				{
    				
    			//document.getElementById("payCashAtDoorID").innerHTML = paymentLebelIndex[1];
				console.log("Lab 1: "+ paymentLebelIndex[1]);
				window.sessionStorage.setItem("cod", paymentLebelIndex[1]);
				$("#eOrderCod").show();
    				
    				}
    			else{
    				$("#eOrderCod").hide();
    			//document.getElementById("payCashAtDoorID").innerHTML = "Pay cash at the door";
    				
    			}
    		
    		}
    	else if(paymentLebelIndex[0]=="paypal")
			{
			
				if(paymentLebelIndex[1])
					{
					
			//document.getElementById("paypalExpressID").innerHTML =paymentLebelIndex[1];
					console.log("Lab 2: "+ paymentLebelIndex[1]);
					window.sessionStorage.setItem("paypal", paymentLebelIndex[1]);
					$("#eOrderPaypal").show();
					}
				else{
    				$("#eOrderPaypal").hide();
					//document.getElementById("paypalExpressID").innerHTML ="Paypal Express";
    				
    			}
			
			}
			else if(paymentLebelIndex[0]=="payu_money")
                        {

            				if(paymentLebelIndex[1])
            				{
            					console.log("Tab payu: "+ paymentLebelIndex[1]);
            					window.sessionStorage.setItem("payu_money", paymentLebelIndex[1]);
            					$("#eOrderPayu").show();
            					getPaymentTypePaypal();
            				}
            				else{
            					$("#eOrderPayu").hide();
            					//document.getElementById("paypalExpressID").innerHTML ="Paypal Express";

            				}

                        }
    	else if(paymentLebelIndex[0]=="cc")
		{
		
			if(paymentLebelIndex[1])
				{
				
				//document.getElementById("ccDisplay").innerHTML = paymentLebelIndex[1];
				console.log("Lab 3: "+ paymentLebelIndex[1]);
				window.sessionStorage.setItem("cc", paymentLebelIndex[1]);
				$("#eOrderCard").show();
				}
			else{
				$("#eOrderCard").hide();
				//document.getElementById("ccDisplay").innerHTML = "Pay with a credit card";
			}
		
		}
    	else if(paymentLebelIndex[0]=="pu")
		{
		
			if(paymentLebelIndex[1])
				{
				
				//document.getElementById("pickUpDisplay").innerHTML = paymentLebelIndex[1];
				console.log("Lab 4: "+ paymentLebelIndex[1]);
				
				}
			else{
				
				//document.getElementById("pickUpDisplay").innerHTML = "Pick Up";
			}
		
		}
    	else if(paymentLebelIndex[0]=="cc_phone")
		{
		
			if(paymentLebelIndex[1])
				{
				
				//document.getElementById("obp").innerHTML = paymentLebelIndex[1];
				console.log("Lab 5: "+ paymentLebelIndex[1]);
				window.sessionStorage.setItem("cc_phone", paymentLebelIndex[1]);
				$("#eOrderPhone").show();
				}
			else
				{
				$("#eOrderPhone").hide();
				//document.getElementById("obp").innerHTML = "Order by Phone";
				}
		
		}
    	
    	}
		
    
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON=$(req.responseText).find("return").text();
           console.log("PaymentTypeecom: "+strJSON);
           var responseData=strJSON.split(",");
           console.log("PaymentTypeecom()----->>strJSON---->>"+responseData[0]+responseData[1]+responseData[2]+responseData[3]);
		   console.log("responseDataVijay: "+ responseData);
		   console.log("responseDataLen: "+ responseData.length);
		   
			
			console.log("lenVijay: "+ responseData.length);
		if(parseInt(responseData.length) == 1) {
			   console.log("enterIf:Vijay " +responseData[0]);
			  if(responseData[0] == 'cc'){
				console.log("in cc condition "+ i);
				$("#eOrderCard").hide();
				$("#eOrderCod").hide();
				$("#eOrderPaypal").hide();
				$("#eOrderPhone").hide();
				$("#eOrderPayu").hide();
				
				$("#payCard").show();
				$("#payPayu").hide();
				$("#payBtn").show();
				$("#payCashOnD").hide();
				$("#payPaypal").hide();
				$("#payPhoneCall").hide();
				}
			  else if(responseData[0] == 'cod'){
				console.log("in cod condition "+ i);
				$("#eOrderCod").hide();
				$("#eOrderPaypal").hide();
				$("#eOrderPhone").hide();
				$("#eOrderCard").hide();
                $("#eOrderPayu").hide();

                $("#payPayu").hide();
				$("#payBtn").hide();
				$("#payCard").hide();
				$("#payCashOnD").show();
				$("#payPaypal").hide();
				$("#payPhoneCall").hide();				
				}
			  else if(responseData[0] == 'paypal'){
				console.log("in paypal condition "+ i);
				$("#eOrderPaypal").hide();
				$("#eOrderPhone").hide();
				$("#eOrderCard").hide();
				$("#eOrderCod").hide();
				
				$("#eOrderPayu").hide();

                $("#payPayu").hide();
				$("#payCard").hide();
				$("#payCashOnD").hide();
				$("#payPaypal").show();
				$("#payPhoneCall").hide();
				}
				else if(responseData[0] == 'payu_money')
				{
                  console.log("in payu condition "+ i);
                  $("#eOrderPaypal").hide();
                  $("#eOrderPhone").hide();
                  $("#eOrderCard").hide();
                  $("#eOrderCod").hide();
                  $("#eOrderPayu").hide();

                  $("#payPayu").show();
                  $("#payCard").hide();
                  $("#payCashOnD").hide();
                 $("#payPaypal").hide();
                 $("#payPhoneCall").hide();
                 	}
			  else if(responseData[0] == 'cc_phone'){
				console.log("in cc_phone condition "+ i);
				$("#eOrderPhone").hide();
				$("#eOrderCard").hide();
				$("#eOrderCod").hide();
				$("#eOrderPaypal").hide();
				getCCPhoneNo('ecom');
				$("#eOrderPayu").hide();

                $("#payPayu").hide();
				$("#payCard").hide();
				$("#payCashOnD").hide();
				$("#payPaypal").hide();
				$("#payPhoneCall").show();
				}
			   } 
	else if(responseData.length > 1)	{	
           for(i=0;i<responseData.length;i++)
           {
			   console.log("enterelse:Vijay");
			  if(responseData[i] == 'cc'){
				console.log("in cc condition "+ i);
				$("#eOrderCard").show();
				$("#payCard").show();
				$("#payBtn").show();
				
				}
			  else if(responseData[i] == 'cod'){
				console.log("in cod condition "+ i);
				$("#eOrderCod").show();
				//$("#payBtn").hide();
				//$("#payCashOnD").show();				
				
				}
			  else if(responseData[i] == 'paypal'){
				console.log("in paypal condition "+ i);
				$("#eOrderPaypal").show();
				//$("#payBtn").hide();
				//$("#payPaypal").show();
				
				}
				else if(responseData[i] == 'payu_money'){
                console.log("in Payu condition "+ i);
                 $("#eOrderPayu").show();
                }
			  else if(responseData[i] == 'cc_phone'){
				console.log("in cc_phone condition "+ i);
				$("#eOrderPhone").show();
				//$("#payBtn").hide();
				//$("#payPhoneCall").show();
				
				getCCPhoneNo('ecom');
				}
           
           }
		   }
           
           $('.appypie-loader').hide();
           $.mobile.changePage('#page7', { transition: 'slidefade',allowSamePageTransition: false});
           },
           error: function(response, textStatus, errorThrown)
           {
           $("#contentHolder6").empty();
           $('.appypie-loader').hide();
           console.log(response);
           }
           });
    
    
}

function checkPaymentTypeFood(labelText)
{
    var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodPaymentMethod";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodPaymentMethod xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodPaymentMethod\"><appId>'+appid+'</appId></foodPaymentMethod></soap:Body></soap:Envelope>';
    
    console.log("PayButtonEcom ----->>  soapRequest=--->>>"+soapRequest);
    
    var paymentLebelType=new Array();
    var paymentLebelIndex=new Array();
    var paymentLebelValue=new Array();
    
    paymentLebelType=labelText.split("##");
    
    for(var t=0;t<paymentLebelType.length ;t++)
	{
	
	paymentLebelIndex=paymentLebelType[t].split("|");
	if(paymentLebelIndex[0]=="cod")
		{
		
			if(paymentLebelIndex[1])
				{
				
				document.getElementById("payCashAtDoorID").innerHTML = paymentLebelIndex[1];
				
				}
			else{
				document.getElementById("payCashAtDoorID").innerHTML = pppaycashatdoor_food;
				
			}
		
		}
	else if(paymentLebelIndex[0]=="paypal")
		{
		
			if(paymentLebelIndex[1])
				{
				
				document.getElementById("paypalExpressID").innerHTML =paymentLebelIndex[1];
				
				}
			else{
				
				document.getElementById("paypalExpressID").innerHTML =ppaypalexpress_food;
				
			}
		
		}
	else if(paymentLebelIndex[0]=="payu_money")
        		{

        			if(paymentLebelIndex[1])
        				{

        				document.getElementById("payuExpressID").innerHTML =paymentLebelIndex[1];

        				}
        			else{

        				document.getElementById("payuExpressID").innerHTML ="PayU Money";

        			}

        		}
	else if(paymentLebelIndex[0]=="cc")
	{
	
		if(paymentLebelIndex[1])
			{
			
			document.getElementById("ccDisplay").innerHTML = paymentLebelIndex[1];
			
			}
		else{
			
			document.getElementById("ccDisplay").innerHTML = ppawithareditcard_food;
		}
	
	}
	else if(paymentLebelIndex[0]=="pu")
	{
	
		if(paymentLebelIndex[1])
			{
			
			//document.getElementById("pickUpDisplay").innerHTML = paymentLebelIndex[1];
			
			}
		else{
			
			//document.getElementById("pickUpDisplay").innerHTML = "Pick Up";
		}
	
	}
	else if(paymentLebelIndex[0]=="cc_phone")
	{
	
		if(paymentLebelIndex[1])
			{
			
			document.getElementById("obp").innerHTML = paymentLebelIndex[1];
			
			}
		else
			{
			
			document.getElementById("obp").innerHTML = oorderbyhone_food;
			}
	
	}
	
	}
    
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON=$(req.responseText).find("return").text();
           var responseData=strJSON.split(",");
           if(typeof responseData[1] =='undefined')
           {
           $("#paypal").slideUp();
           $("#cash_delivery").slideUp();
           $("#pickUp").slideUp();
           $("#paypal_express").slideUp();
           $("#paypal_phone").slideUp();
            $("#payu_express").slideUp();
           if(strJSON == 'cod')
           {
           console.log("strJSON cod --->>>"+strJSON);
           document.getElementById("paypalDisplay").style.display="none";
          // document.getElementById("pickUpDisplay").style.display="none";
           document.getElementById("paypalExpressDisplay").style.display="none";
           document.getElementById("paypalPhoneDisplay").style.display="none";
           document.getElementById("payuExpressDisplay").style.display="none";
           document.getElementById("cashDisplay").style.display="block";
           $('#Paycashatthedoor').attr('checked', true);
           $("#paypal").slideUp();
           $("#payu_express").slideUp();
           $("#cash_delivery").slideDown();
           $("#pickUp").slideUp();
           $("#paypal_express").slideUp();
           $("#paypal_phone").slideUp();
           }
           else if(strJSON == 'cc_phone')
           {
           document.getElementById("paypalDisplay").style.display="none";
          // document.getElementById("pickUpDisplay").style.display="none";
           document.getElementById("paypalExpressDisplay").style.display="none";
           document.getElementById("cashDisplay").style.display="none";
           document.getElementById("paypalPhoneDisplay").style.display="block";
           document.getElementById("payuExpressDisplay").style.display="none";
           
           $('#paypalPhone').attr('checked', true);
           $("#paypal_phone").slideDown();
           getCCPhoneNo('food');
           $("#paypal").slideUp();
           $("#cash_delivery").slideUp();
           $("#pickUp").slideUp();
           $("#paypal_express").slideUp();
           $("#paypal_phone").slideDown();
            $("#payu_express").slideUp();
           }
           
           else if(strJSON == 'cc')
           {
           document.getElementById("paypalDisplay").style.display="block";
          // document.getElementById("pickUpDisplay").style.display="none";
           document.getElementById("paypalExpressDisplay").style.display="none";
           document.getElementById("cashDisplay").style.display="none";
           document.getElementById("paypalPhoneDisplay").style.display="none";
           document.getElementById("payuExpressDisplay").style.display="none";
           $('#Paywithacreditcard').attr('checked', true);
           $("#paypal").slideDown();
           
           }
           else if(strJSON=='paypal')
           {
           document.getElementById("paypalExpressDisplay").style.display="block";
           document.getElementById("paypalDisplay").style.display="none";
           document.getElementById("cashDisplay").style.display="none";
          // document.getElementById("pickUpDisplay").style.display="none";
           document.getElementById("paypalPhoneDisplay").style.display="none";
           document.getElementById("payuExpressDisplay").style.display="none";
           $('#paypalExpress').attr('checked', true);
           $("#paypal_express").slideDown();
           getPaymentTypePaypal();
           }
            else if(strJSON=='payu_money')
             {
                document.getElementById("payuExpressDisplay").style.display="block";
                document.getElementById("paypalExpressDisplay").style.display="none";
                document.getElementById("paypalDisplay").style.display="none";
                document.getElementById("cashDisplay").style.display="none";
               // document.getElementById("pickUpDisplay").style.display="none";
                document.getElementById("paypalPhoneDisplay").style.display="none";
                $('#payuExpress').attr('checked', true);
                $("#payu_express").slideDown();
                getPaymentTypePaypal();
             }
           else
           {
           console.log("strJSON cc --->>>"+strJSON);
           document.getElementById("paypalDisplay").style.display="none";
           document.getElementById("cashDisplay").style.display="none";
           document.getElementById("paypalExpressDisplay").style.display="none";
           document.getElementById("paypalPhoneDisplay").style.display="none";
           //document.getElementById("pickUpDisplay").style.display="none";
           document.getElementById("payuExpressDisplay").style.display="none";
           $('#payment_Type_pickUp').attr('checked', true);
           $("#pickUp").slideDown();
           $("#paypal").slideUp();
           $("#cash_delivery").slideUp();
           $("#paypal_express").slideUp();
           $("#paypal_phone").slideUp();
           $("#payu_express").slideUp();
           }
           }
           else
           {
           document.getElementById("paypalDisplay").style.display="none";
           document.getElementById("cashDisplay").style.display="none";
          // document.getElementById("pickUpDisplay").style.display="none";
           document.getElementById("paypalExpressDisplay").style.display="none";
           document.getElementById("paypalPhoneDisplay").style.display="none";
           document.getElementById("payuExpressDisplay").style.display="none";
           console.log("in else condition--->"+responseData.length+'   --strjson--->'+strJSON);
           
           for(i=0;i<responseData.length;i++)
           {
           
           if(responseData[i] == 'cc')
           {
           document.getElementById("paypalDisplay").style.display="block";
           if(i==0)
           {
           $('#Paywithacreditcard').attr('checked', true);
           $("#paypal").slideDown();
           $("#cash_delivery").slideUp();
           $("#pickUp").slideUp();
           $("#paypal_express").slideUp();
           $("#paypal_phone").slideUp();
           $("#payu_express").slideUp();
           }
           }
           else if(responseData[i] == 'cc_phone')
           {
           
           document.getElementById("paypalPhoneDisplay").style.display="block";
           if(i==0)
           {
           $('#paypalPhone').attr('checked', true);
           $("#paypal").slideUp();
           $("#cash_delivery").slideUp();
           $("#pickUp").slideUp();
           $("#paypal_express").slideUp();
           $("#payu_express").slideUp();
           $("#paypal_phone").slideDown();
           }
           
           getCCPhoneNo('food');
           }
           else if(responseData[i] == 'cod')
           {
           document.getElementById("cashDisplay").style.display="block";
           $('#Paycashatthedoor').attr('checked', true);
           if(i==0)
           {
           $("#paypal").slideUp();
           $("#cash_delivery").slideDown();
           $("#pickUp").slideUp();
           $("#paypal_express").slideUp();
           $("#paypal_phone").slideUp();
           $("#payu_express").slideUp();
           }
           
           console.log("in else condition--->"+responseData[i]+'   --strjson--->'+strJSON);
           }
           else if(responseData[i] == 'pu')
           {
          // document.getElementById("pickUpDisplay").style.display="block";
           $('#pickUp').attr('checked', true);
           if(i==0)
           {
           
           $("#pickUp").slideDown();
           $("#paypal").slideUp();
           $("#cash_delivery").slideUp();
           $("#paypal_express").slideUp();
           $("#paypal_phone").slideUp();
           $("#payu_express").slideUp();
           }
           console.log("in else condition--->"+responseData[i]+'   --strjson--->'+strJSON);
           }
           
           else if(responseData[i] == 'paypal')
           {
           getPaymentTypePaypal();
           document.getElementById("paypalExpressDisplay").style.display="block";
           if(i==0)
           {
           $('#paypalExpress').attr('checked', true);
           $("#paypal").slideUp();
           $("#cash_delivery").slideUp();
           $("#pickUp").slideUp();
           $("#paypal_express").slideDown();
           $("#paypal_phone").slideUp();
           $("#payu_express").slideUp();
           }
           }
           else if(responseData[i] == 'payu_money')
           {
           	 getPaymentTypePaypal();
           	 document.getElementById("payuExpressDisplay").style.display="block";
           	 if(i==0)
           	 {
           	  $('#payu_express').attr('checked', true);
              $("#paypal").slideUp();
              $("#cash_delivery").slideUp();
           	  $("#pickUp").slideUp();
           	  $("#paypal_express").slideUp();
           	  $("#paypal_phone").slideUp();
           	  $("#payu_express").slideDown();
           	}
           	}
           
           }
           }
           $('.appypie-loader').hide();
           $.mobile.changePage('#page7', { transition: 'slidefade',allowSamePageTransition: false});
           },
           error: function(response, textStatus, errorThrown)
           {
           $('.appypie-loader').hide();
           $("#contentHolder6").empty();
           console.log(response);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
}


function hideProgressSpinnerForPayU()
{
   $('.appypie-loader').hide();
}

function PayPaypalExpress()
{
    
    if(localStorage.getItem("payu")=="payu")
    	{
			localStorage.setItem("payu","");
    		var totalAmount= parseFloat(sessionStorage.getItem("ecomnewgtotal"));
    		totalAmount=totalAmount.toFixed(2);
    		console.log("amount->"+totalAmount);
    		toaster.openPayU(totalAmount, sessionStorage.getItem("foodRandomId"), localStorage.getItem("applicationID"), "FirstName" , "LastName", localStorage.getItem("fooduserid"), "886611448822",localStorage.getItem("payu_key"),localStorage.getItem("payu_salt"));
    		//$('.appypie-loader').hide();
    		return;
    	}


    $('.appypie-loader').show();
	var paypalIdHtml;
	if(localStorage.getItem("owneremail").indexOf("mayankr@onsinteractive.com")!=-1 || localStorage.getItem("owneremail").indexOf("mayankr@onsinteractive.com")!=-1)
	{
	paypalIdHtml= '<!DOCTYPE HTML><html><body onload="ClickButtonPaypal();"><form action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post"><!-- Identify your business so that you can collect the payments. --><input type="hidden" name="business" value="'+sessionStorage.getItem('paypalClientId')+'">';
    
	}
	else
	{
	paypalIdHtml= '<!DOCTYPE HTML><html><body onload="ClickButtonPaypal();"><form action="https://www.paypal.com/cgi-bin/webscr" method="post"><!-- Identify your business so that you can collect the payments. --><input type="hidden" name="business" value="'+sessionStorage.getItem('paypalClientId')+'">';
    }
    var paypalUrlFormHtml='<!-- URL --><input name="cancel_return" type="hidden" value="http://'+localStorage.getItem("reseller")+'/paypalmobile/cancel"><input type="hidden" name="notify_url" value="http://'+localStorage.getItem("reseller")+'/paypalmobile/notify/orderId/'+sessionStorage.getItem("foodRandomId")+'/appId/'+localStorage.getItem("applicationID")+'" /><input type="hidden" name="return" value="http://'+localStorage.getItem("reseller")+'/paypalmobile/success" /><!-- Display the payment button. --><input type="image" src="{URL}/images/subscribe_btn.png" name="submit" id="submit" alt="PayPal - The safer, easier way to pay online!"><img alt="" border="0" src="http://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1"></form><script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script><script>function ClickButtonPaypal(){$(\'#submit\').trigger(\'click\');}</script></body></html>';
    
    console.log("html of paypal--->"+paypalIdHtml+sessionStorage.getItem('paypalFormHtml')+paypalUrlFormHtml);
    
    
    if(localStorage.getItem("payTypeFoodOrEcom")=="food")
    {
        window.location="foodpaypal:"+encodeURI(paypalIdHtml+sessionStorage.getItem('paypalFormHtml')+paypalUrlFormHtml);
        setTimeout(function(){$('.appypie-loader').hide();},1000);
    }
    else
    {
        
        window.location="foodpaypal:"+encodeURI(paypalIdHtml+sessionStorage.getItem('paypalFormHtml')+paypalUrlFormHtml);
        setTimeout(function(){$('.appypie-loader').hide();},1000);
    }
    
    
}
function getCCPhoneNo(value)
{
    console.log("value--->"+value);
    if(value == "food")
    {
        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodCCPhone";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodCCPhone xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodCCPhone\"><appId>'+appid+'</appId></foodCCPhone></soap:Body></soap:Envelope>';
    }
    else
    {
        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/ecomm-soap#ecommCCPhone";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommCCPhone xmlns=\"http://'+localStorage.getItem("reseller")+'/services/ecomm-soap#ecommCCPhone\"><appId>'+appid+'</appId></ecommCCPhone></soap:Body></soap:Envelope>';
    }
    
    console.log("getCCPhoneNo ----->>soapRequest=--->>>"+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           console
           var strJSON=$(req.responseText).find("return").text();
           var obj=jQuery.parseJSON(strJSON);
           
           console.log('getCCPhoneNo()----strJSON--->'+obj.cc_phone_no);
           $('#payThroughCallNumber').empty();
           $('#payThroughCallNumber').append(obj.cc_phone_no);
           $('#payThroughCallText').empty();
           $('#payThroughCallText').append(" "+obj.cc_phone_text);
           sessionStorage.setItem("ccPhoneNo",obj.cc_phone_no);
           
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log("in error--->"+response);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           document.getElementById("paypalExpressDisplay").style.display="none";
           
           }
           });
}
function getPaymentTypePaypal(x,y)
{
      getPaymentTypePayU();
    // getEcomPaymentCredential
    
    if(localStorage.getItem("payTypeFoodOrEcom") == "food")
    {
        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#getFoodPaymentCredential";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getFoodPaymentCredential xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#getFoodPaymentCredential\"><appId>'+appid+'</appId></getFoodPaymentCredential></soap:Body></soap:Envelope>';
    }
    else
    {
        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/ecomm-soap#getEcomPaymentCredential";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getEcomPaymentCredential xmlns=\"http://'+localStorage.getItem("reseller")+'/services/ecomm-soap#getEcomPaymentCredential\"><appId>'+appid+'</appId></getEcomPaymentCredential></soap:Body></soap:Envelope>';
    }
    
    console.log("PayButtonEcom ----->>x-->"+x+"<--y--->"+y+" soapRequest=--->>>"+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON=$(req.responseText).find("return").text();
           console.log('getPaymentTypePaypal()----strJSON--->'+strJSON);
           if(strJSON == "PaypalPro")
           {
           }
           else
           {
           sessionStorage.setItem("paypalTypeButton",'paypalExpress');
           sessionStorage.setItem('paypalClientId',strJSON);
           }
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           document.getElementById("paypalExpressDisplay").style.display="none";
           
           }
           });
}

function getPaymentTypePayU()
{
    console.log("in getPaymentTypePayU method.");
    if(localStorage.getItem("payTypeFoodOrEcom") == "food")
    {
        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#getFoodPaymentCredentialPayu";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getFoodPaymentCredentialPayu xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#getFoodPaymentCredentialPayu\"><appId>'+appid+'</appId></getFoodPaymentCredentialPayu></soap:Body></soap:Envelope>';
    }
    else
    {
        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/ecomm-soap#getEcomPaymentCredentialPayu";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><getEcomPaymentCredentialPayu xmlns=\"http://'+localStorage.getItem("reseller")+'/services/ecomm-soap#getEcomPaymentCredentialPayu\"><appId>'+appid+'</appId></getEcomPaymentCredentialPayu></soap:Body></soap:Envelope>';
    }

    console.log("--getPaymentTypePayU--");
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           var strJSON=$(req.responseText).find("return").text();
           console.log('getPaymentTypePayU()----strJSON--->'+strJSON);
           		if(strJSON != null)
				{
					var payu_keys=strJSON.split("#");
					localStorage.setItem("payu_key",payu_keys[0]);
					localStorage.setItem("payu_salt",payu_keys[1]);
			   	}
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log("getPaymentTypePayU->"+response);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           document.getElementById("payuExpressDisplay").style.display="none";

           }
           });
}
function paymentTypeView(value){
	
	var tmpHeader = '<header class="ui-header headerColor" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
   <a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
   <h1 class="ui-title">'+appName+'</h1>\
   <a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>';
	
	
	
	var paymetType_HTML = '';
    $('.appypie-loader').show();
    var newdate=new Date().getTime();
    var orderId='ap'+newdate;
    sessionStorage.setItem("foodRandomId",orderId);
    localStorage.setItem("payTypeFoodOrEcom",value);
    
    console.log("paymentTypeView--->"+value);
    if(localStorage.getItem("fooduserid"))
    {
        
        accountDispl='block';
        loginDispl='none';
    }
    else
    {
        accountDispl='none';
        loginDispl='block';
    }
    if(value=="food") {
    	
    	
    	
    paymetType_HTML='<div class="page-text"><div class="appypie-login">\
        <div class="paymentcheckoutbnx">\
        <ul id="paypalPhoneDisplay" onclick="OrderNowPhone();"><li><input data-role="none" type="radio" id="paypalPhone" name="method"  data-role="none" />\
        <span id="obp"></span></li><div class="messagecheckout" id="paypal_phone" style="display:none;">\
        <p style="color:#000;">'+you_can_order_by_calling_food+'<a id="payThroughCallNumber" onclick="submitOrderCCPhone(\'1\');"> </a><span id="payThroughCallText"></span>.</p><a '+primaryColor+' onclick="submitOrderCCPhone(\'1\');" class="BtText" id="callToPay">'+ccallnow_food+'</a> </div>\
        </ul>\
         <div class="paymentcheckoutbnx">\
          <ul id="payuExpressDisplay" onclick="OrderNowPayu();"><li><input data-role="none" type="radio" id="payuExpress" name="method"  data-role="none" />\
          <span id="payuExpressID"></span></li><div class="messagecheckout" id="payu_express" style="display:none;">\
           <p style="color:#000;">Please Click on the confirm to pay through PayU</p><a '+primaryColor+' onclick="userRegistrationPayuExpress();" class="BtText">Confirm</a> </div>\
         </ul>\
        <div class="paymentcheckoutbnx">\
        <ul id="paypalExpressDisplay" onclick="OrderNowPaypal();"><li><input data-role="none" type="radio" id="paypalExpress" name="method"  data-role="none" />\
        <span id="paypalExpressID"></span></li><div class="messagecheckout" id="paypal_express" style="display:none;">\
        <p style="color:#000;">'+pppleaseclickontheconfirmtopaythroughcreditcard_food+'</p><a '+primaryColor+' onclick="userRegistrationPaypalExpress();" class="BtText">'+cooonfirm_food+'</a> </div>\
        </ul>\
        <div class="paymentcheckoutbnx"><ul id="cashDisplay" onclick="OrderNow();"><li><input data-role="none" type="radio" id="Paycashatthedoor" name="method"  data-role="none" /><span id="payCashAtDoorID"></span></li><div class="messagecheckout" id="cash_delivery" style="display:none;"><p style="color:#000;">'+ppleaseclickonconfirmbuttonlaceherder_food+'</p><a '+primaryColor+' onclick="submitOrder(\'1\');" class="BtText">'+cooonfirm_food+'</a> </div>\</ul><div class="paymentcheckoutbnx"><ul id="paypalDisplay" onclick="paymentnow();"><li><input data-role="none" type="radio" id="Paywithacreditcard" name="method"  data-role="none"  /><span id="ccDisplay"></span></li><div class="messagecheckout" id="paypal" style="display:none;"><div class="appypie-login"><div class="login-feald" ><label>'+cccardype_food+'</label><select id="card_type" data-role="none" ><option value="visa">'+viisa_food+'</option><option value="mastercard">'+mmastercard_food+'</option><option value="amex">'+amexxx_food+'</option><option value="discover">'+dddiscover_food+'</option></select></div><div class="login-feald"><label>'+caaardnumber_food+'</label><input data-role="none" type="text" id="cnumber" data-role="none"/></div><div class="login-feald"><label>'+exxxpirymonth_food+'</label><select id = "ExpairyMonth" data-role="none"><option value = "01">1</option><option value = "02">2</option><option value = "03">3</option><option value = "04">4</option><option value = "05">5</option><option value = "06">6</option><option value = "07">7</option><option value = "08">8</option><option value = "09">9</option><option value = "10">10</option><option value = "11">11</option><option value = "12">12</option></select><label>'+eeexpiryear_food+'</label><select id = "ExpairyYear" data-role="none"><option value = "2014">2014</option><option value = "2015">2015</option><option value = "2016">2016</option><option value = "2017">2017</option><option value = "2018">2018</option><option value = "2019">2019</option><option value = "2020">2020</option><option value = "2021">2021</option><option value = "2022">2022</option><option value = "2023">2023</option><option value = "2024">2024</option></select></div><div class="login-feald"><label>'+cccccardolder_food+'</label><input data-role="none" type="text" id="cHolder" data-role="none"/></div><div class="login-feald"><label>'+ccardssecuritode_food+'</label><input data-role="none" type="text" id="cvvCode" data-role="none"/></div><div class="cart-update-btn"><br/><a class="BtText" onclick="PayButtonFood();" data-role="none">'+pppppayow_food+'</a></div></ul></div></div></div><ul class="sub-menu" style="display:none;" ><li><a onclick="home()" class="home-nav">'+home_food+'</a></li><li style="display:'+loginDispl+';"><a onclick="loginEcom()" class="login-nav">'+llloginregistration_food+'</a></li><li style="display:'+accountDispl+';"><a onclick="myaccount()" class="account-nav">'+mmyaccount_food+'</a></li><li><a onclick="goHome()" class="menu-nav">'+mysshop_food+'</a></li><li><a onclick="bindCart(\'mainpagefood\')" class="cart-nav">'+cartt_food+'</a></li><li><a onclick="termsEcom()" class="terms-nav">'+termscconditions_food+'</a></li><li><a onclick="PolicyEcom()" class="policy-nav">'+privacypolicy_food+'</a></li></ul>';

    	
    	
		}
    else {
    	
    	
		/*
		comes from ecom
		*/
		var appName = localStorage.getItem("AppName");
        paymetType_HTML = '<header class="ui-header headerColor  ecomHeader" style="color:#ffffff;background-color:#2795c8 !important; "><a onclick="showEcomMenu();" style="display:none" class="localHeaderIcon"><div class="headerIcon" id="localEcomMenu"><i class="icon-menu-1" style="color:#ffffff !important"></i></div></a>\
   <a onclick="goBackFromOrderPageEcom();" class="localHeaderIcon" id="localEcomback" style="display:block"><div class="headerIcon"><i class="icon-left-open" style="color:#ffffff !important"></i></div></a>\
   <h1 class="ui-title">'+appName+'</h1>\
   <a class="localHeaderIconRight" id="localEcomCart" style="display:none"><span class="subValue">'+window.sessionStorage.getItem("ecomcartQuantitywise")+'</span><div class="headerIcon"><i class="icon-cart" style="color:#ffffff !important"></i></div></a></header>\
   <div class="payment-mode">\
	<div class="payment-nav tabs-click" dir="paymentTabs">\
		<a href="#" onclick="checkPaymentTabs(this);" class="card" id="eOrderCard">'+caaaard_food+'</a>\
    	<a href="#" onclick="checkPaymentTabs(this);" class="obp" id="eOrderPhone">'+oorderbyhone_food+'</a>\
    	<a href="#" onclick="checkPaymentTabs(this);" class="payu" id="eOrderPayu">PayU</a>\
    	<a href="#" onclick="checkPaymentTabs(this);" class="paypal" id="eOrderPaypal">'+paaaypal_food+'</a>\
    	<a href="#" onclick="checkPaymentTabs(this);" class="cod" id="eOrderCod">'+cood_food+'</a>\
	</div>\
	<div class="wrap" id="paymentTabs">\
	 <div class="payment-tab tabs-view" id="payCard">\
  		<div class="login-feald" style="display:none">\
            <label>'+cccardype_food+'</label>\
            <select id="card_type" data-role="none" >\
            <option value="visa">'+viisa_food+'</option>\
            <option value="mastercard">'+mmastercard_food+'</option>\
            <option value="amex">'+amexxx_food+'</option>\
            <option value="discover">'+dddiscover_food+'</option>\
            </select>\
        </div>\
        <div class="login-feald">\
        	<label style="display:none">'+caaardnumber_food+'</label>\
            <input data-role="none" type="number" onKeyPress="return ( this.value.length < 16 );" id="cnumber" SIZE=16 MAXLENGTH=16 placeholder="XXXX-XXXX-XXXX-XXXX" class="card-number">\
        </div>\
        <div class="expiry-date">\
        <span>\
		<input type="number" data-role="none" onKeyPress="return ( this.value.length < 2 );"" SIZE=2 MAXLENGTH=2 id="ExpairyMonth" placeholder="MM" >\
        '+exxxpirymonth_food+'\
        </span>\
        <span class="mid">\
        &nbsp;\
        </span>\
         <span>\
         	<input type="number" data-role="none" onKeyPress="return ( this.value.length < 4 );" SIZE=4 MAXLENGTH=4 id="ExpairyYear" placeholder="YYYY">\
        '+eeexpiryear_food+'\
        </span>\
        </div>\
        <div class="login-feald" style="display:none">\
        <label>'+ccardssecuritode_food+'</label>\
        </div>\
		 <div class="cvv">\
        	<input data-role="none" SIZE=3 MAXLENGTH=3 type="number" onKeyPress="return ( this.value.length < 3 );" id="cvvCode" placeholder="'+cvvv_food+'">\
			<p>'+checkthebackoyourcreditcardforcvv_ffod+'</p>\
        </div>\
		<div class="login-feald">\
        <label style="display:none">'+cardholdernaaame_food+'</label>\
		<input data-role="none" type="text" id="cHolder" SIZE=20 MAXLENGTH=20 placeholder="'+cccccardolder_food+'" class="card-number">\
        </div>\
    </div>\
    <div class="payment-tab tabs-view" id="payPhoneCall">\
		<p>'+yyoucanorderycalling_food+'<a id="payThroughCallNumber" onClick="submitOrderCCPhoneEcom(\'1\');"> </a><span id="payThroughCallText"></span></p>\
        <a onClick="submitOrderCCPhoneEcom(\'1\');" class="BtText" id="callToPay">'+ccallnow_food+'</a>\
    </div>\
     <div class="payment-tab tabs-view" id="payPayu">\
        	<p>Please click on the confirm to pay through PayU</p>\
            <a onClick="userRegistrationPayuExpress();" class="BtText">Confirm</a>\
        </div>\
     <div class="payment-tab tabs-view" id="payPaypal">\
		<p>'+ppleaseclickontheconfirmaypal_food+'</p>\
        <a onClick="userRegistrationPaypalExpress();" class="BtText">'+cooonfirm_food+'</a>\
    </div>\
     <div class="payment-tab tabs-view" id="payCashOnD">\
		<p>'+paaayusingashonelivery_food+'</p>\
        <p><a onClick="submitOrder(\'1\');" class="BtText">'+cooonfirm_food+'</a></p>\
    </div>\
    </div>\
	</div><footer id="footer-nav">\
	<a href="#" class="next" id="payBtn" onclick="PayButtonFood()">'+pllaaceorder_food+'</a>\
</footer>';
    	         
       
        
    }
    if(value=="food") {
    	
    	
    	
    	var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodPaymentMethodLabel";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodPaymentMethodLabel xmlns=\"'+localStorage.getItem("reseller")+'/services/food-soap#foodPaymentMethodLabel\"><appId>'+appid+'</appId></foodPaymentMethodLabel></soap:Body></soap:Envelope>';
    	console.log("soapRequestsoapRequestsoapRequestsoapRequest>>>>>>"+soapRequest);
        
        $.ajax({
            type: "POST",
            url: wsUrl,
            contentType: "text/xml",
            dataType: "text",
            data: soapRequest,
            success: function(data, status, req)
            {
            var strJSON=$(req.responseText).find("return").text();
            console.log('getPaymentTypePaypal()----strJSON--->'+strJSON);
            
            
            checkPaymentTypeFood(strJSON);
            },
            error: function(response, textStatus, errorThrown) {
               
		  		   //alert(response);
            	 setTimeout(function(){$('.appypie-loader').hide();},1000);
			
          console.log("Error : " + JSON.stringify(response));
          console.log("Error : " + textStatus);
         console.log("Error : " + errorThrown.responseText);
          }
            });
    }
    else {
    	var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/ecomm-soap#ecomPaymentMethodLabel";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecomPaymentMethodLabel xmlns=\"'+localStorage.getItem("reseller")+'/services/ecomm-soap#ecomPaymentMethodLabel\"><appId>'+appid+'</appId></ecomPaymentMethodLabel></soap:Body></soap:Envelope>';
        console.log("ecomm........."+soapRequest);
        $.ajax({
            type: "POST",
            url: wsUrl,
            contentType: "text/xml",
            dataType: "text",
            data: soapRequest,
            success: function(data, status, req)
            {
            var strJSON=$(req.responseText).find("return").text();
            console.log('getPaymentTypePaypal()----strJSON--->'+strJSON);
            checkPaymentType(strJSON);
            },
            error: function(response, textStatus, errorThrown)
            {
            	 setTimeout(function(){$('.appypie-loader').hide();},1000);
     			
                 console.log("Error : " + JSON.stringify(response));
                 console.log("Error : " + textStatus);
                console.log("Error : " + errorThrown.responseText);
            
            }
            });
    }
    appendHtml(paymetType_HTML,6,6,'food');
	
	if(value=='ecom')
	{
		resetEcomHeader();
	}
}
function PayButtonFood(){
	
	
    $('.appypie-loader').show();
    console.log("::::::::::::::::::::::::::::::::::"+sessionStorage.getItem("payTypeFoodOrEcom"));
    var value=localStorage.getItem("payTypeFoodOrEcom");
    sessionStorage.setItem("paypalTypeButton",'paypalPro');
    if(value=="food"){
        var card_type=document.getElementById("card_type").value;
        var cnumber=document.getElementById("cnumber").value;
        var ExpairyMonth=document.getElementById("ExpairyMonth").value;
        var ExpairyYear=document.getElementById("ExpairyYear").value;
        var cHolder=document.getElementById("cHolder").value;
        var cvvCode=document.getElementById("cvvCode").value;
        
        if(isNaN(cnumber) || cnumber.length < 15)
        {
            navigator.notification.alert(ppleaseentervalidumber_food, setTimeout, errorrr_food, okkk_food);
            setTimeout(function(){$('.appypie-loader').hide();},1000);
        }
        else if(ExpairyMonth == null || ExpairyMonth == '')
        {
            console.log("ExpairyMonth-->>>"+ExpairyMonth);
            navigator.notification.alert(ppleaseentervalidxpairyonth_food, setTimeout, errorrr_food, okkk_food);
            setTimeout(function(){$('.appypie-loader').hide();},1000);
        }
        else if(ExpairyYear == null || ExpairyYear == '')
        {
            console.log("ExpairyYear-->>>"+ExpairyYear);
            navigator.notification.alert(ppleaseentervalidxpairyear_food, setTimeout, errorrr_food, okkk_food);
            setTimeout(function(){$('.appypie-loader').hide();},1000);
        }
        else if(!isNaN(cHolder) || cHolder == null || cHolder == '')
        {
            console.log("cHolder-->>>"+cHolder);
            navigator.notification.alert(ppleaseentervalidardolderame_food, setTimeout, errorrr_food, okkk_food);
            setTimeout(function(){$('.appypie-loader').hide();},1000);
        }
        else if(isNaN(cvvCode) || cvvCode.length < 3)
        {
            console.log("cvvCode-->>>"+cvvCode);
            navigator.notification.alert(pppleaseentervalidode_food, setTimeout, errorrr_food, okkk_food);
            setTimeout(function(){$('.appypie-loader').hide();},1000);
        }
        else
        {
            var cHolder1=cHolder.split(" ");
            var firstName=cHolder1[0];
            var lastName= cHolder1[1];
            
            var totalAmount= parseFloat(window.sessionStorage.getItem("gtotal1")).toFixed(2);
            
            var shippingDetail = '{"shipping":"0.00","tax":"0.00","totalAmount":"'+totalAmount+'","currency":"'+window.sessionStorage.getItem("currency")+'"}';
            
            console.log("shipping detai......"+shippingDetail);
            
            var paymentDetail='{"type":"'+card_type+'","number":"'+cnumber+'","expireMonth":"'+ExpairyMonth+'","expireYear":"'+ExpairyYear+'","cvv2":"'+cvvCode+'","firstName":"'+firstName+'","lastName":"'+lastName+'"}';
            
            console.log("paymentDetail......"+paymentDetail);
            
            var billingAddressPayment=sessionStorage.getItem("billingAddressPayment");
            
            console.log("billingAddressPayment......"+billingAddressPayment);
            var OrderPayment=sessionStorage.getItem("OrderPayment");
            console.log("OrderPayment......"+OrderPayment);
            var shipping=sessionStorage.getItem("ushipping");
            console.log("shipping..........."+shipping);
            var newdate=new Date().getTime();
            var orderId='ap'+newdate;
            window.sessionStorage.setItem("foodRandomId",orderId);
            var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodPaymentRegistrationInfo";
            var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodPaymentRegistrationInfo xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodPaymentRegistrationInfo\"><appId>'+appid+'</appId><orderId>'+orderId+'</orderId><paymentDetail>'+paymentDetail+'</paymentDetail><billingAddress>'+billingAddressPayment+'</billingAddress><shipping>'+shipping+'</shipping><productDetail><![CDATA['+OrderPayment+']]></productDetail><shippingAddress>'+shippingDetail+'</shippingAddress></foodPaymentRegistrationInfo></soap:Body></soap:Envelope>';
            console.log("soapRequest????????????????????????"+soapRequest);
            $.ajax({
                   type: "POST",
                   url: wsUrl,
                   contentType: "text/xml",
                   dataType: "text",
                   data: soapRequest,
                   success: function(data, status, req)
                   {
                   console.log('rrrrr=>'+req.responseText);
                   var strJSON=$(req.responseText).find("return").text();
                   var responseData=strJSON;
                   strJSON=strJSON.split("Payment_Status:");
                   var paymentInfo=strJSON[0].split("_");
                   console.log("hsdfjksdfjsdf"+paymentInfo[1]);
                   if(strJSON[1] == "Success")
                   {
                   console.log('successs payment');
                   submitOrder(strJSON[0]);
                   var completionCallback1 = function(usercredential) {
                   if(usercredential!='') {
                   var usercredential= usercredential.split("/");
                   localStorage.setItem("foodEcomUserId",usercredential[0])
                   }
                   
                   }
                   var cancelCallback1 = function(reason) {
                   }
                   //  window.plugins.nativevaluetojs.fetch({uName:paymentInfo[1],pass:"123",chk:"ecomm",loginorlogout:'login'}, completionCallback1,cancelCallback1);
                   
                   
                   }
                   else
                   {
                   console.log("Error -->"+responseData);
                   submitOrder('3');
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                   // Ext.Viewport.setMasked(false);
                   
                   }
                   },
                   error: function(response, textStatus, errorThrown)
                   {
                   console.log(response.responseText);
                   // Ext.Viewport.setMasked(false);
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                   
                   }
                   });
        }
    }
    else {
        
        var card_type=document.getElementById("card_type").value;
        var cnumber=document.getElementById("cnumber").value;
        var ExpairyMonth=document.getElementById("ExpairyMonth").value;
        var ExpairyYear=document.getElementById("ExpairyYear").value;
        var cHolder=document.getElementById("cHolder").value;
        var cvvCode=document.getElementById("cvvCode").value;
        
        if(isNaN(cnumber) || cnumber.length < 15)
        {
            console.log("cnumber-->>>"+cnumber);
            navigator.notification.alert(ppleaseentervalidumber_food, setTimeout, errorrr_food, okkk_food);
            
            setTimeout(function(){$('.appypie-loader').hide();},1000);
        }
        else if(ExpairyMonth == null || ExpairyMonth == '')
        {
            console.log("ExpairyMonth-->>>"+ExpairyMonth);
            navigator.notification.alert(ppleaseentervalidxpairyonth_food, setTimeout, errorrr_food, okkk_food);
            setTimeout(function(){$('.appypie-loader').hide();},1000);
        }
        else if(ExpairyYear == null || ExpairyYear == '')
        {
            console.log("ExpairyYear-->>>"+ExpairyYear);
            navigator.notification.alert(ppleaseentervalidxpairyear_food, setTimeout, errorrr_food, okkk_food);
            setTimeout(function(){$('.appypie-loader').hide();},1000);
        }
        else if(!isNaN(cHolder) || cHolder == null || cHolder == '')
        {
            console.log("cHolder-->>>"+cHolder);
            navigator.notification.alert(ppleaseentervalidardolderame_food, setTimeout, errorrr_food, okkk_food);
            setTimeout(function(){$('.appypie-loader').hide();},1000);
        }
        else if(isNaN(cvvCode) || cvvCode.length < 3)
        {
            console.log("cvvCode-->>>"+cvvCode);
            navigator.notification.alert(pppleaseentervalidode_food, setTimeout, errorrr_food, okkk_food);
            setTimeout(function(){$('.appypie-loader').hide();},1000);
        }
        else
        {
            var cHolder1=cHolder.split(" ");
            var lastName=  cHolder1[1];
            var firstName=cHolder1[0];
            
            if(lastName == '' || lastName == null)
            {
                lastName='';
            }
            
            //  alert(sessionStorage.getItem("ecomnewgtotal"));
            var totalAmount= parseFloat(sessionStorage.getItem("ecomnewgtotal"));
            //  alert(totalAmount);
            totalAmount=totalAmount.toFixed(2);
            
            var shippingDetail = '{"shipping":"0.00","tax":"0.00","totalAmount":"'+totalAmount+'","currency":"'+sessionStorage.getItem("currency")+'"}';
            console.log(totalAmount);
            console.log(shippingDetail);
            var paymentDetail='{"type":"'+card_type+'","number":"'+cnumber+'","expireMonth":"'+ExpairyMonth+'","expireYear":"'+ExpairyYear+'","cvv2":"'+cvvCode+'","firstName":"'+firstName+'","lastName":"'+lastName+'"}';
            console.log(paymentDetail);
            var billingAddressPayment=sessionStorage.getItem("billingAddressPayment");
            console.log(billingAddressPayment);
            var OrderPayment=sessionStorage.getItem("OrderPayment");
            console.log(OrderPayment);
            var shipping=sessionStorage.getItem("ecomubilling");
            console.log("shipping..........."+shipping);
            var newdate=new Date().getTime();
            var orderId='ap'+newdate;
            window.sessionStorage.setItem("ecomRandomId",orderId);
            
            var wsUrl = "http://"+reseller+"/services/ecomm-soap#ecommPaymentRegistrationInfo";
            var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommPaymentRegistrationInfo xmlns=\"http://'+reseller+'/services/ecomm-soap#ecommPaymentRegistrationInfo\"><appId>'+appid+'</appId><orderId>'+orderId+'</orderId><paymentDetail>'+paymentDetail+'</paymentDetail><billingAddress>'+billingAddressPayment+'</billingAddress><shipping>'+sessionStorage.getItem("ecomushipping")+'</shipping><productDetail><![CDATA['+OrderPayment+']]></productDetail><shippingAddress>'+shippingDetail+'</shippingAddress><transType>authorize</transType></ecommPaymentRegistrationInfo></soap:Body></soap:Envelope>';
            
            console.log("SOAP "+soapRequest);
            $.ajax({
                   type: "POST",
                   url: wsUrl,
                   contentType: "text/xml",
                   dataType: "text",
                   data: soapRequest,
                   success: function(data, status, req)
                   {
                   
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                   var newStr=$(req.responseText).find("return").text();
                   console.log("the new responce------>"+newStr);
                   
                   if(newStr == 'success')
                   {
                   var newsoapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommPaymentRegistrationInfo xmlns=\"http://appypieml.pbodev.info/services/ecomm-soap#ecommPaymentRegistrationInfo\"><appId>'+appid+'</appId><orderId>'+orderId+'</orderId><paymentDetail>'+paymentDetail+'</paymentDetail><billingAddress>'+billingAddressPayment+'</billingAddress><shipping>'+sessionStorage.getItem("ecomushipping")+'</shipping><productDetail><![CDATA['+OrderPayment+']]></productDetail><shippingAddress>'+shippingDetail+'</shippingAddress><transType>sale</transType></ecommPaymentRegistrationInfo></soap:Body></soap:Envelope>';
                   
                   
                   $.ajax({
                          type: "POST",
                          url: wsUrl,
                          contentType: "text/xml",
                          dataType: "text",
                          data: newsoapRequest,
                          success: function(data, status, req)
                          {
                          console.log('rrrrr=>'+req.responseText);
                          var strJSON=$(req.responseText).find("return").text();
                          var responseData=strJSON;
                          strJSON=strJSON.split("Payment_Status:");
                          var paymentInfo=strJSON[0].split("_");
                          console.log("hsdfjksdfjsdf"+paymentInfo[1]);
                          if(strJSON[1] == "Success")
                          {
                          console.log('successs payment');
                          submitOrder(strJSON[0]);
                          
                          setTimeout(function(){$('.appypie-loader').hide();},1000);
                          }
                          else
                          {
                          console.log("Error -->"+responseData);
                          submitOrder('3');
                          //Ext.Viewport.setMasked(false);
                          setTimeout(function(){$('.appypie-loader').hide();},1000);
                          }
                          },
                          error: function(response, textStatus, errorThrown)
                          {
                          console.log(response);
                          //Ext.Viewport.setMasked(false);
                          setTimeout(function(){$('.appypie-loader').hide();},1000);
                          }
                          });
                   }
                   else
                   {
                	 
                   navigator.notification.alert(iiinvalidcardetails_food, setTimeout, innnvalidcard_food, okkk_food);
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                   return false;
                   }
                   
                   
                   },
                   error: function(response, textStatus, errorThrown)
                   {
                   console.log(response);
                   //Ext.Viewport.setMasked(false);
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                   }
                   });
        }
        
    }
    
}

function userRegistrationPayuExpress(){
	localStorage.setItem("payu","payu");
	userRegistrationPaypalExpress();
}

function userRegistrationPaypalExpress()
{
    var paymentDetail='paypal_express';
    
    if(localStorage.getItem("payTypeFoodOrEcom")!="food")
    {
        var totalAmount= parseFloat(sessionStorage.getItem("ecomnewgtotal"));
        totalAmount=totalAmount.toFixed(2);
        var shippingDetail = '{"shipping":"0.00","tax":"0.00","totalAmount":"'+totalAmount+'","currency":"'+window.sessionStorage.getItem("currency")+'"}';
        console.log('userRegistrationPaypalExpress()---->'+totalAmount);
        console.log('userRegistrationPaypalExpress()---->'+shippingDetail);
        console.log('userRegistrationPaypalExpress()---->'+paymentDetail);
        var billingAddressPayment=sessionStorage.getItem("billingAddressPayment");
        console.log('userRegistrationPaypalExpress()---->'+billingAddressPayment);
        var OrderPayment=sessionStorage.getItem("OrderPayment");
        console.log('userRegistrationPaypalExpress()---->'+OrderPayment);
        var shipping=sessionStorage.getItem("ecomubilling");
        console.log("userRegistrationPaypalExpress()----> shipping..........."+shipping);
        
        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/ecomm-soap#ecommPaymentRegistrationInfo";
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommPaymentRegistrationInfo xmlns=\"http://'+localStorage.getItem("reseller")+'/services/ecomm-soap#ecommPaymentRegistrationInfo\"><appId>'+appid+'</appId><orderId>'+sessionStorage.getItem("foodRandomId")+'</orderId><paymentDetail>'+paymentDetail+'</paymentDetail><billingAddress>'+billingAddressPayment+'</billingAddress><shipping>'+sessionStorage.getItem("ecomushipping")+'</shipping><productDetail><![CDATA['+OrderPayment+']]></productDetail><shippingAddress>'+shippingDetail+'</shippingAddress></ecommPaymentRegistrationInfo></soap:Body></soap:Envelope>';
    }
    else
    {
        
        var totalAmount= parseFloat(window.sessionStorage.getItem("gtotal1")).toFixed(2);
        
        var shippingDetail = '{"shipping":"0.00","tax":"0.00","totalAmount":"'+totalAmount+'","currency":"'+window.sessionStorage.getItem("currency")+'"}';
        console.log("shipping detai......"+shippingDetail);
        console.log("paymentDetail......"+paymentDetail);
        var billingAddressPayment=sessionStorage.getItem("billingAddressPayment");
        console.log("billingAddressPayment......"+billingAddressPayment);
        var OrderPayment=sessionStorage.getItem("OrderPayment");
        console.log("OrderPayment......"+OrderPayment);
        var shipping=sessionStorage.getItem("ushipping");
        console.log("shipping..........."+shipping);
        
        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodPaymentRegistrationInfo";
        //var wsUrl = "http://appypieml.onsisdev.info/services/food-soap#foodPaymentRegistrationInfo";
        
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodPaymentRegistrationInfo xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodPaymentRegistrationInfo\"><appId>'+appid+'</appId><orderId>'+sessionStorage.getItem("foodRandomId")+'</orderId><paymentDetail>'+paymentDetail+'</paymentDetail><billingAddress>'+billingAddressPayment+'</billingAddress><shipping>'+shipping+'</shipping><productDetail><![CDATA['+OrderPayment+']]></productDetail><shippingAddress>'+shippingDetail+'</shippingAddress></foodPaymentRegistrationInfo></soap:Body></soap:Envelope>';
        
    }
    console.log("SOAP "+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           submitOrder('12231');
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           //Ext.Viewport.setMasked(false);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    
}

function submitOrderCCPhoneEcom(t)
{
    $('.appypie-loader').show();
    var udetails=sessionStorage.getItem("ecomudetails");
    var billing=sessionStorage.getItem("ecomubilling");
    var shipping=sessionStorage.getItem("ecomushipping");
    
    sessionStorage.setItem("transectionId",t);
    var instructionsText = "";
    try{
    	instructionsText=sessionStorage.getItem('instructions_mcom').text;
    	console.log("instructionsText---->"+instructionsText);
	}catch(err){
		console.log("instructionsText is not Found ...ERROR: "+err);
		instructionsText='';
	}
   // var instructionsText=instructions_mcom;
  /*  console.log("dheeraj text"+instructionsText);
    console.log("dheeraj ecom"+instructions_mcom);
    console.log("dheeraj food"+instructions_food);*/
    try{
	    if(instructionsText.length < 1)
	    {
	        instructionsText='';
	    }
    }catch(err){
    	console.log("instructionsText is not valid ...ERROR: "+err);
    }
    
   
    var paymentMethod;
    var flag=0;
    
    paymentMethod='{"paymentId":"ccPhone","paymentMethod":"ccPhone","paymentStatus":"processing"}';
    var shippingDetail = '{"shipping":"0.00","tax":"0.00","totalAmount":"'+sessionStorage.getItem("ecomnewgtotal")+'","currency":"'+window.sessionStorage.getItem("currency")+'"}';
    
    console.log(shippingDetail);
    var paymentDetail='ccPhone';
    console.log(paymentDetail);
    var billingAddressPayment=sessionStorage.getItem("billingAddressPayment");
    console.log(billingAddressPayment);
    var OrderPayment=sessionStorage.getItem("OrderPayment");
    console.log(OrderPayment);
	//change by deepak getItem("ecomushipping");
    var shipping=sessionStorage.getItem("ecomushipping");
    console.log("shipping..........."+shipping);
    var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/ecomm-soap#ecommPaymentRegistrationInfo";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommPaymentRegistrationInfo xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#ecommPaymentRegistrationInfo\"><appId>'+appid+'</appId><orderId>'+sessionStorage.getItem("foodRandomId")+'</orderId><paymentDetail>'+paymentDetail+'</paymentDetail><billingAddress>'+billingAddressPayment+'</billingAddress><shipping>'+shipping+'</shipping><productDetail><![CDATA['+OrderPayment+']]></productDetail><shippingAddress>'+shippingDetail+'</shippingAddress><pickupComment>'+instructionsText+'</pickupComment></ecommPaymentRegistrationInfo></soap:Body></soap:Envelope>';
    
    console.log('payment t=1'+soapRequest);
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           console.log('rrrrr=>'+req.responseText);
           var strJSON=$(req.responseText).find("return").text();
           var responseData=strJSON;
           strJSON=strJSON.split("Payment_Status:");
           var paymentInfo=strJSON[0].split("_");
           console.log("hsdfjksdfjsdf"+paymentInfo[1]);
           if(paymentInfo[2] == "ccPhone")
           {
           console.log('submitOrder ecom cod --->successs payment');
           
           localStorage.setItem("fooduserid",window.sessionStorage.getItem("OrderEmailId"));
           accountDispl='block';
           loginDispl='none';
           window.sessionStorage.setItem("foodlogincheck","1");
           localStorage.setItem("ecomuserid",window.sessionStorage.getItem("OrderEmailId"));
           window.sessionStorage.setItem("ecomlogincheck","1");
           localStorage.setItem("foodEcomUserId",paymentInfo[1]);
           
           var totalAmount=parseFloat(sessionStorage.getItem("ecomnewgtotal"));
           totalAmount=totalAmount.toFixed(2);
           var deliveryAmount=parseFloat(sessionStorage.getItem("ecomdeliveryAmount")).toFixed(2);
           var discountAmount=parseFloat(sessionStorage.getItem("ecomdiscountAmount")).toFixed(2);
           var taxAmount=parseFloat(sessionStorage.getItem("ecomTaxAmount")).toFixed(2);
           var couponAmount=parseFloat(sessionStorage.getItem("ecomcouponAmount")).toFixed(2);
           var subtotal=sessionStorage.getItem("ecomsubtotalAmount");
           var miscTax=calculateMiscTax(subtotal,'json');
           
           var discountInfo = '{"discount":"'+discountAmount+'","delivery":"'+deliveryAmount+'","tax":"'+taxAmount+'","total":"'+totalAmount+'","coupon":"'+couponAmount+'","tip":"0"}';
           
           //var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/ecomm-soap#ecommOrder";
		   var wsUrl = "http://apps.appypie.com/services/ecomm-soap#ecommOrder";
           var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommOrder xmlns=\"http://'+localStorage.getItem("reseller")+'/services/ecomm-soap#ecommOrder\"><appId>'+appid+'</appId><orderId>'+sessionStorage.getItem("foodRandomId")+'</orderId><email>'+sessionStorage.getItem("OrderEmailId")+'</email><discount>'+discountInfo+'</discount><order><![CDATA['+udetails+']]></order><billing>'+billing+'</billing><shipping>'+shipping+'</shipping><payment>'+paymentMethod+'</payment><pickupComment>'+instructionsText+'</pickupComment><miscTax>'+miscTax+'</miscTax></ecommOrder></soap:Body></soap:Envelope>';
           var strJSON='';
           console.log(soapRequest);
           jQuery.support.cors = true;
           $.ajax({
                  type: "POST",
                  url: wsUrl,
                  contentType: "text/xml",
                  dataType: "text",
                  data: soapRequest,
                  success: function(data, status, req)
                  {
                  console.log('rrrrr=>'+req.responseText);
                  if(!localStorage.getItem("fooduserid"))
                  {
                  localStorage.setItem("fooduserid",sessionStorage.getItem("OrderEmailId"));
                  accountDispl='block';
                  loginDispl='none';
                  }
                  
                  setTimeout(function(){$('.appypie-loader').hide();},1000);
                  MakeCall(sessionStorage.getItem("ccPhoneNo"));
                  setTimeout(function(){ sessionStorage.clear();successViewEcom('Success','ccPhone');},2000);
                  
                  },
                  error: function(response, textStatus, errorThrown)
                  {
                  successViewEcom('decline');
                  setTimeout(function(){$('.appypie-loader').hide();},1000);
                  }
                  });
           }
           else
           {
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           console.log("Error -->"+responseData);
           successViewEcom('decline');
           }
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           successViewEcom('decline');
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
    
}

function submitOrderCCPhone(t){
    $('.appypie-loader').show();
    window.sessionStorage.setItem("transectionId",t);
    var udetails= window.sessionStorage.getItem("udetails");
    var billing=window.sessionStorage.getItem("ubilling");
    var shipping=window.sessionStorage.getItem("ushipping");
    var paymentMethod;
    var flag=0;
    console.log(t);
    
    if(t=="1")
    {
        paymentMethod='{"paymentId":"ccPhone","paymentMethod":"ccPhone","paymentStatus":"processing"}';
        
        var lastName=  sessionStorage.getItem("LastNamePayment");
        var totalAmount= parseFloat(window.sessionStorage.getItem("gtotal1")).toFixed(2);
        
        var shippingDetail = '{"shipping":"0.00","tax":"0.00","totalAmount":"'+totalAmount+'","currency":"'+window.sessionStorage.getItem("currency")+'"}';
        
        
        console.log("shipping detai......"+shippingDetail);
        var paymentDetail='ccPhone';
        console.log("paymentDetail......"+paymentDetail);
        var billingAddressPayment=sessionStorage.getItem("billingAddressPayment");
        console.log("billingAddressPayment......"+billingAddressPayment);
        var OrderPayment=sessionStorage.getItem("OrderPayment");
        console.log("OrderPayment......"+OrderPayment);
       // var shipping=sessionStorage.getItem("ushipping");
        console.log("shipping..........."+shipping);
        var newdate=new Date().getTime();
        var orderId='ap'+newdate;
        window.sessionStorage.setItem("foodRandomId",orderId);
        
        
        var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodPaymentRegistrationInfo";
        
        var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodPaymentRegistrationInfo xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodPaymentRegistrationInfo\"><appId>'+appid+'</appId><orderId>'+orderId+'</orderId><paymentDetail>'+paymentDetail+'</paymentDetail><billingAddress>'+billingAddressPayment+'</billingAddress><shipping>'+shipping+'</shipping><productDetail><![CDATA['+OrderPayment+']]></productDetail><shippingAddress>'+shippingDetail+'</shippingAddress></foodPaymentRegistrationInfo></soap:Body></soap:Envelope>';
        console.log("soapRequest????????????????????????"+soapRequest);
        $.ajax({
               type: "POST",
               url: wsUrl,
               contentType: "text/xml",
               dataType: "text",
               data: soapRequest,
               success: function(data, status, req)
               {
               console.log('rrrrr=>'+req.responseText);
               var strJSON=$(req.responseText).find("return").text();
               var responseData=strJSON;
               strJSON=strJSON.split("Payment_Status:");
               var paymentInfo=strJSON[0].split("_");
               console.log("hsdfjksdfjsdf"+paymentInfo[1]);
               if(paymentInfo[2] == "ccPhone")
               {
               console.log('successs payment');
               
               localStorage.setItem("fooduserid",window.sessionStorage.getItem("OrderEmailId"));
               
               
               var totalAmount= parseFloat(window.sessionStorage.getItem("gtotal1")).toFixed(2);
               var deliveryAmount=parseFloat(sessionStorage.getItem("fooddelivery")).toFixed(2);
               var discountAmount=parseFloat(sessionStorage.getItem("fooddiscount")).toFixed(2);
               var taxAmount=parseFloat(sessionStorage.getItem("foodtax")).toFixed(2);
               var couponAmount=parseFloat(sessionStorage.getItem("couponAmount")).toFixed(2);
               var discountInfo = '{"discount":"'+discountAmount+'","delivery":"'+deliveryAmount+'","tax":"'+taxAmount+'","total":"'+totalAmount+'","coupon":"'+couponAmount+'","tip":"'+parseFloat(window.sessionStorage.getItem("tipCharges")).toFixed(2)+'"}';
               var subtotal= sessionStorage.getItem("subtotal");
               var miscTax=calculateMiscTax(subtotal,'json');
               
               var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodOrder";
               var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodOrder xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodOrder\"><appId>'+appid+'</appId><orderId>'+orderId+'</orderId><email>'+window.sessionStorage.getItem("OrderEmailId")+'</email><discount>'+discountInfo+'</discount><order><![CDATA['+udetails+']]></order><billing>'+billing+'</billing><shipping>'+shipping+'</shipping><payment>'+paymentMethod+'</payment><pickupComment>'+sessionStorage.getItem('instructionsText')+'</pickupComment><miscTax>'+miscTax+'</miscTax></foodOrder></soap:Body></soap:Envelope>';
               var strJSON='';
               console.log(""+soapRequest);
               jQuery.support.cors = true;
               $.ajax({
                      type: "POST",
                      url: wsUrl,
                      contentType: "text/xml",
                      dataType: "text",
                      data: soapRequest,
                      success: function(data, status, req)
                      {
                      console.log('rrrrr=>'+req.responseText);
                      if(!localStorage.getItem("fooduserid"))
                      {
                      var userid=sessionStorage.getItem("OrderEmailId");
                      localStorage.setItem("fooduserid",userid);
                      }
                      
                      setTimeout(function(){$('.appypie-loader').hide();},1000);
                      MakeCall(sessionStorage.getItem("ccPhoneNo"));
                      setTimeout(function(){ sessionStorage.clear();successView('Success','ccPhone');},2000);
                      
                      
                      //Ext.Viewport.setMasked(false);
                      },
                      error: function(response, textStatus, errorThrown)
                      {
                      console.log(response);
                      successView('decline');
                      setTimeout(function(){$('.appypie-loader').hide();},1000);
                      //Ext.Viewport.setMasked(false);
                      }
                      });
               
               
               }
               else
               {
               console.log("Error -->"+responseData);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               successView('decline');
               }
               },
               error: function(response, textStatus, errorThrown)
               {
               successView('decline');
               console.log(response.responseText);
               setTimeout(function(){$('.appypie-loader').hide();},1000);
               
               }
               });
    }
    
}

//function displayAddressForPickup()
//{
//    var pickUpAddress=document.getElementById("pickUpAddress").value;
//    if(pickUpAddress == 'others')
//    {
//        $('#pickUpText').show();
//        $('#pickUpAddText').show();
//    }
//    else
//    {
//       $('#pickUpText').hide();
//        $('#pickUpAddText').hide();
//    }
//}

function submitOrderPickUp()
{
     $('.appypie-loader').show();
    var addressData=document.getElementById("pickUpText").value;
    var pickUpAddress=document.getElementById("pickUpAddress").value;
    if(!pickUpAddress || pickUpAddress == 'others')
    {
    $.ajax({
           url:'https://maps.googleapis.com/maps/api/geocode/json?address='+addressData+'&key=AIzaSyAUlshWWtBduQdUrTSA9VMThhWfGk3Hm9A',
           dataType: "text",
           crossDomain: true,
           cache: false,
           success: function(xml)
           {
           var response=JSON.parse(xml);
           if(response.results.length>0)
           {
           submitOrderPickUpNew("1")
           }
           else
           {
           navigator.notification.alert(ppppleaseenterthevalidaddrespickup_food, setTimeout,errorrr_food, okkk_food);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           },
           error: function(XMLHttpRequest, textStatus, errorThrown) {
           navigator.notification.alert(ppppleaseenterthevalidaddrespickup_food, setTimeout, errorrr_food, okkk_food);
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           console.log('fail');
           console.log(XMLHttpRequest + '--' + textStatus + '--' + errorThrown);
           }
           });
    }
    else
    {
        submitOrderPickUpNew("1");
    }
}

function submitOrderPickUpNew(t)
{
   $('.appypie-loader').show();
       var commentsPickUp=document.getElementById("pickUpText").value;
       var pickUpTime=document.getElementById("pickUpTimeFood").value;
       var pickUpAddress=document.getElementById("pickUpAddress").value;
       if(commentsPickUp.length < 5 && pickUpAddress == 'others')
       {
           navigator.notification.alert(ppppleaseenterthevalidaddrespickup_food, setTimeout, errorrr_food, okkk_food);
           $('.appypie-loader').hide();
           return false;
       }


       if(!pickUpTime)
       {

           navigator.notification.alert(ppleasentethtimeforpickup_food, setTimeout, errorrr_food, okkk_food);
           $('.appypie-loader').hide();
           return false;
       }
       else
       {
           localStorage.setItem('isFromPickUp',1);
       }

       console.log("pickUpTime --->"+parseFloat(pickUpTime.replace(':','')));
       //commentsPickUp+='\nPick up Time :-'+pickUpTime+'\nAnd Instructions :-'+ sessionStorage.getItem('instructionsText');
       //commentsPickUp+='\nPick up Time :-'+pickUpTime;

    commentsPickUp+='Pick up Time :-'+pickUpTime+ '#$#$'+sessionStorage.getItem('instructionsText');
       sessionStorage.setItem("transectionId",t);
       var udetails=sessionStorage.getItem("udetails");
       var billing=sessionStorage.getItem("ubilling");
       var shipping=sessionStorage.getItem("ushipping");
       var paymentMethod;
       var flag=0;
       console.log(t);
       var paymentInfo=t.split("_");

       if(t=="1")
       {
           paymentMethod='{"paymentId":"","paymentMethod":"pickup","paymentStatus":"Success"}';

           var lastName=  sessionStorage.getItem("LastNamePayment");
           var totalAmount= parseFloat(window.sessionStorage.getItem("gtotal1")).toFixed(2);

           var shippingDetail = '{"shipping":"0.00","tax":"0.00","totalAmount":"'+totalAmount+'","currency":"'+window.sessionStorage.getItem("currency")+'"}';


           console.log("shipping detai......"+shippingDetail);
           var paymentDetail='pickup';
           console.log("paymentDetail......"+paymentDetail);

           if(paymentDetail=="pickup")
           {
               if(parseFloat(sessionStorage.getItem("fooddelivery"))>0)
               {
                   totalAmount=totalAmount-parseFloat(sessionStorage.getItem("fooddelivery"));
               }

           }
           var shippingDetail = '{"shipping":"0.00","tax":"0.00","totalAmount":"'+totalAmount+'","currency":"'+window.sessionStorage.getItem("currency")+'"}';

           var billingAddressPayment=sessionStorage.getItem("billingAddressPayment");
           console.log("billingAddressPayment......"+billingAddressPayment);
           var OrderPayment=sessionStorage.getItem("OrderPayment");
           console.log("OrderPayment......"+OrderPayment);
           var shipping=sessionStorage.getItem("ushipping");
           console.log("shipping..........."+shipping);
           var newdate=new Date().getTime();
           var orderId='ap'+newdate;
           window.sessionStorage.setItem("foodRandomId",orderId);


           var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodPaymentRegistrationInfo";
           //var wsUrl = "http://appypieml.onsisdev.info/services/food-soap#foodPaymentRegistrationInfo";

           var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodPaymentRegistrationInfo xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodPaymentRegistrationInfo\"><appId>'+appid+'</appId><orderId>'+orderId+'</orderId><paymentDetail>'+paymentDetail+'</paymentDetail><billingAddress>'+billingAddressPayment+'</billingAddress><shipping>'+shipping+'</shipping><productDetail><![CDATA['+OrderPayment+']]></productDetail><shippingAddress>'+shippingDetail+'</shippingAddress></foodPaymentRegistrationInfo></soap:Body></soap:Envelope>';
           console.log("soapRequest????????????????????????"+soapRequest);
           $.ajax({
                  type: "POST",
                  url: wsUrl,
                  contentType: "text/xml",
                  dataType: "text",
                  data: soapRequest,
                  success: function(data, status, req)
                  {
                  console.log('rrrrr=>'+req.responseText);
                  var strJSON=$(req.responseText).find("return").text();
                  var responseData=strJSON;
                  strJSON=strJSON.split("Payment_Status:");
                  var paymentInfo=strJSON[0].split("_");
                  console.log("hsdfjksdfjsdf"+paymentInfo[1]);
                  if(paymentInfo[2] == "pickup")
                  {
                  console.log('successs payment');

                  localStorage.setItem("fooduserid",window.sessionStorage.getItem("OrderEmailId"));

                  var pickUpAddress=document.getElementById("pickUpAddress").value;
   			       pickUpAddress = pickUpAddress.replace(/(\r\n|\n|\r)/gm,"");
                  var billing='{"billShip":"Billing","name":"'+pickUpAddress+'","address":"","city":"","state":"","country":"","zip":"","phone":""}';
                  var totalAmount= parseFloat(window.sessionStorage.getItem("gtotal1")).toFixed(2);
                  var deliveryAmount=parseFloat(sessionStorage.getItem("fooddelivery")).toFixed(2);
                  var discountAmount=parseFloat(sessionStorage.getItem("fooddiscount")).toFixed(2);
                  var taxAmount=parseFloat(sessionStorage.getItem("foodtax")).toFixed(2);
                  var couponAmount=parseFloat(sessionStorage.getItem("couponAmount")).toFixed(2);

                  if(parseFloat(sessionStorage.getItem("fooddelivery"))>0)
                  {
                  totalAmount=totalAmount-parseFloat(sessionStorage.getItem("fooddelivery"));
                  deliveryAmount="0";
                  }

                  var discountInfo = '{"discount":"'+discountAmount+'","delivery":"'+deliveryAmount+'","tax":"'+taxAmount+'","total":"'+totalAmount+'","coupon":"'+couponAmount+'","tip":"'+parseFloat(window.sessionStorage.getItem("tipCharges")).toFixed(2)+'"}';
                  var subtotal= sessionStorage.getItem("subtotal");
                  var miscTax=calculateMiscTax(subtotal,'json');

                  var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodOrder";
                  var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodOrder xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodOrder\"><appId>'+appid+'</appId><orderId>'+orderId+'</orderId><email>'+window.sessionStorage.getItem("OrderEmailId")+'</email><discount>'+discountInfo+'</discount><order><![CDATA['+udetails+']]></order><billing>'+billing+'</billing><shipping>'+shipping+'</shipping><payment>'+paymentMethod+'</payment><pickupComment>'+commentsPickUp+'</pickupComment><miscTax>'+miscTax+'</miscTax></foodOrder></soap:Body></soap:Envelope>';
                  var strJSON='';
                  console.log(""+soapRequest);
                  jQuery.support.cors = true;
                  $.ajax({
                         type: "POST",
                         url: wsUrl,
                         contentType: "text/xml",
                         dataType: "text",
                         data: soapRequest,
                         success: function(data, status, req)
                         {
                         console.log('rrrrr=>'+req.responseText);
                         if(!localStorage.getItem("fooduserid"))
                         {
                         var userid=sessionStorage.getItem("OrderEmailId");
                         localStorage.setItem("fooduserid",userid);
                         }
                         sessionStorage.clear();
                         successView('Success');
                         //Ext.Viewport.setMasked(false);
                         },
                         error: function(response, textStatus, errorThrown)
                         {
                         console.log(response);
                         successView('decline');
                         //Ext.Viewport.setMasked(false);
                         }
                         });


                  }
                  else
                  {
                  console.log("Error -->"+responseData);


                  }
                  },
                  error: function(response, textStatus, errorThrown)
                  {
                  console.log(response.responseText);


                  }
                  });
       }
       else if(t!='3')
       {
           paymentMethod='{"paymentId":"'+paymentInfo[2]+'","paymentMethod":"credit_card","paymentStatus":"Success"}';
           flag=1;
           var orderId=window.sessionStorage.getItem("foodRandomId")
       }
       else if(t=='3'){
           successView('decline');
       }
       if(flag==1)
       {
           var totalAmount= parseFloat(window.sessionStorage.getItem("gtotal1")).toFixed(2);

           var deliveryAmount=parseFloat(sessionStorage.getItem("fooddelivery")).toFixed(2);
           var discountAmount=parseFloat(sessionStorage.getItem("fooddiscount")).toFixed(2);
           var taxAmount=parseFloat(sessionStorage.getItem("foodtax")).toFixed(2);
           var couponAmount=parseFloat(sessionStorage.getItem("couponAmount")).toFixed(2);
           var subtotal=sessionStorage.getItem("subtotal");
           var miscTax=calculateMiscTax(subtotal,'json');

           var discountInfo = '{"discount":"'+discountAmount+'","delivery":"'+deliveryAmount+'","tax":"'+taxAmount+'","total":"'+totalAmount+'","coupon":"'+couponAmount+'","tip":"'+parseFloat(window.sessionStorage.getItem("tipCharges")).toFixed(2)+'"}';

           var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodOrder";
           var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodOrder xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodOrder\"><appId>'+appid+'</appId><orderId>'+orderId+'</orderId><email>'+window.sessionStorage.getItem("OrderEmailId")+'</email><discount>'+discountInfo+'</discount><order><![CDATA['+udetails+']]></order><billing>'+billing+'</billing><shipping>'+shipping+'</shipping><payment>'+paymentMethod+'</payment><pickupComment>'+sessionStorage.getItem('instructionsText')+'</pickupComment><miscTax>'+miscTax+'</miscTax></foodOrder></soap:Body></soap:Envelope>';
           var strJSON='';
           console.log("???????????????????????????????????????????????????????????"+soapRequest);
           jQuery.support.cors = true;
           $.ajax({
                  type: "POST",
                  url: wsUrl,
                  contentType: "text/xml",
                  dataType: "text",
                  data: soapRequest,
                  success: function(data, status, req)
                  {
                  console.log('rrrrr=>'+req.responseText);
                  if(!localStorage.getItem("fooduserid"))
                  {
                  var userid=sessionStorage.getItem("OrderEmailId");
                  localStorage.setItem("fooduserid",userid);
                  }
                  window.sessionStorage.clear();
                  successView('Success');
                  //Ext.Viewport.setMasked(false);
                  },
                  error: function(response, textStatus, errorThrown)
                  {
                  console.log(response);
                  successView('decline');
                  //Ext.Viewport.setMasked(false);
                  }
                  });
           //console.log(userid);
       }
	   }
function submitOrder(t)
{
    $('.appypie-loader').show();
    
    console.log(">>>>>>>>>>>>>>>>>>>>>>>>>"+localStorage.getItem("payTypeFoodOrEcom"));
    console.log("value of t---->"+t);
    if(localStorage.getItem("payTypeFoodOrEcom")=="food"){
        console.log('t=============>'+t);
        window.sessionStorage.setItem("transectionId",t);
        var udetails= window.sessionStorage.getItem("udetails");
        var billing=window.sessionStorage.getItem("ubilling");
        var shipping=window.sessionStorage.getItem("ushipping");
        var paymentMethod;
        var flag=0;
        console.log(t);
        var paymentInfo=t.split("_");
        if(t=="1")
        {
            paymentMethod='{"paymentId":"cod","paymentMethod":"cod","paymentStatus":"processing"}';
            
            var lastName=  sessionStorage.getItem("LastNamePayment");
            var totalAmount= parseFloat(window.sessionStorage.getItem("gtotal1")).toFixed(2);
            var shippingDetail = '{"shipping":"0.00","tax":"0.00","totalAmount":"'+totalAmount+'","currency":"'+window.sessionStorage.getItem("currency")+'"}';
            console.log("shipping detai......"+shippingDetail);
            var paymentDetail='cod';
            console.log("paymentDetail......"+paymentDetail);
            var billingAddressPayment=sessionStorage.getItem("billingAddressPayment");
            console.log("billingAddressPayment......"+billingAddressPayment);
            var OrderPayment=sessionStorage.getItem("OrderPayment");
            console.log("OrderPayment......"+OrderPayment);
            var shipping=sessionStorage.getItem("ushipping");
            console.log("shipping..........."+shipping);
            var newdate=new Date().getTime();
            
            var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodPaymentRegistrationInfo";
            var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodPaymentRegistrationInfo xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodPaymentRegistrationInfo\"><appId>'+appid+'</appId><orderId>'+sessionStorage.getItem("foodRandomId")+'</orderId><paymentDetail>'+paymentDetail+'</paymentDetail><billingAddress>'+billingAddressPayment+'</billingAddress><shipping>'+shipping+'</shipping><productDetail><![CDATA['+OrderPayment+']]></productDetail><shippingAddress>'+shippingDetail+'</shippingAddress></foodPaymentRegistrationInfo></soap:Body></soap:Envelope>';
            console.log("soapRequest????????????????????????"+soapRequest);
            $.ajax({
                   type: "POST",
                   url: wsUrl,
                   contentType: "text/xml",
                   dataType: "text",
                   data: soapRequest,
                   success: function(data, status, req)
                   {
                   console.log('rrrrr=>'+req.responseText);
                   var strJSON=$(req.responseText).find("return").text();
                   var responseData=strJSON;
                   strJSON=strJSON.split("Payment_Status:");
                   var paymentInfo=strJSON[0].split("_");
                   console.log("hsdfjksdfjsdf"+paymentInfo[1]);
                   if(paymentInfo[2] == "cod")
                   {
                   console.log('submitOrder() cod -->successs payment');
                   
                   localStorage.setItem("fooduserid",window.sessionStorage.getItem("OrderEmailId"));
                   accountDispl='block';
                   loginDispl='none';
                   window.sessionStorage.setItem("foodlogincheck","1");
                   localStorage.setItem("ecomuserid",window.sessionStorage.getItem("OrderEmailId"));
                   window.sessionStorage.setItem("ecomlogincheck","1");
                   localStorage.setItem("foodEcomUserId",paymentInfo[1]);
                   console.log(sessionStorage.getItem("gtotal1"));
                   
                   
                   
                   var totalAmount=sessionStorage.getItem("gtotal1");
                   console.log("totalAmount--->"+totalAmount);
                   var deliveryAmount=sessionStorage.getItem("fooddelivery");
                   console.log("deliveryAmount--->"+deliveryAmount);
                   var discountAmount=sessionStorage.getItem("fooddiscount");
                   console.log("discountAmount--->"+discountAmount);
                   var taxAmount=window.sessionStorage.getItem("foodtax");
                   console.log("taxAmount--->"+taxAmount);
                   var couponAmount=window.sessionStorage.getItem("couponAmount");
                   console.log("couponAmount--->"+couponAmount);
                   var subtotal=sessionStorage.getItem("subtotal");
                   var miscTax=calculateMiscTax(subtotal,'json');
                   
                   
                   var discountInfo = '{"discount":"'+discountAmount+'","delivery":"'+deliveryAmount+'","tax":"'+taxAmount+'","total":"'+totalAmount+'","coupon":"'+couponAmount+'","tip":"'+parseFloat(sessionStorage.getItem("tipCharges")).toFixed(2)+'"}';
                   var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodOrder";
                   var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodOrder xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodOrder\"><appId>'+appid+'</appId><orderId>'+sessionStorage.getItem("foodRandomId")+'</orderId><email>'+window.sessionStorage.getItem("OrderEmailId")+'</email><discount>'+discountInfo+'</discount><order><![CDATA['+udetails+']]></order><billing>'+billing+'</billing><shipping>'+shipping+'</shipping><payment>'+paymentMethod+'</payment><pickupComment>'+sessionStorage.getItem('instructionsText')+'</pickupComment><miscTax>'+miscTax+'</miscTax></foodOrder></soap:Body></soap:Envelope>';
                   var strJSON='';
                   console.log("???????????????????????????????????????????????????????????"+soapRequest);
                   jQuery.support.cors = true;
                   $.ajax({
                          type: "POST",
                          url: wsUrl,
                          contentType: "text/xml",
                          dataType: "text",
                          data: soapRequest,
                          success: function(data, status, req)
                          {
                          console.log('rrrrr=>'+req.responseText);
                          if(!localStorage.getItem("fooduserid"))
                          {
                          var userid=sessionStorage.getItem("OrderEmailId");
                          localStorage.setItem("fooduserid",userid);
                          accountDispl='block';
                          loginDispl='none';
                          }
                          window.sessionStorage.clear();
                          successView('Success');
                          },
                          error: function(response, textStatus, errorThrown)
                          {
                          console.log(response);
                          successView('decline');
                          setTimeout(function(){$('.appypie-loader').hide();},1000);
                          }
                          });
                   
                   
                   }
                   else
                   {
                   console.log("Error -->"+responseData);
                   
                   }
                   },
                   error: function(response, textStatus, errorThrown)
                   {
                   successView('decline');
                   console.log(response.responseText);
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                   }
                   });
        }
        else if(t!='3')
        {
            if(localStorage.getItem("payu") == "payu")
            {
              paymentMethod='{"paymentId":"","paymentMethod":"payu_money","paymentStatus":"processing"}';
            } 
          else if(sessionStorage.getItem("paypalTypeButton") == "paypalPro")
            {
              paymentMethod='{"paymentId":"'+paymentInfo[2]+'","paymentMethod":"credit_card","paymentStatus":"Success"}';
          }
            else
            {
                paymentMethod='{"paymentId":"","paymentMethod":"credit_card","paymentStatus":"processing"}';
            }
            flag=1;
            var orderId=window.sessionStorage.getItem("foodRandomId")
        }
        else if(t=='3'){
            successView('decline');
        }
        if(flag==1)
        {
            var totalAmount= parseFloat(window.sessionStorage.getItem("gtotal1")).toFixed(2);
            
            var deliveryAmount=parseFloat(sessionStorage.getItem("fooddelivery")).toFixed(2);
            var discountAmount=parseFloat(sessionStorage.getItem("fooddiscount")).toFixed(2);
            var taxAmount=parseFloat(sessionStorage.getItem("foodtax")).toFixed(2);
            var couponAmount=parseFloat(sessionStorage.getItem("couponAmount")).toFixed(2);
            var subtotal=sessionStorage.getItem("subtotal");
            var miscTax=calculateMiscTax(subtotal,'json');
            sessionStorage.setItem("paymentMethodForPaypal",paymentMethod);
            
            var discountInfo = '{"discount":"'+discountAmount+'","delivery":"'+deliveryAmount+'","tax":"'+taxAmount+'","total":"'+totalAmount+'","coupon":"'+couponAmount+'","tip":"'+parseFloat(sessionStorage.getItem("tipCharges")).toFixed(2)+'"}';
//            
//            var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodOrder";
//            var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodOrder xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodOrder\"><appId>'+appid+'</appId><orderId>'+sessionStorage.getItem("foodRandomId")+'</orderId><email>'+window.sessionStorage.getItem("OrderEmailId")+'</email><discount>'+discountInfo+'</discount><order><![CDATA['+udetails+']]></order><billing>'+billing+'</billing><shipping>'+shipping+'</shipping><payment>'+paymentMethod+'</payment><pickupComment>'+sessionStorage.getItem('instructionsText')+'</pickupComment><miscTax>'+miscTax+'</miscTax></foodOrder></soap:Body></soap:Envelope>';
//            var strJSON='';
//            console.log("???????????????????????????????????????????????????????????"+soapRequest);
//            jQuery.support.cors = true;
//            $.ajax({
//                   type: "POST",
//                   url: wsUrl,
//                   contentType: "text/xml",
//                   dataType: "text",
//                   data: soapRequest,
//                   success: function(data, status, req)
//                   {
//                   console.log('rrrrr=>'+req.responseText);
                   if(!localStorage.getItem("fooduserid"))
                   {
                   var userid=sessionStorage.getItem("OrderEmailId");
                   localStorage.setItem("fooduserid",userid);
                   accountDispl='block';
                   loginDispl='none';
                   }
                   if(sessionStorage.getItem("paypalTypeButton") && t!='3')
                   {
                     if(localStorage.getItem("payu")!="payu")
                    {
                   if(t=='12231')
				   {
				   PayPaypalExpress();
				   }
				    paypalExpressReturn("Success");
                    }
                   }
//                   else
//                   {
//                   sessionStorage.clear();
//                   successView('Success');
//                   }
//                   
//                   },
//                   error: function(response, textStatus, errorThrown)
//                   {
//                   console.log(response);
//                   successView('decline');
//                   setTimeout(function(){$('.appypie-loader').hide();},1000);
//                   }
//                   });
        }
    }
    else {
        var udetails=sessionStorage.getItem("ecomudetails");
        var billing=sessionStorage.getItem("ecomubilling");
        
        var shipping=sessionStorage.getItem("ecomushipping");
       
        var paymentInfo=t.split("_");
        sessionStorage.setItem("transectionId",t);
        //8888888888888//
        var instructionsText=sessionStorage.getItem('instructions_mcom');
        
         if( instructionsText== null )
        {
            instructionsText='';
        }
        if(instructionsText.length < 1)
        {
            instructionsText='';
        }
        
        console.log("instructionsText---->"+instructionsText);
        var paymentMethod;
        var flag=0;
        console.log(t);
        if(t=='1')
        {
            paymentMethod='{"paymentId":"cod","paymentMethod":"cod","paymentStatus":"processing"}';
            var shippingDetail = '{"shipping":"0.00","tax":"0.00","totalAmount":"'+sessionStorage.getItem("ecomnewgtotal")+'","currency":"'+window.sessionStorage.getItem("currency")+'"}';
            
            console.log(shippingDetail);
            var paymentDetail='cod';
            console.log(paymentDetail);
            var billingAddressPayment=sessionStorage.getItem("billingAddressPayment");
            console.log(billingAddressPayment);
            var OrderPayment=sessionStorage.getItem("OrderPayment");
            console.log(OrderPayment);
            var shipping=sessionStorage.getItem("ecomubilling");
            console.log("shipping..........."+shipping);
            //            var newdate=new Date().getTime();
            //            var orderId='ap'+newdate;
            //            window.sessionStorage.setItem("ecomRandomId",orderId);
            var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/ecomm-soap#ecommPaymentRegistrationInfo";
            var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommPaymentRegistrationInfo xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#ecommPaymentRegistrationInfo\"><appId>'+appid+'</appId><orderId>'+sessionStorage.getItem("foodRandomId")+'</orderId><paymentDetail>'+paymentDetail+'</paymentDetail><billingAddress>'+billingAddressPayment+'</billingAddress><shipping>'+sessionStorage.getItem("ecomushipping")+'</shipping><productDetail><![CDATA['+OrderPayment+']]></productDetail><shippingAddress>'+shippingDetail+'</shippingAddress><pickupComment>'+instructionsText+'</pickupComment></ecommPaymentRegistrationInfo></soap:Body></soap:Envelope>';
            
            console.log('payment t=1'+soapRequest);
            $.ajax({
                   type: "POST",
                   url: wsUrl,
                   contentType: "text/xml",
                   dataType: "text",
                   data: soapRequest,
                   success: function(data, status, req)
                   {
                   console.log('rrrrr=>'+req.responseText);
                   var strJSON=$(req.responseText).find("return").text();
                   var responseData=strJSON;
                   strJSON=strJSON.split("Payment_Status:");
                   var paymentInfo=strJSON[0].split("_");
                   console.log("hsdfjksdfjsdf"+paymentInfo[1]);
                   if(paymentInfo[2] == "cod")
                   {
                   console.log('submitOrder ecom cod --->successs payment');
                   
                   localStorage.setItem("fooduserid",window.sessionStorage.getItem("OrderEmailId"));
                   accountDispl='block';
                   loginDispl='none';
                   window.sessionStorage.setItem("foodlogincheck","1");
                   localStorage.setItem("ecomuserid",window.sessionStorage.getItem("OrderEmailId"));
                   window.sessionStorage.setItem("ecomlogincheck","1");
                   localStorage.setItem("foodEcomUserId",paymentInfo[1]);
                   
                   var totalAmount=parseFloat(sessionStorage.getItem("ecomnewgtotal"));
                   totalAmount=totalAmount.toFixed(2);
                   var deliveryAmount=parseFloat(sessionStorage.getItem("ecomdeliveryAmount")).toFixed(2);
                   var discountAmount=parseFloat(sessionStorage.getItem("ecomdiscountAmount")).toFixed(2);
                   var taxAmount=parseFloat(sessionStorage.getItem("ecomTaxAmount")).toFixed(2);
                   var couponAmount=parseFloat(sessionStorage.getItem("ecomcouponAmount")).toFixed(2);
                   var subtotal=sessionStorage.getItem("ecomsubtotalAmount");
                   var miscTax=calculateMiscTax(subtotal,'json');
                   
                   var discountInfo = '{"discount":"'+discountAmount+'","delivery":"'+deliveryAmount+'","tax":"'+taxAmount+'","total":"'+totalAmount+'","coupon":"'+couponAmount+'","tip":"0"}';
                 
                   var wsUrl = "http://apps.appypie.com/services/ecomm-soap#ecommOrder";
                   var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommOrder xmlns=\"http://'+localStorage.getItem("reseller")+'/services/ecomm-soap#ecommOrder\"><appId>'+appid+'</appId><orderId>'+sessionStorage.getItem("foodRandomId")+'</orderId><email>'+sessionStorage.getItem("OrderEmailId")+'</email><discount>'+discountInfo+'</discount><order><![CDATA['+udetails+']]></order><billing>'+billing+'</billing><shipping>'+sessionStorage.getItem("ecomushipping")+'</shipping><payment>'+paymentMethod+'</payment><pickupComment>'+instructionsText+'</pickupComment><miscTax>'+miscTax+'</miscTax></ecommOrder></soap:Body></soap:Envelope>';
                   var strJSON='';
                   console.log(soapRequest);
                   jQuery.support.cors = true;
                   $.ajax({
                          type: "POST",
                          url: wsUrl,
                          contentType: "text/xml",
                          dataType: "text",
                          data: soapRequest,
                          success: function(data, status, req)
                          {
                          console.log('rrrrr=>'+req.responseText);
                          if(!localStorage.getItem("fooduserid"))
                          {
                          localStorage.setItem("fooduserid",sessionStorage.getItem("OrderEmailId"));
                          accountDispl='block';
                          loginDispl='none';
                          }
                          sessionStorage.clear();
                          successViewEcom('Success');
                          },
                          error: function(response, textStatus, errorThrown)
                          {
                          successViewEcom('decline');
                          setTimeout(function(){$('.appypie-loader').hide();},1000);
                          }
                          });
                   }
                   else
                   {
                   console.log("Error -->"+responseData);
                   
                   }
                   },
                   error: function(response, textStatus, errorThrown)
                   {
                   console.log(response);
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                   }
                   });
        }
        else if(t!='3')
        {
            var paymentTypeViewCheck=true;
         if(localStorage.getItem("payu")=="payu")
           	   {
           		 paymentMethod='{"paymentId":"","paymentMethod":"payu_money","paymentStatus":"processing"}';
           	   }
           else if(sessionStorage.getItem("paypalTypeButton") == "paypalPro")
          {
                paymentMethod='{"paymentId":"'+paymentInfo[2]+'","paymentMethod":"credit_card","paymentStatus":"Success"}';
                paymentTypeViewCheck=false;
                localStorage.setItem("foodEcomUserId",paymentInfo[1]);
            }
            else
            {
                paymentMethod='{"paymentId":"","paymentMethod":"paypal_express","paymentStatus":"processing"}';
            }
            console.log("paymentMethod--->"+paymentMethod);
            flag=1;
        }
        else if(t=='3'){
            successView('decline');
        }
        console.log('flag flag-->'+flag);
        if(flag==1)
        {
            console.log('flag flag-->'+flag);
            
            
            
            var totalAmount=parseFloat(sessionStorage.getItem("ecomnewgtotal"));
            totalAmount=totalAmount.toFixed(2);
            var deliveryAmount=parseFloat(sessionStorage.getItem("ecomdeliveryAmount")).toFixed(2);
            var discountAmount=parseFloat(sessionStorage.getItem("ecomdiscountAmount")).toFixed(2);
            var taxAmount=parseFloat(sessionStorage.getItem("ecomTaxAmount")).toFixed(2);
            var couponAmount=parseFloat(sessionStorage.getItem("ecomcouponAmount")).toFixed(2);
            
            console.log("submitOrder() ecom condition-->flag-->"+flag+"<----total amount-->"+totalAmount+"<----deliveryAmount--->"+deliveryAmount+"<----discountAmount--->"+discountAmount+"<---taxAmount--->"+taxAmount+"<---couponAmount-->"+couponAmount);
            var discountInfo = '{"discount":"'+discountAmount+'","delivery":"'+deliveryAmount+'","tax":"'+taxAmount+'","total":"'+totalAmount+'","coupon":"'+couponAmount+'","tip":"0"}';
            var subtotal=sessionStorage.getItem("ecomsubtotalAmount");
            var miscTax=calculateMiscTax(subtotal,'json');
            
            var wsUrl = "http://apps.appypie.com/services/ecomm-soap#ecommOrder";
            var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><ecommOrder xmlns=\"http://'+localStorage.getItem("reseller")+'/services/ecomm-soap#ecommOrder\"><appId>'+appid+'</appId><orderId>'+sessionStorage.getItem("foodRandomId")+'</orderId><email>'+sessionStorage.getItem("OrderEmailId")+'</email><discount>'+discountInfo+'</discount><order><![CDATA['+udetails+']]></order><billing>'+billing+'</billing><shipping>'+sessionStorage.getItem("ecomushipping")+'</shipping><payment>'+paymentMethod+'</payment><pickupComment>'+instructionsText+'</pickupComment><miscTax>'+miscTax+'</miscTax></ecommOrder></soap:Body></soap:Envelope>';
            var strJSON='';
            console.log("submitOrder() ecom condition-->soapRequest--->"+soapRequest);
            jQuery.support.cors = true;
            $.ajax({
                   type: "POST",
                   url: wsUrl,
                   contentType: "text/xml",
                   dataType: "text",
                   data: soapRequest,
                   success: function(data, status, req)
                   {
                   console.log('rrrrr=>'+req.responseText);
                   if(!localStorage.getItem("fooduserid"))
                   {
                   localStorage.setItem("fooduserid",sessionStorage.getItem("OrderEmailId"));
                   accountDispl='block';
                   loginDispl='none';
                   }
                   console.log("checking the condition paymentTypeViewCheck--->"+paymentTypeViewCheck);
                   if(paymentTypeViewCheck)
                   {
                   PayPaypalExpress();
                   }
                   else
                   {
                   sessionStorage.clear();
                   successViewEcom('Success');
                   }
                   },
                   error: function(response, textStatus, errorThrown)
                   {
                   successViewEcom('decline');
                   setTimeout(function(){$('.appypie-loader').hide();},1000);
                   }
                   });
        }
        
    }
}

function paypalExpressReturn(returnValue)
{
    if(localStorage.getItem("payTypeFoodOrEcom")=="food")
    {
        successView(returnValue)
    }
    else
    {
        successViewEcom(returnValue);
    }
    
    if(returnValue == 'Success')
    {
        payPalWhenPaymentComplite();
    }
}

function payPalWhenPaymentComplite()
{
    
    var totalAmount= parseFloat(window.sessionStorage.getItem("gtotal1")).toFixed(2);
    var deliveryAmount=parseFloat(sessionStorage.getItem("fooddelivery")).toFixed(2);
    var discountAmount=parseFloat(sessionStorage.getItem("fooddiscount")).toFixed(2);
    var taxAmount=parseFloat(sessionStorage.getItem("foodtax")).toFixed(2);
    var couponAmount=parseFloat(sessionStorage.getItem("couponAmount")).toFixed(2);
    var subtotal=sessionStorage.getItem("subtotal");
    var miscTax=calculateMiscTax(subtotal,'json');
    var  appid=localStorage.getItem("applicationID");
    var udetails= window.sessionStorage.getItem("udetails");
    var billing=window.sessionStorage.getItem("ubilling");
    var shipping=window.sessionStorage.getItem("ushipping");
    var discountInfo = '{"discount":"'+discountAmount+'","delivery":"'+deliveryAmount+'","tax":"'+taxAmount+'","total":"'+totalAmount+'","coupon":"'+couponAmount+'","tip":"'+parseFloat(sessionStorage.getItem("tipCharges")).toFixed(2)+'"}';
    var wsUrl = "http://"+localStorage.getItem("reseller")+"/services/food-soap#foodOrder";
    var soapRequest ='<?xml version="1.0" encoding="utf-8"?> <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"> <soap:Body><foodOrder xmlns=\"http://'+localStorage.getItem("reseller")+'/services/food-soap#foodOrder\"><appId>'+appid+'</appId><orderId>'+sessionStorage.getItem("foodRandomId")+'</orderId><email>'+window.sessionStorage.getItem("OrderEmailId")+'</email><discount>'+discountInfo+'</discount><order><![CDATA['+udetails+']]></order><billing>'+billing+'</billing><shipping>'+shipping+'</shipping><payment>'+ sessionStorage.getItem("paymentMethodForPaypal")+'</payment><pickupComment>'+sessionStorage.getItem('instructionsText')+'</pickupComment><miscTax>'+miscTax+'</miscTax></foodOrder></soap:Body></soap:Envelope>';
    var strJSON='';
    console.log("???????????????????????????????????????????????????????????"+soapRequest);
    jQuery.support.cors = true;
    $.ajax({
           type: "POST",
           url: wsUrl,
           contentType: "text/xml",
           dataType: "text",
           data: soapRequest,
           success: function(data, status, req)
           {
           console.log('rrrrr=>'+req.responseText);
           if(!localStorage.getItem("fooduserid"))
           {
           var userid=sessionStorage.getItem("OrderEmailId");
           localStorage.setItem("fooduserid",userid);
           accountDispl='block';
           loginDispl='none';
           }
           sessionStorage.clear();
           successView('Success');
           
           },
           error: function(response, textStatus, errorThrown)
           {
           console.log(response);
           successView('decline');
           setTimeout(function(){$('.appypie-loader').hide();},1000);
           }
           });
}

function successView(paymentResult,paymentType){
	
	

    $('.appypie-loader').show();
    $("#mainbackfoodecom").hide();
    //  sessionStorage.setItem("AppPageName","foodordering");
    
    var success_HTML='<div class="page-text"><div class="appypie-login thankyoupage"><h2 id="message"></h2><p id="mess">\
    <strong id="orderId">'+order_id_food+': </strong></p><div class="login-feald">\
    <a id="can" class="login_btn3" onclick="cancelOrder();">'+cccancel_food+'</a>\
    <a id="retry" class="login_btn" onclick="retryOder();">'+rrretry_food+'</a>\
    <a id="pay" class="login_btn1" '+primaryColor+' onclick="paymentSuccess();">'+cccontinue_food+'</a>\
    </div><ul class="sub-menu" style="display:none;" >\
    <li id="homeIcon" class="homeIconClass"><a onclick="home()" class="home-nav">'+home_food+'</a></li>\
    <li style="display:'+loginDispl+';"><a onclick="loginEcom()" class="login-nav">'+login_food+'</a></li>\
    <li style="display:'+accountDispl+';"><a onclick="myaccount()" class="account-nav">'+my_account_food+'</a></li>\
    <li><a onclick="goHome()" class="menu-nav">'+my_shop_food+'</a></li>\
	<li><a onclick="bindCart(\'mainpagefood\')" class="cart-nav">'+cartt_food+'</a></li>\
    <li><a onclick="termsEcom()" class="terms-nav">'+termscconditions_food+'</a></li>\
    <li><a onclick="PolicyEcom()" class="policy-nav">'+privacypolicy_food+'</a></li>\
    </ul>';
    
    appendHtml(success_HTML,10,7,'foodSuccess');
    if(paymentResult=='Success')
    {
        if(paymentType)
        {
            var message='You will receive an email, when your order is ready to be delivered';
            document.getElementById("message").innerHTML=wwvereceivedourorder_food;
        }
        else
        {
            var message='Thank you for your Order. Your transaction has been completed, and a receipt for your purchase has been emailed to you for your records<br><strong id="orderId"></strong><br><strong id='+window.sessionStorage.getItem("transectionId")+'></strong>';
            document.getElementById("message").innerHTML=yourorderwuccessfu_food;
            
        }
        
        document.getElementById("mess").innerHTML=message;
        
        
        if(sessionStorage.getItem("PaymentTypeFlag") == "paypal")
        {
            document.getElementById("orderId").innerHTML='Order ID:'+sessionStorage.getItem("foodOrderId");
            document.getElementById("PayId").innerHTML='Payment ID:'+sessionStorage.getItem("foodPaymentId");
        }
        document.getElementById("retry").style.display="none";
        document.getElementById("can").style.display="none";
    }
    else
    {
        var message='Your order could not be processed. Please try placing the order again.';
        document.getElementById("mess").innerHTML=message;
        document.getElementById("message").innerHTML=wwearesorry_food;
        document.getElementById("pay").style.display="none";
    }
}


function paymentSuccess()
{
    sessionStorage.removeItem("ushipping");
    sessionStorage.removeItem("ubilling");
    sessionStorage.removeItem("ushipping");
    myOrders();
    sessionStorage.removeItem("cart");
}
function cancelOrder()
{
    sessionStorage.removeItem("ushipping");
    sessionStorage.removeItem("ubilling");
    sessionStorage.removeItem("ushipping");
    
    if(localStorage.getItem("payTypeFoodOrEcom")=="food") {
        getFoodorderingData(sessionStorage.getItem("foodorderingIndex"));
    }
    else
    {
        getEcommerceData(0);
    }
}

function retryOder()
{
    paymentTypeView(localStorage.getItem("payTypeFoodOrEcom"));
}

