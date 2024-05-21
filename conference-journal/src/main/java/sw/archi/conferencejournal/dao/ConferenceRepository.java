package sw.archi.conferencejournal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.archi.conferencejournal.entity.Conference;

public interface ConferenceRepository extends JpaRepository<Conference, Integer> {}