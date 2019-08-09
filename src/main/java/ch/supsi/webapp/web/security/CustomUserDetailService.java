package ch.supsi.webapp.web.security;

import ch.supsi.webapp.web.model.Utente;
import ch.supsi.webapp.web.service.BlogpostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private BlogpostService blogpostService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utente user = blogpostService.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        List<GrantedAuthority> auth = AuthorityUtils.createAuthorityList (user.getRuolo().getRuolo());
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), true, true, true, true, auth);
    }

}
