package org.apache.maven.archetypes;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class AppTest {
    @Test
    public void testMavenRunsJUnit() {
        System.out.println("JUnit test is running!");
        assertTrue(true);
    }
//
//    @Test public void testNegative() {
//        System.out.println("This test should fail!");
//        fail();
//    }
}