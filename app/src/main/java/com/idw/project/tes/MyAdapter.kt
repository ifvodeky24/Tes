package com.idw.project.test

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.idw.project.tes.MapsActivity
import com.idw.project.tes.Model.Content
import com.idw.project.tes.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_tour.view.*

class MyAdapter(private val datalist: MutableList<Content>) : RecyclerView.Adapter<MyHolder>(),
    Filterable {

    private lateinit var context: Context

    private var filterListResult: List<Content>

    init {
        this.filterListResult = datalist
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.item_tour, parent, false))
    }

    override fun getItemCount(): Int = filterListResult.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val data = filterListResult[position]

        val ivTour = holder.itemView.ivTour
        val tvPlaceName = holder.itemView.tvPlaceName
        val btnPilih = holder.itemView.btnPilih

        Picasso.get()
            .load("https://api.gaidz.id${data.placeImage}")
            .fit()
            .into(ivTour)

        System.out.println("cek" + data.placeImage)

        tvPlaceName.text = data.placeName

        btnPilih.setOnClickListener {
            Toast.makeText(context, data.placeName, Toast.LENGTH_SHORT).show()
            val moveIntent = Intent(context, MapsActivity::class.java)
            moveIntent.putExtra("placeName", data.placeName)
            moveIntent.putExtra("placeImage", data.placeImage)
            moveIntent.putExtra("latitude", data.latitude)
            moveIntent.putExtra("longitude", data.longitude)
            context.startActivity(moveIntent)

        }

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charSearch = p0.toString()

                if (charSearch.isEmpty())
                    filterListResult = datalist
                else {
                    val resultList = ArrayList<Content>()

                    for (row in datalist) {
                        if (row.placeName!!.toLowerCase().contains(charSearch.toLowerCase()))
                            resultList.add(row)
                    }

                    filterListResult = resultList
                }

                val filterResults = FilterResults()
                filterResults.values = filterListResult
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                filterListResult = p1!!.values as List<Content>
                notifyDataSetChanged()
            }

        }
    }

}