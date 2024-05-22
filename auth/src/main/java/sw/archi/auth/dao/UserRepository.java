package sw.archi.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.archi.auth.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {}
