package com.example.worthless;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class WorthlessTests {

    //Anemic domain objects are tested implicitly by use case unit tests.
    //Anemic domain model increases complexity in use cases due to single responsibility violation
    //which impacts protection against regression and maintenance of unit tests.
    //The best practice is to foster a rich domain model.
    @Nested
    class AnemicDomainModel {

        @Test
        void creation() {
            var someValue = "someValue";
            var someOtherValue = "someOtherValue";

            var anemicDomainObject = new AnemicDomainType(someValue, someOtherValue);

            assertThat(anemicDomainObject)
                    .extracting(AnemicDomainType::getSomeField, AnemicDomainType::getSomeOtherField)
                    .containsExactly(someValue, someOtherValue);
        }

        @Test
        void changingSomeField() {
            var anemicDomainObject = new AnemicDomainType("someValue", "someOtherValue");
            var someNewValue = "someNewValue";

            anemicDomainObject.setSomeField(someNewValue);

            assertThat(anemicDomainObject)
                    .extracting(AnemicDomainType::getSomeField)
                    .isEqualTo(someNewValue);
        }

        static class AnemicDomainType {

            String someField;
            String someOtherField;

            AnemicDomainType(String someField, String someOtherField) {
                this.someField = someField;
                this.someOtherField = someOtherField;
            }

            String getSomeField() {
                return someField;
            }

            void setSomeField(String someField) {
                this.someField = someField;
            }

            String getSomeOtherField() {
                return someOtherField;
            }

            void setSomeOtherField(String someOtherField) {
                this.someOtherField = someOtherField;
            }
        }
    }

    //Verifying interactions that doesn't cross the application boundary is a sign of coupling the tests to implementation details
    //which leads to test fragility, thus impacts the resistance to refactoring.
    //Mocks should be used to verify interactions that cross the application boundary. In hexagonal architecture these are the secondary ports
    @Nested
    class IntraApplicationCollaboration {

        @Test
        void changingAggregate() {
            var entity = mock(Entity.class);
            var aggregate = new Aggregate(entity);

            aggregate.doSomething();

            verify(entity).doSomething();
            verifyNoMoreInteractions(entity);
        }

        @AllArgsConstructor
        class Aggregate {

            Entity entity;

            void doSomething() {
                entity.doSomething();
            }
        }

        @Getter
        class Entity {
            String field;

            void doSomething() {

            }
        }
    }

    // Conversion is an implementation detail and not an observable behaviour
    // It should be:
    // - unit tested as part of a use case e.g. a projection of a domain object is returned
    // - integration tested as part of a repository adapter e.g. a domain object is created from records
    // - integration tested as part of a resource adapter e.g. a request is adapted to the use case signature
    @Nested
    class Conversion {

        @Test
        void conversion() {
            var converter = new RecordToEntityConversion();
            var value = "value";
            var record = new Record(value);

            var entity = converter.convert(record);

            assertThat(entity)
                    .usingRecursiveComparison()
                    .isEqualTo(new Entity(value));
        }

        class RecordToEntityConversion {

            Entity convert(Record record) {
                return new Entity(record.field);
            }
        }

        @AllArgsConstructor
        class Record {
            String field;
        }

        @AllArgsConstructor
        class Entity {
            String attribute;
        }
    }
}
