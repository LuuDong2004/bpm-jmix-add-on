/*
 * Copyright (c) Haulmont 2024. All Rights Reserved.
 * Use is subject to license terms.
 */

package com.vn.bpmcontrol.view.historicincidentdata;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import io.jmix.core.LoadContext;
import io.jmix.core.Messages;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import com.vn.bpmcontrol.entity.incident.HistoricIncidentData;
import com.vn.bpmcontrol.service.incident.IncidentService;
import com.vn.bpmcontrol.service.job.impl.JobServiceImpl;
import com.vn.bpmcontrol.view.externaltask.ExternalTaskErrorDetailsView;
import com.vn.bpmcontrol.view.job.JobErrorDetailsView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "historic-incidents/:id", layout = DefaultMainViewParent.class)
@ViewController("HistoricIncidentData.detail")
@ViewDescriptor("historic-incident-data-detail-view.xml")
@EditedEntityContainer("historicIncidentDataDc")
public class HistoricIncidentDataDetailView extends StandardDetailView<HistoricIncidentData> {

    @Autowired
    protected IncidentService incidentService;
    @ViewComponent
    protected JmixButton viewStacktraceBtn;

    @ViewComponent
    protected TypedTextField<Object> configurationField;
    @Autowired
    protected Messages messages;
    @ViewComponent
    protected TypedTextField<Object> causeIncidentIdField;
    @ViewComponent
    protected TypedTextField<Object> rootCauseIncidentIdField;
    @Autowired
    protected DialogWindows dialogWindows;
    @Autowired
    protected JobServiceImpl jobService;

    @Subscribe
    public void onInit(final InitEvent event) {
        addClassNames(LumoUtility.Padding.Top.XSMALL);
    }

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        initIncidentTypeRelatedFields();
        initCauseIncidentFields();
        initRootCauseIncidentFields();
    }

    @Install(to = "historicIncidentDataDl", target = Target.DATA_LOADER)
    protected HistoricIncidentData historicIncidentDataDlLoadDelegate(final LoadContext<HistoricIncidentData> loadContext) {
        Object id = loadContext.getId();
        if (id != null) {
            return incidentService.findHistoricIncidentById(id.toString());
        }
        return null;
    }

    protected void initIncidentTypeRelatedFields() {
        boolean notEmptyPayload = getEditedEntity().getConfiguration() != null;
        boolean historyJobLogPresent = jobService.isHistoryJobLogPresent(getEditedEntity().getConfiguration());

        if (getEditedEntity().isExternalTaskFailed()) {
            viewStacktraceBtn.setVisible(notEmptyPayload && historyJobLogPresent);
            configurationField.setLabel(messages.getMessage("com.vn.bpmcontrol.view.incidentdata/externalTaskIdLabel"));
        } else if (getEditedEntity().isJobFailed()) {
            configurationField.setLabel(messages.getMessage("com.vn.bpmcontrol.view.incidentdata/jobIdLabel"));
            viewStacktraceBtn.setVisible(notEmptyPayload && historyJobLogPresent);
        } else {
            viewStacktraceBtn.setVisible(false);
        }
    }

    @Subscribe(id = "viewStacktraceBtn", subject = "clickListener")
    public void onViewStacktraceBtnClick(final ClickEvent<JmixButton> event) {
        if (getEditedEntity().isJobFailed()) {
            dialogWindows.view(this, JobErrorDetailsView.class)
                    .withViewConfigurer(view -> {
                        view.setJobId(getEditedEntity().getConfiguration());
                        view.fromHistory();
                    })
                    .build()
                    .open();
        } else if (getEditedEntity().isExternalTaskFailed()) {
            dialogWindows.view(this, ExternalTaskErrorDetailsView.class)
                    .withViewConfigurer(view -> {
                        view.setExternalTaskId(getEditedEntity().getConfiguration());
                        view.fromHistory();
                    })
                    .build()
                    .open();
        }
    }

    protected void initCauseIncidentFields() {
        String causeIncidentLabel;
        String causeIncidentId = getEditedEntity().getCauseIncidentId();
        if (StringUtils.equals(getEditedEntity().getIncidentId(), causeIncidentId)) {
            causeIncidentLabel = messages.formatMessage("com.vn.bpmcontrol.view.incidentdata", "incidentWithProcess", causeIncidentId, getEditedEntity().getProcessDefinitionKey());
        } else {
            causeIncidentLabel = getRelatedIncidentFieldLabel(causeIncidentId);
        }
        causeIncidentIdField.setValue(causeIncidentLabel);
    }

    protected String getRelatedIncidentFieldLabel(String relatedIncidentId) {
        String relatedIncidentLabel;
        HistoricIncidentData relatedIncident = incidentService.findHistoricIncidentById(relatedIncidentId);
        if (relatedIncident != null) {
            relatedIncidentLabel = messages.formatMessage("com.vn.bpmcontrol.view.incidentdata", "incidentWithProcess", relatedIncidentId, relatedIncident.getProcessDefinitionKey());
        } else {
            relatedIncidentLabel = relatedIncidentId;
        }
        return relatedIncidentLabel;
    }

    protected void initRootCauseIncidentFields() {
        String rootCauseIncidentLabel;
        String rootCauseIncidentId = getEditedEntity().getRootCauseIncidentId();
        boolean sameIncident = StringUtils.equals(getEditedEntity().getIncidentId(), getEditedEntity().getRootCauseIncidentId());
        if (sameIncident) {
            rootCauseIncidentLabel = messages.formatMessage("com.vn.bpmcontrol.view.incidentdata", "incidentWithProcess", rootCauseIncidentId, getEditedEntity().getProcessDefinitionKey());
        } else {
            rootCauseIncidentLabel = getRelatedIncidentFieldLabel(rootCauseIncidentId);
        }
        rootCauseIncidentIdField.setValue(rootCauseIncidentLabel);
    }
}
