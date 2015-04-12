package uk.co.jemos.podam.api;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Contains all information to execute a method
 */
public class ExtraMethodExecutorData implements Serializable {

    private final long serialVersionUID = 1L;

    private final Method method;

    private final Object target;

    private final Object[] methodArgs;


    /**
     * Full constructor.
     * @param method The method name
     * @param target The instance on which the method will be invoked
     * @param args The list of args that will be passed to the method
     */
    public ExtraMethodExecutorData(Method method, Object target, Object[] args) {
        this.method = method;
        this.target = target;
        this.methodArgs = args;
    }


    public Method getMethod() {
        return method;
    }

    public Object getTarget() {
        return target;
    }

    public Object[] getMethodArgs() {
        return methodArgs;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExtraMethodExecutorData that = (ExtraMethodExecutorData) o;

        if (!getMethod().equals(that.getMethod())) return false;
        if (!getTarget().equals(that.getTarget())) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(getMethodArgs(), that.getMethodArgs());

    }

    @Override
    public int hashCode() {
        int result = getMethod().hashCode();
        result = 31 * result + getTarget().hashCode();
        result = 31 * result + Arrays.hashCode(getMethodArgs());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExtraMethodExecutorData{");
        sb.append("methodName='").append(method).append('\'');
        sb.append(", target=").append(target);
        sb.append(", methodArgs=").append(Arrays.toString(methodArgs));
        sb.append('}');
        return sb.toString();
    }
}
