/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package by.academy.jc.sergeichik;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {
    Solution jumps = new Solution();
    @Test
    void shouldReturnSolutionTest_0() {
        assertEquals(3, jumps.solution1(10, 85, 30), "The small frog will jumps 3 times");
    }
    @Test
    void shouldReturnSolutionTest_1() {
        assertEquals(2, jumps.solution1(0, 1000000000, 999999999), "The small frog will jumps 2 times");
    }
    @Test
    void shouldReturnSolutionTest_2() {
        assertEquals(1000000, jumps.solution1(1000, 1000000000, 1000), "The small frog will jumps 1000000 times");
    }
    @Test
    void shouldReturnSolutionTest_3() {
        assertEquals(0, jumps.solution1(1000000000, 986565467, 50), "The small frog will jumps 0 times");
    }
    @Test
    void shouldReturnSolutionTest_4() {
        assertEquals(9, jumps.solution1(0, 8, 1), "The small frog will jumps 9 times");
    }
    @Test
    void shouldReturnSolutionTest_5() {
        assertEquals(3, jumps.solution1(15, 195, 80), "The small frog will jumps 3 times");
    }
}
