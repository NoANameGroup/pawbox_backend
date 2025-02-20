package org.noanamegroup.pawbox.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName(value = "pet")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @TableId(value = "pet_id", type = IdType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer petId;

    @TableField(value = "name")
    @Column(name = "name", nullable = false)
    private String name;

    @TableField(value = "avatar_url")
    @Column(name = "avatar_url")
    private String avatarUrl;

    @TableField(value = "birthday")
    @Column(name = "birthday")
    private LocalDateTime birthday;

    @TableField(value = "adopt_time")
    @Column(name = "adopt_time", nullable = false)
    private LocalDateTime adoptTime;

    // 宠物的主人（多对一关系）
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;
} 