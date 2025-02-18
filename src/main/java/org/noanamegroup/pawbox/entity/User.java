package org.noanamegroup.pawbox.entity;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import jakarta.persistence.*;
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
    @TableId(value = "user_id", type = IdType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Box> sentBoxes = new ArrayList<>();

    // 用户接收到的盒子（多对多）
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_received_boxes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "box_id")
    )
    private List<Box> receivedBoxes = new ArrayList<>();

}