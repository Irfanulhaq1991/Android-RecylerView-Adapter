### Description
This is ***Android Recyclerview Adapter*** which encapsulate the boilerplate code and push all the UI related work into the parent activity class of the Recyclerview.

[ ![Download](https://api.bintray.com/packages/irfankhan/android_stuff/rcadapter/images/download.svg) ](https://bintray.com/irfankhan/android_stuff/rcadapter/_latestVersion)

### Adding to project
Enable databinding in the app build.gradle
   ```
   android{
   ...
    buildFeatures {
        dataBinding true
    
      }
    }
   ```

Add the following line to the app build.gradle dependencies
   ```
   implementation 'com.irfan.android:rcadapter:1.0.0'
   ```

### Usage

1-  implement ItemLayoutManger

2-  create row layouts 

    <?xml version="1.0" encoding="utf-8"?>
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
    </layout>

3- Override the getLayoutId()

        override fun getLayoutId(position: Int): Int {
        return when(position){
            0 -> R.layout.layout_header
            else -> R.layout.layout_row
        }
    }
    
4- Initialiaze adapter, set data, and bind recyclerview
   
    private val adapter = RcAdapter<DataItems>(this, this)
    adapter.setItems(createList())
    adapter.bindRecyclerView(binding.nameList)
   
5- override bindView() ***(Optional)*** :Only if the handling of the view is required like for background color change and adding listeners.

    override fun bindView(view: View, getAdapterPosition: () -> Int) {
        view.setOnClickListener { Toast.makeText(this,"Click Position: ${getAdapterPosition()}",Toast.LENGTH_SHORT).show() }
    }
 
 
 ### License
  
   Apache-2.0
    
    
   
   
    
   
    
    
