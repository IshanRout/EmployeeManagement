package com.main.model;

import javax.persistence.*;
import java.util.Collection;
//entity class for the user's detail to give roal
// the unique key here added is email
@Entity
@Table(name = "user" ,uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserEntity {


        @Id
        @GeneratedValue(strategy =  GenerationType.IDENTITY)
        private Long id;

        @Column(name = "first_name")
        private String firstName;

        @Column(name = "last_name")
        private String lastName;

        private String email;

        private String password;

        @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinTable(
                name = "users_roles",
                joinColumns = @JoinColumn(
                        name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(
                        name = "role_id", referencedColumnName = "id"))

        private Collection<Role> roles;

        public UserEntity() {

        }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity(String firstName, String lastName, String email, String password, Collection<Role> roles) {
            super();
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.roles = roles;
        }
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
            this.email = email;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        public Collection<Role> getRoles() {
            return roles;
        }
        public void setRoles(Collection<Role> roles) {
            this.roles = roles;
        }


}

