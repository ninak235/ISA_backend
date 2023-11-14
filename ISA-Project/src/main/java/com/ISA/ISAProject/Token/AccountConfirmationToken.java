package com.ISA.ISAProject.Token;

import com.ISA.ISAProject.Model.User;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "ConfirmationToken")
public class AccountConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long tokenId;

    @Column(name ="confirmationToken")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public AccountConfirmationToken() {
    }
    public AccountConfirmationToken(User user) {
        this.user = user;
        this.createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }


    public Long getTokenId() {
        return tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
