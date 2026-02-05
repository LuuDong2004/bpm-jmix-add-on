/*
 * Copyright (c) Haulmont 2026. All Rights Reserved.
 * Use is subject to license terms.
 */

package com.vn.bpmcontrol.view.processinstance.column;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vn.bpmcontrol.entity.processinstance.ProcessInstanceData;
import com.vn.bpmcontrol.entity.processinstance.RuntimeProcessInstanceData;
import com.vn.bpmcontrol.view.entitydetaillink.ProcessLinkColumnFragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.fragmentrenderer.RendererItemContainer;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.Subscribe;
import org.jspecify.annotations.Nullable;

@FragmentDescriptor("process-column-fragment.xml")
@RendererItemContainer("processInstanceDc")
public class ProcessColumnFragment extends ProcessLinkColumnFragment<HorizontalLayout, RuntimeProcessInstanceData> {

    @Subscribe(id = "idBtn", subject = "clickListener")
    public void onIdBtnClick(final ClickEvent<JmixButton> event) {
        openProcessDetailView();
    }

    protected String getProcessDefinitionId() {
        return item.getProcessDefinitionId();
    }

    @Override
    protected @Nullable String getProcessLabel() {
        ProcessInstanceData processInstanceData = (ProcessInstanceData) item;
        return processInstanceData.getProcessDefinitionVersion() == null ? processInstanceData.getProcessDefinitionId() :
                componentHelper.getProcessLabel(processInstanceData.getProcessDefinitionKey(), processInstanceData.getProcessDefinitionVersion());
    }
}