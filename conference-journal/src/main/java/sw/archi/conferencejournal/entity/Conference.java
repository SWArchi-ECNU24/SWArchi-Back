package sw.archi.conferencejournal.entity;

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
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conference_id", nullable = false)
    int conferenceId;

    @Column(name = "conference_name")
    String conferenceName;

    @Column(name = "conference_url")
    String conferenceUrl;

    @Column(name = "ccf_rank")
    String ccfRank;

    @Column(name = "delay")
    String delay;

    @Column(name = "submission_deadline")
    String submissionDeadline;

    @Column(name = "notification_date")
    Date notificationDate;

    @Column(name = "conference_date")
    Date conferenceDate;

    @Column(name = "conference_location")
    String conferenceLocation;

    @Column(name = "session_number")
    int sessionNumber;

    @Column(name = "submission_information")
    String submissionInformation;
}
