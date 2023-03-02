package com.rudikershaw.remove.attached.artifact;


import com.google.common.collect.Sets;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.project.MavenProject;
import org.junit.Assert;
import org.mockito.Mockito;
import org.junit.Test;

public class RemoveAttachedArtifactsMojoTest
{
    @Test
    public void testArtifactRemovedWhenItShouldBe() throws Exception {
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

        mojo.execute();
        Assert.assertFalse(mojo.project.getAttachedArtifacts().isEmpty());
    }

    private MavenProject createTestMavenProject(final String artifactId) {
        final MavenProject mp = new MavenProject();
        final Artifact artifact = Mockito.mock(Artifact.class);
        Mockito.when(artifact.getId()).thenReturn(artifactId);
        mp.addAttachedArtifact(artifact);
        return mp;
    }
}

