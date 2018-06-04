package com.weekend.assignment5.maintask;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MainTaskBOTest {
	@InjectMocks
	MainTaskBO Bo;
	
	@Mock
	MainTaskDao mainTaskDao;

	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void isDataInsertedIntomainTaskDb() throws IOException, SQLException{
		MainTask mainTask = new MainTask("kajsb", "ansvb", 2,"saikiran", 32, "main-task-head");
		when(mainTaskDao.saveMainTask(mainTask)).thenReturn(new Integer(1));
		boolean result = Bo.insertMainTaskDetails(mainTask);
		Assert.assertEquals(true, result);
	}
	@Test
	public void isDataUpdatedInMainTaskTable() throws ClassNotFoundException, IOException, SQLException {
		when(mainTaskDao.updateMainTask(7, "abcd-task","task")).thenReturn(new Integer(1));
		boolean result = Bo.updateMainTaskDetails(7,"abcd-task","task");
		Assert.assertEquals(true, result);
	}
}
