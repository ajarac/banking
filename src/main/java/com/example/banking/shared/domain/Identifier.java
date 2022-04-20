package com.example.banking.shared.domain;

import java.util.Objects;
import java.util.UUID;

public class Identifier {
    private final String id;

    public Identifier(String id) {
        this.id = id;
    }

    public static Identifier Create() {
        return new Identifier(UUID.randomUUID().toString());
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identifier that = (Identifier) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
