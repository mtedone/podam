package uk.co.jemos.podam.test.unit.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import net.thucydides.core.annotations.Step;
import uk.co.jemos.podam.test.dto.docs.example.Address;
import uk.co.jemos.podam.test.dto.docs.example.Article;
import uk.co.jemos.podam.test.dto.docs.example.BankAccount;
import uk.co.jemos.podam.test.dto.docs.example.Client;
import uk.co.jemos.podam.test.dto.docs.example.Country;
import uk.co.jemos.podam.test.dto.docs.example.Order;
import uk.co.jemos.podam.test.dto.docs.example.OrderItem;

import java.util.Calendar;
import java.util.List;

/**
 * Created by tedonema on 21/06/2015.
 */
public class WalkThroughSteps {


    @Step("Then the BankAccount POJO should be correctly filled")
    public void theBankAccountPojoShouldBeCorrectlyFilled(BankAccount pojo) {

        assertThat("The bank account cannot be null!", pojo, not(nullValue()));

        assertThat("The bank account cannot be zero!",
                pojo.getAccount(), not(equalTo(0)));
        assertThat("The account balance cannot be zero!",
                pojo.getBalance(), not(equalTo(0.0)));
        assertThat("The bank name cannot be empty!", pojo.getBank(),
                not(isEmptyOrNullString()));
        assertThat("The sort code cannot be empty!", pojo.getSortCode(),
                not(isEmptyOrNullString()));

    }

    @Step("Then the Address POJO should be correctly filled")
    public void theAddressPojoShouldBeCorrectlyFilled(Address pojo) {

        assertThat("The address cannot be null!", pojo, not(nullValue()));
        assertThat("The address1 cannot be null!", pojo.getAddress1(), not(nullValue()));
        assertThat("The address2 cannot be null!", pojo.getAddress2(), not(nullValue()));
        assertThat("The city cannot be null!", pojo.getCity(), not(nullValue()));
        assertThat("The zipCode cannot be null!", pojo.getZipCode(), not(nullValue()));

        theCountryPojoShouldBeCorrectlyFilled(pojo.getCountry());

    }


    @Step("Then the Order POJO should be correctly filled")
    public void theOrderPojoShouldBeCorrectlyFilled(Order pojo) {

        assertThat("The pojo cannot be null!", pojo, not(nullValue()));

        assertThat("The order id must not be zero!", pojo.getId(), not(equalTo(0)));

        Calendar createDate = pojo.getCreateDate();
        assertThat("The create date must not be null!", createDate, not(nullValue()));

        assertThat("The order total amount must not be zero!",
                pojo.getTotalAmount(), not(equalTo(0.0)));

        List<OrderItem> orderItems = pojo.getOrderItems();
        assertThat("The order items must not be null!", orderItems, not(nullValue()));
        assertThat("The order items must not be empty!",
                orderItems, is(not(empty())));

        int expectedNbrElements = 5;

        assertThat("The expected number of elements "
                + expectedNbrElements + " does not match the actual number: "
                + orderItems.size(), orderItems, hasSize(expectedNbrElements));

        for (OrderItem orderItem : orderItems) {

            theOrderItemPojoShouldBeCorrectlyFilled(orderItem);

        }

    }


    @Step("Then the OrderItem POJO should be correctly filled")
    public void theOrderItemPojoShouldBeCorrectlyFilled(OrderItem pojo) {

        assertThat("The pojo cannot be null!", pojo, not(nullValue()));

        assertThat("The order item id cannot be zero!",
                pojo.getId(), not(equalTo(0)));

        assertThat("The order item line amount cannot be zero!",
                pojo.getLineAmount(), not(equalTo(0.0)));

        assertThat(
                "The Order Item note must be null because of @PodamExclude annotation",
                pojo.getNote(), is(nullValue()));

        theArticlePojoShouldBeCorrectlyFilled(pojo.getArticle());

    }

    @Step("Then the Article POJO should be correctly filled")
    public void theArticlePojoShouldBeCorrectlyFilled(Article pojo) {
        assertThat("The pojo cannot be null!", pojo, not(nullValue()));

        int expectedMaxValue = 100000;

        assertThat("The article id max value must not exceed "
                + expectedMaxValue, pojo.getId(), lessThanOrEqualTo(expectedMaxValue));

        assertThat("The item cost cannot be null!",
                pojo.getItemCost(), not(nullValue()));
        assertThat(
                "The item cost must have a value different from zero!",
                pojo.getItemCost(), not(equalTo(0.0)));

        assertThat("The article description cannot be null!",
                pojo.getDescription(), not(nullValue()));
    }

    @Step("Then the Country POJO should be correctly filled")
    public void theCountryPojoShouldBeCorrectlyFilled(Country pojo) {
        assertThat("The pojo cannot be null!", pojo, not(nullValue()));

        String countryCode = pojo.getCountryCode();
        assertThat("The country Code cannot be null!", countryCode, not(nullValue()));
        int countryCodeLength = 2;
        assertThat("The length of the country code must be "
                        + countryCodeLength + "! but was " + countryCode.length(),
                countryCode.length(), equalTo(countryCodeLength));

        assertThat("country id must be different from zero!",
                pojo.getCountryId(), not(equalTo(0)));

        assertThat("The country description must not be empty!", pojo
                .getDescription(), not(isEmptyOrNullString()));
    }

    @Step("Then the Client POJO should be correctly filled")
    public void theClientPojoShouldBeCorrectlyFilled(Client pojo) {
        assertThat("The pojo cannot be null!", pojo, not(nullValue()));

        assertThat("The client's first name cannot be empty!", pojo
                .getFirstName(), not(isEmptyOrNullString()));

        String expectedFirstName = "Michael";
        assertThat("The client's first name is not "
                + expectedFirstName, pojo.getFirstName(), equalTo(expectedFirstName));

        assertThat("The client's last name cannot be empty!", pojo
                .getLastName(), not(isEmptyOrNullString()));

        assertThat("The date created cannot be null!",
                pojo.getDateCreated(), not(nullValue()));

        List<Order> orders = pojo.getOrders();
        assertThat("The orders cannot be null!", orders, not(nullValue()));
        int expectedOrdersNbr = 3;
        assertThat("The expected number of orders is "
                + expectedOrdersNbr, orders, hasSize(expectedOrdersNbr));

        for (Order order : orders) {
            theOrderPojoShouldBeCorrectlyFilled(order);
        }

        List<Address> addresses = pojo.getAddresses();
        assertThat("The addresses cannot be null!", addresses, not(nullValue()));
        int expectedAddressesNbr = 2;
        assertThat("The expected number of addresses is "
                        + expectedAddressesNbr,
                addresses, hasSize(expectedAddressesNbr));

        for (Address address : addresses) {
            theAddressPojoShouldBeCorrectlyFilled(address);
        }

        List<BankAccount> bankAccounts = pojo.getBankAccounts();

        for (BankAccount bankAccount : bankAccounts) {
            theBankAccountPojoShouldBeCorrectlyFilled(bankAccount);
        }


    }

}
