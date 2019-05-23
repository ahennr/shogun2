package de.terrestris.shoguncore.dao;

import de.terrestris.shoguncore.model.Application;
import de.terrestris.shoguncore.model.PersistentObject;
import de.terrestris.shoguncore.model.User;
import de.terrestris.shoguncore.model.UserGroup;
import de.terrestris.shoguncore.model.security.PermissionCollection;
import de.terrestris.shoguncore.paging.PagingResult;
import de.terrestris.shoguncore.repository.ApplicationRepository;
import de.terrestris.shoguncore.repository.UserRepository;
import de.terrestris.shoguncore.util.entity.EntityUtil;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * The superclass for all data access objects. Provides basic CRUD
 * functionality and a logger instance for all subclasses.
 *
 * @author Nils Bühner
 */
@Repository("genericDao")
@EnableMongoRepositories
public class GenericHibernateDao<E extends PersistentObject, ID extends Serializable> {

    /**
     * The LOGGER instance (that will be available in all subclasses)
     */
    protected final Logger LOG = getLogger(getClass());

    /**
     * Represents the class of the entity
     */
    private final Class<E> entityClass;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Default constructor
     */
    @SuppressWarnings("unchecked")
    public GenericHibernateDao() {
        this((Class<E>) PersistentObject.class);
    }

    /**
     * Constructor
     *
     * @param clazz
     */
    protected GenericHibernateDao(Class<E> clazz) {
        this.entityClass = clazz;
    }

    /**
     * Return the real object from the database. Returns null if the object does
     * not exist.
     */
    public E findById(String id) {

        LOG.trace("Not found !!!");
        return null;
    }


    /**
     * Returns all Entities by calling findByCriteria(), i.e. without arguments.
     *
     * @return All entities
     * @see GenericHibernateDao#findByCriteria(Criterion...)

    public List<E> findAll() {
        LOG.trace("Finding all instances of " + entityClass.getSimpleName());

        MongoRepository repository = getAssignedRepository(errrr);
        if (repository != null) {
            repository.save(e);
        }
        return null;
    }
     */

    /**
     * Saves or updates the passed entity.
     *
     * @param e The entity to save or update in the database.
     */
    public void saveOrUpdate(E e) {
        final String id = e.getId();
        final boolean hasId = id != null;
        String createOrUpdatePrefix = hasId ? "Updating" : "Creating a new";
        String idSuffix = hasId ? " with ID " + id : "";

        LOG.trace(createOrUpdatePrefix + " instance of " + entityClass.getSimpleName()
            + idSuffix);

        MongoRepository repository = getAssignedRepository(e);
        if (repository != null) {
            repository.save(e);
        }
    }

    /**
     *
     * @param e
     * @return
     */
    private MongoRepository getAssignedRepository(E e) {
        if (e instanceof Application) {
            return applicationRepository;
        }
        if (e instanceof User) {
            return userRepository;
        }
        return null;
    }

    /**
     * Deletes the passed entity.
     *
     * @param e The entity to remove from the database.
     */
    public void delete(E e) {
        LOG.trace("Deleting " + entityClass.getSimpleName() + " with ID " + e.getId());

        MongoRepository repository = getAssignedRepository(e);
        if (repository != null) {
            repository.delete(e);
        }
    }

    /**
     * Unproxy the entity (and eagerly fetch properties).
     */
    @SuppressWarnings("unchecked")
    public E unproxy(E e) {
        if (e == null) {
            throw new NullPointerException("Entity passed for initialization is null");
        }

        //

        return e;
    }

    /**
     * Detach an entity from the hibernate session
     *
     * @param e
     */
    public void evict(E e) {
        LOG.trace("Detaching " + entityClass.getSimpleName() + " with ID " + e.getId() + " from hibernate session");
        //getSession().evict(e);
    }

    /**
     * Gets the results, that match a variable number of passed criterions. Call
     * this method without arguments to find all entities.
     *
     * @param criterion A variable number of hibernate criterions
     * @return Entities matching the passed hibernate criterions

    @SuppressWarnings("unchecked")
    public List<E> findByCriteria(Criterion... criterion) throws HibernateException {
        LOG.trace("Finding instances of " + entityClass.getSimpleName()
            + " based on " + criterion.length + " criteria");

        Criteria criteria = createDistinctRootEntityCriteria(criterion);
        return criteria.list();
    }
     */
    /**
     * Gets the results, that match a variable number of passed criterions, but return a
     * stripped version of the entities, where only the fieldNames in <code>restrictFieldNames</code>
     * have their actual values set.
     * <p>
     * You can call this with <code>restrictFieldNames</code> = <code>null</code> to get the full
     * entities back.
     * <p>
     * You can call this method without criterion arguments to find all entities (stripped down to
     * the <code>restrictFieldNames</code>).
     * <p>
     * If this is called as <code>findByCriteriaRestricted(null)</code> the return value equals the
     * return value of <code>findByCriteria()</code>.
     *
     * @param restrictFieldNames
     * @param criterion
     * @return ds
     */
    @SuppressWarnings("unchecked")
    public List<E> findByCriteriaRestricted(List<String> restrictFieldNames) {
        LOG.trace("Finding instances of " + entityClass.getSimpleName()
            + " based on  criteria");




        return null;
    }

    /**
     * Gets the unique result, that matches a variable number of passed
     * criterions.
     *
     * @param criterion A variable number of hibernate criterions
     * @return Entity matching the passed hibernate criterions
     */
    @SuppressWarnings("unchecked")
    public E findByUniqueCriteria() {
        LOG.trace("Finding one unique " + entityClass.getSimpleName()
            + " based on criteria");
return null;
    }

    /**
     * Gets the results, that match a variable number of passed criterions,
     * considering the paging- and sort-info at the same time.
     *
     * @param firstResult Starting index for the paging request.
     * @param maxResults  Max number of result size.
     * @param criterion   A variable number of hibernate criterions
     * @return

    @SuppressWarnings("unchecked")
    public PagingResult<E> findByCriteriaWithSortingAndPaging(Integer firstResult,
                                                              Integer maxResults, List<Order> sorters, Criterion... criterion) throws HibernateException {

        int nrOfSorters = sorters == null ? 0 : sorters.size();

        LOG.trace("Finding instances of " + entityClass.getSimpleName()
            + " based on " + criterion.length + " criteria"
            + " with " + nrOfSorters + " sorters");

        Criteria criteria = createDistinctRootEntityCriteria(criterion);

        // add paging info
        if (maxResults != null) {
            LOG.trace("Limiting result set size to " + maxResults);
            criteria.setMaxResults(maxResults);
        }
        if (firstResult != null) {
            LOG.trace("Setting the first result to be retrieved to "
                + firstResult);
            criteria.setFirstResult(firstResult);
        }

        // add sort info
        if (sorters != null) {
            for (Order sortInfo : sorters) {
                criteria.addOrder(sortInfo);
            }
        }

        return new PagingResult<E>(criteria.list(), getTotalCount(criterion));
    }*/

    /**
     * This method returns a {@link Map} that maps {@link PersistentObject}s
     * to PermissionCollections for the passed {@link User}. I.e. the keySet
     * of the map is the collection of all {@link PersistentObject}s where the
     * user has at least one permission and the corresponding value contains
     * the {@link PermissionCollection} for the passed user on the entity.
     *
     * @param user
     * @return

    @SuppressWarnings({"unchecked"})
    public Map<PersistentObject, PermissionCollection> findAllUserPermissionsOfUser(User user) {

        Criteria criteria = getSession().createCriteria(PersistentObject.class);

        // by only setting the alias, we will only get those entities where
        // there is at least one permission set...
        // it is hard (or even impossible in this scenario) to create a
        // restriction that filters for permissions of the given user only.
        // using HQL here is no option as the PersistentObject is
        // a MappedSuperclass (without table).
        // another efficient way would be a SQL query, but then the SQL
        // would be written in an explicit SQL dialect...
        criteria.createAlias("userPermissions", "up");
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<PersistentObject> entitiesWithPermissions = criteria.list();

        Map<PersistentObject, PermissionCollection> userPermissions = new HashMap<PersistentObject, PermissionCollection>();

        // TODO find a better way than iterating over all entities of the system
        // that have at least one permission (for any user) (see comment above)
        for (PersistentObject entity : entitiesWithPermissions) {
            Map<User, PermissionCollection> entityUserPermissions = entity.getUserPermissions();
            if (entityUserPermissions.containsKey(user)) {
                userPermissions.put(entity, entityUserPermissions.get(user));
            }
        }

        return userPermissions;
    } */

    /**
     * This method returns a {@link Map} that maps {@link PersistentObject}s
     * to PermissionCollections for the passed {@link UserGroup}. I.e. the keySet
     * of the map is the collection of all {@link PersistentObject}s where the
     * user group has at least one permission and the corresponding value contains
     * the {@link PermissionCollection} for the passed user group on the entity.
     *
     * @param userGroup
     * @return

    @SuppressWarnings({"unchecked"})
    public Map<PersistentObject, PermissionCollection> findAllUserGroupPermissionsOfUserGroup(UserGroup userGroup) {

        Criteria criteria = getSession().createCriteria(PersistentObject.class);

        // by only setting the alias, we will only get those entities where
        // there is at least one permission set...
        // it is hard (or even impossible in this scenario) to create a
        // restriction that filters for permissions of the given user group only.
        // using HQL here is no option as the PersistentObject is
        // a MappedSuperclass (without table).
        // another efficient way would be a SQL query, but then the SQL
        // would be written in an explicit SQL dialect...
        criteria.createAlias("groupPermissions", "gp");
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<PersistentObject> entitiesWithPermissions = criteria.list();

        Map<PersistentObject, PermissionCollection> userGroupPermissions = new HashMap<PersistentObject, PermissionCollection>();

        // TODO find a better way than iterating over all entities of the system
        // that have at least one permission (for any user) (see comment above)
        for (PersistentObject entity : entitiesWithPermissions) {
            Map<UserGroup, PermissionCollection> entityUserGroupPermissions = entity.getGroupPermissions();
            if (entityUserGroupPermissions.containsKey(userGroup)) {
                userGroupPermissions.put(entity, entityUserGroupPermissions.get(userGroup));
            }
        }

        return userGroupPermissions;
    }
     */
    /**
     * Helper method: Creates a criteria for the {@link #entityClass} of this dao.
     * The query results will be handled with a
     * {@link DistinctRootEntityResultTransformer}. The criteria will contain
     * all passed criterions.
     *
     * @return

    protected Criteria createDistinctRootEntityCriteria(Criterion... criterion) {
        Criteria criteria = getSession().createCriteria(entityClass);
        addCriterionsToCriteria(criteria, criterion);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setCacheable(this.useQueryCache);
        return criteria;
    }*/

    /**
     * Returns the total count of db entries for the current type.
     *
     * @param criterion
     * @return

    public Number getTotalCount(Criterion... criterion) throws HibernateException {
        Criteria criteria = getSession().createCriteria(entityClass);
        addCriterionsToCriteria(criteria, criterion);
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    } */

    /**
     * Helper method: Adds all criterions to the criteria (if not null).
     *
     * @param criteria
     * @param criterion

    private void addCriterionsToCriteria(Criteria criteria, Criterion... criterion) {
        if (criteria != null) {
            for (Criterion c : criterion) {
                if (c != null) {
                    criteria.add(c);
                }
            }
        }
    }
     */
    /**
     * @return the entityClass
     */
    public Class<E> getEntityClass() {
        return entityClass;
    }

}
