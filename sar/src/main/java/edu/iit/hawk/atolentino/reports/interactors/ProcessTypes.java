package edu.iit.hawk.atolentino.reports.interactors;

public enum ProcessTypes {
	PROCESS907(907), 
	PROCESS911(911);
	
	private final int pid;
	
	private ProcessTypes(int pid) {
		this.pid = pid;
	}
	
	public int getPid() {
		return pid;
	}
}
