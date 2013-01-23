package com.dorandoran.infixtopostfix;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import com.dorandoran.infixtopostfix.InfixToPostfix;

public class InfixToPostfixTest {

	@Test
	public void testToPostfix_one_operand() {
		InfixToPostfix itp = new InfixToPostfix();
								
		assertEquals("1", itp.toPostfix("1"));
	}
	
	@Test
	public void testToPostfix_two_operands_with_plus() {
		InfixToPostfix itp = new InfixToPostfix();

		assertEquals("1 1 +", itp.toPostfix("1 + 1"));
	}

	@Test
	public void testToPostfix_two_operands_with_multiply() {
		InfixToPostfix itp = new InfixToPostfix();

		assertEquals("1 1 *", itp.toPostfix("1 * 1"));
	}
	
	@Test
	public void testToPostfix_3_operands_with_plus() {
		InfixToPostfix itp = new InfixToPostfix();

		assertEquals("1 1 1 + +", itp.toPostfix("1 + 1 + 1"));
	}
	
	@Test
	public void testToPostfix_3_operands_with_plus_and_multiply() {
		InfixToPostfix itp = new InfixToPostfix();

		assertEquals("1 1 1 * +", itp.toPostfix("1 + 1 * 1"));
	}
	
	@Test
	public void testToPostfix_3_operands_with_multiply_and_plus() {
		InfixToPostfix itp = new InfixToPostfix();

		assertEquals("1 1 * 1 +", itp.toPostfix("1 * 1 + 1"));
	}
	
	@Ignore
	public void testToPostfix_with_parentheses() {
		InfixToPostfix itp = new InfixToPostfix();

		assertEquals("1", itp.toPostfix("(1)"));
	}
}
