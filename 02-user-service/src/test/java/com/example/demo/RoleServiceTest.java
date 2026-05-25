package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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

import com.example.demo.dao.RoleRepository;
import com.example.demo.entity.Roles;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.RoleServiceImpl;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest 
{
	@Mock
	private RoleRepository roleRepo;
	
	@InjectMocks
	private RoleServiceImpl roleService;
	
	private Roles role;
	
	@BeforeEach
	void setUp()
	{
		role = new Roles();
		role.setId(1L);
		role.setRoleName("Admin");
	}
	
	@Test
	void findAll_Success()
	{
		when(roleRepo.findAll()).thenReturn(List.of(role));
		List<Roles> result = roleService.findAll();
		
		assertEquals(1, result.size());
		assertEquals("Admin",result.get(0).getRoleName());
	}
	
	@Test
	void findById_Success()
	{
		when(roleRepo.findById(1L)).thenReturn(Optional.of(role));
		
		Roles result = roleService.findById(1L);
		
		assertNotNull(result);
		assertEquals(1L,result.getId());
		assertEquals("Admin",result.getRoleName());
	}
	
	@Test
	void findById_NotSuccess()
	{
		when(roleRepo.findById(10L)).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class,() -> {
			roleService.findById(10L);
		});
	}
	
	@Test
	void saveOrUpdate_Success()
	{
		roleService.saveOrUpdate(role);
		
		verify(roleRepo,times(1)).save(role);
	}
	
	@Test
	void deleteById_Success()
	{
		when(roleRepo.findById(1L)).thenReturn(Optional.of(role));
		
		roleService.deleteById(1L);
		verify(roleRepo,times(1)).deleteById(1L);
	}
	
	@Test
	void deleteById_NotSuccess()
	{
		when(roleRepo.findById(10L)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class,() -> {
			roleService.deleteById(10L);
		});
		
		verify(roleRepo,never()).deleteById(10L);
	}

}
