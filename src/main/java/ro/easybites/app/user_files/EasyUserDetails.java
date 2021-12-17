package ro.easybites.app.user_files;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ro.easybites.app.model.SimpleUser;

import java.util.Collection;
import java.util.Collections;

public class EasyUserDetails implements UserDetails {

    private final SimpleUser simpleUser;

    public EasyUserDetails(SimpleUser user) {
        this.simpleUser = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println(this.simpleUser.getRole());
        return Collections.singletonList(new SimpleGrantedAuthority(this.simpleUser.getRole()));
    }

    @Override
    public String getPassword() {
        return this.simpleUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.simpleUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public SimpleUser getSimpleUser(){
        return simpleUser;
    }
}
