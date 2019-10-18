package com.windmill.manager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.windmill.manager.entity.WindMillMetric;
import com.windmill.manager.entity.WindMillMetricId;
import com.windmill.manager.model.PowerAggregate;

public interface WindMillMetricRepo extends JpaRepository<WindMillMetric, WindMillMetricId> {

	@Query(value = "SELECT CAST(date_time AS DATE), AVG(power), MIN(power), MAX(power), SUM(power) from windmill_metric GROUP BY CAST(date_time AS DATE) ORDER BY CAST(date_time AS DATE) ", nativeQuery = true)
	public List<Object[]> getAggregatedPowerInfo();
}
