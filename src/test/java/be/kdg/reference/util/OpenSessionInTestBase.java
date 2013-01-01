package be.kdg.reference.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public abstract class OpenSessionInTestBase extends AbstractDependencyInjectionSpringContextTests {
    private SessionFactory sessionFactory;
    private Session session;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected void onSetUp() throws Exception {
        createHibernateSession();
        doSetUp();
    }

    protected void doSetUp() {
    }

    @Override
    protected void onTearDown() throws Exception {
        doTearDown();
        closeHibernateSession();
    }

    protected void doTearDown() {
    }

    private void closeHibernateSession() {
        TransactionSynchronizationManager.unbindResource(sessionFactory);
        session.close();
    }

    private void createHibernateSession() {
        session = sessionFactory.openSession();
        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
    }
}
