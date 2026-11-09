/*
 * Copyright (c) NeoForged and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package dev.engine.bus.api;

import dev.engine.bus.BusBuilderImpl;

/**
 * Class used to build {@linkplain IEventBus event buses}.
 */
public interface BusBuilder {
    /**
     * {@return a new bus builder}
     */
    static BusBuilder builder() {
        return new BusBuilderImpl();
    }

    /**
     * Override the default exception handler.
     *
     * @param handler the custom exception handler to use
     * @return the builder instance
     */
    BusBuilder setExceptionHandler(IEventExceptionHandler handler);

    /**
     * Create the bus in a shut-down state.
     * <p>
     * This bus will not post events (and will instead silently ignore them) until it is {@linkplain IEventBus#start() started}.
     *
     * @return the builder instance
     */
    BusBuilder startShutdown();

    /**
     * Create the bus in a shut-down state.
     * <p>
     * This bus will not post events (and will instead silently ignore them) until it is {@link IEventBus#start() started}.
     * </p>
     * Unlike {@link #startShutdown()}, this method accepts a startup validator,
     * for determining if the event bus is able to start.
     *
     * @param validator the startup validator
     * @return the builder instance
     */
    BusBuilder startShutdown(IStartupValidator validator);

    /**
     * Make the bus check posted events using the {@linkplain #classChecker(IEventClassChecker) class checker}.
     *
     * @return the builder instance
     */
    BusBuilder checkTypesOnDispatch();

    /**
     * Helper for {@link #classChecker(IEventClassChecker)} that will check if the event type is of the given {@code markerInterface}.
     *
     * @param markerInterface a base marker interface for all events that this bus should be able to handle
     * @return the builder instance
     */
    default BusBuilder markerType(Class<?> markerInterface) {
        if (!markerInterface.isInterface()) throw new IllegalArgumentException("Cannot specify a class marker type");
        return classChecker(eventType -> {
            if (!markerInterface.isAssignableFrom(eventType)) {
                throw new IllegalArgumentException("This bus only accepts subclasses of " + markerInterface + ", which " + eventType + " is not.");
            }
        });
    }

    /**
     * Set a class checker that will allow validating the event types that the bus can handle.
     *
     * @param checker the custom checker
     * @return the builder instance
     */
    BusBuilder classChecker(IEventClassChecker checker);

    /**
     * Allow calling {@link IEventBus#post(EventPriority, Event)}.
     */
    BusBuilder allowPerPhasePost();

    /**
     * {@return a built bus configured according to the configuration of this builder}
     */
    IEventBus build();
}
