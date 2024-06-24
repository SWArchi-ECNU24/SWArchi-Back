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
public class JournalCfp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jc_id", nullable = false)
    int jcId;

    @Column(name = "journal_id")
    int journalId;

    @Column(name = "submission_information")
    String submissionInformation;

    @Column(name = "is_approved")
    String isApproved;
}
