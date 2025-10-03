package it.naar.quiz.domain.shared;

import java.util.UUID;

//@MappedSuperclass
public abstract class BaseEntitySupportUUID {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

//    @NotNull
//    @Column(name = "public_uuid", nullable = false, updatable = false)
    protected UUID uuid;

    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

//    @PrePersist
    protected void prePersist() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID();
        }
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof BaseEntitySupportUUID other)) {
            return false;
        }
        if (this.uuid == null || other.uuid == null) {
            return false;
        }
        return this.uuid.equals(other.uuid);
    }

    @Override
    public final int hashCode() {
        return this.uuid == null ? System.identityHashCode(this) : this.uuid.hashCode();
    }
}
