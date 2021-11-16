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
package group.rxcloud.capa.examples.telemetry;

import io.opentelemetry.context.Context;
import io.opentelemetry.sdk.trace.ReadWriteSpan;
import io.opentelemetry.sdk.trace.ReadableSpan;
import io.opentelemetry.sdk.trace.SpanProcessor;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;

/**
 */
public class ConsoleSpanProcessor implements SpanProcessor {

    private SpanProcessor processor = SimpleSpanProcessor.create(new ConsoleSpanExporter());

    @Override
    public void onStart(Context parentContext, ReadWriteSpan span) {
        processor.onStart(parentContext, span);
    }

    @Override
    public boolean isStartRequired() {
        return processor.isStartRequired();
    }

    @Override
    public void onEnd(ReadableSpan span) {
        processor.onEnd(span);
    }

    @Override
    public boolean isEndRequired() {
        return processor.isEndRequired();
    }
}
