/*
 * Copyright (c) Haulmont 2024. All Rights Reserved.
 * Use is subject to license terms.
 */

package com.vn.bpmcontrol.view.processinstance.event;

import org.springframework.context.ApplicationEvent;


public class ExternalTaskRetriesUpdateEvent extends ApplicationEvent {

    public ExternalTaskRetriesUpdateEvent(Object source) {
        super(source);
    }

}