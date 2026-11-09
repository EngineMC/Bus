/*
 * Copyright (c) NeoForged and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package dev.engine.bus.test;

import java.util.function.Supplier;

import dev.engine.bus.api.BusBuilder;

public interface ITestHandler {
    default void before(Supplier<BusBuilder> builder) { before(); }
    default void before() {}
    default void after(Supplier<BusBuilder> builder) { after(); }
    default void after() {}

    void test(Supplier<BusBuilder> builder);
}