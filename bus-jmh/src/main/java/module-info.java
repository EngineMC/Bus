/*
 * Copyright (c) NeoForged and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

open module dev.engine.bus.jmh {
    requires org.junit.jupiter.api;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires dev.engine.bus;
    requires jopt.simple;
    requires jmh.core;

    requires static org.jetbrains.annotations;
    requires static dev.engine.bus.testjars;

    exports dev.engine.bus.benchmarks;
}