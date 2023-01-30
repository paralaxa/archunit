package broken.service;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MyUser {

  @Id
  private String id;
}
