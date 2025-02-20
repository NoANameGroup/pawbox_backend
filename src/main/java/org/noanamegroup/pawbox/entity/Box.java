package org.noanamegroup.pawbox.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName(value = "box")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Box {
    @Id
    @TableId(value = "box_id", type = IdType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boxId;

    @TableField(value = "content")
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @TableField(value = "image_url")
    @Column(name = "image_url")
    private String imageUrl;

    @TableField(value = "create_time")
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    // 发送该盒子的用户（多对一关系）
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

    // 接收该盒子的用户（多对多关系）
    @ManyToMany(mappedBy = "receivedBoxes", fetch = FetchType.LAZY)
    private List<User> receivers = new ArrayList<>();
}
