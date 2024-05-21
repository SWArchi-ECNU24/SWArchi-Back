  package sw.archi.conferencejournal.entity;

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
public class FollowedConference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id", nullable = false)
    int followId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conference_id", referencedColumnName = "conference_id")
    @Column(name = "conference_id", nullable = false)
    int conferenceId;

    @Column(name = "user_id", nullable = false)
    int userId;

}