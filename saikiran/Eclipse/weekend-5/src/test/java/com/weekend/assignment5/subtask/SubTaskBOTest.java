package com.weekend.assignment5.subtask;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SubTaskBOTest {
	@InjectMocks
	SubTaskBO Bo;
	
	@Mock
	SubTaskDao subTaskDao;

	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void isDataInsertedIntoSubTaskDb() throws IOException, SQLException{
		SubTask subTask = new SubTask("abcd", "abcasjcka", 25, "asjcna ckja dbv", "saikiran", 23);
		when(subTaskDao.saveSubTask(subTask)).thenReturn(new Integer(1));
		boolean result = Bo.insertSubTaskDetails(subTask);
		Assert.assertEquals(true,result);
	}
	@Test
	public void isDataUpdatedInSubTaskDb() throws ClassNotFoundException, IOException, SQLException {
		when(subTaskDao.updateSubTaskDetailsInDb(10, "abcd", "aksjbf fakjb", 30)).thenReturn(new Integer(1));
		boolean result = Bo.updateSubTaskDetails(10, "abcd", "aksjbf fakjb", 30);
		Assert.assertEquals(true,result);
	}
}
