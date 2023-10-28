package hudson.plugins.audit_trail.rule;

import hudson.Extension;
import hudson.model.Descriptor;
import hudson.plugins.audit_trail.AuditLogger;
import org.kohsuke.stapler.DataBoundConstructor;

import java.util.List;

public class JobRule extends Rule {

  @DataBoundConstructor
  public JobRule(String jobName, List<AuditLogger> loggers) {
    super(Item.JOB, new JobTrigger(jobName), loggers);
  }

  @Extension
  public static class DescriptorImpl extends Descriptor<Rule> {

    @Override
    public String getDisplayName() {
      return "Job Rule";
    }

  }
}
