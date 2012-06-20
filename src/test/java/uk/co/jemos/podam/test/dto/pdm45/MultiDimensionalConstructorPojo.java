package uk.co.jemos.podam.test.dto.pdm45;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * This is POJO to test PODAM's ability to create an instance
 * using a constructor with multidimensional collections, arrays
 * and maps parameters
 * 
 * @author marciocarmona
 * 
 */
public class MultiDimensionalConstructorPojo extends MultiDimensionalTestPojo {

	public MultiDimensionalConstructorPojo(
			List<List<List<String>>> threeDimensionalList,
			Set<Set<Set<Double>>> threeDimensionalSet,
			Collection<Collection<Collection<Long>>> threeDimensionalCollection,
			Map<Boolean, Map<Float, Map<Integer, Calendar>>> threeDimensionalMap,
			Queue<Queue<Queue<Date>>> threeDimensionalQueue,
			String[][][] threeDimensionalArray) {
		setThreeDimensionalArray(threeDimensionalArray);
		setThreeDimensionalCollection(threeDimensionalCollection);
		setThreeDimensionalList(threeDimensionalList);
		setThreeDimensionalMap(threeDimensionalMap);
		setThreeDimensionalQueue(threeDimensionalQueue);
		setThreeDimensionalSet(threeDimensionalSet);
	}
}
