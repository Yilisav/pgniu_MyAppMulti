package vik.com.example.myappmulti.screens


import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.yandex.mapkit.Animation
import com.yandex.mapkit.map.internal.MapBinding
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import vik.com.example.myappmulti.R
import vik.com.example.myappmulti.activities.CJobsMain
import vik.com.example.myappmulti.databinding.FragmentMapNavLayoutBinding


class CMapNavigator : Fragment() {

    private lateinit var binding: FragmentMapNavLayoutBinding
//    private lateinit var resultLauncherLocatePermission : ActivityResultLauncher<String>

    private var mapview: MapView? = null
    private var zoom: Float = 11.0F


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapNavLayoutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // обработка ответа на запрос доступа к геолокации аппарта
//        resultLauncherLocatePermission = registerForActivityResult(ActivityResultContracts.RequestPermission())
//        {isGranted: Boolean ->
//            if (isGranted){ }else{ }
//        }
        // запрос на доступ к геолокации
        //checkLocatePermission()


        // Инициализация Яндекс карты
        MapKitFactory.initialize(requireContext() as CJobsMain)
        mapview = binding.mapview

        // перемещение камеры на начальную точку
        mapview!!.map.move(
            CameraPosition(Point(58.004805, 56.234248), zoom, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0F),
            null
        )

        // обработка кнопки "."
        binding.buttonBackPosition.setOnClickListener{
            onClickBackPosition()
        }
        // обработка кнопки "+"
        binding.buttonZoomUp.setOnClickListener{
            onClickZoomUp()
        }
        // обработка кнопки "-"
        binding.buttonZoomDown.setOnClickListener{
            onClickZoomDown()
        }

        val locationIcon = convertVectorToBit(R.drawable.ic_location_24)
        mapview!!.map.mapObjects.addPlacemark(Point(58.004805, 56.234248),
            ImageProvider.fromBitmap(locationIcon))


    }

    override fun onStop() {
        // Вызов onStop нужно передавать инстансам MapView и MapKit.
        binding.mapview.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        // Вызов onStart нужно передавать инстансам MapView и MapKit.
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.mapview.onStart()
    }

    // конвертор из векторного в точечный рисунок
    private fun convertVectorToBit(drawableId: Int): Bitmap? {
        val drawable = ContextCompat.getDrawable(requireContext() as CJobsMain, drawableId) ?: return null

        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888) ?: return null
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }

    // сброс позиции камеры карты
    private fun onClickBackPosition() {
        mapview!!.map.move(
            CameraPosition(Point(58.004805, 56.234248), zoom, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0F),
            null
        )
    }

    // уменьшение зума камеры карты
    private fun onClickZoomDown() {
        if (zoom > 5.0F) {
            zoom -= 1.0F
            mapview!!.map.move(
                CameraPosition(binding.mapview.map.cameraPosition.target,
                    zoom,
                    binding.mapview.map.cameraPosition.azimuth,
                    binding.mapview.map.cameraPosition.tilt),
                Animation(Animation.Type.SMOOTH, 0F),
                null)
        }else {
            Toast.makeText(requireContext() as CJobsMain, getString(R.string.minZoom), Toast.LENGTH_SHORT).show()
        }
    }

    // увеличение зума камеры карты
    private fun onClickZoomUp() {
        if (zoom < 18.0F) {
            zoom += 1.0F
            mapview!!.map.move(
                CameraPosition(binding.mapview.map.cameraPosition.target,
                    zoom,
                    binding.mapview.map.cameraPosition.azimuth,
                    binding.mapview.map.cameraPosition.tilt),
                Animation(Animation.Type.SMOOTH, 0F),
                null)
        }else {
            Toast.makeText(requireContext() as CJobsMain, getString(R.string.maxZoom), Toast.LENGTH_SHORT).show()
        }
    }

//    private fun checkLocatePermission() {
//        // запрос доступа к геолокации аппарта
//        when {
//            ContextCompat.checkSelfPermission(this.requireContext(),Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
//                // You can use the API that requires the permission.
//            }
//            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
//                // // You can use the API without permission
//                Toast.makeText(this.requireContext(), "Location access not enabled", Toast.LENGTH_LONG ).show()
//            }
//            else -> {
//                // You can directly ask for the permission.
//                // The registered ActivityResultCallback gets the result of this request.
//                resultLauncherLocatePermission.launch(
//                    Manifest.permission.ACCESS_FINE_LOCATION)
//            }
//        }
//    }

}