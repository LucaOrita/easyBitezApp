package ro.easybites.app.model;

public class RegisterUserRequest {

    public String username;
    public String pass;
    public String tel;
    public String email;

    public RegisterUserRequest(String username, String pass, String tel, String email) {
        this.username = username;
        this.pass = pass;
        this.tel = tel;
        this.email = email;
    }

    public RegisterUserRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "RegisterUserRequest{" +
                "username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
