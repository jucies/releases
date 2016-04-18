# Jucies - Jenkins Update Center on GitHub (beta)
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

# Quick start (for plugin developers)
Jucies was created to make it deadly-simple to share your plugins. And so it is!

For instance, you have a plugin and you want to release it to Jucies:
https://github.com/jucies/maven-example-plugin

First, create a Release on GitHub: https://help.github.com/articles/creating-releases/

**NB:** there is no need to attach any binaries to the release. Basically, only tag is required.

Then, go to https://github.com/jucies/releases and open Pull Request with 1 single file:
`plugins/your-plugin-name.properties`, where `your-plugin-name` is a name of your plugin (the same as specified in your `pom.xml` file). File should contain these lines:
```properties
groupId = com.github.your-github-id
artifactId = repository-of-your-plugin
version = v0.1.0
```

Where:
* **your-github-id** - your login on GitHub.
* **repository-of-your-plugin** - name of the repository of the plugin you created.
* **version** - tag you used for the release.

Done! Now, once PR is accepted, you will see your plugin available in Jucies update center.

## Releasing new version
To release new version, just send another PR where you change `version` property.
