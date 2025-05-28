import com.sun.security.auth.PolicyFile;
import com.sun.security.auth.SolarisNumericGroupPrincipal;
import com.sun.security.auth.SolarisNumericUserPrincipal;
import com.sun.security.auth.SolarisPrincipal;
import com.sun.security.auth.X500Principal;
import com.sun.security.auth.module.SolarisLoginModule;
import com.sun.security.auth.module.SolarisSystem;

import javax.security.auth.Subject;
import java.util.HashMap;
import java.util.Map;

import java.security.Certificate;
import java.security.Identity;
import java.security.IdentityScope;
import java.security.Signer;
import java.security.KeyPairGenerator;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.NoSuchAlgorithmException;

import java.lang.SecurityManager;

public class DeprecatedSecurityManagerUsage {

    public static void main(String[] args) {
        // Get the system security manager
        SecurityManager sm = System.getSecurityManager();

        if (sm != null) {
            // ❌ 1. Accessing deprecated field
            boolean isInCheck = sm.inCheck;
            System.out.println("inCheck: " + isInCheck);

            // ❌ 2. Using deprecated getInCheck method
            boolean check = sm.getInCheck();
            System.out.println("getInCheck: " + check);

            // ❌ 3. Using classDepth to check class stack
            int depth = sm.classDepth("java.lang.String");
            System.out.println("classDepth: " + depth);

            // ❌ 4. Using classLoaderDepth
            int loaderDepth = sm.classLoaderDepth();
            System.out.println("classLoaderDepth: " + loaderDepth);

            // ❌ 5. Get current class loader
            ClassLoader cl = sm.currentClassLoader();
            System.out.println("currentClassLoader: " + cl);

            // ❌ 6. Get currently loaded class
            Class<?> loadedClass = sm.currentLoadedClass();
            System.out.println("currentLoadedClass: " + loadedClass);

            // ❌ 7. Check if in a specific class
            boolean inClass = sm.inClass("java.lang.String");
            System.out.println("inClass: " + inClass);

            // ❌ 8. Check if in class loader
            boolean inCL = sm.inClassLoader();
            System.out.println("inClassLoader: " + inCL);

            // ⚠️ 9. Using checkMemberAccess (now throws unless AllPermission)
            try {
                sm.checkMemberAccess(String.class, java.lang.reflect.Member.PUBLIC);
                System.out.println("checkMemberAccess passed.");
            } catch (SecurityException e) {
                System.out.println("checkMemberAccess blocked.");
            }
        } else {
            System.out.println("No SecurityManager installed.");
        }


        // Example 1: SolarisPrincipal
        SolarisPrincipal principal = new SolarisPrincipal("username");
        System.out.println("SolarisPrincipal: " + principal.getName());

        // Example 2: SolarisNumericUserPrincipal
        SolarisNumericUserPrincipal userPrincipal = new SolarisNumericUserPrincipal(1000);
        System.out.println("SolarisNumericUserPrincipal: " + userPrincipal.getName());

        // Example 3: SolarisNumericGroupPrincipal
        SolarisNumericGroupPrincipal groupPrincipal = new SolarisNumericGroupPrincipal(2000, true);
        System.out.println("SolarisNumericGroupPrincipal: " + groupPrincipal.getName());

        // Example 4: X500Principal (com.sun.security.auth version, NOT javax)
        X500Principal x500 = new X500Principal("CN=Test, OU=Dev, O=Example, L=City, ST=State, C=US");
        System.out.println("X500Principal: " + x500.getName());

        // Example 5: SolarisSystem info
        SolarisSystem solarisSystem = new SolarisSystem();
        System.out.println("User name from SolarisSystem: " + solarisSystem.getUsername());

        // Example 6: SolarisLoginModule
        SolarisLoginModule loginModule = new SolarisLoginModule();
        Subject subject = new Subject();
        Map<String, Object> sharedState = new HashMap<>();
        Map<String, Object> options = new HashMap<>();

        loginModule.initialize(subject, null, sharedState, options);
        System.out.println("Initialized SolarisLoginModule");

        // Example 7: PolicyFile
        PolicyFile policy = new PolicyFile();
        System.out.println("PolicyFile loaded: " + policy);
    
        LegacySecurityIdentity identity = new LegacySecurityIdentity("legacyUser");
        System.out.println("Created identity: " + identity.getName());

        LegacySecurityIdentity.LegacyIdentityScope scope = new LegacySecurityIdentity.LegacyIdentityScope("legacyScope");
        System.out.println("Scope size: " + scope.size());

        identity.addCertificate(cert);
        System.out.println("Added dummy certificate to identity.");

        Principal owner = new UserPrincipal("admin");
        LegacyAclManager acl = new LegacyAclManager("TestACL", owner);

        System.out.println(acl);

        LegacyAuthPolicyChecker checker = new LegacyAuthPolicyChecker();
        boolean allowed = checker.isAccessAllowed(new Subject(), new CodeSource(null, (java.security.cert.Certificate[]) null));
        System.out.println("Access allowed: " + allowed);
        
    }
}
