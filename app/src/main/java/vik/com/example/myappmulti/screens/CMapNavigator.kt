package vik.com.example.myappmulti.screens

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.yandex.mapkit.mapview.MapView
import vik.com.example.myappmulti.activities.CJobsMain
import vik.com.example.myappmulti.databinding.FragmentMapNavLayoutBinding


class CMapNavigator : Fragment() {

    private lateinit var binding: FragmentMapNavLayoutBinding
    private lateinit var resultLauncherLocatePermission : ActivityResultLauncher<String>

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

        // обработка ответа на запрос доступа к геолокации аппарта
        resultLauncherLocatePermission = registerForActivityResult(ActivityResultContracts.RequestPermission())
        {isGranted: Boolean ->
            if (isGranted){

            }else{

            }
        }

        // запрос доступа к геолокации аппарта
        when {
            ContextCompat.checkSelfPermission(
                this.requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
            }
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                // showInContextUI(...)
                Toast.makeText(this.requireContext(), "Location access not enabled", Toast.LENGTH_LONG ).show()
        }
            else -> {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                resultLauncherLocatePermission.launch(
                    Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
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