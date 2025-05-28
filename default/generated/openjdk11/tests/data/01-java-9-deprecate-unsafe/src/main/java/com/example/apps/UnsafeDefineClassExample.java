import sun.misc.Unsafe;
import java.lang.reflect.Field;

public class UnsafeDefineClassExample {
    public static void main(String[] args) throws Exception {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        byte[] classBytes; // your compiled class bytecode
        Class<?> clazz = unsafe.defineClass(
            "com.example.MyClass", // class name
            classBytes,           // bytecode of the class
            0,                    // offset in the byte array
            classBytes.length,    // length of the byte array
            null,                 // protection domain (null for no restrictions)
            null                  // class loader (null for bootstrap loader)
        );
        Object instance = clazz.getDeclaredConstructor().newInstance();
        System.out.println("Class defined via Unsafe: " + clazz.getName());
    }
}
