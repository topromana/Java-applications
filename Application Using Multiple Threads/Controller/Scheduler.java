package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.Task;
import View.SimulationFrame;

public class Scheduler {

	private List<Server> servers;
	private int maxNoServers;
	private Strategy strategy;
	
	public Scheduler(int maxNoServers, SimulationFrame frame) {
		this.maxNoServers = maxNoServers;
		servers = new ArrayList<Server>();
		for (int i = 1; i <= maxNoServers; i++) {
			Server server = new Server(i,frame);
			servers.add(server);
			Thread thread = new Thread(server);
			thread.start();
		}
	}
	
	public void changeStrategy(SelectionPolicy policy) {
		if (policy == SelectionPolicy.SHORTEST_QUEUE) {
			strategy = new ConcreteStrategyQueue();
		}
		if (policy == SelectionPolicy.SHORTEST_TIME) {
			strategy = new ConcreteStrategyTime();
		}
	}
	
	public void dispatchTask(Task t) {
		strategy.addTask(servers, t);
	}
	public List<Server> getServers() {
		return servers;
	}

	public int getMaxNoServers() {
		return maxNoServers;
	}
	public Task[] getTasksForServer(int id){
		return servers.get(id).getTasks();
	}

	public void setMaxNoServers(int maxNoServers) {
		this.maxNoServers = maxNoServers;
	}

}
