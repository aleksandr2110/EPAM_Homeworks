package com.epam.rd.domain;

import javax.persistence.*;

@Entity
@Table(name="billing_details")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "BD_TYPE")
public class BillingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // constants
    @Column(name="billing_id")
    protected Long id;

    @Column(name="bill_detail_id")
    private Long BillDetailsId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn (name="buyer_id", insertable = false, updatable = false)
    protected Buyer buyer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBillDetailsId() {
        return BillDetailsId;
    }

    public void setBillDetailsId(Long billDetailsId) {
        BillDetailsId = billDetailsId;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
}
