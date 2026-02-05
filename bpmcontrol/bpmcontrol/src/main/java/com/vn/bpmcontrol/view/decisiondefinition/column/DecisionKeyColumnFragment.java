/*
 * Copyright (c) Haulmont 2026. All Rights Reserved.
 * Use is subject to license terms.
 */

package com.vn.bpmcontrol.view.decisiondefinition.column;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vn.bpmcontrol.entity.decisiondefinition.DecisionDefinitionData;
import com.vn.bpmcontrol.view.entitydetaillink.EntityDetailLinkFragment;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.fragmentrenderer.RendererItemContainer;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewComponent;

@FragmentDescriptor("decision-key-column-fragment.xml")
@RendererItemContainer("decisionDefinitionDc")
public class DecisionKeyColumnFragment extends EntityDetailLinkFragment<HorizontalLayout, DecisionDefinitionData> {

    @ViewComponent
    protected JmixButton keyBtn;

    @Override
    public void setItem(DecisionDefinitionData item) {
        super.setItem(item);

        keyBtn.setText(item.getKey());
    }

    @Subscribe(id = "keyBtn", subject = "clickListener")
    public void onKeyBtnClick(final ClickEvent<JmixButton> event) {
        openDetailView(DecisionDefinitionData.class);
    }
}