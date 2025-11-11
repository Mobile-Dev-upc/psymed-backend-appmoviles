package com.closedsource.psymed.platform.patientreport.interfaces.rest.resources;

public record CreateBiologicalFunctionRecordResource(Integer hunger,
                                                     Integer hydration, Integer sleep,
                                                     Integer energy) {
}
