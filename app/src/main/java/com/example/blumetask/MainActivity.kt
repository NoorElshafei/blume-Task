package com.example.blumetask

import android.app.Activity
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import at.wirecube.additiveanimations.additive_animator.AdditiveAnimator
import com.example.blumetask.fragments.HomeFragment
import com.example.blumetask.fragments.SearchFragment
import com.example.blumetask.model.Products
import com.example.blumetask.util.anim_menu
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_new_product_layout.view.*
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mdialog: ProgressDialog
    var saveUri: Uri? = null
    lateinit var newProduct: Products
    lateinit var productList: DatabaseReference
    lateinit var database: FirebaseDatabase
    lateinit var storage: FirebaseStorage
    private lateinit var storageReference: StorageReference
    lateinit var add_menu_layout: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //firebase
        database = FirebaseDatabase.getInstance()
        productList = database.getReference("Products")
        storage = FirebaseStorage.getInstance()
        storageReference = storage.reference

        search.setOnClickListener(this)
        home.setOnClickListener(this)
        add_product.setOnClickListener(this)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment.newInstance(), null).commit()


    }

    private fun showAddProductDialog() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setTitle("Add New Product")
        alertDialog.setMessage("Please Add Full Information")
        val inflater = this.layoutInflater
        add_menu_layout =
            inflater.inflate(R.layout.add_new_product_layout, null)


        //Event for button
        add_menu_layout.btnSelect.setOnClickListener {
            chooseImage() //user choose image and save it to firebase by URI
        }
        add_menu_layout.btnUpload.setOnClickListener { uploadImage() }
        alertDialog.setView(add_menu_layout)
        //alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp)
        alertDialog.setPositiveButton("YES") { dialog, which ->
            dialog.dismiss()
            //just create new category

        }
        alertDialog.setNegativeButton(
            "NO"
        ) { dialog, which -> dialog.dismiss() }
        alertDialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 71 && resultCode == RESULT_OK && data != null && data.data != null) {
            saveUri = data.data!!
            add_menu_layout.btnSelect.setText("Image Selected")
        }
    }

    private fun uploadImage() {
        if (saveUri != null) {
             mdialog = ProgressDialog(this)

            mdialog.setMessage("Uploadig...")
            mdialog.show()
            val imageName = UUID.randomUUID().toString()
            val imageFolder: StorageReference = storageReference.child("images/$imageName")
            imageFolder.putFile(saveUri!!)
                .addOnSuccessListener {
                    mdialog.dismiss()
                    Toast.makeText(this, "Uploaded !", Toast.LENGTH_SHORT).show()
                    imageFolder.downloadUrl
                        .addOnSuccessListener { uri -> //set value for image if image uploaded and get download link for it
                            newProduct = Products(
                                add_menu_layout.edt_condition.text.toString(),
                                add_menu_layout.edt_description.text.toString(),
                                uri.toString(),
                                add_menu_layout.edt_material.text.toString(),
                                "",
                                add_menu_layout.edtName.text.toString(),
                                add_menu_layout.edt_price.text.toString()
                            )

                            if (newProduct != null) {
                                productList.push().setValue(newProduct)
                                Snackbar.make(
                                    root,
                                    "New Product " + newProduct.name + " Was added",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }

                        }
                }
                .addOnFailureListener { e ->
                    mdialog.dismiss()
                    Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                }
                .addOnProgressListener { taskSnapshot ->
                    val progress: Double =
                        100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount()
                    mdialog.setMessage("Uploaded$progress%")
                }
        }
        else{
            Toast.makeText(this, "Please add all information", Toast.LENGTH_SHORT).show()
        }
    }


    private fun chooseImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Select Picture"),
            71
        )
    }

    override fun onClick(view: View?) {
        when (view) {
            search -> {

                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SearchFragment.newInstance(), null).commit()
            }
            home -> {

                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HomeFragment.newInstance(), null).commit()
            }
            add_product -> {

                showAddProductDialog()
            }

        }
    }



    override fun onStart() {
        super.onStart()
        anim_menu(
            findViewById(R.id.home),
            R.drawable.ic_home,
            R.drawable.ic_home,
            true
        )
    }

    override fun onStop() {
        super.onStop()
        anim_menu(
            findViewById(R.id.home),
            R.drawable.ic_home,
            R.drawable.ic_home,
            false
        )
    }



}