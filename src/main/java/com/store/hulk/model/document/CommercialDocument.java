
package com.store.hulk.model.document;

import com.store.hulk.listener.CommercialDocumentListener;
import com.store.hulk.model.audit.AuditModel;
import com.store.hulk.model.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners({CommercialDocumentListener.class})
public class CommercialDocument extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Customer customer;

    private Long consecutive;

    @ManyToOne
    private TypeDocument typeDocument;

    private BigDecimal totalValue;

    private String totalLetters;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<CommercialDocumentDetail> details;

}
