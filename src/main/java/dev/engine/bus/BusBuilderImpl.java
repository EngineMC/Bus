/*
 * Copyright (c) NeoForged and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package dev.engine.bus;

import dev.engine.bus.api.BusBuilder;
import dev.engine.bus.api.IEventBus;
import dev.engine.bus.api.IEventClassChecker;
import dev.engine.bus.api.IEventExceptionHandler;
import dev.engine.bus.api.IStartupValidator;
import org.jetbrains.annotations.ApiStatus;

/**
 * BusBuilder Implementation, public for BusBuilder.builder() only, don't use this directly.
 */
@ApiStatus.Internal
public final class BusBuilderImpl implements BusBuilder {
    IStartupValidator startupValidator;
    IEventExceptionHandler exceptionHandler;
    boolean startShutdown = false;
    boolean checkTypesOnDispatch = false;
    IEventClassChecker classChecker = eventClass -> {};
    boolean allowPerPhasePost = false;

    @Override
    public BusBuilder setExceptionHandler(IEventExceptionHandler handler) {
        this.exceptionHandler = handler;
        return this;
    }

    @Override
    public BusBuilder startShutdown() {
        return startShutdown(null);
    }

    @Override
    public BusBuilder startShutdown(IStartupValidator validator) {
        this.startShutdown = true;
        this.startupValidator = validator;
        return this;
    }

    @Override
    public BusBuilder checkTypesOnDispatch() {
        this.checkTypesOnDispatch = true;
        return this;
    }

    @Override
    public BusBuilder classChecker(IEventClassChecker checker) {
        this.classChecker = checker;
        return this;
    }

    @Override
    public BusBuilder allowPerPhasePost() {
        this.allowPerPhasePost = true;
        return this;
    }

    @Override
    public IEventBus build() {
        return new EventBus(this);
    }
}
