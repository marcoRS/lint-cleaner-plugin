#Lint Cleaner Plugin 
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.droidtitan/lint-cleaner-plugin/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/com.droidtitan/lint-cleaner-plugin) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-lint--cleaner--plugin-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/877)

Removes unused resources reported by Android lint including strings, colors and dimensions.

## Usage

Apply the plugin in your `build.gradle`:

```groovy
buildscript {
  repositories {
    mavenCentral()
  }
  dependencies {
    classpath 'com.android.tools.build:gradle:0.12.+'
    classpath 'com.droidtitan:lint-cleaner-plugin:0.3.0'
  }
}

apply plugin: 'android'
apply plugin: 'com.droidtitan.lintcleaner'
```


Finally, to remove unused resources use: 
     
    gradle lintClean

## Optional Configuration using DSL

```groovy
lintCleaner {
    // Exclude specific files
    exclude = ['com_crashlytics_export_strings.xml','config.xml']

    // Ability to ignore all resource files. False by default. 
    ignoreResFiles = true
    
    // Default path is build/outputs/lint-results.xml
    lintXmlFilePath = 'path/to/lint-results.xml'
}
```