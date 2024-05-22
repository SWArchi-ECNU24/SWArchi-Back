package sw.archi.conferencejournal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.archi.conferencejournal.entity.UserFollowers;

public interface UserFollowersRepository extends JpaRepository<UserFollowers, Integer> {}
