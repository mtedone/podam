package uk.co.jemos.podam.test.dto;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class CollectionIndirectRawImplPojo implements PodamRawList {

	private List<Object> list = new LinkedList<Object>();

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
	public Iterator<Object> iterator() {
		return list.iterator();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public Object[] toArray(Object[] a) {
		return list.toArray(a);
	}

	@Override
	public boolean add(Object e) {
		return list.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}

	@Override
	public boolean containsAll(@SuppressWarnings("rawtypes") Collection c) {
		return list.containsAll(c);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean addAll(@SuppressWarnings("rawtypes") Collection c) {
		return list.addAll(c);
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public boolean addAll(int index, @SuppressWarnings("rawtypes") Collection c) {
		return list.addAll(index, c);
	}

	@Override
	public boolean removeAll(@SuppressWarnings("rawtypes") Collection c) {
		return list.removeAll(c);
	}

	@Override
	public boolean retainAll(@SuppressWarnings("rawtypes") Collection c) {
		return list.retainAll(c);
	}

	@Override
	public void clear() {
		list.clear();
		
	}

	@Override
	public Object get(int index) {
		return list.get(index);
	}

	@Override
	public Object set(int index, Object element) {
		return list.set(index, element);
	}

	@Override
	public void add(int index, Object element) {
		list.add(index, element);
	}

	@Override
	public Object remove(int index) {
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
	public ListIterator<Object> listIterator() {
		return list.listIterator();
	}

	@Override
	public ListIterator<Object> listIterator(int index) {
		return list.listIterator(index);
	}

	@Override
	public List<Object> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}
}
