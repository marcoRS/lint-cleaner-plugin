# Lint Cleaner Plugin

Removes unused resources reported by Android lint including strings, colors and dimensions.

## Usage

Clone this repository and run ```gradle install``` then apply the plugin in your `build.gradle`:

```groovy
buildscript {
  repositories {
    mavenCentral()
    mavenLocal()
  }
  dependencies {
    classpath 'com.android.tools.build:gradle:0.12.+'
    classpath 'com.droidtitan:lint-cleaner-plugin:0.1.0'
  }
}

apply plugin: 'android'
apply plugin: 'com.droidtitan.lintcleaner'
```


Finally, to remove unused resources use: 
     
    gradle lintClean

## Optional Configuration using DSL

```groovy
lintClean {
    // Exclude specific files
    exclude = ['com_crashlytics_export_strings.xml','config.xml']

    // Ability to ignore all resource files. False by default. 
    ignoreResFiles = true
    
    // Default path is build/outputs/lint-results.xml
    lintXmlFilePath = 'path/to/lint-results.xml'
}
```