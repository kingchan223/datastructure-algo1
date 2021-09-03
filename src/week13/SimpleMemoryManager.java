package week13;

import java.util.ArrayList;

public class SimpleMemoryManager {
	int maxMemorySize = 1000;
	int memorySlotSize = 50;
	int [] memory;
	
	int freePointer;
	
	public SimpleMemoryManager(int maxTime) {
		memory = new int [maxMemorySize];
		freePointer = 0;
		memory[0] =-1; //prev
		memory[1] = freePointer + memorySlotSize; //next
		int p = memorySlotSize;
		while(p<maxMemorySize) {
			memory[p] = p-memorySlotSize; //prev
			memory[p+1] = p + memorySlotSize; //next
			p += memorySlotSize;
		}
		p = p-memorySlotSize;
		memory[p+1] = -1;//last slot
	}
	
	public int malloc() {
		if(freePointer>=0) {
			int p = getAFreeSlot();
			System.out.println(" *** memory allocated : "+p+" FreeHead "+freePointer);
			
			return p;
			
		}else {
			System.out.println(" >>> Memory run out...");
			return -1;
		}
	}

	private int getAFreeSlot() {
		int p = freePointer;
		freePointer = memory[freePointer+1];
		if(freePointer>0)
			memory[freePointer] = -1;
		
		return p;
	}
	
	public void free(int p) {
		System.out.println(" +++ memory freed : "+p);
		if(freePointer==-1) {
			memory[p] =-1;
			memory[p+1]=-1;
		}else {
			memory[p]=-1;
			memory[freePointer] = p;
			memory[p+1]=freePointer;
		}
		freePointer = p;
	}

	public static void main(String[] args) {
		int timeLimit = 1000;
		SimpleMemoryManager smm = new SimpleMemoryManager(timeLimit);
		ArrayList<ProcessInfo> processTable = new ArrayList<>();
		
		int clock = 0;
		int processNum = 0;
		
		while(clock<timeLimit) {
			System.out.println("\n -- clock = "+clock);
			
			if(Math.random() < 0.5) {
				ProcessInfo p = new ProcessInfo(processNum++,clock);
				p.allocateAddress = smm.malloc();
				if(p.allocateAddress<0) 
					System.out.print(">>> Process Creation Failed");
				else
					processTable.add(p);
			}
			for(int i=0; i<processTable.size(); i++) {
				if(processTable.get(i).endingTime<=clock) {
					smm.free(processTable.get(i).allocateAddress);
					processTable.remove(i);
				}
			}
			clock++;
		}

	}

}
