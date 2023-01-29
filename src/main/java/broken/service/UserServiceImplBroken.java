package broken.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sk.stopangin.archunit.model.User;
import sk.stopangin.archunit.repository.UserRepository;
import sk.stopangin.archunit.to.UserCreateDto;
import sk.stopangin.archunit.to.UserDto;

@Component
@RequiredArgsConstructor
public class UserServiceImplBroken {

  private final UserRepository repository;

  public User get(UUID id) {
    return null;
  }

  public List<UserDto> getAll() {
    return repository.findAll().stream().map(this::dtoFromUser).collect(Collectors.toList());
  }

  private UserDto dtoFromUser(User user) {

    UserDto userDto = new UserDto();
    userDto.setId(user.getId());
    userDto.setName(user.getName());
    return userDto;
  }

  public User save(UserCreateDto user) {
    return null;
  }
}
