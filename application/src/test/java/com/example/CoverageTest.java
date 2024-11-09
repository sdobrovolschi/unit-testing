package com.example;

import org.junit.jupiter.api.Test;

import static com.example.Coverage.intValue;
import static org.assertj.core.api.Assertions.assertThat;

class CoverageTest {

    @Test
    void assertionFreeTesting() {
        intValue("2");
    }

    @Test
    void parsing() {
        //what about the code paths that Integer.parseInt takes?
        assertThat(intValue("2"))
                .isExactlyInstanceOf(Integer.class);
    }
}
