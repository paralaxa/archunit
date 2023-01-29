package sk.stopangin.archunit;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

class ArchitectureLayersTests {

  private final JavaClasses classes = new ClassFileImporter().importPackages(
      "sk.stopangin.archunit");

  @Test
  public void layerCheck() {
    layeredArchitecture()
        .consideringAllDependencies()
        .layer("Resource").definedBy("sk.stopangin.archunit..resource..")
        .layer("Service").definedBy("sk.stopangin.archunit..service..")
        .layer("Persistence").definedBy("sk.stopangin.archunit..repository..")

        .whereLayer("Resource").mayNotBeAccessedByAnyLayer()
        .whereLayer("Service").mayOnlyBeAccessedByLayers("Resource")
        .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service")
        .check(classes);

  }


}
