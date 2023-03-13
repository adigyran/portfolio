package com.aya.digital.core.data.base.dataprocessing

import com.aya.digital.core.networkbase.server.HttpResponseCode

typealias HttpCodeHandler = Pair<HttpResponseCode, ((errorList: List<IServerErrorModel>) -> Boolean)>