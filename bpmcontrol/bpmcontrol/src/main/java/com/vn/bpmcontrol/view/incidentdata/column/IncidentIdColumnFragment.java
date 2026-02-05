/*
 * Copyright (c) Haulmont 2026. All Rights Reserved.
 * Use is subject to license terms.
 */

package com.vn.bpmcontrol.view.incidentdata.column;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vn.bpmcontrol.entity.incident.IncidentData;
import com.vn.bpmcontrol.view.entitydetaillink.EntityDetailLinkFragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.fragmentrenderer.RendererItemContainer;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewComponent;

@FragmentDescriptor("incident-id-column-fragment.xml")
@RendererItemContainer("incidentDc")
public class IncidentIdColumnFragment extends EntityDetailLinkFragment<HorizontalLayout, IncidentData> {
    @ViewComponent
    protected JmixButton idBtn;

    @Override
    public void setItem(IncidentData item) {
        super.setItem(item);

        idBtn.setText(item.getIncidentId());
    }

    @Subscribe(id = "idBtn", subject = "clickListener")
    public void onIdBtnClick(final ClickEvent<JmixButton> event) {
        openDetailView(IncidentData.class);
    }
}