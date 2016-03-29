package moc5.canteenchecker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import javax.xml.datatype.Duration;

/**
 * Created by Roman on 31.10.2015.
 */
public class CanteenListActivity extends Activity {

    CanteenListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CanteenListAdapter();
        this.setContentView(R.layout.activity_canteenlist);

        Button btnSearch = (Button) findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             updateCanteenList();
                                         }
                                     }
        );
        ListView lsvCanteens =(ListView) findViewById(R.id.lsvCanteens);
        lsvCanteens.setAdapter(adapter);
        lsvCanteens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(CanteenListActivity.this, CanteenDetailsActivity.class);
                intent.putExtra("canteenId",adapter.getItem(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void updateCanteenList() {
        adapter.clear();
        String filter = ((EditText)findViewById(R.id.edtFilter))
                .getText().toString();

        new AsyncTask<String, Object, Canteen[]>() {
            @Override
            protected Canteen[] doInBackground(String... params) {
                Log.i(CanteenListActivity.this.toString(),String.format("Loading canteen list (filter = '%s')",params[0]));
                try {
                    return new ServiceProxy().getCanteens(params[0]);
                    }
                catch (ServiceCallException e){
                    Log.e(CanteenListActivity.this.toString(),"Failed to load canteen list");
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Canteen[] canteens) {
                if(canteens != null) {
                    adapter.addAll(canteens);
                } else {
                    Toast.makeText(CanteenListActivity.this,R.string.couldNotLoadCanteens, Toast.LENGTH_SHORT).show();
                }
            }
        }.execute(filter);
    }

    private class CanteenListAdapter extends ArrayAdapter<Canteen> {

        public CanteenListAdapter() {
            super(CanteenListActivity.this,0);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = View.inflate(getContext(),R.layout.view_cateenlistentry,null);
            }

            Canteen canteen = getItem(position);
            ((TextView) convertView.findViewById(R.id.txvName)).setText(canteen.getName());
            ((TextView) convertView.findViewById(R.id.txvDescription)).setText(canteen.getDescription());
            return convertView;
        }
    }
}
