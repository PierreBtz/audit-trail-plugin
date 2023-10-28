package hudson.plugins.audit_trail.rule;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import hudson.model.Descriptor;
import hudson.plugins.audit_trail.AuditLogger;
import org.kohsuke.stapler.DataBoundConstructor;

import java.util.List;

public class CredentialRule extends Rule {

  @DataBoundConstructor
  public CredentialRule(List<AuditLogger> loggers) {
    super(Item.CREDENTIAL, new CredentialTrigger(), loggers);
  }

  @Extension
  public static class DescriptorImpl extends Descriptor<Rule> {

    @Override
    @NonNull
    public String getDisplayName() {
      return "Credential Rule";
    }
  }
}
