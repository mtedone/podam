/**
 * 
 */
package uk.co.jemos.podam.test.unit;

import java.util.Calendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.api.RandomDataProviderStrategy;
import uk.co.jemos.podam.test.dto.docs.example.Address;
import uk.co.jemos.podam.test.dto.docs.example.Article;
import uk.co.jemos.podam.test.dto.docs.example.BankAccount;
import uk.co.jemos.podam.test.dto.docs.example.Client;
import uk.co.jemos.podam.test.dto.docs.example.Country;
import uk.co.jemos.podam.test.dto.docs.example.Order;
import uk.co.jemos.podam.test.dto.docs.example.OrderItem;

/**
 * A set of tests to check the Walk-through example
 * 
 * @author mtedone
 * 
 */
public class WalkThroughExampleUnitTest {

	// ------------------->> Constants

	// ------------------->> Instance / Static variables

	/** The Podam Factory */
	private PodamFactory factory;

	/** The default data provider strategy */
	private final DataProviderStrategy strategy = RandomDataProviderStrategy
			.getInstance();

	// ------------------->> Constructors

	// ------------------->> Public methods

	@Before
	public void init() {

		factory = new PodamFactoryImpl(strategy);
	}

	@Test
	public void testCountrySetup() {

		Country pojo = factory.manufacturePojo(Country.class);
		validateCountry(pojo);

	}

	@Test
	public void testArticleSetup() {

		Article pojo = factory.manufacturePojo(Article.class);
		validateArticle(pojo);
	}

	@Test
	public void testOrderItemSetup() {

		OrderItem pojo = factory.manufacturePojo(OrderItem.class);

		validateOrderItem(pojo);

	}

	@Test
	public void testOrderSetup() {

		Order pojo = factory.manufacturePojo(Order.class);

		validateOrder(pojo);

	}

	@Test
	public void testAddressSetup() {

		Address pojo = factory.manufacturePojo(Address.class);

		validateAddress(pojo);

	}

	@Test
	public void testBankAccountSetup() {

		BankAccount pojo = factory.manufacturePojo(BankAccount.class);
		validateBankAccount(pojo);

	}

	@Test
	public void testClientSetup() {

		Client pojo = factory.manufacturePojo(Client.class);
		Assert.assertNotNull("The pojo cannot be null!", pojo);

		Assert.assertNotNull("The client's first name cannot be null!",
				pojo.getFirstName());
		Assert.assertTrue("The client's first name cannot be empty!", pojo
				.getFirstName().length() > 0);

		String expectedFirstName = "Michael";
		Assert.assertEquals("The client's first name is not "
				+ expectedFirstName, expectedFirstName, pojo.getFirstName());

		Assert.assertNotNull("The client's last name cannot be null!",
				pojo.getLastName());
		Assert.assertTrue("The client's last name cannot be empty!", pojo
				.getLastName().length() > 0);

		Assert.assertNotNull("The date created cannot be null!",
				pojo.getDateCreated());

		List<Order> orders = pojo.getOrders();
		Assert.assertNotNull("The orders cannot be null!", orders);
		int expectedOrdersNbr = 3;
		Assert.assertTrue("The expected number of orders is "
				+ expectedOrdersNbr, orders.size() == expectedOrdersNbr);

		for (Order order : orders) {
			validateOrder(order);
		}

		List<Address> addresses = pojo.getAddresses();
		Assert.assertNotNull("The addresses cannot be null!", addresses);
		int expectedAddressesNbr = 2;
		Assert.assertTrue("The expected number of addresses is "
				+ expectedAddressesNbr,
				addresses.size() == expectedAddressesNbr);

		for (Address address : addresses) {
			validateAddress(address);
		}

		List<BankAccount> bankAccounts = pojo.getBankAccounts();
		Assert.assertNotNull("The bank accounts cannot be null!", bankAccounts);
		int expectedBankAccountsNbr = strategy.getNumberOfCollectionElements();
		Assert.assertTrue("The expected number of addresses is "
				+ expectedBankAccountsNbr,
				bankAccounts.size() == expectedBankAccountsNbr);

		for (BankAccount bankAccount : bankAccounts) {
			validateBankAccount(bankAccount);
		}

	}

	// ------------------->> Getters / Setters

	// ------------------->> Private methods

	/**
	 * It validates a {@link BankAccount} POJO
	 * 
	 * @param pojo
	 */
	private void validateBankAccount(BankAccount pojo) {

		Assert.assertNotNull("The bank account cannot be null!", pojo);

		Assert.assertTrue("The bank account cannot be zero!",
				pojo.getAccount() != 0);
		Assert.assertTrue("The account balance cannot be zero!",
				pojo.getBalance() != 0.0);
		Assert.assertNotNull("The bank name cannot be null!", pojo.getBank());
		Assert.assertTrue("The bank name cannot be empty!", pojo.getBank()
				.length() > 0);
		Assert.assertNotNull("The sort code cannot be null!",
				pojo.getSortCode());
		Assert.assertTrue("The sort code cannot be empty!", pojo.getSortCode()
				.length() > 0);

	}

	/**
	 * It validates an {@link Address} POJO
	 * 
	 * @param pojo
	 */
	private void validateAddress(Address pojo) {

		Assert.assertNotNull("The address cannot be null!", pojo);
		Assert.assertNotNull("The address1 cannot be null!", pojo.getAddress1());
		Assert.assertNotNull("The address2 cannot be null!", pojo.getAddress2());
		Assert.assertNotNull("The city cannot be null!", pojo.getCity());
		Assert.assertNotNull("The zipCode cannot be null!", pojo.getZipCode());

		validateCountry(pojo.getCountry());

	}

	/**
	 * @param pojo
	 */
	private void validateOrder(Order pojo) {

		Assert.assertNotNull("The pojo cannot be null!", pojo);

		Assert.assertTrue("The order id must not be zero!", pojo.getId() != 0);

		Calendar createDate = pojo.getCreateDate();
		Assert.assertNotNull("The create date must not be null!", createDate);

		Assert.assertTrue("The order total amount must not be zero!",
				pojo.getTotalAmount() != 0.0);

		List<OrderItem> orderItems = pojo.getOrderItems();
		Assert.assertNotNull("The order items must not be null!", orderItems);
		Assert.assertFalse("The order items must not be empty!",
				orderItems.isEmpty());

		int expectedNbrElements = 5;

		Assert.assertTrue("The expected number of elements "
				+ expectedNbrElements + " does not match the actual number: "
				+ orderItems.size(), orderItems.size() == expectedNbrElements);

		for (OrderItem orderItem : orderItems) {

			validateOrderItem(orderItem);

		}

	}

	/**
	 * It validates an {@link OrderItem} POJO
	 * 
	 * @param pojo
	 */
	private void validateOrderItem(OrderItem pojo) {

		Assert.assertNotNull("The pojo cannot be null!", pojo);

		Assert.assertTrue("The order item id cannot be zero!",
				pojo.getId() != 0);

		Assert.assertTrue("The order item line amount cannot be zero!",
				pojo.getLineAmount() != 0.0);

		Assert.assertNull(
				"The Order Item note must be null because of @PodamExclude annotation",
				pojo.getNote());

		validateArticle(pojo.getArticle());

	}

	/**
	 * It validates an {@link Article} POJO
	 * 
	 * @param pojo
	 */
	private void validateArticle(Article pojo) {
		Assert.assertNotNull("The pojo cannot be null!", pojo);

		int expectedMaxValue = 100000;

		Assert.assertTrue("The article id max value must not exceed "
				+ expectedMaxValue, pojo.getId() <= expectedMaxValue);

		Assert.assertNotNull("The item cost cannot be null!",
				pojo.getItemCost());
		Assert.assertTrue(
				"The item cost must have a value different from zero!",
				pojo.getItemCost() != 0);

		Assert.assertNotNull("The article description cannot be null!",
				pojo.getDescription());
	}

	/**
	 * It validates a {@link Country} POJO
	 * 
	 * @param pojo
	 */
	private void validateCountry(Country pojo) {
		Assert.assertNotNull("The pojo cannot be null!", pojo);

		String countryCode = pojo.getCountryCode();
		Assert.assertNotNull("The country Code cannot be null!", countryCode);
		int countryCodeLength = 2;
		Assert.assertTrue("The length of the country code must be "
				+ countryCodeLength + "! but was " + countryCode.length(),
				countryCode.length() == countryCodeLength);

		Assert.assertTrue("country id must be different from zero!",
				pojo.getCountryId() != 0);

		Assert.assertNotNull("The country description must not be null!",
				pojo.getDescription());
		Assert.assertTrue("The country description must not be empty!", pojo
				.getDescription().length() > 0);
	}

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}
