package com.example.zoolisting

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.view.*

class MainActivity : AppCompatActivity() {

    var listOfAnimals = ArrayList<Animal>()
    var adapter:AnimalsAdapter ?= null //instance of animalsadapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //load animals
        listOfAnimals.add(
            Animal("Андрей", "He is a baboon", R.drawable.baboon, false))
        listOfAnimals.add(
            Animal("Panda", "He is a baboon", R.drawable.panda, false))
        listOfAnimals.add(
            Animal("Bird", "He is a baboon", R.drawable.swallow_bird, false))
        listOfAnimals.add(
            Animal("Bulldog", "He is a baboon", R.drawable.bulldog, true))
        listOfAnimals.add(
            Animal("Zebra", "He is a baboon", R.drawable.zebra, false))
        listOfAnimals.add(
            Animal("Tiger", "He is a baboon", R.drawable.white_tiger, true))

        //create instance of adapter
        adapter = AnimalsAdapter(this, listOfAnimals)
        tvListAnimal.adapter = adapter
    }

    fun delete(index:Int) {
        listOfAnimals.removeAt(index)
        adapter!!.notifyDataSetChanged()

    }

    fun add(index:Int) {
        listOfAnimals.add(listOfAnimals[index])
        adapter.notifyDataSetChanged()
    }

    inner class AnimalsAdapter: BaseAdapter {
        var listOfAnimals = ArrayList<Animal>()
        var context: Context?=null
        constructor(context: Context, listOfAnimals: ArrayList<Animal>):super(){
            this.listOfAnimals = listOfAnimals
            this.context = context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val animal = listOfAnimals[p0]
            if (animal.isKiller == true) {
                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animal_killer_ticket, null)

                myView.tvName.text = animal.name
                myView.tvDes.text = animal.des
                //!! asserts that an expression is non-null
                myView.ivAnimalImage.setImageResource(animal.image!!)
                //lesson 95
                myView.setOnClickListener {
                    delete(p0)
               /*     val intent = Intent(context, AnimalInfo::class.java)
                    intent.putExtra("name", animal.name!!)
                    intent.putExtra("des", animal.des!!)
                    intent.putExtra("image", animal.image!!)
                    context!!.startActivity(intent)*/
                }
                myView.swip
                return myView
            } else {
                //!! asserts that an expression is non-null
                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animal_ticket, null)

                myView.tvName.text = animal.name
                myView.tvDes.text = animal.des
                //!! asserts that an expression is non-null
                myView.ivAnimalImage.setImageResource(animal.image!!)

                myView.setOnClickListener {
                    delete(p0)
//                    val intent = Intent(context, AnimalInfo::class.java)
//                    intent.putExtra("name", animal.name!!)
//                    intent.putExtra("des", animal.des!!)
//                    intent.putExtra("image", animal.image!!)
//                    context!!.startActivity(intent)
                }
                return myView
            }
        }
        //never used
        override fun getItem(p0: Int): Any {
            return  listOfAnimals[p0]

        }
        //never used
        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return listOfAnimals.size
        }

    }
}
