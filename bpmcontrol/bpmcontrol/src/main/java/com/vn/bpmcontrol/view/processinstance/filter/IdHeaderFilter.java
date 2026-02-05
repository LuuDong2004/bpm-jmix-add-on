/*
 * Copyright (c) Haulmont 2024. All Rights Reserved.
 * Use is subject to license terms.
 */

package com.vn.bpmcontrol.view.processinstance.filter;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.QueryParameters;
import com.vn.bpmcontrol.entity.filter.ProcessInstanceFilter;
import com.vn.bpmcontrol.entity.processinstance.ProcessInstanceData;
import com.vn.bpmcontrol.facet.urlqueryparameters.HasFilterUrlParamHeaderFilter;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.grid.DataGridColumn;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.model.InstanceContainer;

import java.util.HashMap;
import java.util.Map;

import static com.vn.bpmcontrol.facet.urlqueryparameters.ProcessInstanceListQueryParamBinder.INSTANCE_ID_FILTER_PARAM;
import static com.vn.bpmcontrol.view.util.FilterQueryParamUtils.getStringParam;

public class IdHeaderFilter extends ProcessInstanceDataGridHeaderFilter implements HasFilterUrlParamHeaderFilter {

    protected TypedTextField<String> processInstanceIdField;

    public IdHeaderFilter(DataGrid<ProcessInstanceData> dataGrid,
                          DataGridColumn<ProcessInstanceData> column, InstanceContainer<ProcessInstanceFilter> filterDc) {
        super(dataGrid, column, filterDc);
    }

    @Override
    protected Component createFilterComponent() {
        return createProcessInstanceIdFilter();
    }

    @Override
    protected void resetFilterValues() {
        processInstanceIdField.clear();
    }

    @Override
    public void apply() {
        String value = processInstanceIdField.getTypedValue();
        filterDc.getItem().setProcessInstanceId(value);

        filterButton.getElement().setAttribute(COLUMN_FILTER_BUTTON_ACTIVATED_ATTRIBUTE_NAME, value != null);
    }

    protected TextField createProcessInstanceIdFilter() {
        processInstanceIdField = uiComponents.create(TypedTextField.class);
        processInstanceIdField.setWidthFull();
        processInstanceIdField.setMinWidth("30em");
        processInstanceIdField.setClearButtonVisible(true);
        processInstanceIdField.setLabel(messages.getMessage(ProcessInstanceFilter.class, "ProcessInstanceFilter.processInstanceId"));
        processInstanceIdField.setPlaceholder(messages.getMessage(getClass(), "processInstanceId.placeholder"));

        return processInstanceIdField;
    }

    @Override
    public void updateComponents(QueryParameters queryParameters) {
        String paramValue = getStringParam(queryParameters, INSTANCE_ID_FILTER_PARAM);

        processInstanceIdField.setTypedValue(paramValue);
        apply();
    }

    @Override
    public Map<String, String> getQueryParamValues() {
        Map<String, String> paramValues = new HashMap<>();
        paramValues.put(INSTANCE_ID_FILTER_PARAM, processInstanceIdField.getTypedValue());
        return paramValues;
    }
}
