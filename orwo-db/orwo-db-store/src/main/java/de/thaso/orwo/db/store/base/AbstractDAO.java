package de.thaso.orwo.db.store.base;

import de.thaso.orwo.db.api.base.EntityBase;
import de.thaso.orwo.db.common.DatabaseError;
import de.thaso.orwo.db.common.DatabaseException;

import java.util.List;

/**
 * AbstractDAO
 *
 * @author thaler
 * @since 2017-06-07
 */
public class AbstractDAO<T extends EntityBase> {

    final public T singleResultOrNull(List<T> resultList) {
        if (resultList.size() > 1) {
            throw new DatabaseException(DatabaseError.NON_UNIQUE_RESULT, "more than 1 results found");
        }
        return resultList.isEmpty() ? null : resultList.get(0);
    }
}
