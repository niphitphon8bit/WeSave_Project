package com.example.wesave_project


import android.R.attr.defaultValue
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase


/**
 * A simple [Fragment] subclass.
 */
class DataRealtime : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_data_realtime, container, false)
        // Inflate the layout for this fragment

        val btn1 = view.findViewById<Button>(R.id.btn1)
        val text = view.findViewById<TextInputEditText>(R.id.textInputEditText)
//        val btn2 = view.findViewById<Button>(R.id.btn2)
//        val btn3 = view.findViewById<Button>(R.id.btn3)
//        val btn4 = view.findViewById<Button>(R.id.btn4)

//ประกาศตัวแปร DatabaseReference รับค่า Instance และอ้างถึง path ที่เราต้องการใน database
        val mRootRef = FirebaseDatabase.getInstance().getReference()

        //อ้างอิงไปที่ path ที่เราต้องการจะจัดการข้อมูล ตัวอย่างคือ users และ messages
        val mUsersRef = mRootRef.child("users")
        val mMessagesRef = mRootRef.child("messages")

        btn1.setOnClickListener {
            view.clearFocus()

            val builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
            builder.setMessage("ต้องการเพิ่มข้อมูลหรือไม่?")
            builder.setPositiveButton("ต้องการ",
                DialogInterface.OnClickListener { dialog, id ->
                    val bundle = this.arguments
                    var myName = "Niphitphon Thanatkulkit"
                    if (bundle != null) {
                        myName = bundle.getString("Name").toString()
                    }
                    val mMessagesRef2 = mRootRef.child("data")
                    val key = mMessagesRef.push().key
                    val postValues: HashMap<String, Any> = HashMap()
                    postValues["username"] = myName
                    postValues["text"] = text.getText().toString()

                    val childUpdates: MutableMap<String, Any> = HashMap()

                    childUpdates["$key"] = postValues

                    mMessagesRef2.updateChildren(childUpdates)
                    //setValue() เป็นการ write หรือ update ข้อมูล ไปยัง path ที่เราอ้างถึงได้ เช่น users/<user-id>/<username>
                    mUsersRef.child("id-60160170").setValue("Niphitphon")
                    Toast.makeText(
                        this.context,
                        "เพิ่มข้อมูลสำเร็จ", Toast.LENGTH_SHORT
                    ).show()
                })
            builder.setNegativeButton("ไม่ต้องการ",
                DialogInterface.OnClickListener { dialog, which ->
                    //dialog.dismiss();
                })
            builder.show()

        }
//
//        btn2.setOnClickListener {
//            val friendlyMessage = FriendlyMessage("60160170", "Hello World!");
//            mMessagesRef.push().setValue(friendlyMessage);
//        }
//
//        btn3.setOnClickListener {
//            // push เป็นการ generate $postid ของ object ชื่อ posts ออกมาก่อนเพื่อใช้ใน // /user-posts/$userid/$postid
//            val key = mMessagesRef.push().key
//            val postValues: HashMap<String, Any> = HashMap()
//            postValues["username"] = "60160000"
//            postValues["text"] = "Hello World Dota2"
//
//            val childUpdates: MutableMap<String, Any> = HashMap()
//
//            childUpdates["/messages/$key"] = postValues
//
//            childUpdates["/user-messages/Niphitphon/$key"] = postValues
//
//            mMessagesRef.updateChildren(childUpdates)
//
//        }
//
//        btn4.setOnClickListener {
//            val mMessagesRef2 = mRootRef.child("data")
//
//            val key = mMessagesRef.push().key
//            val postValues: HashMap<String, Any> = HashMap()
//            postValues["username"] = "60160170"
//            postValues["text"] = "Hello World!"
//
//            val childUpdates: MutableMap<String, Any> = HashMap()
//
//            childUpdates["$key"] = postValues
//
//            mMessagesRef2.updateChildren(childUpdates)
//
//        }

        return view

    }

    data class FriendlyMessage(
        var username: String? = "",
        var text: String? = ""
    )
}
