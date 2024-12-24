package com.crm.interlinecrm.models.entity;

import com.crm.interlinecrm.models.vo.Address;
import com.crm.interlinecrm.models.vo.Name;
import com.crm.interlinecrm.models.vo.Phone;
import com.crm.interlinecrm.models.vo.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class UserTest {

    private User testUser;
    private User testUserAdmin;

    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        passwordEncoder = mock(PasswordEncoder.class);

        testUser = new User(
                UserId.generate(),
                "testUser1",
                new Name("testName"),
                new Phone("testPhone"),
                new Address("testAddress")
        );

        testUser.changePassword("1111", passwordEncoder);

        testUserAdmin = new User(
                UserId.generate(),
                "testUser1",
                new Name("testName"),
                new Phone("testPhone"),
                new Address("testAddress")
        );

        testUserAdmin.changePassword("1234", passwordEncoder);

        testUserAdmin.roleToAdmin();
    }


    @Test
    @DisplayName("Test isAdmin")
    void isAdminTest(){
        boolean user1 = testUser.isAdmin();
        boolean admin1 = testUserAdmin.isAdmin();

        assertThat(user1).isFalse();
        assertThat(admin1).isTrue();
    }

    @Test
    @DisplayName("Test changePassword")
    void changePassword(){
//        testUser.changePassword("2222", passwordEncoder);
//        testUserAdmin.changePassword("3333", passwordEncoder);

        boolean changedUser1 = testUser.checkPassword("1111", passwordEncoder);
        boolean changedAdmin0 = testUserAdmin.checkPassword("1234", passwordEncoder);

//        System.out.println(changedUser1);
//        System.out.println(changedAdmin0);

//        assertThat(changedUser1).isTrue();
//        assertThat(changedAdmin0).isTrue();

    }

    @Test
    @DisplayName("Test changeBasicInfo")
    void changeBasicInfo(){
        testUser.changeBasicInfo(
                new Name("chagedName"),
                new Phone("chagedPhone"),
                new Address("chagedAddress")
        );

        assertThat(testUser.name().toString()).isEqualTo("chagedName");
        assertThat(testUser.phone().toString()).isEqualTo("chagedPhone");
        assertThat(testUser.address().toString()).isEqualTo("chagedAddress");
    }

}