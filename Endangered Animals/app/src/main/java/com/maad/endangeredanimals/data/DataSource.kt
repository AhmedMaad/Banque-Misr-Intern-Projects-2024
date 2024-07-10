package com.maad.endangeredanimals.data

import com.maad.endangeredanimals.R
import com.maad.endangeredanimals.model.Animal

class DataSource {

    fun getAnimalsData(): List<Animal> {
        val animals = mutableListOf<Animal>()
        animals.add(
            Animal(
                R.string.african_forest_elephant,
                R.string.african_forest_elephant_details,
                R.drawable.african_forest_elephant,
                "https://www.worldwildlife.org/species/african-forest-elephant"
            )
        )
        animals.add(
            Animal(
                R.string.amur_leopard,
                R.string.amur_leopard_details,
                R.drawable.amur_leopard,
                "https://www.worldwildlife.org/species/amur-leopard"
            )
        )
        animals.add(
            Animal(
                R.string.axolotl,
                R.string.axolotl_details,
                R.drawable.axolotl,
                "https://www.nationalgeographic.com/animals/amphibians/facts/axolotl"
            )
        )
        animals.add(
            Animal(
                R.string.javan_rhino,
                R.string.javan_rhino_details,
                R.drawable.javan_rhino,
                "https://rhinos.org/about-rhinos/rhino-species/javan-rhino/"
            )
        )
        animals.add(
            Animal(
                R.string.mountain_gorilla,
                R.string.mountain_gorilla_details,
                R.drawable.mountain_gorilla,
                "https://www.worldwildlife.org/species/mountain-gorilla"
            )
        )
        animals.add(
            Animal(
                R.string.pangolin,
                R.string.pangolin_details,
                R.drawable.pangolin,
                "https://www.awf.org/wildlife-conservation/pangolin"
            )
        )
        animals.add(
            Animal(
                R.string.saola,
                R.string.saola_details,
                R.drawable.saola,
                "https://www.worldwildlife.org/species/saola"
            )
        )
        animals.add(
            Animal(
                R.string.sunda_island_tiger,
                R.string.sunda_island_tiger_details,
                R.drawable.sunda_island_tiger,
                "https://www.worldwildlife.org/species/sunda-tiger"
            )
        )
        animals.add(
            Animal(
                R.string.tapanuli_orangutan,
                R.string.tapanuli_orangutan_details,
                R.drawable.tapanuli_orangutan,
                "https://en.wikipedia.org/wiki/Tapanuli_orangutan"
            )
        )
        animals.add(
            Animal(
                R.string.vaquita,
                R.string.vaquita_details,
                R.drawable.vaquita,
                "https://www.fisheries.noaa.gov/species/vaquita"
            )
        )
        return animals
    }


}