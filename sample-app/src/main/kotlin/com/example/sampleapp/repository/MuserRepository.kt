package com.example.sampleapp.repository

import org.springframework.data.jpa.repository.JpaRepository

/**
 * SpringBootとJPAを使ったRepositoryクラス
 * Spring Data JPAで提供されているAPIではできない処理を記述する
 */
interface MuserRepository : JpaRepository<Muser, String> {
    // テーブルへアクセスするための基本的な処理はJPAが用意してくれているのでSQLを書かなくてよい
    // JPAのJpaRepositoryを継承したinterfaceを作成することで利用できる
    //  - findAll()など基本的なメソッドは持っている
    //  - ex.fun findByName(name: String): Muser?
}
