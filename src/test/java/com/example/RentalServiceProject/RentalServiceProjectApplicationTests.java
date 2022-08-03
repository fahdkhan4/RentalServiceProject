package com.example.RentalServiceProject;


import com.example.RentalServiceProject.config.exception.ContentNotFoundException;
import com.example.RentalServiceProject.model.Asset;
import com.example.RentalServiceProject.model.AssetReview;
import com.example.RentalServiceProject.model.User;
import com.example.RentalServiceProject.model.enums.InitialStatus;
import com.example.RentalServiceProject.repo.AssetRepository;
import com.example.RentalServiceProject.repo.UserRepository;

import com.example.RentalServiceProject.service.UserService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class RentalServiceProjectApplicationTests {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void getUserTestTest(){
		List<User> userdetails = new ArrayList<>();
		User user1 = new User(1l,"fahd","123","aa","fahdkhan@gmail.com","seller","222", InitialStatus.in_review);
		User user2 = new User(2l,"ali","123","aa","alikhan@gmail.com","seller","111", InitialStatus.in_review);
		userdetails.add(user1);
		userdetails.add(user2);
		when(userRepository.findByStatus(InitialStatus.Published)).thenReturn(Stream.of(user1,user2).collect(Collectors.toList()));
		assertEquals(2,userService.getUsersbyStatus().size());
	}

	@Test
	public void getUsersTest(){

//		User user1 = new User(1l,"fahd","123","fahdkhan@gmail.com","seller","222", InitialStatus.in_review);
//		User user2 = new User(2l,"ali","123","alikhan@gmail.com","seller","111", InitialStatus.in_review);
//		when(userRepository.findByStatus(InitialStatus.Published)).thenReturn(Stream.of(user1,user2).collect(Collectors.toList()));
//		when(userRepository.findByStatus(InitialStatus.in_review)).thenReturn(Stream.of(user1,user2).collect(Collectors.toList()));
////		No user found error should be generated
//		assertEquals(2,userService.getUsersbyStatus().size());

	}

	@Test
	public void getUserByIdTest(){

//		User user2 = new User(2l,"ali","123","alikhan@gmail.com","seller","111", InitialStatus.in_review);
//		Optional<User> user = Optional.of(user2);
//		when(userRepository.findById(2l)).thenReturn(Stream.of(user).findAny().get());
//		assertEquals(user,userService.getUserById(2l));
//		verify(userRepository).findById(2l);

	}


}
