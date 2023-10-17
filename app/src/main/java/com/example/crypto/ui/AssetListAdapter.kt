package com.example.crypto.ui

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.crypto.R
import com.example.crypto.data.local.AssetEntity
import com.example.crypto.databinding.AssetListItemBinding
import com.squareup.picasso.Picasso
import java.math.BigDecimal
import java.math.RoundingMode

class AssetListAdapter(var itemClick: OnItemClickListener) : ListAdapter<AssetEntity, AssetListAdapter.AssetEntityViewHolder>(AssetsComparator()) {

    private val TYPE_UP = 1
    private val TYPE_DOWN = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetEntityViewHolder {
        val layoutId = if (viewType == TYPE_UP) {
            R.layout.asset_list_item_up
        } else {
            R.layout.asset_list_item_down
        }
        return AssetEntityViewHolder.create(parent, layoutId)

        //return AssetEntityViewHolder.create(parent,R.layout.asset_list_item)
    }

    override fun onBindViewHolder(holder: AssetEntityViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)

        val backgroundResId = if (current.changePercent24Hr != null && current.changePercent24Hr.toDouble() >= 0) {
            R.drawable.list_item_up_background
        } else {
            R.drawable.list_item_down_background
        }
        holder.itemView.setBackgroundResource(backgroundResId)

        holder.itemView.setOnClickListener {
            itemClick.onItemClick(current)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val asset = getItem(position)
        // Aquí debes determinar si el valor ha subido o bajado y devolver TYPE_UP o TYPE_DOWN
        return if (asset.changePercent24Hr != null && asset.changePercent24Hr.toDouble() >= 0) {
            TYPE_UP
            //Log.i("TYPE_UP",asset.changePercent24Hr.toString() + asset.name)
        } else {
            TYPE_DOWN
            //Log.i("TYPE_DOWN",asset.changePercent24Hr.toString() + asset.name)
        }
    }

    class AssetEntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = AssetListItemBinding.bind(itemView)

        fun bind(assetEntity: AssetEntity) {

            binding.assetPosition.text = assetEntity.rank

            Picasso.get()
                .load("https://static.coincap.io/assets/icons/${assetEntity.symbol?.lowercase()}@2x.png")
                .into(binding.assetImage)

            binding.assetId.text = assetEntity.name

            //binding.assetUsdPrice.text = "1 ${assetEntity.symbol} = ${assetEntity.priceUsd} USD"

            val priceUsd = assetEntity.priceUsd?.let { price ->
                val priceBigDecimal = BigDecimal(price)
                val roundedPrice = priceBigDecimal.setScale(5, RoundingMode.HALF_UP)
                roundedPrice.toString()
            } ?: "N/A"

            //binding.assetUsdPrice.text = "1 ${assetEntity.symbol} = $priceUsd USD"
            binding.assetUsdPrice.text = "USD$ $priceUsd"

        }

        companion object {
            fun create(parent: ViewGroup, layoutId: Int): AssetEntityViewHolder {
/*                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.asset_list_item, parent, false)*/
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(layoutId, parent, false)
                return AssetEntityViewHolder(view)
            }
        }
    }

    class AssetsComparator : DiffUtil.ItemCallback<AssetEntity>() {
        override fun areItemsTheSame(oldItem: AssetEntity, newItem: AssetEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: AssetEntity, newItem: AssetEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }
}