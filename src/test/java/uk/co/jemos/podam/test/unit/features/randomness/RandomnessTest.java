package uk.co.jemos.podam.test.unit.features.randomness;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;

import org.junit.Test;
import org.junit.runner.RunWith;

import uk.co.jemos.podam.api.PodamUtils;
import uk.co.jemos.podam.test.unit.AbstractPodamSteps;

/**
 * Created by daivanov on 05/02/2017.
 */
@RunWith(SerenityRunner.class)
public class RandomnessTest extends AbstractPodamSteps {

	@Test
	@Title("Podam should generate evenly distributed random integers")
	public void podamShouldGenerateEvenlyDistributedRandomIntegers() throws Exception {
		final int cnt = 10;
		final int runs = 1000000;
		int[] counters = new int[cnt];

		for (int i = 0; i < runs; i++) {
			int idx = (int)PodamUtils.getLongInRange(0, cnt - 1);
			counters[idx]++;
		}

		double mse = 0;
		double expected = runs / cnt;
		for (int i = 0; i < cnt; i++) {
			double error = 100 * (counters[i] - expected) / expected;
			mse += (error * error / cnt);
		}
		podamValidationSteps.theDoubleValueShouldBeBetween(mse, 0.0, 1.0);
	}

}
