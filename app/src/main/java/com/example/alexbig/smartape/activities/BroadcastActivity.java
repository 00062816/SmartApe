package com.example.alexbig.smartape.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.abemart.wroup.client.WroupClient;
import com.abemart.wroup.common.WiFiDirectBroadcastReceiver;
import com.abemart.wroup.common.WiFiP2PError;
import com.abemart.wroup.common.WiFiP2PInstance;
import com.abemart.wroup.common.WroupDevice;
import com.abemart.wroup.common.WroupServiceDevice;
import com.abemart.wroup.common.listeners.ClientConnectedListener;
import com.abemart.wroup.common.listeners.ClientDisconnectedListener;
import com.abemart.wroup.common.listeners.DataReceivedListener;
import com.abemart.wroup.common.listeners.ServiceConnectedListener;
import com.abemart.wroup.common.listeners.ServiceDiscoveredListener;
import com.abemart.wroup.common.listeners.ServiceRegisteredListener;
import com.abemart.wroup.common.messages.MessageWrapper;
import com.abemart.wroup.service.WroupService;
import com.example.alexbig.smartape.R;
import com.example.alexbig.smartape.database.viewmodels.QuizViewModel;
import com.example.alexbig.smartape.models.Quiz;
import com.example.alexbig.smartape.utils.JsonConverter;
import com.example.alexbig.smartape.utils.Toaster;

import java.util.ArrayList;
import java.util.List;

public class BroadcastActivity extends AppCompatActivity implements DataReceivedListener{

    private WiFiDirectBroadcastReceiver wiFiDirectBroadcastReceiver;
    private WroupService wroupService;
    private WroupClient wroupClient;
    private List<String> groups = new ArrayList<>();
    private List<WroupServiceDevice> devices = new ArrayList<>();
    private ListView listView;
    private QuizViewModel quizViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast_activity);

        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        wiFiDirectBroadcastReceiver = WiFiP2PInstance.getInstance(this).getBroadcastReceiver();

        listView = findViewById(R.id.broadcast_groupListView);
        FloatingActionButton createGroupButton = findViewById(R.id.broadcast_addGroupButton);
        FloatingActionButton findGroupButton = findViewById(R.id.broadcast_findGroupButton);

        createGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               createGroupDialog();
            }
        });
        findGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findGroups();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                WroupServiceDevice selectedService = devices.get(i);
                connectTo(selectedService);
            }
        });
    }

    private void createGroupDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select a quiz");

        List<String> titles = new ArrayList<>();
        /*for (Quiz q:quizViewModel.getQuizzes().getValue()){
            titles.add(q.getTitle());
        }*/
        builder.setItems(titles.toArray(new String[titles.size()]), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Quiz quiz = quizViewModel.getQuizzes().getValue().get(i);
                //System.out.println("JSON pre "+JsonConverter.toJson(quiz));
                //createGroup(quiz);
            }
        });
        AlertDialog pickQuizDialog = builder.create();
        pickQuizDialog.show();
    }

    private void createGroup(Quiz quiz){
        wroupService = WroupService.getInstance(getApplicationContext());
        wroupService.registerService(quiz.getTitle(), new ServiceRegisteredListener() {
            @Override
            public void onSuccessServiceRegistered() {
                Toaster.makeToast(getApplicationContext(), "Group created");
                Intent intent = new Intent(getApplicationContext(), BroadcastResultsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("QUIZ", quiz);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onErrorServiceRegistered(WiFiP2PError wiFiP2PError) {

            }
        });
    }

    private void findGroups(){
        final ProgressDialog progressDialog = new ProgressDialog(BroadcastActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Searching...");
        progressDialog.show();

        groups.clear();
        devices.clear();

        wroupClient = WroupClient.getInstance(getApplicationContext());
        wroupClient.setDataReceivedListener(this);
        wroupClient.discoverServices(5000L, new ServiceDiscoveredListener() {

            @Override
            public void onNewServiceDeviceDiscovered(WroupServiceDevice serviceDevice) {
                System.out.println("GROUP FOUND "+serviceDevice.getTxtRecordMap().get(WroupService.SERVICE_GROUP_NAME));
                groups.add(serviceDevice.getTxtRecordMap().get(WroupService.SERVICE_GROUP_NAME));
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,groups);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFinishServiceDeviceDiscovered(List<WroupServiceDevice> serviceDevices) {
                System.out.println("Found '" + serviceDevices.size() + "' groups");
                progressDialog.dismiss();
                devices = serviceDevices;

                if (serviceDevices.isEmpty()) {
                    Toaster.makeToast(getApplicationContext(), "No groups found");
                }
            }

            @Override
            public void onError(WiFiP2PError wiFiP2PError) {
               Toaster.makeToast(getApplicationContext(), "Error searching groups");
            }
        });
    }

    private void connectTo(WroupServiceDevice serviceDevice){
        ProgressDialog progressDialog = new ProgressDialog(BroadcastActivity.this);
        progressDialog.setMessage("Connecting...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        wroupClient.connectToService(serviceDevice, new ServiceConnectedListener() {
            @Override
            public void onServiceConnected(WroupDevice serviceDevice) {
                progressDialog.dismiss();
                Toaster.makeToast(getApplicationContext(), "Connected");
            }
        });
    }

    @Override
    public void onDataReceived(MessageWrapper messageWrapper) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("MESSAGE "+messageWrapper.getMessage());
                if (messageWrapper.getMessageType() == MessageWrapper.MessageType.NORMAL) {
                    if (messageWrapper.getMessage().contains("{")) {
                        openQuiz(JsonConverter.toQuiz(messageWrapper.getMessage()));
                    }else{
                        System.out.println("GRADE RECEIVED "+messageWrapper.getMessage());
                    }
                }
            }
        });
    }

    private void openQuiz(Quiz quiz){
        Intent intent = new Intent(this, QuizActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("QUIZ", quiz);
        intent.putExtras(bundle);
        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 1){
            if (resultCode == 1){
                String grade = data.getStringExtra("GRADE");
                MessageWrapper message = new MessageWrapper();
                message.setMessage(grade);
                message.setMessageType(MessageWrapper.MessageType.NORMAL);
                wroupClient.sendMessageToServer(message);
            }
        }
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

        if (wroupClient != null) {
            wroupClient.disconnect();
        }
    }
}
