# Remove Attached Artifact Maven Plugin

This plugin is to resolve a relatively niche issue. During a Maven build, a MavenProject object exists in memory that tracks builds state for operations later on in the build process. In the build state their exists a register of `attachedArtifacts`, which acts as a list of artifacts that have been created and require later processing. If any of these artifacts do not exist at a later stage in the build there are various failures you can expect to see. If you find yourself in a position where you need to temporarily create and/or install artifacts early in your build, but want them removed before a later stage, you may find yourself in need of a way to remove the artifact and de-register these `attachedArtifacts`. That is what this plugin is for.

If you find yourself in need of this plugin it may be that your build is in a bit of a mess. You may want to consider alternatives before going down this route.

# Example Usage

```xml
<plugin>
  <groupId>com.rudikershaw.remove.attached.artifact</groupId>
  <artifactId>remove-attached-artifact-maven-plugin</artifactId>
  <version>1.0</version>
  <executions>
    <execution>
      <id>remove-attached-artifacts</id>
      <goals>
        <goal>remove</goal>
      </goals>
      <configuration>
        <artifactIds>
          <artifactId>${project.groupId}:${project.artifactId}:jar:TEST:${project.version}</artifactId>
          <artifactId>${project.groupId}:${project.artifactId}:test-jar:TEMP:${project.version}</artifactId>
        </artifactIds>
      </configuration>
    </execution>
  </executions>
</plugin>
```

By default, the plugin will remove the artifact file as well as deregistering the artifact for later processing. To turn off the file removal you can add;

```xml
<configuration>
  <deleteFile>false</deleteFile>
  <artifactIds>
    <artifactId>${project.groupId}:${project.artifactId}:jar:TEST:${project.version}</artifactId>
    <artifactId>${project.groupId}:${project.artifactId}:test-jar:TEMP:${project.version}</artifactId>
  </artifactIds>
</configuration>
```
