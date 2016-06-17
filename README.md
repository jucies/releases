# Jucies - Alternative Jenkins Update Center on GitHub (beta)
[![Build Status](https://travis-ci.org/jucies/releases.svg?branch=master)](https://travis-ci.org/jucies/releases)
## What is it?
How much time you think it will take to release a plugin for Jenkins? How many steps you will have to perform? With Jucies it's damn simple - just create a tag (release) on GitHub, send/update Pull Request to make it published - and it's done! It's not even required to build `*.hpi` file yourself!

## Whoa! But how?
Jucies is based on top of https://jitpack.io - great service to lazily build your Maven/Gradle projects. Also, Jucies acts as an Updates Center for Jenkins, and your users will be notified when you update your plugin to the newer version.

# Quick start (for users)
1. Install Jucies Plugin: http://wiki.jenkins-ci.org/display/JENKINS/Jucies+Plugin
1. Open `Plugin Manager -> Advanced` and verify that Jucies appears in "Other Sites" section.
1. Press "Check now"

**NB:** verify that installed version is at least 0.1.1. If not - go to "Updates" and update your plugin to 0.1.1 or higher.

Any plugin available on Jucies should appear on "available" tab of `Manage Plugins` page. Just install it as any other Jenkins Plugin.

Alternatively, you can download the latest build of plugin from here:
https://jitpack.io/com/github/jucies/jucies-plugin/master-SNAPSHOT/jucies-plugin-master-SNAPSHOT.hpi

# Quick start (for plugin developers)
Jucies was created to make it deadly-simple to share your plugins. And so it is!

For instance, you have a plugin and you want to release it to Jucies:
https://github.com/jucies/maven-example-plugin

First, create a Release on GitHub: https://help.github.com/articles/creating-releases/

**NB:** there is no need to attach any binaries to the release.

Then, go to https://github.com/jucies/releases and open Pull Request with 1 single file: `plugins/your-plugin-name.properties`.

File should contain these lines:
```properties
repository = your-github-id/repository-of-your-plugin
```

Where:
* **your-github-id** - your login on GitHub.
* **repository-of-your-plugin** - name of the repository of the plugin you created.

Done! Now, once PR is accepted, you will see your plugin available in Jucies update center.

**Hint1:** if you use submodules, you can point Jucies to the module:
```properties
repository = your-github-id/repository-of-your-plugin
module = sub-module
```

**Hint2:** you can force Jucies to use a concrete tag and version:
```properties
repository = your-github-id/repository-of-your-plugin
tag = your-plugin-name-0.1.0
version = 0.1.0
```

Where:
* **version** - tag you used for the release (try using the semantic version as a tag).
* (optional) tag - git tag for this release.

## Releasing new version
To release new version, just create a new release on GitHub - that's it!
