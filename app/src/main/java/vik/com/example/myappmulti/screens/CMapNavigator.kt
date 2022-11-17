package vik.com.example.myappmulti.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yandex.mapkit.mapview.MapView
import vik.com.example.myappmulti.databinding.FragmentMapNavLayoutBinding


class CMapNavigator : Fragment() {

    private lateinit var binding: FragmentMapNavLayoutBinding
    private var mapview: MapView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapNavLayoutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//            MapKitFactory.setApiKey("340e1f06-59b9-4e22-bd0f-a6c9903da439");
//            binding.mapview.map!!
//            //mapview = findViewById(R.id.mapview)
//            //setContentView(R.layout.activity_main)
//            // Перемещение камеры в центр Санкт-Петербурга.
//            binding.mapview?.map!!.move(
//            CameraPosition(com.yandex.mapkit.geometry.Point(55.751574, 37.573856), 11.0f, 0.0f, 0.0f),
//            Animation(Animation.Type.SMOOTH, 5F),
//            null
//        )
    }

}