/*
 * Copyright (c) NeoForged and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package dev.engine.bus.test;

import dev.engine.bus.api.BusBuilder;

public class TestNoLoaderBase {
    private BusBuilder builder() {
        return BusBuilder.builder();
    }

    protected void doTest(ITestHandler handler) {
        handler.before(this::builder);
        handler.test(this::builder);
        handler.after(this::builder);
    }
}
