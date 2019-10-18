package com.windmill.manager.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.windmill.manager.entity.WindMill;
import com.windmill.manager.entity.WindMillMetric;
import com.windmill.manager.exceptions.AppException;
import com.windmill.manager.model.WindMillDTO;
import com.windmill.manager.model.WindMillMetricDTO;
import com.windmill.manager.repo.WindMillMetricRepo;
import com.windmill.manager.repo.WindMillRegisterRepo;
import com.windmill.manager.service.impl.WindMillServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class WindMillServiceUnitTest {

	@Mock
	private WindMillMetricRepo windMillMetricRepo;

	@Mock
	private WindMillRegisterRepo windMillRegisterRepo;

	@InjectMocks
	private WindMillServiceImpl windMillService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testRegisterWindMillHappyPath() {

		WindMillDTO windMillDTO = new WindMillDTO();
		windMillDTO.setSerialNumber("1234567890qwerty");
		windMillDTO.setLatitude(123.456789);
		windMillDTO.setLongitude(100.123456);

		lenient().when(this.windMillRegisterRepo.save(any(WindMill.class))).thenReturn(new WindMill());

		lenient().when(this.windMillRegisterRepo.existsByLatitudeAndLongitude(any(Double.class), any(Double.class)))
				.thenReturn(false);

		lenient().when(this.windMillRegisterRepo.existsBySerialNumber(any(String.class))).thenReturn(false);

		boolean result = this.windMillService.registerWindMill(windMillDTO);

		assertEquals(true, result);

	}

	@Test(expected = AppException.class)
	public void testRegisterWindMillUnhappyPath() {

		WindMillDTO windMillDTO = new WindMillDTO();
		windMillDTO.setSerialNumber("1234567890qwerty");
		windMillDTO.setLatitude(123.456789);
		windMillDTO.setLongitude(100.123456);

		lenient().when(this.windMillRegisterRepo.existsByLatitudeAndLongitude(any(Double.class), any(Double.class)))
				.thenReturn(true);

		lenient().when(this.windMillRegisterRepo.existsBySerialNumber(any(String.class))).thenReturn(true);

		lenient().when(this.windMillRegisterRepo.save(any(WindMill.class))).thenReturn(new WindMill());

		this.windMillService.registerWindMill(windMillDTO);

	}

	@Test
	public void testWindMillMetricHappyPath() {

		WindMillMetricDTO windMillMetricDTO = new WindMillMetricDTO();

		windMillMetricDTO.setSerialNumber("1234567890qwerty");
		windMillMetricDTO.setDateTime(LocalDateTime.now());
		windMillMetricDTO.setPower(2.0f);
		lenient().when(this.windMillRegisterRepo.existsBySerialNumber(any(String.class))).thenReturn(true);
		lenient().when(this.windMillMetricRepo.save(any(WindMillMetric.class))).thenReturn(new WindMillMetric());

		boolean result = this.windMillService.windMillMetric(windMillMetricDTO);

		assertEquals(true, result);

	}

	@Test(expected = AppException.class)
	public void testWindMillMetricUnhappyPath() {

		WindMillMetricDTO windMillMetricDTO = new WindMillMetricDTO();

		windMillMetricDTO.setSerialNumber("1234567890qwerty");
		windMillMetricDTO.setDateTime(LocalDateTime.now());
		windMillMetricDTO.setPower(2.0f);
		lenient().when(this.windMillRegisterRepo.existsBySerialNumber(any(String.class))).thenReturn(false);
		lenient().when(this.windMillMetricRepo.save(any(WindMillMetric.class))).thenReturn(new WindMillMetric());

		this.windMillService.windMillMetric(windMillMetricDTO);

	}

	@Test(expected = AppException.class)
	public void testGetAggregatedPowerInfoUnhappyPath() {

		lenient().when(this.windMillMetricRepo.getAggregatedPowerInfo()).thenReturn(new ArrayList<Object[]>());
		this.windMillService.getAggregatedPowerInfo();

	}

}
