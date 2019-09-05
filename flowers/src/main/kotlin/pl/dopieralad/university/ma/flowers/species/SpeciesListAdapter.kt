package pl.dopieralad.university.ma.flowers.species

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.dopieralad.university.ma.flowers.R
import java.util.function.Consumer

class SpeciesListAdapter(context: Context, val onSpeciesSelected: Consumer<Species>) : RecyclerView.Adapter<SpeciesListAdapter.FlowerViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)
    private var selectedSpecies: Species? = null
        set(species) {
            species?.let {
                field = species
                onSpeciesSelected.accept(species)
                notifyDataSetChanged()
            }
        }

    var species: List<Species> = emptyList()
        set(species) {
            field = species.sortedBy(Species::name)
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = species.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
        val speciesListItem = R.layout.species_list_item
        val view = layoutInflater.inflate(speciesListItem, parent, false)
        return FlowerViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        val species = species[position]

        holder.itemView.let {
            val isSelected = species == selectedSpecies
            val selectionColor = Color.parseColor("#cfcfcf")
            val defaultColor = Color.parseColor("#ffffff")

            it.setOnClickListener { selectedSpecies = species }
            it.setBackgroundColor(if (isSelected) selectionColor else defaultColor)
        }

        holder.name.text = species.name
        holder.latinName.text = species.latinName
        holder.wateringFrequency.text = species.wateringFrequency.toString()
    }

    class FlowerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.species_name)
        val latinName: TextView = view.findViewById(R.id.species_latin_name)
        val wateringFrequency: TextView = view.findViewById(R.id.species_watering_frequency)
    }
}
