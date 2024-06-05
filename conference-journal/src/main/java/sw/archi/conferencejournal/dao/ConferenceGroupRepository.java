package sw.archi.conferencejournal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.archi.conferencejournal.entity.ConferenceGroup;

public interface ConferenceGroupRepository extends JpaRepository<ConferenceGroup, Integer> {}
