package sw.archi.conferencejournal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.archi.conferencejournal.entity.Journal;

public interface JournalRepository extends JpaRepository<Journal, Integer> {}