package uk.co.jemos.podam.test.strategies;

import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;

import uk.co.jemos.podam.common.AttributeStrategy;

public class ByteArrayStrategy implements AttributeStrategy<byte[]> {

	public static int LENGTH = 20;

	@Override
	public byte[] getValue(Class<?> attrType, List<Annotation> annotations) {
		byte[] b = new byte[LENGTH];
		ThreadLocalRandom.current().nextBytes(b);
		return b;
	}
}
