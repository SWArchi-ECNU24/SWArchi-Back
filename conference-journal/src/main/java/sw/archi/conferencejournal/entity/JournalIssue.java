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
    Journal journalId;

    @Column(name = "special_issue")
    String specialIssue;

    @Column(name = "is_approved")
    String isApproved;
}
