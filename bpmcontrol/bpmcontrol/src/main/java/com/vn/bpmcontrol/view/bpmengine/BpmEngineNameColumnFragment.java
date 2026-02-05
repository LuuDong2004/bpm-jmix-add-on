/*
 * Copyright (c) Haulmont 2026. All Rights Reserved.
 * Use is subject to license terms.
 */

package com.vn.bpmcontrol.view.bpmengine;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vn.bpmcontrol.view.entitydetaillink.EntityDetailLinkFragment;
import io.jmix.core.Messages;
import io.jmix.flowui.fragment.FragmentDescriptor;
import io.jmix.flowui.fragmentrenderer.RendererItemContainer;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewComponent;
import com.vn.bpmcontrol.entity.engine.BpmEngine;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@FragmentDescriptor("bpm-engine-name-column-fragment.xml")
@RendererItemContainer("bpmEngineDc")
public class BpmEngineNameColumnFragment extends EntityDetailLinkFragment<HorizontalLayout, BpmEngine> {

    @ViewComponent
    protected Span defaultEngineBadge;
    @ViewComponent
    protected EngineEnvironmentBadgeFragment envBadge;
    @Autowired
    protected Messages messages;
    @ViewComponent
    protected JmixButton nameBtn;

    @Override
    public void setItem(BpmEngine item) {
        super.setItem(item);

        if (BooleanUtils.isTrue(item.getIsDefault())) {
            defaultEngineBadge.setVisible(true);
        }

        envBadge.setItem(item);
        envBadge.setSmall(false);
        nameBtn.setText(item.getName());
    }

    @Subscribe(id = "nameBtn", subject = "clickListener")
    public void onNameBtnClick(final ClickEvent<JmixButton> event) {
        openDetailView(BpmEngine.class);
    }

}
