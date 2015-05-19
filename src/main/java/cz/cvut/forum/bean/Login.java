package cz.cvut.forum.bean;

import cz.cvut.forum.dto.UserDTO;
import cz.cvut.forum.helper.FacesUtil;
import cz.cvut.forum.service.UserService;
import cz.cvut.forum.service.UserServiceImpl;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

@ManagedBean(name = "login")
@RequestScoped
public class Login implements Serializable {

    private UserService userService;

    @PostConstruct
    public void postConstruct() {
        this.userService = new UserServiceImpl();
    }

    @ManagedProperty(value = "#{user}")
    LoggedUser user;

    private String email;
    private String password;

    public void login() throws IOException {

        UserDTO u = userService.login(email, password);

        if (u != null) {
            user.setUser(u);
            FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Byl jste příhlášen."));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("");
        } else {
            FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "INFO", "Chyba"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        }
    }

    public void logout() throws IOException {
        user.setUser(null);
        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Byl jste odhlasen."));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/");
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

    public LoggedUser getUser() {
        return user;
    }

    public void setUser(LoggedUser user) {
        this.user = user;
    }
}
