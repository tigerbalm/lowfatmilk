package com.dorandoran.infixtopostfix;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OperatorTest {
	@Test
	public void testHasHigherOperatorThan() {
		testAddOperator("+");
		testAddOperator("-");
		
		testMultiplyOperator("*");
		testMultiplyOperator("/");
	}

	private void testAddOperator(String op) {
		assertFalse(new Operator(op).isHigherPriorityThan(new Operator("+")));
		assertFalse(new Operator(op).isHigherPriorityThan(new Operator("-")));		
		assertFalse(new Operator(op).isHigherPriorityThan(new Operator("*")));
		assertFalse(new Operator(op).isHigherPriorityThan(new Operator("/")));
	}
	
	private void testMultiplyOperator(String op) {
		assertTrue(new Operator(op).isHigherPriorityThan(new Operator("+")));
		assertTrue(new Operator(op).isHigherPriorityThan(new Operator("-")));		
		assertFalse(new Operator(op).isHigherPriorityThan(new Operator("*")));
		assertFalse(new Operator(op).isHigherPriorityThan(new Operator("/")));
	}
}
