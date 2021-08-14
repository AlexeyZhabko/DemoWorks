package by.academy.jc.Zabrodski.ht2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DirtySupomenTest {
  DirtySumopen sum = new DirtySumopen();

  @Test
  public void shouldReturnDirtySumInt() {
    assertEquals(23, sum.result(5, 3));
  }

  @Test
  public void shouldReturnDirtySumLong() {
    assertEquals(13L, sum.result(6L, 1L));
  }

  @Test
  public void shouldReturnDirtySumDouble() {
    assertEquals(15.79, sum.result(6.3, 1.3));
  }

  @Test
  public void shouldReturnDirtySumFloat() {
    assertEquals(24.9F, sum.result(2.5F, 6.4F));
  }
}

