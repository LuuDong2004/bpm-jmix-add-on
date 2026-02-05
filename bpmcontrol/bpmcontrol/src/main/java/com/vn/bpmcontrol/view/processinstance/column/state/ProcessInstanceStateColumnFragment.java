/*
 * Copyright (c) Haulmont 2025. All Rights Reserved.
 * Use is subject to license terms.
 */

package com.vn.bpmcontrol.view.processinstance.column.state;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.fragmentrenderer.FragmentRenderer;
import io.jmix.flowui.fragmentrenderer.RendererItemContainer;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewComponent;
import com.vn.bpmcontrol.entity.processinstance.ProcessInstanceData;
import com.vn.bpmcontrol.entity.processinstance.ProcessInstanceState;
import com.vn.bpmcontrol.entity.processinstance.RuntimeProcessInstanceData;
import com.vn.bpmcontrol.view.processinstance.terminatereason.InstanceTerminateReasonView;
import com.vn.bpmcontrol.view.util.ComponentHelper;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import static io.jmix.flowui.component.UiComponentUtils.getCurrentView;

@FragmentDescriptor("process-instance-state-column-fragment.xml")
@RendererItemContainer("processInstanceDataDc")
public class ProcessInstanceStateColumnFragment extends FragmentRenderer<HorizontalLayout, RuntimeProcessInstanceData> {

    @Autowired
    protected ComponentHelper componentHelper;
    @Autowired
    protected DialogWindows dialogWindows;
    @ViewComponent
    protected Icon incidentIcon;
    @ViewComponent
    protected JmixButton viewTerminateReasonBtn;

    @Override
    public void setItem(RuntimeProcessInstanceData item) {
        super.setItem(item);

        Span processInstanceStateBadge = componentHelper.createProcessInstanceStateBadge(item.getState());
        getContent().addComponentAsFirst(processInstanceStateBadge);

        incidentIcon.setVisible(BooleanUtils.isTrue(item.getHasIncidents()));

        if (item instanceof ProcessInstanceData processInstanceData) {
            updateComponentsVisibility(processInstanceData);
        }
    }

    @Subscribe(id = "viewTerminateReasonBtn", subject = "clickListener")
    public void onViewTerminateReasonBtnClick(final ClickEvent<JmixButton> event) {
        dialogWindows.view(getCurrentView(), InstanceTerminateReasonView.class)
                .withViewConfigurer(instanceTerminateReasonView ->
                        instanceTerminateReasonView.setProcessInstance((ProcessInstanceData) item))
                .open();
    }

    protected void updateComponentsVisibility(ProcessInstanceData processInstanceData) {
        ProcessInstanceState state = processInstanceData.getState();
        boolean externallyCompleted = state == ProcessInstanceState.COMPLETED && BooleanUtils.isTrue(processInstanceData.getExternallyTerminated());

        viewTerminateReasonBtn.setVisible(externallyCompleted);
    }

}