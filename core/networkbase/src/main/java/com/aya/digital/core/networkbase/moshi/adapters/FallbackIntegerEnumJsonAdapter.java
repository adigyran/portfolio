package com.aya.digital.core.networkbase.moshi.adapters;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * {@linkplain JsonAdapter} that fallbacks to a default enum constant declared in the enum type
 * annotated with {@linkplain FallbackEnum}.
 */
public final class FallbackIntegerEnumJsonAdapter<T extends Enum<T>> extends JsonAdapter<T> {
    public static Factory ADAPTER_FACTORY = (type, annotations, moshi) -> {
        if (!annotations.isEmpty()) return null;

        Class<?> rawType = Types.getRawType(type);
        if (rawType.isEnum()) {
            FallbackEnum annotation = rawType.getAnnotation(FallbackEnum.class);

            //noinspection unchecked
            return new FallbackIntegerEnumJsonAdapter<>((Class<? extends Enum>) rawType, annotation != null ? annotation.name() : null);
        }

        return null;
    };
    private final String[] nameStrings;
    private final T fallbackConstant;
    private final Map<String, T> nameConstantMap;

    private FallbackIntegerEnumJsonAdapter(Class<T> enumType, String fallback) {
        if (fallback != null)
            fallbackConstant = Enum.valueOf(enumType, fallback);
        else
            fallbackConstant = null;

        try {
            final T[] constants = enumType.getEnumConstants();
            nameStrings = new String[constants.length];
            nameConstantMap = new HashMap<>();

            for (int i = 0; i < constants.length; i++) {
                T constant = constants[i];
                Json annotation = enumType.getField(constant.name()).getAnnotation(Json.class);
                String name = annotation != null ? annotation.name() : constant.name();
                nameConstantMap.put(name, constant);
                nameStrings[i] = name;
            }
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Missing field in " + enumType.getName());
        }
    }

    @Override
    public T fromJson(JsonReader reader) throws IOException {
        if (reader.peek() == JsonReader.Token.NULL) {
            reader.skipValue();
            return null;
        }

        String name = reader.nextString();
        T constant = nameConstantMap.get(name);

        if (constant != null)
            return constant;

        if (fallbackConstant != null)
            return fallbackConstant;

        throw new JsonDataException("Expected one of " + Arrays.toString(nameStrings) + " but was " + name + " at path " + reader.getPath());
    }

    @Override
    public void toJson(JsonWriter writer, T value) throws IOException {
        String newValue = null;
        for (Map.Entry<String, T> entry : nameConstantMap.entrySet()) {
            if (value == entry.getValue()) {
                newValue = entry.getKey();
                break;
            }
        }
        if (newValue != null)
            writer.value(newValue);
        else
            writer.nullValue();
    }
}