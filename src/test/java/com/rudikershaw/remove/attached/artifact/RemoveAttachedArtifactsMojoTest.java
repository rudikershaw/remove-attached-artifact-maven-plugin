package com.rudikershaw.remove.attached.artifact;


import org.apache.maven.plugin.testing.MojoRule;
import org.apache.maven.plugin.testing.WithoutMojo;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RemoveAttachedArtifactsMojoTest
{
    @Rule
    public MojoRule rule = new MojoRule() {
        @Override
        protected void before() throws Throwable {

        }

        @Override
        protected void after() {

        }
    };

    /**
     * @throws Exception if any
     */
    @Test
    public void testSomething() throws Exception {

    }

    /** Do not need the MojoRule. */
    @WithoutMojo
    @Test
    public void testSomethingWhichDoesNotNeedTheMojoAndProbablyShouldBeExtractedIntoANewClassOfItsOwn() {
        assertTrue( true );
    }

}

