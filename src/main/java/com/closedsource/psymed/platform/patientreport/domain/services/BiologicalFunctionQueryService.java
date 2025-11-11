package com.closedsource.psymed.platform.patientreport.domain.services;

import com.closedsource.psymed.platform.patientreport.domain.model.aggregates.BiologicalFunction;
import com.closedsource.psymed.platform.patientreport.domain.model.queries.GetAllBiologicalFunctionsByPatientIdQuery;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface BiologicalFunctionQueryService {
    List<BiologicalFunction> handle(GetAllBiologicalFunctionsByPatientIdQuery query);
}
