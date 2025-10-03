package org.app.property_manager.common.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GeneralWarning  extends Exception{
    private static final Logger logger = LogManager.getLogger(GeneralWarning.class);
    private static final long serialVersionUID = -8668297014578074295L;

    public GeneralWarning(String warningMessage) {
        super(warningMessage);
        logger.error(warningMessage);
    }

    public GeneralWarning() {
        super();
    }
}
