/*
 * Copyright (c) EngineMC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package dev.engine.bus.api;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * A helper class for creating event listeners
 * that should only trigger once.
 * <p>
 * A one-time event listener will unregister
 * itself once having received the target event,
 * allowing for the garbage collector to remove
 * it from memory.
 *
 * @param <E> the type of event
 */
public class OneTimeEventListener<E extends Event> implements Consumer<E> {
    private final IEventBus bus;
    private final Consumer<E> listener;
    private final AtomicBoolean consumed = new AtomicBoolean();

    /**
     * Adds a one-time event listener to the given event bus.
     *
     * @param bus      the event bus
     * @param eClass   the event class
     * @param listener the event listener
     * @param <E>      the type of event
     */
    public static <E extends Event> void addListener(IEventBus bus, Class<E> eClass, Consumer<E> listener) {
        Objects.requireNonNull(bus);
        Objects.requireNonNull(eClass);
        Objects.requireNonNull(listener);

        bus.addListener(eClass, new OneTimeEventListener<>(bus, listener));
    }

    private OneTimeEventListener(IEventBus bus, Consumer<E> listener) {
        this.bus = bus;
        this.listener = listener;
    }

    @Override
    public void accept(E event) {
        if (consumed.compareAndSet(false, true)) {
            listener.accept(event);
            bus.unregister(this);
        }
    }
}
