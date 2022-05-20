package net.moshi.gymlog.repository;

import net.moshi.gymlog.model.TrainingDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingDayRepository extends JpaRepository<TrainingDay, Integer> {
}
