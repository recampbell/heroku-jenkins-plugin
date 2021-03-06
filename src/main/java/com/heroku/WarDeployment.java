package com.heroku;

import com.google.common.collect.ImmutableMap;
import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.util.FormValidation;
import org.kohsuke.stapler.AncestorInPath;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import java.io.IOException;

import static com.heroku.WarDeployment.WarDeploymentDescriptor.WAR_PATH;

/**
 * @author Ryan Brainard
 */
public class WarDeployment extends AbstractArtifactDeployment {

    @DataBoundConstructor
    public WarDeployment(String apiKey, String appName, String warPath) {
        super(apiKey, appName, ImmutableMap.of(WAR_PATH, warPath));
    }

    // Overridding and delegating to parent because Jelly only looks at concrete class when rendering views
    @Override
    public String getAppName() {
        return super.getAppName();
    }

    // Overridding and delegating to parent because Jelly only looks at concrete class when rendering views
    @Override
    public String getApiKey() {
        return super.getApiKey();
    }

    public String getWarPath() {
        return artifactPaths.get(WAR_PATH);
    }

    @Extension
    public static class WarDeploymentDescriptor extends AbstractArtifactDeployment.AbstractArtifactDeploymentDescriptor {

        static final String WAR_PIPELINE = "war";
        static final String WAR_PATH = "war";

        @Override
        public String getPipelineName() {
            return WAR_PIPELINE;
        }

        public FormValidation doCheckWarPath(@AncestorInPath AbstractProject project, @QueryParameter String value) throws IOException {
            return checkAnyArtifactPath(project, value, ".war");
        }
    }
}
