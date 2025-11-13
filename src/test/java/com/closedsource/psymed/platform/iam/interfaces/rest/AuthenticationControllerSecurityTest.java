package com.closedsource.psymed.platform.iam.interfaces.rest;

import com.closedsource.psymed.platform.iam.domain.model.aggregate.Account;
import com.closedsource.psymed.platform.iam.domain.model.commands.SignInCommand;
import com.closedsource.psymed.platform.iam.domain.model.valueobjects.Roles;
import com.closedsource.psymed.platform.iam.domain.service.AccountCommandService;
import com.closedsource.psymed.platform.iam.infrastructure.authorization.sfs.configuration.WebSecurityConfiguration;
import com.closedsource.psymed.platform.iam.infrastructure.hashing.bcrypt.BCryptHashingService;
import com.closedsource.psymed.platform.iam.infrastructure.tokens.jwt.BearerTokenService;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthenticationController.class)
@ImportAutoConfiguration(exclude = {
        HibernateJpaAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class
})
@Import(WebSecurityConfiguration.class)
class AuthenticationControllerSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(name = "defaultUserDetailsService")
    private UserDetailsService userDetailsService;

    @MockBean
    private BearerTokenService tokenService;

    @MockBean
    private BCryptHashingService hashingService;

    @MockBean
    private AuthenticationEntryPoint unauthorizedRequestHandlerEntryPoint;

    @MockBean
    private AccountCommandService accountCommandService;

    @MockBean
    private JpaMetamodelMappingContext jpaMappingContext;

    @Test
    void signIn_shouldBeAccessibleWithoutAuthentication() throws Exception {
        var token = "test-token";
        when(accountCommandService.handle(any(SignInCommand.class)))
                .thenAnswer(invocation -> {
                    var account = new Account();
                    ReflectionTestUtils.setField(account, "role", Roles.ROLE_PROFESSIONAL);
                    ReflectionTestUtils.setField(account, "userName", "user");
                    ReflectionTestUtils.setField(account, "password", "pass");
                    ReflectionTestUtils.setField(account, "id", 1L);
                    return java.util.Optional.of(ImmutablePair.of(account, token));
                });

        mockMvc.perform(post("/api/v1/authentication/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"username":"user","password":"pass"}
                                """))
                .andExpect(status().isOk());
    }
}

