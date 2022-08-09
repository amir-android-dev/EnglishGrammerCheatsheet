package com.amir.englishgrammercheatsheet.presentation.grammar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amir.englishgrammercheatsheet.R
import com.amir.englishgrammercheatsheet.data.model.ContentsModel

class AdapterContent(private val contentList: List<ContentsModel>) : RecyclerView.Adapter<MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val layoutInflater  = LayoutInflater.from(parent.context)
        val contentItem = layoutInflater.inflate(R.layout.content_item,parent,false)

        return MyViewHolder(contentItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
     holder.bind(contentList[position])
    }

    override fun getItemCount(): Int {
    return contentList.size
    }


}

class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(contentsModel: ContentsModel) {
        val ivContent = view.findViewById<ImageView>(R.id.iv_content)
        val tvContent = view.findViewById<TextView>(R.id.tv_content)
        ivContent.setImageResource(contentsModel.image)
        tvContent.text = contentsModel.title
    }
}