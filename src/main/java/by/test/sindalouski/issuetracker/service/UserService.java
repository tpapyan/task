package by.test.sindalouski.issuetracker.service;

import by.test.sindalouski.issuetracker.dto.RegistrationDto;
import by.test.sindalouski.issuetracker.dto.UserDto;
import by.test.sindalouski.issuetracker.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
	void register(RegistrationDto registrationDto);

	List<User> listUsers();

	void addUser(UserDto userDto) throws IOException;

	void remove(Integer id);

	void updateUser(UserDto userDto);

}
