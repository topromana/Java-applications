package streamsPack;

import java.time.LocalDateTime;

import org.joda.time.DateTime;


public class MonitoredData {
	private DateTime start_time;
	private DateTime end_time;
	private String activity_label;
	public MonitoredData(DateTime start_time, DateTime end_time, String activity_label) {
		super();
		this.start_time = start_time;
		this.end_time = end_time;
		this.activity_label = activity_label;
	}
	public DateTime getStart_time() {
		return start_time;
	}
	public void setStart_time(DateTime start_time) {
		this.start_time = start_time;
	}
	public DateTime getEnd_time() {
		return end_time;
	}
	public void setEnd_time(DateTime end_time) {
		this.end_time = end_time;
	}
	public String getActivity_label() {
		return activity_label;
	}
	public void setActivity_label(String activity_label) {
		this.activity_label = activity_label;
	}
	public int getDay() {
		return start_time.getDayOfMonth();
	}
	
	
	public int getDuration(){
		return  (end_time).getMinuteOfDay()-(start_time).getMinuteOfDay();
	}
	
	
}
