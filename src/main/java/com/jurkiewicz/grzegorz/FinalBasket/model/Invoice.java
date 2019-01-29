package com.jurkiewicz.grzegorz.FinalBasket.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "invoice")
public class Invoice {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "invoice_id")
        private Long id;

        @NotNull
        @Column(name = "amount")
        private int amount;

        @NotNull
        @Column(name = "status")
        private String status;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        public Invoice(@NotNull int amount, @NotNull String status, User user) {
            this.amount = amount;
            this.status = status;
            this.user = user;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", user=" + user +
                '}';
    }
}
