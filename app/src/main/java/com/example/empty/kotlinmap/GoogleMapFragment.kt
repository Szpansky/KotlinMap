package com.example.empty.kotlinmap

import android.graphics.Point
import android.graphics.Rect
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolygonOptions
import kotlinx.android.synthetic.main.fragment_map.*
import com.google.android.gms.maps.*
import kotlinx.android.synthetic.main.fragment_map.view.*


class GoogleMapFragment : Fragment(), OnMapReadyCallback {

    var map: GoogleMap? = null
    lateinit var mapView: MapView

    override fun onMapReady(p0: GoogleMap?) {
        map = p0
        map!!.moveCamera(CameraUpdateFactory.newLatLng(LatLng(arguments!!.getDouble(LATITUDE), arguments!!.getDouble(LONGITUDE))))
    }

    companion object {
        const val ARGS_NAME = "name"
        const val LATITUDE = "latitude"
        const val LONGITUDE = "longitude"


        fun newInstance(nazwa: String, latitude: Double, longitude: Double): GoogleMapFragment {
            val args: Bundle = Bundle()
            args.putString(ARGS_NAME, nazwa)
            args.putDouble(LATITUDE, latitude)
            args.putDouble(LONGITUDE, longitude)
            val fragment = GoogleMapFragment()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        mapView = view.map_view


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView.onCreate(savedInstanceState)

        mapView.getMapAsync(this)

        view.show_position_button.setOnClickListener { onShowPositionClick() }

    }


    private fun onShowPositionClick() {
        val rect = Rect()
        val squareCardies = IntArray(2)

        square_image.getLocationInWindow(squareCardies)
        square_image.getLocalVisibleRect(rect)
        

        val nw = map!!.projection.fromScreenLocation(Point((square_image.x + rect.left).toInt(), (square_image.y + rect.top).toInt()))
        val se = map!!.projection.fromScreenLocation(Point((square_image.x + rect.right).toInt(), (square_image.y + rect.bottom).toInt()))
        val ne = map!!.projection.fromScreenLocation(Point((square_image.x + rect.right).toInt(), (square_image.y + rect.top).toInt()))
        val sw = map!!.projection.fromScreenLocation(Point((square_image.x + rect.left).toInt(), (square_image.y + rect.bottom).toInt()))

        val po = PolygonOptions().apply {
            add(nw)
            add(ne)
            add(se)
            add(sw)
        }



        map!!.addPolygon(po)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onResume() {
        mapView.onResume()
        super.onResume()
    }

    override fun onPause() {

        super.onPause()
        mapView.onPause()

    }

    override fun onDestroy() {
        mapView.onDestroy()

        super.onDestroy()

    }

    override fun onLowMemory() {

        super.onLowMemory()
        mapView.onLowMemory()

    }


}