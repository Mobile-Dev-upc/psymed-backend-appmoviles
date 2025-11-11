package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.transform;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.entities.Task;
import com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest.resources.TaskResource;

public class TaskResourceFromEntityAssembler {
    public static TaskResource toResourceFromEntity(Task entity) {
        return new TaskResource(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getStatus().status());
    }
}
