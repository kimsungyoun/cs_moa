package com.mju.csmoa.home.event_item.viewholder

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mju.csmoa.R
import com.mju.csmoa.databinding.ItemEventItemBinding
import com.mju.csmoa.home.event_item.DetailEventItemActivity
import com.mju.csmoa.home.event_item.domain.model.ItemEventItem
import com.mju.csmoa.util.Constants.TAG

class EventItemViewHolder(
    private val context: Context,
    private val itemEventItemBinding: ItemEventItemBinding
) :
    RecyclerView.ViewHolder(itemEventItemBinding.root) {

    fun bind(itemEventItem: ItemEventItem) {
        // 제품 이름
        itemEventItemBinding.textViewItemEventItemItemName.text = itemEventItem.itemName
        // 제품 가격
        itemEventItemBinding.textViewItemEventItemPrice.text = "${itemEventItem.itemPrice}원"
        // 제품 실제 가격
        itemEventItemBinding.textViewItemEventItemActualPrice.text =
            "(개당 ${itemEventItem.itemActualPrice}원)"
        // 제품 조회수
        itemEventItemBinding.textViewItemEventItemViewCount.text =
            itemEventItem.viewCount.toString()
        // 제품 좋아요 개수
        itemEventItemBinding.textViewItemEventItemLikeCount.text =
            itemEventItem.likeCount.toString()


        Glide.with(context).load(itemEventItem.itemImageSrc)
            .placeholder(R.drawable.img_all_itemimage)
            .error(R.drawable.ic_all_big_x)
            .into(itemEventItemBinding.imageViewItemEventItemEventItemImage)

        // csbrand
        val csBrandColorList = context.resources.getStringArray(R.array.cs_brand_color_list)
        var csBrandStrokeColor = Color.BLACK
        var csBrandResourceId = -1
        when (itemEventItem.csBrand) {
            "cu" -> {
                csBrandStrokeColor = Color.parseColor(csBrandColorList[0])
                csBrandResourceId = R.drawable.img_cs_cu
            }
            "gs25" -> {
                csBrandStrokeColor = Color.parseColor(csBrandColorList[1])
                csBrandResourceId = R.drawable.img_cs_gs25
            }
            "seven" -> {
                csBrandStrokeColor = Color.parseColor(csBrandColorList[2])
                csBrandResourceId = R.drawable.img_cs_seveneleven
            }
            "ministop" -> {
                csBrandStrokeColor = Color.parseColor(csBrandColorList[3])
                csBrandResourceId = R.drawable.img_cs_ministop
            }
            "emart24" -> {
                csBrandStrokeColor = Color.parseColor(csBrandColorList[4])
                csBrandResourceId = R.drawable.img_cs_emart24
            }

        }
        // 편의점 브랜드 설정
        itemEventItemBinding.cardViewItemEventItemCsBrandContainer.strokeColor = csBrandStrokeColor
        itemEventItemBinding.imageViewItemEventItemCsBrand.setImageResource(csBrandResourceId)

        val eventTypeColorList = context.resources.getStringArray(R.array.event_type_color_list)
        var eventTypeColor = Color.BLACK
        when (itemEventItem.itemEventType) {
            "1+1" -> eventTypeColor = Color.parseColor(eventTypeColorList[0])
            "2+1" -> eventTypeColor = Color.parseColor(eventTypeColorList[1])
            "3+1" -> eventTypeColor = Color.parseColor(eventTypeColorList[2])
            "4+1" -> eventTypeColor = Color.parseColor(eventTypeColorList[3])
        }

        // 이벤트 타입 설정
        itemEventItemBinding.textViewItemEventItemEventType.text =
            itemEventItem.itemEventType
        itemEventItemBinding.textViewItemEventItemEventType.setTextColor(eventTypeColor)
        itemEventItemBinding.cardViewItemEventItemEventTypeContainer.strokeColor = eventTypeColor


        // 특정 뷰 클릭했을 때 -> 세부 정보로 이동
        itemEventItemBinding.root.setOnClickListener {
            val detailEventItemIntent = Intent(context, DetailEventItemActivity::class.java).apply {
                putExtra("itemEventItem", itemEventItem)
            }
            context.startActivity(detailEventItemIntent)
        }
    }
}