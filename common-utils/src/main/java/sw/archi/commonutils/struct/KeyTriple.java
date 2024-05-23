package sw.archi.commonutils.struct;

public class KeyTriple {

    private String keyName;

    private Class<?> className;

    private Object keyValue;

    KeyTriple(String keyName, Class<?> className, Object keyValue) {
        this.keyName = keyName;
        this.className = className;
        this.keyValue = keyValue;
    }

    public String getKeyName() {
        return this.keyName;
    }

    public Class<?> getClassName() {
        return this.className;
    }

    public Object getKeyValue() {
        return this.keyValue;
    }
}
