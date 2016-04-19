package com.github.jucies

import com.google.gson.GsonBuilder
import groovy.transform.CompileStatic
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import ru.lanwen.jenkins.juseppe.beans.UpdateSite
import ru.lanwen.jenkins.juseppe.gen.HPI
import ru.lanwen.jenkins.juseppe.util.PluginListSerializer

@CompileStatic
class JuciesPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.apply(plugin: "base")

        project.repositories.jcenter()

        project.repositories.maven {
            (it as MavenArtifactRepository).url = "http://repo.jenkins-ci.org/public/"
        }

        project.repositories.maven {
            (it as MavenArtifactRepository).url = "https://jitpack.io"
        }

        def pluginsConfiguration = project.configurations.create("plugin")
        pluginsConfiguration.transitive = false

        project.file("plugins").eachFile {
            def properties = new Properties()
            it.withInputStream(properties.&load)

            project.dependencies.add(pluginsConfiguration.name, "${properties.groupId}:${properties.artifactId}:${properties.version}")
        }

        project.task("generate") << {
            def site = new UpdateSite(
                    id: "jucies",
                    updateCenterVersion: 1,
                    plugins: pluginsConfiguration.resolvedConfiguration.resolvedArtifacts.collect {
                        def identifier = it.moduleVersion.id
                        return HPI.loadHPI(it.file)
                                .withVersion(identifier.version)
                                .withUrl("https://jitpack.io/${identifier.group.replace(".", "/")}/${identifier.name}/${identifier.version}/${identifier.name}-${identifier.version}.hpi")
                    }
            )

            def gson = new GsonBuilder()
                    .registerTypeAdapter(PluginListSerializer.PLUGIN_LIST_TYPE, PluginListSerializer.asUpdateSite())
                    .disableHtmlEscaping()
                    .create()

            def result = String.format("updateCenter.post(%n%s%n);", gson.toJson(site))

            def targetFile = project.file("${project.buildDir}/update-center.json")
            targetFile.parentFile.mkdirs()
            targetFile.delete()

            targetFile << result
        }
    }
}