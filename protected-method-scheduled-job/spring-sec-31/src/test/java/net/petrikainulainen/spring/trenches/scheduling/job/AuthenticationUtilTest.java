package net.petrikainulainen.spring.trenches.scheduling.job;

import org.junit.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Petri Kainulainen
 */
public class AuthenticationUtilTest {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ROLE = "ROLE_USER";

    @Test
    public void clearAuthentication_ShouldRemoveAuthenticationFromSecurityContext() {
        Authentication authentication = createAuthentication();
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AuthenticationUtil.clearAuthentication();

        Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
        assertThat(currentAuthentication).isNull();
    }

    private Authentication createAuthentication() {
        Collection<GrantedAuthority> grantedAuthorities = createGrantedAuthorities(ROLE);
        User principal = new User(USERNAME, PASSWORD, grantedAuthorities);
        return new TestingAuthenticationToken(principal, grantedAuthorities);

    }

    private Collection<GrantedAuthority> createGrantedAuthorities(String role) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
        return Arrays.asList(grantedAuthority);
    }

    @Test
    public void configurationAuthentication_ShouldSetAuthenticationToSecurityContext() {
        AuthenticationUtil.configureAuthentication(ROLE);

        Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
        assertThat(currentAuthentication.getAuthorities()).hasSize(1);
        assertThat(currentAuthentication.getAuthorities().iterator().next().getAuthority()).isEqualTo(ROLE);

        User principal = (User) currentAuthentication.getPrincipal();
        assertThat(principal.getAuthorities()).hasSize(1);
        assertThat(principal.getAuthorities().iterator().next().getAuthority()).isEqualTo(ROLE);
    }
}
