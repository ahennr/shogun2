package de.terrestris.shogun2.util.json;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.text.DateFormat;
import java.util.List;
import java.util.TimeZone;

import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.joda.JodaModule;

/**
 * @author Nils Bühner
 *
 */
public class Shogun2JsonObjectMapperTest {

	/**
	 * The object mapper to test.
	 */
	private final ObjectMapper objectMapper = new Shogun2JsonObjectMapper();

	/**
	 * Tests whether the JodaModule is registered.
	 */
	@Test
	public void testModules() {

		List<Module> modules = Shogun2JsonObjectMapper.findModules();

		assertEquals(1, modules.size());
		assertThat(modules.get(0), instanceOf(JodaModule.class));
	}

	/**
	 * Tests whether the dateFormat is ISO8601
	 */
	@Test
	public void testDateFormat() {

		SerializationConfig serializationConfig = objectMapper.getSerializationConfig();
		DeserializationConfig deserializationConfig = objectMapper.getDeserializationConfig();

		DateFormat serializationDateFormat = serializationConfig.getDateFormat();
		DateFormat deserializationDateFormat = deserializationConfig.getDateFormat();

		assertThat(serializationDateFormat, instanceOf(ISO8601DateFormat.class));
		assertThat(deserializationDateFormat, instanceOf(ISO8601DateFormat.class));
	}

	/**
	 * Tests whether the correct TimeZone is set.
	 */
	@Test
	public void testTimezone() {

		TimeZone expectedTimeZone = TimeZone.getDefault();

		SerializationConfig serializationConfig = objectMapper.getSerializationConfig();
		DeserializationConfig deserializationConfig = objectMapper.getDeserializationConfig();

		TimeZone serializationTimeZone = serializationConfig.getTimeZone();
		TimeZone deserializationTimeZone = deserializationConfig.getTimeZone();

		assertEquals(expectedTimeZone, serializationTimeZone);
		assertEquals(expectedTimeZone, deserializationTimeZone);
	}

	/**
	 * Tests whether the dates are not serialized as timestamps, but as
	 * their textual representation.
	 */
	@Test
	public void testDateSerialization() {

		SerializationConfig serializationConfig = objectMapper.getSerializationConfig();

		boolean writesDatesAsTimestamps = serializationConfig.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		assertFalse(writesDatesAsTimestamps);
	}

}