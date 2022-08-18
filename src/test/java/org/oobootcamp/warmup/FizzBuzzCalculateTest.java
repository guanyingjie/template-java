package org.oobootcamp.warmup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzCalculateTest {
  private final FizzBuzzCalculate fizzBuzzCalculate = new FizzBuzzCalculate();

  @Test
  void should_return_Fizz_when_call_calculateReturnValue_given_input_is_3() {
    assertEquals("Fizz", fizzBuzzCalculate.calculateReturnValue(3));
  }

  @Test
  void should_return_Buzz_when_call_calculateReturnValue_given_input_is_5() {
    assertEquals("Buzz", fizzBuzzCalculate.calculateReturnValue(5));
  }

  @Test
  void should_return_FizzBuzz_when_call_calculateReturnValue_given_input_is_15() {
    assertEquals("FizzBuzz", fizzBuzzCalculate.calculateReturnValue(15));
  }

  @Test
  void should_return_1_when_call_calculateReturnValue_given_input_is_1() {
    assertEquals("1", fizzBuzzCalculate.calculateReturnValue(1));
  }

//  @Test
//  void should_throw_exception_when_call_calculateReturnValue_given_input_is_0() {
//    fizzBuzzCalculate.calculateReturnValue(0);
//    assertThrows(InputValidationException.class, () -> fizzBuzzCalculate.calculateReturnValue(0));
//  }

}