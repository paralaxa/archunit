package sk.stopangin.archunit.to;

import java.util.UUID;
import lombok.Data;

@Data
public class UserDto {

  private UUID id;
  private String name;
}
