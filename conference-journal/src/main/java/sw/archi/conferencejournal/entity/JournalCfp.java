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
public class JournalCfp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jc_id", nullable = false)
    int jcId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "journal_id", referencedColumnName = "journal_id")
    Journal journalId;

    @Column(name = "submission_information")
    String submissionInformation;

    @Column(name = "is_approved")
    String isApproved;
}
