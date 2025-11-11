package com.closedsource.psymed.platform.patientreport.application.queryservices;

import com.closedsource.psymed.platform.patientreport.domain.model.aggregates.BiologicalFunction;
import com.closedsource.psymed.platform.patientreport.domain.model.queries.GetAllBiologicalFunctionsByPatientIdQuery;
import com.closedsource.psymed.platform.patientreport.domain.services.BiologicalFunctionQueryService;
import com.closedsource.psymed.platform.patientreport.infrastructure.persistence.jpa.repositories.BiologicalFunctionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiologicalFunctionQueryServiceImpl implements BiologicalFunctionQueryService {
    private final BiologicalFunctionRepository biologicalFunctionRepository;

    public BiologicalFunctionQueryServiceImpl(BiologicalFunctionRepository biologicalFunctionRepository) {
        this.biologicalFunctionRepository = biologicalFunctionRepository;
    }

    @Override
    public List<BiologicalFunction> handle(GetAllBiologicalFunctionsByPatientIdQuery query) {
        return biologicalFunctionRepository.findAllByPatientId(query.patientId());
    }

}
