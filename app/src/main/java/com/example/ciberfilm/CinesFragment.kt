package com.example.ciberfilm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ciberfilm.adapter.CinesAdapter
import com.example.ciberfilm.databinding.FragmentCinesBinding
import com.example.ciberfilm.model.Cines


class CinesFragment : Fragment() {
    private var _binding : FragmentCinesBinding? = null
    private val binding get() = _binding!!
    private lateinit var cinesAdapterv2: CinesAdapter
    private var idCustomer : String = "1"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCinesBinding.inflate(inflater, container, false)
        val root : View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cinesAdapterv2 = CinesAdapter()
        binding.rvCines.adapter = cinesAdapterv2
        var sedes = ArrayList<Cines>()
        sedes.add(Cines("San Juan de Lurigacho",200,"Cine Rojo: Sumérgete en un espacio íntimo y acogedor donde la pasión por el séptimo arte se desborda. Disfruta de proyecciones cautivadoras en una atmósfera envolvente.",12,"abierto", "https://www.sanjuandelurigancho.com/uploads/imgnoticias/art-20180226-599d.jpg"))
        sedes.add(Cines("Comas",120,"Sala Épica: Vive experiencias cinematográficas inolvidables en esta sala de cine de última generación. Con una pantalla gigante y sonido envolvente, te transportarás a mundos fascinantes en cada función.",12,"abierto", "https://elcomercio.pe/resizer/6vUfIfF6IzeXCYnyglEm7Y1QpsU=/580x330/smart/filters:format(jpeg):quality(75)/cloudfront-us-east-1.images.arcpublishing.com/elcomercio/3YGBGR3JIFBAXH3WZZCOSHUMHY.jpg"))
        sedes.add(Cines("Ate Vitarte",100,"Cine Zen: Sumérgete en la calma y el equilibrio de este cine único. Con una iluminación suave y asientos cómodos, disfruta de películas que te transportarán a un estado de relajación profunda.",12,"abierto", "https://peruretail.sfo3.cdn.digitaloceanspaces.com/wp-content/uploads/Cinemark-MegaPlaza-Peru-Retail-1024x683.jpg"))
        sedes.add(Cines("Miraflores",200,"Teatro Brillante: Adéntrate en un espacio de ensueño, donde la magia del cine brilla con intensidad. Con una decoración deslumbrante y una programación de películas cautivadoras, vivirás momentos de pura emoción.",12,"abierto", "https://peruretail.sfo3.cdn.digitaloceanspaces.com/wp-content/uploads/cinestar-2.jpg"))
        sedes.add(Cines("San Borja",150,"MicroCine: Descubre la grandeza en lo pequeño con este cine íntimo y exclusivo. Disfruta de proyecciones cuidadosamente seleccionadas en un ambiente íntimo y acogedor, perfecto para los amantes del cine de autor.",12,"abierto", "https://1.bp.blogspot.com/-G5Zyj8ZnKvA/TvTdmPO2k1I/AAAAAAAAAr8/B8s41pDuXgQ/s1600/cinestars.jpg"))
        sedes.add(Cines("La Molina",90,"Cine Éter: Embárcate en un viaje transcendental a través de las imágenes y sonidos que cobran vida en esta sala mágica. Sumérgete en historias cautivadoras que te transportarán más allá de la realidad, en un espacio donde la imaginación no tiene límites.",12,"abierto", "https://www.cinergie.be/images/actualite/breve/2019_02/imagix-mons_0.jpg"))
        cinesAdapterv2.updateList(sedes)

    }


}