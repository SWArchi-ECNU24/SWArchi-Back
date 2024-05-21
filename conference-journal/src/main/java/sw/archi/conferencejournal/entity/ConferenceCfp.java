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
public class ConferenceCfp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cfp_id", nullable = false)
    int cfpId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conference_id", referencedColumnName = "conference_id")
    @Column(name = "conference_id", nullable = false)
    int conferenceId;

    @Column(name = "user_id", nullable = false)
    int userId;

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

    @Column(name = "is_approved")
    String isApproved;
}