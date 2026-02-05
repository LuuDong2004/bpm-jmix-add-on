/*
 * Copyright (c) Haulmont 2026. All Rights Reserved.
 * Use is subject to license terms.
 */

package com.vn.bpmcontrol.view.historicincidentdata.column;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vn.bpmcontrol.entity.incident.HistoricIncidentData;
import com.vn.bpmcontrol.view.entitydetaillink.EntityDetailLinkFragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.fragmentrenderer.RendererItemContainer;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewComponent;

@FragmentDescriptor("incident-id-column-fragment.xml")
@RendererItemContainer("incidentDc")
public class IncidentIdColumnFragment extends EntityDetailLinkFragment<HorizontalLayout, HistoricIncidentData> {
    @ViewComponent
    protected JmixButton idBtn;

    @Override
    public void setItem(HistoricIncidentData item) {
        super.setItem(item);

        idBtn.setText(item.getIncidentId());
    }

    @Subscribe(id = "idBtn", subject = "clickListener")
    public void onIdBtnClick(final ClickEvent<JmixButton> event) {
        openDetailView(HistoricIncidentData.class);
    }
}