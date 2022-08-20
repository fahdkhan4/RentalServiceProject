package com.example.RentalServiceProject.model;

import com.example.RentalServiceProject.model.enums.InitialStatus;
import lombok.*;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String number;
    private String image;
    private String email;
    private String password;
    private String address;
    private String gender;
    private String type;
    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id") ,
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles = new HashSet<Roles>();
    private String cnic;
    @Enumerated(EnumType.STRING)
    private InitialStatus status = InitialStatus.Published;

//
//    public void addRole(Roles role){
//        this.roles.add(role);
//    }
}
