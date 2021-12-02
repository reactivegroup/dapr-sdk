/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package group.rxcloud.capa.spi.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import group.rxcloud.capa.component.log.CapaLogbackAppenderAgent;
import group.rxcloud.capa.spi.log.enums.CapaLogLevel;
import group.rxcloud.capa.spi.log.manager.LogManager;

import java.util.Optional;

public abstract class CapaLogbackAppenderSpi extends UnsynchronizedAppenderBase<ILoggingEvent>
        implements CapaLogbackAppenderAgent.CapaLogbackAppender {

    @Override
    public void append(ILoggingEvent event) {
        if (event != null && event.getLevel() != null) {
            Optional<CapaLogLevel> capaLogLevel = CapaLogLevel.toCapaLogLevel(event.getLevel().levelStr);
            if (capaLogLevel.isPresent() && LogManager.whetherLogsCanOutput(capaLogLevel.get())) {
                this.appendLog(event);
            }
        }
    }

    protected abstract void appendLog(ILoggingEvent event);
}
