package uk.co.jemos.podam.test.dto;

import java.util.Collection;
import java.util.Vector;

/**
 * @author daivanov
 *
 */
public class ImmutableVector<E> extends Vector<E> {
	private static final long serialVersionUID = 1L;

	@Override
	public boolean add(E e) {
		throw new UnsupportedOperationException("Immutable vector");
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException("Immutable vector");
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException("Immutable vector");
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException("Immutable vector");
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException("Immutable vector");
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException("Immutable vector");
	}
}
