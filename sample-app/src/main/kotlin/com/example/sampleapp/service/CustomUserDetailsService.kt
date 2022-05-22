package com.example.sampleapp.service

import com.example.sampleapp.repository.MuserRepository
import com.example.sampleapp.util.AppUtil
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.Calendar
import java.util.Collections

@Service
class CustomUserDetailsService(
    private val mUserRepository: MuserRepository
) : UserDetailsService {

    // loadUserByUsername
    // - ログイン画面で login される度に実行される
    //      - userId : 入力されたID
    //      - 入力されたパスワードは渡ってこない
    // - userId が 不正な値の場合は throw UsernameNotFoundException() する → /login?error に遷移する
    // - userId が 正しい値の場合は UserDetails クラスを生成して返す
    //      - Userクラスに password と authorities を設定する
    //      - return後 spring が password と authorities を verrify して認証成功の場合は .defaultSuccessUrl("/success") に遷移する
    override fun loadUserByUsername(userId: String?): UserDetails {
        if (userId == null || userId.isEmpty()) {
            throw UsernameNotFoundException("userId is null or empty")
        }

        val mUser = mUserRepository.findById(userId).orElse(null) ?: throw UsernameNotFoundException("Not found userId")

        val loginUser = AppUtil.getLoginUser()
        val authorities = if (loginUser != null) {
            // 再認証のとき
            if (loginUser.username != userId) {
                throw UsernameNotFoundException("userId is invalid")
            }

            // ADMIN権限を付与する
            listOf(
                SimpleGrantedAuthority(AppUtil.Role.ROLE_USER.name),
                SimpleGrantedAuthority(AppUtil.Role.ROLE_ADMIN.name),
            )
        } else {
            // 初回認証のときは USER権限を付与する
            listOf(
                SimpleGrantedAuthority(AppUtil.Role.ROLE_USER.name),
            )
        }

        return User(mUser.id,mUser.password, authorities)
    }
}
