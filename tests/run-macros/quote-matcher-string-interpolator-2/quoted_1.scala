import scala.quoted._



object Macros {

  inline def (self: => StringContext) xyz(inline args: String*): String = ${impl('self, 'args)}

  private def impl(self: Expr[StringContext], args: Expr[Seq[String]])(using QuoteContext): Expr[String] = {
    (self, args) match {
      case ('{ StringContext(${Exprs(parts)}: _*) }, Exprs(args1)) =>
        val strParts = parts.map { case Const(str) => str.reverse }
        val strArgs = args1.map { case Const(str) => str }
        Expr(StringContext(strParts: _*).s(strArgs: _*))
      case _ => ???
    }

  }

}
