package com.kursx.demo.common.domain

class HttpTextException(e: Throwable, val text: String) : Exception(e)
