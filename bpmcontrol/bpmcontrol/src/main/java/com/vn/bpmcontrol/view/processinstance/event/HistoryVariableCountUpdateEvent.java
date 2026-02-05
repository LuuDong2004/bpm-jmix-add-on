/*
 * Copyright (c) Haulmont 2024. All Rights Reserved.
 * Use is subject to license terms.
 */

package com.vn.bpmcontrol.view.processinstance.event;

import org.springframework.context.ApplicationEvent;


public class HistoryVariableCountUpdateEvent extends ApplicationEvent {
    private final long count;

    public HistoryVariableCountUpdateEvent(Object source, long count) {
        super(source);
        this.count = count;
    }

    public long getCount() {
        return count;
    }
}
