package com.saber.aiintegration.data

import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.Buffer
import okio.BufferedSource
import okio.ForwardingSource
import okio.Source
import okio.buffer

class ProgressResponseBody(
    private val responseBody: ResponseBody,
    private val progressCallback: (Long, Long) -> Unit
) : ResponseBody() {
    private var bufferedSource: BufferedSource? = null

    override fun contentLength(): Long = responseBody.contentLength()

    override fun contentType(): MediaType? = responseBody.contentType()

    override fun source(): BufferedSource {
        if (bufferedSource == null) {
            bufferedSource = ProgressSource(responseBody.source(), contentLength(), progressCallback).buffer()
        }
        return bufferedSource!!
    }

    private class ProgressSource(
        source: Source,
        private val totalBytes: Long,
        private val progressCallback: (Long, Long) -> Unit
    ) : ForwardingSource(source) {
        private var bytesReadSoFar: Long = 0

        override fun read(sink: Buffer, byteCount: Long): Long {
            val bytesRead = super.read(sink, byteCount)
            if (bytesRead != -1L) {
                bytesReadSoFar += bytesRead
                progressCallback(bytesReadSoFar, totalBytes)
            }
            return bytesRead
        }
    }
}
