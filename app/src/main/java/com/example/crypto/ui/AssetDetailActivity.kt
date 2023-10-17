package com.example.crypto.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crypto.data.local.AssetEntity
import com.example.crypto.databinding.ActivityAssetDetailBinding
import com.squareup.picasso.Picasso

class AssetDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityAssetDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssetDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val asset = intent.extras?.getSerializable("asset") as AssetEntity

        Picasso.get()
            .load("https://static.coincap.io/assets/icons/${asset.symbol?.lowercase()}@2x.png")
            .resize(250, 250)
            .into(binding.assetImage)

        binding.assetName.text = asset.name
        binding.assetSymbol.text = asset.symbol
        binding.assetPrice.text = "USD$ ${asset.priceUsd}"

        if (asset.maxSupply == null) {
            binding.assetSupply.text = "Supply: Unavailable"
        } else {
            binding.assetSupply.text = "USD$ ${asset.supply}"
        }

        binding.assetMarketcap.text = "Market Cap: ${asset.marketCapUsd}"
    }
}