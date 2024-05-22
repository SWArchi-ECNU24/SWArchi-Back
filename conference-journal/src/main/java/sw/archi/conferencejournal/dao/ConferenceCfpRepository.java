package sw.archi.conferencejournal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sw.archi.conferencejournal.entity.ConferenceCfp;

public interface ConferenceCfpRepository extends JpaRepository<ConferenceCfp, Integer> {}
