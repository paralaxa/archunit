package sk.stopangin.archunit.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.stopangin.archunit.model.User;
import sk.stopangin.archunit.repository.UserRepository;
import sk.stopangin.archunit.to.UserCreateDto;
import sk.stopangin.archunit.to.UserDto;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  @Override
  public UserDto get(UUID id) {
    return dtoFromUser(repository.getReferenceById(id));
  }

  @Override
  public List<UserDto> getAll() {
    return repository.findAll().stream().map(this::dtoFromUser).toList();
  }

  private UserDto dtoFromUser(User user) {

    UserDto userDto = new UserDto();
    userDto.setId(user.getId());
    userDto.setName(user.getName());
    return userDto;
  }

  @Override
  public UserDto save(UserCreateDto user) {
    User userEntity = new User();
    userEntity.setId(UUID.randomUUID());
    userEntity.setName(user.getName());
    return dtoFromUser(repository.save(userEntity));
  }
}
