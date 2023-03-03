package com.rudikershaw.remove.attached.artifact;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Goal which removes 'attached artifacts'.
 */
@Mojo( name = "remove", defaultPhase = LifecyclePhase.VERIFY )
public class RemoveAttachedArtifactsMojo extends AbstractMojo {

    @Parameter
    Set<String> artifactIds;

    @Parameter(defaultValue = "true")
    boolean deleteFile = true;

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    MavenProject project;

    public void execute() throws MojoExecutionException {
        List<Artifact> artifacts = project.getAttachedArtifacts();
        for (final String artifactId : artifactIds) {
            final Optional<Artifact> artifact = artifacts.stream().filter(a -> a.getId().equals(artifactId)).findAny();
            artifact.ifPresent(artifacts::remove);

            if (deleteFile && artifact.isPresent()) {
                try {
                    Files.delete(artifact.get().getFile().toPath());
                } catch (IOException e) {
                    throw new MojoExecutionException("Failed to delete artifact with ID " + artifactId, e);
                }
            }
        }
    }
}
