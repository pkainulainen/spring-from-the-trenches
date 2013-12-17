package net.petrikainulainen.spring.trenches.scheduling.job;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * This class provides static methods which are used to configure
 * the Authentication object which is used when a scheduled job
 * is invoked.
 *
 * The methods of this class must be called from every job which invokes protected
 * methods. There is one reason for this:
 *
 * If you haven't configured otherwise, the security context is stored to ThreadLocal.
 * In other words, Each thread has its own security context. This means that all
 * scheduled jobs which are executed in the same thread share the same security context.
 *
 * If you use the default thread pool which has only one thread, the other jobs have the same
 * privileges than the job which added the Authentication object to the the security context.
 *
 * If you use a thread pool, the other jobs have the same privileges than the job which
 * added the Authentication object to the security context IF these jobs are executed in
 * the same thread than the "original" job.
 *
 * This might not a problem because often we want that all jobs are run
 * by using the same privileges. The problem is that we want to avoid dependencies
 * between jobs. Because we want that each job is can act is an independent unit
 * of work, we have to configure the used Authentication object in every job
 * which requires access to protected methods.
 * @author Petri Kainulainen
 */
final class AuthenticationUtil {

    private static final String PASSWORD = "password";
    private static final String USERNAME = "user";

    //Ensures that this class cannot be instantiated
    private AuthenticationUtil() {
    }

    /**
     * Removes the authentication from the security context. This method must
     * be called after the job has been run.
     */
    static void clearAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    /**
     * Sets the authentication to the security context. This method must
     * be called before the job is run.
     * @param role  The role which is granted to the created Authentication object.
     */
    static void configureAuthentication(String role) {
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(role);
        User principal = new User(USERNAME, PASSWORD, authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, "", authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
