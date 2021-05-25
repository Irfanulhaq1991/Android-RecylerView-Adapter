import React from 'react'
import Ccode from './Ccode';
import Content from './Content';
import Heading from './Heading';

function Contents() {

    const cod1 = `android{
        ...
         buildFeatures {
             dataBinding true
         
           }
         }`


    const cod2 = `<?xml version="1.0" encoding="utf-8"?>
    <layout>
        <data>
            <variable
                name="dataObject"
                type="com.mm.rcyclerviewadoptorexperiment.DataItems" />
        </data>
    <LinearLayout
        xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent"
        android:layout_height="wrap_content">
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:text="@{dataObject.message}"
            />
    
    </LinearLayout>
    </layout>`

    const cod3 = `
    override fun getLayoutId(position: Int): Int {
        return when(position){
            0 -> R.layout.layout_header
            else -> R.layout.layout_row
        }
    }
    `
    const cod4 = `
    private val adapter = RcAdapter<DataItems>(this, this)
    adapter.setItems(createList())
    adapter.bindRecyclerView(binding.nameList)
    `
    const cod5 = `
    override fun bindView(view: View, getAdapterPosition: () -> Int) {
        view.setOnClickListener { Toast.makeText(this,"Click Position: \${getAdapterPosition()}",Toast.LENGTH_SHORT).show() }
    }
    `


    const desp = "This is Android Recyclerview Adapter  which encapsulate the boilerplate code and push all the UI related work into the parent activity class of the Recyclerview."
    return (
        <div className="content" >
            <Heading title="Description" />
            <Content descp={desp} /> <Heading title="Adding to project" />
            <Content descp="Enable databinding in the app build.gradle" />
            <Ccode sample={cod1} />
            <Content descp="Add the following line to the app build.gradle dependencies" />
            <Ccode sample="implementation 'com.irfan.android:rcadapter:1.0.0'" />
            <Heading title="Usage" />
            <ol style={{ paddingLeft: "10px" }}>
                <li>implement ItemLayoutManger</li>
                <li>create row layouts</li>
                <Ccode sample={cod2} />
                <li>Override the getLayoutId()</li>
                <Ccode sample={cod3} />
                <li>Initialiaze adapter, set data, and bind recyclerview</li>
                <Ccode sample={cod4} />
                <li>override bindView() (Optional) :Only if the handling of the view is required like for background color change and adding listeners.</li>
                <Ccode sample={cod5} />
            </ol>





        </div>
    )
}

export default Contents