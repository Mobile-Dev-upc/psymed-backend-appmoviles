package com.closedsource.psymed.platform.patientreport.interfaces.rest.resources;

import java.util.Date;

public record BiologicalFunctionResource(Long id, Integer hunger,
                                         Integer hydration, Integer sleep,
                                         Integer energy, Date createdAt) {

}
