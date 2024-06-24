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
public class JournalIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ji_id", nullable = false)
    int jiId;

    @Column(name = "journal_id")
    int journalId;

    @Column(name = "special_issue")
    String specialIssue;

    @Column(name = "is_approved")
    String isApproved;
}
