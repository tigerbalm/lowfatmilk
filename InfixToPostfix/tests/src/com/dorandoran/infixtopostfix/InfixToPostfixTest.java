package com.dorandoran.infixtopostfix;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InfixToPostfixTest {

	@Test
	public void testToPostfix_one_operand() {
		InfixToPostfix itp = new InfixToPostfix();
								
		assertEquals("1", itp.toPostfix2("1"));
	}
	
	@Test
	public void testToPostfix_two_operands_with_plus() {
		InfixToPostfix itp = new InfixToPostfix();

		assertEquals("1 1 +", itp.toPostfix2("1 + 1"));
	}

	@Test
	public void testToPostfix_two_operands_with_multiply() {
		InfixToPostfix itp = new InfixToPostfix();

		assertEquals("1 1 *", itp.toPostfix2("1 * 1"));
	}
	
	@Test
	public void testToPostfix_3_operands_with_plus() {
		InfixToPostfix itp = new InfixToPostfix();

		assertEquals("1 1 + 1 +", itp.toPostfix2("1 + 1 + 1"));
	}
	
	@Test
	public void testToPostfix_3_operands_with_plus_and_multiply() {
		InfixToPostfix itp = new InfixToPostfix();

		assertEquals("1 1 1 * +", itp.toPostfix2("1 + 1 * 1"));
	}
	
	@Test
	public void testToPostfix_3_operands_with_multiply_and_plus() {
		InfixToPostfix itp = new InfixToPostfix();

		assertEquals("1 1 * 1 +", itp.toPostfix2("1 * 1 + 1"));
	}
	
	@Test
	public void testToPostfix_with_parentheses() {
		InfixToPostfix itp = new InfixToPostfix();

		assertEquals("1", itp.toPostfix2("(1)"));
	}
	
	@Test
	public void testToPostfix_complex_expression() {
		InfixToPostfix itp = new InfixToPostfix();

		assertEquals("3 4 5 * 6 / +", itp.toPostfix2("3+4*5/6"));
	}
	
	@Test
	public void testToPostfix_complex_expression2() {
		InfixToPostfix itp = new InfixToPostfix();

		assertEquals("300 23 + 43 21 - * 84 7 + /", 
				itp.toPostfix2("(300+23)*(43-21)/(84+7)"));
	}
	
	@Test
	public void testToPostfix_complex_expression3() {
		InfixToPostfix itp = new InfixToPostfix();

		assertEquals("4 8 + 6 5 - * 3 2 - 2 2 + * /", 
				itp.toPostfix2("(4+8)*(6-5)/((3-2)*(2+2))"));
	}	
}
