/**
 * @author Krishna Alisetty
 * @date 30-12-2025
 */

package com.portal.interview.mapper;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class ExperienceDeserializer extends JsonDeserializer<Float> {

    @Override
    public Float deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String value = jsonParser.getText();
        if (value == null || value.isBlank()) {
            return null;
        }
        String parsedValue = value.replaceAll("[^0-9.]", "");

        return parsedValue.isEmpty() ? null : Float.parseFloat(parsedValue);
    }
}
