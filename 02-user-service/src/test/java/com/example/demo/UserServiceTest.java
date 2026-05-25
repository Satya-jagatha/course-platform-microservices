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

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.Users;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest 
{
	@Mock
	private UserRepository userRepo;
	
	@InjectMocks
	private UserServiceImpl userService;
	
	
	private Users user;
	
	@BeforeEach
	void setUp()
	{
		user = new Users();
		user.setId(1L);
		user.setName("Satya");
		user.setEmail("satya@gmail.com");
		user.setPassword("12345");
		user.setRoleId(1L);
	}
	
	@Test
	void findAll_Success()
	{
		when(userRepo.findAll()).thenReturn(List.of(user));
		
		List<Users> result = userService.findAll();
		
		assertEquals(1,result.size());
		assertEquals("Satya",result.get(0).getName());
		assertEquals("satya@gmail.com",result.get(0).getEmail());
		assertEquals("12345",result.get(0).getPassword());
	}
	
	@Test
	void findById_Success()
	{
		when(userRepo.findById(1L)).thenReturn(Optional.of(user));
		
		Users result = userService.findById(1L);
		
		assertNotNull(result);
		assertEquals(1L,result.getId());
		assertEquals("Satya",result.getName());
		assertEquals("satya@gmail.com",result.getEmail());
	}
	
	@Test
	void findById_NotSuccess()
	{
		when(userRepo.findById(10L)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> {
			userService.findById(10L);
		});
	}
	
	@Test
	void findByEmail_Success()
	{
		when(userRepo.findByEmail("satya@gmail.com")).thenReturn(Optional.of(user));
		
		Users result = userService.findByEmail("satya@gmail.com");
		
		assertNotNull(result);
		assertEquals(1L,result.getId());
		assertEquals("Satya",result.getName());
		assertEquals("satya@gmail.com",result.getEmail());
	}
	
	@Test
	void findByEmail_NotSuccess()
	{
		when(userRepo.findByEmail("siva@gmail.com")).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class,() -> {
			userService.findByEmail("siva@gmail.com");
		});
	}
	
	@Test
	void deleteById_Success()
	{
		when(userRepo.findById(1L)).thenReturn(Optional.of(user));
		
		userService.deleteById(1L);
		
		verify(userRepo, times(1)).deleteById(1L);
	}
	
	@Test
	void deleteById_notSuccess()
	{
		when(userRepo.findById(10L)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> {
			userService.deleteById(10L);
		});
		
		verify(userRepo,never()).deleteById(10L);
	}
	
	@Test
	void save_Success()
	{
		when(userRepo.findByEmail("satya@gmail.com")).thenReturn(Optional.empty());
		
		userService.save(user);
		
		verify(userRepo, times(1)).save(user);
	}
	
	@Test
	void save_notSuccess()
	{
		when(userRepo.findByEmail("satya@gmail.com")).thenReturn(Optional.of(user));
		
		assertThrows(DuplicateResourceException.class, () -> {
			userService.save(user);
		});
		
		verify(userRepo, never()).save(user);
	}
	
	@Test
	void update_Success()
	{
		when(userRepo.findById(1L)).thenReturn(Optional.of(user));
		when(userRepo.save(user)).thenReturn(user);
		
		Users result = userService.update(1L, user);
		
		assertNotNull(result);
		assertEquals(1L,result.getId());
		assertEquals("Satya",result.getName());
		assertEquals("satya@gmail.com",result.getEmail());
		
		verify(userRepo, times(1)).save(user);
		
	}
	
	@Test
	void update_notSuccess()
	{
		when(userRepo.findById(10L)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () ->{
			userService.update(10L, user);
		});
	}

}
