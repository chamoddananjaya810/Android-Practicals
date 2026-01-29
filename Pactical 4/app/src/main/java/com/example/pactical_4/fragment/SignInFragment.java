package com.example.pactical_4.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pactical_4.R;
import com.example.pactical_4.activity.HomeActiviry;


public class SignInFragment extends Fragment {

private EditText emailEditText,passwordEditText;
private Button signInButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emailEditText=view.findViewById(R.id.editTextEmail);
        passwordEditText=view.findViewById(R.id.editTextPassword);
        signInButton=view.findViewById(R.id.btnSignIn);

        signIn();
    }
    private void signIn(){
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String email=  emailEditText.getText().toString();
                String password=passwordEditText.getText().toString();

          if (email.isEmpty() || password.isEmpty()){
              Toast.makeText(getContext(),"Please fill the filed",Toast.LENGTH_SHORT).show();
              return;
          }

         Intent intent =new Intent(getContext(), HomeActiviry.class);
          intent.putExtra("email",email);
          startActivity(intent);

//                Log.i("SignInfragment","email:"+email);
//                Log.i("SignInfragment","password:"+password);
            }
        });
    }

}
