package it.naar.quiz.common;

import java.util.UUID;

//@MappedSuperclass
public abstract class BaseEntitySupport {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public Long getId() {
        return id;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof BaseEntitySupport other)) {
            return false;
        }
        if (this.id == null || other.id == null) {
            return false;
        }
        return this.id.equals(other.id);
    }

    @Override
    public final int hashCode() {
        return this.id == null ? System.identityHashCode(this) : this.id.hashCode();
    }
}
