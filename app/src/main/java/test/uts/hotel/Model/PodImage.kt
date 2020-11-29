package test.uts.hotel.Model

import android.net.Uri

import java.io.Serializable

class PodImage : Serializable {
    lateinit var source: Uri
    var isSignature: Boolean = false
    var isUpload: Boolean = false
}
