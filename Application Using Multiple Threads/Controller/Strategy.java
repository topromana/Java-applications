package Controller;

import java.util.List;

import Model.Task;

public interface Strategy {
	public void addTask(List<Server> servers,Task t);
}
