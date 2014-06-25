package uk.co.jemos.podam.test.dto;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import uk.co.jemos.podam.common.PodamCollection;

/**
 * This is POJO to test PODAM's ability to create instances
 * of multidimensional collections, arrays and maps
 * 
 * @author mcarmona
 * 
 */
public class MultiDimensionalTestPojo {

	@PodamCollection(nbrElements = 2)
	private List<List<List<String>>> threeDimensionalList;

	@PodamCollection(nbrElements = 2)
	private Set<Set<Set<Double>>> threeDimensionalSet;

	@PodamCollection(nbrElements = 2)
	private Collection<Collection<Collection<Long>>> threeDimensionalCollection;

	@PodamCollection(nbrElements = 2)
	private Map<Boolean, Map<Float, Map<Integer, Calendar>>> threeDimensionalMap;
	
	@PodamCollection(nbrElements = 2)
	private Queue<Queue<Queue<Date>>> threeDimensionalQueue;
	
	@PodamCollection(nbrElements = 2)
	private String[][][] threeDimensionalArray;

	/**
	 * @return the threeDimensionalList
	 */
	public List<List<List<String>>> getThreeDimensionalList() {
		return threeDimensionalList;
	}

	/**
	 * @param threeDimensionalList the threeDimensionalList to set
	 */
	public void setThreeDimensionalList(
			List<List<List<String>>> threeDimensionalList) {
		this.threeDimensionalList = threeDimensionalList;
	}

	/**
	 * @return the threeDimensionalSet
	 */
	public Set<Set<Set<Double>>> getThreeDimensionalSet() {
		return threeDimensionalSet;
	}

	/**
	 * @param threeDimensionalSet the threeDimensionalSet to set
	 */
	public void setThreeDimensionalSet(Set<Set<Set<Double>>> threeDimensionalSet) {
		this.threeDimensionalSet = threeDimensionalSet;
	}

	/**
	 * @return the threeDimensionalCollection
	 */
	public Collection<Collection<Collection<Long>>> getThreeDimensionalCollection() {
		return threeDimensionalCollection;
	}

	/**
	 * @param threeDimensionalCollection the threeDimensionalCollection to set
	 */
	public void setThreeDimensionalCollection(
			Collection<Collection<Collection<Long>>> threeDimensionalCollection) {
		this.threeDimensionalCollection = threeDimensionalCollection;
	}

	/**
	 * @return the threeDimensionalMap
	 */
	public Map<Boolean, Map<Float, Map<Integer, Calendar>>> getThreeDimensionalMap() {
		return threeDimensionalMap;
	}

	/**
	 * @param threeDimensionalMap the threeDimensionalMap to set
	 */
	public void setThreeDimensionalMap(
			Map<Boolean, Map<Float, Map<Integer, Calendar>>> threeDimensionalMap) {
		this.threeDimensionalMap = threeDimensionalMap;
	}

	/**
	 * @return the threeDimensionalQueue
	 */
	public Queue<Queue<Queue<Date>>> getThreeDimensionalQueue() {
		return threeDimensionalQueue;
	}

	/**
	 * @param threeDimensionalQueue the threeDimensionalQueue to set
	 */
	public void setThreeDimensionalQueue(
			Queue<Queue<Queue<Date>>> threeDimensionalQueue) {
		this.threeDimensionalQueue = threeDimensionalQueue;
	}

	/**
	 * @return the threeDimensionalArray
	 */
	public String[][][] getThreeDimensionalArray() {
		return threeDimensionalArray;
	}

	/**
	 * @param threeDimensionalArray the threeDimensionalArray to set
	 */
	public void setThreeDimensionalArray(String[][][] threeDimensionalArray) {
		this.threeDimensionalArray = threeDimensionalArray;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MultiDimensionalTestPojo\n"
				+ "[threeDimensionalList=" + threeDimensionalList + ",\n"
				+ "threeDimensionalSet=" + threeDimensionalSet + ",\n"
				+ "threeDimensionalCollection=" + threeDimensionalCollection + ",\n"
				+ "threeDimensionalMap=" + threeDimensionalMap + ",\n"
				+ "threeDimensionalQueue=" + threeDimensionalQueue + ",\n"
				+ "threeDimensionalArray=" + printArrayRecursively(threeDimensionalArray) + "]";
	}
	
	/**
	 * Prints an multidimensional array recursively.
	 * 
	 * @param array
	 *            the array to print
	 * @return the printed array
	 */
	private String printArrayRecursively(Object[] array) {
		StringBuilder sb = new StringBuilder("[");
		for (Object object : array) {
			if (object.getClass().isArray()) {
				sb.append(printArrayRecursively((Object[]) object));
			} else {
				sb.append(object.toString());
			}
			sb.append(", ");
		}
		sb.setLength(sb.length() - 2);
		sb.append("]");
		
		return sb.toString();
	}

}
