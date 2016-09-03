package uk.co.jemos.podam.typeManufacturers;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.exceptions.PodamMockeryException;

/**
 * Implementation of the type multiplexer
 *
 * @since 6.0.4.RELEASE
 * @author daivanov
 */
public class TypeMultiplexerImpl implements TypeMultiplexer {

	/* Temporary mapping of types to their manufacturers */
	private ConcurrentHashMap<Class<?>, TypeManufacturer<?>> manufacturers
			= new ConcurrentHashMap<Class<?>, TypeManufacturer<?>>();

	public TypeMultiplexerImpl() {

		TypeManufacturer<?> byteManufacturer = new ByteTypeManufacturerImpl();
		manufacturers.put(byte.class, byteManufacturer);
		manufacturers.put(Byte.class, byteManufacturer);

		TypeManufacturer<?> booleanManufacturer = new BooleanTypeManufacturerImpl();
		manufacturers.put(boolean.class, booleanManufacturer);
		manufacturers.put(Boolean.class, booleanManufacturer);

		TypeManufacturer<?> charManufacturer = new CharTypeManufacturerImpl();
		manufacturers.put(char.class, charManufacturer);
		manufacturers.put(Character.class, charManufacturer);

		TypeManufacturer<?> shortManufacturer = new ShortTypeManufacturerImpl();
		manufacturers.put(short.class, shortManufacturer);
		manufacturers.put(Short.class, shortManufacturer);

		TypeManufacturer<?> intManufacturer = new IntTypeManufacturerImpl();
		manufacturers.put(int.class, intManufacturer);
		manufacturers.put(Integer.class, intManufacturer);

		TypeManufacturer<?> longManufacturer = new LongTypeManufacturerImpl();
		manufacturers.put(long.class, longManufacturer);
		manufacturers.put(Long.class, longManufacturer);

		TypeManufacturer<?> floatManufacturer = new FloatTypeManufacturerImpl();
		manufacturers.put(float.class, floatManufacturer);
		manufacturers.put(Float.class, floatManufacturer);

		TypeManufacturer<?> doubleManufacturer = new DoubleTypeManufacturerImpl();
		manufacturers.put(double.class, doubleManufacturer);
		manufacturers.put(Double.class, doubleManufacturer);

		TypeManufacturer<?> stringManufacturer = new StringTypeManufacturerImpl();
		manufacturers.put(String.class, stringManufacturer);

		TypeManufacturer<?> enumManufacturer = new EnumTypeManufacturerImpl();
		manufacturers.put(Enum.class, enumManufacturer);

		TypeManufacturer<?> typeManufacturer = new GenericTypeManufacturerImpl();
		manufacturers.put(Type.class, typeManufacturer);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getTypeValue(DataProviderStrategy strategy,
			AttributeMetadata attributeMetadata,
			Map<String, Type> genericTypesArgumentsMap,
			Class<?> pojoType) {
		TypeManufacturerParamsWrapper wrapper =
				new TypeManufacturerParamsWrapper(strategy, attributeMetadata,
						genericTypesArgumentsMap);
		try {
			TypeManufacturer<?> manufacturer = manufacturers.get(pojoType);
			return manufacturer.getType(wrapper);
		} catch (Exception e) {
			throw new PodamMockeryException(
					"Unable to instantiate " + pojoType, e);
		}
	}

}
