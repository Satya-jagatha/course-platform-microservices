package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dao.ModuleRepository;
import com.example.demo.entity.CourseModules;
import com.example.demo.entity.Courses;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.ModuleServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ModuleServiceImplTest 
{
	@Mock
	private ModuleRepository moduleRepo;
	
	@InjectMocks
	private ModuleServiceImpl moduleService;
	
	private CourseModules module;
	
	private Courses course;
	
	@BeforeEach
	void  setup()
	{
		course = new Courses();
		course.setId(1L);
		course.setDescription("Spring Boot");
		course.setTitle("Backend");
				
		module = new CourseModules();
		module.setId(1L);
		module.setDurationMinutes(10);
		module.setModuleOrder(1);
		module.setTitle("Java");
		module.setVideoUrl("test");
		module.setCourse(course);
		
	}
	
	@Test
	void findAll_Success()
	{
		when(moduleRepo.findAll()).thenReturn(List.of(module));
		
		List<CourseModules> result = moduleService.findAll();
		
		assertEquals(1, result.size());
		assertEquals(1L, result.get(0).getId());
	}
	
	@Test
	void findById_Success()
	{
		when(moduleRepo.findById(1L)).thenReturn(Optional.of(module));
		
		CourseModules result = moduleService.findById(1L);
		
		assertNotNull(result);
		assertEquals(1L,result.getId());
		assertEquals(10,result.getDurationMinutes());
		assertEquals(1,result.getModuleOrder());
		assertEquals("Java",result.getTitle());
	}
	
	@Test
	void findByCourseId_Success()
	{
		when(moduleRepo.findByCourse_Id(1L)).thenReturn(List.of(module));
		
		List<CourseModules> result = moduleService.findByCourseId(1L);
		
		assertEquals(1, result.size());
		assertEquals(1L, result.get(0).getId());
		assertEquals("Java", result.get(0).getTitle());
		
		
	}
	
	@Test
	void findById_notSuccess()
	{
		when(moduleRepo.findById(10L)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> {
			moduleService.findById(10L);
		});
	}
	
	@Test
	void saveOrUpdate_Success()
	{
		moduleService.saveOrUpdate(module);
		
		verify(moduleRepo,times(1)).save(module);
		
	}
	
	@Test
	void DeleteById_Success()
	{
		when(moduleRepo.findById(1L)).thenReturn(Optional.of(module));
		
		moduleService.deleteById(1L);
		
		verify(moduleRepo,times(1)).deleteById(1L);
	}
	
	@Test
	void DeleteById_notSuccess()
	{
        when(moduleRepo.findById(10L)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> {
			moduleService.findById(10L);
		});
		
		verify(moduleRepo, never()).deleteById(10L);
	}

}
