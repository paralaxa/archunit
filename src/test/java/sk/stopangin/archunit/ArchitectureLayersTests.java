package sk.stopangin.archunit;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

class ArchitectureLayersTests {


  /**
   * some test 2
   */
  private final JavaClasses classes = new ClassFileImporter().importPackages(
      "sk.stopangin.archunit");

  @Test
  public void layerCheck() {
    layeredArchitecture()
        .consideringAllDependencies()
        .layer("Resource").definedBy("..resource..")
        .layer("Service").definedBy("..service..")
        .layer("Persistence").definedBy("..repository..")

        .whereLayer("Resource").mayNotBeAccessedByAnyLayer()
        .whereLayer("Service").mayOnlyBeAccessedByLayers("Resource")
        .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service")
        .check(classes);

  }


}
