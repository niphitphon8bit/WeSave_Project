package com.example.wesave_project


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

/**
 * A simple [Fragment] subclass.
 */
class MemberDetail : Fragment() {

    private var head : String = ""
    private var body : String = ""
    private var img : String = ""
    private var pos1 : String = ""
    private var pos2 : String = ""
    private var pos3 : String = ""
    private var pos4 : String = ""
    private var pos5 : String = ""
    private var coach : String = ""

    fun newInstance(head: String,body: String,img: String,pos1: String,pos2: String,pos3: String,pos4: String, pos5: String,coach: String): MemberDetail {

        val fragment = MemberDetail()
        val bundle = Bundle()
        bundle.putString("head", head)
        bundle.putString("body", body)
        bundle.putString("img", img)
        bundle.putString("pos1",pos1)
        bundle.putString("pos2",pos2)
        bundle.putString("pos3",pos3)
        bundle.putString("pos4",pos4)
        bundle.putString("pos5",pos5)
        bundle.putString("coach",coach)
        fragment.setArguments(bundle)

        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            head = bundle.getString("head").toString()
            body = bundle.getString("body").toString()
            img = bundle.getString("img").toString()
            pos1 = bundle.getString("pos1").toString()
            pos2 = bundle.getString("pos2").toString()
            pos3 = bundle.getString("pos3").toString()
            pos4 = bundle.getString("pos4").toString()
            pos5 = bundle.getString("pos5").toString()
            coach = bundle.getString("coach").toString()
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_member_detail, container, false)
        val imgVi : ImageView = view.findViewById(R.id.imgV)
        val headTxt : TextView = view.findViewById(R.id.tv_name)
        val bodyTxt : TextView = view.findViewById(R.id.tv_description)
        val pos1Txt : TextView = view.findViewById(R.id.pos1)
        val pos2Txt : TextView = view.findViewById(R.id.pos2)
        val pos3Txt : TextView = view.findViewById(R.id.pos3)
        val pos4Txt : TextView = view.findViewById(R.id.pos4)
        val pos5Txt : TextView = view.findViewById(R.id.pos5)
        val coachTxt : TextView = view.findViewById(R.id.coach)

        headTxt.setText(head)
        bodyTxt.setText(body)
        pos1Txt.setText(pos1)
        pos2Txt.setText(pos2)
        pos3Txt.setText(pos3)
        pos4Txt.setText(pos4)
        pos5Txt.setText(pos5)
        coachTxt.setText(coach)
        Glide.with(activity!!.baseContext)
            .load(img)
            .into(imgVi);
        // Inflate the layout for this fragment

        return view
    }


}
