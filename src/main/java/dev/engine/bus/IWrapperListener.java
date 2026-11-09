/*
 * Copyright (c) NeoForged and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package dev.engine.bus;

import dev.engine.bus.api.EventListener;
import org.jetbrains.annotations.ApiStatus;

/**
 * Listener that wraps a listener to add a check.
 */
@ApiStatus.Internal
public interface IWrapperListener {
    EventListener getWithoutCheck();
}
