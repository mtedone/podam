/**
 * 
 */
package uk.co.jemos.podam.test.dto;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Test pojo
 * <p>
 * Pojo extending Pojo with generic type.
 * </p>
 * 
 * @author daivanov
 * 
 */
public class CollectionImplementingGenericsInterface implements List<String> {

	private List<String> list = new LinkedList<String>();

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@Override
	public Iterator<String> iterator() {
		return list.iterator();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	@Override
	public boolean add(String e) {
		return list.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends String> c) {
		return list.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends String> c) {
		return list.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

	@Override
	public void clear() {
		list.clear();
		
	}

	@Override
	public String get(int index) {
		return list.get(index);
	}

	@Override
	public String set(int index, String element) {
		return list.set(index, element);
	}

	@Override
	public void add(int index, String element) {
		list.add(index, element);
	}

	@Override
	public String remove(int index) {
		return list.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return list.indexOf(o);
	}

	@Override
	public ListIterator<String> listIterator() {
		return list.listIterator();
	}

	@Override
	public ListIterator<String> listIterator(int index) {
		return list.listIterator(index);
	}

	@Override
	public List<String> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}
}
