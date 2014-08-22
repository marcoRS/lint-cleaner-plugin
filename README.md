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
    // Default configuration removes unused strings, colors, dimens.
    ignoreResFiles = false
    // Default configuration uses build/outputs/lint-results.xml
    lintXmlFilePath = "path/to/lint-results.xml"
}
```