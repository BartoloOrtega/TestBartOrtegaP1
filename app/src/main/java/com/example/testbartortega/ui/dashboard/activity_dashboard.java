package com.example.testbartortega.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testbartortega.R;
import com.example.testbartortega.data.model.User;
import com.example.testbartortega.utils.JWTUtils;
import com.google.gson.Gson;
import com.google.zxing.WriterException;
import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class activity_dashboard extends AppCompatActivity {

    TextView textUsuario, textCode, textNombreCompleto;
    ImageView imgCode;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        textUsuario=findViewById(R.id.textViewSaludo);
        textNombreCompleto=findViewById(R.id.textviewNombreCompleto);
        textCode=findViewById(R.id.textviewCode);
        String tokenResult = getIntent().getStringExtra("token");
        String tokenJson=JWTUtils.decoded(tokenResult);
        User usrObj= Gson().fromJson(tokenJson,User::class.java)

        /*
        * {
              "sub": "1234567890",
              "name": "John Doe",
              "iat": 1516239022
            }
        *
        * */
        textUsuario.setText(getString(R.string.msg_welcome)+" "+ usrObj.getTitular());
        textCode.setText(tokenResult);
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // initializing a variable for default display.
        Display display = manager.getDefaultDisplay();

        // creating a variable for point which
        // is to be displayed in QR Code.
        Point point = new Point();
        display.getSize(point);

        // getting width and
        // height of a point
        int width = point.x;
        int height = point.y;

        // generating dimension from width and height.
        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;

        // setting this dimensions inside our qr code
        // encoder to generate our qr code.
        qrgEncoder = new QRGEncoder(tokenResult, null, QRGContents.Type.TEXT, dimen);
        try {
            // getting our qrcode in the form of bitmap.
            bitmap = qrgEncoder.encodeAsBitmap();
            // the bitmap is set inside our image
            // view using .setimagebitmap method.
            imgCode.setImageBitmap(bitmap);








    }
}