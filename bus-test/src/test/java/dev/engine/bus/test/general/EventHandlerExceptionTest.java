/*
 * Copyright (c) NeoForged and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package dev.engine.bus.test.general;

import dev.engine.bus.api.BusBuilder;
import dev.engine.bus.test.ITestHandler;
import dev.engine.bus.testjar.DummyEvent;
import dev.engine.bus.testjar.EventBusTestClass;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Supplier;

public class EventHandlerExceptionTest implements ITestHandler {
    @Override
    public void test(Supplier<BusBuilder> builder) {
        var bus = builder.get().build();
        var listener = new EventBusTestClass();
        bus.register(listener);
        assertThrows(RuntimeException.class, ()->bus.post(new DummyEvent.BadEvent()));
    }
}
