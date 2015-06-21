package uk.co.jemos.podam.test.unit.steps;


import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import uk.co.jemos.podam.test.dto.docs.example.*;

import java.util.Calendar;
import java.util.List;

/**
 * Created by tedonema on 21/06/2015.
 */
public class WalkThroughSteps {


    @Step("Then the BankAccount POJO should be correctly filled")
    public void theBankAccountPojoShouldBeCorrectlyFilled(BankAccount pojo) {

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

    @Step("Then the Address POJO should be correctly filled")
    public void theAddressPojoShouldBeCorrectlyFilled(Address pojo) {

        Assert.assertNotNull("The address cannot be null!", pojo);
        Assert.assertNotNull("The address1 cannot be null!", pojo.getAddress1());
        Assert.assertNotNull("The address2 cannot be null!", pojo.getAddress2());
        Assert.assertNotNull("The city cannot be null!", pojo.getCity());
        Assert.assertNotNull("The zipCode cannot be null!", pojo.getZipCode());

        theCountryPojoShouldBeCorrectlyFilled(pojo.getCountry());

    }


    @Step("Then the Order POJO should be correctly filled")
    public void theOrderPojoShouldBeCorrectlyFilled(Order pojo) {

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

            theOrderItemPojoShouldBeCorrectlyFilled(orderItem);

        }

    }


    @Step("Then the OrderItem POJO should be correctly filled")
    public void theOrderItemPojoShouldBeCorrectlyFilled(OrderItem pojo) {

        Assert.assertNotNull("The pojo cannot be null!", pojo);

        Assert.assertTrue("The order item id cannot be zero!",
                pojo.getId() != 0);

        Assert.assertTrue("The order item line amount cannot be zero!",
                pojo.getLineAmount() != 0.0);

        Assert.assertNull(
                "The Order Item note must be null because of @PodamExclude annotation",
                pojo.getNote());

        theArticlePojoShouldBeCorrectlyFilled(pojo.getArticle());

    }

    @Step("Then the Article POJO should be correctly filled")
    public void theArticlePojoShouldBeCorrectlyFilled(Article pojo) {
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

    @Step("Then the Country POJO should be correctly filled")
    public void theCountryPojoShouldBeCorrectlyFilled(Country pojo) {
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
    
    @Step("Then the Client POJO should be correctly filled")
    public void theClientPojoShouldBeCorrectlyFilled(Client pojo) {
        
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
            theOrderPojoShouldBeCorrectlyFilled(order);
        }

        List<Address> addresses = pojo.getAddresses();
        Assert.assertNotNull("The addresses cannot be null!", addresses);
        int expectedAddressesNbr = 2;
        Assert.assertTrue("The expected number of addresses is "
                        + expectedAddressesNbr,
                addresses.size() == expectedAddressesNbr);

        for (Address address : addresses) {
            theAddressPojoShouldBeCorrectlyFilled(address);
        }

        List<BankAccount> bankAccounts = pojo.getBankAccounts();

        for (BankAccount bankAccount : bankAccounts) {
            theBankAccountPojoShouldBeCorrectlyFilled(bankAccount);
        }


    }

}
