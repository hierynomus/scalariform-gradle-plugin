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
import spock.lang.Unroll

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

  @Unroll
  def "should support non-boolean Scalariform preference 'danglingCloseParenthesis' with value '#value'"() {
    given:
    def file = writeTestFile(contents)
    project.scalariform { danglingCloseParenthesis = value }

    when:
    scalariformTask.execute()

    then:
    file.text == expected

    where:
    value      | contents                             | expected
    "prevent"  | "\ncase class Foo(\nbars: String)"   | "\ncase class Foo(\n  bars: String)"
    "prevent"  | "\ncase class Foo(\nbars: String\n)" | "\ncase class Foo(\n  bars: String)"
    "preserve" | "\ncase class Foo(\nbars: String)"   | "\ncase class Foo(\n  bars: String)"
    "preserve" | "\ncase class Foo(\nbars: String\n)" | "\ncase class Foo(\n  bars: String\n)"
    "force"    | "\ncase class Foo(\nbars: String)"   | "\ncase class Foo(\n  bars: String\n)"
    "force"    | "\ncase class Foo(\nbars: String\n)" | "\ncase class Foo(\n  bars: String\n)"
  }

  private File writeTestFile(String contents, File dir = srcDir) {
    def f = new File(dir, "Test.scala")
    f << contents
    f
  }
}
