package com.example.realm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val customAdapter by lazy { CustomAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    private fun initialize() {
        initLayout()
        initData()
    }

    private fun initLayout() {
        initClick()
        initRecyclerView()
    }

    private fun initClick() {
        fab.setOnClickListener {
            addMemo()
        }
    }

    private fun initRecyclerView() {
        customAdapter.callback = object : CustomAdapter.CustomAdapterCallback {
            override fun onClick(data: ListObject) {
                deleteMemo(data)
            }
        }
        memoRecyclerView.apply {
            adapter = customAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun addMemo() { // Memoを追加するために、入力するためのUIを作る。ここでは、一番簡単なDialogを作ってみる。
        // https://programming-jissen.com/how-to-create-dialog-that-can-be-input/
        // ↑を参考にしました（Javaでしたけど)
        val editText = EditText(this).apply {
            hint = "入力できるよ"
        }
        AlertDialog.Builder(this)
            .setTitle("メモの入力")
            .setMessage("タイトルを入力してください。")
            .setView(editText)
            .setPositiveButton("ok") { _, _ ->
                saveMemo(editText.text.toString())
            }
            .show()
    }

    // Memoの保存
    private fun saveMemo(memoTitle: String) {
        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                realm.insertOrUpdate(ListObject().apply {
                    title = memoTitle
                })
            }
        }
        initData()
    }

    // Memoの削除
    private fun deleteMemo(data: ListObject) {
        ListObject.delete(data)
        initData()
    }

    private fun initData() {
        customAdapter.refresh(ListObject.findAll())
    }
}
