package com.gaodashang.jmd;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * comments.
 *
 * @author eva
 */

public class AtomikosJtaPlatform extends AbstractJtaPlatform {

    private final Log logger = LogFactory.getLog(getClass());

    public static TransactionManager transactionManager;

    public static UserTransaction userTransaction;


    @Override
    protected TransactionManager locateTransactionManager() {
        logger.debug("atomikosTransactionManager:" + AtomikosJtaPlatform.transactionManager);
        return AtomikosJtaPlatform.transactionManager;
    }

    @Override
    protected UserTransaction locateUserTransaction() {
        logger.debug("userTransaction:" + AtomikosJtaPlatform.userTransaction);
        return AtomikosJtaPlatform.userTransaction;
    }
}
