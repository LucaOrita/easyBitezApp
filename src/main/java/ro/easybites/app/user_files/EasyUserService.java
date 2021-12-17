package ro.easybites.app.user_files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.easybites.app.logic.MainInfoFetcher;
import ro.easybites.app.model.SimpleUser;

@Service
public class EasyUserService implements UserDetailsService {

    @Autowired
    private MainInfoFetcher mainInfoFetcher;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SimpleUser user = mainInfoFetcher.login(email);

        if(user == null)
            throw new UsernameNotFoundException("User not Found");
        if(!user.getActivated())
            throw new UsernameNotFoundException("User is not activated");
        return new EasyUserDetails(user);
    }
}
