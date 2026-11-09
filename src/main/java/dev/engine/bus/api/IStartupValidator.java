/*
 * Copyright (c) EngineMC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package dev.engine.bus.api;

/**
 * Interface used to check whether an event bus can start.
 */
@FunctionalInterface
public interface IStartupValidator {
    /**
     * If the event bus can start, this does nothing.
     * Otherwise, if it's not allowed to start, throws an {@link IllegalStateException}.
     *
     * @throws IllegalStateException if the event bus is not allowed to start
     */
    void validateStartAttempt() throws IllegalStateException;
}
