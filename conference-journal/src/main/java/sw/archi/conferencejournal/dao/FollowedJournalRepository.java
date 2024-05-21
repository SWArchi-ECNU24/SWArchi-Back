package sw.archi.conferencejournal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.archi.conferencejournal.entity.FollowedJournal;

public interface FollowedJournalRepository extends JpaRepository<FollowedJournal, Integer> {}