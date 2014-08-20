package com.droidtitan.lintcleaner

import org.gradle.api.Plugin
import org.gradle.api.Project

class LintCleanerPlugin implements Plugin<Project> {

  static final String GROUP = "LintCleaner"
  static final String LINT_CLEAN_TASK = "lintClean"
  static final String LINT_TASK = "lint"
  static final String LINT_CLEAN_DESCRIPTION = "Removes unused resources reported by lint"

  @Override void apply(Project project) {

    project.task(LINT_CLEAN_TASK, type: LintCleanerTask, dependsOn: LINT_TASK) {
      group = GROUP
      description = LINT_CLEAN_DESCRIPTION
    }

    project.tasks.build.dependsOn LINT_CLEAN_TASK
  }
}
