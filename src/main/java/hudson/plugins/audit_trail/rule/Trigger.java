package hudson.plugins.audit_trail.rule;

@FunctionalInterface
public interface Trigger<T> {

  boolean shouldTrigger(T input);
}
