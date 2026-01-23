public class Arithmetic {

	// Evaluates a String exp that has an arithmetic expression, written in classic
	// notation
	public static int evaluate(String exp) {
		return evaluateStout(convertClassicToStout(exp));
	}

	// Returns the result of doing operand1 operation operand2,
	// e.g. operate(5, 2, "-") should return 3
	public static int operate(int operand1, int operand2, String operation) {
		if (operation.equals("-")) {
			return operand1 - operand2;
		} else if (operation.equals("+")) {
			return operand1 + operand2;
		} else if (operation.equals("*")) {
			return operand1 * operand2;
		} else if (operation.equals("/")) {
			return operand1 / operand2; // assumes operand2 != 0
		} else if (operation.equals("%")) {
			return operand1 % operand2;
		} else if (operation.equals("^")) {
			return (int) Math.pow(operand1, operand2);
		} else {
			throw new IllegalArgumentException("Invalid operation");
		}
	}

	// Evaluates a String exp that has an arithmetic expression written in STOUT
	// notation
	public static int evaluateStout(String exp) {
		exp = autoFormat(exp);
		String[] str = exp.split(" ");
		MyStack<Integer> values = new MyStack<>();
		for (String value : str) {
			if (value.isEmpty())
				continue;
			try {
				values.push(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				int operand2 = values.pop(), operand1 = values.pop();
				values.push(operate(operand1, operand2, value));
			}
		}
		return values.peek();
	}

	public static String convertClassicToStout(String exp) {
		exp = autoFormat(exp);
		String[] values = exp.split(" ");
		MyStack<String> operations = new MyStack<String>();
		StringBuilder str = new StringBuilder();

		for (String variable : values) {
			try {
				str.append(Integer.parseInt(variable)).append(" ");
			} catch (Exception e) {
				if (variable.equals("(")) {
					operations.push(variable);
				} else if (variable.equals(")")) {
					while (!operations.peek().equals("(")) {
						str.append(operations.pop()).append(" ");
					}
					operations.pop();
				} else {
					while (!operations.empty() && !operations.peek().equals("(")
							&& shouldPop(operations.peek(), variable)) {
						str.append(operations.pop()).append(" ");
					}
					operations.push(variable);
				}
			}
		}

		while (!operations.empty()) {
			str.append(operations.pop()).append(" ");
		}

		return !str.isEmpty() ? str.deleteCharAt(str.length() - 1).toString() : "";
	}

	private static boolean shouldPop(String stack, String incoming) {
		if (stack == null)
			return false;

		String dictionary = "4+4-5*5/5%6^8(8)";
		int stackValue = 0, incomingValue = 0;

		for (int i = 0; i < dictionary.length(); i++) {
			if (stack.equals(dictionary.substring(i, i + 1))) {
				stackValue = Integer.parseInt(dictionary.substring(i - 1, i));
			}
			if (incoming.equals(dictionary.substring(i, i + 1))) {
				incomingValue = Integer.parseInt(dictionary.substring(i - 1, i));
			}
		}

		if (stackValue == incomingValue) {
			return !stack.equals("^");
		}

		return stackValue > incomingValue;
	}


	private static String autoFormat(String exp) {
		StringBuilder str = new StringBuilder(exp);
		for (int i = 0; i < str.length(); i++) {
			try {
				if (i + 1 < str.length()) {
					if (str.charAt(i) == '(') {
						str.insert(i + 1, " ");
						i++;
					} else if (str.charAt(i) == ')') {
						str.insert(i, " ");
						i++;
					} else {
						Integer.parseInt("" + str.charAt(i + 1));
					}
				}
			} catch (Exception e) {
				if (str.charAt(i + 1) != ' ') {
					str.insert(i + 1, " ");
					i += 3;
				} else {
					i++;
				}
			}
		}
		return str.toString().trim();
	}
}
