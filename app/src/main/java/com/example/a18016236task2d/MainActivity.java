package com.example.a18016236task2d;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText etThisID;
    private Button btnCheck;
    private TextView tvTmpMsg;

    // TODO: Task 1 - Declare Firebase variables
    private FirebaseFirestore db;
    private CollectionReference colRef;
    private DocumentReference docRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etThisID = (EditText) findViewById(R.id.editTextStudentID);
        tvTmpMsg = (TextView) findViewById(R.id.textViewMessage);
        btnCheck = (Button) findViewById(R.id.buttonCheck);


        db = FirebaseFirestore.getInstance();

        colRef = db.collection("temp_screening");
        docRef = colRef.document("18016236");


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID = etThisID.getText().toString();
                int id = Integer.parseInt(ID);
                if (id == 18016236){
                    colRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException e) {
                            if (e!= null){
                                Log.w(TAG,"Listen failed!",e);
                                return;
                            }

                            for (QueryDocumentSnapshot doc : snapshots){
                                if (doc.get("name")!=null){
                                    StudentHealth student = doc.toObject(StudentHealth.class);
                                    Log.d(TAG,"onEvent: " + doc.getId());
                                    student.setName(doc.getId());
                                    if (student.getTemp()<=37.2){
                                        tvTmpMsg.setText((CharSequence) student.getName() + "temperature is " + student.getTemp() + "and is healthy ");
                                    }else {
                                        tvTmpMsg.setText((CharSequence) student.getName() + "temperature is " + student.getTemp() + "and is sick ");

                                    }
                                }
                            }
                }
            });

    }
}})
    ;}}