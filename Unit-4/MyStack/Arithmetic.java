
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
		int operationStart = -1;
		int sum = 0;
		String[] str = exp.split(" ");
		for (int i = 0; i < str.length; i++) {
			try {
				Integer.parseInt("" + str[i]);
			} catch (Exception e) {
				operationStart = i;
				break;
			}
		}
		sum = Integer.parseInt(str[operationStart - 1]);
		int temp = operationStart - 1;
		for (int i = temp; i > -1; i--) {
			sum = operate(Integer.parseInt(str[i - 1 < 0 ? 0 : i - 1]), sum, str[operationStart]);
			operationStart++;
			i--;
		}
		return sum;
	}

	public static String convertClassicToStout(String exp) {
		exp = autoFormat(exp);
		StringBuilder str = new StringBuilder();
		MyStack<String> operations = new MyStack<String>();
		String[] split = exp.split(" ");
		for (int i = 0; i < split.length; i++) {
			String incoming = split[i];
			try {
				int value = Integer.parseInt(incoming);
				str.append(value).append(" ");
			} catch (Exception e) {
				if (incoming.contains("(")) {
					int back = -1;
					for (int j = exp.length() - 1; j > -1; j--) {
						if (exp.charAt(j) == ')') {
							back = j;
							break;
						}
					}
					str.append(convertClassicToStout(exp.substring(exp.indexOf("(") + 1, back)));
					for (int j = split.length - 1; j > -1; j--) {
						if (split[j].contains(")")) {
							i += j;
							break;
						}
					}
				} else {
					if (!operations.empty() && shouldPop(operations.peek(), incoming)) {
						str.append(operations.peek()).append(" ");
						operations.pop();
					}
					operations.push(incoming);
				}
			}
		}
		while (!operations.empty()) {
			str.append(operations.peek()).append(" ");
			operations.pop();
		}
		return str.toString();
	}

	private static boolean shouldPop(String stack, String incoming) {
		if (stack == null) {
			return false;
		}
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
		return stackValue >= incomingValue;
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
					str.insert(i + 3, " ");
					i += 3;
				} else {
					i++;
				}
			}
		}
		return str.toString().trim();
	}
}
