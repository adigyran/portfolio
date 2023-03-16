package com.aya.digital.core.data.base.dataprocessing

typealias HttpCodeHandler = Pair<HttpResponseCode, ((errorList: List<IServerErrorModel>) -> Boolean)>