package com.azericard.insurance.entity;

import com.azericard.insurance.util.PaymentStatus;
import com.azericard.insurance.util.Status;
import com.azericard.insurance.util.Utils;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDateTime registerDate = LocalDateTime.now();
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private int insuranceCost;
    private long numberOfDays;
//    @Enumerated(EnumType.STRING)
    private Status status;
//    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String modifiedBy;
    @ManyToOne
    private Product product;
    @OneToOne
    private Client client;
    private String policyNumber;

    public void setFromDate(LocalDateTime registerDate) {
        this.fromDate = registerDate.plusDays(1);
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber + Utils.randomString();
    }

    public Status buildStatus() {
        if (LocalDateTime.now().getDayOfMonth() == registerDate.getDayOfMonth()) {
            return Status.ACTIVE;
        } else if (LocalDateTime.now().isAfter(toDate)) {
            return Status.DE_ACTIVE;
        } else {
            return Status.IN_PROGRESS;
        }
    }

    public int buildInsuranceCost() {
        if (numberOfDays == 30) {
            return (int) numberOfDays * (int) product.getFirstAmount();
        } else if (numberOfDays == 60) {
            return (int) numberOfDays * (int) product.getSecondAmount();
        } else if (numberOfDays==90){
            return (int) numberOfDays * (int) product.getThirdAmount();
        }else throw new IllegalArgumentException("Number of days of insurance not correct");
    }
}
