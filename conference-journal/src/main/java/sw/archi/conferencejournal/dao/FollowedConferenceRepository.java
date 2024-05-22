package sw.archi.conferencejournal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.archi.conferencejournal.entity.FollowedConference;

public interface FollowedConferenceRepository extends JpaRepository<FollowedConference, Integer> {}
