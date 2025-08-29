package com.example.cmu.data.remote

import com.google.firebase.firestore.FirebaseFirestore

object FirebaseProvider {
    val db: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }
}
