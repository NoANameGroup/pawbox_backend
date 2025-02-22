package org.noanamegroup.pawbox.entity;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName(value = "user")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        }
)
public class User
{
    @Id
    @TableId(value = "userId", type = IdType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Integer userId;

    @TableField(value = "username")
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @TableField(value = "password")
    @Column(name = "password", nullable = false)
    private String password;

    @TableField(value = "email")
    @Column(name = "email", nullable = false)
    private String email;

    // 用户创建的盒子（一对多）
    @TableField(exist = false)
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Box> sentBoxes = new ArrayList<>();

    // 用户接收到的盒子（多对多）
    @TableField(exist = false)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_received_boxes",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "boxId")
    )
    @JsonIgnore
    private List<Box> receivedBoxes = new ArrayList<>();

    // 用户的宠物（一对一）
    @TableField(exist = false)
    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Pet pet;

}