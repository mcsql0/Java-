package mcsql.net.entities;

public class LoginInfo {

    private String userName;
    private String Password;

    public LoginInfo() {
    }

    public LoginInfo(String userName, String password) {
        this.userName = userName;
        Password = password;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                ", userName='" + userName + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
