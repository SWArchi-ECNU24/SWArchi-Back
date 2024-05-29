package sw.archi.commonutils.struct;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeyTriple {

    private String keyName;

    private Class<?> className;

    private Object keyValue;

    public KeyTriple(String keyName, Class<?> className, Object keyValue) {
        this.keyName = keyName;
        this.className = className;
        this.keyValue = keyValue;
    }
}
