package com.closedsource.psymed.platform.clinicalhistory.infrastructure.persistence.jpa.repositories;

import com.closedsource.psymed.platform.clinicalhistory.domain.model.aggregates.ClinicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClinicalHistoryRepository extends JpaRepository<ClinicalHistory,Long> {

}
