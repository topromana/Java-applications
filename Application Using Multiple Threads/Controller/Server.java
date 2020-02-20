package Controller;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import Model.Task;
import View.SimulationFrame;

import java.util.concurrent.BlockingQueue;

public class Server implements Runnable {
	private BlockingQueue<Task> tasks;
	private AtomicInteger waitingPeriod;
	private SimulationFrame simulationFrame;
	int serverId;

	public Server(int serverId, SimulationFrame frame) {
		tasks = new LinkedBlockingQueue<Task>();
		waitingPeriod = new AtomicInteger();
		simulationFrame = frame;
		this.serverId = serverId;
	}

	public void addTask(Task newTask) {
		tasks.add(newTask);
		waitingPeriod.addAndGet(newTask.getProcessingTime());
	}

	public void run() {
		System.out.println("server no." + serverId + "running");
		Task auxTask;
		while (true) {
			auxTask = tasks.peek();
			if (auxTask != null) {
				try {
					String message = "Task " + auxTask.toStringEntry() + " added at queue " + serverId + ". Start processing\n";
					simulationFrame.setLogOfEvents(message);
					Thread.sleep(auxTask.getProcessingTime() * 1000);
					message = "Finnished processing " + auxTask.toStringExit() + "\n";
					simulationFrame.setLogOfEvents(message);
					Task currentTask = tasks.take();
					waitingPeriod.addAndGet(-1 * currentTask.getProcessingTime());
					message = simulationFrame.getQueuesTextField(serverId-1).getText();
					int pos = message.indexOf("/");
					message = message.substring(pos+1);
					simulationFrame.getQueuesTextField(serverId-1).setText(message);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public BlockingQueue<Task> getTask() {
		return tasks;
	}

	public void setTasks(BlockingQueue<Task> tasks) {
		this.tasks = tasks;
	}

	public Task[] getTasks() {
		Task[] tasksArray = new Task[tasks.size()];
		tasks.toArray(tasksArray);
		return tasksArray;
	}

	public void setWaitingPeriod(AtomicInteger waitingPeriod) {
		this.waitingPeriod = waitingPeriod;
	}

	public int getWaitingPeriod() {
		return waitingPeriod.get();
	}

	public String toString() {
		String msg = new String();
		for (Task task : tasks) {
			msg += task.getId() + " ";
		}
		return msg;
	}
}
