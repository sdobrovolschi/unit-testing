package com.example.naming;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class NamingTests {

    @Nested
    class Project {

        //bad name
        @Test
        void createProjectShouldThrowIllegalArgumentExceptionWhenNameIsMissing() {

        }

        //good name
        @Test
        void cannotCreateProjectWithoutName() {

        }

        @Nested
        class Creation {

            //best name
            @Test
            void nameIsRequired() {

            }
        }
    }
}
