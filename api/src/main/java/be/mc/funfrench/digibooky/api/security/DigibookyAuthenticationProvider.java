package be.mc.funfrench.digibooky.api.security;

import be.mc.funfrench.digibooky.domain.users.BaseUser;
import be.mc.funfrench.digibooky.service.repositories.BaseUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DigibookyAuthenticationProvider implements AuthenticationProvider {

    private final BaseUserRepository userRepository;

    @Autowired
    public DigibookyAuthenticationProvider(BaseUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        BaseUser user = userRepository.findOneByIdAndPassword(authentication.getPrincipal().toString(), authentication.getCredentials().toString());
        if (user == null) {
            throw new BadCredentialsException("Those credentials are not valid");
        }
        return new UsernamePasswordAuthenticationToken(user.getId(), user.getPassword(), rolesToGrantedAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> rolesToGrantedAuthorities(List<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
