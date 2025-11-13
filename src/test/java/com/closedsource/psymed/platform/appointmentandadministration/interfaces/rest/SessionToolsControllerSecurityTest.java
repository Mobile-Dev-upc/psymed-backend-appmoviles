package com.closedsource.psymed.platform.appointmentandadministration.interfaces.rest;

import com.closedsource.psymed.platform.appointmentandadministration.domain.model.queries.GetAllTasksBySessionIdQuery;
import com.closedsource.psymed.platform.appointmentandadministration.domain.services.SessionCommandService;
import com.closedsource.psymed.platform.appointmentandadministration.domain.services.SessionQueryService;
import com.closedsource.psymed.platform.iam.infrastructure.authorization.sfs.configuration.WebSecurityConfiguration;
import com.closedsource.psymed.platform.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.closedsource.psymed.platform.iam.infrastructure.hashing.bcrypt.BCryptHashingService;
import com.closedsource.psymed.platform.iam.infrastructure.tokens.jwt.BearerTokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionToolsController.class)
@ImportAutoConfiguration(exclude = {
        HibernateJpaAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class
})
@Import(WebSecurityConfiguration.class)
class SessionToolsControllerSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessionCommandService sessionCommandService;

    @MockBean
    private SessionQueryService sessionQueryService;

    @MockBean
    private BearerTokenService tokenService;

    @MockBean(name = "defaultUserDetailsService")
    private UserDetailsService userDetailsService;

    @MockBean
    private BCryptHashingService hashingService;

    @MockBean(name = "unauthorizedRequestHandlerEntryPoint")
    private AuthenticationEntryPoint unauthorizedRequestHandlerEntryPoint;

    @MockBean
    private JpaMetamodelMappingContext jpaMappingContext;

    @Test
    void givenValidToken_whenRequestingTasks_thenReturnsOk() throws Exception {
        var token = "test-token";
        var username = "camila";
        var userDetails = new UserDetailsImpl(
                username,
                "password",
                List.of(new SimpleGrantedAuthority("ROLE_PROFESSIONAL"))
        );

        when(tokenService.getBearerTokenFrom(any(HttpServletRequest.class))).thenReturn(token);
        when(tokenService.validateToken(token)).thenReturn(true);
        when(tokenService.getUsernameFromToken(token)).thenReturn(username);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(sessionQueryService.handle(any(GetAllTasksBySessionIdQuery.class))).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/sessions/1/tasks")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk());

        Mockito.verify(sessionQueryService).handle(any(GetAllTasksBySessionIdQuery.class));
    }
}

