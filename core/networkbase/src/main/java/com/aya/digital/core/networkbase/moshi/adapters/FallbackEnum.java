package com.aya.digital.core.networkbase.moshi.adapters;

import com.squareup.moshi.Moshi;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated enum has a fallback value. The fallback must be set via
 * {@link #name()}. If no enum constant with the provided name is declared in the annotated
 * enum type an {@linkplain AssertionError assertion error} will be thrown.
 *
 * <p>To leverage from {@link FallbackEnum} {@link FallbackIntegerEnumJsonAdapter#ADAPTER_FACTORY} must be added to
 * your {@linkplain Moshi moshi instance}:
 *
 * <pre><code>
 *   Moshi moshi = new Moshi.Builder()
 *      .add(FallbackIntegerEnumJsonAdapter.ADAPTER_FACTORY)
 *      .build();
 * </code></pre>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FallbackEnum {
    String name();
}