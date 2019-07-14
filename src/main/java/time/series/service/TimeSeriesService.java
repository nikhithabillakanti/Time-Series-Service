package time.series.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import time.series.converter.TimeSeriesConverter;
import time.series.dto.TimeSeriesDTO;
import time.series.dto.WeeklyTimeSeriesDTO;
import time.series.dto.WeeklyTimeSeriesPerMeterDTO;
import time.series.model.TimeSeries;

@Component
public class TimeSeriesService {
	private static List<TimeSeries> timeSeriesList=new ArrayList<>();
	
	@Autowired
	private TimeSeriesConverter timeSeriesConverter;
	
	public void addTimeSeries(TimeSeriesDTO request) {
		timeSeriesList.add(timeSeriesConverter.convert(request));
	}
	
	public List<TimeSeriesDTO> getTimeSeriesByDay(LocalDate day){
		return timeSeriesList.stream().filter(ts->ts.getFrom().toLocalDateTime().toLocalDate().equals(day)).map(timeSeriesConverter::convert).collect(Collectors.toList());
	}
	
	public List<TimeSeriesDTO> getTimeSeriesByWeek(int week){
		return timeSeriesList.stream().filter(ts->ts.getWeek()==week).map(timeSeriesConverter::convert).collect(Collectors.toList());
	}

	public List<WeeklyTimeSeriesDTO> getAllTotalTimeSeries(String customerId) {
		Map<Integer, List<TimeSeries>> filter = timeSeriesList.stream().filter(ts->ts.getCustomerId().equals(customerId)).collect(Collectors.groupingBy(TimeSeries::getWeek));
		List<Integer> sortedWeekList = filter.keySet().stream().collect(Collectors.toList());
		return sortedWeekList.stream().map(week->{
		WeeklyTimeSeriesDTO weeklyTimeSeriesDTO=new WeeklyTimeSeriesDTO();
		weeklyTimeSeriesDTO.setWeek(week);
		weeklyTimeSeriesDTO.setTotal(filter.get(week).stream().mapToDouble(ts->ts.getTotal().doubleValue()).sum());
		return weeklyTimeSeriesDTO;
		}).collect(Collectors.toList());
	}
	
	public List<WeeklyTimeSeriesPerMeterDTO> getTotalTimeSeries(String customerId) {
		Map<Integer, List<TimeSeries>> filter = timeSeriesList.stream().filter(ts->ts.getCustomerId().equals(customerId)).collect(Collectors.groupingBy(TimeSeries::getWeek));
		List<Integer> sortedWeekList = filter.keySet().stream().collect(Collectors.toList());
		return sortedWeekList.stream().flatMap(week->{
			Map<String , List<TimeSeries>> filterByMeter = filter.get(week).stream().collect(Collectors.groupingBy(TimeSeries::getMeterId));
		
			return filterByMeter.keySet().stream().map(meter->{
				WeeklyTimeSeriesPerMeterDTO weeklyTimeSeriesDTO=new WeeklyTimeSeriesPerMeterDTO();
				weeklyTimeSeriesDTO.setWeek(week);
				weeklyTimeSeriesDTO.setMeterId(meter);
				weeklyTimeSeriesDTO.setTotal(filterByMeter.get(meter).stream().mapToDouble(ts->ts.getTotal().doubleValue()).sum());
				return weeklyTimeSeriesDTO;
			});
			
		}).collect(Collectors.toList());
	}
	
}
