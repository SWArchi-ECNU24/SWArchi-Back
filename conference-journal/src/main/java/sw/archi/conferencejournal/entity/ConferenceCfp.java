package sw.archi.conferencejournal.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

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
    Conference conferenceId;

    @Column(name = "submission_deadline")
    String submissionDeadline;

    @Column(name = "notification_date")
    @JSONField(format = "yyyy-MM-dd")
    Date notificationDate;

    @Column(name = "submission_information")
    String submissionInformation;

    @Column(name = "is_approved")
    String isApproved;
}
