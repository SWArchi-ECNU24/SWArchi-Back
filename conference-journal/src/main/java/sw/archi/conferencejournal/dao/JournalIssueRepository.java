package sw.archi.conferencejournal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.archi.conferencejournal.entity.JournalIssue;

public interface JournalIssueRepository extends JpaRepository<JournalIssue, Integer> {}