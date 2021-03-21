package entity;

import java.util.StringJoiner;

public abstract class PrototypeEntity {
    private int contextId = 0;
    private String name;

    public PrototypeEntity(String name) {
        this.name = name;
    }

    public int getContextId() {
        return contextId;
    }

    public void setContextId(int contextId) {
        this.contextId = contextId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PrototypeEntity.class.getSimpleName() + "[", "]")
                .add("contextId=" + contextId)
                .add("name='" + name + "'")
                .toString();
    }
}
