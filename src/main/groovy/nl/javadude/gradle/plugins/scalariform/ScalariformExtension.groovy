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

import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import scalariform.formatter.preferences.AllPreferences
import scalariform.formatter.preferences.FormattingPreferences$
import scalariform.formatter.preferences.PreferenceDescriptor

class ScalariformExtension {
  private static final Logger logger = Logging.getLogger(ScalariformExtension.class)

  def prefs = FormattingPreferences$.newInstance().apply()

  def charSetName = "UTF-8"

  boolean hasProperty(String name) {
    logger.debug("Checking missing property $name (${AllPreferences.preferencesByKey().contains(name)})")
    AllPreferences.preferencesByKey().contains(name)
  }

  void setProperty(String name, value) {
    if (!hasProperty(name)) {
      throw new MissingPropertyException("Missing property $name")
    }
    logger.info("Setting Scalariform preference '$name' to '$value'")
    def descriptor = AllPreferences.preferencesByKey().get(name).get() as PreferenceDescriptor
    if (value instanceof String) {
      value = descriptor.preferenceType().parseValue(value).right().get()
    }
    prefs = prefs.setPreference(descriptor, value)
  }

}
