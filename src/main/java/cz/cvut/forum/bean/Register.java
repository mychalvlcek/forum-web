package cz.cvut.forum.bean;

import cz.cvut.forum.dto.UserDTO;
import cz.cvut.forum.helper.FacesUtil;
import cz.cvut.forum.service.UserService;
import cz.cvut.forum.service.UserServiceImpl;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class Register {

    private String username;
    private String email;
    private String password;
    private boolean isAdmin;

    protected UserService userService;

    @PostConstruct
    public void postConstruct() {
        this.userService = new UserServiceImpl();
    }


    public String storeUser() throws IOException {
        UserDTO user = new UserDTO();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        userService.save(user);
//        userService.addUser(getUsername(), getPassword(), getEmail(), getIsAdmin());
        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Úspěch!", "Registrace proběhla úspěšně."));
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml?new=1");
        return "index";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}