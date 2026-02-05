/*
 * Copyright (c) Haulmont 2026. All Rights Reserved.
 * Use is subject to license terms.
 */

package com.vn.bpmcontrol.view.externaltask.column;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vn.bpmcontrol.entity.ExternalTaskData;
import com.vn.bpmcontrol.view.entitydetaillink.EntityDetailLinkFragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.fragmentrenderer.RendererItemContainer;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewComponent;

@FragmentDescriptor("external-task-id-column-fragment.xml")
@RendererItemContainer("externalTaskDc")
public class ExternalTaskIdColumnFragment extends EntityDetailLinkFragment<HorizontalLayout, ExternalTaskData> {
    @ViewComponent
    protected JmixButton idBtn;

    @Override
    public void setItem(ExternalTaskData item) {
        super.setItem(item);

        idBtn.setText(item.getExternalTaskId());
    }

    @Subscribe(id = "idBtn", subject = "clickListener")
    public void onIdBtnClick(final ClickEvent<JmixButton> event) {
        openDetailView(ExternalTaskData.class);
    }
}