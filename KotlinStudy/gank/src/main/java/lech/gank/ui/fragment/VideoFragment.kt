package lech.gank.ui.fragment

import lech.gank.common.Type

/**
 * Created by Android_61 on 2017/6/9.
 * Description
 * Others
 */
class VideoFragment : ArticleFragment(){


    companion object{
        fun  newInstance():VideoFragment{
            return VideoFragment()
        }
    }


    override fun getType(): String {
        return Type.休息视频.name
    }
}