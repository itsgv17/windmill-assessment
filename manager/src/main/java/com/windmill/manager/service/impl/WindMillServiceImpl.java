package com.windmill.manager.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.windmill.manager.constant.AppLevelErrorConstant;
import com.windmill.manager.entity.WindMill;
import com.windmill.manager.entity.WindMillMetric;
import com.windmill.manager.entity.WindMillMetricId;
import com.windmill.manager.exceptions.AppException;
import com.windmill.manager.exceptions.ErrorLevel;
import com.windmill.manager.exceptions.ErrorType;
import com.windmill.manager.exceptions.ExceptionResponse;
import com.windmill.manager.model.PowerAggregate;
import com.windmill.manager.model.WindMillDTO;
import com.windmill.manager.model.WindMillMetricDTO;
import com.windmill.manager.repo.WindMillMetricRepo;
import com.windmill.manager.repo.WindMillRegisterRepo;
import com.windmill.manager.service.WindMillService;
import com.windmill.manager.util.BeanValidatorUtil;

@Service
public class WindMillServiceImpl implements WindMillService {

	@Autowired
	private WindMillRegisterRepo windMillRegisterRepo;
	@Autowired
	private WindMillMetricRepo windMillMetricRepo;
	
	@Override
	public boolean registerWindMill(WindMillDTO windMillDto) {

		List<String> errors = BeanValidatorUtil.validate(windMillDto, WindMillDTO.class);

		if (null != errors && !errors.isEmpty()) {

			throw new AppException("BadRequest", HttpStatus.BAD_REQUEST, Arrays.asList(
					new ExceptionResponse.ExceptionResponseBuilder(AppLevelErrorConstant.BAD_REQUEST, errors.toString())
							.withErrorLevel(ErrorLevel.TECHNICAL).withErrorType(ErrorType.ERROR).build()));
		}

		WindMill windmill = this.populateWindMillEntity(windMillDto);

		if (null != windmill) {
			this.windMillRegisterRepo.save(windmill);
		}
		return true;
	}

	private WindMill populateWindMillEntity(WindMillDTO windMillDto) {

		WindMill windMill = null;

		if (null != windMillDto) {

			List<String> errors = new ArrayList<>();

			boolean hasSerialNumber = this.windMillRegisterRepo.existsBySerialNumber(windMillDto.getSerialNumber());

			if (hasSerialNumber) {

				errors.add("WindMill with Serial Number " + windMillDto.getSerialNumber() + " already exists");
			}

			boolean hasLatLong = this.windMillRegisterRepo.existsByLatitudeAndLongitude(windMillDto.getLatitude(),
					windMillDto.getLongitude());

			if (hasLatLong) {
				errors.add("Provided geo coordinates(" + windMillDto.getLatitude() + ", " + windMillDto.getLongitude()
						+ ") has been already occpied. Provide unique geo coordinates.");
			}

			if (hasSerialNumber || hasLatLong) {
				throw new AppException("Conflict", HttpStatus.CONFLICT,
						Arrays.asList(new ExceptionResponse.ExceptionResponseBuilder(AppLevelErrorConstant.BAD_REQUEST,
								errors.toString()).withErrorLevel(ErrorLevel.TECHNICAL).withErrorType(ErrorType.ERROR)
										.build()));
			}

			windMill = new WindMill();
			windMill.setSerialNumber(windMillDto.getSerialNumber());
			windMill.setName(windMillDto.getName());
			windMill.setAddress(windMillDto.getAddress());
			windMill.setLatitude(windMillDto.getLatitude());
			windMill.setLongitude(windMillDto.getLongitude());

		}

		return windMill;
	}

	@Override
	public boolean windMillMetric(WindMillMetricDTO windMillMetricDTO) {

		List<String> errors = BeanValidatorUtil.validate(windMillMetricDTO, WindMillMetricDTO.class);

		if (null != errors && !errors.isEmpty()) {

			throw new AppException("BadRequest", HttpStatus.BAD_REQUEST, Arrays.asList(
					new ExceptionResponse.ExceptionResponseBuilder(AppLevelErrorConstant.BAD_REQUEST, errors.toString())
							.withErrorLevel(ErrorLevel.TECHNICAL).withErrorType(ErrorType.ERROR).build()));
		}

		WindMillMetric windMillMetric = this.populateWindMillMetricEntity(windMillMetricDTO);

		if (null != windMillMetric) {
			this.windMillMetricRepo.save(windMillMetric);
		}

		return true;
	}

	private WindMillMetric populateWindMillMetricEntity(WindMillMetricDTO windMillMetricDTO) {

		WindMillMetric windMillMetric = null;

		if (null != windMillMetricDTO) {
			boolean hasSerialNumber = this.windMillRegisterRepo
					.existsBySerialNumber(windMillMetricDTO.getSerialNumber());

			if (!hasSerialNumber) {
				throw new AppException("BadRequest", HttpStatus.BAD_REQUEST,
						Arrays.asList(new ExceptionResponse.ExceptionResponseBuilder(AppLevelErrorConstant.BAD_REQUEST,
								"Unregistered windmill. Register windmill for posting the readings")
										.withErrorLevel(ErrorLevel.FUNCTIONAL).withErrorType(ErrorType.INFO).build()));
			}

			windMillMetric = new WindMillMetric();

			WindMillMetricId windMillMetricId = new WindMillMetricId(windMillMetricDTO.getSerialNumber(),
					windMillMetricDTO.getDateTime());
			windMillMetric.setWindMillMetricId(windMillMetricId);
			windMillMetric.setPower(windMillMetricDTO.getPower());
		}

		return windMillMetric;

	}

	@Override
	public List<PowerAggregate> getAggregatedPowerInfo() {

		List<Object[]> objectResultList = this.windMillMetricRepo.getAggregatedPowerInfo();

		if (null != objectResultList && objectResultList.isEmpty()) {

			throw new AppException("No Content", HttpStatus.NOT_FOUND,
					Arrays.asList(new ExceptionResponse.ExceptionResponseBuilder(AppLevelErrorConstant.NO_CONTENT,
							"No Aggregated results found.").withErrorLevel(ErrorLevel.FUNCTIONAL)
									.withErrorType(ErrorType.INFO).build()));
		}

		List<PowerAggregate> powerAggregateList = new ArrayList<>();

		for (Object[] item : objectResultList) {

			PowerAggregate powerAggregate = new PowerAggregate();

			powerAggregate.setDate(((java.sql.Date) item[0]).toLocalDate());
			powerAggregate.setAvg((double) item[1]);
			powerAggregate.setMin((double) item[2]);
			powerAggregate.setMax((double) item[3]);
			powerAggregate.setSum((double) item[4]);

			powerAggregateList.add(powerAggregate);
		}

		return powerAggregateList;

	}

}
