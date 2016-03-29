package moc5.canteenchecker;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.NumberFormat;

/**
 * Created by Roman on 31.10.2015.
 */
public class CanteenDetailsActivity extends Activity {

    private CanteenDetails canteen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canteendetails);

        ActionBar actionBar = getActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.canteendetailsmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.mnuCall).setVisible(canteen != null && canteen.getPhone()!= null);
        menu.findItem(R.id.mnuShowWebsite).setVisible(canteen != null && canteen.getHomepage() != null);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.mnuCall:
                if(canteen != null && canteen.getPhone() != null) {
                    startActivity(new Intent(Intent.ACTION_CALL,
                            Uri.parse("tel:"+canteen.getPhone())));

                }
                return true;
            case R.id.mnuShowWebsite:
                if(canteen != null && canteen.getHomepage() != null) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(canteen.getHomepage())));
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        canteen = null;

        updateUI();
        int canteenId = getIntent().getIntExtra("canteenId",0);
        new AsyncTask<Integer, Object, CanteenDetails>() {

            protected CanteenDetails doInBackground(Integer... parameters) {
                Log.i(CanteenDetailsActivity.this.toString(), String.format("Loading details for canteen, id %d", parameters[0]));
                try {
                    return new ServiceProxy().getCanteenDetails(parameters[0]);
                } catch (ServiceCallException e) {
                    Log.e(CanteenDetailsActivity.this.toString(), "Failed to laod canteen details", e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(CanteenDetails canteenDetails) {
                if(canteenDetails != null) {
                    canteen = canteenDetails;
                    updateUI();
                    invalidateOptionsMenu();
                }
            }
        }.execute(canteenId);
    }

    private void updateUI() {
        View incSetMail1 = findViewById(R.id.incSetMeal1);
        View incSetMail2 = findViewById(R.id.incSetMeal2);

        setTitle("");
        incSetMail1.setVisibility(View.GONE);
        incSetMail2.setVisibility(View.GONE);
        FragmentManager fragmentManager = getFragmentManager();
        GoogleMap map = ((MapFragment)fragmentManager.findFragmentById(R.id.fragmentMap)).getMap();

        if(canteen != null) {
            setTitle(canteen.getName());
            updateSetMealDetails(incSetMail1, canteen.getSetMeal1());
            updateSetMealDetails(incSetMail2, canteen.getSetMeal2());
            if(map != null) {
                LatLng location = new LatLng(canteen.getLatitude(),canteen.getLongitude());
                map.addMarker(new MarkerOptions()
                                        .position(location)
                                        .title(canteen.getName()));
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(location,17));
            }
        }




    }

    private void updateSetMealDetails(View mealView, SetMeal meal) {
        mealView.setVisibility(meal != null ? View.VISIBLE : View.GONE);
        if(meal != null) {
            ((TextView)mealView.findViewById(R.id.txvMainCourse)).setText(meal.getMainCourse());
            ((TextView)mealView.findViewById(R.id.txvPrice)).setText(NumberFormat.getCurrencyInstance().format(meal.getPrice()));
        }
    }
}
