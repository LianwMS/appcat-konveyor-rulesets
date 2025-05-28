import java.security.acl.Acl;
import java.security.acl.AclEntry;
import java.security.acl.Group;
import java.security.acl.Permission;
import java.security.acl.Owner;
import java.security.acl.NotOwnerException;

import java.security.Principal;
import java.util.Enumeration;
import java.util.Vector;

// Dummy principal for testing
class UserPrincipal implements Principal {
    private final String name;

    public UserPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}

// Sample class using deprecated java.security.acl APIs
public class LegacyAclManager implements Acl {

    private final String name;
    private final Principal owner;
    private final Vector<AclEntry> entries = new Vector<>();

    public LegacyAclManager(String name, Principal owner) {
        this.name = name;
        this.owner = owner;
    }

    @Override
    public boolean addOwner(Principal caller, Principal owner) throws NotOwnerException {
        // Simplified for demonstration
        return false;
    }

    @Override
    public boolean deleteOwner(Principal caller, Principal owner) throws NotOwnerException {
        return false;
    }

    @Override
    public boolean isOwner(Principal principal) {
        return this.owner.equals(principal);
    }

    @Override
    public void setName(Principal caller, String name) throws NotOwnerException {
        throw new UnsupportedOperationException("Renaming not supported.");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean addEntry(Principal caller, AclEntry entry) throws NotOwnerException {
        return entries.add(entry);
    }

    @Override
    public boolean removeEntry(Principal caller, AclEntry entry) throws NotOwnerException {
        return entries.remove(entry);
    }

    @Override
    public Enumeration<Permission> getPermissions(Principal user) {
        Vector<Permission> permissions = new Vector<>();
        for (AclEntry entry : entries) {
            if (entry.getPrincipal().equals(user)) {
                permissions.addAll(entry.permissions());
            }
        }
        return permissions.elements();
    }

    @Override
    public Enumeration<Principal> getPrincipals() {
        Vector<Principal> principals = new Vector<>();
        for (AclEntry entry : entries) {
            principals.add(entry.getPrincipal());
        }
        return principals.elements();
    }

    @Override
    public Enumeration<AclEntry> entries() {
        return entries.elements();
    }

    @Override
    public String toString() {
        return "LegacyAclManager: " + name;
    }
}
