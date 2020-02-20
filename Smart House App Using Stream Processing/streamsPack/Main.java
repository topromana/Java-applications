package streamsPack;

import java.io.IOException;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Controller c = new Controller();
		c.readData();
		for(MonitoredData d:c.getMonitoredData())
			System.out.println(d.getActivity_label()+" "+ d.getStart_time()+"  "+ d.getEnd_time() );
		int nrOfDays = c.countDays();
		System.out.println("nr of days "+nrOfDays);
		Map<String,Long> appearancesList = c.activitiesAllPeriod();
		Map<Integer,Map<String,Long>> appearancesListPerDay = c.activitiesPerDay();
		c.duration();
		c.lessThanFiveMinutes();
	}

}
