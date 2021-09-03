package week13;

public class ProcessInfo {
	int processID;
	int duration;
	int allocateAddress;
	int endingTime;
	
	public ProcessInfo(int processNum, int currentTime) {
		processID = processNum;
		duration = getDuration();
		allocateAddress = -1;
		endingTime = currentTime + duration;
	}
	
	private int getDuration() {
		return (int)(Math.random()*100 + 1);
	}
	
	public String toString() {
		return "ID:"+processID+"--Duration="+duration+"\nAddress="+allocateAddress;
	}
}
