package edu.iit.hawk.atolentino.reports;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.iit.hawk.atolentino.reports.boundaries.ShareARideProcess;
import edu.iit.hawk.atolentino.reports.interactors.Process907;
import edu.iit.hawk.atolentino.reports.interactors.Process911;
import edu.iit.hawk.atolentino.reports.interactors.ProcessFactory;
import edu.iit.hawk.atolentino.reports.interactors.ProcessTypes;
import edu.iit.hawk.atolentino.ride_management.boundaries.RideUtilities;

public class ProcessFactoryTest {
	private ProcessFactory processFactory;
	
	@BeforeEach
	void init() {
		List<RideUtilities> rides = new ArrayList<RideUtilities>();
		processFactory = new ProcessFactory("", "", rides.iterator());
	}
	
	@Test
	void test_buildProcess_givenPid907_shouldReturnProcess907() {
		ShareARideProcess process = processFactory.buildProcess(Process907.PID);
		assertTrue(process instanceof Process907);
	}
	
	@Test
	void test_buildProcess_givenPid911_shouldReturnProcess911() {
		ShareARideProcess process = processFactory.buildProcess(Process911.PID);
		assertTrue(process instanceof Process911);
	}
	
	@Test
	void test_buildProcess_givenNonExistingPidNumber_shouldThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> {
			processFactory.buildProcess(-1);
		});
	}
	
	@Test
	void test_getProcessName_givenProcessTypeProcess907_shouldReturnProcess907Name() {
		String name = ProcessFactory.getProcessName(ProcessTypes.PROCESS907);
		assertEquals(Process907.NAME, name);
	}
	
	@Test
	void test_getProcessName_givenProcessTypeProcess911_shouldReturnProcess911Name() {
		String name = ProcessFactory.getProcessName(ProcessTypes.PROCESS911);
		assertEquals(Process911.NAME, name);
	}
}
