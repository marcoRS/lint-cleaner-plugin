package com.droidtitan.lintcleaner

import org.gradle.api.Plugin
import org.gradle.api.Project

class LintCleanerPlugin implements Plugin<Project> {
  static final String GROUP = "LintCleaner"
  static final String ANDROID_LINT_TASK = "lint"
  static final String EXTENSION_NAME = 'lintCleaner'

  @Override void apply(Project project) {

    project.extensions.create(EXTENSION_NAME, LintCleanerPluginExtension, project)

    project.task(LintCleanerTask.NAME, type: LintCleanerTask, dependsOn: ANDROID_LINT_TASK) {
      def extension = project.extensions.findByName(EXTENSION_NAME) as LintCleanerPluginExtension
      conventionMapping.lintXmlFilePath = { extension.lintXmlFilePath }
      conventionMapping.ignoreResFiles = { extension.ignoreResFiles }
      conventionMapping.excludes = { extension.exclude }
    }
  }
}
