package com.example.sampleapp.repository

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * SpringBootとJPAを使ったEntityクラス
 * @Entity：Entityクラスであることを宣言する
 * @Table：name属性で連携するテーブル名を指定する
 */
@Entity
@Table(name = "M_USER")
data class Muser(
    // Table定義は以下の通り
    // create table M_USER (
    //   USER_ID varchar(32) not null primary key,
    //   USER_NAME varchar(64) not null,
    //   PASSWORD varchar(128) not null
    // );
    // INSERT INTO M_USER VALUES ('user1', 'ユーザ1','{bcrypt}$2a$10$xeYLBfOQILT1XKYhofosg.a3I1Vg8vF6Kd4NXjfigyy/.N.7AwYU.');
    // INSERT INTO M_USER VALUES ('user2', 'ユーザ2','{bcrypt}$2a$10$142YrOgdho1EvrXhstuYMuD.6l5XrJt4yyJ6t6kcJLi7bHvDzpF3O');
    // INSERT INTO M_USER VALUES ('user3', 'ユーザ3','{bcrypt}$2a$10$WlijXJStltiGmakfhoRBQuMy2Xlw6EOtnbrMQRg65tlF0aU5y2.7i');

    // 登録されるユーザー
    // - user1/password1
    // - user2/password2
    // - user3/password3

    /**
     *  @Id：主キーに指定する。※複合キーの場合は@EmbeddedIdを使用
     *  @Column：name属性でマッピングするカラム名を指定する
     */
    @Id
    @Column(name="USER_ID")
    var id: String,

    @Column(name="USER_NAME")
    var name: String,

    @Column(name="PASSWORD")
    var password: String,
)
