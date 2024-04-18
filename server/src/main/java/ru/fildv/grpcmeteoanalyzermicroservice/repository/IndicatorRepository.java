package ru.fildv.grpcmeteoanalyzermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.fildv.grpcmeteoanalyzermicroservice.model.Indicator;

import java.util.List;

public interface IndicatorRepository extends JpaRepository<Indicator, Long> {
    @Query(value = """
            SELECT id, meteo_id, timestamp, value, type
            FROM indicator
            OFFSET (SELECT current_offset FROM offsets LIMIT 1)
            LIMIT :batchSize
            """, nativeQuery = true)
    List<Indicator> findAllWithOffset(@Param("batchSize") long batchSize);

    @Modifying
    @Query(value = """
            UPDATE offsets
            SET current_offset = current_offset + :batchSize
            """, nativeQuery = true)
    void incrementOffset(@Param("batchSize") long batchSize);
}
