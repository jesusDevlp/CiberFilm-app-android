package com.example.ciberfilm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    lateinit var navegation : BottomNavigationView
    private var idCustomer : String = ""

    private val mOnNavMenu = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when(item.itemId){
            R.id.itemFrag1 -> {
                supportFragmentManager.commit {
                    replace<InicioFragment>(R.id.frameCont)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.itemFrag2 -> {
                supportFragmentManager.commit {
                    replace<PeliculasFragment>(R.id.frameCont)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.itemFrag3 -> {
                supportFragmentManager.commit {
                    replace<CinesFragment>(R.id.frameCont)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.itemFrag4 -> {
                supportFragmentManager.commit {
                    replace<TiendaFragment>(R.id.frameCont)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.itemFrag5 -> {
                supportFragmentManager.commit {
                    replace<MasFragment>(R.id.frameCont)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }
        }

        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val bundleActivity = intent.extras
        bundleActivity?.let {
            idCustomer = it.getString("KEY_CUSTOMER")?:"1"
        }

        navegation = findViewById(R.id.navmenu)
        navegation.setOnNavigationItemSelectedListener(mOnNavMenu)

        val bundleFragment = Bundle()
        bundleFragment.putString("KEY_CUSTOMER_FRAG",idCustomer)

        supportFragmentManager.commit {
            replace<InicioFragment>(R.id.frameCont,"",bundleFragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }
}