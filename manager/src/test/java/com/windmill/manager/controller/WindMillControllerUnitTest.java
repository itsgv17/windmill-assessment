package com.windmill.manager.controller;

import static org.mockito.Mockito.lenient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;

import com.windmill.manager.constant.AppLevelErrorConstant;
import com.windmill.manager.exceptions.AppException;
import com.windmill.manager.exceptions.ErrorLevel;
import com.windmill.manager.exceptions.ErrorType;
import com.windmill.manager.exceptions.ExceptionResponse;
import com.windmill.manager.exceptions.HttpExceptionHandler;
import com.windmill.manager.model.WindMillDTO;
import com.windmill.manager.model.WindMillMetricDTO;
import com.windmill.manager.service.impl.WindMillServiceImpl;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(MockitoJUnitRunner.class)
public class WindMillControllerUnitTest {

	@Mock
	private WindMillServiceImpl windMillService;

	@InjectMocks
	private WindMillController windMillController;

	@InjectMocks
	private HttpExceptionHandler httpExceptionHandler;

	@Before
	public void initialiseRestAssuredMockMvcStandalone() {
		RestAssuredMockMvc.standaloneSetup(windMillController, httpExceptionHandler);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void givenInvalidRequestBodyWhenPostWindMillToRegisterThenRespondWithStatusBadRequest() {

		lenient().when(this.windMillService.registerWindMill(new WindMillDTO()))
				.thenThrow(new HttpMessageNotReadableException("Invalid Request body payload"));

		RestAssuredMockMvc.given().accept("application/json").contentType("application/json;charset=UTF-8")
				.body(new WindMillDTO()).when().post("/register").then().log().ifValidationFails()
				.statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	public void givenValidRequestBodyWhenPostWindMillToRegisterThenRespondWithStatusOk() {

		WindMillDTO windMillDTO = new WindMillDTO();
		windMillDTO.setSerialNumber("1234567890qwerty");
		windMillDTO.setLatitude(123.456789);
		windMillDTO.setLongitude(100.123456);

		lenient().when(this.windMillService.registerWindMill(windMillDTO)).thenReturn(true);

		RestAssuredMockMvc.given().accept("application/json").contentType("application/json;charset=UTF-8")
				.body(windMillDTO).when().post("/register").then().log().ifValidationFails()
				.statusCode(HttpStatus.OK.value());

	}

	@Test
	public void givenInvaildRequestBodyWhenPostWindMillMetricThenRespondWithBadRequest() {

		WindMillMetricDTO windMillMetricDto = new WindMillMetricDTO();
		windMillMetricDto.setSerialNumber("1234567890qwertxx");
		windMillMetricDto.setDateTime(LocalDateTime.now());
		windMillMetricDto.setPower(2.0f);

		lenient().when(this.windMillService.windMillMetric(windMillMetricDto))
				.thenThrow(new AppException("BadRequest", HttpStatus.BAD_REQUEST,
						Arrays.asList(new ExceptionResponse.ExceptionResponseBuilder(AppLevelErrorConstant.BAD_REQUEST,
								"SerialNumber must be 16 characters").withErrorLevel(ErrorLevel.FUNCTIONAL)
										.withErrorType(ErrorType.INFO).build())));

		RestAssuredMockMvc.given().accept("application/json").contentType("application/json;charset=UTF-8")
				.body(windMillMetricDto).when().post("/windmill-metric").then().log().ifValidationFails()
				.statusCode(HttpStatus.BAD_REQUEST.value());
	}

	@Test
	public void givenVaildRequestBodyWhenPostWindMillMetricThenRespondWithOK() {

		WindMillMetricDTO windMillMetricDto = new WindMillMetricDTO();
		windMillMetricDto.setSerialNumber("1234567890qwerty");
		windMillMetricDto.setDateTime(LocalDateTime.now());
		windMillMetricDto.setPower(2.0f);

		lenient().when(this.windMillService.windMillMetric(windMillMetricDto)).thenReturn(true);

		RestAssuredMockMvc.given().accept("application/json").contentType("application/json;charset=UTF-8")
				.body(windMillMetricDto).when().post("/windmill-metric").then().log().ifValidationFails()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void whenGetAggregatePowerRespondWithOK() {

		lenient().when(this.windMillService.getAggregatedPowerInfo()).thenReturn(new ArrayList<>());

		RestAssuredMockMvc.given().when().get("/power-aggregate").then().log().ifValidationFails()
				.statusCode(HttpStatus.OK.value());
	}

}
