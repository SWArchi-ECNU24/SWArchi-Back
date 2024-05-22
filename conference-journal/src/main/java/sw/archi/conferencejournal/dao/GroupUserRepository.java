package sw.archi.conferencejournal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.archi.conferencejournal.entity.GroupUser;

public interface GroupUserRepository extends JpaRepository<GroupUser, Integer> {}
