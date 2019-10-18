package com.windmill.manager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.windmill.manager.entity.WindMill;

@Repository
public interface WindMillRegisterRepo extends JpaRepository<WindMill, String> {

	public boolean existsBySerialNumber(String serialNumber);

	public boolean existsByLatitudeAndLongitude(Double latitude, Double longitude);

}
