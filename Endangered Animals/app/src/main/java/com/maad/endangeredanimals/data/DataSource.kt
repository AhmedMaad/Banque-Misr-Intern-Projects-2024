package com.maad.endangeredanimals.data

import com.maad.endangeredanimals.R
import com.maad.endangeredanimals.data.constants.Constants
import com.maad.endangeredanimals.model.Animal

class DataSource {

    fun getAnimalsData(): List<Animal> {
        val animals = mutableListOf<Animal>()
        animals.add(
            Animal(
                R.string.african_forest_elephant,
                R.string.african_forest_elephant_details,
                R.drawable.african_forest_elephant,
                Constants.AFRICAN_FOREST_ELEPHANT_URL
            )
        )
        animals.add(
            Animal(
                R.string.amur_leopard,
                R.string.amur_leopard_details,
                R.drawable.amur_leopard,
                Constants.AMUR_LEOPARD_URL
            )
        )
        animals.add(
            Animal(
                R.string.axolotl,
                R.string.axolotl_details,
                R.drawable.axolotl,
                Constants.AXOLOTL_URL
            )
        )
        animals.add(
            Animal(
                R.string.javan_rhino,
                R.string.javan_rhino_details,
                R.drawable.javan_rhino,
                Constants.JAVAN_RHINO_URL
            )
        )
        animals.add(
            Animal(
                R.string.mountain_gorilla,
                R.string.mountain_gorilla_details,
                R.drawable.mountain_gorilla,
                Constants.MOUNTAIN_GORILLA_URL
            )
        )
        animals.add(
            Animal(
                R.string.pangolin,
                R.string.pangolin_details,
                R.drawable.pangolin,
                Constants.PANGOLIN_URL
            )
        )
        animals.add(
            Animal(
                R.string.saola,
                R.string.saola_details,
                R.drawable.saola,
                Constants.SAOLA_URL
            )
        )
        animals.add(
            Animal(
                R.string.sunda_island_tiger,
                R.string.sunda_island_tiger_details,
                R.drawable.sunda_island_tiger,
                Constants.SUNDA_ISLAND_TIGER_URL
            )
        )
        animals.add(
            Animal(
                R.string.tapanuli_orangutan,
                R.string.tapanuli_orangutan_details,
                R.drawable.tapanuli_orangutan,
                Constants.TAPANULI_ORANGUTAN_URL
            )
        )
        animals.add(
            Animal(
                R.string.vaquita,
                R.string.vaquita_details,
                R.drawable.vaquita,
                Constants.VAQUITA_URL
            )
        )
        return animals
    }


}