package primer.intento.time.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class HexagonalArchitectureTest {

    @Test
    void application_should_not_depend_on_infrastructure() {
        JavaClasses classes = new ClassFileImporter().importPackages("primer.intento.time");

        ArchRule rule = noClasses()
                .that().resideInAPackage("..application..")
                .should().dependOnClassesThat().resideInAnyPackage("..infraestructure..", "org.springframework.data..");

        rule.check(classes);
    }
}