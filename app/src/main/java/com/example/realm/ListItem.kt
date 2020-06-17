package com.example.realm

import android.app.AlertDialog
import androidx.lifecycle.ViewModel

class ListItem(private val obj: ListObject, private val viewModel: ViewModel) : BindableItem<ItemListBinding>() {
    override fun getLayout(): Int = R.layout.item_list

    override fun bind(viewBinding: ItemListBinding, position: Int) {
        viewBinding.text = obj.title

        viewBinding.deleteButton.setOnClickListener { view ->
            androidx.appcompat.app.AlertDialog.Builder(view.context)
                .setMessage("「${obj.title}」のメモを削除しますか？")
                .setPositiveButton("削除") { _, _ ->
                    viewModel.deleteId.value = obj.id
                }
                .setNegativeButton("キャンセル", null)
                .create().show()
        }

        viewBinding.root.setOnLongClickListener {
            viewModel.updateItem.value = obj
            true
        }
    }
}