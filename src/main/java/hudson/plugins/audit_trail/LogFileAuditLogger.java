package hudson.plugins.audit_trail;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import hudson.Extension;
import hudson.model.Descriptor;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;
import java.util.logging.FileHandler;

/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 * @author Pierre Beitz
 */
public class LogFileAuditLogger extends AbstractLogFileAuditLogger {
    protected static final String DAILY_ROTATING_FILE_REGEX_PATTERN = "-[0-9]{4}-[0-9]{2}-[0-9]{2}" + ".*" + "(?<!lck)$";

    @DataBoundConstructor
    public LogFileAuditLogger(String log, int limit, int count, String logSeparator) {
        super(log, count, logSeparator);
        this.limit = limit;
        configure();
    }

    @Override
    FileHandler createFileHandler() throws IOException {
        return new FileHandler(getLog(), limit * 1024 * 1024, getCount(), true);
    }

    @SuppressFBWarnings(
            value = "RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE",
            justification = "value can be null if no config file exists")
    Object readResolve() {
        super.readResolve();

        configure();

        return this;
    }

    private int limit = 1;

    public int getLimit() {
        return limit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogFileAuditLogger that = (LogFileAuditLogger) o;

        return limit == that.limit;
    }

    @Override
    public int hashCode() {
        return limit;
    }

    @Extension
    public static class DescriptorImpl extends Descriptor<AuditLogger> {

        @Override
        public String getDisplayName() {
            return "Log file";
        }
    }

}