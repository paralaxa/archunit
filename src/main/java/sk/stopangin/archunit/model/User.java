package sk.stopangin.archunit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.Data;

@Data
@Entity(name = "myUser")
public class User {

  @Id
  private UUID id;
  private String name;
}
