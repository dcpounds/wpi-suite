/*******************************************************************************
 * Copyright (c) 2014 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Team What? We Thought This Was Bio!
 *******************************************************************************/
package tests;

public class ExampleClass {
	
	
   private long long1;
   private long long2;

   //Constructor
   //@param numbers to be added
   public ExampleClass(long num1, long num2){
      long1 = num1;
      long2 = num2;
   }
      
   // prints the message
   public long addNumbers(){
      return long1+long2;
   }

}