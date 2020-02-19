package com.idw.project.tes.Fragment


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idw.project.tes.Model.Content
import com.idw.project.tes.R
import com.idw.project.test.MyAdapter
import kotlinx.android.synthetic.main.fragment_beranda.*
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

/**
 * A simple [Fragment] subclass.
 */
class BerandaFragment : Fragment() {
    private var tourList = ArrayList<Content>()
    private lateinit var adapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_beranda, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTour.layoutManager = GridLayoutManager(activity, 2) as RecyclerView.LayoutManager?
        rvTour.setHasFixedSize(true)
        adapter = MyAdapter(tourList)
        rvTour.adapter = adapter

        read_json();

        setHasOptionsMenu(true)
    }

    private fun read_json() {
        var json: String? = null

        try {
            val inputStream: InputStream = activity!!.assets.open("tour.json")
            json = inputStream.bufferedReader().use { it.readText() }

            val jsonRESULTS = JSONObject(json)
            val tes = jsonRESULTS.getJSONObject("data").getJSONArray("content")

            for (i in 0..tes.length() - 1) {
                val jsonobjs = tes.getJSONObject(i)

                val placeName = jsonobjs.getString("placeName")
                val latitude = jsonobjs.getString("latitude")
                val longitude = jsonobjs.getString("longitude")
                val placeImage = jsonobjs.getString("placeImage")

                val content = Content(latitude, longitude, placeImage, placeName)

                println("datanyaa ajaa $content")

                tourList.add(content)

            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        val inflater = activity!!.menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        val searchManager = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu!!.findItem(R.id.action_search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(activity, query, Toast.LENGTH_SHORT).show()
                return true
            }


            override fun onQueryTextChange(newText: String): Boolean {
                adapter.getFilter().filter(newText)
                return true
            }
        })
    }
}
