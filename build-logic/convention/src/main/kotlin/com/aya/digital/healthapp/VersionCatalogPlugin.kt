package com.aya.digital.healthapp

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.plugins.PluginManager

internal fun PluginManager.applyVersionCatalogPlugin(
    versionCatalog: VersionCatalog,
    alias: String
) = apply(versionCatalog.pluginId(alias))

internal fun PluginManager.applyVersionCatalogPlugins(
    versionCatalog: VersionCatalog,
    aliases: List<String>
) = aliases.map { versionCatalog.pluginId(it) }.filter { it.isNotEmpty() }.forEach {
    apply(it)
}

internal fun VersionCatalog.pluginId(alias: String): String {
    val plugin = this.findPlugin(alias)
    if (!plugin.isPresent) return ""
    return plugin.get().get().pluginId
}