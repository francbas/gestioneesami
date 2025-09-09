package testutil.logging;

import java.util.logging.Logger;

public interface Loggable {
    default Logger logger() {
        return Logger.getLogger(getClass().getName());
    }
}
