package com.crm.interlinecrm.models.entity;

import com.crm.interlinecrm.models.vo.Address;
import com.crm.interlinecrm.models.vo.CustomerId;
import com.crm.interlinecrm.models.vo.Name;
import com.crm.interlinecrm.models.vo.Phone;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
public class Customer {

    @EmbeddedId
    private CustomerId id;

    @Embedded
    private Name name;

    @Embedded
    private Phone phone;

    @Embedded
    private Address address;

    @Column(name = "title")
    private String title;

    @Column(name = "memo")
    private String memo;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    private Customer(){}

    public Customer(CustomerId customerId, Name name, Phone phone, Address address, String title, String memo){
        this.id = customerId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.title = title;
        this.memo = memo;
    }

    public CustomerId customerId(){ return this.id; }
    public Name name(){return this.name;}

    public boolean isSame(CustomerId customerId){
        return this.id.equals(customerId);
    }


    public void changeCustomer(Name name, Phone phone, Address address, String title, String memo){
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.title = title;
        this.memo = memo;
    }

}
