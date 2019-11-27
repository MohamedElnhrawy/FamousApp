package com.example.famousapp.ui.previewImage

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.example.famousapp.BuildConfig
import com.example.famousapp.R
import com.example.famousapp.famous.data.model.Profiles
import com.example.famousapp.famous.di.component.FragmentComponent
import com.example.famousapp.famous.ui.base.BaseFragment
import com.example.famousapp.famous.utils.common.GlideHelper
import com.example.famousapp.famous.utils.common.ImageUtils
import com.example.famousapp.famous.utils.common.ImageUtils.getImageName
import com.example.famousapp.famous.utils.common.ImageUtils.insertImage
import com.example.famousapp.famous.utils.common.ViewUtils.showAlertDialog
import com.example.famousapp.famous.utils.interfaces.DialogActions
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.image_preview_fragment.*
import kotlinx.android.synthetic.main.item_view_person.view.*


class ImagePreviewFragment :  BaseFragment<ImagePreviewViewModel>(),
    DialogActions, MultiplePermissionsListener {
    override fun onPermissionRationaleShouldBeShown(
        permissions: MutableList<PermissionRequest>?,
        token: PermissionToken?
    ) {

    }

    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
        showAlertDialog(context!!,this,getString(R.string.confirmation),getString(R.string.save_image))

    }

    override fun onAccept() {
//        ImageUtils.saveBitmap(context!!,iv_full.drawable.toBitmap(profile.width,profile.height)
//                  ,getImageName(profile.file_path))

        insertImage(context!!.getContentResolver(),iv_full.drawable.toBitmap(profile.width,profile.height)
            ,getImageName(profile.file_path))
//                  )
    }

    override fun onDecline() {
        Log.e("onDecline","onDecline")

    }

    lateinit var profile : Profiles

    companion object {

        const val TAG = "ImagePreviewFragment"

        fun newInstance(): ImagePreviewFragment {
            val args = Bundle()
            val fragment = ImagePreviewFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun provideLayoutId(): Int = R.layout.image_preview_fragment

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
           }

    override fun setupView(view: View) {
        profile = arguments!!.getParcelable("Profiles")!!
        Glide.with(view).load(BuildConfig.BASE_IMAGE_URL+profile.file_path).apply(GlideHelper.GlideRequetOptions(view.context)
            .override(profile.width, profile.height).error(R.drawable.ic_broken_image)).into(iv_full)

        iv_full.setOnLongClickListener( View.OnLongClickListener{
            checkPermission()
            return@OnLongClickListener true
        })
    }

    fun checkPermission(){
        Dexter.withActivity(activity).withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(this)
            .check()
    }




}
