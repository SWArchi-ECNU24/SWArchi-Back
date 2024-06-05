package sw.archi.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    int userId;

    @Column(name = "user_name")
    String userName;

    @Column(name = "user_password")
    String userPassword;

    @Column(name = "authority")
    String authority;

    @Column(name = "email")
    String email;

    @Column(name = "institution")
    String institution;

    @Column(name = "signup_date")
    Date signupDate;
}
