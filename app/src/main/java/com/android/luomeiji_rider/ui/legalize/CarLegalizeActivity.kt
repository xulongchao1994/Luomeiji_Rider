package com.android.luomeiji_rider.ui.legalize

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.DatePicker
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.luomeiji_rider.R
import com.android.luomeiji_rider.base.LBaseAppCompatActivity
import com.android.luomeiji_rider.data.UserData_Java
import com.android.luomeiji_rider.tools.LActivityTool
import com.android.luomeiji_rider.ui.legalizeresult.LegalizeresultsuccessActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.vondear.rxtool.RxPhotoTool
import com.vondear.rxtool.view.RxToast
import com.yalantis.ucrop.UCrop
import com.yalantis.ucrop.UCropActivity
import kotlinx.android.synthetic.main.activity_carlegalize.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class CarLegalizeActivity : LBaseAppCompatActivity<CarLegalizePersenter>(), ICarLegalizeView {
    override fun carlegalizesuccess(string: String) {
        RxToast.showToast(string)
        UserData_Java.islegalize = "1"
        editor!!.putString("certification", "1")
        editor!!.commit()
        startActivity(Intent(this, LegalizeresultsuccessActivity::class.java))
        finish()
    }

    override fun carlegalizeerror(string: String) {
        RxToast.showToast(string)
    }

    override fun initlayout(): Int {
        return R.layout.activity_carlegalize
    }

    var vehicleExpirationTime = ""
    var sp: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var carid = ""
    override fun initView() {
        LActivityTool.addActivity(this)
        sp = getSharedPreferences("USERINFO", Context.MODE_PRIVATE)
        carid = sp!!.getString("driveruserid", "").toString()
        editor = sp!!.edit()
        carlegalize_bt.setOnClickListener {
            var name_text = carlegalize_name.text.toString()
            var idnumber = carlegalize_idnumber.text.toString()
            if (name_text == "") {
                RxToast.normal("请输入姓名")
                return@setOnClickListener
            }
            if (idnumber == "") {
                RxToast.normal("请输入身份证号")
                return@setOnClickListener
            }
            if (phonefile1 == null || phonefile2 == null || phonefile3 == null) {
                RxToast.normal("请个人完善信息")
                return@setOnClickListener
            }
            mPersenter!!.carlegalize(name_text, idnumber, phonefile1!!, phonefile2!!, phonefile3!!, UserData_Java.userid)
        }
        carlegalize_two_car_zheng.setOnClickListener {
            openphoto(1)
        }
        carlegalize_two_buycar.setOnClickListener {
            openphoto(2)
        }
        carlegalize_two_varbaoxian.setOnClickListener {
            openphoto(3)
        }
    }

    private fun showdataload() {
        val c = Calendar.getInstance()
        var dataload = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                Log.d("时间", p1.toString() + "  " + p2.toString() + "  " + p3)
                vehicleExpirationTime = p1.toString() + "-" + p2.toString() + "-" + p3.toString()
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH))
        dataload.show()
    }

    override fun initPersenter() {
        mPersenter = CarLegalizePersenter(this, this)
    }

    override fun showlogin() {
    }

    override fun dissmisslogin() {
    }

    var imagenumber = 0
    private fun openphoto(imgnumber: Int) {
        if (Build.VERSION.SDK_INT >= 23) {
            val checkCallPhonePermission = ContextCompat.checkSelfPermission(
                    this, Manifest.permission.CAMERA
            )
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        arrayOf(
                                Manifest.permission.CAMERA
                        ), 111
                )
            } else {
                imagenumber = imgnumber
//                    val intent = Intent()
//                    intent.type = "image/*"
//                    intent.action = Intent.ACTION_GET_CONTENT
//                    startActivityForResult(intent, 1111)

                RxPhotoTool.openLocalImage(this)
//                    val dialogChooseImage =
//                        RxDialogChooseImage(this, RxDialogChooseImage.LayoutType.TITLE)
//                    dialogChooseImage.show()
            }
        } else {
            imagenumber = imgnumber
//                val intent = Intent()
//                intent.type = "image/*"
//                intent.action = Intent.ACTION_GET_CONTENT
//                startActivityForResult(intent, 1111)

            RxPhotoTool.openLocalImage(this)
//                val dialogChooseImage =
//                    RxDialogChooseImage(this, RxDialogChooseImage.LayoutType.TITLE)
//                dialogChooseImage.show()
        }
    }

    var resultUri1: Uri? = null
    var phonefile1: File? = null

    var resultUri2: Uri? = null
    var phonefile2: File? = null


    var resultUri3: Uri? = null
    var phonefile3: File? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            RxPhotoTool.GET_IMAGE_FROM_PHONE//选择相册之后的处理
            -> if (resultCode == Activity.RESULT_OK) {
                //                    RxPhotoTool.cropImage(ActivityUser.this, );// 裁剪图片
                initUCrop(data!!.data)
            }
            RxPhotoTool.GET_IMAGE_BY_CAMERA//选择照相机之后的处理
            -> if (resultCode == Activity.RESULT_OK) {
                /* data.getExtras().get("data");*/
                //                    RxPhotoTool.cropImage(ActivityUser.this, RxPhotoTool.imageUriFromCamera);// 裁剪图片
                initUCrop(RxPhotoTool.imageUriFromCamera)
            }
            RxPhotoTool.CROP_IMAGE//普通裁剪后的处理
            -> {
                val options = RequestOptions()
                        .placeholder(R.mipmap.ic_launcher)
                        //异常占位图(当加载异常的时候出现的图片)
                        .error(R.mipmap.ic_launcher)
                        //禁止Glide硬盘缓存缓存
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                when (imagenumber) {
                    1 -> {
                        Glide.with(this).load(RxPhotoTool.cropImageUri).apply(options).thumbnail(0.5f)
                                .into(carlegalize_two_car_zheng)
                        Log.d("图片地址1", RxPhotoTool.CROP_IMAGE.toString() + RxPhotoTool.cropImageUri.toString())
                    }
                    2 -> {
                        Glide.with(this).load(RxPhotoTool.cropImageUri).apply(options).thumbnail(0.5f)
                                .into(carlegalize_two_buycar)
                        Log.d("图片地址2", RxPhotoTool.CROP_IMAGE.toString() + RxPhotoTool.cropImageUri.toString())
                    }
                    3 -> {
                        Glide.with(this).load(RxPhotoTool.cropImageUri).apply(options).thumbnail(0.5f)
                                .into(carlegalize_two_varbaoxian)
                        Log.d("图片地址3", RxPhotoTool.CROP_IMAGE.toString() + RxPhotoTool.cropImageUri.toString())
                    }
                }
            }

            UCrop.REQUEST_CROP//UCrop裁剪之后的处理
            -> if (resultCode == Activity.RESULT_OK) {
                when (imagenumber) {
                    1 -> {
                        resultUri1 = UCrop.getOutput(data!!)
                        phonefile1 = roadImageView(resultUri1!!, carlegalize_two_car_zheng)
                        Log.d("图片地址1", UCrop.REQUEST_CROP.toString() + resultUri1.toString())
                        Log.d("图片地址1", UCrop.REQUEST_CROP.toString() + phonefile1.toString())
                    }
                    2 -> {
                        resultUri2 = UCrop.getOutput(data!!)
                        phonefile2 = roadImageView(resultUri2!!, carlegalize_two_buycar)
                        Log.d("图片地址2", UCrop.REQUEST_CROP.toString() + resultUri2.toString())
                        Log.d("图片地址2", UCrop.REQUEST_CROP.toString() + phonefile2.toString())
                    }
                    3 -> {
                        resultUri3 = UCrop.getOutput(data!!)
                        phonefile3 = roadImageView(resultUri3!!, carlegalize_two_varbaoxian)
                        Log.d("图片地址3", UCrop.REQUEST_CROP.toString() + resultUri3.toString())
                        Log.d("图片地址3", UCrop.REQUEST_CROP.toString() + phonefile3.toString())
                    }
                }
//                var file = File(resultUri.toString())
//                var requestFile = RequestBody.create(MediaType.parse("image/jpg"), file)
//                val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
//                var jsong = JSONObject()
//                var jsonarr = JSONArray()
//                jsonarr.put(KUtils.bitmapToStringforfile(this, phonefile!!.path))
//                jsong.put("data", jsonarr)
//                images = jsong.toString()
//                RxSPTool.putContent(this, "AVATAR", phonefile.toString())
            } else if (resultCode == UCrop.RESULT_ERROR) {
                val cropError = UCrop.getError(data!!)
            }
            //UCrop裁剪错误之后的处理
            UCrop.RESULT_ERROR -> {
                val cropError = UCrop.getError(data!!)
            }
            else -> {
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    //从Uri中加载图片 并将其转化成File文件返回
    private fun roadImageView(uri: Uri, imageView: ImageView): File {
        val options = RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                //异常占位图(当加载异常的时候出现的图片)
                .error(R.mipmap.ic_launcher)
//            .transform(CircleCrop())
                //禁止Glide硬盘缓存缓存
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        Glide.with(this).load(uri).apply(options).thumbnail(0.5f).into(imageView)

        return File(RxPhotoTool.getImageAbsolutePath(this, uri))
    }

    private fun initUCrop(uri: Uri?) {
        val timeFormatter = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA)
        val time = System.currentTimeMillis()
        val imageName = timeFormatter.format(Date(time))

        val destinationUri = Uri.fromFile(File(cacheDir, "$imageName.jpeg"))

        val options = UCrop.Options()
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL)
        //设置隐藏底部容器，默认显示
        //options.setHideBottomControls(true);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(this, R.color.colorPrimary))
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryDark))

        //开始设置
        //设置最大缩放比例
        options.setMaxScaleMultiplier(5f)
        //设置图片在切换比例时的动画f
        options.setImageToCropBoundsAnimDuration(666)
        //设置裁剪窗口是否为椭圆
        //options.setCircleDimmedLayer(true);
        //设置是否展示矩形裁剪框
        // options.setShowCropFrame(false);
        //设置裁剪框横竖线的宽度
        //options.setCropGridStrokeWidth(20);
        //设置裁剪框横竖线的颜色
        //options.setCropGridColor(Color.GREEN);
        //设置竖线的数量
        //options.setCropGridColumnCount(2);
        //设置横线的数量
        //options.setCropGridRowCount(1);
        Log.d("图片地址_图片剪裁", uri!!.path)
        UCrop.of(uri!!, destinationUri)
                .withAspectRatio(1f, 1f)
                .withMaxResultSize(1000, 1000)
                .withOptions(options)
                .start(this)

    }

}