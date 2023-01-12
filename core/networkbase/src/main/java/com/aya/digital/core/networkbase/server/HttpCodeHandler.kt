package com.aya.digital.core.networkbase.server

typealias HttpCodeHandler = Pair<HttpResponseCode, ((errorList: List<IServerError>) -> Boolean)>