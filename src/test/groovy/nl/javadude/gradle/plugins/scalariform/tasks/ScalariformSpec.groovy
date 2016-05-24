/*
 *    Copyright 2016 Jeroen van Erp <jeroen@hierynomus.com>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package nl.javadude.gradle.plugins.scalariform.tasks

import nebula.test.ProjectSpec

class ScalariformSpec extends ProjectSpec {

  def scalariformTask
  private File srcDir

  def setup() {
    srcDir = new File(projectDir, "src/main/scala")
    srcDir.mkdirs()
    project.apply plugin: 'com.github.hierynomus.scalariform'
    project.apply plugin: 'scala'
    scalariformTask = project.tasks.getByName("formatScala")
  }

  @Override
  boolean deleteProjectDir() {
    true
  }

  def "should format imports"() {
    given:
    def file = writeTestFile("import scala.collection.mutable.{Map,ListBuffer}")

    when:
    scalariformTask.execute()

    then:
    file.text == "import scala.collection.mutable.{Map, ListBuffer}"
  }

  def "should align parameters"() {
    given:
    project.scalariform { alignParameters = true }
    def file = writeTestFile("""
case class Foo(name: String,
  bar: Int
)""")

    when:
    scalariformTask.execute()

    then:
    file.text == """
case class Foo(
  name: String,
  bar:  Int
)"""
  }

  def "should align single line case statements"() {
    given:
    project.scalariform { alignSingleLineCaseStatements = true }
    def file = writeTestFile("""class Foo() {
  val x = 5
  x match {
    case i: Int => "Foo"
    case b: Boolean => "Boo!"
  }
}""")

    when:
    scalariformTask.execute()

    then:
    file.text == """class Foo() {
  val x = 5
  x match {
    case i: Int     => "Foo"
    case b: Boolean => "Boo!"
  }
}"""
  }


  def "should double indent class declaration"() {
    given:
    project.scalariform { doubleIndentClassDeclaration = true }
    def file = writeTestFile("""
class Person(
  name: String,
  age: Int) {
  def method = ???
}""")

    when:
    scalariformTask.execute()

    then:
    file.text == """
class Person(
    name: String,
    age: Int
) {
  def method = ???
}"""
  }

  // Somehow adding all the cases and @Unroll-ing this method leads to the following error sometimes for some of the cases.
  // scala.MatchError: None (of class scala.None$)
  //  at scalariform.lexer.ScalaLexer.nextChar(ScalaLexer.scala:122)
  //  at scalariform.lexer.ScalaOnlyLexer$class.fetchScalaToken(ScalaOnlyLexer.scala:23)
  //  at scalariform.lexer.ScalaLexer.fetchScalaToken(ScalaLexer.scala:14)
  //  at scalariform.lexer.ScalaLexer.next(ScalaLexer.scala:192)
  //  at scalariform.lexer.WhitespaceAndCommentsGrouper.<init>(WhitespaceAndCommentsGrouper.scala:8)
  //  at scalariform.lexer.ScalaLexer$.tokenise(ScalaLexer.scala:263)
  //  at scalariform.formatter.SpecificFormatter$class.fullFormat(SpecificFormatter.scala:31)
  //  at scalariform.formatter.ScalaFormatter$$anon$1.fullFormat(ScalaFormatter.scala:476)
  //  at scalariform.formatter.ScalaFormatter$.formatAsEdits(ScalaFormatter.scala:485)
  //  at scalariform.formatter.ScalaFormatter$.format(ScalaFormatter.scala:469)
  //  at nl.javadude.gradle.plugins.scalariform.tasks.Scalariform.format_closure1(Scalariform.groovy:44)
  // So we just test one (non-default-value) case at the moment.
  def "should support non-boolean Scalariform preference 'danglingCloseParenthesis' with value 'prevent'"() {
    given:
    def file = writeTestFile(contents)
    project.scalariform { danglingCloseParenthesis = value }

    when:
    scalariformTask.execute()

    then:
    file.text == expected

    where:
    value     | contents                               | expected
    "prevent"  | "\ncase class Foo(\nbars: String\n)" | "\ncase class Foo(\n  bars: String)"
  }

  private File writeTestFile(String contents, File dir = srcDir) {
    def f = new File(dir, "Test.scala")
    f << contents
    f
  }
}
