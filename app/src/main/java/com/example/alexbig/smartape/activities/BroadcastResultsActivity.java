package com.example.alexbig.smartape.activities;

import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.abemart.wroup.common.WiFiDirectBroadcastReceiver;
import com.abemart.wroup.common.WiFiP2PInstance;
import com.abemart.wroup.common.WroupDevice;
import com.abemart.wroup.common.listeners.ClientConnectedListener;
import com.abemart.wroup.common.listeners.ClientDisconnectedListener;
import com.abemart.wroup.common.listeners.DataReceivedListener;
import com.abemart.wroup.common.messages.MessageWrapper;
import com.abemart.wroup.service.WroupService;
import com.example.alexbig.smartape.R;
import com.example.alexbig.smartape.models.Quiz;
import com.example.alexbig.smartape.utils.JsonConverter;

import java.util.ArrayList;
import java.util.List;

public class BroadcastResultsActivity extends AppCompatActivity implements DataReceivedListener {

    private WiFiDirectBroadcastReceiver wiFiDirectBroadcastReceiver;
    private WroupService wroupService;
    private ListView listView;
    private TextView textView;
    private List<String> results = new ArrayList<>();
    private List<Float> grades = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.broadcast_results_activity);

        listView = findViewById(R.id.broadcast_resultListView);
        textView = findViewById(R.id.broadcast_resultTextView);

        wiFiDirectBroadcastReceiver = WiFiP2PInstance.getInstance(this).getBroadcastReceiver();
        wroupService = WroupService.getInstance(getApplicationContext());
        wroupService.setDataReceivedListener(this);

        Quiz quiz = (Quiz) getIntent().getSerializableExtra("QUIZ");

        wroupService.setClientConnectedListener(new ClientConnectedListener() {
            @Override
            public void onClientConnected(WroupDevice wroupDevice) {
                MessageWrapper message = new MessageWrapper();
                message.setMessage(JsonConverter.toJson(quiz));
                message.setMessageType(MessageWrapper.MessageType.NORMAL);
                wroupService.sendMessage(wroupDevice, message);
            }
        });

        wroupService.setClientDisconnectedListener(new ClientDisconnectedListener() {
            @Override
            public void onClientDisconnected(WroupDevice wroupDevice) {

            }
        });
    }

    @Override
    public void onDataReceived(MessageWrapper messageWrapper) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("MESSAGE " + messageWrapper.getMessage());
                System.out.println("GRADE RECEIVED " + messageWrapper.getMessage());
                results.add(messageWrapper.getWroupDevice().getDeviceName() + ": " + messageWrapper.getMessage());
                grades.add(Float.parseFloat(messageWrapper.getMessage()));
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, results);
                listView.setAdapter(adapter);

                float average = 0;
                for (float f : grades) {
                    average += f;
                }
                average /= results.size();
                textView.setText("Average " + average);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        registerReceiver(wiFiDirectBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(wiFiDirectBroadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (wroupService != null) {
            wroupService.disconnect();
        }
    }
}
