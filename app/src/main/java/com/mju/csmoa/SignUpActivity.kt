package com.mju.csmoa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import com.mju.csmoa.SignInActivity
import android.text.TextWatcher
import android.text.Editable
import androidx.core.content.ContextCompat
import com.mju.csmoa.R
import com.mju.csmoa.CustomBottomSheetDialog
import com.mju.csmoa.DialogButtonDelegate
import android.content.DialogInterface
import com.mju.csmoa.databinding.ActivitySignUpBinding
import java.util.*

class SignUpActivity : AppCompatActivity() {
    private var binding: ActivitySignUpBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        init()
    }

    private fun init() {

        // '이미 계정이 있으신가요? 로그인'을 눌렀을 때
        binding!!.textViewSignUpSignIn.setOnClickListener { v: View? ->
            startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
            finish()
        }

        // 닉네임 중복 처리 (Debounce)
        // TODO

        // 아이디(이메일) 중복 처리 (Debounce)
        // TODO

        // 비밀번호 확인
        binding!!.textInputEditTextSignUpConfirmPasswordInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // 비밀번호랑 비밀번호 확인이랑 일치할 때
                if (Objects.requireNonNull(binding!!.textInputEditTextSignUpPasswordInput.text).toString().trim { it <= ' ' }
                        == Objects.requireNonNull(binding!!.textInputEditTextSignUpConfirmPasswordInput.text).toString()) {
                    binding!!.textInputEditTextSignUpConfirmPasswordInput.setCompoundDrawablesWithIntrinsicBounds(
                            null, null, ContextCompat.getDrawable(this@SignUpActivity, R.drawable.ic_all_checked), null)
                    binding!!.textInputLayoutSignUpConfirmPasswordInputLayout.error = null
                } else {
                    binding!!.textInputLayoutSignUpConfirmPasswordInputLayout.error = "비밀번호가 서로 일치하지 않습니다"
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })

        // click signUp button
        binding!!.buttonSignUpSignUp.setOnClickListener { v: View? -> }
    }

    override fun onBackPressed() {
        val bottomSheetDialog = CustomBottomSheetDialog
                .builder()
                .context(this@SignUpActivity)
                .theme(R.style.BottomSheetDialogTheme)
                .lottieName("man_question.json")
                .title("Exit?")
                .message("회원가입을 중단하실 건가요?\n중간에 나가시면 기존 정보는 저장되지 않아요 :(")
                .build()

        // dirty code (builder에서 전달해서 처리하고 싶은데, super.onBackPressed()가 안 먹는다)
        bottomSheetDialog.setOnClickListener(object : DialogButtonDelegate {
            override fun setOnYesClickedListener(dialogInterface: DialogInterface) {
                dialogInterface.dismiss()
                super@SignUpActivity.onBackPressed()
            }

            override fun setOnNoClickedListener(dialogInterface: DialogInterface) {
                dialogInterface.dismiss()
            }
        })
        bottomSheetDialog.show()
    }
}