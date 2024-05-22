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
public class JournalIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ji_id", nullable = false)
    int jiId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "journal_id", referencedColumnName = "journal_id")
    @Column(name = "journal_id", nullable = false)
    Conference conferenceId;

    @Column(name = "user_id", nullable = false)
    int userId;

    @Column(name = "journal_name")
    String journalName;

    @Column(name = "journal_url")
    String journalUrl;

    @Column(name = "ccf_rank")
    String ccfRank;

    @Column(name = "impact_factor")
    String impactFactor;

    @Column(name = "publisher")
    String publisher;

    @Column(name = "issn")
    String issn;

    @Column(name = "submission_information")
    String submissionInformation;

    @Column(name = "special_issue")
    String specialIssue;

    @Column(name = "is_approved")
    String isApproved;
}