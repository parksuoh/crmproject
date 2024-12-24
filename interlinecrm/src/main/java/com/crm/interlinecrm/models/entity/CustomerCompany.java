package com.crm.interlinecrm.models.entity;


import com.crm.interlinecrm.models.vo.*;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Entity
@Table(name = "customer_company")
public class CustomerCompany {

    @EmbeddedId
    private CustomerCompanyId id;

    @Column(name = "co_name")
    private String companyName;

    @Embedded
    private Name boss;

    @Embedded
    private Phone phone;

    @Embedded
    private Address address;

    @Column(name = "memo")
    private String memo;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "company_id")
    private List<Customer> customers = new ArrayList<>();

    private CustomerCompany(){}

    public CustomerCompany(CustomerCompanyId customerCompanyId, String companyName, Name boss, Phone phone, Address address, String memo){
        this.id = customerCompanyId;
        this.companyName = companyName;
        this.boss = boss;
        this.phone = phone;
        this.address = address;
        this.memo = memo;
    }

    public CustomerCompanyId customerCompanyId(){return this.id;}
    public String companyName(){ return this.companyName; }
    public Name boss(){ return this.boss; }
    public Phone phone(){ return this.phone; }
    public Address address(){ return this.address; }
    public String memo(){ return this.memo; }


    public void changeCompanyInfo(String companyName, Name boss, Phone phone, Address address, String memo){
        this.companyName = companyName;
        this.boss = boss;
        this.phone = phone;
        this.address = address;
        this.memo = memo;
    }

    private static Customer createCustomer(Name name, Phone phone, Address address, String title, String memo) {
        CustomerId customerId = CustomerId.generate();
        return new Customer(customerId, name, phone, address, title, memo);
    }

    public Customer addCustomer(Name name, Phone phone, Address address, String title, String memo){
        Customer customer = createCustomer(name, phone, address, title, memo);
        customers.add(customer);
        return customer;
    }

    public Optional<Customer> findCustomer(CustomerId customerId){
        return customers.stream()
                .filter(customer -> customer.isSame(customerId))
                .findFirst();
    }

    public void changeCustomer(
            CustomerId customerId,
            Name name,
            Phone phone,
            Address address,
            String title,
            String memo
    ){
        Customer changedCustomer = findCustomer(customerId).orElseThrow();
        changedCustomer.changeCustomer(name, phone, address, title, memo);
        customers.removeIf(c -> c.isSame(customerId));
        customers.add(changedCustomer);
    }

    public int customersSize() {
        return customers.size();
    }
}
