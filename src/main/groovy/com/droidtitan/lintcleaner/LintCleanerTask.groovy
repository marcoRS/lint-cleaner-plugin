package com.droidtitan.lintcleaner

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.NodeList
import javax.xml.parsers.DocumentBuilderFactory

/** Removes unused resources from projects by analyzing an Android lint xml file. */
class LintCleanerTask extends DefaultTask {

  final String LINE_SEPARATOR = System.getProperty("line.separator")
  final String UNUSED_RESOURCES_ID = "UnusedResources"

  final String ARRAY_XML_KEY = "array"
  final String FILE_PATH_XML_KEY = "file"
  final String ID_XML_KEY = "id"
  final String ISSUE_XML_KEY = "issue"
  final String LINE_XML_KEY = "line"
  final String LOCATION_XML_KEY = "location"

  /** Mapping of filePaths to a list of line numbers to be removed. */
  final Map<String, List<String>> filePathToLines = new HashMap<String, ArrayList<String>>()
  String lintXmlFilePath = "$project.buildDir/outputs/lint-results.xml"
  boolean ignoreResFiles = false;
  boolean run = true

  @TaskAction def removeUnusedResources() {
    if (!run) {
      return;
    }

    if (!lintXmlFilePath || lintXmlFilePath.empty) {
      println 'Lint results xml file path is unspecified'
    }

    def lintFile = new File(lintXmlFilePath)
    def builderFactory = DocumentBuilderFactory.newInstance()
    Document lintDocument = builderFactory.newDocumentBuilder().parse(lintFile)

    NodeList issues = lintDocument.getElementsByTagName(ISSUE_XML_KEY)
    processIssues(issues)
    if (!ignoreResFiles) {
      removeUnusedLinesInResFiles()
    }
  }

  void processIssues(NodeList issues) {
    issues.each {
      Element issue = it as Element

      if (issue.getAttribute(ID_XML_KEY).equals(UNUSED_RESOURCES_ID)) {
        NodeList locations = issue.getElementsByTagName(LOCATION_XML_KEY)

        if (locations.length == 1) {
          processLocation(locations.item(0) as Element)
        } else {
          locations.each {
            processLocation(it as Element)
          }
        }
      }
    }
  }

  /** Removes unused files or adds filePath to map for processing by @removeUnusedLinesInResFiles. */
  void processLocation(Element location) {
    String line = location.getAttribute(LINE_XML_KEY)
    String filePath = location.getAttribute(FILE_PATH_XML_KEY)

    if (line.empty) {
      File file = new File(filePath)
      file.delete()
      println "Removed $file.name"
    } else {
      List<String> lineNumbers = filePathToLines.get(filePath)
      lineNumbers = lineNumbers ? lineNumbers : new ArrayList<String>()
      lineNumbers.add(line)
      filePathToLines.put(filePath, lineNumbers)
    }
  }

  /** Removes unused resources from single files like strings.xml, color.xml etc. */
  void removeUnusedLinesInResFiles() {
    filePathToLines.each { filePath, unusedLines ->
      File sourceFile = new File(filePath)
      def sourceDir = sourceFile.getParentFile().toString()
      File tempFile = new File("${sourceDir}/${sourceFile.name}bak");

      tempFile.withWriter { writer ->
        int index = 1
        boolean removingArray = false;
        sourceFile.eachLine { line ->

          String lineNumber = Integer.toString(index);
          if (unusedLines.contains(lineNumber) || removingArray) {
            if (line.contains(ARRAY_XML_KEY)) {
              removingArray = !removingArray;
            }
          } else {
            writer << line + LINE_SEPARATOR
          }
          index++
        }
      }

      tempFile.renameTo(sourceFile)
      println "Removed entries from $sourceFile.name"
    }
  }
}
