package com.epam.esm.model.entity;

import com.epam.esm.repository.CertificateColumnName;
import com.epam.esm.repository.TableName;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = TableName.TABLE_CERTIFICATES)
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = CertificateColumnName.ID)
    @Getter
    @Setter
    private Long id;

    @Column(name = CertificateColumnName.PRICE)
    @Getter
    @Setter
    private Double price;

    @Column(name = CertificateColumnName.DURATION)
    @Getter
    @Setter
    private Integer duration;

    @Column(name = CertificateColumnName.NAME)
    @Getter
    @Setter
    private String name;

    @Column(name = CertificateColumnName.DESCRIPTION)
    @Getter
    @Setter
    private String description;

    @Column(name = CertificateColumnName.CREATE_DATE)
    @Getter
    @Setter
    private Timestamp createDate;

    @Column(name = CertificateColumnName.LAST_UPDATE_DATE)
    @Getter
    @Setter
    private Timestamp lastUpdateDate;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinTable(name = "certificate_tags",
            joinColumns = @JoinColumn(name = "certificate_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @Getter
    @Setter
    private Set<Tag> tags = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinTable(name = "certificates_orders",
            joinColumns = @JoinColumn(name = "certificate_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    @Getter
    @Setter
    private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Certificate that = (Certificate) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (lastUpdateDate != null ? !lastUpdateDate.equals(that.lastUpdateDate) : that.lastUpdateDate != null)
            return false;
        if (tags != null ? !tags.equals(that.tags) : that.tags != null) return false;
        return order != null ? order.equals(that.order) : that.order == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (lastUpdateDate != null ? lastUpdateDate.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Certificate{");
        sb.append("id=").append(id);
        sb.append(", price=").append(price);
        sb.append(", duration=").append(duration);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", createDate=").append(createDate);
        sb.append(", lastUpdateDate=").append(lastUpdateDate);
        sb.append(", tags=").append(tags);
        sb.append(", order=").append(order);
        sb.append('}');
        return sb.toString();
    }
}
