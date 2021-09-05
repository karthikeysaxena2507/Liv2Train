package liv2train.repository;

import liv2train.entity.TrainingCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A repository for the training center entity, to handle database operations
 */
@Repository
public interface TrainingCenterRepository extends JpaRepository<TrainingCenter, String> {
}
