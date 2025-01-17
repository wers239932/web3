package database;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "paswd_hash")
    private String passwd_hash;

    @Column(name = "access_token")
    private String access_token;

    @Column(name = "logged_in")
    private Boolean logged_in;

    public Integer getId() {
        return id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public Boolean getLogged_in() {
        return logged_in;
    }

    public String getPasswd_hash() {
        return passwd_hash;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setLogged_in(Boolean logged_in) {
        this.logged_in = logged_in;
    }

    public void setPasswd_hash(String passwd_hash) {
        this.passwd_hash = passwd_hash;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
