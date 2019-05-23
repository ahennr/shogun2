package de.terrestris.shoguncore.security.access;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import de.terrestris.shoguncore.dao.UserDao;
import de.terrestris.shoguncore.helper.IdHelper;
import de.terrestris.shoguncore.model.Application;
import de.terrestris.shoguncore.model.PersistentObject;
import de.terrestris.shoguncore.model.User;
import de.terrestris.shoguncore.model.security.Permission;
import de.terrestris.shoguncore.security.access.entity.PersistentObjectPermissionEvaluator;
import de.terrestris.shoguncore.security.access.factory.EntityPermissionEvaluatorFactory;

/**
 * @author Nils Bühner
 */
public class ShogunCorePermissionEvaluatorTest {

    @SuppressWarnings("rawtypes")
    @Mock
    private EntityPermissionEvaluatorFactory permissionEvaluatorFactoryMock;

    @Mock
    private UserDao<User> userDao;

    @InjectMocks
    private ShogunCorePermissionEvaluator permissionEvaluator;

    @Before
    public void setUp() {
        // Process mock annotations
        MockitoAnnotations.initMocks(this);
    }

    /**
     *
     */
    @Test
    public void hasPermission_ShouldRestrictAccessIfAuthenticationIsNull() {
        Authentication authentication = null;
        Object targetDomainObject = new Application("Test", "Test");
        Object permissionObject = "READ";

        // execute method that is tested here
        boolean permissionResult = permissionEvaluator.hasPermission(authentication, targetDomainObject, permissionObject);

        // verify
        assertFalse(permissionResult);
    }

    /**
     *
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Test
    public void hasPermission_ShouldRestrictAccessIfPrinicipalIsNotAUser() {
        // mock auth object
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn("Not a User object");

        PersistentObject targetDomainObject = new Application("Test", "Test");
        String permissionObject = "READ";

        PersistentObjectPermissionEvaluator persistentObjectEvaluatorMock = mock(PersistentObjectPermissionEvaluator.class);
        when(persistentObjectEvaluatorMock.hasPermission(null, targetDomainObject, Permission.fromString(permissionObject))).thenReturn(false);

        // mock factory (with previously mocked evaluator)
        when(permissionEvaluatorFactoryMock.getEntityPermissionEvaluator(targetDomainObject.getClass())).thenReturn(persistentObjectEvaluatorMock);

        // execute method that is tested here
        boolean permissionResult = permissionEvaluator.hasPermission(authentication, targetDomainObject, permissionObject);

        // verify
        assertFalse(permissionResult);
        verify(authentication, times(1)).getPrincipal();
        verifyNoMoreInteractions(authentication);
    }

    /**
     *
     */
    @Test
    public void hasPermission_ShouldRestrictAccessIfTargetDomainObjectIsNull() {
        // mock auth object
        Authentication authentication = mock(Authentication.class);
        final User user = new User("First name", "Last Name", "accountName");
        when(authentication.getPrincipal()).thenReturn(user);

        Object targetDomainObject = null;
        Object permissionObject = "READ";

        // execute method that is tested here
        boolean permissionResult = permissionEvaluator.hasPermission(authentication, targetDomainObject, permissionObject);

        // verify
        assertFalse(permissionResult);
        verify(authentication, times(0)).getPrincipal();
        verifyNoMoreInteractions(authentication);
    }

    /**
     *
     */
    @Test
    public void hasPermission_ShouldRestrictAccessIfTargetDomainObjectIsNotAPersistentObject() {
        // mock auth object
        Authentication authentication = mock(Authentication.class);
        final User user = new User("First name", "Last Name", "accountName");
        when(authentication.getPrincipal()).thenReturn(user);

        Object targetDomainObject = "Not a persistent object";
        Object permissionObject = "READ";

        // execute method that is tested here
        boolean permissionResult = permissionEvaluator.hasPermission(authentication, targetDomainObject, permissionObject);

        // verify
        assertFalse(permissionResult);
        verify(authentication, times(0)).getPrincipal();
        verifyNoMoreInteractions(authentication);
    }

    /**
     *
     */
    @Test
    public void hasPermission_ShouldRestrictAccessIfPermissionObjectIsNotAString() {
        // mock auth object
        Authentication authentication = mock(Authentication.class);
        final User user = new User("First name", "Last Name", "accountName");
        when(authentication.getPrincipal()).thenReturn(user);

        Object targetDomainObject = new Application("Test", "Test");
        Object permissionObject = 42; // not a string

        // execute method that is tested here
        boolean permissionResult = permissionEvaluator.hasPermission(authentication, targetDomainObject, permissionObject);

        // verify
        assertFalse(permissionResult);
        verify(authentication, times(0)).getPrincipal();
        verifyNoMoreInteractions(authentication);
    }

    /**
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Test
    public void hasPermission_ShouldRestrictAccessForSecuredTargetDomainObjectWithoutPermissions() throws NoSuchFieldException, IllegalAccessException {

        // mock auth object with user
        Authentication authenticationMock = mock(Authentication.class);
        final User user = new User("First name", "Last Name", "accountName");
        IdHelper.setIdOnPersistentObject(user, ""+42);

    }

}
