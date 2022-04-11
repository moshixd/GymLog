package net.moshi.gymlog.repository;

import net.moshi.gymlog.model.Records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordsRepository extends JpaRepository<Records, Integer> {
}
