package com.aya.digital.core.data.mappers.profile

import com.aya.digital.core.data.model.profile.CurrentProfile
import com.aya.digital.core.network.model.response.profile.RoleResponse
import com.aya.digital.core.util.mappers.base.EntityMapper

abstract class RoleMapper : EntityMapper<RoleResponse,CurrentProfile.Role>