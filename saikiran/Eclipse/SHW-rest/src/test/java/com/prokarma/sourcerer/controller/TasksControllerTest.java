/**
 * 
 */
package com.prokarma.sourcerer.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.prokarma.sourcerer.dto.Subtechnology;
import com.prokarma.sourcerer.dto.Technology;
import com.prokarma.sourcerer.service.TasksService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:application-context.xml"})

public class TasksControllerTest {

	private MockMvc mockMvc;
	@InjectMocks
	private TasksController tasksController=new TasksController() ;
	
	@Mock
	private TasksService tasksService;
	
	
	@Before
	public void setup() {
	MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.tasksController).build();
	}
	
	@Test
	public void testGetTechnoligies_withStatusOk() throws Exception {
	List<String> technologies= new ArrayList<String>();
	technologies.add("java");
	when(tasksService.getTechnologies()).thenReturn(technologies);

	MvcResult result = mockMvc.perform(get("/superadmin/getTechnnologies")).andExpect(status().isOk()).andReturn();

	
	   String responseTechnologies=result.getResponse().getContentAsString();
		
		assertEquals("["+"\"java"+"\"]", responseTechnologies);
	}
	
	@Test
	@Ignore
	public void testGetsubTechnologies_withStatusOk() throws Exception {
		List<Subtechnology> subtechnologies = new ArrayList<Subtechnology>();
		Subtechnology firstSubtechnology = new Subtechnology();
		firstSubtechnology.setId(1);
		firstSubtechnology.setTechnology("java");
		firstSubtechnology.setSubTechnology("oops");
		subtechnologies.add(firstSubtechnology);
		
		when(tasksService.getSubtechnologies()).thenReturn(subtechnologies);
		MvcResult result= mockMvc.perform(get("/superadmin/getSubtechnnologies")).andExpect(status().isOk()).andReturn();
		String  responseSubtechnologies=result.getResponse().getContentAsString();
		
		assertEquals("[{\"technology\":\"java\",\"id\":1,\"subTechnology\":\"oops\"}]", responseSubtechnologies);
		
	}
	
	@Test
	public void testAddTechnology_withStatusOk() throws Exception {
		Technology technology= new Technology();
		technology.setName("java");
		String inputJson="{\"name\":\"java\"}";
		when(tasksService.addTechnology((Technology) anyObject())).thenReturn(true);
		 mockMvc.perform(post("/superadmin/addTechnology")
		    		.contentType(MediaType.APPLICATION_JSON).content(inputJson).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void testAddTechnology_withStatusBadRequest() throws Exception {
		Technology technology= new Technology();
		technology.setName("java");
		String inputJson="{\"name\":\"java\"}";
		when(tasksService.addTechnology((Technology) anyObject())).thenReturn(false);
		 mockMvc.perform(post("/superadmin/addTechnology")
		    		.contentType(MediaType.APPLICATION_JSON).content(inputJson).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}
	
	@Test
	public void testAddSubtechnology_withStatusOk() throws Exception {
		String subTechnologyJson="{\"technology\":\"java\",\"id\":1,\"subtechnology\":\"oops\"}";
	
		when(tasksService.addsubtechnology((Subtechnology) anyObject())).thenReturn(true);
		 mockMvc.perform(post("/superadmin/addSubtechnology").contentType(MediaType.APPLICATION_JSON).content(subTechnologyJson)
		    		.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void testAddSubtechnology_withStatusError() throws Exception {
		String subTechnologyJson="{\"technology\":\"java\",\"id\":1,\"subtechnology\":\"oops\"}";
	
		when(tasksService.addsubtechnology((Subtechnology) anyObject())).thenReturn(false);
		 mockMvc.perform(post("/superadmin/addSubtechnology").contentType(MediaType.APPLICATION_JSON).content(subTechnologyJson)
		    		.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}
	
	@Test
	public void testEditSubtechnology_withStatusOk() throws Exception {
		String subTechnologyJson="{\"technology\":\"java\",\"id\":1,\"subtechnology\":\"oops\"}";
	
		when(tasksService.editSubtechnology((Subtechnology) anyObject())).thenReturn(true);
		 mockMvc.perform(post("/superadmin/editSubtechnology").contentType(MediaType.APPLICATION_JSON).content(subTechnologyJson)
		    		.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void testEditSubtechnology_withStatusError() throws Exception {
		String subTechnologyJson="{\"technology\":\"java\",\"id\":1,\"subtechnology\":\"oops\"}";
	
		when(tasksService.editSubtechnology((Subtechnology) anyObject())).thenReturn(false);
		 mockMvc.perform(post("/superadmin/editSubtechnology").contentType(MediaType.APPLICATION_JSON).content(subTechnologyJson)
		    		.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	
	@Test
	public void testDeleteSubtechnology_withStatusOk() throws Exception {
		
        when(tasksService.deleteSubtechnology(3)).thenReturn(true);
        
	    mockMvc.perform(get("/superadmin/deleteSubtechnology/{id}",3)).andExpect(status().isOk());
	    
	}

	@Test
	public void testDeleteSubtechnology_withStatusError() throws Exception {
		
        when(tasksService.deleteSubtechnology(3)).thenReturn(false);
        
	    mockMvc.perform(get("/superadmin/deleteSubtechnology/{id}",3)).andExpect(status().isNotFound());
	    
	}
}