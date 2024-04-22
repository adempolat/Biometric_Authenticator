package com.example.biometricauthenticator

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.example.biometricauthenticator.ui.theme.biometricauthenticator

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val biometricAuthenticator= BiometricAuthenticator(this)
        setContent {
            biometricauthenticator {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val activity = LocalContext.current as FragmentActivity
                    var message by remember {
                        mutableStateOf("")
                    }
                    TextButton(onClick = {
                        biometricAuthenticator.promptBiometriccAuth(
                            title = "Login",
                            subTitle = "Use your fingerprint or face id ",
                            negativeButtonText = "Cancel",
                            fragmentactivity = activity,
                            onSuccess = {
                                message = "Success"
                            },
                            onFaied = {
                                message = "wrong fingerprint"
                            },
                            onError = { _, error ->
                                message = error
                            }
                        )
                    }) {
                        Text(text = "Login with fingerprint or face id")
                    }
                    
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = message)
                }
            }
        }
    }
}
