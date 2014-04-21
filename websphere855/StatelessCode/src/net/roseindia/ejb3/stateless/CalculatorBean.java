package com.javajazzup.examples.ejb3.stateless;

import java.math.*;
import javax.ejb.Stateless;
import javax.ejb.Remote;  

@Stateless(name="CalculatorBean")
@Remote(CalculatorRemote.class)  
public class CalculatorBean implements CalculatorRemote{
	public float add(float x, float y){	 
		return x + y;
   }

   public float subtract(float x, float y){
      return x - y;
   }
    public float multiply(float x, float y){
      return x * y;
   }
   public float division(float x, float y){
	   return x / y;
   }
}
