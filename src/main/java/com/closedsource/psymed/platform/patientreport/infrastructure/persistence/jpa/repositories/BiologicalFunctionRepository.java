package com.closedsource.psymed.platform.patientreport.infrastructure.persistence.jpa.repositories;

import com.closedsource.psymed.platform.patientreport.domain.model.aggregates.BiologicalFunction;
import com.closedsource.psymed.platform.patientreport.domain.model.valueobjects.PatientId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BiologicalFunctionRepository extends JpaRepository<BiologicalFunction, Long> {
    List<BiologicalFunction> findAllByPatientId(PatientId patientId);
    boolean existsByPatientId(PatientId patientId);
}
