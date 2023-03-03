package com.rudikershaw.remove.attached.artifact;


import com.google.common.collect.Sets;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.junit.Assert;
import org.mockito.Mockito;
import org.junit.Test;

import java.io.File;

public class RemoveAttachedArtifactsMojoTest
{
    @Test
    public void testArtifactRemovedWhenItShouldBe() throws Exception {
        RemoveAttachedArtifactsMojo mojo = new RemoveAttachedArtifactsMojo();
        mojo.artifactIds = Sets.newHashSet("remove-me");
        mojo.project = createTestMavenProject("remove-me");
        mojo.deleteFile = false;

        mojo.execute();
        Assert.assertTrue(mojo.project.getAttachedArtifacts().isEmpty());
    }

    @Test(expected = MojoExecutionException.class)
    public void testQuitsBuildIfArtifactFileCannotBeDeleted() throws Exception {
        RemoveAttachedArtifactsMojo mojo = new RemoveAttachedArtifactsMojo();
        mojo.artifactIds = Sets.newHashSet("remove-me");
        mojo.project = createTestMavenProject("remove-me");

        mojo.execute();
        Assert.assertTrue(mojo.project.getAttachedArtifacts().isEmpty());
    }

    @Test
    public void testWrongArtifactsArentRemoved() throws Exception {
        RemoveAttachedArtifactsMojo mojo = new RemoveAttachedArtifactsMojo();
        mojo.artifactIds = Sets.newHashSet("remove-me");
        mojo.project = createTestMavenProject("dont-remove-me");
        mojo.deleteFile = false;

        mojo.execute();
        Assert.assertFalse(mojo.project.getAttachedArtifacts().isEmpty());
    }

    private MavenProject createTestMavenProject(final String artifactId) {
        final MavenProject mp = new MavenProject();
        final Artifact artifact = Mockito.mock(Artifact.class);
        Mockito.when(artifact.getId()).thenReturn(artifactId);
        Mockito.when(artifact.getFile()).thenReturn(new File("/some/file/that/doesnt/exist.jar"));
        mp.addAttachedArtifact(artifact);
        return mp;
    }
}

