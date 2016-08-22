package uk.co.jemos.podam.typeManufacturers;

import java.lang.reflect.Type;
import java.util.Map;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandlingException;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.common.PodamConstants;
import uk.co.jemos.podam.exceptions.PodamMockeryException;

/**
 * Implementation of the type multiplexer
 *
 * @since 7.0.0.RELEASE
 * @author daivanov
 */
public class TypeMultiplexerImpl implements TypeMultiplexer {

	/* Application context */
	private AbstractApplicationContext applicationContext;

	/** The message channel where to send/receive the type request */
	private MessageChannel messageChannel;

	public TypeMultiplexerImpl() {
		this.applicationContext = new ClassPathXmlApplicationContext(PodamConstants.SPRING_ROOT_CONFIG_LOCATION);
		this.messageChannel = applicationContext.getBean("podamInputChannel", MessageChannel.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void finalize() {
		applicationContext.close();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getTypeValue(DataProviderStrategy strategy,
			AttributeMetadata attributeMetadata,
			Map<String, Type> genericTypesArgumentsMap,
			String qualifier) {
		TypeManufacturerParamsWrapper wrapper =
				new TypeManufacturerParamsWrapper(strategy, attributeMetadata,
						genericTypesArgumentsMap);
		return getValueForType(wrapper, qualifier);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValueForType(TypeManufacturerParamsWrapper payload,
			String qualifier) {

		try {

			Message<?> message = MessageBuilder.withPayload(payload).setHeader(
					PodamConstants.HEADER_NAME, qualifier).build();

			MessagingTemplate template = new MessagingTemplate();
			return template.sendAndReceive(messageChannel, message).getPayload();

		} catch(MessageHandlingException e) {
			throw new PodamMockeryException("Unable to instantiate " + qualifier, e);
		}
	}

}
