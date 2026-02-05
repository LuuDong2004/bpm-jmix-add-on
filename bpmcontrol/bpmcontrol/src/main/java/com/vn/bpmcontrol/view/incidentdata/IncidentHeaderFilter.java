/*
 * Copyright (c) Haulmont 2024. All Rights Reserved.
 * Use is subject to license terms.
 */

package com.vn.bpmcontrol.view.incidentdata;

import com.vaadin.flow.component.grid.Grid;
import io.jmix.flowui.component.grid.DataGridColumn;
import io.jmix.flowui.model.InstanceContainer;
import com.vn.bpmcontrol.entity.filter.IncidentFilter;
import com.vn.bpmcontrol.entity.incident.IncidentData;
import com.vn.bpmcontrol.uicomponent.ContainerDataGridHeaderFilter;

public abstract class IncidentHeaderFilter extends ContainerDataGridHeaderFilter<IncidentFilter, IncidentData> {
    public IncidentHeaderFilter(Grid<IncidentData> dataGrid,
                                DataGridColumn<IncidentData> column,
                                InstanceContainer<IncidentFilter> filterDc) {
        super(dataGrid, column, filterDc);
    }
}