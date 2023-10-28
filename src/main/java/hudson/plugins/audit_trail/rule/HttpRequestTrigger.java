package hudson.plugins.audit_trail.rule;

import java.util.regex.Pattern;

public class HttpRequestTrigger implements Trigger<HttpRequestTrigger.HttpRequest> {

  private final HttpRequestTrigger.Verb verb;
  private final Pattern regexp;

  HttpRequestTrigger(HttpRequestTrigger.Verb verb, Pattern regexp) {
    this.verb = verb;
    this.regexp = regexp;
  }

  public Pattern getRegexp() {
    return regexp;
  }

  @Override
  public boolean shouldTrigger(HttpRequestTrigger.HttpRequest input) {
    return input.getVerb() == verb && regexp.matcher(input.getUrl()).matches();
  }

  public Verb getVerb() {
    return verb;
  }

  public static class HttpRequest {
    private final HttpRequestTrigger.Verb verb;
    private final String url;

    HttpRequest(HttpRequestTrigger.Verb verb, String url) {
      this.verb = verb;
      this.url = url;
    }

    public String getUrl() {
      return url;
    }

    public Verb getVerb() {
      return verb;
    }
  }

  public enum Verb {
    GET, POST, PUT, DELETE
  }
}
