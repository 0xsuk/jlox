package com.craftinginterpreters.lox;

class AstPrinter implements Expr.Visitor<String> {
	String print(Expr expr) {
		return expr.accept(this);
	}

	@Override
	public String visitAssignExpr(Expr.Assign expr) {
		return parenthesize("assign", new Expr.Variable(expr.name), expr.value);
	}

	@Override
	public String visitBinaryExpr(Expr.Binary expr) {
		return parenthesize(expr.operator.lexeme, expr.left, expr.right);
	}

	@Override
	public String visitGroupingExpr(Expr.Grouping expr) {
		return parenthesize("group", expr.expression);
	}

	@Override
	public String visitLiteralExpr(Expr.Literal expr) {
		if (expr.value == null)
			return "nil";
		// TODO: should be escaped if value string
		return expr.value.toString();
	}

	@Override
	public String visitLogicalExpr(Expr.Logical expr) {
		return parenthesize(expr.operator.lexeme, expr.left, expr.right);
	}

	@Override
	public String visitUnaryExpr(Expr.Unary expr) {
		return parenthesize(expr.operator.lexeme, expr.right);
	}

	@Override
	public String visitVariableExpr(Expr.Variable expr) {
		return expr.name.lexeme;
	}

	@Override
	public String visitCallExpr(Expr.Call expr) {
		String lexeme = expr.callee.accept(this);
		return lexeme; // TODO: for now just return function name;
	}

	private String parenthesize(String name, Expr... exprs) {
		StringBuilder builder = new StringBuilder();

		builder.append("(").append(name);
		for (Expr expr : exprs) {
			builder.append(" ");
			builder.append(expr.accept(this));
		}
		builder.append(")");
		return builder.toString();
	}

	// java -cp target/classes/ com.craftinginterpreters.lox.AstPrinter
	// (* (- 123) (group 45.67))
	public static void main(String[] args) { // for test
		Expr expression = new Expr.Assign(new Token(TokenType.IDENTIFIER, "myvar", null, 1),
				new Expr.Literal("mynew\nvalue"));

		System.out.println(new AstPrinter().print(expression));
	}
}
