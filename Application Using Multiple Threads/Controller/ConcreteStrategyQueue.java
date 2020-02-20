package Controller;

import java.util.List;

import Model.Task;

public class ConcreteStrategyQueue implements Strategy {
@Override
	public void addTask(List<Server> servers, Task t) {
		// TODO Auto-generated method stub
		int minTasks = 100;
		for (Server server : servers) {
			if (server.getTask().size() < minTasks) {
				minTasks = server.getTask().size();
			}   
		}

		for (Server server : servers) {
			if (server.getTask().size() == minTasks) {
				server.addTask(t);
				t.setQueueNr(server.serverId);
				break;
			}
		}
	}

}
