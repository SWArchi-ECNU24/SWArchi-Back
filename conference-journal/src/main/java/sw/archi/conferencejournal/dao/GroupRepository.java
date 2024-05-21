package sw.archi.conferencejournal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.archi.conferencejournal.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {}