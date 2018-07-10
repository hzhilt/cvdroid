package vip.frendy.cv

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_test.*
import vip.frendy.opencv.OpenCVManager

/**
 * Created by frendy on 2018/7/6.
 */
class TestActivity: AppCompatActivity() {

    private var mSeekbarType = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        //测试图片
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
        //显示原图
        image.setImageBitmap(bitmap)

        bw.setOnClickListener {
            //转换
            val bwBitmap = OpenCVManager.getInstance().toBW(bitmap)
            //显示图片
            image.setImageBitmap(bwBitmap)
        }

        bokeh.setOnClickListener {
            updateBokeh(bitmap, 10)
            mSeekbarType = 0
            seekbar.setProgress(10)
        }

        bokehCircle.setOnClickListener {
            updateBokehCircle(bitmap, 10)
            mSeekbarType = 1
            seekbar.setProgress(10)
        }

        enlarge.setOnClickListener {
            updateEnlarge(bitmap, 30)
            mSeekbarType = 2
            seekbar.setProgress(30)
        }

        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(mSeekbarType == 0) {
                    updateBokeh(bitmap, progress)
                } else if(mSeekbarType == 1) {
                    updateBokehCircle(bitmap, progress)
                } else if(mSeekbarType == 2) {
                    updateEnlarge(bitmap, progress)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    fun updateBokeh(bitmap: Bitmap, _blurSize: Int) {
        val blurSize = if(_blurSize <= 0) 0 else _blurSize
        //转换
        val bwBitmap = OpenCVManager.getInstance().toBokeh(bitmap,
                35, 35, 60, 60, blurSize)
        //显示图片
        image.setImageBitmap(bwBitmap)
    }

    fun updateBokehCircle(bitmap: Bitmap, _blurSize: Int) {
        val blurSize = if(_blurSize <= 0) 0 else _blurSize
        //转换
        val bwBitmap = OpenCVManager.getInstance().toBokehWithCircle(bitmap, 40, blurSize)
        //显示图片
        image.setImageBitmap(bwBitmap)
    }

    fun updateEnlarge(bitmap: Bitmap, strength: Int) {
        //转换
        val bigBitmap = OpenCVManager.getInstance().toEnlarge(bitmap, 80, 80, 40, strength);
        //显示图片
        image.setImageBitmap(bigBitmap)
    }
}