package in.project.restapi.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user")
public class UserClientModel {
    @Id
    
    private Long userId;
    private Boolean admin;
    @Indexed(unique=true)
    private String username;
    private String userPassword;
    private String userMail;
    private Boolean supervisor;
    private Long ticket;
    


    public UserClientModel() {

   

 }

    public UserClientModel(Long userId, String username, String userFirstName, String userLasStName, String userPassword, String userMail, Long ticket) {
        this.userId = userId;
        this.admin = false;
        this.username = username;
        this.userPassword = userPassword;
        this.userMail = userMail;
        this.supervisor = false;
        this.ticket = ticket;
    }


    public UserClientModel(Long userId, Boolean admin, String username, String userFirstName, String userLasStName, String userPassword, String userMail, Boolean supervisor, Long ticket) {
        this.userId = userId;
        this.admin = admin;
        this.username = username;
        this.userPassword = userPassword;
        this.userMail = userMail;
        this.supervisor = supervisor;
        this.ticket = ticket;
    }
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public UserClientModel admin(Boolean admin) {
        setAdmin(admin);
        return this;
    }

    public UserClientModel ticket(Long ticket) {
        setTicket(ticket);
        return this;
    }

   
    
    

    public Long getTicket() {
        return this.ticket;
    }

    public void setTicket(Long ticket) {
        this.ticket = ticket;
    }

    public Long getUserId() {
        return this.userId;
    }


    public Boolean isAdmin() {
        return this.admin;
    }

    public Boolean getAdmin() {
        return this.admin;
    }
//-------------------------------------
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public UserClientModel userId(Long userId) {
        setUserId(userId);
        return this;
    }

    public UserClientModel username(String username) {
        setUsername(username);
        return this;
    }

    public UserClientModel userPassword(String userPassword) {
        setUserPassword(userPassword);
        return this;
    }

  
//-----------------------
  


    public String getUserMail() {
        return this.userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public Boolean isSupervisor() {
        return this.supervisor;
    }

    public Boolean getSupervisor() {
        return this.supervisor;
    }

    public void setSupervisor(Boolean supervisor) {
        this.supervisor = supervisor;
    }

    


    public UserClientModel userMail(String userMail) {
        setUserMail(userMail);
        return this;
    }

    public UserClientModel supervisor(Boolean supervisor) {
        setSupervisor(supervisor);
        return this;
    }



    @Override
    public String toString() {
        return "{" +
            " userId='" + getUserId() + "'" +
            ", admin='" + isAdmin() + "'" +
            ", username='" + getUsername() + "'" +
            ", userPassword='" + getUserPassword() + "'" +
            ", userMail='" + getUserMail() + "'" +
            ", supervisor='" + isSupervisor() + "'" +
            ", ticket='" + getTicket() + "'" +
            "}";
    }

    public UserClientModel get() {
        return this ;
    }

    public UserClientModel orElseThrow(Object object) {
        return null;
    }

    public String getEmail() {
        return null;
    }
   
}
