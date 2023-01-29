package sk.stopangin.archunit.service;

import java.util.List;
import java.util.UUID;
import sk.stopangin.archunit.to.UserCreateDto;
import sk.stopangin.archunit.to.UserDto;

public interface UserService {


  UserDto get(UUID id);

  List<UserDto> getAll();

  UserDto save(UserCreateDto user);
}
