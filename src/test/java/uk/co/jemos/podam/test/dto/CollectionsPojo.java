/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A test POJO dedicated to collections
 * 
 * @author mtedone
 * 
 */
public class CollectionsPojo {

	// ------------------->> Constants

	// ------------------->> Instance / Static variables

	/** A List of Strings */
	private List<String> strList = new ArrayList<String>();

	/** A list implementation of strings */
	private ArrayList<String> arrayListStr = new ArrayList<String>();

	/** A concurrency list */
	private List<String> copyOnWriteList = new CopyOnWriteArrayList<String>();

	/** A set of strings */
	private Set<String> strSet = new HashSet<String>();

	/** A set implementation of strings */
	private HashSet<String> hashSetStr = new HashSet<String>();

	/** A collection of Strings */
	private Collection<String> strCollection = new ArrayList<String>();

	/** A Map of Strings vs Pojos */
	private Map<String, OneDimensionalTestPojo> map = new HashMap<String, OneDimensionalTestPojo>();

	/** A map implementation of Strings */
	private HashMap<String, OneDimensionalTestPojo> hashMap = new HashMap<String, OneDimensionalTestPojo>();

	/** A Concurrent Map */
	private ConcurrentMap<String, OneDimensionalTestPojo> concurrentHashMap = new ConcurrentHashMap<String, OneDimensionalTestPojo>();

	/** A Concurrent Map */
	private ConcurrentHashMap<String, OneDimensionalTestPojo> concurrentHashMapImpl = new ConcurrentHashMap<String, OneDimensionalTestPojo>();

	/** A non-initialised Queue */
	private Queue<SimplePojoToTestSetters> queue;

	/** A non-generified list */
	@SuppressWarnings("rawtypes")
	private List nonGenerifiedList;

	/** A non-generified map */
	@SuppressWarnings("rawtypes")
	private Map nonGenerifiedMap;

	// ------------------->> Constructors

	// ------------------->> Public methods

	// ------------------->> Getters / Setters

	/**
	 * @return the strList
	 */
	public List<String> getStrList() {
		return strList;
	}

	/**
	 * @return the arrayListStr
	 */
	public ArrayList<String> getArrayListStr() {
		return arrayListStr;
	}

	/**
	 * @return the strSet
	 */
	public Set<String> getStrSet() {
		return strSet;
	}

	/**
	 * @return the hashSetStr
	 */
	public HashSet<String> getHashSetStr() {
		return hashSetStr;
	}

	/**
	 * @return the strCollection
	 */
	public Collection<String> getStrCollection() {
		return strCollection;
	}

	/**
	 * @return the map
	 */
	public Map<String, OneDimensionalTestPojo> getMap() {
		return map;
	}

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	/**
	 * @param strList
	 *            the strList to set
	 */
	public void setStrList(List<String> strList) {
		this.strList = strList;
	}

	/**
	 * @param arrayListStr
	 *            the arrayListStr to set
	 */
	public void setArrayListStr(ArrayList<String> arrayListStr) {
		this.arrayListStr = arrayListStr;
	}

	/**
	 * @param strSet
	 *            the strSet to set
	 */
	public void setStrSet(Set<String> strSet) {
		this.strSet = strSet;
	}

	/**
	 * @param hashSetStr
	 *            the hashSetStr to set
	 */
	public void setHashSetStr(HashSet<String> hashSetStr) {
		this.hashSetStr = hashSetStr;
	}

	/**
	 * @param strCollection
	 *            the strCollection to set
	 */
	public void setStrCollection(Collection<String> strCollection) {
		this.strCollection = strCollection;
	}

	/**
	 * @param map
	 *            the map to set
	 */
	public void setMap(Map<String, OneDimensionalTestPojo> map) {
		this.map = map;
	}

	/**
	 * @return the copyOnWriteList
	 */
	public List<String> getCopyOnWriteList() {
		return copyOnWriteList;
	}

	/**
	 * @param copyOnWriteList
	 *            the copyOnWriteList to set
	 */
	public void setCopyOnWriteList(List<String> copyOnWriteList) {
		this.copyOnWriteList = copyOnWriteList;
	}

	/**
	 * @return the hashMap
	 */
	public HashMap<String, OneDimensionalTestPojo> getHashMap() {
		return hashMap;
	}

	/**
	 * @param hashMap
	 *            the hashMap to set
	 */
	public void setHashMap(HashMap<String, OneDimensionalTestPojo> hashMap) {
		this.hashMap = hashMap;
	}

	/**
	 * @return the concurrentHashMap
	 */
	public ConcurrentMap<String, OneDimensionalTestPojo> getConcurrentHashMap() {
		return concurrentHashMap;
	}

	/**
	 * @param concurrentHashMap
	 *            the concurrentHashMap to set
	 */
	public void setConcurrentHashMap(
			ConcurrentMap<String, OneDimensionalTestPojo> concurrentHashMap) {
		this.concurrentHashMap = concurrentHashMap;
	}

	/**
	 * @return the concurrentHashMapImpl
	 */
	public ConcurrentHashMap<String, OneDimensionalTestPojo> getConcurrentHashMapImpl() {
		return concurrentHashMapImpl;
	}

	/**
	 * @param concurrentHashMapImpl
	 *            the concurrentHashMapImpl to set
	 */
	public void setConcurrentHashMapImpl(
			ConcurrentHashMap<String, OneDimensionalTestPojo> concurrentHashMapImpl) {
		this.concurrentHashMapImpl = concurrentHashMapImpl;
	}

	/**
	 * @return the queue
	 */
	public Queue<SimplePojoToTestSetters> getQueue() {
		return queue;
	}

	/**
	 * @param queue
	 *            the queue to set
	 */
	public void setQueue(Queue<SimplePojoToTestSetters> queue) {
		this.queue = queue;
	}

	/**
	 * @return the nonGenerifiedList
	 */
	@SuppressWarnings("rawtypes")
	public List getNonGenerifiedList() {
		return nonGenerifiedList;
	}

	/**
	 * @param nonGenerifiedList
	 *            the nonGenerifiedList to set
	 */
	@SuppressWarnings("rawtypes")
	public void setNonGenerifiedList(List nonGenerifiedList) {
		this.nonGenerifiedList = nonGenerifiedList;
	}

	/**
	 * @return the nonGenerifiedMap
	 */
	@SuppressWarnings("rawtypes")
	public Map getNonGenerifiedMap() {
		return nonGenerifiedMap;
	}

	/**
	 * @param nonGenerifiedMap
	 *            the nonGenerifiedMap to set
	 */
	@SuppressWarnings("rawtypes")
	public void setNonGenerifiedMap(Map nonGenerifiedMap) {
		this.nonGenerifiedMap = nonGenerifiedMap;
	}

	// ------------------->> Inner classes

}
