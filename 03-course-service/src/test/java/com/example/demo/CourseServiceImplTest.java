package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.example.demo.dao.CourseRepository;
import com.example.demo.entity.CourseCategories;
import com.example.demo.entity.Courses;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.CourseServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CourseServiceImplTest 
{
	@Mock
	private CourseRepository courseRepo;
	
	@InjectMocks
	private CourseServiceImpl courseService;
	
	private Courses course;
	
	private CourseCategories category;
	
	@BeforeEach
	void setUp()
	{
		category = new CourseCategories();
		category.setId(1L);
		category.setName("Backend");
		
		course = new Courses();
		course.setId(1L);
		course.setCategory(category);
		course.setDescription("Test");
		course.setDurationHours(10);
		course.setLevel("begin");
		course.setTitle("Java");
		course.setActive(true);
	}
	
	@Test
	void findAll_Success()
	{
		when(courseRepo.findAll()).thenReturn(List.of(course));
		
		List<Courses> result = courseService.findAll();
		
		assertEquals(1,result.size());
		assertEquals(1L,result.get(0).getId());
		assertEquals("Test",result.get(0).getDescription());
		assertEquals(10,result.get(0).getDurationHours());
		assertEquals("begin",result.get(0).getLevel());
		assertEquals("Java",result.get(0).getTitle());
	}
	
	@Test
	void findByCategoryId_Success()
	{
		when(courseRepo.findByCategoryId(1L)).thenReturn(List.of(course));
		
        List<Courses> result = courseService.findByCategoryId(1L);
		
		assertEquals(1,result.size());
		assertEquals(1L,result.get(0).getId());
		assertEquals("Test",result.get(0).getDescription());
		assertEquals(10,result.get(0).getDurationHours());
		assertEquals("begin",result.get(0).getLevel());
		assertEquals("Java",result.get(0).getTitle());
	}
	
	@Test
	void findByCategoryName_Success()
	{
		when(courseRepo.findByCategory_Name("Backend")).thenReturn(List.of(course));
		
        List<Courses> result = courseService.findByCategoryName("Backend");
		
		assertEquals(1,result.size());
		assertEquals(1L,result.get(0).getId());
		assertEquals("Test",result.get(0).getDescription());
		assertEquals(10,result.get(0).getDurationHours());
		assertEquals("begin",result.get(0).getLevel());
		assertEquals("Java",result.get(0).getTitle());
	}
	
	@Test
	void findByActive()
	{
		when(courseRepo.findByActive(true)).thenReturn(List.of(course));
		
		List<Courses> result = courseService.findByActive(true);
		
		assertEquals(1,result.size());
		assertEquals(1L,result.get(0).getId());
		assertEquals("Test",result.get(0).getDescription());
		assertEquals(10,result.get(0).getDurationHours());
		assertEquals("begin",result.get(0).getLevel());
		assertEquals("Java",result.get(0).getTitle());
	}
	
	@Test
	void findByLevel()
	{
        when(courseRepo.findByLevel("begin")).thenReturn(List.of(course));
		
		List<Courses> result = courseService.findByLevel("begin");
		
		assertEquals(1,result.size());
		assertEquals(1L,result.get(0).getId());
		assertEquals("Test",result.get(0).getDescription());
		assertEquals(10,result.get(0).getDurationHours());
		assertEquals("begin",result.get(0).getLevel());
		assertEquals("Java",result.get(0).getTitle());
	}
	
	
	@Test
	void findById_Success()
	{
		when(courseRepo.findById(1L)).thenReturn(Optional.of(course));
		
		Courses result = courseService.findById(1L);
		
		assertEquals(1L,result.getId());
		assertEquals("Test",result.getDescription());
		assertEquals(10,result.getDurationHours());
		assertEquals("begin",result.getLevel());
		assertEquals("Java",result.getTitle());
	}
	
	@Test
	void findById_notSuccess()
	{
		when(courseRepo.findById(10L)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> {
			courseService.findById(10L);
		});
	}
	
	@Test
	void findByTitle_Success()
	{
		when(courseRepo.findByTitle("Java")).thenReturn(Optional.of(course));
		
        Courses result = courseService.findByTitle("Java");
		
		assertEquals(1L,result.getId());
		assertEquals("Test",result.getDescription());
		assertEquals(10,result.getDurationHours());
		assertEquals("begin",result.getLevel());
		assertEquals("Java",result.getTitle());
		
	}
	
	@Test
	void findByTitle_notSuccess()
	{
        when(courseRepo.findByTitle("Javas")).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> {
			courseService.findByTitle("Javas");
		});
	}
	
	@Test
	void save_Success()
	{
		courseService.save(course);
		
		verify(courseRepo,times(1)).save(course);
	}
	
	@Test
	void update_Success()
	{
		when(courseRepo.findById(1L)).thenReturn(Optional.of(course));
		when(courseRepo.save(course)).thenReturn(course);
		
		Courses result = courseService.update(1L, course);
		
		assertEquals(1L,result.getId());
		assertEquals("Test",result.getDescription());
		assertEquals(10,result.getDurationHours());
		assertEquals("begin",result.getLevel());
		assertEquals("Java",result.getTitle());
		
		verify(courseRepo, times(1)).save(course);
		
	}
	
	@Test
	void update_notSuccess()
	{
        when(courseRepo.findById(10L)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> {
			courseService.update(10L, course);
		});
		
		verify(courseRepo, never()).save(course);
		
	}
	
	@Test
	void deleteById_Success()
	{
		when(courseRepo.findById(1L)).thenReturn(Optional.of(course));
		
		courseService.deleteById(1L);
		
		verify(courseRepo,times(1)).deleteById(1L);
	}
	
	@Test
	void deleteById_notSuccess()
	{
        when(courseRepo.findById(10L)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> {
			courseService.deleteById(10L);
		});
		
		verify(courseRepo, never()).deleteById(10L);
	}
	

}
