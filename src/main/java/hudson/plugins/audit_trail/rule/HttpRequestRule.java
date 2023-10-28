package hudson.plugins.audit_trail.rule;

import com.cloudbees.syslog.MessageFormat;
import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import hudson.model.Descriptor;
import hudson.plugins.audit_trail.AuditLogger;
import hudson.util.ListBoxModel;
import org.kohsuke.stapler.DataBoundConstructor;

import java.util.List;
import java.util.regex.Pattern;

public class HttpRequestRule extends Rule {

  @DataBoundConstructor
  public HttpRequestRule(HttpRequestTrigger.Verb verb, String regexp, List<AuditLogger> loggers) {
    super(Item.HTTP_REQUEST, new HttpRequestTrigger(verb, Pattern.compile(regexp)), loggers);
  }


  @Override
  public HttpRequestTrigger getTrigger() {
    return (HttpRequestTrigger) super.getTrigger();
  }

  @Extension
  public static class DescriptorImpl extends Descriptor<Rule> {

    @Override
    @NonNull
    public String getDisplayName() {
      return "Http Rule";
    }

    public ListBoxModel doFillVerbItems() {
      var items = new ListBoxModel();
      for (var verb : HttpRequestTrigger.Verb.values()) {
        items.add(verb.name());
      }
      return items;
    }
  }

}
