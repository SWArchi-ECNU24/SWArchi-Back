package sw.archi.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.archi.auth.entity.UserFollowers;

public interface UserFollowersRepository extends JpaRepository<UserFollowers, Integer> {}
