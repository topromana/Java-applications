package Model;

public class Task {
	private int id;
	private int queueNr;
	private int arrivalTime;
	private int finishTime;
	private int processingTime;
	public Task(int id, int arrivalTime, int processingTime) {
		this.id = id;
		this.arrivalTime = arrivalTime;
		this.processingTime = processingTime;
		this.finishTime = 0;
		this.queueNr = 0;
	}
	
	public int getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public void setQueueNr(int queueNr) {
		this.queueNr = queueNr;
	}
	public int getQueueNr() {
		return this.queueNr;
	}
	public int getProcessingTime() {
		return processingTime;
	}
	public void setProcessingTime(int processingTime) {
		this.processingTime = processingTime;
	}
	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}
	public int getWaitingTime() {
		return finishTime-arrivalTime-processingTime;
	}
	public int getId() {
		return id;
	}
	public String toStringEntry() {
		String msg = "Task "+ this.id+ " has ENTERED with arrival: " + Integer.toString(this.arrivalTime)+" and processing: "+ Integer.toString(this.processingTime);
		return msg;
	}
	
	public String toStringExit() {
		String msg = "Task "+ this.id+ " has EXITED " ;
		return msg;
	}
}
