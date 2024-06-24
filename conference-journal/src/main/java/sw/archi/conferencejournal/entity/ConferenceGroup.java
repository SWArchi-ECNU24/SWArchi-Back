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
public class ConferenceGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false)
    int groupId;

    @Column(name = "conference_id")
    int conferenceId;

    @Column(name = "group_name")
    String groupName;

    @Column(name = "user_id")
    String userId;
}
