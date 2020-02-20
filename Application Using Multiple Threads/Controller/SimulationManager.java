package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import Model.Task;
import View.SimulationFrame;

public class SimulationManager implements Runnable {

	public int timeLimit;
	public int minArrivalTime;
	public int maxArrivalTime;
	public int maxProcessingTime;
	public int minProcessingTime;
	public int numberOfServers;

	public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_QUEUE;
	
	private Scheduler scheduler;
	private SimulationFrame frame;
	private List<Task> generatedTasks;
	private AtomicInteger currentTime = new AtomicInteger();
	public SimulationManager(SimulationFrame frame) {
		this.frame = frame;
		this.timeLimit = this.frame.getSimulationInterval();
		this.maxArrivalTime = this.frame.getMaxArrivalTime();
		this.minArrivalTime = this.frame.getMinArrivalTime();
		this.maxProcessingTime = this.frame.getMaxProcessingTime();
		this.minProcessingTime = this.frame.getMinProcessingTime();
		this.numberOfServers = this.frame.getNrQueues();
		this.generatedTasks = new ArrayList<Task>();
		scheduler = new Scheduler(numberOfServers,frame);
		scheduler.changeStrategy(selectionPolicy);
		//generateNRandomTasks();
		
	}
	

	
	@Override
	public void run() {
		int id = 0;
		Random random = new Random();
		while (currentTime.intValue() < timeLimit) {
			ArrayList<Task> toBeRemoved= new ArrayList<Task>() ;
			//for (Task t : generatedTasks) {
			int arrivalTime = 0;
			while(arrivalTime< this.timeLimit) {
				
				int processingTime = random.nextInt((maxProcessingTime - minProcessingTime) + 1) + minProcessingTime;
				arrivalTime+= random.nextInt((maxArrivalTime - minArrivalTime)+1) +minArrivalTime;
				//Task t = new Task(id,arrivalTime, processingTime);
				//System.out.println("id: "+ t.getId());
				
		
				if (arrivalTime == currentTime.intValue()) {
					id++;
					Task t = new Task(id,arrivalTime, processingTime);
					scheduler.dispatchTask(t);
					String msg = frame.getQueuesTextField(t.getQueueNr()-1).getText();
					msg += "client"+t.getId()+"/";
					frame.getQueuesTextField(t.getQueueNr()-1).setText(msg);
					toBeRemoved.add(t);				
				}
			}
			//}
			generatedTasks.removeAll(toBeRemoved);
			currentTime.getAndIncrement();
			try {
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		
		}
	}

//	public static void main(String[] args) {
//		SimulationFrame	frame = new SimulationFrame();
//		SimulationManager gen = new SimulationManager(frame);
//		Thread t = new Thread(gen);
//		t.start();
//	}


}
