package com.closedsource.psymed.platform.patientreport.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record BiologicalFunctionStatus(Integer hunger, Integer hydration,
                                       Integer sleep, Integer energy) {
    public BiologicalFunctionStatus {
        if (hunger < 0 || hunger >5)
            throw new IllegalArgumentException("Hunger must be between 0 and 5");
        if (hydration < 0 || hydration >5)
            throw new IllegalArgumentException("Hydration must be between 0 and 5");
        if(sleep <0 || sleep >5)
            throw new IllegalArgumentException("Sleep must be between 0 and 5");
        if(energy < 0 || energy >5)
            throw new IllegalArgumentException("Energy must be between 0 and 5");
    }

}
