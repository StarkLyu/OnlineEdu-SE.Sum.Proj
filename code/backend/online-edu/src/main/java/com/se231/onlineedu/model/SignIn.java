package com.se231.onlineedu.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class SignIn {

    @EmbeddedId
    private SignInPrimaryKey signInPrimaryKey;

    private Date startDate;

    private Date endDate;
}
