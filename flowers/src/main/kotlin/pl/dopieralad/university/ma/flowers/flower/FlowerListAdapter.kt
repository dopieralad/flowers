package pl.dopieralad.university.ma.flowers.flower

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.dopieralad.university.ma.flowers.R
import java.util.function.Consumer

class FlowerListAdapter(context: Context,
                        private val waterListener: Consumer<Flower>,
                        private val deleteListener: Consumer<Flower>
) : RecyclerView.Adapter<FlowerListAdapter.FlowerViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    var flowers: List<FlowerWithSpecies> = emptyList()
        set(flowers) {
            field = flowers
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = flowers.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
        val flowerListItem = R.layout.flower_list_item
        val view = layoutInflater.inflate(flowerListItem, parent, false)
        return FlowerViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        val flower = flowers[position]

        holder.name.text = flower.flower.name
        holder.species.text = flower.species.name

        holder.water.setOnClickListener { waterListener.accept(flower.flower) }
        holder.delete.setOnClickListener { deleteListener.accept(flower.flower) }
    }

    class FlowerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.flower_name)
        val species: TextView = view.findViewById(R.id.flower_species)
        val water: ImageView = view.findViewById(R.id.water_flower)
        val delete: ImageView = view.findViewById(R.id.delete_flower)
    }
}
