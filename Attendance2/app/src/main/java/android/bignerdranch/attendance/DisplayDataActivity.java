package android.bignerdranch.attendance;

import android.bignerdranch.attendance.R;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("scannedData")) {
            String scannedData = intent.getStringExtra("scannedData");

            TextView textView = findViewById(R.id.textView);
            textView.setText(scannedData);

            textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("label", scannedData);
                    clipboardManager.setPrimaryClip(clipData);

                    Toast.makeText(DisplayDataActivity.this, "Đã sao chép dữ liệu", Toast.LENGTH_SHORT).show();

                    return true;
                }
            });
        }
    }
}
