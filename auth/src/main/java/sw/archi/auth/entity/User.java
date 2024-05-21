package sw.archi.auth.entity;

<<<<<<< HEAD
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
    String authority;

    @Column(name = "signup_date")
    Date authority;
}
=======
public class User {
}
>>>>>>> 4cad37abb387eb21150ec5262e461568451e39e9
