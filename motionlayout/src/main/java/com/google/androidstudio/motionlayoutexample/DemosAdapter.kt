/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.androidstudio.motionlayoutexample

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

class DemosAdapter(private val myDataset: Array<DemosAdapter.Demo>) :
        RecyclerView.Adapter<DemosAdapter.ViewHolder>() {

    data class Demo(val title: String, val description : String, val layout : Int = 0, val activity : Class<*> = DemoActivity::class.java) {
        constructor(title: String, description: String, activity : Class<*> = DemoActivity::class.java) : this(title, description, 0, activity) {}
    }

    class ViewHolder(val layout: ConstraintLayout) : RecyclerView.ViewHolder(layout) {
        var title = layout.findViewById<TextView>(R.id.title)
        var description = layout.findViewById<TextView>(R.id.description)
        var layoutFileId = 0
        var activity : Class<*>? = null

        init {
            layout.setOnClickListener {
                val context = it?.context as MainActivity
                if (activity != null) {
                    context.start(activity!!, layoutFileId)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): DemosAdapter.ViewHolder {
        val row = LayoutInflater.from(parent.context)
                .inflate(R.layout.row, parent, false) as ConstraintLayout
        return ViewHolder(row)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = myDataset[position].title
        holder.description.text = myDataset[position].description
        holder.layoutFileId = myDataset[position].layout
        holder.activity = myDataset[position].activity
    }

    override fun getItemCount() = myDataset.size
}