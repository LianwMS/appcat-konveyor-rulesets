import javax.security.auth.Subject;
import javax.security.auth.login.LoginException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.Policy;
import java.security.PermissionCollection;
import java.security.CodeSource;
import java.security.ProtectionDomain;

// Dummy class showing usage of deprecated javax.security.auth.Policy
public class LegacyAuthPolicyChecker {

    private final Policy policy;

    public LegacyAuthPolicyChecker() {
        // Deprecated class usage
        this.policy = Policy.getPolicy();
    }

    public boolean isAccessAllowed(Subject subject, CodeSource codeSource) {
        // Create ProtectionDomain with dummy data
        ProtectionDomain pd = new ProtectionDomain(codeSource, null);
        PermissionCollection perms = policy.getPermissions(subject, pd);

        // For demo: print permissions
        perms.elements().asIterator().forEachRemaining(perm ->
            System.out.println("Permission: " + perm)
        );

        // Fake logic: just return true if permissions exist
        return perms.elements().hasMoreElements();
    }
}
