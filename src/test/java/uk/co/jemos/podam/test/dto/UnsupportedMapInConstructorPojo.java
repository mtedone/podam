/**
 *
 */
package uk.co.jemos.podam.test.dto;

import java.util.Hashtable;

/**
 * @author daivanov
 *
 */
public class UnsupportedMapInConstructorPojo<K,V> {

	private Hashtable<K,V> hashTable;

	public UnsupportedMapInConstructorPojo(Hashtable<K,V> hashTable) {
		this.hashTable = hashTable;
	}

	public Hashtable<K,V> getHashTable() {
		return hashTable;
	}
}
