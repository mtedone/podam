package uk.co.jemos.podam.typeManufacturers;

import java.lang.reflect.Type;
import java.util.Map;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;

/**
 * Interface for multiplexer matching type instantiation requests
 * to type manufacturers
 *
 * @since 7.0.0.RELEASE
 * @author daivanov
 */
public interface TypeMultiplexer {

	/**
	 * Obtains a type value
	 * @param strategy The Data Provider strategy
	 * @param attributeMetadata The AttributeMetadata information
	 * @param genericTypesArgumentsMap The generic attribute type argument types
	 * @param pojoType The class of the requested type
	 * @return The type value
	 */
	Object getTypeValue(DataProviderStrategy strategy,
			AttributeMetadata attributeMetadata,
			Map<String, Type> genericTypesArgumentsMap, Class<?> pojoType);

}
