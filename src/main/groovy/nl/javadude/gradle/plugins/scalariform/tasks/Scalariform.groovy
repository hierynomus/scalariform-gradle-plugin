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

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.TaskAction
import scala.None
import scala.None$
import scala.Option
import scalariform.ScalaVersions
import scalariform.formatter.ScalaFormatter
import scalariform.formatter.ScalaFormatter$
import scalariform.formatter.preferences.FormattingPreferences
import scalariform.formatter.preferences.FormattingPreferences$

class Scalariform extends DefaultTask {
  @Input
  FormattingPreferences prefs

  @Input
  SourceSet sourceSet

  @Input
  def charSetName

  @TaskAction
  def format() {
    logger.info("Reformatting the '${sourceSet.name}' source set")
    sourceSet.allSource.filter { File f -> f.getAbsolutePath().endsWith(".scala") } .each { File f ->
      String contents = f.getText("${charSetName}")
      logger.debug("Formatting '$f'")
      def formattedContents = ScalaFormatter$.newInstance().format(contents, prefs, None$.MODULE$ as Option<String>, 0, ScalaVersions.DEFAULT().toString())
      f.write(formattedContents, "${charSetName}")
    }
  }
}
