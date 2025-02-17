package com.noanamegroup.boxbuddy.entity;

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
    @Column(name = "username", nullable = false)
    private String username;

    @TableField(value = "password")
    @Column(name = "password", nullable = false)
    private String password;

    @TableField(value = "email")
    @Column(name = "email", nullable = false)
    private String email;
}