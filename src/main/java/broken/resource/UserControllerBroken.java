package broken.resource;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.stopangin.archunit.model.User;
import sk.stopangin.archunit.repository.UserRepository;

@RestController
@Transactional
@RequestMapping("userb")
@RequiredArgsConstructor
public class UserControllerBroken {

  private final UserRepository userRepository;

  @GetMapping("{id}")
  public User get(@PathVariable("id") UUID id) {
    return userRepository.getReferenceById(id);
  }

  @GetMapping
  public List<User> getAll() {
    return userRepository.findAll();
  }


  @PostMapping
  public User save(User user) {
    return userRepository.save(user);
  }

}
