package com.closedsource.psymed.platform.patientreport.domain.model.commands;

public record CreateBiologicalFunctionRecordCommand(Integer hunger, Integer hydration,
                                                    Integer sleep, Integer energy,
                                                    Long patientId) {
}
