package com.example.wesave_project

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.recy_layout.view.*
import org.json.JSONArray

class MyRecyclerAdapter(fragmentActivity: FragmentActivity, val dataSource: JSONArray) : RecyclerView.Adapter<MyRecyclerAdapter.Holder>() {

    private val thiscontext : Context = fragmentActivity.baseContext
    private val thisActivity = fragmentActivity

    class Holder(view : View) : RecyclerView.ViewHolder(view) {

        private val View = view

        lateinit var layout : LinearLayout
        lateinit var titleTextView: TextView
        lateinit var detailTextView: TextView
        lateinit var image: ImageView

        fun Holder(){

            layout = View.findViewById<View>(R.id.recyview_layout) as LinearLayout
            titleTextView = View.findViewById<View>(R.id.tv_name) as TextView
            detailTextView = View.findViewById<View>(R.id.tv_description) as TextView
            image = View.findViewById<View>(R.id.imgV) as ImageView

        }

    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.recy_layout, parent, false))
    }


    override fun getItemCount(): Int {
        return dataSource.length()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        if (position % 2 == 0) {
            println("== 0 ")
            println(position)
            holder.itemView.setBackgroundResource(R.drawable.artists_list_backgroundcolor2);
            holder.itemView.tv_name.setTextColor(Color.BLACK)
            holder.itemView.tv_description.setTextColor(Color.BLACK)
        } else {
            println("!= 0 ")
            println(position)
            holder.itemView.setBackgroundResource(R.drawable.artists_list_background_alternate2);
            holder.itemView.tv_name.setTextColor(Color.WHITE)
            holder.itemView.tv_description.setTextColor(Color.WHITE)
        }

        holder.Holder()

        holder.titleTextView.setText(dataSource.getJSONObject(position).getString("title").toString())
        holder.detailTextView.setText(dataSource.getJSONObject(position).getString("description").toString())

        Glide.with(thiscontext)
            .load(dataSource.getJSONObject(position).getString("image").toString())
            .into(holder.image)

        holder.layout.setOnClickListener {

            Toast.makeText(thiscontext, holder.titleTextView.text.toString(), Toast.LENGTH_SHORT)
                .show()


            val head = dataSource.getJSONObject(position).getString("title").toString()
            val body = dataSource.getJSONObject(position).getString("description").toString()
            val img = dataSource.getJSONObject(position).getString("image").toString()
            val pos1 = dataSource.getJSONObject(position).getString("pos1").toString()
            val pos2 = dataSource.getJSONObject(position).getString("pos2").toString()
            val pos3 = dataSource.getJSONObject(position).getString("pos3").toString()
            val pos4 = dataSource.getJSONObject(position).getString("pos4").toString()
            val pos5 = dataSource.getJSONObject(position).getString("pos5").toString()
            val coach = dataSource.getJSONObject(position).getString("coach").toString()

        val fragment_member_detail = MemberDetail().newInstance(head,body,img,pos1,pos2,pos3,pos4,pos5,coach)
        val fm = thisActivity.supportFragmentManager
        val transaction: FragmentTransaction = fm.beginTransaction()
        transaction.replace(R.id.layout, fragment_member_detail, "fragment_member_detail")
        transaction.addToBackStack("main_layout")
        transaction.commit()
        }
    }

}
