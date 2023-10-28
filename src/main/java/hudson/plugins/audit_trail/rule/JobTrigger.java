package hudson.plugins.audit_trail.rule;

public class JobTrigger implements Trigger<String> {
  private final String name;

  JobTrigger(String name) {
    this.name = name;
  }

  @Override
  public boolean shouldTrigger(String input) {
    return input.equalsIgnoreCase(name);
  }
}
