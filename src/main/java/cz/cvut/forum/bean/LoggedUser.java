package cz.cvut.forum.bean;

import cz.cvut.forum.dto.UserDTO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "user")
@SessionScoped
public class LoggedUser implements Serializable {
    private UserDTO user = null;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Long getId() {
        return user.getId();
    }

    public String getUsername() {
//        LoggedUserDetails user = (LoggedUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }
}
