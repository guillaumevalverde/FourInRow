package com.gvestyle;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import com.facebook.android.*;
import com.facebook.android.Facebook.*;

public class FourInRowActivity extends Activity {

	private static boolean ENABLE_LOG = true;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourinrow);
        
        facebook.authorize(this, new DialogListener() {
            @Override
            public void onComplete(Bundle values) {}

            @Override
            public void onFacebookError(FacebookError error) {}

            @Override
            public void onError(DialogError e) {}

            @Override
            public void onCancel() {}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fourinrow, menu);
        return true;
    }

    
    Facebook facebook = new Facebook("336401423119965");

   

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        facebook.authorizeCallback(requestCode, resultCode, data);
    }
}
    
    
    

