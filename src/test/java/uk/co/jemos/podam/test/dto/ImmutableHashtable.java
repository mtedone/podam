package uk.co.jemos.podam.test.dto;

import java.util.Hashtable;
import java.util.Map;

/**
 * @author daivanov
 *
 */
public class ImmutableHashtable<K,V> extends Hashtable<K,V> {
	private static final long serialVersionUID = -1L;

	@Override
	public V put(K key, V value) {
		throw new UnsupportedOperationException("Immutable hashtable");
	}

	@Override
	public V remove(Object key) {
		throw new UnsupportedOperationException("Immutable hashtable");
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		throw new UnsupportedOperationException("Immutable hashtable");
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException("Immutable hashtable");
	}
}
