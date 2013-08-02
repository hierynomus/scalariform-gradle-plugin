package nl.javadude.gradle.plugins.scalariform

import nl.javadude.gradle.plugins.scalariform.tasks.Scalariform
import org.gradle.api.Plugin
import org.gradle.api.Project

class ScalariformPlugin implements Plugin<Project> {
  @Override
  void apply(Project project) {
    def extension = project.extensions.create("scalariform", ScalariformExtension)

    project.tasks.create("formatScala", Scalariform).configure {
      conventionMapping.prefs = {-> extension.prefs }
    }
  }
}
