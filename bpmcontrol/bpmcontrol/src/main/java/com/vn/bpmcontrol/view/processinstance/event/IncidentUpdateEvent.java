/*
 * Copyright (c) Haulmont 2024. All Rights Reserved.
 * Use is subject to license terms.
 */

package com.vn.bpmcontrol.view.processinstance.event;

import org.springframework.context.ApplicationEvent;


public class IncidentUpdateEvent extends ApplicationEvent {

    public IncidentUpdateEvent(Object source) {
        super(source);
    }

}
