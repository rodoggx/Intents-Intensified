package com.example.rodoggx.intentsintensified;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText websiteText;
    private EditText locationText;
    private EditText shareThisText;

    private Button websiteBtn;
    private Button locationBtn;
    private Button sharebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        websiteText = (EditText) findViewById(R.id.website_text);
        locationText = (EditText) findViewById(R.id.location_text);
        shareThisText = (EditText) findViewById(R.id.share_text);

        websiteBtn = (Button) findViewById(R.id.website_button);
        locationBtn = (Button) findViewById(R.id.location_button);
        sharebtn = (Button) findViewById(R.id.share_button);

        websiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWebsite(view);
            }
        });

        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLocation(view);
            }
        });

        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startShareThis(view);
            }
        });
    }

    public void startWebsite(View view) {
        String website = websiteText.getText().toString();
        Uri webpage = Uri.parse(website);

        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(TAG, "startWebsite: Failed Intent");
        }
    }

    public void startLocation(View view) {
        String location = locationText.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" + location);

        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(TAG, "startLocation: Failed Intent");
        }
    }

    public void startShareThis(View view) {
        String text = shareThisText.getText().toString();
        String mimeType = "text/plain";

        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(text)
                .startChooser();
    }
}
