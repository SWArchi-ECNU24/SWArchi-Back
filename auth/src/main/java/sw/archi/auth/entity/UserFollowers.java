package sw.archi.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserFollowers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uf_id", nullable = false)
    int ufId;

    @Column(name = "user_id", nullable = false)
    int userId;

    @Column(name = "follow_id", nullable = false)
    int followersId;
}
