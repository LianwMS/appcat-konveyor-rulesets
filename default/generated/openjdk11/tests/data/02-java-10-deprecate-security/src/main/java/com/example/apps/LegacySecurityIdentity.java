import java.security.Identity;
import java.security.IdentityScope;
import java.security.Signer;
import java.security.PublicKey;
import java.security.Principal;
import java.security.cert.Certificate; // java.security.Certificate is abstract and deprecated

// Dummy class extending deprecated Identity
public class LegacySecurityIdentity extends Identity {

    public LegacySecurityIdentity(String name) {
        super(name);
    }

    @Override
    public void addCertificate(Certificate certificate) {
        // Deprecated usage
        System.out.println("Adding certificate: " + certificate);
        super.addCertificate(certificate);
    }

    public static class LegacySigner extends Signer {
        public LegacySigner(String name) {
            super(name);
        }

        @Override
        public void setKeyPair(java.security.KeyPair pair) {
            System.out.println("Setting KeyPair");
            super.setKeyPair(pair); // deprecated usage
        }
    }

    public static class LegacyIdentityScope extends IdentityScope {

        public LegacyIdentityScope(String name) {
            super(name);
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public Identity getIdentity(String name) {
            return null;
        }

        @Override
        public Identity getIdentity(PublicKey key) {
            return null;
        }

        @Override
        public Identity getIdentity(Principal principal) {
            return null;
        }

        @Override
        public Enumeration<Identity> identities() {
            return java.util.Collections.emptyEnumeration();
        }
    }
}
