package org.app.property_manager.common.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GeneralException extends Exception {

    private static final Logger logger = LogManager.getLogger(GeneralException.class);

    private static final long serialVersionUID = -2803799245666892878L;

    public GeneralException(String errorMessage) {
        super(errorMessage);
        logger.error(errorMessage);
    }
    public GeneralException() {
        super();
    }
}
