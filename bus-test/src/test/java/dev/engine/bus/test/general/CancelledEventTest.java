/*
 * Copyright (c) NeoForged and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package dev.engine.bus.test.general;

import dev.engine.bus.api.BusBuilder;
import dev.engine.bus.api.IEventBus;
import dev.engine.bus.api.SubscribeEvent;
import dev.engine.bus.test.ITestHandler;
import dev.engine.bus.testjar.DummyEvent;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CancelledEventTest implements ITestHandler {
    @Override
    public void test(Supplier<BusBuilder> builder) {
        IEventBus bus = builder.get().build();

        Listener listener = new Listener();
        bus.register(listener);

        DummyEvent.CancellableEvent event = new DummyEvent.CancellableEvent();
        event.setCanceled(true);
        bus.post(event);

        assertTrue(listener.receivedCanceled, "Received the canceled event");
    }

    public static class Listener {

        boolean receivedCanceled = false;

        @SubscribeEvent(receiveCanceled = true)
        public void onEvent(DummyEvent.CancellableEvent event) {
            this.receivedCanceled = true;
        }

    }
}
