package com.example.realm

import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val customAdapter by lazy { CustomAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        initData()
    }

    private fun initialize() {
        initLayout()
    }

    private fun initLayout() {
        initClick()
        initClick2()
        initClick3()
        initRecyclerView()
    }

    private fun initClick() {
        fab.setOnClickListener {
            addMemo()
        }
    }

    private fun initClick2() {
        button.setOnClickListener {
            addMemo2()
        }
    }
    private fun initClick3() {
        button2.setOnClickListener {
            addMemo3()
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
            hint = "タイトル入力"
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

    private fun addMemo2() { // Memoを追加するために、入力するためのUIを作る。ここでは、一番簡単なDialogを作ってみる。
        // https://programming-jissen.com/how-to-create-dialog-that-can-be-input/
        // ↑を参考にしました（Javaでしたけど)
        val editText2 = EditText(this).apply {
            hint = "本文入力"
        }
        AlertDialog.Builder(this)
            .setTitle("本文ですよ")
            .setMessage("本文を入力してね")
            .setView(editText2)
            .setPositiveButton("いいね") { _, _ ->
                saveMemo2(editText2.text.toString())
            }
            .setNegativeButton("ダメ",null)
            .show()
    }
    private fun addMemo3() {
        val editText3 = EditText(this).apply {
        }
        var item2 = arrayOf("list1","list2","list3")
        AlertDialog.Builder(this)
            .setTitle("Selector")
            .setItems(item2,{ dialog, which -> })
            .setPositiveButton("いいね") { _, _ ->
                saveMemo2(editText3.text.toString())
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

    private fun saveMemo2(memoTitle2: String) {
        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                realm.insertOrUpdate(ListObject().apply {
                    title2 = memoTitle2
                })
            }
        }
        initData()
    }
    private fun saveMemo3(memoTitle3: String) {
        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                realm.insertOrUpdate(ListObject().apply {
                    title3 = memoTitle3
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
        val list = ListObject.findAll()
        customAdapter.refresh(list)
    }
}
