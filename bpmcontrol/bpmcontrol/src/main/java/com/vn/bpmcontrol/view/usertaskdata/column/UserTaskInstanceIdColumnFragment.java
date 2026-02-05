/*
 * Copyright (c) Haulmont 2026. All Rights Reserved.
 * Use is subject to license terms.
 */

package com.vn.bpmcontrol.view.usertaskdata.column;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vn.bpmcontrol.entity.UserTaskData;
import com.vn.bpmcontrol.view.entitydetaillink.ProcessInstanceLinkColumnFragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.fragmentrenderer.RendererItemContainer;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.Subscribe;

@FragmentDescriptor("user-task-instance-id-column-fragment.xml")
@RendererItemContainer("userTaskDc")
public class UserTaskInstanceIdColumnFragment extends ProcessInstanceLinkColumnFragment<HorizontalLayout, UserTaskData> {

    @Subscribe(id = "idBtn", subject = "clickListener")
    public void onIdBtnClick(final ClickEvent<JmixButton> event) {
       openProcessInstanceDetailView();
    }
}