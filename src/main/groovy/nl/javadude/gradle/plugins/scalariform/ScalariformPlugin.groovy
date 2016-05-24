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

import nl.javadude.gradle.plugins.scalariform.tasks.Scalariform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaBasePlugin
import org.gradle.api.plugins.JavaPluginConvention

class ScalariformPlugin implements Plugin<Project> {
  @Override
  void apply(Project project) {
    def extension = new ScalariformExtension()
    project.extensions.add("scalariform", extension)
    def formatAllTask = project.tasks.create("formatAllScala")

    project.plugins.withType(JavaBasePlugin) {
      def jpc = project.convention.getPlugin(JavaPluginConvention)
      jpc.sourceSets.all { sourceSet ->
        def task = project.tasks.create(sourceSet.getTaskName("format", "Scala"), Scalariform).configure { t ->
          t.sourceSet = sourceSet
          t.conventionMapping.prefs = { -> extension.prefs }
        }
        formatAllTask.dependsOn task
      }
    }


  }
}
