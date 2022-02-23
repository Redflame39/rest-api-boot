package com.epam.esm.model.entity;

import com.epam.esm.repository.TableName;
import com.epam.esm.repository.TagColumnName;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = TableName.TABLE_TAGS)
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(name = TagColumnName.NAME)
    @Getter
    @Setter
    private String name;

    @ManyToMany(mappedBy = "tags",
            cascade = CascadeType.ALL)
    @Getter
    @Setter
    private Set<Certificate> certificates;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (!id.equals(tag.id)) return false;
        return name.equals(tag.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tag{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
