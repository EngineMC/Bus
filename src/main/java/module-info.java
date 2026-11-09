/*
 * Copyright (c) NeoForged and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

open module dev.engine.bus {
    requires org.objectweb.asm;
    requires org.apache.logging.log4j;
    requires static org.jetbrains.annotations;
    requires net.jodah.typetools;
    requires jdk.unsupported; // required for typetools

    exports dev.engine.bus;
    exports dev.engine.bus.api;
}
