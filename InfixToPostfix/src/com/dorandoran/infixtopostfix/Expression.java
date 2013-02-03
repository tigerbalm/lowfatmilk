package com.dorandoran.infixtopostfix;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

public class Expression {
	ArrayList<Term> terms;
	
	public Expression() {
		terms = new ArrayList<Term>();				
	}

	// TODO: find how to check invalid expression
	
	// expression := summand { adding_op summand }+
	// summand := factor { multiplying_op factor }+
	// factor := (expression) | digit
	// adding_op = + | -
	// multiplying_op = * | /
	// digit = [0 - 9]*
	public boolean parse(String expression) {
		StringCharacterIterator iter = new StringCharacterIterator(expression);
		
		boolean result = parseExpression(iter);
		
		removeWhitespace(iter); 
		
		return result && iter.current() == CharacterIterator.DONE;		
	}

	// expression := summand { adding_op summand }*
	private boolean parseExpression(CharacterIterator iter) {
		if (parseSummand(iter)) {
			while(parseAddOperator(iter)) {
				if (!parseSummand(iter)) {					
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}

	// summand := factor { multiplying_op factor }+
	private boolean parseSummand(CharacterIterator iter) {
		if (parseFactor(iter)) {
			while(parseMultiplyOperator(iter)) {
				if (!parseFactor(iter)) {
					return false;
				}
			}
			return true;
		}
		
		return false;
	}

	private boolean parseFactor(CharacterIterator iter) {
		if (parseExpressionWithParenthesis(iter) || parseDigit(iter)) {			
			return true;
		}
		return false;
	}
	
	private boolean parseExpressionWithParenthesis(CharacterIterator iter) {
		removeWhitespace(iter);
		
		char c = iter.current();
		
		if ('(' == c) {
			terms.add(new LeftParen());
			
			iter.next();
			
			if (parseExpression(iter)) {
				c = iter.current();
				
				if (')' == c) {
					terms.add(new RightParen());
					
					iter.next();
					return true;
				}
			}
		}
		
		return false;
	}

	/*private*/ boolean parseDigit(CharacterIterator iter) {
		removeWhitespace(iter);
		
		char c = (char) iter.current();
		
		StringBuilder digits = new StringBuilder();
		
		while (Character.isDigit(c) && !Character.isSpace(c) && c != CharacterIterator.DONE) {
			digits.append(c);
			
			c = (char) iter.next();
		}
		
		if (digits.length() > 0) {
			terms.add(new Digit(digits.toString()));
			return true;
		}
		
		return false;
	}
	
	/*private*/ boolean parseAddOperator(CharacterIterator iter) {
		removeWhitespace(iter);
		
		char c = iter.current();
		
		if ('+' == c || '-' == c) {
			iter.next();
			
			terms.add(new Operator(String.valueOf(c)));
			return true;
		}
		
		return false;
	}
	
	private boolean parseMultiplyOperator(CharacterIterator iter) {
		removeWhitespace(iter);
		
		char c = iter.current();
		
		if ('*' == c || '/' == c) {
			iter.next();
			
			terms.add(new Operator(String.valueOf(c)));
			return true;
		}
		
		return false;
	}

	private void removeWhitespace(CharacterIterator iter) {
		while (Character.isSpaceChar(iter.current())) {			
			iter.next();
		}		
	}

	public ArrayList<Term> getTermsInOrder() {		
		return terms;
	}
}
