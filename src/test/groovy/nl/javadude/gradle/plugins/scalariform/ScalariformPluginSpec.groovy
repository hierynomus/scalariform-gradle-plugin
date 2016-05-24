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
package nl.javadude.gradle.plugins.scalariform

import nebula.test.IntegrationSpec
import nebula.test.functional.ExecutionResult
import spock.lang.Unroll

class ScalariformPluginSpec extends IntegrationSpec {

  private File srcDir
  private File testDir

  def setup() {
    srcDir = directory("src/main/scala")
    testDir = directory("src/test/scala")
    buildFile << """
apply plugin: "com.github.hierynomus.scalariform"
apply plugin: "scala"
""".stripIndent()
  }

  def "should not fail on empty project"() {
    expect:
    runTasksSuccessfully("formatAllScala")
  }

  def "should format test sourceset"() {
    given:
    def testFile = writeTestFile("import scala.collection.mutable.{Map,ListBuffer}", testDir)

    when:
    def result = runTasksSuccessfully("formatTestScala")

    then:
    testFile.text == "import scala.collection.mutable.{Map, ListBuffer}"
  }

  def "should execute all individual tasks when calling formatAll"() {
    when:
    ExecutionResult result = runTasksSuccessfully("formatAllScala")

    then:
    result.wasExecuted("formatScala")
    result.wasExecuted("formatTestScala")
  }

  private File writeTestFile(String contents, File dir = srcDir) {
    def f = file("Test.scala", dir)
    f << contents
    f
  }
}
