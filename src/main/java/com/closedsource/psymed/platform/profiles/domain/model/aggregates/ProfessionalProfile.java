package com.closedsource.psymed.platform.profiles.domain.model.aggregates;

import com.closedsource.psymed.platform.profiles.domain.model.commands.CreateProfessionalProfileCommand;
import com.closedsource.psymed.platform.profiles.domain.model.valueobjects.AccountId;
import com.closedsource.psymed.platform.profiles.domain.model.valueobjects.Email;
import com.closedsource.psymed.platform.profiles.domain.model.valueobjects.PersonName;
import com.closedsource.psymed.platform.profiles.domain.model.valueobjects.StreetAddress;
import com.closedsource.psymed.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class ProfessionalProfile extends AuditableAbstractAggregateRoot<ProfessionalProfile > {

    @Embedded
    private PersonName personName;

    @Embedded
    private Email email;

    @Embedded
    AccountId accountId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "address_street")),
            @AttributeOverride(name = "city" , column = @Column(name = "address_city")),
            @AttributeOverride(name = "country", column = @Column(name = "address_country"))
    })
    private StreetAddress streetAddress;

    public ProfessionalProfile(String firstName, String lastName, String street, String city, String country, String email) {
        this.personName = new PersonName(firstName, lastName);
        this.streetAddress = new StreetAddress(street, city, country);
        this.email = new Email(email);
    }

    public ProfessionalProfile(CreateProfessionalProfileCommand command, AccountId accountId) {
        this.personName = new PersonName(command.firstName(), command.lastName());
        this.streetAddress = new StreetAddress(command.street(), command.city(), command.country());
        this.email = new Email(command.email());
        this.accountId = accountId;
    }


    public void updatePersonName(String firstName, String lastName) {
        this.personName = new PersonName(firstName, lastName);
    }

    public void updateStreetAddress(String street, String city, String country) {
        this.streetAddress = new StreetAddress(street, city, country);
    }

    public String getFullName() {
        return personName.getFullName();
    }

    public String getStreetAddress() {
        return streetAddress.getStreetAddress();
    }

    public String getEmail() {
        return email.email();
    }

    public AccountId getAccountId() {
        return accountId;
    }
}
