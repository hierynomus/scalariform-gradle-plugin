package nl.javadude.gradle.plugins.scalariform.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import scala.None
import scalariform.ScalaVersions
import scalariform.formatter.ScalaFormatter
import scalariform.formatter.ScalaFormatter$
import scalariform.formatter.preferences.FormattingPreferences
import scalariform.formatter.preferences.FormattingPreferences$

class Scalariform extends DefaultTask {

  FormattingPreferences prefs

  @TaskAction
  def format() {
    logger.info("Goind to reformat your codebase")
    project.fileTree(project.projectDir).include("**/*.scala").each { File f ->

      String contents = f.text
      logger.info("Formatting $f")
      def formattedContents = ScalaFormatter$.newInstance().format(contents, prefs, scala.None$.newInstance(), 0, ScalaVersions.Scala_2_10().toString())
      f.write(formattedContents)
    }
  }
}
