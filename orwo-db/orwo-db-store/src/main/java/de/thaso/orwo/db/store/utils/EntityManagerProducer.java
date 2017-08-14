package de.thaso.orwo.db.store.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * EntityManagerProducer
 *
 * @author thaler
 * @since 13.09.16
 */
public class EntityManagerProducer {

    private static final Logger LOG = LoggerFactory.getLogger(EntityManagerProducer.class);

    @PersistenceContext(unitName = "orwodb")
    private EntityManager entityManager;

    @Produces
    public EntityManager produceEntityManager() {
        LOG.debug("Produce Entity Manager: {}", entityManager);
        return entityManager;
    }

    protected void closeEntityManager(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
