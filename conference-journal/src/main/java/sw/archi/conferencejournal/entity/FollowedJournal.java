package sw.archi.conferencejournal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class FollowedJournal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "followed_id", nullable = false)
    int followedId;

    @Column(name = "journal_id")
    int journalId;

    @Column(name = "user_id", nullable = false)
    int userId;
}
