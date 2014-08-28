package com.droidtitan.lintcleaner

import org.gradle.api.Project

class LintCleanerPluginExtension {

  List<String> exclude = []
  String lintXmlFilePath
  boolean ignoreResFiles = false

  LintCleanerPluginExtension(Project project) {
    /** Default lint file path when user does not set it explicitly using DSL */
    this.lintXmlFilePath = "$project.buildDir/outputs/lint-results.xml"
  }
}
