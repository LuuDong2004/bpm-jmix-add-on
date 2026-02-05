/*
 * Copyright (c) Haulmont 2024. All Rights Reserved.
 * Use is subject to license terms.
 */

package com.vn.bpmcontrol.view.processinstance.filter;

import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.grid.DataGridColumn;
import io.jmix.flowui.model.InstanceContainer;
import com.vn.bpmcontrol.entity.filter.ProcessInstanceFilter;
import com.vn.bpmcontrol.entity.processinstance.ProcessInstanceData;
import com.vn.bpmcontrol.uicomponent.ContainerDataGridHeaderFilter;

public abstract class ProcessInstanceDataGridHeaderFilter extends ContainerDataGridHeaderFilter<ProcessInstanceFilter, ProcessInstanceData> {

    public ProcessInstanceDataGridHeaderFilter(DataGrid<ProcessInstanceData> dataGrid,
                                               DataGridColumn<ProcessInstanceData> column,
                                               InstanceContainer<ProcessInstanceFilter> filterDc) {
        super(dataGrid, column, filterDc);
    }
}
