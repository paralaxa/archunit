package sk.stopangin.archunit.resource;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.stopangin.archunit.service.UserService;
import sk.stopangin.archunit.to.UserCreateDto;
import sk.stopangin.archunit.to.UserDto;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("{id}")
  public UserDto get(@PathVariable("id") UUID id) {
    return userService.get(id);
  }

  @GetMapping
  public List<UserDto> getAll() {
    return userService.getAll();
  }


  @PostMapping
  public UserDto save(UserCreateDto user) {
    return userService.save(user);
  }

}
