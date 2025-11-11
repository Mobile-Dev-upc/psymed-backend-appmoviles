    package com.closedsource.psymed.platform.patientreport.application.commandservices;

    import com.closedsource.psymed.platform.patientreport.application.outboundservices.ExternalProfileService;
    import com.closedsource.psymed.platform.patientreport.application.outboundservices.acl.ExternalProfileServiceImpl;
    import com.closedsource.psymed.platform.patientreport.domain.exceptions.PatientNotFoundException;
    import com.closedsource.psymed.platform.patientreport.domain.model.aggregates.MoodState;
    import com.closedsource.psymed.platform.patientreport.domain.model.commands.CreateMoodStateRecordCommand;
    import com.closedsource.psymed.platform.patientreport.infrastructure.persistence.jpa.repositories.MoodStateRepository;
    import com.closedsource.psymed.platform.patientreport.domain.services.MoodStateCommandService;
    import org.springframework.stereotype.Service;

    import java.util.Optional;

    @Service
    public class MoodStateCommandServiceImpl implements MoodStateCommandService {

        private final MoodStateRepository moodStateRecordRepository;
        private final ExternalProfileService externalProfileService;

        public MoodStateCommandServiceImpl(MoodStateRepository moodStateRepository, ExternalProfileServiceImpl externalProfileService) {
            this.moodStateRecordRepository = moodStateRepository;
            this.externalProfileService = externalProfileService;
        }
        @Override
        public Optional<MoodState> handle(CreateMoodStateRecordCommand command) {

            var patientIdExists = externalProfileService.existsPatientById(command.patientId());
            if(patientIdExists) {
                var moodStateRecord = new MoodState(command.patientId(), command.moodStatus());
                var lastMoodStateRecord = moodStateRecordRepository
                        .findAllByPatientId(moodStateRecord.getPatientId())
                        .stream().reduce((first, second) -> second).orElse(null);

                if(lastMoodStateRecord != null) moodStateRecord.validateRecordAvailability(lastMoodStateRecord);

                try {
                    var createdMoodStateRecord = moodStateRecordRepository.save(moodStateRecord);
                    return Optional.of(createdMoodStateRecord);

                }catch(Exception e) {
                    throw new IllegalArgumentException("Error creating the mood state record: %s"
                            .formatted(e.getMessage()));
                }
            }
            throw new PatientNotFoundException(command.patientId());




        }
    }
