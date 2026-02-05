/*
 * Copyright (c) Haulmont 2024. All Rights Reserved.
 * Use is subject to license terms.
 */

package com.vn.bpmcontrol.view.startprocess;

import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vn.bpmcontrol.entity.processdefinition.ProcessDefinitionData;
import com.vn.bpmcontrol.entity.variable.ObjectTypeInfo;
import com.vn.bpmcontrol.entity.variable.VariableInstanceData;
import com.vn.bpmcontrol.entity.variable.VariableValueInfo;
import com.vn.bpmcontrol.service.processinstance.ProcessInstanceService;
import com.vn.bpmcontrol.view.processvariable.VariableInstanceDataDetail;
import io.jmix.core.Metadata;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@ViewController("bpm_StartProcessWithVariableView")
@ViewDescriptor("start-process-with-variable-view.xml")
@EditedEntityContainer("processDefinitionDc")
@DialogMode(width = "50em", height = "41em")
public class StartProcessWithVariableView extends StandardDetailView<ProcessDefinitionData> {
    @Autowired
    protected ProcessInstanceService processInstanceService;
    @ViewComponent
    protected CollectionContainer<VariableInstanceData> variableDc;
    @Autowired
    protected DialogWindows dialogWindows;
    @ViewComponent
    protected DataGrid<VariableInstanceData> variableGrid;
    @Autowired
    protected Metadata metadata;
    @ViewComponent
    protected TypedTextField<String> businessKeyField;

    @Subscribe
    public void onInit(final InitEvent event) {
        addClassNames(LumoUtility.Padding.Top.NONE,
                LumoUtility.Padding.Left.LARGE,
                LumoUtility.Padding.Right.LARGE,
                LumoUtility.Padding.Bottom.MEDIUM);
    }

    @Supply(to = "variableGrid.value", subject = "renderer")
    protected Renderer<VariableInstanceData> variableValueRenderer() {
        return new TextRenderer<>(e -> e.getValue() != null ? e.getValue().toString() : null);
    }

    @Override
    public boolean hasUnsavedChanges() {
        return false;
    }

    @Subscribe("startProcessAction")
    public void onStartProcessAction(final ActionPerformedEvent event) {
        processInstanceService.startProcessByDefinitionId(getEditedEntity().getProcessDefinitionId(), variableDc.getItems(),
                 businessKeyField.getTypedValue());
        close(StandardOutcome.SAVE);
    }

    @Subscribe("variableGrid.add")
    public void onVariableGridAdd(final ActionPerformedEvent event) {
        dialogWindows.detail(variableGrid)
                .withViewClass(VariableInstanceDataDetail.class)
                .withViewConfigurer(view -> view.setNewVariable(true))
                .newEntity()
                .withInitializer(variableInstanceData -> {
                    VariableValueInfo variableValueInfo = metadata.create(VariableValueInfo.class);
                    ObjectTypeInfo objectTypeInfo = metadata.create(ObjectTypeInfo.class);
                    variableValueInfo.setObject(objectTypeInfo);
                    variableInstanceData.setValueInfo(variableValueInfo);
                })
                .build()
                .open();
    }

    @Subscribe("variableGrid.edit")
    public void onVariableGridEdit(final ActionPerformedEvent event) {
        dialogWindows.detail(variableGrid)
                .withViewClass(VariableInstanceDataDetail.class)
                .withViewConfigurer(view -> view.setNewVariable(true))
                .editEntity(Objects.requireNonNull(variableGrid.getSingleSelectedItem()))
                .build()
                .open();
    }
}
