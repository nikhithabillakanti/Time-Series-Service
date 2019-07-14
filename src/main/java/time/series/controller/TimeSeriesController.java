package time.series.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import time.series.dto.TimeSeriesDTO;
import time.series.dto.WeeklyTimeSeriesDTO;
import time.series.dto.WeeklyTimeSeriesPerMeterDTO;
import time.series.service.TimeSeriesService;

@RestController
public class TimeSeriesController {

	@Autowired
	private TimeSeriesService timeSeriesService;

	@PutMapping("/timeseries")
	public void addTimeSeries(@Valid @RequestBody TimeSeriesDTO request) {
		timeSeriesService.addTimeSeries(request);

	}

	@RequestMapping("/timeseries")
	public List<TimeSeriesDTO> getTimeSeriesList(@RequestParam(required = false, name="day") String dayStr,
			@RequestParam(required = false) Integer week) {
		LocalDate day = null;
		if (dayStr != null) {
			try {
				day = LocalDate.parse(dayStr);
			} catch (DateTimeParseException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong day format", null);
			}
		}

		if (day == null && week == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Search criteria found", null);
		}
		if (day != null && week != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't search by day and week in the same time.",
					null);
		}

		return day != null ? timeSeriesService.getTimeSeriesByDay(day) : timeSeriesService.getTimeSeriesByWeek(week);
	}
	
	@RequestMapping("/timeseries/customer/{customerId}/all")
	public List<WeeklyTimeSeriesDTO> getAllTotalTimeSeries(@PathVariable("customerId") String customerId) {
		return timeSeriesService.getAllTotalTimeSeries(customerId);
	}
	
	@RequestMapping("/timeseries/customer/{customerId}")
	public List<WeeklyTimeSeriesPerMeterDTO> getTotalTimeSeries(@PathVariable("customerId") String customerId) {
		return timeSeriesService.getTotalTimeSeries(customerId);
	}
}