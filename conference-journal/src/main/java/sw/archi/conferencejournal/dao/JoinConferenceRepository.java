package sw.archi.conferencejournal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sw.archi.conferencejournal.entity.JoinConference;

public interface JoinConferenceRepository extends JpaRepository<JoinConference, Integer> {}
