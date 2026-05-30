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

import com.example.demo.dao.CategoryRepository;
import com.example.demo.entity.CourseCategories;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.CategoryServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest 
{
	@Mock
	private CategoryRepository categoryRepo;
	
	@InjectMocks
	private CategoryServiceImpl categoryService;
	
	private CourseCategories category;
	
	@BeforeEach
	void setUp()
	{
		category = new CourseCategories();
		category.setId(1L);
		category.setName("Backend");
	}
	
	@Test
	void findAll_Success()
	{
		when(categoryRepo.findAll()).thenReturn(List.of(category));
		
		List<CourseCategories> result = categoryService.findAll();
		
		assertEquals(1,result.size());
		assertEquals(1L, result.get(0).getId());
		assertEquals("Backend",result.get(0).getName());
	}
	
	@Test
	void findById_Success()
	{
		when(categoryRepo.findById(1L)).thenReturn(Optional.of(category));
		
		CourseCategories result = categoryService.findById(1L);
		
		assertNotNull(result);
		assertEquals(1L,result.getId());
		assertEquals("Backend",result.getName());
	}
	
	@Test
	void findById_notSuccess()
	{
		when(categoryRepo.findById(10L)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> {
			categoryService.findById(10L);
		});
	}
	
	@Test
	void findByName_Success()
	{
        when(categoryRepo.findByName("Backend")).thenReturn(Optional.of(category));
		
		CourseCategories result = categoryService.findByName("Backend");
		
		assertNotNull(result);
		assertEquals(1L,result.getId());
		assertEquals("Backend",result.getName());
		
	}
	
	@Test
	void findByName_notSuccess()
	{
        when(categoryRepo.findByName("Backends")).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> {
			categoryService.findByName("Backends");
		});
	}
	
	@Test
	void save_Success()
	{
		when(categoryRepo.findByName("Backend")).thenReturn(Optional.empty());
	       
		categoryService.save(category);
		
		verify(categoryRepo, times(1)).save(category);
		
	}
	
	@Test
	void save_notSuccess()
	{
        when(categoryRepo.findByName("Backend")).thenReturn(Optional.of(category));
		
		assertThrows(DuplicateResourceException.class, () -> {
			categoryService.save(category);
		});
		
		verify(categoryRepo, never()).save(category);
	}
	
	@Test
	void update_Success()
	{
        when(categoryRepo.findById(1L)).thenReturn(Optional.of(category));
		when(categoryRepo.save(category)).thenReturn(category);
        
		CourseCategories result = categoryService.update(1L, category);
		
		assertNotNull(result);
		assertEquals(1L,result.getId());
		assertEquals("Backend",result.getName());
		
		verify(categoryRepo, times(1)).save(result);
		
	}
	
	@Test
	void update_notSuccess()
	{
        when(categoryRepo.findById(10L)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> {
			categoryService.update(10L, category);
		});
	}
	
	@Test
	void DeleteById_Success()
	{
		when(categoryRepo.findById(1L)).thenReturn(Optional.of(category));
		
		categoryService.deleteById(1L);
		
		verify(categoryRepo, times(1)).deleteById(1L);
		
	}
	
	@Test
	void DeleteById_notSuccess()
	{
        when(categoryRepo.findById(10L)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> {
			categoryService.deleteById(10L);
		});
		
		verify(categoryRepo, never()).deleteById(10L);
	}

}
