package sk.stopangin.archunit;

import static com.tngtech.archunit.core.domain.JavaModifier.PUBLIC;
import static com.tngtech.archunit.core.domain.properties.HasModifiers.Predicates.modifier;
import static com.tngtech.archunit.lang.Priority.MEDIUM;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.priority;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaMember;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.PackageMatcher;
import com.tngtech.archunit.core.domain.properties.HasModifiers;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.AbstractClassesTransformer;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ClassesTransformer;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import jakarta.persistence.Entity;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@AnalyzeClasses(packages = "sk.stopangin.archunit")
class ArchitectureRulesTests {

  @ArchTest
  public static final ArchRule resourcesShouldBeNamedController = classes().that()
      .resideInAPackage("sk.stopangin.archunit..resource..")
      .should().haveSimpleNameEndingWith("Controller")
      .because("Resource's name should end with Controller");


  @ArchTest
  public static final ArchRule resourcesShouldNotBeTransactional = classes().that()
      .resideInAPackage("sk.stopangin.archunit..resource..")
      .should().notBeAnnotatedWith(Transactional.class)
      .because("Resources should not be transactional");

  @ArchTest
  public static final ArchRule resourcesShouldBeAnnotatedRestController = classes().that()
      .resideInAPackage("sk.stopangin.archunit..resource..")
      .should().beAnnotatedWith(RestController.class)
      .because("Resources should be annotated with RestController annotation");

  @ArchTest
  public static final ArchRule servicesShouldBeTransactional = classes()
      .that().resideInAPackage("sk.stopangin.archunit..service..").and().areNotInterfaces()
      .should().beAnnotatedWith(Transactional.class)
      .because("Services should be transactional");


  @ArchTest
  public static final ArchRule servicesShouldUseServiceStereotype = classes()
      .that().resideInAPackage("sk.stopangin.archunit..service..").and().areNotInterfaces()
      .should().beAnnotatedWith(Service.class)
      .because("Services should use @Service stereotype annotation");

  @ArchTest
  public static ArchRule serviceShouldNotReturnEntity = priority(
      MEDIUM)
      .no(methods().that(areInPackage(PackageMatcher.of("sk.stopangin.archunit..service.."))))
      .that(arePublic())
      .should(beAnnotatedWith(Entity.class))
      .because("Service should not return entity, rather return DTO");

  static ClassesTransformer<JavaMethod> methods() {
    return new AbstractClassesTransformer<JavaMethod>("methods") {
      @Override
      public Iterable<JavaMethod> doTransform(JavaClasses javaClasses) {
        List<JavaMethod> methods = new ArrayList<>();
        for (JavaClass javaClass : javaClasses) {
          methods.addAll(javaClass.getMethods());
        }
        return methods;
      }
    };
  }

  static DescribedPredicate<JavaMember> areInPackage(PackageMatcher packageMatcher) {
    return new DescribedPredicate<JavaMember>("are in " + packageMatcher) {
      @Override
      public boolean test(JavaMember member) {
        return packageMatcher.matches(member.getOwner().getPackage().getName());
      }
    };
  }

  static DescribedPredicate<HasModifiers> arePublic() {
    return modifier(PUBLIC).as("are public");
  }

  static ArchCondition<JavaMethod> returnType(Class<?> type) {
    return new ArchCondition<JavaMethod>("return type " + type.getName()) {
      @Override
      public void check(JavaMethod method, ConditionEvents events) {
        boolean typeMatches = method.getReturnType().toErasure().isAssignableTo(type);
        String message = method.getFullName() + " returns " + method.getReturnType().getName() + " "
            + "(" + method.getOwner() + ":" + 1 + ")";
        events.add(new SimpleConditionEvent(method, typeMatches, message));
      }
    };
  }

  static ArchCondition<JavaMethod> beAnnotatedWith(Class<? extends Annotation> type) {
    return new ArchCondition<JavaMethod>("annotation " + type.getName()) {
      @Override
      public void check(JavaMethod method, ConditionEvents events) {
        boolean typeMatches = method.getReturnType().toErasure().isAnnotatedWith(type);
        String message = method.getFullName() + " returns " + method.getReturnType().getName() + " "
            + "(" + method.getOwner() + ":" + 1 + ")";
        events.add(new SimpleConditionEvent(method, typeMatches, message));
      }
    };
  }
}
