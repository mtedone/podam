/**
 * 
 */
package uk.co.jemos.podam.test.unit.features.walkThroughExample;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.test.dto.docs.example.*;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;

import java.util.List;

/**
 * A set of tests to check the Walk-through example
 * 
 * @author mtedone
 * 
 */
@RunWith(SerenityRunner.class)
public class WalkThroughExampleUnitTest extends AbstractPodamSteps {



	@Test
	@Title("Podam should fill in the Country POJO correctly")
	public void testCountrySetup() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

		Country pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(Country.class, podamFactory);
		walkThroughSteps.theCountryPojoShouldBeCorrectlyFilled(pojo);

	}

	@Test
	@Title("Podam should fill the Article POJO correctly")
	public void testArticleSetup() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();
		Article pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(Article.class, podamFactory);
		walkThroughSteps.theArticlePojoShouldBeCorrectlyFilled(pojo);
	}

	@Test
	@Title("Podam should fill the OrderItem POJO correctly")
	public void testOrderItemSetup() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

		OrderItem pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(OrderItem.class, podamFactory);

		walkThroughSteps.theOrderItemPojoShouldBeCorrectlyFilled(pojo);

	}

	@Test
	@Title("Podam should fill in the Order POJO correctly")
	public void testOrderSetup() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

		Order pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(Order.class, podamFactory);

		walkThroughSteps.theOrderPojoShouldBeCorrectlyFilled(pojo);

	}

	@Test
	@Title("Podam should fill in the Address POJO correctly")
	public void testAddressSetup() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

		Address pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(Address.class, podamFactory);

		walkThroughSteps.theAddressPojoShouldBeCorrectlyFilled(pojo);

	}

	@Test
	@Title("Podam should fill in the Bank Account POJO correctly")
	public void testBankAccountSetup() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

		BankAccount pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(BankAccount.class, podamFactory);
		walkThroughSteps.theBankAccountPojoShouldBeCorrectlyFilled(pojo);

	}

	@Test
	@Title("Podam should fill the Client POJO correctly")
	public void testClientSetup() throws Exception {

		PodamFactory podamFactory = podamFactorySteps.givenAStandardPodamFactory();

		Client pojo = podamInvocationSteps.whenIInvokeTheFactoryForClass(Client.class, podamFactory);
		walkThroughSteps.theClientPojoShouldBeCorrectlyFilled(pojo);
		List<BankAccount> bankAccounts = pojo.getBankAccounts();
		podamValidationSteps.theCollectionShouldNotBeNullOrEmpty(bankAccounts);
		podamValidationSteps.theTwoObjectsShouldBeEqual(
				podamFactory.getStrategy().getNumberOfCollectionElements(BankAccount.class), bankAccounts.size()
		);
	}

	// ------------------->> Getters / Setters

	// ------------------->> Private methods


}
