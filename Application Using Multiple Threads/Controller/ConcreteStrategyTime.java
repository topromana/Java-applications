package Controller;

import java.util.List;

import Model.Task;

public class ConcreteStrategyTime implements Strategy{
	@Override
	public void addTask(List<Server> servers, Task t) {
		// TODO Auto-generated method stub
		int minTime = 100;
		Server minServer = servers.get(0);
		for (Server server : servers) {
			if (server.getWaitingPeriod() < minTime) {
				minTime = server.getWaitingPeriod();
				minServer = server;
			}
		}

		minServer.addTask(t);
		System.out.println("task that arrived at " + t.getArrivalTime() + "added on the server with  waiting period " + minServer.getWaitingPeriod());

	}

}
