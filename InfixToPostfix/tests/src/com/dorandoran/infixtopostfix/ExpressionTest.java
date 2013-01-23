package com.dorandoran.infixtopostfix;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

import org.junit.Test;

public class ExpressionTest {

	@Test
	public void testShouldParsePolynomialExpression() {
		Expression exp = new Expression();
		assertTrue(exp.parse("1 + 2"));
		
		assertEquals(3, exp.getTermsInOrder().size());
	}
	
	@Test
	public void testShouldParsePolynomialExpressionWith3Operands() {
		Expression exp = new Expression();
		assertTrue(exp.parse("1 + 2 + 3"));
		
		assertEquals(5, exp.getTermsInOrder().size());
	}
	
	@Test
	public void testShouldParsePolynomialExpressionWithAddAndMultiplyOperator() {
		Expression exp = new Expression();
		assertTrue(exp.parse("1 + 2 * 3"));
		
		ArrayList<Term> terms = exp.getTermsInOrder(); 
		
		assertEquals(5, terms.size());
		assertEquals("1", terms.get(0).toString());
		assertEquals("+", terms.get(1).toString());
		assertEquals("2", terms.get(2).toString());
		assertEquals("*", terms.get(3).toString());
		assertEquals("3", terms.get(4).toString());
	}
	
	@Test
	public void testShouldParsePolynomialExpressionWithParenthesis() {
		Expression exp = new Expression();
		assertTrue(exp.parse("(1 + 2) * 3"));
		
		ArrayList<Term> terms = exp.getTermsInOrder(); 
		
		assertEquals(7, terms.size());
		assertEquals("(", terms.get(0).toString());		
		assertEquals("1", terms.get(1).toString());
		assertEquals("+", terms.get(2).toString());
		assertEquals("2", terms.get(3).toString());
		assertEquals(")", terms.get(4).toString());
		assertEquals("*", terms.get(5).toString());
		assertEquals("3", terms.get(6).toString());
	}
	
	@Test
	public void testShouldParsePolynomialExpressionWithParenthesisInDifferentOrder() {
		Expression exp = new Expression();
		assertTrue(exp.parse("(1 * 2) * 3 + (101+3)"));
		
		ArrayList<Term> terms = exp.getTermsInOrder(); 
		
		assertEquals(13, terms.size());
	}
	
	@Test
	public void testShouldParsePolynomialExpressionWithMultipleParenthesis() {
		Expression exp = new Expression();
		assertTrue(exp.parse("((1) * (2)) * (3) + (((101)+(3)))"));
		
		ArrayList<Term> terms = exp.getTermsInOrder(); 
		
		assertEquals(25, terms.size());
	}
	
	@Test
	public void testShouldFailParsingPolynomialExpressionWithInvalidOperand() {
		Expression exp = new Expression();
		
		assertFalse(exp.parse("1 +"));
		assertFalse(exp.parse("1 # 2"));
		assertFalse(exp.parse("abc"));
		assertFalse(exp.parse("1 + 2 = "));
		assertFalse(exp.parse("1 + 2 *"));
		assertFalse(exp.parse("1 2"));
		assertFalse(exp.parse("1 * + 2"));
		assertFalse(exp.parse("((1) * (2)) * (3) + (((101)+(3))"));
	}
	
	@Test
	public void testShouldParsePolynomialExpressionWithRepeatedMultiplyAndDivide() {
		Expression exp = new Expression();
		assertTrue(exp.parse("(1 * 2) * 3 * (123/3) /5/4*7"));
		
		ArrayList<Term> terms = exp.getTermsInOrder(); 
		
		assertEquals(19, terms.size());
	}
	
	@Test
	public void testShouldParseMonomialExpression() {
		Expression exp = new Expression();
		assertTrue(exp.parse("1 *    2"));
		
		assertEquals(3, exp.getTermsInOrder().size());
	}
	
	@Test
	public void testInit() {
		Expression exp = new Expression();
		exp.parse("1");
		
		assertEquals(1, exp.getTermsInOrder().size());
		
		assertEquals("1", ((Digit)(exp.getTermsInOrder().get(0))).toString());
	}
	
	@Test
	public void testParseBigNumbers() {
		Expression exp = new Expression();
		exp.parse("123456");
		
		assertEquals(1, exp.getTermsInOrder().size());
		
		assertEquals("123456", ((Digit)(exp.getTermsInOrder().get(0))).toString());
	}
	
	@Test
	public void testParseDigit() throws Exception {
		CharacterIterator ci = new StringCharacterIterator("12");
		Expression exp = new Expression();
		
		assertTrue(exp.parseDigit(ci));
		assertEquals("12", ((Digit)(exp.getTermsInOrder().get(0))).toString());
		assertEquals(CharacterIterator.DONE, ci.current());
	}
	
	@Test
	public void testParseAddOperator() throws Exception {
		CharacterIterator ci = new StringCharacterIterator("+");
		Expression exp = new Expression();
		
		assertTrue(exp.parseAddOperator(ci));
		assertEquals("+", ((Operator)(exp.getTermsInOrder().get(0))).toString());
		assertEquals(CharacterIterator.DONE, ci.current());
	}
	
	@Test
	public void testParseAddOperator_ShouldParseOnlyOperator() throws Exception {
		CharacterIterator ci = new StringCharacterIterator("+1");
		Expression exp = new Expression();
		
		assertTrue(exp.parseAddOperator(ci));
		assertEquals("+", ((Operator)(exp.getTermsInOrder().get(0))).toString());
		assertEquals('1', ci.current());
	}
}
