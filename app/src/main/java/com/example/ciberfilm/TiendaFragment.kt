package com.example.ciberfilm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ciberfilm.adapter.TiendaAdapter
import com.example.ciberfilm.databinding.FragmentTiendaBinding
import com.example.ciberfilm.model.Tienda


class TiendaFragment : Fragment() {

    private var _binding : FragmentTiendaBinding? = null
    private val binding get() = _binding!!
    private lateinit var tiendaAdapter: TiendaAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTiendaBinding.inflate(inflater, container, false)
        val root : View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tiendaAdapter = TiendaAdapter()

        binding.rvTie.adapter = tiendaAdapter
        var tienda = ArrayList<Tienda>()
        tienda.add(Tienda("Combo 2 Mix OL CC + Caramel Pop Corn","1 Canchita Gigante (Mix) + 1 supercanchita POPSTER Caramel + 2 Bebidas (32oz)", 20.0,"https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/ItemGraphic/2374?allowPlaceHolder=true"))
        tienda.add(Tienda("Combo 2 Dulce OL CC","1 Canchita Gigante (Dulce) + 2 Bebidas (32oz). *Sabor bebida sujeto a stock / canchita sin refill", 28.0,"https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/ItemGraphic/1246?allowPlaceHolder=true"))
        tienda.add(Tienda("Combo 2 Salado OL CC","Canchita Gigante Salada + 2 Bebidas (32oz). *Sabor bebida sujeto a stock / canchita sin refill", 21.0,"https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/ItemGraphic/1244?allowPlaceHolder=true"))
        tienda.add(Tienda("Combo 2 Mix OL CC","1 Canchita Gigante (Mix) + 2 Bebidas (32oz). *Sabor bebida sujeto a stock / canchita sin refill", 54.0,"https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/ItemGraphic/2371?allowPlaceHolder=true"))
        tienda.add(Tienda("Com.2 Salado Dob.Gig. OL","2 Canchita Gigante Salada + 2 Bebidas Grandes (32oz) *Sabor bebida sujeto a stock / canchita sin refill", 31.0,"https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/ItemGraphic/2368?allowPlaceHolder=true"))
        tienda.add(Tienda("Com.2 Mix Dob.Gig. OL","¡Exclusivo para ti! 2 Canchita Gigante + 2 Bebidas Grandes (32oz) a un precio especial / Canchita sin refill", 44.0,"https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/ItemGraphic/2371?allowPlaceHolder=true"))
        tienda.add(Tienda("Com.2 Dulce Dob.Gig. OL","2 Canchita Gigante (Dulce) + 2 Bebidas Grandes (32oz) . *Sabor bebida sujeto a stock / canchita sin refill", 10.0,"https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/ItemGraphic/2368?allowPlaceHolder=true"))
        tienda.add(Tienda("Combo 1 OL CC","Canchita Grande(Salada) + 1 Bebida (32oz).*Sabor gaseosa sujeto a stock del cine.", 46.0,"https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/ItemGraphic/2373?allowPlaceHolder=true"))
        tienda.add(Tienda("Combo 1 OL CC Dulce","Canchita Grande(Dulce) + 1 Bebida (32oz).*Sabor gaseosa sujeto a stock del cine", 48.0,"https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/ItemGraphic/2370?allowPlaceHolder=true"))
        tienda.add(Tienda("Combo de Película OL CC","1 Canchita Grande (Salada) + 2 Bebidas Medianas (21 oz). *Sabor gaseosa sujeto a stock del cine", 31.0,"https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/ItemGraphic/2367?allowPlaceHolder=true"))
        tienda.add(Tienda("Combo de Película OL CC Dulce","1 Canchita Grande (Dulce) + 2 Bebidas Medianas (21 oz). *Sabor gaseosa sujeto a stock del cine", 32.0,"https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/ItemGraphic/1246?allowPlaceHolder=true"))
        tienda.add(Tienda("Canchita Gigante Mix","La mejor opción para compartir en pareja. *Canchita sin refill", 29.0,"https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/ItemGraphic/1244?allowPlaceHolder=true"))
        tienda.add(Tienda("Canchita Gigante Dulce","Canchita Grande Dulce", 21.0,"https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/ItemGraphic/2374?allowPlaceHolder=true"))
        tienda.add(Tienda("Hot Dog Jumbo","Disfrútalo con papitas al hilo y las salsas que prefieras", 9.0,"https://cdn.apis.cineplanet.com.pe/CDN/media/entity/get/ItemGraphic/179?allowPlaceHolder=true"))

        tiendaAdapter.updateList(tienda)

    }
}

