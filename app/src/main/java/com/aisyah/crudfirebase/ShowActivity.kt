package com.aisyah.crudfirebase

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class ShowActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var list: MutableList<Model>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        ref = FirebaseDatabase.getInstance().getReference("user")
        list = mutableListOf()
        listView = findViewById(R.id.listview)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    list.clear() //kosongin listnya
                    for (a in p0.children) {
                        val user = a.getValue(Model::class.java)
                        list.add(user!!)
                    }

                    val adapter = Adapterlist(this@ShowActivity, R.layout.item_list, list)
                    listView.adapter = adapter
                }
            }
        })

    }
}
