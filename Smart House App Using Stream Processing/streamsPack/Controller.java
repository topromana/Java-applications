package streamsPack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class Controller {

	private ArrayList<MonitoredData> monitoredData;
	public Controller() {
		monitoredData = new ArrayList<MonitoredData>(); 
	}
	
	public ArrayList<MonitoredData> getMonitoredData() {
		return monitoredData;
	}

	public void setMonitoredData(ArrayList<MonitoredData> monitoredData) {
		this.monitoredData = monitoredData;
	}

	public void readData() {
		String file = "F:\\PT\\Stream\\activities.txt";
		try (Stream<String> stream = Files.lines(Paths.get(file))) {
			stream.forEach(line -> {
				org.joda.time.format.DateTimeFormatter inputFormat = DateTimeFormat.forPattern("YYYY-MM-dd' 'HH:mm:ss");
				String[] fields = line.split("\t\t");
				monitoredData.add(new MonitoredData(inputFormat.parseDateTime(fields[0]),
						inputFormat.parseDateTime(fields[1]), fields[2]));
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int countDays() {
		int nrOfDays = 0;
		DateTime prevEntry = new DateTime(monitoredData.get(0).getStart_time());
		for(MonitoredData d:getMonitoredData()) {
			if(!(d.getStart_time().equals(prevEntry)))
				nrOfDays++;
		prevEntry = d.getStart_time();
		}
			
		return nrOfDays;
	}
	public Map<String,Long> activitiesAllPeriod() throws IOException{
		Map<String,Long> appearancesList = new HashMap<String,Long>();
		appearancesList = monitoredData.stream().collect(Collectors.groupingBy(MonitoredData::getActivity_label, Collectors.counting()));
		File file1 = new File("countActivities.txt");
        FileOutputStream os = new FileOutputStream(file1);
        OutputStreamWriter osWr = new OutputStreamWriter(os);    
        BufferedWriter wr = new BufferedWriter(osWr);
        for(String s : appearancesList.keySet()){
        	wr.write(s + " " + appearancesList.get(s) + " \n");
        	wr.newLine();
        }
        wr.close();
        return appearancesList;
	}
	public Map<Integer, Map<String,Long>> activitiesPerDay() throws IOException{
		Map<Integer,	Map<String,Long>> appearancesList = new HashMap<Integer,Map<String,Long>>();
		appearancesList = monitoredData.stream().collect(Collectors.groupingBy(MonitoredData::getDay,Collectors.groupingBy(MonitoredData::getActivity_label,Collectors.counting())));
		File file2 = new File("activitiesPerDay.txt");
        FileOutputStream os = new FileOutputStream(file2);
        OutputStreamWriter osWr = new OutputStreamWriter(os);    
        BufferedWriter wr = new BufferedWriter(osWr);
        for(Integer s : appearancesList.keySet()){
        	wr.write(s.toString() + " " + appearancesList.get(s) + " \n");
        	wr.newLine();
        }
        wr.close();
	    return appearancesList;
	}
	public void duration() throws IOException {
		Map<String, Long> labelAndDuration = new HashMap<String, Long>();
		labelAndDuration = monitoredData.stream().collect(Collectors.groupingBy(MonitoredData::getActivity_label, Collectors.summingLong(MonitoredData::getDuration)));
		File file3 = new File("durationForEach.txt");
        FileOutputStream os = new FileOutputStream(file3);
        OutputStreamWriter osWr = new OutputStreamWriter(os);    
        BufferedWriter wr = new BufferedWriter(osWr);
		for(MonitoredData m: monitoredData) {
			
			wr.write(m.getActivity_label() + " with duration " + m.getDuration());
			wr.newLine();}
			
		
		
		wr.close();
	}
	
	
	public void lessThanFiveMinutes() throws IOException {
		Map<String, Long> m1 = new HashMap<String, Long>();
		
		m1 = monitoredData.stream().filter(d -> (d.getDuration() / (1000 * 60)) < 5).collect(Collectors.groupingBy(MonitoredData::getActivity_label, Collectors.counting()));
		List<String> list = m1.keySet().stream().filter(d -> {Map<String, Long> m2 = new HashMap<String, Long>();
		m2 = monitoredData.stream().collect(Collectors.groupingBy(MonitoredData::getActivity_label, Collectors.counting()));
		Map<String, Long> m3 = new HashMap<String, Long>();
		m3 = monitoredData.stream().filter(a -> (a.getDuration() / (1000 * 60)) < 5).collect(Collectors.groupingBy(MonitoredData::getActivity_label, Collectors.counting()));
		if((float) m3.get(d) / m2.get(d) > (float) 9/10) return true;
			
		return false;
		}).collect(Collectors.toList());
		
		File f4 = new File("lessThanFiveMins.txt");
        FileOutputStream os = new FileOutputStream(f4);
        OutputStreamWriter osWr = new OutputStreamWriter(os);    
        BufferedWriter w5 = new BufferedWriter(osWr);
		
        for(String s : list){
    		w5.write(s + " ");
    		w5.newLine();
        }
		
		w5.close();
	}
	
}
