# Remove Attached Artifact Maven Plugin

This plugin covers is to resolve a relatively niche issue. During a Maven build, a MavenProject object exists in memory that tracks builds state for operations later on in the build process. In the build state their exists a register of `attachedArtifacts`, which acts as a list of artifacts that have been created and require later processing. If any of these artifacts do not exist at a later stage in the build there are various failures you can expect to see. If you find yourself in a position where you need to temporarily create and/or install artifacts early in your build, but do not want them there at a later stage, you may find yourself in need of a way to de-register these `attachedArtifacts`. That is what this plugin is for.

If you find yourself in need of this plugin it may be that your build is in a bit of a mess. You may want to consider alternatives before going down this route.
