package hudson.plugins.audit_trail.rule;

import hudson.DescriptorExtensionList;
import hudson.ExtensionPoint;
import hudson.model.Describable;
import hudson.model.Descriptor;
import hudson.plugins.audit_trail.AuditLogger;
import jenkins.model.Jenkins;

import java.util.List;

public class Rule implements Describable<Rule>, ExtensionPoint {
    private final Item item;
    private final Trigger<?> trigger;
    private final List<AuditLogger> loggers;

    Rule(Item item, Trigger<?> trigger, List<AuditLogger> loggers) {
        this.item = item;
        this.trigger = trigger;
        this.loggers = loggers;
    }

    public Trigger<?> getTrigger() {
        return trigger;
    }

    public List<AuditLogger> getLoggers() {
        return loggers;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public Descriptor<Rule> getDescriptor() {
        return Jenkins.getInstance().getDescriptorOrDie(getClass());
    }

    public static DescriptorExtensionList<Rule, Descriptor<Rule>> all() {
        return Jenkins.getInstance().getDescriptorList(Rule.class);
    }

    public enum Item {
        HTTP_REQUEST, CREDENTIAL, JOB
    }
}
