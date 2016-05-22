package nl.javadude.gradle.plugins.scalariform

import com.google.common.io.Files
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

class ScalariformPluginTest {

  File projectDir
  def formatTask
  Project project
  private File srcDir

  @Before
  void initialize() {
    projectDir = Files.createTempDir()
    srcDir = new File(projectDir, "src/main/scala")
    srcDir.mkdirs()
    project = ProjectBuilder.builder().withProjectDir(projectDir).build()

    project.apply plugin: "scalariform"

    formatTask = project.tasks.formatScala
  }

  @After
  void cleanUp() {
    new AntBuilder().delete(dir: projectDir)
  }

  @Test
  void shouldSucceedOnEmptyProject() {
    formatTask.execute()
  }

  @Test
  void shouldFormatScalaFile() {
   testFile("import scala.collection.mutable.{Map,ListBuffer}", "import scala.collection.mutable.{ Map, ListBuffer }")
  }

  @Test
  void shouldAlignParameters() {
    project.scalariform {
      alignParameters = true
    }

    testFile("""
case class Foo(name: String,
  bar: Int
)""", """
case class Foo(name: String,
               bar: Int)""")
  }

  @Test
  void shouldAlignSingleLineCaseStatements() {
    project.scalariform {
      alignSingleLineCaseStatements = true
    }

    testFile("""class Foo() {
  val x = 5
  x match {
    case i: Int => "Foo"
    case b: Boolean => "Boo!"
  }
}""", """class Foo() {
  val x = 5
  x match {
    case i: Int     => "Foo"
    case b: Boolean => "Boo!"
  }
}""")
  }

  private void testFile(String contents, String expectedContents) {
    def file = writeTestFile(contents)
    formatTask.execute()
    assert file.text.equals(expectedContents)
  }

  private File writeTestFile(String contents) {
    def file = new File(srcDir, "Test.scala")
    file.write(contents)
    file
  }
}
