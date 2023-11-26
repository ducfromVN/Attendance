package android.bignerdranch.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityRegister extends AppCompatActivity {
    EditText etUser,etPwd,etRepwd;
    Button btnRegister,btnGotoLogin;
    DbHelper2 dbHelper2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUser = findViewById(R.id.etUsername);
        etPwd = findViewById(R.id.etPassword);
        etRepwd = findViewById(R.id.etRePassword);
        btnRegister = findViewById(R.id.btnRegister);
        dbHelper2 = new DbHelper2(this);
        btnGotoLogin = findViewById(R.id.btnLogin);
        btnGotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityRegister.this, ActivityLogin.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user, pwd, rePwd;
                user = etUser.getText().toString();
                pwd = etPwd.getText().toString();
                rePwd = etRepwd.getText().toString();
                if (user.equals("") || pwd.equals("") || rePwd.equals("")){
                    Toast.makeText(ActivityRegister.this, "Hãy điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (pwd.equals(rePwd)){
                        if(dbHelper2.checkUsername(user)){
                            Toast.makeText(ActivityRegister.this, "Người dùng đã tồn tại", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        boolean registeredSuccess = dbHelper2.insertData(user, pwd);
                        if (registeredSuccess)
                            Toast.makeText(ActivityRegister.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(ActivityRegister.this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ActivityRegister.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
