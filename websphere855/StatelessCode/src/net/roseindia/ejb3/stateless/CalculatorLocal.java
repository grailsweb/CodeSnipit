package com.javajazzup.examples.ejb3.stateless;
import java.math.*;
import javax.ejb.Local;
import java.lang.annotation.*;
@Local
public interface CalculatorLocal {
 public float add(float x, float y);
 public float subtract(float x, float y);
 public float multiply(float x, float y);
 public float division(float x, float y);
}
