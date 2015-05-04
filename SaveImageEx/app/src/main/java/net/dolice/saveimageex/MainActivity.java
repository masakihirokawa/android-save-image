package net.dolice.saveimageex;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import net.dolice.image.ImageManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 画像保存ボタン定義
        Button sampleButton = (Button)findViewById(R.id.button);
        sampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 画像保存
                saveImage();
            }
        });
    }

    // 画像保存
    private void saveImage() {
        // イメージビューからビットマップ保持
        ImageView sampleImageView = (ImageView)findViewById(R.id.image);
        Bitmap sampleImage = ((BitmapDrawable)sampleImageView.getDrawable()).getBitmap();

        // ビットマップを SDカードに保存
        ImageManager imageManager = new ImageManager(this);
        try {
            // 画像の保存実行
            String albumName = "Save image sample";
            imageManager.save(sampleImage, albumName);
        } catch (Error e) {
            Log.e("MainActivity", "onCreate: " + e);

            // 画像の保存失敗メッセージ表示
            Toast.makeText(MainActivity.this, "SDカードに保存できませんでした", Toast.LENGTH_SHORT).show();
        } finally {
            // 画像の保存完了メッセージ表示
            Toast.makeText(MainActivity.this, "SDカードに保存しました", Toast.LENGTH_SHORT).show();
        }
    }
}
