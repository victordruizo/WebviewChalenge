package com.example.chalenge_webview.WebviewFragment

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.chalenge_webview.R
import com.example.chalenge_webview.databinding.FragmentMainBinding
import com.example.chalenge_webview.databinding.FragmentWebviewBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WebviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WebviewFragment : Fragment() {

    private lateinit var binding: FragmentWebviewBinding
    private lateinit var url : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString("url", "https://www.google.com.co/")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWebviewBinding.inflate(inflater, container, false)
        var webview = binding.webview
        var swipe = binding.swipeRefresh

        webview.webChromeClient = object : WebChromeClient(){
        }
        webview.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                swipe.isRefreshing = true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                swipe.isRefreshing = false
            }
        }
        webview.settings.javaScriptEnabled = true
        webview.loadUrl(url)

        swipe.setOnRefreshListener {
            webview.reload()
        }

        return binding.root
    }


}