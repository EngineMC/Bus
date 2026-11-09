/*
 * Copyright (c) NeoForged and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package dev.engine.bus.testjar;

import dev.engine.bus.api.Event;
import dev.engine.bus.api.ICancellableEvent;

public class DummyEvent extends Event {
    public static class GoodEvent extends DummyEvent {}
    public static class BadEvent extends DummyEvent {}
    public static class CancellableEvent extends DummyEvent implements ICancellableEvent {}
}
