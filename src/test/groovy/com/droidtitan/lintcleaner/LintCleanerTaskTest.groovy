package com.droidtitan.lintcleaner

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

import static org.junit.Assert.assertTrue

class LintCleanerTaskTest {

  @Test public void canAddTaskToProject() {
    Project project = ProjectBuilder.builder().build()
    def task = project.task('lintcleaner', type: LintCleanerTask)
    assertTrue(task instanceof LintCleanerTask)
  }
}
